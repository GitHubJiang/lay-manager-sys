package com.lay.shop.greeston.manager.inv;

import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.greeston.model.inv.InventoryChange;
/**
 * 销售库存管理
 * @author Lay
 * @date 2017年8月2日 下午3:33:49
 * @since
 */
public interface InventoryChangeManager {
    /**分页查询库存数据*/
    Pagination<InventoryChange> findListByQueryMapWithPage(Page page,Sort[] sorts,Map<String, Object> params);
    /**库存导出*/
    List<InventoryChange> findListByQueryMapParam(Map<String, Object> params);
    /**销售库存导入*/
    void importInv(XSSFWorkbook workbook,Long userId);
}
