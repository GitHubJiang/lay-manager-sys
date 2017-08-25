package com.lay.shop.greeston.controller.inv;

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
import com.lay.shop.greeston.command.auth.UserDetailsCommand;
import com.lay.shop.greeston.controller.inv.view.ImportInvcInView;
import com.lay.shop.greeston.controller.inv.view.ImportInvcOutView;
import com.lay.shop.greeston.manager.inv.InventoryChangeManager;
import com.lay.shop.greeston.model.inv.InventoryChange;

@Controller
@RequestMapping(value = {"/inv/invc"})
public class InventoryChangeController extends BaseController {

    @Autowired
    private InventoryChangeManager inventoryChangeManager;
    
    /**库存列表*/
    @RequestMapping(value = {"/list"})
    public String list(@QueryBeanParam QueryBean queryBean, Model model){
        Page page = queryBean.getPage();
        Sort[] sorts = queryBean.getSorts();
        Map<String, Object> param = queryBean.getParaMap(); 
        Pagination<InventoryChange> pagination = this.inventoryChangeManager.findListByQueryMapWithPage(page, sorts, param);
        model.addAttribute("pagination",pagination);
        return "modules/inv/invclist";
    }
    
    /**入库模板下载*/
    @RequestMapping(value = {"/invcImportInTemplate"})
    public ModelAndView invcImportInTemplate(Model model) {
        ModelAndView mv = new ModelAndView(new ImportInvcInView("classpath:template/invc-import-in-template.xlsx"));
        return mv;
    }
    
    /**销售出库库模板下载*/
    @RequestMapping(value = {"/invcImportOutTemplate"})
    public ModelAndView invcImportOutTemplate(Model model) {
        ModelAndView mv = new ModelAndView(new ImportInvcOutView("classpath:template/invc-import-in-template.xlsx"));
        return mv;
    }
    
    /**入库库存导入*/
    @RequestMapping(value = {"/importInInvc"})
    @ResponseBody
    public Result<Object> importInInvc(@RequestParam(value = "invcFile",required = false) MultipartFile file) {
        Result<Object> result = new Result<>();
        try {
            if(file==null){
                throw new BusinessException(ErrorCodes.FILE_IS_EMPTY);
            }
            UserDetailsCommand user = this.getCurrentUserDetails();
            XSSFWorkbook workbook = (XSSFWorkbook)PoiUtil.createWorkbook(file.getInputStream());
            this.inventoryChangeManager.importInInvc(workbook, user.getUser().getId());
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
    
    /**销售出库库存导入*/
    @RequestMapping(value = {"/importOutInvc"})
    @ResponseBody
    public Result<Object> importOutInvc(@RequestParam(value = "invcFile",required = false) MultipartFile file) {
        Result<Object> result = new Result<>();
        try {
            if(file==null){
                throw new BusinessException(ErrorCodes.FILE_IS_EMPTY);
            }
            UserDetailsCommand user = this.getCurrentUserDetails();
            XSSFWorkbook workbook = (XSSFWorkbook)PoiUtil.createWorkbook(file.getInputStream());
            this.inventoryChangeManager.importOutInvc(workbook, user.getUser().getId());
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
