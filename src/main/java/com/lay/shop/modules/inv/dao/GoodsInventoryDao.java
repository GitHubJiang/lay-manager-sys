package com.lay.shop.modules.inv.dao;


import java.util.Map;

import com.lay.shop.common.persistence.db.annotation.QueryPage;
import com.lay.shop.common.persistence.db.dao.BaseDao;
import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.modules.inv.model.GoodsInventory;

public interface GoodsInventoryDao extends BaseDao<GoodsInventory,Long>{


	@QueryPage("findListCountByQueryMap")
	Pagination<GoodsInventory> findListByQueryMapWithPage(Page page,Sort[] sorts,Map<String, Object> params);
	
}
