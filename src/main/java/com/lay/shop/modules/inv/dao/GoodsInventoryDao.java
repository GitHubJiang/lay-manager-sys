package com.lay.shop.modules.inv.dao;


import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lay.shop.common.persistence.db.annotation.MyBatisDao;
import com.lay.shop.common.persistence.db.annotation.QueryPage;
import com.lay.shop.common.persistence.db.dao.BaseDao;
import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.modules.inv.command.GoodsInvCommand;
import com.lay.shop.modules.inv.entity.GoodsInventory;

@MyBatisDao
public interface GoodsInventoryDao extends BaseDao<GoodsInventory, Long> {

    @QueryPage("findPageGoodsInventoryListCount")
    public Pagination<GoodsInvCommand> findPageGoodsInventoryList(Page page, Sort[] sorts, Map<String, Object> params);
    
    public int updateInventoryById(@Param("id") Long id, @Param("quantity") Integer quantity);
    
    public GoodsInvCommand findInventoryByCompanyCodeAndSkuCode(@Param("companyCode") String companyCode, @Param("skuCode") String skuCode);
    
    public GoodsInvCommand findInventoryById(@Param("id") Long id);

}
