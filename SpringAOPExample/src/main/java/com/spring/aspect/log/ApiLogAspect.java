package com.spring.aspect.log;

import com.ane.luban.common.utils.json.JacksonHelper;
import com.spring.annotation.log.Log;
import com.spring.aspect.log.model.ApiLog;
import com.spring.uitls.IPAddressUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * api 日志切面
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/7/20 22:02
 * @since 0.1
 */
@Aspect
@Component
public class ApiLogAspect {
    /**
     * 操作成功
     */
    private static final int STATUS_SUCCESS = 1;
    /**
     * 操作失败
     */
    private static final int STATUS_FAIL = 0;

    /**
     * 请求的类型
     */
    private static String CONTENT_TYPE = "content-type";

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiLogAspect.class);

    // Controller层切点，@annotation用于匹配当前执行方法持有指定注解的方法；
    @Pointcut("@annotation(com.spring.annotation.log.Log)")
    public void logAspect() {
    }

    @Around("logAspect()")
    public Object around(ProceedingJoinPoint pjp) {

        ApiLog apiLog = new ApiLog();
        Object retVal = null;
        try {
            long startTime = System.currentTimeMillis();
            retVal = pjp.proceed();
            long endTime = System.currentTimeMillis();

            HttpServletRequest request = getRequest();
            //请求地址
            apiLog.setReqUrl(request.getRequestURI());

            //请求ip
            apiLog.setClientIp(IPAddressUtils.getIpAddress(request));

            // 请求类名称
            String targetName = pjp.getSignature().getDeclaringTypeName();
            apiLog.setClazz(targetName);

            // 请求方法
            String methodName = pjp.getSignature().getName();
            apiLog.setMethod(methodName);

            //操作事件
            Class<?> targetClass = Class.forName(targetName);
            Method[] methods = targetClass.getMethods();
            String value;
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Log annotation = AnnotationUtils.findAnnotation(method, Log.class);
                    if (annotation != null) {
                        value = annotation.value();
                        apiLog.setEvent(value);
                    }
                }
            }

            //请求类型
            apiLog.setRequestContentType(request.getHeader(CONTENT_TYPE));
            //请求参数
            apiLog.setRequestContent(JacksonHelper.convertObjectToJson(pjp.getArgs()));

            apiLog.setStartTime(startTime);
            apiLog.setEndTime(endTime);
            apiLog.setExecuteTime(endTime - startTime);

            //请求响应结果
            apiLog.setResponseInfo(JacksonHelper.convertObjectToJson(retVal));
            //请求结果
            apiLog.setStatus(STATUS_SUCCESS);

            LOGGER.info("logAspect()", apiLog);

        } catch (Throwable e) {
            apiLog.setStatus(STATUS_FAIL);
            LOGGER.error("接口异常", apiLog, e);
        }

        return retVal;
    }

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

}