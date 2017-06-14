package com.lay.shop.modules.sys.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lay.shop.common.web.BaseController;
import com.lay.shop.modules.sys.command.LoginUserDetail;
import com.lay.shop.modules.sys.dao.MenuDao;
import com.lay.shop.modules.sys.entity.User;

/**
 * 
 * @author Lay
 * @date 2017年6月9日 上午9:58:37
 * @since
 */
@Controller
@RequestMapping("/sys/menu/")
public class MenuController extends BaseController {
    
    
    
    @RequestMapping(value = "indexMenu")
    @ResponseBody
    public List menu(User user, Model model) {
        LoginUserDetail userDetails = (LoginUserDetail) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        
        return null;
    }    
}
