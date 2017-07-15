package com.lay.shop.modules.sys.service;

import java.util.List;
import java.util.Map;

import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.modules.sys.command.OrganizationCommand;
import com.lay.shop.modules.sys.model.Organization;

public interface OrganizationService {
    
    /**查询组织信息*/
    OrganizationCommand findOrganizationById(Long id);
    /**修改组织信息*/
    void save(Organization organization);
    /**修改组织信息*/
    void update(Organization organization);
    /**分页查询组织列表*/
    Pagination<OrganizationCommand> findPageListByQueryMapWithPage(Page page,Sort[] sorts,Map<String, Object> params);
    /**删除组织*/
    void delete(Long id);
    /**查询所有的组织*/
    List<Organization> findAllList();
}
