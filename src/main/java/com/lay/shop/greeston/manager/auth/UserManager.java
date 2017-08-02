package com.lay.shop.greeston.manager.auth;

import com.lay.shop.greeston.command.auth.UserPrivilegeCommand;
import com.lay.shop.greeston.model.auth.User;
/**
 * 用户信息管理
 * @author Lay
 * @date 2017年8月2日 下午3:33:49
 * @since
 */
public interface UserManager {

    /**根据用户ID或者用户登录名 查询用户信息*/
    User findUserByIdOrLoginName(Long id, String loginName);
    
    /**获取用户信息、组织信息、权限信息*/
    UserPrivilegeCommand findUserPrivilegeByLoginName(String loginName);
}
