package com.lay.shop.pacs.service;

import java.util.Map;

import com.lay.shop.pacs.command.GoodsInvCommand;
import com.lay.shop.pacs.orm.dao.Page;
import com.lay.shop.pacs.orm.dao.Pagination;
import com.lay.shop.pacs.orm.dao.Sort;

public interface GoodsInventoryService {

    /** 分页查询库存信息 */
    public Pagination<GoodsInvCommand> findPageGoodsInventoryList(Page page, Sort[] sorts, Map<String, Object> params);

}
