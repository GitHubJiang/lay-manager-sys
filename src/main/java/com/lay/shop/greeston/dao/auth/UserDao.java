package com.lay.shop.greeston.dao.auth;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lay.shop.common.persistence.db.annotation.MyBatisDao;
import com.lay.shop.common.persistence.db.annotation.QueryPage;
import com.lay.shop.common.persistence.db.dao.BaseDao;
import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.greeston.command.auth.UserPrivilegeCommand;
import com.lay.shop.greeston.model.auth.User;


@MyBatisDao
public interface UserDao extends BaseDao<User, Long> {


    @QueryPage("findListCountByQueryMap")
    Pagination<User> findListByQueryMapWithPage(Page page, Sort[] sorts, Map<String, Object> params);

    /**根据用户的id或登录名查询用户信息*/
    User findUserByIdOrLoginName(@Param("id") Long id, @Param("loginName") String loginName);
    /**根据用户的登录名查询用户信息和组织信息*/
    UserPrivilegeCommand findUserPrivilegeByLoginName(String loginName);
    
    @QueryPage("findUserListCountByQueryMapWithPage")
    Pagination<User> findUserListByQueryMapWithPage(Page page, Sort[] sorts, Map<String, Object> params);
}
