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
package com.lay.shop.pacs.util;

public final class PageUtils {

    private PageUtils() {}
    
    /**
     * 通过页码和每页条数，计算出第一条记录的索引数
     * @author 李光辉
     * @param pageNo 页码(第几页，从1开始)
     * @param size 每页条数
     * @return
     * @since
     */
    public static int pageNoToStart(int pageNo, int size) {
        return (pageNo - 1) * size;
    }
    
    /**
     * 通过索引数和每页条数，计算出页码
     * @author 李光辉
     * @param start 第一条记录的索引数
     * @param size 每页条数
     * @return
     * @since
     */
    public static int startToPageNo(int start, int size) {
        return (start+size-1) / size;
    }
}
