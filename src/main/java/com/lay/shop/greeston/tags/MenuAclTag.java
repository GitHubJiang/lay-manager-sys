package com.lay.shop.greeston.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.lay.shop.common.constants.AuthConstants;
import com.lay.shop.common.utils.SpringWebUtil;
import com.lay.shop.common.utils.Validator;
import com.lay.shop.greeston.command.auth.MenuCommand;

/**
 * 用于菜单标签
 * @Company:baozun
 * @Author:jiuzhou.hu@baozun.cn
 * @Since:2016年4月14日 下午7:19:51
 * @Copyright:Copyright (c) 2016
 * @Version:1.0
 */
public class MenuAclTag extends BaseTag {

	/**
	 * @Title serialVersionUID
	 * @type long
	 * @date 2016年4月14日 下午5:48:10
	 * 含义 TODO
	 */
	private static final long serialVersionUID = 1781264820208854415L;
	
	private String pageCode;

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = this.pageContext.getOut();
		HttpSession session = pageContext.getSession();
		@SuppressWarnings("unchecked")
		List<MenuCommand> miList = (List<MenuCommand>) session.getAttribute(AuthConstants.MENU_ITEMS);
		StringBuffer sf = new StringBuffer("<ul><li><a href=\"/index.html\"><i class=\"icon-home\"></i><span>仪表盘</span></a></li>");
		if(miList != null && miList.size() > 0) {
			randerMenuString(sf, miList);
		}
		sf.append("</ul>");
		try {
			out.print(selectItem(sf.toString()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	
	private String selectItem(String html) {
		Document doc = Jsoup.parseBodyFragment(html);
		Element body = doc.body();
		Elements active = body.select(".active");
		if(active!=null && active.size() > 0) {
			Elements elements = active.parents();
			Element superSelect = elements.get(elements.size() - 4);
			Elements downs = superSelect.select(".fa-angle-down");
			if(downs.size() > 0) {
				downs.first().parent().parent().addClass("subdrop");
				downs.removeClass("fa-angle-down").addClass("fa-angle-up");
			}
			superSelect.select("ul").attr("style","display: block;");
		}
		return body.html();
	}
	
	private void randerMenuString(StringBuffer sf, List<MenuCommand> miList) {
		for(MenuCommand item : miList) {
			sf.append("<li><a href=\"");
			if(Validator.isNotNullOrEmpty(item.getUrl()) && !Validator.equalsIgnoreCase(item.getUrl(), "null")){
				sf.append(getContextPath()).append(item.getUrl());
			} else {
				sf.append("javascript:void(0);");
			}
			sf.append("\"");
			if(Validator.isNotNullOrEmpty(getPageCode()) && Validator.equals(item.getAcl(), getPageCode())) {
				sf.append("class=\"active\"");
			}
			sf.append(">");
			sf.append("<i class=\"");
			if(item.getChildList() !=null && item.getChildList().size() > 0){
				sf.append("icon-feather");
			}
			sf.append("\"></i>");
			sf.append("<span>").append(item.getName()).append("</span>");
			if(item.getChildList() !=null && item.getChildList().size() > 0){
				sf.append("<span class=\"pull-right\"><i class=\"fa fa-angle-down\"></i></span>");
			}
			sf.append("</a>");
			if(item.getChildList() !=null && item.getChildList().size() > 0){
				sf.append("<ul>");
				randerMenuString(sf, item.getChildList());
				sf.append("</ul>");
			}
			sf.append("</li>");
		}
	}
	
	/**
	 * 获得项目名称
	 * @author jiuzhou.hu@baozun.cn
	 * @date 2016年2月26日 下午2:35:52
	 * @return
	 */
	private String getContextPath() {
		HttpServletRequest request = SpringWebUtil.getRequest();
		String contextPath = "";
		if(request != null) {
			contextPath = request.getContextPath();
		}
		return contextPath;
	}
	
	@Override
    public void release() {
        super.release();
    }

	/**
	 * pageCode的获取.
	 * @return String
	 */
	public String getPageCode() {
		return pageCode;
	}

	/**
	 * 设定pageCode的值.
	 * @param String
	 */
	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}
}
