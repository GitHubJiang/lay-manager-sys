package com.lay.shop.modules.sys.service;

import java.util.List;
import java.util.Map;

import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.modules.sys.model.OrganizationType;

public interface OrganizationTypeService {

    /**新增或修改组织类型*/
    void saveOrUpdate(OrganizationType organizationType);
    /**分页查询组织类型列表*/
    Pagination<OrganizationType> findListByQueryMapWithPage(Page page,Sort[] sorts,Map<String, Object> params);
    /**删除组织类型*/
    void delete(Long id);
    /**查询所有的组织类型(下拉菜单选项会用到)*/
    List<OrganizationType> findAllList();
}
