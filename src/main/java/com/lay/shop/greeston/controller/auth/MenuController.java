package com.lay.shop.greeston.controller.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lay.shop.common.web.bind.QueryBean;
import com.lay.shop.common.web.bind.QueryBeanParam;
import com.lay.shop.common.web.controller.BaseController;
import com.lay.shop.greeston.command.auth.MenuCommand;
import com.lay.shop.greeston.manager.auth.MenuManager;

@Controller
@RequestMapping(value = "/auth")
public class MenuController extends BaseController {

    @Autowired
    private MenuManager menuManager;
    
    @RequestMapping(value = {"/menu/list"})
    public String list(@QueryBeanParam QueryBean queryBean, Model model) {
        List<MenuCommand> sourceList = this.menuManager.findAllMenuItems();
        List<MenuCommand> rootList = new ArrayList<>();
        this.buildMenus(rootList, sourceList);
        model.addAttribute("list", rootList);
        return "modules/auth/menu/list";
    }
    
    @RequestMapping(value = {"/menu/allMenu"})
    @ResponseBody
    public List<MenuCommand> allMenu() {
        List<MenuCommand> list = this.menuManager.findAllMenuItems(); 
        return list;
    }
    
    /**因为用JS动态渲染页面没办法控制按钮  所有这里重新构建列表*/
    private void buildMenus(List<MenuCommand> rootList,List<MenuCommand> sourceList){
        for (int i = 0, l = sourceList.size(); i < l; i++) {
            MenuCommand menu = sourceList.get(i);
            if(menu.getParentId() == null || (menu.getChildList()!=null && !menu.getChildList().isEmpty())){
                rootList.add(menu);
                if(!menu.getChildList().isEmpty()){
                    buildMenus(rootList,menu.getChildList());
                }
            } else {
                rootList.add(menu);    
            }
        }
    }
}
