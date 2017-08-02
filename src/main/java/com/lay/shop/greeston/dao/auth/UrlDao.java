package com.lay.shop.greeston.dao.auth;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lay.shop.common.persistence.db.annotation.MyBatisDao;
import com.lay.shop.common.persistence.db.annotation.QueryPage;
import com.lay.shop.common.persistence.db.dao.BaseDao;
import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.greeston.model.auth.Url;


@MyBatisDao
public interface UrlDao extends BaseDao<Url,Long>{


	@QueryPage("findListCountByQueryMap")
	Pagination<Url> findListByQueryMapWithPage(Page page,Sort[] sorts,Map<String, Object> params);
	/**根据用户ID查询出所有的URL*/
	List<String> findUrlByUserId(@Param("userId") Long userId);
	/**查询所有的URL*/
	List<String> findAllUrlList();
}
