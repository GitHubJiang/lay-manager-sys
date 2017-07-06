package com.lay.shop.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.lay.shop.modules.sys.dao.SysUserDao;
import com.lay.shop.modules.sys.model.SysUser;
import com.lay.shop.modules.sys.service.UserService;

public class UserServiceImpl implements UserService,UserDetailsService{

    @Autowired
    private SysUserDao sysUserDao;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(SysUser user) {
        // TODO Auto-generated method stub
        
    }
    
}
