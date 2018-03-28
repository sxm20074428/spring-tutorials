package com.spring.aspect.employee;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * I always prefer annotation but
 * we also have option to configure aspects in spring configuration file.
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/4/18 13:36
 * @since 0.1
 */
public class EmployeeXMLConfigAspect {

    public Object employeeAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("EmployeeXMLConfigAspect:: Before invoking getName() method");
        Object value = null;
        try {
            value = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("EmployeeXMLConfigAspect:: After invoking getName() method. Return value=" + value);
        return value;
    }
}
