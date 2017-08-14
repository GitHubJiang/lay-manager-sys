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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lay.shop.greeston.dao.auth.PrifunUrlDao;
import com.lay.shop.greeston.manager.auth.PrivilegeFunUrlManager;
import com.lay.shop.greeston.model.auth.PrifunUrl;

@Service("privilegeFunUrlManager")
public class PrivilegeFunUrlManagerImpl implements PrivilegeFunUrlManager {

    @Autowired
    private PrifunUrlDao prifunUrlDao;
    
    @Override
    public List<PrifunUrl> findAllPrifunUrlByAcl(String acl) {
        PrifunUrl prifunUrl = new PrifunUrl();
        prifunUrl.setAcl(acl);
        return this.prifunUrlDao.findListByParam(prifunUrl);
    }

}
