package com.lay.shop.modules.sys.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.lay.shop.SpringTest;
import com.lay.shop.modules.sys.entity.User;

public class UserDaoTest extends SpringTest{
    
    @Autowired
    private UserDao userDao;
    @Test
    public void findUserInfoByLoginNameTest(){
        User user = this.userDao.findUserInfoByLoginName("LayJiang");
        this.printPrettyJson(JSON.toJSONString(user));
        ;
    }
    
}
