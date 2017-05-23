package com.lay.shop.pacs.orm.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.mybatis.spring.SqlSessionTemplate;

import com.lay.shop.pacs.orm.model.BaseModel;

public class CommonQueryProcess extends QueryProcess {

	public static final String SAVE_OR_UPDATE="saveOrUpdate";
	
	public static final String SAVE_OR_UPDATE_VERSION="saveOrUpdateByVersion";
		
	public CommonQueryProcess(SqlSessionTemplate sqlSessionTemplate){
		this.sqlSessionTemplate=sqlSessionTemplate;
		
	}
	
	private Object processSaveOrUpdate(Object[] objs,String className){
		
		BaseModel baseModel=(BaseModel)objs[0];
		
		//update
        if (baseModel.getId() != null) {
            String statementPath = className + ".update";
            return sqlSessionTemplate.update(statementPath, objs[0]);
        } else {
            //insert
            String statementPath = className + ".insert";
            return sqlSessionTemplate.insert(statementPath, objs[0]);
        }
		
		
	}
	/**
	 * 通过version的方式进行修改
	 * @param objs
	 * @param className
	 * @return
	 */
	private Object processSaveOrUpdateByVersion(Object[] objs,String className){
		
		BaseModel baseModel=(BaseModel)objs[0];
		
		//update
		if(baseModel.getId()!=null){
			
			String statementPath=className+".updateByVersion";
			
			return sqlSessionTemplate.update(statementPath, objs[0]);
			
		}
		//insert
		else{
			String statementPath=className+".insert";
			
			return sqlSessionTemplate.insert(statementPath, objs[0]);
		}
		
		
	}
	
	@Override
	public Object process(ProceedingJoinPoint pjp) {
		// TODO Auto-generated method stub
		MethodSignature ms = (MethodSignature)pjp.getSignature();
		Method method=ms.getMethod();
		
		if(method.getName().equalsIgnoreCase(SAVE_OR_UPDATE)){
			return processSaveOrUpdate(pjp.getArgs(),ms.getDeclaringType().getName());
		}
		
		else if(method.getName().equalsIgnoreCase(SAVE_OR_UPDATE_VERSION)){
			return processSaveOrUpdateByVersion(pjp.getArgs(),ms.getDeclaringType().getName());
		}
		
		String queryPath=ms.getDeclaringType().getName()+"."+method.getName();
		return sqlSessionTemplate.selectList(queryPath,  pjp.getArgs());
	}

}
