package com.lay.shop.modules.sys.web;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lay.shop.common.web.BaseController;

/**
 * 
 * @author Lay
 * @date 2017年6月9日 上午9:58:37
 * @since
 */
@Controller
@RequestMapping(value = "/sys/organization")
public class OrganizationController extends BaseController {

    @RequestMapping(value = "/menu")
    @ResponseBody
    public List<Map<String, Object>> menu(@RequestParam(value="pid",required=false) String id) {

        List<Map<String, Object>> topMenuList = Lists.newArrayList();
        Map<String, Object> topMap1 = Maps.newHashMap();
        topMap1.put("title", "系统管理");
        topMap1.put("icon", "larry-xitongshezhi1");
        topMap1.put("pid", "0");
        Map<String, Object> topMap2 = Maps.newHashMap();
        topMap2.put("title", "内容管理");
        topMap2.put("icon", "larry-neirongguanli");
        topMap2.put("pid", "35");
        Map<String, Object> topMap3 = Maps.newHashMap();
        topMap3.put("title", "微信公众");
        topMap3.put("icon", "larry-weixingongzhongpingtai");
        topMap3.put("pid", "40");
        topMenuList.add(topMap1);
        topMenuList.add(topMap2);
        topMenuList.add(topMap3);
        
        //系统管理子菜单
        List<Map<String, Object>> childrenList = Lists.newArrayList();
        Map<String, Object> childrenMap1 = Maps.newHashMap();
        childrenMap1.put("title", "后台首页");
        childrenMap1.put("icon", "larry-houtaishouye");
        childrenMap1.put("href", "#");
       
        
        List<Map<String, Object>> children_List = Lists.newArrayList();
        Map<String, Object> childrenMap3 = Maps.newHashMap();
        childrenMap3.put("title", "个人信息");
        childrenMap3.put("icon", "larry-gerenxinxi1");
        childrenMap3.put("href", "#");
        Map<String, Object> childrenMap4 = Maps.newHashMap();
        childrenMap4.put("title", "修改密码");
        childrenMap4.put("icon", "larry-xiugaimima21");
        childrenMap4.put("href", "#");
        Map<String, Object> childrenMap5 = Maps.newHashMap();
        childrenMap5.put("title", "日志信息");
        childrenMap5.put("icon", "larry-rizhi2'");
        childrenMap5.put("href", "#");
        children_List.add(childrenMap3);
        children_List.add(childrenMap4);
        children_List.add(childrenMap5);
        Map<String, Object> childrenMap2 = Maps.newHashMap();
        childrenMap2.put("title", "我的面板");
        childrenMap2.put("icon", "larry-gerenxinxi5");
        childrenMap2.put("spread", "true");
        childrenMap2.put("children", children_List);
        
        childrenList.add(childrenMap1);
        childrenList.add(childrenMap2);
        logger.info("主菜单{}",JSON.toJSONString(topMenuList));
        logger.info("子菜单{}",JSON.toJSONString(childrenList));
        return id==null?topMenuList:childrenList;
    }
    
    @RequestMapping(value = "/index")
    public String menu() {

        return "index";
        
    }
}
