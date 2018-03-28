package com.spring.service.impl;

import com.spring.domain.Employee;
import com.spring.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

    private Employee employee;

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee e) {
        this.employee = e;
    }


}
