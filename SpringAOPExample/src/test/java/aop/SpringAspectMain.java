package aop;

import com.spring.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-aop.xml"})
public class SpringAspectMain {

    @Autowired
    private ApplicationContext ctx;

    @Test
    public void TestEmployeeAspect() {

        EmployeeService employeeService = ctx.getBean(EmployeeService.class);

        System.out.println("getName():" + employeeService.getEmployee().getName());

        System.out.println("getPassword():" + employeeService.getEmployee().getPassword());

        employeeService.getEmployee().setName("sxm");

        employeeService.getEmployee().throwException();

    }


}
