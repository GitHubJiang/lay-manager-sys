<%@include file="/pages/commons/common.jsp" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<title>登录 | 格林斯顿 </title>   
	<%@include file="/pages/commons/common-meta.jsp" %>
	<%@include file="/pages/commons/common-css.jsp" %>
	<%@include file="/pages/commons/common-compatible.jsp" %>
	<%@include file="/pages/commons/common-icon.jsp" %>
</head>
<body class="fixed-left login-page">
	<%@include file="/pages/commons/common-layer.jsp" %>
	<!-- Begin page -->
	<div class="container">
		<div class="full-content-center">
<!-- 			<p class="text-center"><a href="#"><img src="assets/img/login-logo.png" alt="Logo"></a></p> -->
			<div class="login-wrap animated flipInX">
				<div class="login-block">
					<img src="images/users/default-user.png" class="img-circle not-logged-avatar">
					<form role="form" method="post" action="<%=request.getContextPath()%>/j_spring_security_check">
						<div class="form-group login-input">
						<i class="fa fa-user overlay"></i>
						<input type="text" name="loginName" class="form-control text-input"  placeholder="用户名">
						</div>
						<div class="form-group login-input">
						<i class="fa fa-key overlay"></i>
						<input type="password" name="password" class="form-control text-input" placeholder="密码">
						</div>
						
						<div class="row">
							<div class="col-sm-12">
							<button type="submit" class="btn btn-success btn-block">LOGIN</button>
							</div>
						</div>
					</form>
				</div>
			</div>
			
		</div>
	</div>
	<!-- the overlay modal element -->
	<div class="md-overlay"></div>
	<!-- End of eoverlay modal -->
	<script>
		var resizefunc = [];
	</script>
	<%@include file="/pages/commons/common-script.jsp" %>
	</body>
</html>
