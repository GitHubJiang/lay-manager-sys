package com.lay.shop.greeston.manager.auth;

import java.util.List;

import com.lay.shop.greeston.model.auth.OperationUnitType;

public interface OperationUnitTypeManager {

    /**查询组织列表*/
    List<OperationUnitType> findAllOptByParam(OperationUnitType opt);
}
