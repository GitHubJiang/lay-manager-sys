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
package com.lay.shop.pacs.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lay.shop.pacs.command.GoodsInvCommand;
import com.lay.shop.pacs.dao.GoodsInventoryDao;
import com.lay.shop.pacs.dao.ItemSkuDao;
import com.lay.shop.pacs.model.GoodsInventory;
import com.lay.shop.pacs.model.ItemSku;
import com.lay.shop.pacs.orm.dao.Page;
import com.lay.shop.pacs.orm.dao.Pagination;
import com.lay.shop.pacs.orm.dao.Sort;

@Service
public class GoodsInventoryServiceImpl implements GoodsInventoryService {

    @Autowired
    private GoodsInventoryDao goodsInventoryDao;
    
    @Autowired
    private ItemSkuDao itemSkuDao;
    
    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public Pagination<GoodsInvCommand> findPageGoodsInventoryList(Page page, Sort[] sorts, Map<String, Object> params) {
        return this.goodsInventoryDao.findPageGoodsInventoryList(page, sorts, params);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public int updateInventoryById(Long id, Integer quantity) {        
        return this.goodsInventoryDao.updateInventoryById(id, quantity);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void addInventory(GoodsInvCommand command) {
        
        GoodsInventory inventory = new GoodsInventory();        
        BeanUtils.copyProperties(command, inventory);
        inventory.setCreateTime(new Date());
        inventory.setVersion(new Date());
        this.goodsInventoryDao.insert(inventory);
        
        ItemSku item = new ItemSku();
        BeanUtils.copyProperties(command, item);
        item.setCreateTime(new Date());
        item.setUpdateTime(new Date());
        this.itemSkuDao.insert(item); 
    }

    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public GoodsInvCommand findInventoryByCompanyCodeAndSkuCode(String companyCode, String skuCode) {
        return this.goodsInventoryDao.findInventoryByCompanyCodeAndSkuCode(companyCode, skuCode);
    }

    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public GoodsInvCommand findInventoryById(Long id) {      
        return this.goodsInventoryDao.findInventoryById(id);
    }
    
    
}
