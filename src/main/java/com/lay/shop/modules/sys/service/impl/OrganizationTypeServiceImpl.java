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
package com.lay.shop.modules.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.common.utils.Validator;
import com.lay.shop.modules.sys.dao.OrganizationTypeDao;
import com.lay.shop.modules.sys.model.OrganizationType;
import com.lay.shop.modules.sys.service.OrganizationTypeService;

@Service
public class OrganizationTypeServiceImpl implements OrganizationTypeService {

    @Autowired
    private OrganizationTypeDao organizationTypeDao;
    
    @Override
    public void saveOrUpdate(OrganizationType organizationType) {
        if(Validator.isNullOrEmpty(organizationType.getId())){
            organizationTypeDao.insert(organizationType);
        }else{
            organizationTypeDao.update(organizationType);
        }
    }

    @Override
    public Pagination<OrganizationType> findListByQueryMapWithPage(Page page, Sort[] sorts, Map<String, Object> params) {
        return this.organizationTypeDao.findListByQueryMapWithPage(page, sorts, params);
    }

    @Override
    public void delete(Long id) {
        this.organizationTypeDao.delete(id);
    }

    @Override
    public List<OrganizationType> findAllList() {
        return this.organizationTypeDao.findAllList();
    }
    
    

}
