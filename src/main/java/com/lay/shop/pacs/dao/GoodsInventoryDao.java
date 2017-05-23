package com.lay.shop.pacs.dao;


import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.lay.shop.pacs.command.GoodsInvCommand;
import com.lay.shop.pacs.model.GoodsInventory;
import com.lay.shop.pacs.orm.dao.supports.BaseDao;
public interface GoodsInventoryDao extends BaseDao<GoodsInventory,Long>{
	
    public List<GoodsInvCommand> findPageGoodsInventoryList(RowBounds rowBounds,GoodsInvCommand goodsInvCommand);
    
}
