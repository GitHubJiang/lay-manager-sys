/**
 * 
 */
package com.lay.shop.common.web.bind;

import java.util.Map;

import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Sort;

/**
 * 
 * @author Lay
 * @date 2017年7月14日 下午5:28:16
 * @since
 */
public class QueryBean {
    
	public static final int DEFULT_START = 0;
	public static final int DEFULT_PAGE = 1;
	public static final int DEFULT_SIZE = 20;
	
	private Page page = new Page(DEFULT_PAGE, DEFULT_SIZE);
	
	/** 排序 */
	private Sort[] sorts;
	
	/** 查询参数 */
    private Map<String, Object> paraMap;

    public Sort[] getSorts() {
        return sorts;
    }

    public void setSorts(Sort[] sorts) {
        this.sorts = sorts;
    }

    public Map<String, Object> getParaMap() {
        return paraMap;
    }

    public void setParaMap(Map<String, Object> paraMap) {
        this.paraMap = paraMap;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
	
}
