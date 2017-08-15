package com.lay.shop.greeston.manager.auth;

import java.util.Map;

import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.greeston.command.auth.PrivilegeCommand;

public interface PrivilegeManager {

    /**分页查询权限信息列表*/
    Pagination<PrivilegeCommand> findPrivilegeListWithPage(Page page, Sort[] sorts, Map<String, Object> params);
    
    /**根据权限ID查询 该权限拥有的所有权限功能*/
    PrivilegeCommand findAllPrifunUrlByAclId(Long id);
    /**新增权限信息*/
    void saveOrUpdatePrivilege(PrivilegeCommand command);
    /**删除权限*/
    void deletePrivilegeById(Long id);
    /**查询权限信息*/
    PrivilegeCommand findAclAndUrlById(Long id);
}
