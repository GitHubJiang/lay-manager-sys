/**
 * 
 */
package com.lay.shop.pacs.bind.bean;

import java.util.Map;

import com.lay.shop.pacs.orm.dao.Page;
import com.lay.shop.pacs.orm.dao.Sort;

/**
 * @author xianze.zhang
 *@creattime 2013-6-5
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

    private Map<String, String> sortDirection;

    public Map<String, String> getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(Map<String, String> sortDirection) {
        this.sortDirection = sortDirection;
    }

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
