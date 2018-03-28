package com.spring.aspect.timeStat;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 *  aop统计请求执行时间
 * Created by Administrator on 2016/5/11.
 */
@Aspect
@Component
public class RequestProcessingTimeStat {

    private static final Logger logger = LoggerFactory.getLogger(RequestProcessingTimeStat.class);


    @Pointcut("execution(* com.spring.controller.*.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
        public Object around(ProceedingJoinPoint pjp) throws Throwable {

            long startTime = System.currentTimeMillis();
            Object retVal = pjp.proceed();
            long endTime = System.currentTimeMillis();
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String requestURI = request.getRequestURI();
            logger.debug("request uri : " + requestURI + " && request args : " + pjp.getArgs() + " && took : " + (endTime - startTime) + "ms");

            String className = pjp.getSignature().getDeclaringTypeName();
            String method = pjp.getSignature().getName();
            logger.debug("当前线程： " + Thread.currentThread() + " ========类名：" + className + " ========方法名：" + method + "=======执行时间：" + (endTime - startTime) + "毫秒");

            return retVal;
        }


}
