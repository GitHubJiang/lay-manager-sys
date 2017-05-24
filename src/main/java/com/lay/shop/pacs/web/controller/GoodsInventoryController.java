/**
 * Copyright (c) 2015 Jumbomart All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Jumbomart. You shall not
 * disclose such Confidential Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jumbo.
 * 
 * JUMBOMART MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT. JUMBOMART SHALL NOT BE LIABLE FOR ANY
 * DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 * 
 */
package com.lay.shop.pacs.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lay.shop.pacs.bind.annotation.QueryBeanParam;
import com.lay.shop.pacs.bind.bean.QueryBean;
import com.lay.shop.pacs.command.GoodsInvCommand;
import com.lay.shop.pacs.exception.ErrorCodes;
import com.lay.shop.pacs.orm.dao.Page;
import com.lay.shop.pacs.orm.dao.Pagination;
import com.lay.shop.pacs.orm.dao.Sort;
import com.lay.shop.pacs.result.Result;
import com.lay.shop.pacs.service.GoodsInventoryService;


@Controller
public class GoodsInventoryController extends BaseController{

    @Autowired
    GoodsInventoryService goodsInventoryService;
    
    /**品牌列表*/
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = {"/list"})
    @ResponseBody
    public Result<Pagination> list(@QueryBeanParam QueryBean queryBean) {
        
        Result<Pagination> result = new Result<Pagination>();        
        Page page = queryBean.getPage();
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
        }        
        return result;
    }
}
