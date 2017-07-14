package com.lay.shop.modules.sys.web;

import java.util.List;

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
import com.lay.shop.modules.sys.model.OrganizationType;
import com.lay.shop.modules.sys.service.OrganizationTypeService;

/**
 * 
 * @author Lay
 * @date 2017年6月9日 上午9:58:37
 * @since
 */
@Controller
@RequestMapping("/sys/orgtype/")
public class OrganizationTypeController extends BaseController {
    
    @Autowired
    private OrganizationTypeService organizationTypeService;
    
    @RequestMapping(value={"list"})
    @ResponseBody
    public Result<Pagination<OrganizationType>> list(@QueryBeanParam QueryBean queryBean) {
        Result<Pagination<OrganizationType>> result = new Result<>();
        try {
            Pagination<OrganizationType> list = this.organizationTypeService.findListByQueryMapWithPage(queryBean.getPage(), queryBean.getSorts(), queryBean.getParaMap());
            result.setData(list);
        } catch (BusinessException e) {
            logger.error("OrganizationTypeController.businessException:{}",e.getMessage());
            result.setCode(e.getValue());
            result.setMsg(e.getMessage());
        } catch (Exception e) {
            logger.error("OrganizationTypeController:{}", e.getMessage());
            result.setCode(ErrorCodes.RESULT_NO.getValue());
            result.setMsg(ErrorCodes.RESULT_NO.getMsg());
        }
        return result;
    }
    
    @RequestMapping(value={"add"})
    @ResponseBody
    public Result<OrganizationType> add(OrganizationType OrganizationType) {
        Result<OrganizationType> result = new Result<>();
        try {
            this.organizationTypeService.saveOrUpdate(OrganizationType);
        } catch (BusinessException e) {
            result.setCode(e.getValue());
            result.setMsg(e.getMessage());
        } catch (Exception e) {
            logger.error("OrganizationTypeController:{}", e.getMessage());
            result.setCode(ErrorCodes.RESULT_NO.getValue());
            result.setMsg(ErrorCodes.RESULT_NO.getMsg());
        }
        return result;
    }
    
    @RequestMapping(value={"update"})
    @ResponseBody
    public Result<OrganizationType> update(OrganizationType OrganizationType) {
        Result<OrganizationType> result = new Result<>();
        try {
            this.organizationTypeService.saveOrUpdate(OrganizationType);
        } catch (BusinessException e) {
            result.setCode(e.getValue());
            result.setMsg(e.getMessage());
        } catch (Exception e) {
            logger.error("OrganizationTypeController:{}", e.getMessage());
            result.setCode(ErrorCodes.RESULT_NO.getValue());
            result.setMsg(ErrorCodes.RESULT_NO.getMsg());
        }
        return result;
    }
    
    @RequestMapping(value={"delete/{id}"})
    @ResponseBody
    public Result<OrganizationType> update(@PathVariable("id") Long id) {
        Result<OrganizationType> result = new Result<>();
        try {
            this.organizationTypeService.delete(id);
        } catch (BusinessException e) {
            result.setCode(e.getValue());
            result.setMsg(e.getMessage());
        } catch (Exception e) {
            logger.error("OrganizationTypeController:{}", e.getMessage());
            result.setCode(ErrorCodes.RESULT_NO.getValue());
            result.setMsg(ErrorCodes.RESULT_NO.getMsg());
        }
        return result;
    }
    
    @RequestMapping(value={"alllist"})
    @ResponseBody
    public Result<List<OrganizationType>> allList(@QueryBeanParam QueryBean queryBean) {
        Result<List<OrganizationType>> result = new Result<>();
        try {
            List<OrganizationType> list = this.organizationTypeService.findAllList();
            result.setData(list);
        } catch (BusinessException e) {
            logger.error("OrganizationTypeController.businessException:{}",e.getMessage());
            result.setCode(e.getValue());
            result.setMsg(e.getMessage());
        } catch (Exception e) {
            logger.error("OrganizationTypeController:{}", e.getMessage());
            result.setCode(ErrorCodes.RESULT_NO.getValue());
            result.setMsg(ErrorCodes.RESULT_NO.getMsg());
        }
        return result;
    }
}
