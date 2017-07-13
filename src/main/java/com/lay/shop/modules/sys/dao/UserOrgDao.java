package com.lay.shop.modules.sys.dao;

import java.util.Map;

import com.lay.shop.common.persistence.db.annotation.MyBatisDao;
import com.lay.shop.common.persistence.db.annotation.QueryPage;
import com.lay.shop.common.persistence.db.dao.BaseDao;
import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.modules.sys.model.UserOrg;


@MyBatisDao
public interface UserOrgDao extends BaseDao<UserOrg,Long>{


	@QueryPage("findListCountByQueryMap")
	Pagination<UserOrg> findListByQueryMapWithPage(Page page,Sort[] sorts,Map<String, Object> params);
	
	
}
