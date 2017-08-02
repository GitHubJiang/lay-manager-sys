package com.lay.shop.greeston.dao.auth;

import java.util.Map;

import com.lay.shop.common.persistence.db.annotation.MyBatisDao;
import com.lay.shop.common.persistence.db.annotation.QueryPage;
import com.lay.shop.common.persistence.db.dao.BaseDao;
import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.greeston.model.auth.PrifunUrl;


@MyBatisDao
public interface PrifunUrlDao extends BaseDao<PrifunUrl,Long>{


	@QueryPage("findListCountByQueryMap")
	Pagination<PrifunUrl> findListByQueryMapWithPage(Page page,Sort[] sorts,Map<String, Object> params);

}
