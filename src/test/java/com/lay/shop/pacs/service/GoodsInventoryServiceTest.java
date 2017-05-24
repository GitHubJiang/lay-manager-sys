package com.lay.shop.pacs.service;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lay.shop.pacs.SpringTest;
import com.lay.shop.pacs.bind.bean.QueryBean;
import com.lay.shop.pacs.command.GoodsInvCommand;
import com.lay.shop.pacs.orm.dao.Page;
import com.lay.shop.pacs.orm.dao.Pagination;
import com.lay.shop.pacs.orm.dao.Sort;

public class GoodsInventoryServiceTest extends SpringTest {

    @Autowired
    GoodsInventoryService goodsInventoryService;
    
    /** 分页查询库存信息 */
    @Test
    public void findPageGoodsInventoryListTest(){
        QueryBean queryBean = new QueryBean();
        Page page = queryBean.getPage();
        queryBean.setSorts(Sort.parse("id desc"));
        Sort[] sorts = queryBean.getSorts();
        Map<String, Object> params = queryBean.getParaMap();
        Pagination<GoodsInvCommand>  pagination = this.goodsInventoryService.findPageGoodsInventoryList(page, sorts, params);
        this.printPretty(pagination);
    }

    @Test
    public void addInventoryTest(){
        //this.goodsInventoryService.addInventory(command);
    }
}
