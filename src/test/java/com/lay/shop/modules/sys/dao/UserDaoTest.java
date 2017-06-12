package com.lay.shop.modules.sys.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.lay.shop.SpringTest;
import com.lay.shop.modules.sys.command.UserCommand;

public class UserDaoTest extends SpringTest{
    
    @Autowired
    private UserDao userDao;
    @Test
    public void findUserInfoByLoginNameTest(){
        UserCommand user = this.userDao.findUserInfoByLoginName("LayJiang");
        this.printPrettyJson(JSON.toJSONString(user));
        ;
    }
    
}
