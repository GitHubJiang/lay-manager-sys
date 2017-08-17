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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lay.shop.common.exception.BusinessException;
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
    @Transactional(propagation = Propagation.SUPPORTS)
    public Pagination<Inventory> findListByQueryMapWithPage(Page page, Sort[] sorts, Map<String, Object> params) {       
        return this.inventoryDao.findListByQueryMapWithPage(page, sorts, params);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Inventory> findListByQueryMapParam(Map<String, Object> params) {
        return this.inventoryDao.findListByQueryMapParam(params);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void importInv(XSSFWorkbook workbook) {
        XSSFSheet xssfSheet = workbook.getSheetAt(0);
        // 获取当前工作薄的每一行
        for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
            XSSFRow xssfRow = xssfSheet.getRow(rowNum);
            try{
                if (xssfRow != null) {
                    String brandCode = xssfRow.getCell(0).getStringCellValue();
                    String brandName = xssfRow.getCell(1).getStringCellValue();
                    String skuCode = xssfRow.getCell(2).getStringCellValue();
                    String skuName = xssfRow.getCell(3).getStringCellValue();
                    Integer quantity = 0;
                    if(xssfRow.getCell(4).getCellType()==Cell.CELL_TYPE_NUMERIC){
                        quantity = Double.valueOf(xssfRow.getCell(4).getNumericCellValue()).intValue();
                    }else{
                        quantity = Integer.valueOf(xssfRow.getCell(4).getStringCellValue());
                    }                    
                    Inventory temp = this.inventoryDao.findInventoryBySkuCode(skuCode);
                    if(temp == null){
                        temp = new Inventory();
                        temp.setBrandCode(brandCode);
                        temp.setBrandName(brandName);
                        temp.setSkuCode(skuCode);
                        temp.setSkuName(skuName);
                        temp.setQuantity(quantity);
                        temp.setOriginalQuantity(quantity);
                        temp.setCreateTime(new Date());
                        temp.setVersion(new Date());
                        this.inventoryDao.insert(temp); 
                    }else{
                        temp.setQuantity(quantity);
                        temp.setOriginalQuantity(quantity);
                        temp.setVersion(new Date());
                        this.inventoryDao.update(temp);
                    }
                    
                }
            }catch(Exception e){
                throw new BusinessException("", "EXCEL 文件第"+rowNum+1+"行有问题请重新检查一遍再重新导入");
            }            
        }
        
    }

}
