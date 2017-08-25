package com.lay.shop.greeston.controller.inv;

import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lay.shop.common.exception.BusinessException;
import com.lay.shop.common.exception.ErrorCodes;
import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.common.utils.PoiUtil;
import com.lay.shop.common.web.Result;
import com.lay.shop.common.web.bind.QueryBean;
import com.lay.shop.common.web.bind.QueryBeanParam;
import com.lay.shop.common.web.controller.BaseController;
import com.lay.shop.greeston.controller.inv.view.ExportInvView;
import com.lay.shop.greeston.controller.inv.view.ImportInvView;
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
    
    /**全量库存模板下载*/
    @RequestMapping(value = {"/invImportTemplate"})
    public ModelAndView invImportTemplate(Model model) {
        ModelAndView mv = new ModelAndView(new ImportInvView("classpath:template/inv-import-template.xlsx"));
        return mv;
    }
    
    /**全量库存导入*/
    @RequestMapping(value = {"/importInv"})
    @ResponseBody
    public Result<Object> importInv(@RequestParam(value = "invFile",required = false) MultipartFile file) {
        Result<Object> result = new Result<>();
        try {
            if(file==null){
                throw new BusinessException(ErrorCodes.FILE_IS_EMPTY);
            }
            XSSFWorkbook workbook = (XSSFWorkbook)PoiUtil.createWorkbook(file.getInputStream());
            this.inventoryManager.importInv(workbook);
        } catch (BusinessException e) {
            logger.error(e.getMessage());
            result.setCode(e.getValue());
            result.setMsg(e.getMessage());
        } catch (Exception e) {
            result.setCode(ErrorCodes.FAILED.getValue());
            result.setMsg(ErrorCodes.FAILED.getMsg());
        }

        return result;
    }
}
