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
import com.lay.shop.greeston.command.auth.MenuCommand;
import com.lay.shop.greeston.model.auth.Menu;


@MyBatisDao
public interface MenuDao extends BaseDao<Menu,Long>{


	@QueryPage("findListCountByQueryMap")
	Pagination<Menu> findListByQueryMapWithPage(Page page,Sort[] sorts,Map<String, Object> params);
	/**查询系统中所有的菜单*/
	List<MenuCommand> findAllMenuCommandList();
	/**根据用户的ID及用户的组织ID查询菜单信息*/
	List<MenuCommand> findLeftMenuItems(@Param("userId") Long userId,@Param("ouId") Long ouId);
}
