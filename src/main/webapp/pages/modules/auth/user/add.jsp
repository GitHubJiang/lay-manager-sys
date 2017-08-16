<%@include file="/pages/commons/common.jsp" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>格林斯顿 | 权限管理</title>
<%@include file="/pages/commons/common-meta.jsp" %>
<%@include file="/pages/commons/common-css.jsp" %>
<%@include file="/pages/commons/common-compatible.jsp" %>
<%@include file="/pages/commons/common-icon.jsp" %>

</head>
<body class="fixed-left" locale="${locale }" onload="prettyPrint()">
<%@include file="/pages/commons/common-layer.jsp" %>
<!-- Begin page -->
<div id="wrapper"> 
  
	<%@include file="/pages/commons/common-top.jsp" %>
  
	<jsp:include page="/pages/commons/common-left.jsp">
		<jsp:param name="pagecode" value="ACL_AUTH_USER" />
	</jsp:include>
  
	<div class="content-page">
		<!-- 面包屑 -->
	    <ol class="breadcrumb">
			<li><a href="javaScript:void(0);">权限管理</a></li>
			<li class="active"><a href="javaScript:void(0);">用户管理</a></li>
	    </ol>
	    
<!--主要内容开始部分 START--> 
   
<!-- ============================================================== --> 
<!-- Start Content here --> 
<!-- ============================================================== -->
		<div class="content"> 
	     <!--主要内容开始部分 START-->
				<h3>新增/编辑用户</h3>
				<div class="row">
				<div class="col-md-12 portlets">
					<div class="widget padding">
						<div class="widget-content padding">
							<form action="${staticbase}/auth/user/add" class="form-horizontal" method="post" id="userInfoForm" role="form" data-toggle="form-validator">
								<input id="user_id" type="hidden" name="id" value="${id}" >		
								<input id="ouType_id" type="hidden" name="ouType" value="" >					
								<div class="form-group">
									<label class="col-sm-2 control-label" for="loginName"><span class="glyphicon glyphicon-asterisk text-red-1"></span>登录名</label>
									<div class="col-sm-4">
										<input placeholder="英文字母，数字或_.-组成，必须以英文字母，数字开头" data-custom="uniqueLoginName loginName" maxlength="20" id="loginName" name="loginName" type="text" class="form-control" required/>
										<div class="help-block with-errors"></div>
									</div>									
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="userName"><span class="glyphicon glyphicon-asterisk text-red-1"></span>用户姓名</label>
									<div class="col-sm-4">
										<input placeholder="用户姓名" maxlength="20" id="userName" name="userName" type="text" class="form-control" required/>
										<div class="help-block with-errors"></div>
									</div>									
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="jobNumber">员工工号</label>
									<div class="col-sm-4">
										<input placeholder="员工工号" maxlength="20" id="jobNumber" name="jobNumber" type="text" class="form-control"/>
										<div class="help-block with-errors"></div>
									</div>									
								</div>
								<div class="form-group" id="password-hid">
									<label class="col-sm-2 control-label" for="password"><span class="glyphicon glyphicon-asterisk text-red-1"></span>密码</label>
									<div class="col-sm-4">
										<input id="password" name="password" data-custom="Password" type="password" placeholder="英文字母，数字或_.-组成，必须以英文字母，数字开头" class="form-control" required/>
										<div class="help-block with-errors"></div>
									</div>									
								</div>
								<div class="form-group" id="repassword-hid">
									<label class="col-sm-2 control-label" for="repassword"><span class="glyphicon glyphicon-asterisk text-red-1"></span>确认密码</label>
									<div class="col-sm-4">
										<input maxlength="10" data-custom="rePassword" id="repassword" type="password" class="form-control" required/>
										<div class="help-block with-errors"></div>
									</div>									
								</div>								
								<div class="form-group">
									<label class="col-sm-2 control-label" for="email"><span class="glyphicon glyphicon-asterisk text-red-1"></span>邮箱</label>
									<div class="col-sm-4">
										<input placeholder="邮箱" maxlength="50" data-custom="email" id="email" name="email" type="text" class="form-control" required/>
										<div class="help-block with-errors"></div>
									</div>									
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="mobile">手机号</label>
									<div class="col-sm-4">
										<input placeholder="手机号" maxlength="15" id="mobile" name="mobile" type="text" class="form-control"/>
										<div class="help-block with-errors"></div>
									</div>									
								</div>									   
							</form>
							<div class="row">
								<div class="col-md-12 border-top-solid savepadding-rig-style mg-top10 mg-bottom20">
									<button class="btn  btn-primary pull-right role-save" id="userInfoSubmit" type="submit"><i class="fa fa-save"></i>保存</button>
									<a class="btn btn-default" href="${staticbase}/auth/user/list"><i class="icon-left-open"></i>返回</a>
								</div>
							</div>
							<br/>
							<div class="form-group">
								<div class="col-sm-12">
									<div class="col-md-4">
							        	<h4 class="marign-none">配置角色</h4>
							        </div>
									<div class="col-md-8">					            
							            <div class="toolbar-btn-action">	                
							                <a href="javaScript:void(0);" id="roleManager" data-toggle="modal" data-target="#roleModal" class="btn btn-success disabled" ><i class="fa fa-plus-circle"></i>配置角色</a>
							            </div>
							        </div>
								</div>
								<div class="col-sm-12">									
									<table class="table table-hover th-bold-none" data-sortable-initialized="true">
										<thead>
											<tr>
												<th  width="25%">所属组织类型</th>
												<th  width="15%">组织名称</th>
												<th  width="15%">角色</th>										
												<th  width="15%">操作</th>
											</tr>
										</thead>
										<tbody id="tbody">
										</tbody>
									</table>
								</div>					
							</div>
		              	</div>
	            	</div>
				</div>
			</div>
		</div>
