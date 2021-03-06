package com.lay.shop.greeston.controller.auth;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lay.shop.common.exception.ErrorCodes;
import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.common.web.Result;
import com.lay.shop.common.web.bind.QueryBean;
import com.lay.shop.common.web.bind.QueryBeanParam;
import com.lay.shop.common.web.controller.BaseController;
import com.lay.shop.greeston.manager.auth.UrlManager;
import com.lay.shop.greeston.model.auth.Url;

@Controller
@RequestMapping(value = "/auth")
public class UrlController extends BaseController {

    @Autowired
    private UrlManager urlManager;
    
    @RequestMapping(value = {"/url/list"})
    public String list(@QueryBeanParam QueryBean queryBean, Model model){
        Page page = queryBean.getPage();       
        queryBean.setSorts(Sort.parse("id desc"));      
        Sort[] sorts = queryBean.getSorts();
        Map<String, Object> params = queryBean.getParaMap();
        
        Pagination<Url> pagination =  this.urlManager.findListByQueryMapWithPage(page, sorts, params);
        model.addAttribute("pagination",pagination);
        return "modules/auth/url/list";
    }
    
    @RequestMapping(value = {"/url/update"})
    @ResponseBody
    public Result<Object> addOrUpdate(Url url){
        Result<Object> result = new Result<>();
        try{
            this.urlManager.saveOrUpdateUrl(url);
        }catch(Exception e){
            result.setCode(ErrorCodes.FAILED.getValue());
            result.setMsg(ErrorCodes.FAILED.getMsg());
        }
        return result;
    }
    
    @RequestMapping(value = {"/url/remove"})
    public String remove(Long id){
        this.urlManager.deleteUrlById(id);
        return "redirect:/auth/url/list";
    }
    
    //权限管理使用
    @RequestMapping(value = {"/url/allUrl"})
    @ResponseBody
    public List<Url> allUrl() {        
        return this.urlManager.findAllUrlByParam(null);
    }
	
}
