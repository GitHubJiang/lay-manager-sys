package com.lay.shop.greeston.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lay.shop.greeston.command.CheckCommand;
import com.lay.shop.greeston.manager.CheckManager;

@Controller
@RequestMapping(value = {"/check"})
public class CheckController extends BaseController {
    
    @Autowired
    private CheckManager checkManager;
    
    @RequestMapping(value = {"/checkUniqueCode"})
    @ResponseBody
    public Boolean checkUniqueCode(CheckCommand command){
        return this.checkManager.checkUniqueCode(command);
    }

}
