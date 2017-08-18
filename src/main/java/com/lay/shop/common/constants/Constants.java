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
package com.lay.shop.common.constants;

public final class Constants {
    
    private Constants(){};

    /** 正常*/
    public static final Integer LIFECYCLE_NORMAL=1;    
    /** 禁用 */
    public static final Integer LIFECYCLE_DISABLE=2;    
    /** 已删除 */
    public static final Integer LIFECYCLE_DELETED=3;    
    /** 入库操作 */
    public static final Integer INVC_TYPE_IN = 1;    
    /** 出库操作 */
    public static final Integer INVC_TYPE_OUT = 2;    
    /** 处理状态:0.待处理 */
    public static final Integer PROCESS_STATUS_NEW = 0;
    /** 处理状态:1.已处理 */
    public static final Integer PROCESS_STATUS_SUCCESS = 1;
    /** 处理状态:2.异常 */
    public static final Integer PROCESS_STATUS_ERROR = 2;
}
