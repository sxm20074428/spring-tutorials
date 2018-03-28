package com.spring.annotation.cache.aspect;

import com.spring.annotation.cache.Cache;
import com.spring.annotation.cache.CacheEvict;
import com.spring.annotation.cache.VisitorCache;
import com.spring.annotation.cache.aspect.provider.CacheProvider;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 缓存切面定义
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2015年4月26日 上午9:20:07
 * @since 0.1
 */
@Aspect
@Component
public class CacheAspect {

    private static final Logger logger = LoggerFactory.getLogger(CacheAspect.class);

    @Autowired
    private CacheProvider cacheProvider;

    @Autowired
    private DataSource dataSource;
    /**
     * 是否开启缓存策略
     */
    private Boolean isOpenCache = true;

    /**
     * 定义环绕通知 处理 缓存 的 读取 以及 写入
     *
     * @param pjp
     * @return
     * @throws Throwable
     * @author 苏晓蒙
     * @time 2015年4月26日 上午9:35:23
     * @version 0.1
     * @since 0.1
     */
    @Around("@annotation(com.spring.annotation.cache.Cache)")
    public Object doCacheProfiling(ProceedingJoinPoint pjp) throws Throwable {
        Method targetMethod = getTargetMethod(pjp);
        Cache cacheAnnotation = targetMethod.getAnnotation(Cache.class);
        String condition = CacheAspect.parseKey(cacheAnnotation.condition(), targetMethod, pjp.getArgs());
        if (!isOpenCache || (!StringUtils.isEmpty(condition) && !Boolean.valueOf(condition))) {
            return pjp.proceed();
        }
        String key = cacheAnnotation.key();
        String filed = CacheAspect.parseKey(cacheAnnotation.field(), targetMethod, pjp.getArgs());
        Integer expire = cacheAnnotation.expire();
        Object retVal = null;
        try {
            retVal = cacheProvider.readCache(key, filed, targetMethod.getReturnType());
            if (null == retVal) {
                retVal = pjp.proceed();
                if (null != retVal) {
                    cacheProvider.writerCache(key, filed, expire, retVal);
                }
                return retVal;
            }
        } catch (Exception e) {
            logger.error("doCacheProfiling", e);
            return pjp.proceed();
        }
        return retVal;
    }

    /**
     * 定义环绕通知 清除 缓存
     *
     * @return
     * @throws Throwable
     * @author 苏晓蒙
     * @time 2015年4月26日 上午9:35:23
     * @version 0.1
     * @since 0.1
     */
    @Around("@annotation(com.spring.annotation.cache.CacheEvict)")
    public Object doEvictCacheProfiling(ProceedingJoinPoint pjp) throws Throwable {
        if (!isOpenCache) {
            return pjp.proceed();
        }
        try {
            Method targetMethod = getTargetMethod(pjp);
            CacheEvict cacheAnnotation = targetMethod.getAnnotation(CacheEvict.class);
            String cacheKey = cacheAnnotation.key();
            String[] fields = cacheAnnotation.field();
            if (null != fields && fields.length > 0) {
                for (String item : fields) {
                    String filed = CacheAspect.parseKey(item, targetMethod, pjp.getArgs());
                    cacheProvider.removeCache(cacheKey, filed);
                }
            }
        } catch (Exception e) {
            logger.error("doEvictCacheProfiling", e);
        }
        return pjp.proceed();
    }

    /**
     * 更新访问量处理
     *
     * @param pjp
     * @return
     * @throws Throwable
     * @author 苏晓蒙
     * @time 2015年6月8日 上午10:57:54
     * @version 0.1
     * @since 0.1
     */
    @Around("@annotation(com.spring.annotation.cache.VisitorCache)")
    public Object visitorCacheProfiling(ProceedingJoinPoint pjp) throws Throwable {
        if (!isOpenCache) {
            updateVisitorCountWhenNotCache(pjp);
            return pjp.proceed();
        }
        try {
            Method targetMethod = getTargetMethod(pjp);
            VisitorCache visitorCache = targetMethod.getAnnotation(VisitorCache.class);
            Map<String, Integer> cachesMap = cacheProvider.readCache(visitorCache.tableName(), visitorCache.tableColumn(), Map.class);
            if (null == cachesMap) {
                cachesMap = new HashMap<>();
            }
            String idValue = CacheAspect.parseKey(visitorCache.id(), targetMethod, pjp.getArgs());
            if (cachesMap.containsKey(idValue)) {
                cachesMap.put(idValue, cachesMap.get(idValue) + 1);
            } else {
                cachesMap.put(idValue, 1);
            }
            cacheProvider.writerCache(visitorCache.tableName(), visitorCache.tableColumn(), null, cachesMap);
        } catch (Exception e) {
            logger.error("visitorCacheProfiling", e);
            updateVisitorCountWhenNotCache(pjp);
        }
        return pjp.proceed();
    }

