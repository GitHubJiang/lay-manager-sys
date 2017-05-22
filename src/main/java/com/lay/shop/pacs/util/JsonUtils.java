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
package com.lay.shop.pacs.util;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;


public final class JsonUtils {

    private JsonUtils() {}
    
    public static String toJsonString(Object obj) {
        if (obj == null) {
            return null;
        }
        
        return JSON.toJSONString(obj);
    }
    
    public static String toJsonString(Object obj, boolean prettyFormat) {
        if (obj == null) {
            return null;
        }
        
        return JSON.toJSONString(obj, prettyFormat);
    }
    
    public static <T> T parseJsonString(String jsonString, Class<T> clazz) {
        if (jsonString == null) {
            return null;
        }
        
        return JSON.parseObject(jsonString, clazz, Feature.IgnoreNotMatch);
    }
    
    public static <T> List<T> parseJsonArray(String jsonString, Class<T> clazz) {
        if (jsonString == null) {
            return null;
        }
        
        return JSON.parseArray(jsonString, clazz);
    }
}
