package com.lay.shop.greeston.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lay.shop.greeston.command.CheckCommand;

@Controller
@RequestMapping(value = {"/check"})
public class CheckController extends BaseController {
    
    
    @RequestMapping(value = {"/checkUniqueCode"})
    public Boolean checkUniqueCode(CheckCommand command){
        return Boolean.FALSE;
    }

}
