package com.spring.aspect.employee;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Before Aspect Example
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/4/18 11:01
 * @since 0.1
 */
@Aspect
public class EmployeeBeforeAspect {

    /**
     * getNameAdvice() advice will execute for any Spring Bean method with signature public String getName()
     */
    @Before("execution(public String getName())")
    public void getNameAdvice() {
        System.out.println("@Before: Executing Advice on getName()");
    }

    /**
     * 第一个 * 表示  所有修饰符的所有返回值类型
     * getAllAdvice() will be applied for all the classes in com.spring.service package whose name starts with get and doesn’t take any arguments.
     */
    @Before("execution(* com.spring.service.*.get*())")
    public void getAllAdvice() {
        System.out.println("@Before: Service method getter called");
    }
}
