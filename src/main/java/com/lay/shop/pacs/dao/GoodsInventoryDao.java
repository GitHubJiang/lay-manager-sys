package com.lay.shop.pacs.dao;


import java.util.Map;

import com.lay.shop.pacs.command.GoodsInvCommand;
import com.lay.shop.pacs.model.GoodsInventory;
import com.lay.shop.pacs.orm.annotation.QueryPage;
import com.lay.shop.pacs.orm.dao.Page;
import com.lay.shop.pacs.orm.dao.Pagination;
import com.lay.shop.pacs.orm.dao.Sort;
import com.lay.shop.pacs.orm.dao.supports.BaseDao;

public interface GoodsInventoryDao extends BaseDao<GoodsInventory, Long> {

    @QueryPage("findPageGoodsInventoryListCount")
    public Pagination<GoodsInvCommand> findPageGoodsInventoryList(Page page, Sort[] sorts, Map<String, Object> params);

}
