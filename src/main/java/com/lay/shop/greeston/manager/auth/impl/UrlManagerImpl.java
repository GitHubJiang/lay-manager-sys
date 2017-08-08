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
package com.lay.shop.greeston.manager.auth.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.greeston.dao.auth.UrlDao;
import com.lay.shop.greeston.manager.auth.UrlManager;
import com.lay.shop.greeston.model.auth.Url;
@Service("urlManager")
public class UrlManagerImpl implements UrlManager {

    @Autowired
    private UrlDao urlDao;
    
    @Override
    public List<String> findAllUrlList() {
        return this.urlDao.findAllUrlList();
    }

    @Override
    public void saveOrUpdateUrl(Url url) {
        if (url.getId() != null) {
            this.urlDao.update(url);
        }else{
            this.urlDao.insert(url);
        }
    }

    @Override
    public void deleteUrlById(Long id) {
        this.urlDao.delete(id);
    }

    @Override
    public Pagination<Url> findListByQueryMapWithPage(Page page, Sort[] sorts, Map<String, Object> params) {
        return this.urlDao.findListByQueryMapWithPage(page, sorts, params);
    }

}
