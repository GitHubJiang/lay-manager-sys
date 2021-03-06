package com.lay.shop.greeston.command.auth;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.lay.shop.greeston.model.auth.User;

/***
 * 用户、组织、权限以及URL信息
 * @author Lay
 * @date 2017年8月2日 下午4:04:30
 * @since
 */
public class UserPrivilegeCommand implements Serializable {
    
    /** */
    private static final long serialVersionUID = 5539770075870144613L;

    /** 用户 */
    private User user;
    
    /** 用户所属的组织 */
    private OperationUnitCommand ou;
    
    /** 拥有的组织ID列表 */
    private List<Long> ouList;
    
    /** 用户能够访问的URL列表 */
    private List<String> urlList;
    
    /** key为acl，value为funCode集合 */
    private Map<String, List<String>> priFunMap;

    public UserPrivilegeCommand() { }

    public UserPrivilegeCommand(User user) {
        this.user = user;
    }

    public OperationUnitCommand getOu() {
        return ou;
    }

    public void setOu(OperationUnitCommand ou) {
        this.ou = ou;
    }

    public List<Long> getOuList() {
        return ouList;
    }

    public void setOuList(List<Long> ouList) {
        this.ouList = ouList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }

    public Map<String, List<String>> getPriFunMap() {
        return priFunMap;
    }

    public void setPriFunMap(Map<String, List<String>> priFunMap) {
        this.priFunMap = priFunMap;
    }

}
