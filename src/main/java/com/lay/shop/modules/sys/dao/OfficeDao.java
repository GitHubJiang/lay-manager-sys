package com.lay.shop.modules.sys.dao;

import com.lay.shop.common.persistence.TreeDao;
import com.lay.shop.common.persistence.annotation.MyBatisDao;
import com.lay.shop.modules.sys.entity.Office;

/**
 * 机构DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface OfficeDao extends TreeDao<Office> {
	
}
