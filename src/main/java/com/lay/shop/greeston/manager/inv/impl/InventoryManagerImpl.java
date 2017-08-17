package com.lay.shop.greeston.manager.inv.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.greeston.dao.inv.InventoryDao;
import com.lay.shop.greeston.manager.inv.InventoryManager;
import com.lay.shop.greeston.model.inv.Inventory;

@Service("inventoryManager")
public class InventoryManagerImpl implements InventoryManager {

    @Autowired
    private InventoryDao inventoryDao;
    
    @Override
    public Pagination<Inventory> findListByQueryMapWithPage(Page page, Sort[] sorts, Map<String, Object> params) {       
        return this.inventoryDao.findListByQueryMapWithPage(page, sorts, params);
    }

    @Override
    public List<Inventory> findListByQueryMapParam(Map<String, Object> params) {
        return this.inventoryDao.findListByQueryMapParam(params);
    }

}
