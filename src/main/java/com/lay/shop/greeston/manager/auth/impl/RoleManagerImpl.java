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
import org.springframework.util.StringUtils;

import com.lay.shop.common.constants.Constants;
import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.common.utils.JsonUtil;
import com.lay.shop.greeston.command.auth.RoleCommand;
import com.lay.shop.greeston.dao.auth.PrivilegeDao;
import com.lay.shop.greeston.dao.auth.RoleDao;
import com.lay.shop.greeston.dao.auth.RolePriDao;
import com.lay.shop.greeston.manager.auth.RoleManager;
import com.lay.shop.greeston.model.auth.Privilege;
import com.lay.shop.greeston.model.auth.Role;
import com.lay.shop.greeston.model.auth.RolePri;

@Service("roleManager")
public class RoleManagerImpl implements RoleManager {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RolePriDao rolePriDao;
    @Autowired
    private PrivilegeDao privilegeDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Pagination<RoleCommand> findRoleListWithPage(Page page, Sort[] sorts, Map<String, Object> params) {
        return this.roleDao.findRoleListWithPage(page, sorts, params);
    }
    
    @Override
    public RoleCommand findRoleAndPriById(Long id) {
        Role role = roleDao.findById(id);
        RoleCommand command = new RoleCommand();
        BeanUtils.copyProperties(role, command);
        RolePri rolePri = new RolePri();
        rolePri.setRoleId(role.getId());
        List<RolePri> rolePriList = this.rolePriDao.findListByParam(rolePri);        
        Map<String, List<String>> rolePriMap = new HashMap<String, List<String>>();
        
        for (RolePri rolePriTemp : rolePriList) {
            List<String> list = rolePriMap.get(rolePriTemp.getAcl());
            if (list == null || list.size() < 1) {
                List<String> listFun = new ArrayList<String>();
                listFun.add(rolePriTemp.getFunCode());
                rolePriMap.put(rolePriTemp.getAcl(), listFun);
            } else {
                list.add(rolePriTemp.getFunCode());
            }
        }
        command.setRolePriMap(rolePriMap);
        return command;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrUpdateRole(RoleCommand command) {

        Role role = new Role();
        BeanUtils.copyProperties(command, role);
        List<String> rolePriList = command.getRolePriList();
        List<RolePri> rolePris = new ArrayList<>();
        if (rolePriList != null && !rolePriList.isEmpty()) {
            for (String rolePriCommand : rolePriList) {
                if(StringUtils.isEmpty(rolePriCommand)){
                    continue;
                }
                RolePri rolePri = JsonUtil.buildNormalBinder().getJsonToObject(rolePriCommand, RolePri.class);
                rolePri.setRoleId(role.getId());
                rolePris.add(rolePri);
            }
        }
        if(role.getId() == null){
            this.saveRole(role, rolePris);
        }else{
            this.updateRole(role, rolePris);
        }
    }
    
    private void saveRole(Role role,List<RolePri> rolePris){
        role.setLifecycle(Constants.LIFECYCLE_NORMAL);
        this.roleDao.insert(role);
        for(RolePri rolePri : rolePris){
            rolePri.setRoleId(role.getId());
            this.rolePriDao.insert(rolePri);
        }
    }
    
    private void updateRole(Role role,List<RolePri> rolePris){
        Role oldRole = this.roleDao.findById(role.getId());
        oldRole.setName(role.getName());
        oldRole.setOuTypeId(role.getOuTypeId());
        this.roleDao.update(oldRole);
        this.rolePriDao.deleteRolePriByRoleId(oldRole.getId());
        for(RolePri rolePri : rolePris){
            this.rolePriDao.insert(rolePri);
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteRoleById(Long id) {
        this.rolePriDao.deleteRolePriByRoleId(id);
        this.roleDao.delete(id);
    }

}
