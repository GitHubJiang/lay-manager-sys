package com.lay.shop.pacs.dao;


import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lay.shop.pacs.command.GoodsInvCommand;
import com.lay.shop.pacs.model.GoodsInventory;
import com.lay.shop.pacs.orm.annotation.QueryPage;
import com.lay.shop.pacs.orm.dao.Page;
import com.lay.shop.pacs.orm.dao.Pagination;
import com.lay.shop.pacs.orm.dao.Sort;
import com.lay.shop.pacs.orm.dao.supports.BaseDao;

public interface GoodsInventoryDao extends BaseDao<GoodsInventory, Long> {

    @QueryPage("findPageGoodsInventoryListCount")
    public Pagination<GoodsInvCommand> findPageGoodsInventoryList(Page page, Sort[] sorts, Map<String, Object> params);
    
    public int updateInventoryById(@Param("id") Long id, @Param("quantity") Integer quantity);
    
    public GoodsInvCommand findInventoryByCompanyCodeAndSkuCode(@Param("companyCode") String companyCode, @Param("skuCode") String skuCode);
    
    public GoodsInvCommand findInventoryById(@Param("id") Long id);

}
