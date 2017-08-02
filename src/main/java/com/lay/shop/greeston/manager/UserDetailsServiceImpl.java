package com.lay.shop.greeston.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.lay.shop.greeston.command.auth.UserDetailsCommand;
import com.lay.shop.greeston.manager.auth.UserManager;
import com.lay.shop.greeston.model.auth.User;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserManager userManager;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailsCommand command = this.constructByUsername(username);
        return command;
    }
    
    private UserDetailsCommand constructByUsername(String username) throws UsernameNotFoundException {
        User user = this.userManager.findUserByIdOrLoginName(null, username);
        if (user == null) {
            throw new UsernameNotFoundException("can't found user " + username);
        }
        
        return new UserDetailsCommand(user);
    }

}
