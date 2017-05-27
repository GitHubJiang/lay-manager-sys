/**
 * 
 */
package com.lay.shop.pacs.bind.bean;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.lay.shop.pacs.orm.dao.Sort;

/**
 * 对分页查询，分页参数做处理
 * @author 江家雷
 * @date 2017年5月27日 下午3:18:25
 * @since
 */
public class QueryBeanUtil {
	/**
	 * 解析request中参数，对分页查询做一个处理
	 * @param parameterMap
	 * @return
	 * @throws ParseException 
	 */
	public static QueryBean parseParameter(HttpServletRequest request) throws ParseException{
		QueryBean queryBean = new QueryBean();
		Object size = request.getParameter("size");
		if(size!=null){
			try {
				queryBean.getPage().setSize(Integer.parseInt(size.toString()));
			} catch (NumberFormatException e) {
				queryBean.getPage().setSize(QueryBean.DEFULT_SIZE);
			}
		}
		Object startObject = request.getParameter("page");
		if(startObject!=null){
			try {
				int page = Integer.parseInt(startObject.toString());
				if(page > 0) {
					queryBean.getPage().setPage(page);
				}
			} catch (NumberFormatException e) {
				queryBean.getPage().setStart(QueryBean.DEFULT_START);
			}
		}
		String sortStr = request.getParameter("sort");
		if(sortStr!=null){
			queryBean.setSorts(Sort.parse(sortStr));
		}
		
		Map<String, Object> queryParaMap = new HashMap<String, Object>();
		for(Object keyObject:request.getParameterMap().keySet()){
			String key = keyObject.toString();
			String value = request.getParameter(key);
            //若值为null不做处理
            if(StringUtils.isBlank(value)){
                continue;
            }
            //去空格处理
            value=value.trim();
            queryParaMap.put(key, value);
		}
		queryBean.setParaMap(queryParaMap);
		return queryBean;
	}
}
