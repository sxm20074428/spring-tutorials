package com.spring.aspect.employee;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Spring Around Aspect Example
 * we can use Around aspect to cut the method execution before and after.
 * We can use it to control whether the advised method will execute or not.
 * We can also inspect the returned value and change it.
 * This is the most powerful advice and needs to be applied properly.
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/4/18 11:11
 * @since 0.1
 */
@Aspect
public class EmployeeAroundAspect {

    /**
     * Around advice are always required to have ProceedingJoinPoint as argument and we should use it's proceed() method to invoke the target object advised method.
     * If advised method is returning something, it's advice responsibility to return it to the caller program.
     * For void methods, advice method can return null.
     *
     * @param proceedingJoinPoint
     * @return
     */
    @Around("execution(* com.spring.domain.Employee.getName())")
    public Object employeeAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("Before invoking getName() method");
        Object value = null;
        try {
            value = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("After invoking getName() method. Return value=" + value);
        return value;
    }
}
