package com.lay.shop.greeston.manager.auth.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lay.shop.greeston.command.auth.UserPrivilegeCommand;
import com.lay.shop.greeston.dao.auth.RolePriDao;
import com.lay.shop.greeston.dao.auth.UrlDao;
import com.lay.shop.greeston.dao.auth.UserDao;
import com.lay.shop.greeston.manager.auth.UserManager;
import com.lay.shop.greeston.model.auth.RolePri;
import com.lay.shop.greeston.model.auth.User;

@Service("userManager")
public class UserManagerImpl implements UserManager {

    @Autowired
    private UserDao userDao;    
    @Autowired
    private RolePriDao rolePriDao;
    @Autowired
    private UrlDao urlDao;
    
    @Override
    public User findUserByIdOrLoginName(Long id, String loginName) {        
        return this.userDao.findUserByIdOrLoginName(id, loginName);
    }

    @Override
    public UserPrivilegeCommand findUserPrivilegeByLoginName(String loginName) {        
        UserPrivilegeCommand userPrivilegeCommand = this.userDao.findUserPrivilegeByLoginName(loginName);    
        //获取用户所拥有的角色和权限
        List<RolePri> rpList = this.rolePriDao.findRolePriByUserId(userPrivilegeCommand.getUser().getId());        
        userPrivilegeCommand.setPriFunMap(this.rolePrivilegeToMap(rpList));
        //获取用户拥有的Url
        userPrivilegeCommand.setUrlList(this.urlDao.findUrlByUserId(userPrivilegeCommand.getUser().getId()));
        return userPrivilegeCommand;
    }

    
    private Map<String, List<String>> rolePrivilegeToMap(List<RolePri> rpList) {
        if (rpList == null || rpList.size() == 0) {
            return null;
        }
        
        Map<String, List<String>> map = new HashMap<String, List<String>>(rpList.size());
        List<String> funCodeList = null;
        for (RolePri rolePrivilege : rpList) {
            funCodeList = map.get(rolePrivilege.getAcl());
            if (funCodeList == null) {
                funCodeList = new ArrayList<String>();
                map.put(rolePrivilege.getAcl(), funCodeList);
            }
            funCodeList.add(rolePrivilege.getFunCode());
        }
        
        return map;
    }
}
