<%@include file="/modules/pages/commons/common.jsp" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="/modules/pages/commons/common-meta.jsp" %>
<%@include file="/modules/pages/commons/common-css.jsp" %>
<%@include file="/modules/pages/commons/common-script.jsp" %>
</head>
<body>
	<div class="layui-layout layui-layout-admin" id="layui_layout">
		<%@include file="/modules/pages/commons/common-menu.jsp" %>
	    <!-- 右侧主题内容 -->
	    <div class="layui-body" id="larry-body">
	        <div class="layui-tab" id="larry-tab" lay-filter="larryTab">
	             <%@include file="/modules/pages/commons/common-right.jsp" %>
	             <div class="layui-tab-content">
	                  <div class="layui-tab-item layui-show">
	                      <iframe class="larry-iframe" data-id='0' name="ifr_0" id='ifr0' src="/modules/html/personInfo.html"></iframe>
	                  </div>
	             </div>
	        </div>
	    </div>
	    <!-- footer -->
	    <%@include file="/modules/pages/commons/common-footer.jsp" %>
	    <!-- footer end -->
	    <!-- layui-layout-admin end -->
	</div>
	<%@include file="/modules/pages/commons/common-ext.jsp" %>
</body>
</html>