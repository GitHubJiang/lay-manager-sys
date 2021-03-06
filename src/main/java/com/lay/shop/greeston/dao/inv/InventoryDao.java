/**
 * Copyright (c) 2013 Baozun All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Baozun.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Baozun.
 *
 * BAOZUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. BAOZUN SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 *
 */
 package com.lay.shop.greeston.dao.inv;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lay.shop.common.persistence.db.annotation.MyBatisDao;
import com.lay.shop.common.persistence.db.annotation.QueryPage;
import com.lay.shop.common.persistence.db.dao.BaseDao;
import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.greeston.model.inv.Inventory;


@MyBatisDao
public interface InventoryDao extends BaseDao<Inventory,Long>{

    @QueryPage("findListCountByQueryMap")
	Pagination<Inventory> findListByQueryMapWithPage(Page page,Sort[] sorts,Map<String, Object> params);
	/**库存报表导出*/
	List<Inventory> findListByQueryMapParam(Map<String, Object> params);
	/**根据商品编码查询库存信息*/
	Inventory findInventoryBySkuCode(@Param("skuCode")String skuCode);
    /**更新库存信息*/
    void updateInventoryById(@Param("id") Long id, @Param("quantity") Integer quantity);
}
