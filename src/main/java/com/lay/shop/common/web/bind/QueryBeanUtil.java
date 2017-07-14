/**
 * 
 */
package com.lay.shop.common.web.bind;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.common.utils.Validator;

/****
 * 
 * @author Lay
 * @date 2017年7月14日 下午5:44:30
 * @since
 */
public class QueryBeanUtil {
    /**
     * 解析request中参数，转换成queryBean
     * 
     * @param parameterMap
     * @return
     * @throws ParseException
     */
    public static QueryBean parseParameter(HttpServletRequest request) throws ParseException {
        QueryBean queryBean = new QueryBean();
        Object size = request.getParameter("size");
        if (size != null) {
            try {
                queryBean.getPage().setSize(Integer.parseInt(size.toString()));
            } catch (NumberFormatException e) {
                queryBean.getPage().setSize(QueryBean.DEFULT_SIZE);
            }
        }
        Object startObject = request.getParameter("page");
        if (startObject != null) {
            try {
                int page = Integer.parseInt(startObject.toString());
                if (page > 0) {
                    queryBean.getPage().setPage(page);
                }
            } catch (NumberFormatException e) {
                queryBean.getPage().setStart(QueryBean.DEFULT_START);
            }
        }
        String sortStr = request.getParameter("sort");
        if (sortStr != null) {
            queryBean.setSorts(Sort.parse(sortStr));
        }

        Map<String, Object> queryParaMap = new HashMap<String, Object>();
        for (Object keyObject : request.getParameterMap().keySet()) {
            String key = keyObject.toString();
            String value = request.getParameter(key);
            // 若值为null不做处理
            if (Validator.isNullOrEmpty(value)) {
                continue;
            }
            // 去空格处理
            value = value.trim();
            queryParaMap.put(key, value);
        }
        queryBean.setParaMap(queryParaMap);
        return queryBean;
    }
}
