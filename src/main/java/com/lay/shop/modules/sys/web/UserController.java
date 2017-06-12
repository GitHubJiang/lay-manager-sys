package com.lay.shop.modules.sys.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lay.shop.common.web.BaseController;
import com.lay.shop.modules.sys.entity.User;

/**
 * 
 * @author Lay
 * @date 2017年6月9日 上午9:58:37
 * @since
 */
@Controller
public class UserController extends BaseController {

    @RequestMapping(value = "/login")
    public String login() {
        return "login";        
    }
    
    @RequestMapping(value = "/sys/user/index")
    public String index(User user, Model model) {
        return "modules/sys/userIndex";
    }
}
