package com.lay.shop.greeston.servlet;

import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lay.shop.common.constants.AppContext;
import com.lay.shop.greeston.manager.auth.UrlManager;


/**
 * 系统初始化时的servlet
 * @author Lay
 */
public class SystemInitServlet extends HttpServlet{
    
    private static final Logger log = LoggerFactory.getLogger(SystemInitServlet.class);
    
	private static final long serialVersionUID = 4724299124899039939L;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		initAppContext(config);
	}
	
	private void initAppContext(ServletConfig config) {
	    ApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());	    
	    
	    UrlManager urlManager = applicationContext.getBean("urlManager", UrlManager.class);	    
	    log.info("start to load controlled privilege urls");
	    // 获取所有受管控的URL
	    List<String> urlList = urlManager.findAllUrlList();
	    AppContext.getInstance().setControlledUrlSet(AppContext.initControlledUrlSet(urlList));
	    	   
	}
	
	
}
