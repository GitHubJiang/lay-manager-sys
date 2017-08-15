package com.lay.shop.greeston.manager.auth.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lay.shop.greeston.dao.auth.OperationUnitTypeDao;
import com.lay.shop.greeston.manager.auth.OperationUnitTypeManager;
import com.lay.shop.greeston.model.auth.OperationUnitType;

/***
 * 组织类型
 * @author Lay
 * @date 2017年8月15日 上午10:03:40
 * @since
 */
@Service("operationUnitTypeManager")
public class OperationUnitTypeManagerImpl implements OperationUnitTypeManager {

    @Autowired
    private OperationUnitTypeDao operationUnitTypeDao;
    
    @Override
    public List<OperationUnitType> findAllOptByParam(OperationUnitType opt) {        
        return this.operationUnitTypeDao.findListByParam(opt);
    }

}
