package com.spring.aspect.log;

import com.spring.annotation.log.Log;
import com.spring.aspect.log.model.OperateLog;
import com.spring.uitls.IPAddressUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 操作日志切面
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/7/20 22:02
 * @since 0.1
 */
@Aspect
@Component
public class OperateLogAspect {

    /**
     * 操作成功
     */
    private static final int OPER_LOG_STATUS_SUCCESS = 1;
    /**
     * 操作失败
     */
    private static final int OPER_LOG_STATUS_FAIL = 0;

    @Autowired
    private HttpServletRequest request;

    private static final Logger LOGGER = LoggerFactory.getLogger(OperateLogAspect.class);

    @Pointcut("@annotation(com.spring.annotation.log.Log)")
    public void logAspect() {
    }

    /**
     * 后置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     * @param rvt       指定一个 returning 属性，该属性值为 rvt , 表示允许在增强处理方法中使用名为rvt的形参，该形参代表目标方法的返回值。
     */
    @AfterReturning(pointcut = "logAspect()", returning = "rvt")
    public void after(JoinPoint joinPoint, Object rvt) {
        OperateLog operateLog = new OperateLog();
        try {
            Method method = null;
            if (joinPoint instanceof MethodInvocationProceedingJoinPoint) {
                Signature signature = joinPoint.getSignature();
                if (signature instanceof MethodSignature) {
                    MethodSignature methodSignature = (MethodSignature) signature;
                    method = methodSignature.getMethod();
                }
            }
            String value;
            StringBuilder descr = new StringBuilder();
            // 如果包含注解@Log()
            if (method != null && method.getAnnotation(Log.class) != null) {
                //操作事件
                value = method.getAnnotation(Log.class).value();
                operateLog.setEvent(value);
                //字段组装描述内容,例如anEntry[0]="parameter1=参数1",anEntry[2]="status=状态,1=成功;2=失败"
                String[] anEntry = method.getAnnotation(Log.class).entry();
                for (String en : anEntry) {
                    String[] entry = en.split(",");
                    String[] nameArray = entry[0].split("=");
                    String val = StringUtils.defaultString(request.getParameter(nameArray[0]), "");
                    if (!StringUtils.isBlank(val)) {
                        if (entry.length == 2) {
                            String[] valueEntry = entry[1].split(";");
                            for (String valueArray : valueEntry) {
                                String[] vals = valueArray.split("=");
                                if (vals[0].equalsIgnoreCase(val)) {
                                    val = vals[1];
                                    break;
                                }
                            }
                        }
                        descr.append(',');
                        descr.append(nameArray[1]);
                        descr.append('=');
                        descr.append(val);
                    }
                }
                if (descr.length() > 0) {
                    descr.deleteCharAt(0);
                }
            }

            operateLog.setUserName("sxm");
            operateLog.setUserId(123456);
            operateLog.setOperateTime(new Date());
            operateLog.setStatus(OPER_LOG_STATUS_SUCCESS);

            operateLog.setClientIp(IPAddressUtils.getIpAddress(request));
            operateLog.setReqUrl(request.getRequestURI());
            operateLog.setClazz(joinPoint.getTarget().getClass().getSimpleName());
            operateLog.setMethod(joinPoint.getSignature().getName());
            operateLog.setDescription("该方法实际入参为：" + descr.toString()); // 描述信息

            LOGGER.info("OperateLogAspect.after()", operateLog.toString());

        } catch (Exception e) {
            operateLog.setStatus(OPER_LOG_STATUS_FAIL);
            LOGGER.error("后置通知异常:异常信息:", operateLog, e);
        }
    }


}