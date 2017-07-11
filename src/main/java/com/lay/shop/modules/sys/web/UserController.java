package com.lay.shop.modules.sys.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lay.shop.common.web.BaseController;
import com.lay.shop.modules.sys.command.LoginUserDetail;
import com.lay.shop.modules.sys.model.SysUser;
import com.mysql.fabric.xmlrpc.base.Array;

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
    
    @RequestMapping(value = "menu")
    @ResponseBody
    public List<Map<String,Object>> menu(HttpServletRequest request) {
        String pid =  request.getParameter("pid");
        List<Map<String,Object>> list = new ArrayList<>();
        if(!"0".equals(pid)){
            Map<String,Object> map1 = new HashMap<>() ;
            map1.put("title", "系统配置");
            map1.put("pid", "0");
            Map<String,Object> map2 = new HashMap<>() ;
            map2.put("title", "库存管理");
            map2.put("pid", "1"); 
            list.add(map1);
            list.add(map2);
        }else{
            Map<String,Object> map1 = new HashMap<>() ;
            map1.put("title", "后台首页");
            map1.put("href", "html/main");
            Map<String,Object> map2 = new HashMap<>() ;
            map2.put("title", "用户管理");
            map2.put("spread", "true"); 
            Map<String,Object> children = new HashMap<>() ;
            children.put("title", "个人信息");
            children.put("href", "html/personInfo.html");
            Map<String,Object> children2 = new HashMap<>() ;
            children2.put("title", "修改密码");
            children2.put("href", "html/personInfo.html");
            List<Map<String,Object>> childrenList = new ArrayList<>();
            childrenList.add(children);
            childrenList.add(children2);
            map2.put("children", childrenList); 
            list.add(map1);
            list.add(map2);            
        }
        
        
        
        return list;
    }
}
