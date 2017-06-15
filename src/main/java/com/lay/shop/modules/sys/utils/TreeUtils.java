package com.lay.shop.modules.sys.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lay.shop.common.persistence.TreeCommand;

public class TreeUtils<T extends TreeCommand> {

    private TreeUtils(){        
    }
    
    private static TreeUtils instance = new TreeUtils();  

    
    public static TreeUtils getInstance() {  
        return instance;  
    } 
    
    public T toTreeNode(List<T> nodes,Long roodId){
        HashMap nodeList = new HashMap();
        // 根节点
        T root = null;
        // 根据结果集构造节点列表（存入散列表）
        for (T t : nodes) {
            nodeList.put(t.getId(),t);
        }
        // 构造无序的多叉树
        Set<T> entrySet = nodeList.entrySet();
        
        for (Iterator it = entrySet.iterator(); it.hasNext();) {
            T node = (T) ((Map.Entry) it.next()).getValue();            
            if (node.getParentId() == null || node.getParentId().equals("") || node.getParentId().equals(roodId)) {
                root = node;
            } else {
                ((T) nodeList.get(node.getParentId())).addChild(node);
            }
        }        
        return root;
    }
}
