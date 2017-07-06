package com.lay.shop.modules.sys.web;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lay.shop.common.web.BaseController;
import com.lay.shop.modules.sys.command.LoginUserDetail;
import com.lay.shop.modules.sys.model.SysUser;

/**
 * 
 * @author Lay
 * @date 2017年6月9日 上午9:58:37
 * @since
 */
@Controller
@RequestMapping("/sys/user/")
public class UserController extends BaseController {
    
    @RequestMapping(value = "userInfo")
    public String index(SysUser user, Model model) {
        LoginUserDetail userDetails = (LoginUserDetail) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        model.addAttribute("userDetails", userDetails.getUser());
        return "sys/userInfo";
    }
    
    @RequestMapping(value = "updateUserInfoSelf")
    public String updateUserInfoSelf(SysUser user, Model model) {
        return "sys/userInfo";
    }
    
    @RequestMapping(value = "updateUserInfoByAdmin")
    public String updateUserInfoByAdmin(SysUser user, Model model) {
        return "sys/userInfo";
    }
    
    @RequestMapping(value = "updateUserPassword")
    public String updateUserPassword(SysUser user, Model model) {
        return "sys/userInfo";
    }
}
