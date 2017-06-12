package com.lay.shop.modules.sys.command;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class LoginUserDetail implements UserDetails{

    /** */
    private static final long serialVersionUID = -9153862480568039220L;
    
    private UserCommand user;
    
    public LoginUserDetail() { }

    public LoginUserDetail(UserCommand user) {
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.getUser().getPassword();
    }

    @Override
    public String getUsername() {
        return this.getUser().getLoginName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserCommand getUser() {
        return user;
    }

    public void setUser(UserCommand user) {
        this.user = user;
    }

    
}
