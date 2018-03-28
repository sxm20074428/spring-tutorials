package com.spring.aspect.employee;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Pointcut Methods and Reuse
 * Sometimes we have to use same Pointcut expression at multiple places,
 * we can create an empty method with @Pointcut annotation and then use it as expression in advices.
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/4/18 11:01
 * @since 0.1
 */
@Aspect
public class EmployeeAspectPointcut {

    @Before("getNamePointcut()")
    public void loggingAdvice() {
        System.out.println("Executing loggingAdvice on getName()");
    }

    @Before("getNamePointcut()")
    public void secondAdvice() {
        System.out.println("Executing secondAdvice on getName()");
    }

    @Pointcut("execution(public String getName())")
    public void getNamePointcut() {
    }

    @Before("allMethodsPointcut()")
    public void allServiceMethodsAdvice() {
        System.out.println("Before executing service method");
    }

    /**
     * Pointcut to execute on all the methods of classes in a package
     */
    @Pointcut("within(com.spring.service.*.*)")
    public void allMethodsPointcut() {
    }

}
