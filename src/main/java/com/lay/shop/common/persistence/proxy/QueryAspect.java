package com.lay.shop.common.persistence.proxy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;

import com.lay.shop.pacs.orm.annotation.QueryPage;

@Aspect
public class QueryAspect implements Ordered, InitializingBean {

    @Autowired(required = false)
    private SqlSessionTemplate sqlSessionTemplate;

    public void afterPropertiesSet() throws Exception {
        System.out.println();
    }

    public int getOrder() {
        return 20;
    }

    @Around("this(com.lay.shop.common.persistence.dao.BaseDao)")
    public Object doQuery(ProceedingJoinPoint pjp) throws Throwable {

        MethodSignature ms = (MethodSignature) pjp.getSignature();
        QueryPage queryPageCount = ms.getMethod().getAnnotation(QueryPage.class);

        if (queryPageCount != null) {
            return new CustomPageProcess(sqlSessionTemplate).process(pjp);
        } else {
            return pjp.proceed(pjp.getArgs());
        }
    }
}

