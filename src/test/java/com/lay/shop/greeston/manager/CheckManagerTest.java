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
package com.lay.shop.greeston.manager;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lay.shop.SpringTest;
import com.lay.shop.greeston.command.CheckCommand;

public class CheckManagerTest extends SpringTest{

    @Autowired
    private CheckManager checkManager;
    
    @Test
    public void checkUniqueCodeTest(){
        CheckCommand command = new CheckCommand();
        command.setFieldName("role_id");
        command.setFieldValue("1");
        command.setTable("au_role_pri");
        command.setId(1L);
        this.checkManager.checkUniqueCode(command);
    }
}
