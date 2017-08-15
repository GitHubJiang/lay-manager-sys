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
	<div class="col-md-9"></div>
	<div class="toolbar-btn-action">
		<div class="btn-toolbar" role="toobar">
			<div class="btn-group">
			<pri:add acl="ACL_AUTH_ROLE"><a class="btn btn-success btn-add" data-toggle="modal" data-target="#myModalAdd" ><i class="fa fa-plus-circle"></i> 新增</a></pri:add>
			</div>
		</div>
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
												<th data-sort-name="id">ID</th>
												<th data-sort-name="ouType" width="25%">角色名称</th>
												<th data-sort-name="code" width="25%">所属组织类型</th>
												<th data-sort-name="name" width="25%">角色状态</th>												
												<th data-sortable="false" width="15%">操作</th>
											</tr>
											<tr class="filter-header">
												<th></th>
												<th>
													<input type="text" value="${param.q_sl_name }" name="q_sl_name" class="form-control input-sm filter">
												</th>
												<th>
													<select id="q_int_ouType" name="q_int_ouType" class="form-control" style="width:100px">
														<option value="0">——</option>
														<option value="1" <c:if test="${param.q_int_ouType == 1}">selected</c:if>>系统</option>
														<option value="2" <c:if test="${param.q_int_ouType == 2}">selected</c:if>>品牌</option>
														<option value="3" <c:if test="${param.q_int_ouType == 3}">selected</c:if>>渠道</option>
													</select>
												</th>
												<th>
													<select id="q_int_lifecycle" name="q_int_lifecycle" class="form-control" style="width:100px">
														<option value="0">——</option>
														<option value="1" <c:if test="${param.q_int_lifecycle == 1}">selected</c:if>>启用</option>
														<option value="2" <c:if test="${param.q_int_lifecycle == 2}">selected</c:if>>禁用</option>
													</select>
												</th>
												<th>
													<button id="queryFormBtn" class="btn btn-primary btn-sm" name="" type="button"><i class="icon-search"></i> <spring:message code="label.operator.search"/></button>
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
													<c:choose>
														<c:when test="${item.ouType == 1}">
															系统
														</c:when>
														<c:when test="${item.ouType == 2 }">
															品牌
														</c:when>
														<c:when test="${item.ouType == 3 }">
															渠道
														</c:when>
														<c:otherwise>
															——
														</c:otherwise>
													</c:choose>
												</td>
												<td name="lifecycle${item.id}">
													<c:choose>
														<c:when test="${item.lifecycle == 1 }">
															<spring:message code="label.off"/>
															<a type="button" name="select" class="label label-warning"  onclick="isStatusChange('${item.id}','${item.lifecycle}')">
																<spring:message code="label.store.on"/>
															</a>
														</c:when>
														<c:when test="${item.lifecycle == 2 }">
															<spring:message code="label.on"/>
															<a type="button" name="select" class="label label-success"  onclick="isStatusChange('${item.id}','${item.lifecycle}')">
																<spring:message code="label.store.off"/>
															</a>
														</c:when>
													</c:choose>
												</td>
												<td>
													<div class="btn-group btn-group-xs">
														<pri:view acl="ACL_AUTH_ROLE">
															<a type="button" class="btn btn-default btn-add" data-id="${item.id}" data-toggle="modal" data-target="#myModalAdd"
															 href="javaScript:void(0)"  onclick="findRoleByIdModify('${item.id}')"> <i class="fa fa-edit"></i><spring:message code="label.operator.edit"/>
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
																<li><a data-toggle="modal" data-target="#myModalDelete" onclick="affirmDelete('${item.id}')"><i class="fa fa-trash-o"></i><spring:message code="label.operator.delete"/></a></li>
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
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font>角色名称</label>
						<div class="col-sm-10">
							<input type="text"  data-custom="uniqueName" placeholder="角色名称" id="nameAdd" name="name" class="form-control"  minlength="2" maxlength="8" value=""   required/>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font>所属组织类型</label>
						<div class="col-sm-10">
							<select class="form-control" aria-labelledby="birth-label" id="ouTypeAdd"  runat="server" name="ouType" >
								<option value=''>-</option>	
								<option value='1'>系统</option>
								<option value='2'>品牌</option>
								<option value='3'>渠道</option>
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
					<button type="button" class="btn btn-default"  id="closeBtn" data-dismiss="modal" >< <spring:message code="label.operator.return"/></button>
				</div>
				<div class="col-sm-9">
					<button class="btn  btn-primary pull-right role-save" id="subBtn"  type="button" ><i class="fa fa-floppy-o"></i><spring:message code="label.operator.save"/></button>
				</div>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<div id="myModalDelete" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">确定要删除该角色?</h4>
				<form class="form-horizontal" role="form" data-toggle="form-validator"  id="deleteForm" novalidate="true" method="post" action="${staticbase}/auth/role/delete">
					<input type="hidden"  name="id" id="idDelete" value="" /> 				
					<div class="form-group">
						<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-power-off"></i><spring:message code="tip.user.org.change.no"/></button>
						<button class="btn  btn-primary pull-right role-save" data-dismiss="modal" onclick="deleteFormSubmit()"><i class="fa fa-check"></i><spring:message code="tip.user.org.change.yes"/></button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

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
<script src="${staticbase }/scripts/pages/auth/roleList.js?${version}"></script>


<script type="text/javascript">
	var maxPage="${param.pageCount}";
	var curPage="${param.currentPage}";
</script>
</body>
</html>