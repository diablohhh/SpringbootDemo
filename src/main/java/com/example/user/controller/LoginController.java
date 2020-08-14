package com.example.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author ZhangQiangLong
 * @Date 2020/8/14 15:59
 * @Text:
 **/
@Controller
public class LoginController {
    @GetMapping("/login")
    public String toLoginPage(){
        return "loginPage";
    }

    @GetMapping("/home")
    public String toHomePage(){
        return "homePage";
    }
}
