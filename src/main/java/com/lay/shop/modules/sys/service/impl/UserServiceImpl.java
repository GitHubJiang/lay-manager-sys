package com.lay.shop.modules.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lay.shop.common.exception.BusinessException;
import com.lay.shop.common.exception.ErrorCodes;
import com.lay.shop.common.utils.Validator;
import com.lay.shop.modules.sys.command.OrganizationCommand;
import com.lay.shop.modules.sys.command.UserCommand;
import com.lay.shop.modules.sys.dao.OrganizationDao;
import com.lay.shop.modules.sys.dao.UserDao;
import com.lay.shop.modules.sys.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;    
    @Autowired
    private OrganizationDao organizationDao;
    
    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public UserCommand findUserByLoginNameAndEncryptedPassword(String loginName,String password) {
        if(Validator.isNullOrEmpty(loginName)){
            throw new BusinessException(ErrorCodes.LOGIN_NAME_NO);
        }
        if(Validator.isNullOrEmpty(password)){
            throw new BusinessException(ErrorCodes.LOGIN_PASSWORD_NO);
        }
        UserCommand userCommand = userDao.findUserByLoginNameAndEncryptedPassword(loginName, password);
        if(!Validator.isNullOrEmpty(userCommand)){
            List<OrganizationCommand> organizationList = organizationDao.findOrganizationByUserId(userCommand.getId());
            userCommand.setOrganizationList(organizationList);
        }        
        return userCommand;
    }

}
