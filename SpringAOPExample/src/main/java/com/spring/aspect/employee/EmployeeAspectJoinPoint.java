package com.spring.aspect.employee;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * JoinPoint and Advice Arguments
 * We can use JoinPoint as parameter in the advice methods and using it get the method signature or the target object.
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/4/18 11:02
 * @since 0.1
 */
@Aspect
public class EmployeeAspectJoinPoint {

    /**
     * . 代表entity
     *
     * @param joinPoint
     */
    @Before("execution(public void com.spring.domain..set*(*))")
    public void loggingAdvice(JoinPoint joinPoint) {

        System.out.println("Before running loggingAdvice on method=" + joinPoint.toString());
        // 请求类名称
        String targetName = joinPoint.getTarget().getClass().getName();
        //获取方法名
        String mathName = joinPoint.getSignature().getName();
        //获取参数列表
        List<Object> args = Arrays.asList(joinPoint.getArgs());

        System.out.println("前置通知---->before   方法名是:" + mathName + "\t参数列表是:" + args);

    }

    /**
     * args() expression in the pointcut to be applied to any method that matches the argument pattern.
     * If we use this, then we need to use the same name in the advice method from where argument type is determined
     * We can use Generic objects also in the advice arguments.
     * <p>
     * Advice arguments, will be applied to bean methods with single String argument
     *
     * @param name
     * @author 苏晓蒙
     * @time 2016/3/25  21:07
     * @version 0.1
     * @since 0.1
     */
    @Before("args(name)")
    public void logStringArguments(String name) {
        System.out.println("String argument passed=" + name);
    }
}
