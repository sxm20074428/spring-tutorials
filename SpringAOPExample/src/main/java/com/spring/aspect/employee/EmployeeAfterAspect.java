package com.spring.aspect.employee;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

/**
 * After Advice Example
 * After Throwing and After Returning advices.
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/4/18 11:08
 * @since 0.1
 */
@Aspect
public class EmployeeAfterAspect {

    @After("args(name)")
    public void logStringArguments(String name) {
        System.out.println("Running After Advice. String argument passed=" + name);
    }

    /**
     * use within in pointcut expression to apply advice to all the methods in the class.
     *
     * @param
     * @return
     * @author 苏晓蒙
     * @time 2017/4/18 11:10
     * @version 0.1
     * @since 0.1
     */
    @AfterThrowing("within(com.spring.domain.Employee)")
    public void logExceptions(JoinPoint joinPoint) {
        System.out.println("Exception thrown in Employee Method=" + joinPoint.toString());
    }

    /**
     * use @AfterReturning advice to get the object returned by the advised method.
     *
     * @param
     * @return
     * @author 苏晓蒙
     * @time 2017/4/18 11:10
     * @version 0.1
     * @since 0.1
     */
    @AfterReturning(pointcut = "execution(* getName())", returning = "returnString")
    public void getNameReturningAdvice(String returnString) {
        System.out.println("getNameReturningAdvice executed. Returned String=" + returnString);
    }

}
