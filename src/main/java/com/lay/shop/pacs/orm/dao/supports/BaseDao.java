/**
 * Copyright (c) 2015 Jumbomart All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Jumbomart. You shall not
 * disclose such Confidential Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jumbo.
 * 
 * JUMBOMART MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT. JUMBOMART SHALL NOT BE LIABLE FOR ANY
 * DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 * 
 */
package com.lay.shop.pacs.orm.dao.supports;

import java.util.List;
import java.util.Map;

public interface BaseDao<ET,PT> {

    /**
     * 通过id进行查询单个对象
     * @param id
     * @return
     */
    ET findById(PT id);
    
    /**
     * 通过id list 查询对象列表
     * @param ids
     * @return
     */
    List<ET> findByIds(List<PT> ids);
    
    /**
     * 多条件查询对象列表
     * @param map
     * @return
     */
    List<ET> findListByQueryMap(Map<String,Object> map);
    
    /**
     * 多条件获取数量
     * @param map
     * @return
     */
    long findListCountByQueryMap(Map<String,Object> map);
    
    /**
     * 多条件查询对象列表
     * @param map
     * @return
     */
    List<ET> findListByParam(ET obj);
    
    /**
     * 多条件获取数量
     * @param map
     * @return
     */
    long findListCountByParam(ET obj);
    
    /**
     * 新增
     * @param obj
     * @return
     */
    long insert(ET obj);
    
    /**
     * 更新
     * @param obj
     * @return
     */
    int update(ET obj);
    
    /**
     * 单个删除
     * @param id
     * @return
     */
    int delete(PT id);
    
    /**
     * 通过多个id删除
     * @param ids
     * @return
     */
    int deleteByIds(List<PT> ids);   
    
}
