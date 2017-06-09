package com.lay.shop.modules.inv.dao;

import com.lay.shop.common.persistence.db.annotation.MyBatisDao;
import com.lay.shop.common.persistence.db.dao.BaseDao;
import com.lay.shop.modules.inv.entity.ItemSku;

@MyBatisDao
public interface ItemSkuDao extends BaseDao<ItemSku,Long>{
    
}
