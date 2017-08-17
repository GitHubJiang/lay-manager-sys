package com.lay.shop.greeston.manager.inv.impl;

import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.greeston.dao.inv.InventoryChangeDao;
import com.lay.shop.greeston.manager.inv.InventoryChangeManager;
import com.lay.shop.greeston.model.inv.InventoryChange;

@Service("inventoryChangeManager")
public class InventoryChangeManagerImpl implements InventoryChangeManager {

    @Autowired
    private InventoryChangeDao inventoryChangeDao;
    
    @Override
    public Pagination<InventoryChange> findListByQueryMapWithPage(Page page, Sort[] sorts, Map<String, Object> params) {
        return this.inventoryChangeDao.findListByQueryMapWithPage(page, sorts, params);
    }

    @Override
    public List<InventoryChange> findListByQueryMapParam(Map<String, Object> params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void importInv(XSSFWorkbook workbook, Long userId) {
        // TODO Auto-generated method stub

    }

}
