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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/user")
public class LoginController extends BaseController{

    @RequestMapping(value = {"/login"})
    public String login() {
         
        return "index";
    }
    
    @RequestMapping(value = {"/menu"})
    @ResponseBody
    public List<Map<String,String>> menu() {
        List<Map<String,String>> top_menu = new ArrayList<>();
        Map<String,String> map1 = new HashMap<>();
        map1.put("title", "系统管理");
        map1.put("icon", "larry-xitongshezhi1");
        map1.put("pid", "0");
        Map<String,String> map2 = new HashMap<>();
        map2.put("title", "内容管理");
        map2.put("icon", "larry-neirongguanli");
        map2.put("pid", "35");
        Map<String,String> map3 = new HashMap<>();
        map3.put("title", "微信公众");
        map3.put("icon", "larry-weixingongzhongpingtai");
        map3.put("pid", "40");
        Map<String,String> map4 = new HashMap<>();
        map4.put("title", "扩展模块");
        map4.put("icon", "larry-ht_expand");
        map4.put("pid", "46");
        top_menu.add(map1);
        top_menu.add(map2);
        top_menu.add(map3);
        top_menu.add(map4);
        
        
        /*List pidone = new ArrayList<>();
        Map map5 = new HashMap<>();
        map5.put("title", "后台首页");
        map5.put("icon", "larry-houtaishouye");
        map5.put("href", "html/main.html");
        pidone.add(map5);
        
        List pidone01 = new ArrayList<>();
        Map map501c1 = new HashMap<>();
        map501c1.put("title", "个人信息");
        map501c1.put("icon", "larry-gerenxinxi1");
        map501c1.put("href", "html/personInfo.html");
        Map map501c2 = new HashMap<>();
        map501c2.put("title", "修改密码");
        map501c2.put("icon", "larry-xiugaimima2");
        map501c2.put("href", "html/changepwd.html");
        Map map501c3 = new HashMap<>();
        map501c3.put("title", "日志信息");
        map501c3.put("icon", "larry-rizhi2");
        map501c3.put("href", "html/myloginfo.html");
        pidone01.add(map501c1);
        pidone01.add(map501c2);
        pidone01.add(map501c3);
        
        Map map501 = new HashMap<>();
        map501.put("title", "我的面板");
        map501.put("icon", "larry-gerenxinxi5");
        map501.put("spread", "true");
        map501.put("children",pidone01 );
        pidone.add(map501);*/
        
        return top_menu;
    }
    
}

