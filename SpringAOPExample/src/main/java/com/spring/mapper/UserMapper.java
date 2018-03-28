package com.spring.mapper;

import com.spring.domain.User;

public interface UserMapper {

    User getUserById(Integer id);

    int update(User user);

}
