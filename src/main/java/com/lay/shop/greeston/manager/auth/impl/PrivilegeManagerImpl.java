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
import java.util.Date;
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
import com.lay.shop.common.utils.JsonUtil;
import com.lay.shop.greeston.command.auth.PrivilegeCommand;
import com.lay.shop.greeston.dao.auth.PrifunUrlDao;
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
    @Autowired
    private PrifunUrlDao prifunUrlDao;
    
    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public Pagination<PrivilegeCommand> findPrivilegeListWithPage(Page page, Sort[] sorts, Map<String, Object> params) {
        
        return this.privilegeDao.findPrivilegeListWithPage(page, sorts, params);
    }

    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
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
    
    @Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public void saveOrUpdatePrivilege(PrivilegeCommand command) {
        List<String> rpsList = command.getRps();
        List<PrifunUrl> priFuns = new ArrayList<>();
        if (rpsList != null && !rpsList.isEmpty()) {
            for (String rps : rpsList) {
                PrifunUrl priFun = JsonUtil.buildNormalBinder().getJsonToObject(rps, PrifunUrl.class);
                priFun.setAcl(command.getAcl());
                priFuns.add(priFun);
            }
        }
        command.setPriFuns(priFuns);
        if (command.getId() == null) {
            this.savePrivilege(command);
        } else {
            this.updatePrivilege(command);
        }
    }
        
    /**新增权限信息*/
    private void savePrivilege(PrivilegeCommand command) {
        Privilege privilege = new Privilege();
        BeanUtils.copyProperties(command, privilege);
        privilege.setVersion(new Date());
        this.privilegeDao.insert(privilege);
        if (command.getPriFuns() != null && !command.getPriFuns().isEmpty()) {
            for (PrifunUrl priFun : command.getPriFuns()) {
                this.prifunUrlDao.insert(priFun);
            }
        }
    }
    
    /**修改权限信息*/
    private void updatePrivilege(PrivilegeCommand command) {
        Privilege privilege = new Privilege();
        BeanUtils.copyProperties(command, privilege);
        privilege.setVersion(new Date());
        this.privilegeDao.update(privilege);

        PrifunUrl prifunUrl = new PrifunUrl();
        prifunUrl.setAcl(command.getAcl());
        List<PrifunUrl> priFuns = this.prifunUrlDao.findListByParam(prifunUrl);
        if (priFuns != null && !priFuns.isEmpty()) {
            List<Long> ids = new ArrayList<>();
            for (PrifunUrl temp : priFuns) {
                ids.add(temp.getId());
            }
            this.prifunUrlDao.deleteByIds(ids);
        }

        if (command.getPriFuns() != null && !command.getPriFuns().isEmpty()) {
            for (PrifunUrl temp : command.getPriFuns()) {
                temp.setAcl(command.getAcl());
                this.prifunUrlDao.insert(temp);
            }
        }
    }

    // 删除权限
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deletePrivilegeById(Long id) {
        Privilege privilege = this.privilegeDao.findById(id);
        this.prifunUrlDao.deletePrifunUrlByAcl(privilege.getAcl());
        this.privilegeDao.delete(id);
    }
    
    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public PrivilegeCommand findAclAndUrlById(Long id) {

        Privilege privilege = this.privilegeDao.findById(id);
        PrivilegeCommand command = new PrivilegeCommand();
        BeanUtils.copyProperties(privilege, command);
        PrifunUrl prifunUrl = new PrifunUrl();
        prifunUrl.setAcl(privilege.getAcl());
        List<PrifunUrl> priFunList = this.prifunUrlDao.findListByParam(prifunUrl);
        Map<Long,List<String>> priFunMap = null;
        if (priFunList != null && !priFunList.isEmpty()) {            
            priFunMap = new HashMap<>();       
            for(PrifunUrl temp :priFunList){
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
