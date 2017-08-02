package com.lay.shop.greeston.tags;

import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;

import org.springframework.security.core.context.SecurityContextHolder;

import com.lay.shop.common.constants.AuthConstants;
import com.lay.shop.greeston.command.auth.UserDetailsCommand;



/**
 * 用于spring security的标签
 * @author lihui
 *
 */
public class AndPrivilegeTag extends BaseTag{
	
	private static final long serialVersionUID = 3325028303555297674L;
	
	/** 查看 */
    private String view;
   	/** 新增 */
    private String add;
    /** 修改 */
    private String update;
    /** 删除 */
    private String remove;
    /** 执行 */
    private String operate;
    /** 配置 */
    private String config;

	@Override
	public int doStartTag() throws JspException {
		Boolean status=true;
		UserDetailsCommand udc=(UserDetailsCommand) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String, List<String>> priMap=udc.getCommand().getPriFunMap();
		
		if(priMap!=null && !priMap.isEmpty()){
			
			boolean v=check(view,AuthConstants.P_FUNCTION_TYPE_VIEW,priMap);
			boolean a=check(add,AuthConstants.P_FUNCTION_TYPE_SAVE,priMap);
			boolean u=check(update,AuthConstants.P_FUNCTION_TYPE_UPDATE,priMap);
			boolean r=check(remove,AuthConstants.P_FUNCTION_TYPE_DELETE,priMap);
			boolean o=check(operate,AuthConstants.P_FUNCTION_TYPE_OPERATE,priMap);
			boolean c=check(config,AuthConstants.P_FUNCTION_TYPE_CONFIG,priMap);
			if(!v||!a||!u||!r||!o||!c){
				return SKIP_BODY;
			}
			
		}else{
			return SKIP_BODY;
		}
		if(status){
			return EVAL_BODY_INCLUDE;
		}else{
			return SKIP_BODY;
		}
		
		
	}
	
	/**
	 * 验证用户是否有权限
	 * @param property
	 * @param type
	 * @param priMap
	 * @param status
	 */
	public boolean check(String property,String type,Map<String, List<String>> priMap) {
		boolean status=true;
		if(property!=null&&!"".equals(property)){
			String[] acls=property.split(",");
			for (String acl : acls) {
				//用户当前权限acl列表
				List<String> list=priMap.get(acl);
				if(list!=null){
					if(!list.contains(type)){
						return false;
					}
				}else{
					return false;
				}
			}
		}
		return status;
	}
	
	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		
		return EVAL_PAGE;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getRemove() {
		return remove;
	}

	public void setRemove(String remove) {
		this.remove = remove;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}

	
}
