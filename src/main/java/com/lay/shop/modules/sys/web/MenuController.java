package com.lay.shop.modules.sys.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lay.shop.common.web.BaseController;
import com.lay.shop.modules.sys.command.LoginUserDetail;
import com.lay.shop.modules.sys.command.MenuCommand;
import com.lay.shop.modules.sys.entity.User;
import com.lay.shop.modules.sys.service.MenuService;

/**
 * 
 * @author Lay
 * @date 2017年6月9日 上午9:58:37
 * @since
 */
@Controller
@RequestMapping("/sys/menu/")
public class MenuController extends BaseController {
    
    @Autowired
    private MenuService menuService ;
    
    @RequestMapping(value = "indexMenu")
    @ResponseBody
    public List<MenuCommand> menu(@RequestParam(value="type")Integer type, @RequestParam(value="pid",required=false)Long pid) {
        LoginUserDetail userDetails = (LoginUserDetail) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        return this.menuService.findIndexMenu(type, pid, userDetails.getUsername());
    }    
}
