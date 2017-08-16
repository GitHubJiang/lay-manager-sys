package com.lay.shop.greeston.controller.auth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lay.shop.common.exception.ErrorCodes;
import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.common.web.Result;
import com.lay.shop.common.web.bind.QueryBean;
import com.lay.shop.common.web.bind.QueryBeanParam;
import com.lay.shop.greeston.command.auth.UserCommand;
import com.lay.shop.greeston.command.auth.UserRoleCommand;
import com.lay.shop.greeston.controller.BaseController;
import com.lay.shop.greeston.manager.auth.UserManager;
import com.lay.shop.greeston.model.auth.User;

@Controller
@RequestMapping(value = "/auth")
public class UserController extends BaseController {

    @Autowired
    private UserManager userManager;
    /**用户列表页*/
    @RequestMapping(value = {"/user/list"})
    public String list(@QueryBeanParam QueryBean queryBean, Model model){
        Page page = queryBean.getPage();
        Sort[] sorts = queryBean.getSorts();
        Map<String, Object> param = queryBean.getParaMap(); 
        Pagination<User> pagination = this.userManager.findUserListByQueryMapWithPage(page, sorts, param);
        model.addAttribute("pagination",pagination);
        return "modules/auth/user/list";
    }
    
    /**启用禁用用户*/
    @RequestMapping(value = {"/user/changeStatus/{id}/{status}"})
    @ResponseBody
    public Boolean changeStatus(@PathVariable("id") Long id, @PathVariable("status") Integer status) {
        try {
           this.userManager.updateUserStatusById(id, status);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }
    
    /**跳转到新增用户页*/
    @RequestMapping(value = {"/user/toAdd"})
    public String toAdd(Long id,Model model){        
        model.addAttribute("id", id);
        return "modules/auth/user/add";
    }
    
    /**新增用户*/
    @RequestMapping(value = {"/user/add"})
    @ResponseBody
    public Result<Long> add(UserCommand command) {
        Result<Long> result = new Result<>();
        try {
            Long id = this.userManager.saveUser(command);
            result.setData(id);
        } catch (Exception e) {
            result.setCode(ErrorCodes.RESULT_NO.getValue());
            result.setMsg(ErrorCodes.RESULT_NO.getMsg());
        }
        
        return result;
    }
    
    /**获取用户信息*/
    @RequestMapping(value = {"/user/getUserInfo"})
    @ResponseBody
    public Result<UserCommand> findUserInfo(Long id) {
        Result<UserCommand> result = new Result<>();
        try {
            UserCommand command = this.userManager.findUserCommandByUserId(id);
            result.setData(command);
        } catch (Exception e) {
            logger.error(e.getMessage());
            result.setCode(ErrorCodes.RESULT_NO.getValue());
            result.setMsg(ErrorCodes.RESULT_NO.getMsg());
        }
        return result;
    }
    
    @RequestMapping(value = {"/user/removeUserRole"})
    @ResponseBody
    public Result<Object> removeUserRole(Long id) {
        Result<Object> result = new Result<>();
        try {
            this.userManager.removeUserRoleById(id);
            result.setData(null);
        } catch (Exception e) {
            logger.error(e.getMessage());
            result.setCode(ErrorCodes.RESULT_NO.getValue());
            result.setMsg(ErrorCodes.RESULT_NO.getMsg());
        }
        return result;
    }
    
    @RequestMapping(value = {"/user/saveUserRole"})
    @ResponseBody
    public Result<Object> saveUserRole(UserRoleCommand command) {
        Result<Object> result = new Result<>();
        try {
            this.userManager.saveUserRole(command);
            result.setData(null);
        } catch (Exception e) {
            logger.error(e.getMessage());
            result.setCode(ErrorCodes.RESULT_NO.getValue());
            result.setMsg(ErrorCodes.RESULT_NO.getMsg());
        }
        return result;
    }
    
}
