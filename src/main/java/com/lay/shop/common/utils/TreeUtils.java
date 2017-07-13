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
package com.lay.shop.common.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.google.common.collect.Maps;
import com.lay.shop.common.constants.SystemConstants;
import com.lay.shop.common.persistence.Node;
import com.lay.shop.modules.sys.command.MenuCommand;

public final class TreeUtils<T extends Node<T>> {
    
    @SuppressWarnings("rawtypes")
    private static TreeUtils treeUtils;
    
    private TreeUtils(){};
    
    @SuppressWarnings("rawtypes")
    public static TreeUtils getInstance() {  
         if (treeUtils == null) {    
             treeUtils = new TreeUtils();  
         }    
        return treeUtils;  
    }
    
    @SuppressWarnings("rawtypes")
    public T menu(List<T> dataList){
        
        Map<Long,MenuCommand> nodeMap = Maps.newHashMap();
        //构造节点列表（存入散列表）  
        for (Iterator<T> it = dataList.iterator(); it.hasNext();) {
            MenuCommand command = (MenuCommand) it.next();
            MenuCommand node = new MenuCommand();
            BeanUtils.copyProperties(command,node);
            nodeMap.put(node.getId(), node);
        }

        // 构造无序的多叉树  
        Set<Entry<Long, MenuCommand>> entrySet = nodeMap.entrySet();
        T root = null;
        for (Iterator<Entry<Long, MenuCommand>> it = entrySet.iterator(); it.hasNext();) {
            T node = (T) ((Map.Entry) it.next()).getValue();
            if (node.getParentId() == null || SystemConstants.treeRoot.equals(node.getParentId())) {
                root = node;
            } else {
                ((T) nodeMap.get(node.getParentId())).getChildren().add(node);
            }
        } 
        return root;
    }
}
