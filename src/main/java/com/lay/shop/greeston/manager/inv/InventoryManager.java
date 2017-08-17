package com.lay.shop.greeston.manager.inv;

import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.greeston.model.inv.Inventory;
/**
 * 全量库存管理
 * @author Lay
 * @date 2017年8月2日 下午3:33:49
 * @since
 */
public interface InventoryManager {
    /**分页查询库存数据*/
    Pagination<Inventory> findListByQueryMapWithPage(Page page,Sort[] sorts,Map<String, Object> params);
    /**库存导出*/
    List<Inventory> findListByQueryMapParam(Map<String, Object> params);
    /**库存导入*/
    void importInv(XSSFWorkbook workbook);
}