<!-- ============================================================== --> 
<!-- End content here --> 
<!-- ============================================================== -->    
<!--主要内容开始部分 END-->

 		
		<%@include file="/pages/commons/common-footer.jsp" %>
	</div>
</div>
<!-- End of page -->

<!-- the overlay modal element -->
<div class="md-overlay"></div>
<%@include file="/pages/commons/common-menu-template.jsp" %>
<!-- End of eoverlay modal --> 

<!-- 弹出窗体部分 START -->
<div id="roleModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-keyboard="true" data-backdrop="static">
    
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">配置角色</h4>
            </div>
            <div class="modal-body">
				<form class="form-horizontal" role="form" id="roleForm" data-toggle="form-validator" method="post">
					<input id="userId" name="userId" type="hidden" value="${id}"/>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="label-ouType"><span class="glyphicon glyphicon-asterisk text-red-1"></span>所属组织类型</label>
						<div class="col-sm-10"> 
							<select id="label-ouType" class='form-control input-sm filter' name='ouType' required>
							</select>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="label-ouId"><span class="glyphicon glyphicon-asterisk text-red-1"></span>组织名称</label>
						<div class="col-sm-10"> 
							<select id="label-ouId" class='modal-select selectPicker form-control input-sm filter' name='auId' required>
							</select>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="label-role"><span class="glyphicon glyphicon-asterisk text-red-1"></span>角色</label>
						<div class="col-sm-10"> 
							<select id="label-role" class='modal-select selectPicker form-control input-sm filter' name='roleId' required>
							</select>
							<div class="help-block with-errors"></div>
						</div>
					</div>
				</form>
			</div>
			<div class="form-group">
				<div class="col-sm-12">
					<h4 class="marign-none">勾选权限</h4>
				</div>
				<div class="col-sm-12">									
					<table class="table table-hover th-bold-none" data-sortable-initialized="true">
						<thead>
							<tr>
								<th  width="25%">URL</th>
								<th  width="15%">查看</th>
								<th  width="15%">新增</th>										
								<th  width="15%">修改</th>
								<th  width="15%">删除</th>
							</tr>
						</thead>
						<tbody id="aclTBody">
						</tbody>
					</table>
				</div>					
			</div>
			<div class="modal-footer">
				<div class="form-group">
					<div class="pull-right">						
						<button class="btn btn-default" id="closeBtn" data-dismiss="modal" type="button"><i class="fa fa-close"></i> 关闭</button>
						<button class="btn btn-primary " id="submitBtn" type="button"><i class="fa fa-save"></i> 保存</button>
					</div>
				</div>
			</div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!-- 弹出窗体部分 END -->
<script>
	var resizefunc = [];
</script> 
 <%@include file="/pages/commons/common-script.jsp" %>

<!-- Page Specific JS Libraries --> 
<script src="${staticbase }/scripts/pages/auth/user/add.js?${version}"></script>

<script type="text/javascript">
	var maxPage="${param.pageCount}";
	var curPage="${param.currentPage}";
</script>
</body>
</html>