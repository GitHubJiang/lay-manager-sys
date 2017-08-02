package com.lay.shop.greeston.command.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lay.shop.common.constants.Constants;
import com.lay.shop.greeston.model.auth.User;

/***
 * 当前用户的所有信息，基于SpringSecurity
 * @author Lay
 * @date 2017年8月2日 下午3:57:35
 * @since
 */
public class UserDetailsCommand implements UserDetails {

    /** */
    private static final long serialVersionUID = 8100596048956187703L;

    private UserPrivilegeCommand command;
    
    /** 当前选择的组织的ID */
    private Long currentOuId;
    
    public UserDetailsCommand() { }

    public UserDetailsCommand(User user) {
        this.command = new UserPrivilegeCommand(user);
    }

    public UserDetailsCommand(UserPrivilegeCommand command) {
        this.command = command;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<GrantedAuthority>();
    }

    @Override
    public String getPassword() {
        return this.command.getUser().getPassword();
    }
    
    @Override
    public String getUsername() {
        return this.command.getUser().getLoginName();
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
        return Constants.LIFECYCLE_NORMAL.equals(this.command.getUser().getLifecycle());
    }

    public UserPrivilegeCommand getCommand() {
        return command;
    }

    public void setCommand(UserPrivilegeCommand command) {
        this.command = command;
    }

    public User getUser() {
        return this.command.getUser();
    }
    
    public String getDisplayName() {
        return this.command.getUser().getUserName();
    }
    
    public List<String> getPrivilegeUrls() {
        return this.command.getUrlList();
    }

    public Long getCurrentOuId() {
        return currentOuId;
    }

    public void setCurrentOuId(Long currentOuId) {
        this.currentOuId = currentOuId;
    }
    
}
