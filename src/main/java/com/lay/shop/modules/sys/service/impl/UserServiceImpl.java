package com.lay.shop.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lay.shop.common.exception.BusinessException;
import com.lay.shop.common.exception.ErrorCodes;
import com.lay.shop.common.utils.Validator;
import com.lay.shop.modules.sys.command.UserCommand;
import com.lay.shop.modules.sys.dao.UserDao;
import com.lay.shop.modules.sys.model.User;
import com.lay.shop.modules.sys.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;
    
    @Override
    public UserCommand findUserByLoginNameAndEncryptedPassword(String loginName,String password) {
        if(Validator.isNullOrEmpty(loginName)){
            throw new BusinessException(ErrorCodes.LOGIN_NAME_NO);
        }
        if(Validator.isNullOrEmpty(password)){
            throw new BusinessException(ErrorCodes.LOGIN_PASSWORD_NO);
        }
        UserCommand userCommand = userDao.findUserByLoginNameAndEncryptedPassword(loginName, password);
        return userCommand;
    }

    @Override
    public void save(User user) {
        // TODO Auto-generated method stub        
    }
    
}
