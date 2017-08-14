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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.greeston.command.auth.PrivilegeCommand;
import com.lay.shop.greeston.dao.auth.PrivilegeDao;
import com.lay.shop.greeston.manager.auth.PrivilegeFunUrlManager;
import com.lay.shop.greeston.manager.auth.PrivilegeManager;
import com.lay.shop.greeston.model.auth.PrifunUrl;
import com.lay.shop.greeston.model.auth.Privilege;


@Service("privilegeManager")
public class PrivilegeManagerImpl implements PrivilegeManager {
    
    @Autowired
    private PrivilegeDao privilegeDao;
    @Autowired
    private PrivilegeFunUrlManager privilegeFunUrlManager;
    
    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public Pagination<PrivilegeCommand> findPrivilegeListWithPage(Page page, Sort[] sorts, Map<String, Object> params) {
        
        return this.privilegeDao.findPrivilegeListWithPage(page, sorts, params);
    }

    @Override
    public PrivilegeCommand findAllPrifunUrlByAclId(Long id) {
        
        Privilege privilege = this.privilegeDao.findById(id);
        PrivilegeCommand command = new PrivilegeCommand();
        BeanUtils.copyProperties(privilege, command);
        
        List<PrifunUrl> prifunUrlList = this.privilegeFunUrlManager.findAllPrifunUrlByAcl(privilege.getAcl());
        
        Map<Long,List<String>> priFunMap = null;
        if (prifunUrlList != null && !prifunUrlList.isEmpty()) {            
            priFunMap = new HashMap<>();       
            for(PrifunUrl temp :prifunUrlList){
                if (priFunMap.get(temp.getUrlId()) == null) {
                    List<String> tempList = new ArrayList<>();
                    tempList.add(temp.getFunCode());
                    priFunMap.put(temp.getUrlId(), tempList);
                }else{
                    priFunMap.get(temp.getUrlId()).add(temp.getFunCode());
                }
            }            
        }
        command.setPriFunMap(priFunMap);
        return command;
    }
}
