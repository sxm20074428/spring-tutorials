package com.spring.service;

import com.spring.domain.User;

public interface UserServcie {

    User queryById(Integer id); 

    Boolean updateUserById(Integer id);

    Boolean updateUser(Integer id);

}
