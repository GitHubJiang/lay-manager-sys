package com.lay.shop.modules.inv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lay.shop.common.exception.ErrorCodes;
import com.lay.shop.common.persistence.Result;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.web.BaseController;
import com.lay.shop.modules.inv.command.GoodsInvCommand;
import com.lay.shop.modules.inv.service.GoodsInventoryService;


@Controller
@RequestMapping(value = "/inventory")
public class GoodsInventoryController extends BaseController{

    @Autowired
    GoodsInventoryService goodsInventoryService;
    
    /**分页查询商品库存列表*/
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = {"/list"})
    @ResponseBody
    public Result<Pagination> list() {
        
       Result<Pagination> result = new Result<Pagination>();        
       /*  Page page = queryBean.getPage();
        queryBean.setSorts(Sort.parse("id desc"));
        Sort[] sorts = queryBean.getSorts();
        Map<String, Object> params = queryBean.getParaMap();     
        try{
            Pagination<GoodsInvCommand> pagination = this.goodsInventoryService.findPageGoodsInventoryList(page, sorts, params);
            result.setData(pagination);
        }catch (Exception e) {
          logger.error("系统异常，错误信息：{}",e);
          result.setCode(ErrorCodes.RESULT_NO.getValue());
          result.setMessage(ErrorCodes.RESULT_NO.getMessage());
        }        */
        return result;
    }
    
    /** 根据库存id 更新库存 */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = {"/updateInventoryById/{id}/{quantity}"})
    @ResponseBody
    public Result updateInventoryById(@PathVariable Long id, @PathVariable Integer quantity) {
        Result result = new Result();
        try {
            this.goodsInventoryService.updateInventoryById(id, quantity);
        } catch (Exception e) {
            logger.error("系统异常，错误信息：{}", e);
            result.setCode(ErrorCodes.RESULT_NO.getValue());
            result.setMessage(ErrorCodes.RESULT_NO.getMessage());
        }
        return result;
    }
    
    /**新增库信息*/
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = {"/addInventory"})
    @ResponseBody
    public Result addInventory(GoodsInvCommand command) {        
        Result result = new Result<Pagination>();
        try{
           
        }catch (Exception e) {
          logger.error("系统异常，错误信息：{}",e);
          result.setCode(ErrorCodes.RESULT_NO.getValue());
          result.setMessage(ErrorCodes.RESULT_NO.getMessage());
        }        
        return result;
    }
    
    /**校验库存表的sku_code是否已经存在*/
    @RequestMapping(value = {"/checkSkuCode/{skuCode}"})
    @ResponseBody
    public Result<Boolean> checkSkuCode(@PathVariable String skuCode) {
        Result<Boolean> result = new Result<Boolean>();
        try {
            GoodsInvCommand goodsInvCommand = this.goodsInventoryService.findInventoryByCompanyCodeAndSkuCode("YIJIN", skuCode);
           /* if (ObjectValidator.isNotNullOrEmpty(goodsInvCommand)) {
                result.setData(Boolean.FALSE);
            } else {
                result.setData(Boolean.TRUE);
            }*/
        } catch (Exception e) {
            logger.error("系统异常，错误信息：{}", e);
            result.setCode(ErrorCodes.RESULT_NO.getValue());
            result.setMessage(ErrorCodes.RESULT_NO.getMessage());
        }
        return result;
    }
    
    @RequestMapping(value = {"/find/{skuCode}"})
    @ResponseBody
    public Result<GoodsInvCommand> getGoodsInventory(@PathVariable Long id) {
        Result<GoodsInvCommand> result = new Result<GoodsInvCommand>();
        try {
            GoodsInvCommand goodsInvCommand = this.goodsInventoryService.findInventoryById(id);
            result.setData(goodsInvCommand);
        } catch (Exception e) {
            logger.error("系统异常，错误信息：{}", e);
           
        }
        return result;
    }
}
