package com.sxm.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {

        logger.info("Welcome home! The client locale is {}.", locale);
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);

        model.addAttribute("serverTime", formattedDate);

        return "front/home/home";
    }

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/index2", method = RequestMethod.GET)
    public String home2(Locale locale, Model model) {
        model.addAttribute("msg", "sxm2");
        return "front/home/home";
    }

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/index3", method = RequestMethod.GET)
    public String home3(Locale locale, Model model) {
        model.addAttribute("serverTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        model.addAttribute("msg", "sxm3");
        return "front/home/home";
    }

    @RequestMapping("*")
    @ResponseBody
    public String fallbackMethod() {
        return "fallback method";
    }
}
