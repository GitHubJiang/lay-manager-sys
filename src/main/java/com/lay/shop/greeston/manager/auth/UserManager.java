package com.lay.shop.greeston.manager.auth;

import java.util.Map;

import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.greeston.command.auth.UserCommand;
import com.lay.shop.greeston.command.auth.UserPrivilegeCommand;
import com.lay.shop.greeston.command.auth.UserRoleCommand;
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
    /**分页查询用户列表*/
    Pagination<User> findUserListByQueryMapWithPage(Page page, Sort[] sorts, Map<String, Object> params);
    /**更新用户状态*/
    void updateUserStatusById(Long id,Integer status);
    /**保存用户信息*/
    Long saveUser(UserCommand user);
    /**根据用户ID获取用户信息*/
    UserCommand findUserCommandByUserId(Long userId);
    /**删除用户角色关联信息 */
    void removeUserRoleById(Long id);
    /**保存用户角色组织关联信息*/
    void saveUserRole(UserRoleCommand command);
}
