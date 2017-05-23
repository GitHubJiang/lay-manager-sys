package com.lay.shop.pacs.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.lay.shop.pacs.util.ProfileConfigUtil;

/**
 * 调用profileUtil的初始化方法
 * @author Justin Hu
 *
 */
public class ProfileInitListener implements ServletContextListener {
	
	private static final String PROFILE_KEY="spring.profiles.active";

	private void initProfileUtil(ServletContext servletContext){
		String profileName=servletContext.getInitParameter(PROFILE_KEY);
		ProfileConfigUtil.setMode(profileName);
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		initProfileUtil(sce.getServletContext());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
