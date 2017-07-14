package com.lay.shop.modules.sys.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lay.shop.common.persistence.db.annotation.MyBatisDao;
import com.lay.shop.common.persistence.db.annotation.QueryPage;
import com.lay.shop.common.persistence.db.dao.BaseDao;
import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.modules.sys.command.UserCommand;
import com.lay.shop.modules.sys.model.User;


@MyBatisDao
public interface UserDao extends BaseDao<User,Long>{


	@QueryPage("findListCountByQueryMap")
	Pagination<User> findListByQueryMapWithPage(Page page,Sort[] sorts,Map<String, Object> params);
	/**登录*/
	UserCommand findUserByLoginNameAndEncryptedPassword(@Param("loginName")String loginName,@Param("password")String password);
}
