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
import com.lay.shop.greeston.model.auth.RolePri;


@MyBatisDao
public interface RolePriDao extends BaseDao<RolePri,Long>{


	@QueryPage("findListCountByQueryMap")
	Pagination<RolePri> findListByQueryMapWithPage(Page page,Sort[] sorts,Map<String, Object> params);
	/**根据用户ID获取用户角色权限信息*/
	List<RolePri>  findRolePriByUserId(@Param("userId")Long userId);
	/**根据role_id删除，角色权限关联信息*/
	void deleteRolePriByRoleId(Long roleId);
}
