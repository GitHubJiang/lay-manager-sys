package com.lay.shop.modules.inv.service;

import java.util.Map;

import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.modules.inv.command.GoodsInvCommand;

public interface GoodsInventoryService {

    /** 分页查询库存信息 */
    public Pagination<GoodsInvCommand> findPageGoodsInventoryList(Page page, Sort[] sorts, Map<String, Object> params);

    /** 根据库存id 更新库存*/
    public int updateInventoryById(Long id, Integer quantity);
    
    /** 新增库存数据*/
    public void addInventory(GoodsInvCommand command);
    
    /** 根据公司编码和skuCode查询库存信息*/
    public GoodsInvCommand findInventoryByCompanyCodeAndSkuCode(String companyCode, String skuCode);
    
    /** 根据库存id查询库存信息*/
    public GoodsInvCommand findInventoryById(Long id);
}
