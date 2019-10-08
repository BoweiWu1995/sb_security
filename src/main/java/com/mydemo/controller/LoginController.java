package com.mydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author wuwei
 * @create 2019/10/8 9:43
 * @desc
 **/
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/limit")
    public String limit(){
        return "limit";
    }
}
