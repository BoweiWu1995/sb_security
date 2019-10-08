package com.mydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wuwei
 * @create 2019/10/8 9:21
 * @desc
 **/
@Controller
public class HomeController {

    @RequestMapping("/home")
    public String home(){
        return "home";
    }
}
