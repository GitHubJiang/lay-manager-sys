package com.lay.shop.greeston.manager.auth.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lay.shop.common.constants.Constants;
import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.greeston.command.auth.UserCommand;
import com.lay.shop.greeston.command.auth.UserPrivilegeCommand;
import com.lay.shop.greeston.command.auth.UserRoleCommand;
import com.lay.shop.greeston.dao.auth.RolePriDao;
import com.lay.shop.greeston.dao.auth.UrlDao;
import com.lay.shop.greeston.dao.auth.UserDao;
import com.lay.shop.greeston.dao.auth.UserRoleDao;
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
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public User findUserByIdOrLoginName(Long id, String loginName) {
        return this.userDao.findUserByIdOrLoginName(id, loginName);
    }

    @Override
    public UserPrivilegeCommand findUserPrivilegeByLoginName(String loginName) {
        UserPrivilegeCommand userPrivilegeCommand = this.userDao.findUserPrivilegeByLoginName(loginName);
        // 获取用户所拥有的角色和权限
        List<RolePri> rpList = this.rolePriDao.findRolePriByUserId(userPrivilegeCommand.getUser().getId());
        userPrivilegeCommand.setPriFunMap(this.rolePrivilegeToMap(rpList));
        // 获取用户拥有的Url
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

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Pagination<User> findUserListByQueryMapWithPage(Page page, Sort[] sorts, Map<String, Object> params) {
        return this.userDao.findUserListByQueryMapWithPage(page, sorts, params);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUserStatusById(Long id, Integer status) {
        User user = this.userDao.findById(id);
        user.setLifecycle(status);
        this.userDao.update(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Long saveUser(UserCommand userCommand) {
        if (userCommand.getId() == null) {
            User user = new User();
            BeanUtils.copyProperties(userCommand, user);
            user.setLifecycle(Constants.LIFECYCLE_NORMAL);
            user.setCreateTime(new Date());
            this.userDao.insert(user);
            return user.getId();
        } else {
            User user = this.userDao.findById(userCommand.getId());
            user.setUserName(userCommand.getUserName());
            user.setJobNumber(userCommand.getJobNumber());
            user.setEmail(userCommand.getEmail());
            user.setMobile(userCommand.getMobile());
            this.userDao.update(user);
            return user.getId();
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public UserCommand findUserCommandByUserId(Long userId) {
        UserCommand userCommand = new UserCommand();
        User user = this.userDao.findById(userId);
        BeanUtils.copyProperties(user, userCommand);
        List<UserRoleCommand> list = this.userRoleDao.findUserRoleOUByUserId(userId);
        userCommand.setList(list);
        return userCommand;
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void removeUserRoleById(Long id) {
        this.userRoleDao.delete(id);
    }


}
