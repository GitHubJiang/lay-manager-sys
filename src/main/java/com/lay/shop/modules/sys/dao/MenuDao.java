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
import com.lay.shop.modules.sys.entity.Menu;


@MyBatisDao
public interface MenuDao extends BaseDao<Menu,Long>{


    @QueryPage("findListCountByQueryMap")
    Pagination<Menu> findListByQueryMapWithPage(Page page,Sort[] sorts,Map<String, Object> params);
    
    List<Menu> findIndexMenu(@Param("type")Integer type,@Param("id")Long id);
}
