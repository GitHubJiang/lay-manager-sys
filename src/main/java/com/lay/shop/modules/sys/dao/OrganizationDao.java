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
import com.lay.shop.modules.sys.command.OrganizationCommand;
import com.lay.shop.modules.sys.model.Organization;


@MyBatisDao
public interface OrganizationDao extends BaseDao<Organization,Long>{

	@QueryPage("findPageCountByQueryMapWithPage")
    Pagination<OrganizationCommand> findPageListByQueryMapWithPage(Page page,Sort[] sorts,Map<String, Object> params);
	
	OrganizationCommand findOrganizationById(Long id);
	
	List<OrganizationCommand> findOrganizationByUserId(@Param("userId")Long userId);
}
