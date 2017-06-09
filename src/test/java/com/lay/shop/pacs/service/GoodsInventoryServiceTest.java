package com.lay.shop.pacs.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.lay.shop.modules.inv.command.GoodsInvCommand;
import com.lay.shop.modules.inv.service.GoodsInventoryService;
import com.lay.shop.pacs.SpringTest;

public class GoodsInventoryServiceTest extends SpringTest {

    @Autowired
    GoodsInventoryService goodsInventoryService;
    
    /** 分页查询库存信息 */
    @Test
    public void findPageGoodsInventoryListTest(){
       
    }

    @Test
    @Rollback(false)
    public void addInventoryTest(){
        GoodsInvCommand command = new GoodsInvCommand();
        command.setBarCode("456123789");
        command.setCompanyCode("YIJIN");
        command.setImgUrl("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1495678779&di=eeebb0abbd92f49685f1121f7b9b1c1f&src=http://img.jdsc35.com/path14619/20160826014839.jpg");
        command.setQuantity(100);
        command.setSkuCode("YIJIN-123455");
        command.setSkuName("老虎钳");
        command.setSkuProperties("");
        command.setSupplierSkuCode("");
        this.goodsInventoryService.addInventory(command);
    }
    
    /** 根据公司编码和skuCode查询库存信息*/
    @Test
    public void findInventoryByCompanyCodeAndSkuCodeTest(){
        this.printPretty(this.goodsInventoryService.findInventoryByCompanyCodeAndSkuCode("YIJIN", "ESP0027-699-XS"));
    }
    
    /** 根据库存id查询库存信息*/
    @Test
    public void findInventoryByIdTest(){
        this.printPretty(this.goodsInventoryService.findInventoryById(1L));
    }
}
