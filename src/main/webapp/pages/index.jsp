<%@include file="/pages/commons/common.jsp" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>后台管理系统</title>
    <%@include file="/pages/commons/common-meta.jsp" %>
	<%@include file="/pages/commons/common-css.jsp" %>
	<%@include file="/pages/commons/common-script.jsp" %>
</head>
<body>
    <div class="layui-layout layui-layout-admin">
       <!--头部-->
       <%@include file="/pages/commons/common-head.jsp" %>
       <!-- 侧栏 -->
       <%@include file="/pages/commons/common-left.jsp" %>
       <!--内容-->
       <div class="layui-body layui-tab-content" id="lay-body" style="bottom: 0px;">
           <p>
	        	<blockquote class="layui-elem-quote">
	           		 欢迎使用库存管理系统
	            </blockquote>
    		</p>
       </div>
       <%@include file="/pages/commons/common-footer.jsp" %>
    </div>
</body>
</html>
