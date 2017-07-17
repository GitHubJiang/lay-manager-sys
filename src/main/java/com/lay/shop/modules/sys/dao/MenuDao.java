package com.lay.shop.modules.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lay.shop.common.persistence.db.annotation.MyBatisDao;
import com.lay.shop.common.persistence.db.annotation.QueryPage;
import com.lay.shop.common.persistence.db.dao.BaseDao;
import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.modules.sys.command.MenuCommand;
import com.lay.shop.modules.sys.model.Menu;


@MyBatisDao
public interface MenuDao extends BaseDao<Menu,Long>{


	@QueryPage("findListCountByQueryMap")
	Pagination<Menu> findListByQueryMapWithPage(Page page,Sort[] sorts,Map<String, Object> params);
	/**查询用户权限菜单*/
    List<MenuCommand> findAllMenuList(@Param("userId") Long userId, @Param("orgCode") String orgCode);
	/**查询上级菜单*/
    List<MenuCommand> findUpMenuList(List<Long> parentIds);
    
    
}
