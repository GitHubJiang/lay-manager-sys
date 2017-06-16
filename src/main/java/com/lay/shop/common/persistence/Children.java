package com.lay.shop.common.persistence;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class Children<T> {
    
    private List<T> list = new ArrayList();

    public void addChild(T node) {
        list.add(node);
    }
    @JSONField(name="array")
    public List getList(){
        return list;
    }
}