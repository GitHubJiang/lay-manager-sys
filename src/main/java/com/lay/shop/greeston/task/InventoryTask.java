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
package com.lay.shop.greeston.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lay.shop.greeston.manager.inv.InventoryChangeManager;

@Component
public class InventoryTask {
    
    Logger logger = LoggerFactory.getLogger(InventoryTask.class);

    @Autowired
    private InventoryChangeManager inventoryChangeManager;
    
    /**销售出库定时任务*/
    @Scheduled(cron = "0 0 0/1 * * *")   
    public void importOutInvcTask(){
        logger.info("销售出库定时任务----------start----------");
        inventoryChangeManager.importOutInvcTask();
        logger.info("销售出库定时任务----------end----------");
    }
    
    /**入库定时任务*/
    @Scheduled(cron = "0 0 0/1 * * *")   
    public void importInInvcTask(){
        logger.info("入库定时任务----------start----------");
        inventoryChangeManager.importInInvcTask();
        logger.info("入库定时任务----------end----------");
    }
}
