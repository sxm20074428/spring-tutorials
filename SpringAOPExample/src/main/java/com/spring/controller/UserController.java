package com.spring.controller;

import com.spring.annotation.cache.VisitorCache;
import com.spring.annotation.log.Log;
import com.spring.domain.User;
import com.spring.service.UserServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServcie userServcie;

    @VisitorCache(tableName = "t_user", tableColumn = "visit_count", idName = "id", id = "#id")
    @RequestMapping("/detail/{id}")
    public String queryUser(@PathVariable("id") Integer id, Model model) {
        User user = userServcie.queryById(id);
        model.addAttribute(null == user ? new User() : user);
        return "user/detail";
    }

    @RequestMapping("/update/{id}")
    public ModelAndView updateUser(@PathVariable("id") Integer id) {
        if (null != id) {
            userServcie.updateUserById(id);
        }
        return null;
    }


}
