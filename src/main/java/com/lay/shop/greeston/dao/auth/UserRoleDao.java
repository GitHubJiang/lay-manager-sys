package com.lay.shop.greeston.dao.auth;

import java.util.Map;

import com.lay.shop.common.persistence.db.annotation.MyBatisDao;
import com.lay.shop.common.persistence.db.annotation.QueryPage;
import com.lay.shop.common.persistence.db.dao.BaseDao;
import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.greeston.model.auth.UserRole;


@MyBatisDao
public interface UserRoleDao extends BaseDao<UserRole,Long>{


	@QueryPage("findListCountByQueryMap")
	Pagination<UserRole> findListByQueryMapWithPage(Page page,Sort[] sorts,Map<String, Object> params);
	
}
