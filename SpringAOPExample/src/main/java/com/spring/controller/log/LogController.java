package com.spring.controller.log;

import com.spring.annotation.log.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/7/21 15:37
 * @since 0.1
 */
@Controller
public class LogController {

    @Log(value = "新增", entry = {"parameter1=参数1", "status=状态,1=成功;2=失败"})
    @GetMapping(value = "/guest")
    @ResponseBody
    public String work(String parameter1, Integer status) {
        return "ok";
    }
}
