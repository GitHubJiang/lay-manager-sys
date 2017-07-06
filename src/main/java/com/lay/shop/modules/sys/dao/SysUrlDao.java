package com.lay.shop.modules.sys.dao;


import java.util.Map;

import com.lay.shop.common.persistence.db.annotation.QueryPage;
import com.lay.shop.common.persistence.db.dao.BaseDao;
import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.modules.sys.model.SysUrl;



public interface SysUrlDao extends BaseDao<SysUrl,Long>{


	@QueryPage("findListCountByQueryMap")
	Pagination<SysUrl> findListByQueryMapWithPage(Page page,Sort[] sorts,Map<String, Object> params);
	
}
