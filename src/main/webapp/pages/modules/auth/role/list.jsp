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
	<form id="deleteForm" action="${staticbase}/auth/role/del">	
		<input name="id" id="role-id" type="hidden" value="">	
		<div class="md-content" style="height:120px;">			
			<div>
				<p class="text-center">你确定删除此角色吗?</p>
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
		<jsp:param name="pagecode" value="ACL_AUTH_ROLE" />
	</jsp:include>
	
	<!-- Start right content -->
	<div class="content-page">
		<!-- 面包屑 -->
	    <ol class="breadcrumb">
			<li><a href="#">权限管理</a></li>
			<li class="active"><a href="#">角色管理</a></li>
	    </ol>
	    
<!--主要内容开始部分 START--> 
   
<!-- ============================================================== --> 
<!-- Start Content here --> 
<!-- ============================================================== -->
<div class="content">
	<div class="col-md-4">
		<h3 class="marign-none">角色管理</h3>
	</div>
	<div class="col-md-8">
		<div class="toolbar-btn-action">
			<pri:add acl="ACL_AUTH_ROLE"><a class="btn btn-success btn-add" data-toggle="modal" data-target="#myModalAdd" ><i class="fa fa-plus-circle"></i> 新增</a></pri:add>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12  portlets">
			<div class="widget">
				<div class="widget-content padding">
					<div class="row">
						<div class="col-md-12">
							<form id="queryForm" method="post" role="form" action="${staticbase}/auth/role/list">
								<input type="hidden" value="${pagination.currentPage }" name="page" class="startPage">
								<input type="hidden" name="sort" value="${param.sort }" /> 
								<input type="hidden" name="sortDirection" value="${param.sortDirection }" />
								<div class="table-responsive">
									<table class="table table-hover" data-form="queryForm"
										data-sortable="data-sortable" data-sortable-type="server"
										data-page="${pagination.currentPage}" data-page-count="${pagination.totalPages}">
										<thead>
											<tr class="sort-header">
												<th data-sortable="false">ID</th>
												<th data-sortable="false" width="25%">角色名称</th>
												<th data-sortable="false" width="25%">所属组织类型</th>
												<th data-sortable="false" width="25%">角色状态</th>												
												<th data-sortable="false" width="15%">操作</th>
											</tr>
											<tr class="filter-header">
												<th></th>
												<th>
													<input type="text" value="${param.name }" name="name" class="form-control input-sm filter">
												</th>
												<th>
													<select id="ouTypeId" name="ouTypeId" class="form-control input-sm filter">														
													</select>
												</th>
												<th>
													<select name="lifecycle" class="form-control input-sm filter" >
														<option ></option>
														<option value="1" <c:if test="${param.lifecycle == 1}">selected</c:if>>正常</option>
														<option value="2" <c:if test="${param.lifecycle == 2}">selected</c:if>>已禁用</option>
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
												<td>${item.id }</td>
												<td>
													${item.name }
												</td>
												<td>
													${item.ouTypeName }
												</td>
												<td>
													<c:choose>
														<c:when test="${item.lifecycle == 1 }">
															正常
														</c:when>
														<c:when test="${item.lifecycle == 2 }">
															已禁用
														</c:when>
													</c:choose>
												</td>
												<td>
													<div class="btn-group btn-group-xs">
														<pri:view acl="ACL_AUTH_ROLE">
															<a type="button" class="btn btn-default btn-add" data-id="${item.id}" data-toggle="modal" data-target="#myModalAdd"
															 href="javaScript:void(0)" > <i class="fa fa-edit"></i>编辑
															</a>
														</pri:view>
														<pri:or acl="ACL_AUTH_ROLE">
															<button type="button"
																class="btn btn-default dropdown-toggle"
																data-toggle="dropdown">
																<span class="caret"></span> <span class="sr-only">Toggle Dropdown</span>
															</button>
														</pri:or>
														<ul class="dropdown-menu" role="menu">
															<pri:remove acl="ACL_AUTH_ROLE">																
																<li><a href="javaScript:void(0);" class="md-trigger state" data-modal="delete-modal" data-id="${item.id}" class="role-trash-o state"><i class="fa fa-trash-o"></i>删除</a></li>
															</pri:remove>
														</ul>
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

<!-- 弹出窗体部分 START -->
<!-- /.modal -->
<div id="myModalAdd" class="modal fade add-model-form in" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-keyboard="true"  data-toggle="form-validator" data-backdrop="static">
	<div class="modal-dialog modal-lg"  style="height:90%">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">角色</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" role="form" data-toggle="form-validator" id="addForm" method="post" action ="${staticbase}/auth/role/add" >
					<input type="hidden" name="id" id="idAdd" class="form-control" value="" /> 
					<input type="hidden" name="rolePriList"  /> 
					<div class="form-group">
						<label class="col-sm-2 control-label" ><span class="glyphicon glyphicon-asterisk text-red-1"></span>角色名称</label>
						<div class="col-sm-10">
							<input type="text"  data-custom="uniqueCode" placeholder="角色名称" id="nameAdd" name="name" class="form-control" value=""   required/>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="ouTypeAdd" ><span class="glyphicon glyphicon-asterisk text-red-1"></span>所属组织类型</label>
						<div class="col-sm-10">
							<select class="form-control" id="ouTypeAdd" name="ouTypeId" required>
							</select>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<h4>勾选权限</h4>
						</div>
						<div class="col-sm-12">									
							<table data-sortable class="table table-hover th-bold-none" id="pri" data-sortable-initialized="true">
								<thead>
									<tr>
										<th  width="25%">权限名称</th>
										<th  width="15%">查看</th>
										<th  width="15%">新增</th>										
										<th  width="15%">修改</th>
										<th  width="15%">删除</th>
										<th  width="15%">所有</th>
									</tr>
								</thead>
								<tbody id="tbody">
								</tbody>
							</table>
						</div>					
					</div>			
				</form>
			</div>
			<div class="modal-footer">
				<div class="col-sm-3">
					<button type="button" class="btn btn-default"  id="closeBtn" data-dismiss="modal" >返回</button>
				</div>
				<div class="col-sm-9">
					<button class="btn  btn-primary pull-right role-save" id="subBtn"  type="button" ><i class="fa fa-floppy-o"></i>保存</button>
				</div>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<!-- 弹出窗体部分 END -->

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
<script src="${staticbase }/scripts/pages/auth/role/list.js?${version}"></script>


<script type="text/javascript">
	var maxPage="${param.pageCount}";
	var curPage="${param.currentPage}";
</script>
</body>
</html>