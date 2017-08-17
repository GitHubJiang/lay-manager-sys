package com.lay.shop.greeston.controller.inv;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.common.web.bind.QueryBean;
import com.lay.shop.common.web.bind.QueryBeanParam;
import com.lay.shop.greeston.controller.BaseController;
import com.lay.shop.greeston.controller.inv.view.ExportInvView;
import com.lay.shop.greeston.manager.inv.InventoryManager;
import com.lay.shop.greeston.model.inv.Inventory;

@Controller
@RequestMapping(value = {"/inv/inventory"})
public class InventoryController extends BaseController {

    @Autowired
    private InventoryManager inventoryManager;
    
    /**库存列表*/
    @RequestMapping(value = {"/list"})
    public String list(@QueryBeanParam QueryBean queryBean, Model model){
        Page page = queryBean.getPage();
        Sort[] sorts = queryBean.getSorts();
        Map<String, Object> param = queryBean.getParaMap(); 
        Pagination<Inventory> pagination = this.inventoryManager.findListByQueryMapWithPage(page, sorts, param);
        model.addAttribute("pagination",pagination);
        return "modules/inv/invlist";
    }
    
    /**导出库存信息*/
    @RequestMapping(value = {"/exportInv"})
    public ModelAndView exportInv(@QueryBeanParam QueryBean queryBean, Model model){
        Map<String, Object> param = queryBean.getParaMap(); 
        List<Inventory> invList = this.inventoryManager.findListByQueryMapParam(param);
        model.addAttribute("invList", invList);
        return new ModelAndView(new ExportInvView("classpath:template/inv-export.xlsx"));
    }
    
}
