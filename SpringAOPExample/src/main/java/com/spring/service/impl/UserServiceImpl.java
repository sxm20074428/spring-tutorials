package com.spring.service.impl;

import com.spring.annotation.cache.Cache;
import com.spring.annotation.cache.CacheEvict;
import com.spring.annotation.cache.VisitorCache;
import com.spring.domain.User;
import com.spring.mapper.UserMapper;
import com.spring.service.UserServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class UserServiceImpl implements UserServcie {

    @Autowired
    private UserMapper userMapper;

    /**
     * 缓存查询
     */
    @Cache(key = "userById", field = "#id", expire = 50)
    public User queryById(Integer id) {
        System.out.println("数据库查询........................");
        return userMapper.getUserById(id);
    }


    @CacheEvict(key = "userById", field = "#id")
    public Boolean updateUserById(Integer id) {
        User user = userMapper.getUserById(id);
        if (null == user) {
            return false;
        }
        user.setUpdateTime(new Date());
        return userMapper.update(user) > 0;
    }


    @VisitorCache(tableName = "t_user", tableColumn = "visit_count", idName = "id", id = "#id")
    public Boolean updateUser(Integer id) {
        User user = userMapper.getUserById(id);
        if (null == user) {
            return false;
        }
        user.setUpdateTime(new Date());
        return userMapper.update(user) > 0;
    }


}
