package com.spring.aspect.employee;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Advice with Custom Annotation Pointcut
 * we should keep the scope of pointcut expression as narrow as possible.
 * <p>
 * create a custom annotation and annotate the methods where we want the advice to be applied
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/4/18 13:33
 * @since 0.1
 */
@Aspect
public class EmployeeAnnotationAspect {

    @Before("@annotation(com.spring.annotation.log.Log)")
    public void myAdvice() {
        System.out.println("Executing myAdvice!!");
    }
}
