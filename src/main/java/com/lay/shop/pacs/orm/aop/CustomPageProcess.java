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
package com.lay.shop.pacs.orm.aop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.mybatis.spring.SqlSessionTemplate;

import com.lay.shop.pacs.orm.annotation.QueryPage;
import com.lay.shop.pacs.orm.dao.Page;
import com.lay.shop.pacs.orm.dao.PageSort;
import com.lay.shop.pacs.orm.dao.Pagination;
import com.lay.shop.pacs.orm.dao.Sort;
import com.lay.shop.pacs.orm.exception.ParameterException;
import com.lay.shop.pacs.orm.util.PageBeanUtilsBean;

public class CustomPageProcess extends QueryProcess {

    public CustomPageProcess(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    private Number queryCount(String path, Object filters) {

        Number count = (Number) sqlSessionTemplate.selectOne(path, filters);

        return count;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> querySearchFilter(Object o) {

        if (o instanceof Map) {
            return (Map<String, Object>) o;
        }

        Map<String, Object> param = null;
        try {
            param = new PageBeanUtilsBean().describe(o);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return param;

    }

    /**
     * 获取平行参数并封装
     * 
     * @param args
     * @return
     */
    private PageSort queryPageSort(Object[] args) {

        Map<String, Object> argMap = new HashMap<String, Object>();

        for (Object obj : args) {
            if (obj instanceof Page)
                argMap.put(Page.class.getName(), obj);
            else if (obj instanceof Sort[])
                argMap.put(Sort[].class.getName(), obj);
            else
                argMap.put("filter", obj);
        }

        Map<String, Object> param = querySearchFilter(argMap.get("filter"));
        return new PageSort((Page) argMap.get(Page.class.getName()), (Sort[]) argMap.get(Sort[].class.getName()), param);
    }


    @SuppressWarnings({"rawtypes", "unchecked"})
    public Object process(ProceedingJoinPoint pjp) {
        MethodSignature ms = (MethodSignature) pjp.getSignature();

        Method method = ms.getMethod();

        String queryListPath = ms.getDeclaringType().getName() + "." + method.getName();


        QueryPage queryPageCount = method.getAnnotation(QueryPage.class);

        if (queryPageCount == null) throw new ParameterException("if method  has Page parameter,need set annotation QueryPageCount");

        String queryCountPath = ms.getDeclaringType().getName() + "." + queryPageCount.value();

        PageSort pageSort = queryPageSort(pjp.getArgs());

        Page page = pageSort.getPage();

        Number count = 0;
        // 如果有分页
        if (page != null) {
            count = queryCount(queryCountPath, pageSort.getSearchFilter());
            if ((count == null) || (count.intValue() <= 0)) {
                return new Pagination(new ArrayList(), count.longValue());
            }
        } else {
            page = new Page(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);
        }

        RowBounds rowBounds = new RowBounds(page.getStart(), page.getSize());
        List list = (List) sqlSessionTemplate.selectList(queryListPath, pageSort, rowBounds);

        if (count.equals(0)) count = list.size();

        int pageCount = (count.intValue() + page.getSize() - 1) / page.getSize();

        Pagination pagiList = new Pagination(list, count.longValue(), page.getPage(), pageCount, page.getStart(), page.getSize());

        return pagiList;
    }


}
