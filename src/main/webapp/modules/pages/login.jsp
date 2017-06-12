<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@include file="/modules/pages/commons/common-meta.jsp" %>
	<%@include file="/modules/pages/commons/common-css.jsp" %>
	<link rel="stylesheet" type="text/css" href="${staticbase }/modules/css/login.css" media="all">
</head>
<body>
	<div class="larry-canvas" id="canvas"></div>
<div class="layui-layout layui-layout-login">
	<h1>
		 <strong>LayCMS管理系统后台</strong>
		 <em>Management System</em>
	</h1>
	<form id="login"  method="post" action="<%=request.getContextPath()%>/j_spring_security_check">
		<div class="layui-user-icon larry-login">
			 <input type="text" name="loginName" placeholder="账号" class="login_txtbx"/>
		</div>
		<div class="layui-pwd-icon larry-login">
			 <input type="password" name="password" placeholder="密码" class="login_txtbx"/>
		</div>
	    <div class="layui-val-icon larry-login">
	    	<div class="layui-code-box">
	    		<input type="text" id="code" name="code" placeholder="验证码" maxlength="4" class="login_txtbx">
	            <img src="modules/images/verifyimg.png" alt="" class="verifyImg" id="verifyImg" onclick="javascript:this.src='xxx'+Math.random();">
	    	</div>
	    </div>
	    <div class="layui-submit larry-login">
	    	<input type="button" value="立即登陆" class="submit_btn"/>
	    </div>
    </form>
    <div class="layui-login-text">
    	<p>© 2016-2017 Larry 版权所有</p>
        <p>鄂ICP <a href="http://www.larrycms.com" title="">larrycms.com</a></p>
    </div>
</div>
<script type="text/javascript" src="${staticbase }/common/layui/layui.js"></script>
<script type="text/javascript" src="${staticbase }/common/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${staticbase }/common/jsplugin/jparticle.jquery.js"></script>
<script type="text/javascript" src="${staticbase }/modules/js/login.js"></script>
</body>
</html>