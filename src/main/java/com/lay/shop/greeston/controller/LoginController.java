package com.lay.shop.greeston.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lay.shop.common.web.controller.BaseController;

@Controller
public class LoginController extends BaseController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/index")
    public String index() {     
        
        return "index";
    }
}