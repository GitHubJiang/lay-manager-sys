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
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lay.shop.greeston.command.auth.OpUnitTreeCommand;
import com.lay.shop.greeston.dao.auth.OperationUnitDao;
import com.lay.shop.greeston.manager.auth.OperationUnitManager;
import com.lay.shop.greeston.model.auth.OperationUnit;

@Service("operationUnitManager")
public class OperationUnitManagerImpl implements OperationUnitManager {

    @Autowired
    private OperationUnitDao operationUnitDao;
    
    @Override
    public List<OpUnitTreeCommand> findOpUnitTreeByUserId(Long userId) {
        //获取所有组织信息
        List<OpUnitTreeCommand> orgAll = this.operationUnitDao.findAllOpUnitTreeCommand();
        //获取用户拥有的组织
        List<OpUnitTreeCommand> outList = this.operationUnitDao.findOpUnitTreeByUserId(userId);
        
        List<OpUnitTreeCommand> rootList=null;
        if(outList!=null){
            //获取根结点
            rootList=this.findListByParentId(null,orgAll);
            if(rootList!=null){
                for(OpUnitTreeCommand root:rootList){
                    assemble(root,outList,orgAll);//添加子节点
                }
                if(outList != null && outList.size() > 0){
                    rootList.add(outList.get(0));
                }
            }
        }
        return rootList;
    }

    /**
     * 获取子节点
     */
    private List<OpUnitTreeCommand> findListByParentId(Long parent,List<OpUnitTreeCommand> allList){
        List<OpUnitTreeCommand> childList=new ArrayList<OpUnitTreeCommand>();
        for (OpUnitTreeCommand org : allList) {
            if(parent==org.getParentUnitId()){
                childList.add(org);
            }
        }
        return childList;
    }
    
    /**
     * 递归添加子节点
     * @param parent
     * @param outList
     * @return
     */
    public Boolean assemble(OpUnitTreeCommand parent,List<OpUnitTreeCommand> outList,List<OpUnitTreeCommand> allOrg) {
        boolean b=false;
        List<OpUnitTreeCommand> childList=findListByParentId(parent.getId(),allOrg);//获取子节点
        List<OpUnitTreeCommand> childList2=new ArrayList<OpUnitTreeCommand>();
        for(OpUnitTreeCommand oc:outList){//验证当前结点是否是用户拥有的
            if(oc.getId()==parent.getId()){
                b=true;
                parent.setSelectable("true");
                break;
            }
        }
        if(!b){
            parent.setSelectable("false");//页面中此节点是否可以选择
        }
        for(OpUnitTreeCommand o:childList){//查找子节点的子节点
            if(assemble(o,outList,allOrg)){
                childList2.add(o);
                b=true;
            }
        }
        if(childList2.isEmpty()){
            childList2=null;
        }
        parent.setNodes(childList2);
        return b;
    }

    
    @Override
    public List<OperationUnit> findListByParam(OperationUnit ou) {
        return this.operationUnitDao.findListByParam(ou);
    }
    
    @Override
    public OperationUnit get(Long id) {        
        return this.operationUnitDao.findById(id);
    }
}
