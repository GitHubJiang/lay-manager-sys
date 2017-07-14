package com.lay.shop.modules.sys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lay.shop.common.exception.BusinessException;
import com.lay.shop.common.exception.ErrorCodes;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.web.BaseController;
import com.lay.shop.common.web.Result;
import com.lay.shop.common.web.bind.QueryBean;
import com.lay.shop.common.web.bind.QueryBeanParam;
import com.lay.shop.modules.sys.model.Url;
import com.lay.shop.modules.sys.service.UrlService;

/**
 * 
 * @author Lay
 * @date 2017年6月9日 上午9:58:37
 * @since
 */
@Controller
@RequestMapping("/sys/url/")
public class UrlController extends BaseController {
    
    @Autowired
    private UrlService urlService;
    
    @SuppressWarnings("rawtypes")
    @RequestMapping(value={"list"})
    @ResponseBody
    public Result<Pagination> list(@QueryBeanParam QueryBean queryBean) {
        Result<Pagination> result = new Result<>();
        try {
            Pagination<Url> list = this.urlService.findListByQueryMapWithPage(queryBean.getPage(), queryBean.getSorts(), queryBean.getParaMap());
            result.setData(list);
        } catch (BusinessException e) {
            logger.error("UrlController.businessException:{}",e.getMessage());
            result.setCode(e.getValue());
            result.setMsg(e.getMessage());
        } catch (Exception e) {
            logger.error("UrlController:{}", e.getMessage());
            result.setCode(ErrorCodes.RESULT_NO.getValue());
            result.setMsg(ErrorCodes.RESULT_NO.getMsg());
        }
        return result;
    }
    
    @RequestMapping(value={"add"})
    @ResponseBody
    public Result<Url> add(Url url) {
        Result<Url> result = new Result<>();
        try {
            this.urlService.saveOrUpdateUrl(url);
        } catch (BusinessException e) {
            result.setCode(e.getValue());
            result.setMsg(e.getMessage());
        } catch (Exception e) {
            logger.error("UserController:{}", e.getMessage());
            result.setCode(ErrorCodes.RESULT_NO.getValue());
            result.setMsg(ErrorCodes.RESULT_NO.getMsg());
        }
        return result;
    }
    
    @RequestMapping(value={"update"})
    @ResponseBody
    public Result<Url> update(Url url) {
        Result<Url> result = new Result<>();
        try {
            this.urlService.saveOrUpdateUrl(url);
        } catch (BusinessException e) {
            result.setCode(e.getValue());
            result.setMsg(e.getMessage());
        } catch (Exception e) {
            logger.error("UserController:{}", e.getMessage());
            result.setCode(ErrorCodes.RESULT_NO.getValue());
            result.setMsg(ErrorCodes.RESULT_NO.getMsg());
        }
        return result;
    }
    
    @RequestMapping(value={"delete/{id}"})
    @ResponseBody
    public Result<Url> update(@PathVariable("id") Long id) {
        Result<Url> result = new Result<>();
        try {
            this.urlService.delete(id);
        } catch (BusinessException e) {
            result.setCode(e.getValue());
            result.setMsg(e.getMessage());
        } catch (Exception e) {
            logger.error("UserController:{}", e.getMessage());
            result.setCode(ErrorCodes.RESULT_NO.getValue());
            result.setMsg(ErrorCodes.RESULT_NO.getMsg());
        }
        return result;
    }
    
}
