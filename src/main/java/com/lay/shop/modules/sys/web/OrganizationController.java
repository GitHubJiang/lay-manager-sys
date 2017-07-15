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
import com.lay.shop.modules.sys.command.OrganizationCommand;
import com.lay.shop.modules.sys.model.Organization;
import com.lay.shop.modules.sys.service.OrganizationService;

/**
 * 
 * @author Lay
 * @date 2017年6月9日 上午9:58:37
 * @since
 */
@Controller
@RequestMapping("/sys/organization/")
public class OrganizationController extends BaseController {
    
    @Autowired
    private OrganizationService organizationService;
    
    @RequestMapping(value={"list"})
    @ResponseBody
    public Result<Pagination<OrganizationCommand>> list(@QueryBeanParam QueryBean queryBean) {
        Result<Pagination<OrganizationCommand>> result = new Result<>();
        try {
            Pagination<OrganizationCommand> list = this.organizationService.findPageListByQueryMapWithPage(queryBean.getPage(), queryBean.getSorts(), queryBean.getParaMap());
            result.setData(list);
        } catch (BusinessException e) {
            logger.error("OrganizationController.businessException:{}",e.getMessage());
            result.setCode(e.getValue());
            result.setMsg(e.getMessage());
        } catch (Exception e) {
            logger.error("OrganizationController:{}", e.getMessage());
            result.setCode(ErrorCodes.RESULT_NO.getValue());
            result.setMsg(ErrorCodes.RESULT_NO.getMsg());
        }
        return result;
    }
    
    @RequestMapping(value={"add"})
    @ResponseBody
    public Result<Organization> add(Organization organization) {
        Result<Organization> result = new Result<>();
        try {
            this.organizationService.save(organization);
        } catch (BusinessException e) {
            result.setCode(e.getValue());
            result.setMsg(e.getMessage());
        } catch (Exception e) {
            logger.error("OrganizationController:{}", e.getMessage());
            result.setCode(ErrorCodes.RESULT_NO.getValue());
            result.setMsg(ErrorCodes.RESULT_NO.getMsg());
        }
        return result;
    }
    
    @RequestMapping(value={"update"})
    @ResponseBody
    public Result<Organization> update(Organization organization) {
        Result<Organization> result = new Result<>();
        try {
            this.organizationService.update(organization);
        } catch (BusinessException e) {
            result.setCode(e.getValue());
            result.setMsg(e.getMessage());
        } catch (Exception e) {
            logger.error("OrganizationController:{}", e.getMessage());
            result.setCode(ErrorCodes.RESULT_NO.getValue());
            result.setMsg(ErrorCodes.RESULT_NO.getMsg());
        }
        return result;
    }
    
    @RequestMapping(value={"delete/{id}"})
    @ResponseBody
    public Result<Organization> update(@PathVariable("id") Long id) {
        Result<Organization> result = new Result<>();
        try {
            this.organizationService.delete(id);
        } catch (BusinessException e) {
            result.setCode(e.getValue());
            result.setMsg(e.getMessage());
        } catch (Exception e) {
            logger.error("OrganizationController:{}", e.getMessage());
            result.setCode(ErrorCodes.RESULT_NO.getValue());
            result.setMsg(ErrorCodes.RESULT_NO.getMsg());
        }
        return result;
    }
    
}
