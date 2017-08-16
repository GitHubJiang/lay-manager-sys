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
import com.lay.shop.greeston.command.auth.OpUnitTreeCommand;
import com.lay.shop.greeston.model.auth.OperationUnit;

@MyBatisDao
public interface OperationUnitDao extends BaseDao<OperationUnit,Long>{


	@QueryPage("findListCountByQueryMap")
	Pagination<OperationUnit> findListByQueryMapWithPage(Page page,Sort[] sorts,Map<String, Object> params);
	/**获取用户拥有的组织信息*/
	List<OpUnitTreeCommand> findOpUnitTreeByUserId(@Param("userId")Long userId);
	/**获取所有的组织信息*/
	List<OpUnitTreeCommand> findAllOpUnitTreeCommand();
}