    /**
     * @param pjp
     * @throws SQLException
     * @author 苏晓蒙
     * @time 2015年5月23日 上午10:51:55
     * @version 0.1
     * @since 0.1
     */
    private void updateVisitorCountWhenNotCache(ProceedingJoinPoint pjp) throws SQLException {
        Method targetMethod = getTargetMethod(pjp);
        VisitorCache visitorCache = targetMethod.getAnnotation(VisitorCache.class);
        //  主键的名称
        String idName = visitorCache.idName();
        String tableName = visitorCache.tableName();
        String tableColumn = visitorCache.tableColumn();
        //主键的值
        String idValue = CacheAspect.parseKey(visitorCache.id(), targetMethod, pjp.getArgs());

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("UPDATE ").append(tableName).append(" SET ").append(tableColumn).append("= IF(").append(tableColumn).append(",").append(tableColumn).append("+1").append(",1)").append("  WHERE ").append(idName).append("=").append(idValue);

        Connection connetion = dataSource.getConnection();
        PreparedStatement pst = connetion.prepareStatement(stringBuilder.toString());
        pst.execute();
        if (null != pst) {
            try {
                pst.close();
            } finally {
                if (null != connetion) {
                    connetion.close();
                }
            }
        }
    }

    /**
     * 获取目标对象的方法
     *
     * @param pjp
     * @return
     * @author 苏晓蒙
     * @time 2015年4月26日 上午9:49:11
     * @version 0.1
     * @since 0.1
     */
    @SuppressWarnings("rawtypes")
    private Method getTargetMethod(ProceedingJoinPoint pjp) {
        Object[] args = pjp.getArgs();
        Class[] argTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] != null) {
                argTypes[i] = args[i].getClass();
            }
        }
        try {
            return pjp.getTarget().getClass().getMethod(pjp.getSignature().getName(), argTypes);
        } catch (NoSuchMethodException e) {
            Method[] targetMethods = pjp.getTarget().getClass().getMethods();
            for (int i = 0; i < targetMethods.length; i++) {
                if (!targetMethods[i].getName().equals(pjp.getSignature().getName())) {
                    continue;
                }
                if (classIsAssignable(targetMethods[i].getParameterTypes(), argTypes)) {
                    return targetMethods[i];
                }
            }
            logger.info("getTargetMethod", e);
        } catch (SecurityException e) {
            logger.error("getTargetMethod", e);
        }
        return null;
    }

    /**
     * 判断class数组中的 每一个是否是子类的class
     *
     * @param source
     * @param target
     * @return
     * @author 苏晓蒙
     * @time 2015年5月25日 上午11:22:35
     * @version 0.1
     * @since 0.1
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public boolean classIsAssignable(Class[] source, Class[] target) {
        if (source.length != target.length) {
            return false;
        }
        for (int i = 0; i < source.length; i++) {
            if (target[i] != null && !source[i].isAssignableFrom(target[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 解析 SpEL 表达式
     *
     * @param key
     * @param method
     * @param args
     * @return
     * @author 苏晓蒙
     * @time 2015年4月26日 上午11:35:50
     * @version 0.1
     * @since 0.1
     */
    private static String parseKey(String key, Method method, Object[] args) {
        if (StringUtils.isEmpty(key)) {
            return "";
        }
        LocalVariableTableParameterNameDiscoverer localVariableTableParameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = localVariableTableParameterNameDiscoverer.getParameterNames(method);
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < parameterNames.length; i++) {
            if (null == args[i]) {
                continue;
            }
            context.setVariable(parameterNames[i], args[i]);
        }
        String result = parser.parseExpression(key).getValue(context, String.class);
        return null == result ? "" : result;
    }

    public CacheProvider getCacheProvider() {
        return cacheProvider;
    }

    public void setCacheProvider(CacheProvider cacheProvider) {
        this.cacheProvider = cacheProvider;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Boolean getIsOpenCache() {
        return isOpenCache;
    }

    public void setIsOpenCache(Boolean isOpenCache) {
        this.isOpenCache = isOpenCache;
    }

}
