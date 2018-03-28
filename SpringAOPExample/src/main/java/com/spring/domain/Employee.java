package com.spring.domain;

import com.spring.annotation.log.Log;

public class Employee {

    private String name;

    private String password;

    public String getName() {
        return name;
    }

    @Log(value = "设置名字")
    public void setName(String nm) {
        this.name = nm;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void throwException() {
        throw new RuntimeException("sxm Exception");
    }

}
