package com.lay.shop.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.lay.shop.modules.sys.command.UserCommand;
import com.lay.shop.modules.sys.dao.UserDao;
import com.lay.shop.modules.sys.entity.User;
import com.lay.shop.modules.sys.service.UserService;

public class UserServiceImpl implements UserService,UserDetailsService{

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userDao.findUserInfoByLoginName(username);
        UserDetails userDetail = new UserCommand(user);
        return userDetail;
    }

    @Override
    public void save(User user) {
        
    }
}
