package com.lay.shop.modules.sys.service;

import java.util.Map;

import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.modules.sys.model.Url;

/**
 * 
 * @author Lay
 * @date 2017年6月9日 下午2:33:25
 * @since
 */
public interface UrlService{	
    
    /**新增或修改URL*/
    void saveOrUpdateUrl(Url url);
    /**分页查询URL*/
    Pagination<Url> findListByQueryMapWithPage(Page page,Sort[] sorts,Map<String, Object> params);
    /**删除Url*/
    void delete(Long id);
}
