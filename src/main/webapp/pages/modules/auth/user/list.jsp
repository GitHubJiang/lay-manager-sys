<%@include file="/pages/commons/common.jsp" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>格林斯顿|权限管理</title>
<%@include file="/pages/commons/common-meta.jsp" %>
<%@include file="/pages/commons/common-css.jsp" %>
<%@include file="/pages/commons/common-compatible.jsp" %>
<%@include file="/pages/commons/common-icon.jsp" %>

</head>
<body class="fixed-left" locale="${locale }" onload="prettyPrint()">
<div class="md-modal md-3d-slit" id="delete-modal">
	<form id="deleteForm" action="${staticbase}/auth/user/del">	
		<input name="id" id="user-id" type="hidden" value="">	
		<div class="md-content" style="height:120px;">			
			<div>
				<p class="text-center">你确定删除此用户吗?</p>
				<p class="text-center">
					<a class="btn btn-default md-close">取消</a>
					<a href="javascript:void(0)" id="deleteBtn" class="btn btn-danger btn-lifecycle md-close">确认删除</a>
				</p>
			</div>   
		</div>
	</form>
</div>
<%@include file="/pages/commons/common-layer.jsp" %>
<!-- Begin page -->
<div id="wrapper"> 
  
	<%@include file="/pages/commons/common-top.jsp" %>
  
	<jsp:include page="/pages/commons/common-left.jsp">
		<jsp:param name="pagecode" value="ACL_AUTH_USER" />
	</jsp:include>
  
	<!-- Start right content -->
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
	<div class="row">
 		<div class="col-md-4">
        	<h3 class="marign-none">用户管理</h3>
        </div>
        <div class="col-md-8">
            <pri:add acl="ACL_AUTH_USER">
	            <div class="toolbar-btn-action">	                
	                <a href="${staticbase}/auth/user/toAdd" class="btn btn-success" ><i class="fa fa-plus-circle"></i>新增</a>
	            </div>
            </pri:add>
        </div>
	</div>

	<div class="row">
		<div class="col-md-12  portlets">
			<div class="widget">
				<div class="widget-content padding">
					<div class="row">
						<div class="col-md-12">
							<form id="queryForm" method="post" role="form" action="${staticbase}/auth/user/list">
								<input type="hidden" value="${pagination.currentPage}"  name="page" class="startPage">
                                <input type="hidden" name="sort" value="${param.sort }"/>
                   				<input type="hidden" name="sortDirection" value="${param.sortDirection }"/>
								<div class="table-responsive">
									<table class="table table-hover" data-form="queryForm" data-sortable data-sortable-type="server" 
											data-page="${pagination.currentPage}" data-page-count="${pagination.totalPages}">
										<thead>
											<tr class="sort-header">
												<th data-sortable="false" width="15%">登录名</th>
												<th data-sortable="false" width="15%">用户名</th>
												<th data-sortable="false" width="15%">员工工号</th>
												<th data-sortable="false" width="15%">邮箱</th>
												<th data-sortable="false" width="15%">启用禁用</th>
												<th data-sortable="false" width="10%">操作</th>
											</tr>
											<tr class="filter-header">												
												<th>
													<input type="text" name="loginName" value="${param.loginName }" class="form-control input-sm filter">			
												</th>
												<th>													
													<input type="text" name="userName" value="${param.userName }" class="form-control input-sm filter">	
												</th>
												<th>
													<input type="text" name="jobNumber" value="${param.jobNumber }" class="form-control input-sm filter">
												</th>
												<th>
													<input type="text" name="email" value="${param.email }" class="form-control input-sm filter">
												</th>
												<th>
													<select name="lifecycle" class="form-control input-sm filter">
														<option value="">-</option>
														<option value="1" <c:if test="${param.lifecycle == 1}">selected</c:if>>正常</option>
														<option value="2" <c:if test="${param.lifecycle == 2 }">selected</c:if>>已禁用</option>
													</select>
												</th>												
												<th>
													<button id="queryFormBtn" class="btn btn-primary btn-sm" name="" type="button"><i class="icon-search"></i> 搜索</button>
												</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pagination.items }" var="item">
											<tr>												
												<td>${item.loginName }</td>
												<td>
													${item.userName }
												</td>
												<td>
													${item.jobNumber }
												</td>
												<td>
													${item.email }
												</td>
												<td id="user_${item.id }">
													<c:if test="${item.lifecycle == 1 }">
														正常
														<a type="button" name="select" class="label label-success"  onclick="changeUserStatus('${item.id}','2')">禁用</a>
													</c:if>
													<c:if test="${item.lifecycle == 2 }">
														已禁用
														<a type="button" name="select" class="label label-success"  onclick="changeUserStatus('${item.id}','1')">启用</a>
													</c:if>
												</td>
												<td>
													
													<div class="btn-group btn-group-xs">
														<pri:update acl="ACL_AUTH_USER">
															<a type="button" class="btn btn-default" href="${staticbase}/auth/user/toAdd/?id=${item.id}">
	                                                        	<i class="fa fa-edit"></i>编辑
	                                                       	</a>
                                                       	</pri:update>
                                                       	<pri:remove acl="ACL_AUTH_USER">
															<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
																<span class="caret"></span>
																<span class="sr-only">Toggle Dropdown</span>
															</button>
															<ul class="dropdown-menu" role="menu">
	                                                        	<li><a href="javaScript:void(0);" class="md-trigger state" data-modal="delete-modal" data-id="${item.id}" class="role-trash-o state"><i class="fa fa-trash-o"></i>删除</a></li>
															</ul>
														</pri:remove>
													</div>
													
												</td>
											</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</form>
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
	<!-- End right content -->
</div>
<!-- End of page -->

<!-- the overlay modal element -->
<div class="md-overlay"></div>
<%@include file="/pages/commons/common-menu-template.jsp" %>
<!-- End of eoverlay modal --> 
<script>
	var resizefunc = [];
</script> 
 <%@include file="/pages/commons/common-script.jsp" %>

<!-- Page Specific JS Libraries --> 
<script src="${staticbase }/scripts/pages/auth/user/list.js?${version}"></script>

<script type="text/javascript">
	var maxPage="${param.pageCount}";
	var curPage="${param.currentPage}";
</script>
</body>
</html>