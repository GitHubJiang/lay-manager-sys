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
import com.lay.shop.greeston.command.auth.UserRoleCommand;
import com.lay.shop.greeston.model.auth.UserRole;


@MyBatisDao
public interface UserRoleDao extends BaseDao<UserRole,Long>{


	@QueryPage("findListCountByQueryMap")
	Pagination<UserRole> findListByQueryMapWithPage(Page page,Sort[] sorts,Map<String, Object> params);
	/**根据用户id查询用户角色组织信息*/
	List<UserRoleCommand> findUserRoleOUByUserId(@Param("userId")Long userId);
}
