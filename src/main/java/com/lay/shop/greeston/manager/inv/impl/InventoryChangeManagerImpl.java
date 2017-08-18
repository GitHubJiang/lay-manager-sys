package com.lay.shop.greeston.manager.inv.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lay.shop.common.constants.Constants;
import com.lay.shop.common.exception.BusinessException;
import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.greeston.dao.inv.InventoryChangeDao;
import com.lay.shop.greeston.dao.inv.InventoryDao;
import com.lay.shop.greeston.manager.inv.InventoryChangeManager;
import com.lay.shop.greeston.model.inv.Inventory;
import com.lay.shop.greeston.model.inv.InventoryChange;

@Service("inventoryChangeManager")
public class InventoryChangeManagerImpl implements InventoryChangeManager {

    @Autowired
    private InventoryChangeDao inventoryChangeDao;
    @Autowired
    private InventoryDao inventoryDao;
    
    @Override
    public Pagination<InventoryChange> findListByQueryMapWithPage(Page page, Sort[] sorts, Map<String, Object> params) {
        return this.inventoryChangeDao.findListByQueryMapWithPage(page, sorts, params);
    }

    @Override
    public List<InventoryChange> findListByQueryMapParam(Map<String, Object> params) {
        return this.inventoryChangeDao.findListByQueryMap(params);
    }

    @Override
    public void importInInvc(XSSFWorkbook workbook, Long userId) {
        this.importInvc(workbook, userId, Constants.INVC_TYPE_IN);
    }

    @Override
    public void importOutInvc(XSSFWorkbook workbook, Long userId) {
       this.importInvc(workbook, userId, Constants.INVC_TYPE_OUT);
    }
    
    private void importInvc(XSSFWorkbook workbook, Long userId,Integer invcType){
        XSSFSheet xssfSheet = workbook.getSheetAt(0);
        // 获取当前工作薄的每一行
        for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
            XSSFRow xssfRow = xssfSheet.getRow(rowNum);
            try{
                if (xssfRow != null) {
                    String skuCode = xssfRow.getCell(0).getStringCellValue();
                    Integer quantity = 0;
                    if(xssfRow.getCell(1).getCellType()==Cell.CELL_TYPE_NUMERIC){
                        quantity = Double.valueOf(xssfRow.getCell(1).getNumericCellValue()).intValue();
                    }else{
                        quantity = Integer.valueOf(xssfRow.getCell(1).getStringCellValue());
                    }                    
                    Inventory temp = this.inventoryDao.findInventoryBySkuCode(skuCode);
                    if(temp == null){
                        throw new BusinessException("", "商品编码"+skuCode+"在库存表中不存在");
                    }
                    InventoryChange inventoryChange = new InventoryChange();
                    inventoryChange.setBrandCode(temp.getBrandCode());
                    inventoryChange.setSkuCode(skuCode);
                    inventoryChange.setQuantity(quantity);
                    inventoryChange.setStatus(Constants.PROCESS_STATUS_NEW);
                    inventoryChange.setInvType(invcType);
                    inventoryChange.setCreateTime(new Date());
                    inventoryChange.setUpdateTime(new Date());
                    inventoryChange.setUserId(userId);
                    this.inventoryChangeDao.insert(inventoryChange);
                }
            }catch(Exception e){
                throw new BusinessException("", "EXCEL 文件第"+rowNum+1+"行有问题请重新检查一遍再重新导入");
            }            
        }
    }

}
