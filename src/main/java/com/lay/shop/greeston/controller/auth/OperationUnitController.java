package com.lay.shop.greeston.controller.auth;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lay.shop.common.constants.AuthConstants;
import com.lay.shop.common.exception.ErrorCodes;
import com.lay.shop.common.web.Result;
import com.lay.shop.greeston.command.auth.MenuCommand;
import com.lay.shop.greeston.command.auth.UserDetailsCommand;
import com.lay.shop.greeston.controller.BaseController;
import com.lay.shop.greeston.manager.auth.MenuManager;
import com.lay.shop.greeston.manager.auth.OperationUnitManager;
import com.lay.shop.greeston.model.auth.OperationUnit;
@Controller
@RequestMapping(value = "/auth")
public class OperationUnitController extends BaseController {
    @Autowired
    private MenuManager menuManager;
    @Autowired
    private OperationUnitManager operationUnitManager;
    
    //切换组织
    @RequestMapping(value = "/org/change")
    public String changeOrganization(HttpServletRequest request,HttpServletResponse response,@RequestParam("ouId" ) String ouId){
        HttpSession session=request.getSession();
        UserDetailsCommand udc=getCurrentUserDetails();
        Long ouid=Long.parseLong(ouId);
        udc.setCurrentOuId(ouid);
        //重新加载菜单
        List<MenuCommand> miList=menuManager.findLeftMenuItems(udc.getUser().getId(), ouid);
        session.setAttribute(AuthConstants.MENU_ITEMS, miList);
        
        //当前组织
        OperationUnit ou=new OperationUnit();
        ou.setId(ouid);
        List<OperationUnit> oulist=operationUnitManager.findListByParam(ou);
        if(oulist!=null&&!oulist.isEmpty()){
            session.setAttribute(AuthConstants.ORG_TYPE, oulist.get(0));
            udc.setCurrentOu(oulist.get(0));
        }        
        //新建COOKIE
        Cookie cookie=new Cookie(AuthConstants.COOKIE_NAME_PREFIX+udc.getUser().getId(), ouId);
        cookie.setMaxAge(7*24*60*60);
        cookie.setPath("/");
        response.addCookie(cookie);
        
        return "redirect:/index";
    }
    
    /**获取组织列表*/
    @RequestMapping(value = {"/org/operationUnitList"})
    @ResponseBody
    public Result<List<OperationUnit>> getOperationUnitList(Long ouTypeId) {
        Result<List<OperationUnit>> result = new Result<>();
        try {
            OperationUnit operationUnit = new OperationUnit();
            operationUnit.setOuTypeId(ouTypeId);
            List<OperationUnit> list = this.operationUnitManager.findListByParam(operationUnit);            
            result.setData(list);
        } catch (Exception e) {
            logger.error(e.getMessage());
            result.setCode(ErrorCodes.RESULT_NO.getValue());
            result.setMsg(ErrorCodes.RESULT_NO.getMsg());
        }
        return result;
    }
}
