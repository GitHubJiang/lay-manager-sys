package com.lay.shop.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.lay.shop.modules.sys.command.UserCommand;
import com.lay.shop.modules.sys.dao.UserDao;
import com.lay.shop.modules.sys.model.User;
import com.lay.shop.modules.sys.service.UserService;

public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;
    
    @Override
    public UserCommand findUserByLoginNameAndEncryptedPassword(String loginName,String password) {
        UserCommand userCommand = userDao.findUserByLoginNameAndEncryptedPassword(loginName, password);
        return userCommand;
    }

    @Override
    public void save(User user) {
        // TODO Auto-generated method stub        
    }
    
}
