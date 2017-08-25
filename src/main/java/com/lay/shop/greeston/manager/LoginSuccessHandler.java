package com.lay.shop.greeston.manager;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.lay.shop.common.constants.AuthConstants;
import com.lay.shop.common.constants.Constants;
import com.lay.shop.common.utils.JsonUtil;
import com.lay.shop.greeston.command.auth.MenuCommand;
import com.lay.shop.greeston.command.auth.OpUnitTreeCommand;
import com.lay.shop.greeston.command.auth.UserDetailsCommand;
import com.lay.shop.greeston.command.auth.UserPrivilegeCommand;
import com.lay.shop.greeston.manager.auth.MenuManager;
import com.lay.shop.greeston.manager.auth.OperationUnitManager;
import com.lay.shop.greeston.manager.auth.UserManager;
import com.lay.shop.greeston.model.auth.OperationUnit;


public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
    private UserManager userManager;	
	@Autowired
	private OperationUnitManager operationUnitManager;
	@Autowired
    private MenuManager menuManager;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetailsCommand userDetails = (UserDetailsCommand) authentication.getPrincipal();
        
        UserPrivilegeCommand userCommand = this.userManager.findUserPrivilegeByLoginName(userDetails.getUsername());
        userDetails.setCommand(userCommand);
        
        initialize(request, response, userDetails);
        
        response.sendRedirect(request.getContextPath()+"/index");
    }
    
    /**
	 * 初始化菜单和组织
	 * 
	 * @param session
	 */
	public void initialize(HttpServletRequest request, HttpServletResponse response, UserDetailsCommand udc) {
		HttpSession session = request.getSession();
		Cookie orgIdCookie = getCookieByName(request,AuthConstants.COOKIE_NAME_PREFIX + udc.getUser().getId());

		//String orgTree = (String) session.getAttribute(AuthConstants.ORG_LIST);
		
		List<OpUnitTreeCommand> otcList = operationUnitManager.findOpUnitTreeByUserId(udc.getUser().getId());//获取组织
        OpUnitTreeCommand unit =null;
        if(otcList!=null){
            unit=otcList.get(otcList.size()-1);//获取默认组织
            otcList.remove(otcList.size()-1);
            session.setAttribute(AuthConstants.ORG_LIST, JsonUtil.buildNormalBinder().toJson(otcList));// 初始化组织
            
            Long orgid = null;
            if (orgIdCookie != null) {
                String org = orgIdCookie.getValue();
                orgid = Long.parseLong(org);// 获取上次退出时使用的组织ID
            } else {
                // 获取默认组织
                orgid = unit.getId();
                // 新建COOKIE
                Cookie cookie = new Cookie(AuthConstants.COOKIE_NAME_PREFIX
                        + udc.getUser().getId(), orgid.toString());
                cookie.setMaxAge(7*24*60*60);
                response.addCookie(cookie);
            }
            OperationUnit opunit = operationUnitManager.get(orgid);
            //判断该组织生命周期是否正常，不正常则选择默认组织生成菜单
            if(opunit==null || opunit.getLifecycle()!=Constants.LIFECYCLE_NORMAL) {
                orgid = unit.getId();                   
            }
            udc.setCurrentOuId(orgid);
            
            List<MenuCommand> miList = menuManager.findLeftMenuItems(udc.getUser().getId(), orgid);
            
            
            session.setAttribute(AuthConstants.MENU_ITEMS, miList);// 初始化左菜单
            
            OperationUnit ou=new OperationUnit();
            ou.setId(orgid);
            List<OperationUnit> oulist=operationUnitManager.findListByParam(ou);
            if (oulist != null && !oulist.isEmpty()) {
                OperationUnit currentOu = oulist.get(0);
                udc.setCurrentOu(currentOu);
            }
        }
		
	}
	
	/**
	 * 根据名字获取cookie
	 * 
	 * @param request
	 * @param name
	 *            cookie名字
	 * @return
	 */
	public Cookie getCookieByName(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie;
		} else {
			return null;
		}
	}
	
	/**
	 * 将cookie封装到Map里面
	 * 
	 * @param request
	 * @return
	 */
	private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}

}
