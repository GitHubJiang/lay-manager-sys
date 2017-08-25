package com.lay.shop.greeston.manager.auth;

import java.util.List;

import com.lay.shop.greeston.command.auth.OpUnitTreeCommand;
import com.lay.shop.greeston.model.auth.OperationUnit;


public interface OperationUnitManager {
    
    /**根据用户ID获取组织*/
    List<OpUnitTreeCommand> findOpUnitTreeByUserId(Long userId);
    /**查询组织信息*/
    List<OperationUnit> findListByParam(OperationUnit ou);
    /**根据组织ID查询组织*/
    OperationUnit get(Long id); 
    /**查询所有的组织*/
    List<OpUnitTreeCommand> findAllOpUnitTree();
}
