/**
 * 
 */
package com.lay.shop.pacs.bind.bean;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.lay.shop.pacs.bind.DateUtil;
import com.lay.shop.pacs.orm.dao.Sort;

/**
 * 对分页查询，分页参数做处理
 * @author 江家雷
 * @date 2017年5月27日 下午3:18:25
 * @since
 */
public class QueryBeanUtil {
    /**
     * 解析request中参数，转换成queryBean
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
        
        String sortDirection=request.getParameter("sortDirection");
        if(StringUtils.isNotBlank(sortDirection)){
            String[] sortDArray=sortDirection.split("@");
            
            Map<String,String> sortDirectionMap=new HashMap<String,String>();
            sortDirectionMap.put(sortDArray[0], "data-sorted-direction=\""+sortDArray[1]+"\"");
            queryBean.setSortDirection(sortDirectionMap);
            
        }
        
        Map<String, Object> queryParaMap = new HashMap<String, Object>();
        for(Object keyObject:request.getParameterMap().keySet()){
            String key = keyObject.toString();
            if(QueryPara.isQueryPara(key)){
                
                String value = request.getParameter(key);
                //若值为null不做处理
                if(StringUtils.isBlank(value)){
                    continue;
                }
                //去空格处理
                value=value.trim();
                
                QueryPara queryPara = QueryPara.parse(key);
    
                queryParaMap.put(queryPara.getName(), dealType(queryPara.getType(),value));
            }
        }
        queryBean.setParaMap(queryParaMap);
        return queryBean;
    }
    /**
     * 根据传入的参数，生成sql进行like查询的表达式
     * 例如：
     * 传入abc
     * 返回%abc%
     * 
     * 传入ab_c
     * 返回%ab\_c%
     * @param para
     * @return
     */
    public static String genLikeString(String para){
        String likeString = para.replace("_", "\\_").replace("%", "\\%");
        return "%" + likeString + "%";
    }
    /**
     * 根据传入的参数，生成sql进行like查询的表达式(左like)
     * 例如：
     * 传入abc
     * 返回%abc%
     * 
     * 传入ab_c
     * 返回%ab\_c%
     * @param para
     * @return
     */
    public static String genLeftLikeString(String para){
        String likeString = para.replace("_", "\\_").replace("%", "\\%");
        return likeString + "%";
    }

    /**
     * 
     * @param para
     * @return
     * @throws ParseException 
     */
    public static Object dealType(String typeString,String value) throws ParseException{
        if(QueryPara.TYPE_INT.equals(typeString)){
            return Integer.parseInt(value);
        }else if(QueryPara.TYPE_LONG.equals(typeString)){
            return Long.parseLong(value);
        }else if(QueryPara.TYPE_STRING_LIKE.equals(typeString)){
            return genLikeString(value);
        }else if(QueryPara.TYPE_STRING_LEFT_LIKE.equals(typeString)){
            return genLeftLikeString(value);
        }else if(QueryPara.TYPE_DATE.equals(typeString)){
            return DateUtil.parse(value,"yyyy-MM-dd");
        }else if(QueryPara.TYPE_TIME.equals(typeString)){
            return DateUtil.parse(value,"yyyy-MM-dd hh:mm");
        }
        return value;
    }
    public static Map<String, Object> revertParams(Map<String, Object> params){
        if(params==null || params.size()==0){
            return params;
        }
        Map<String, Object>  newParams = new HashMap<String, Object>();
        Set<String> keys = params.keySet();
        for (String key : keys) {
            Object val = params.get(key);
            if(val instanceof String){
                String str = (String) val;
                if(str.startsWith("%") && str.endsWith("%")){
                    str = str.substring(1, str.length()-1);
                    newParams.put(key, str);
                }else if(str.startsWith("%") && !str.endsWith("%")){
                    str = str.substring(1, str.length());
                    newParams.put(key, str);
                }else if(!str.startsWith("%") &&str.endsWith("%")){
                    str = str.substring(0, str.length()-1);
                    newParams.put(key, str);
                }else{
                    newParams.put(key, str);
                }
            }else{
                newParams.put(key, val);
            }
        }
        return newParams;
    }
}
