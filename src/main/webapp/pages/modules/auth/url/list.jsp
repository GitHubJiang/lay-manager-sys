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
<div class="md-modal md-3d-slit" id="delete-modal">
	<form id="deleteForm" action="${staticbase}/auth/url/remove">	
		<input name="id" id="url-id" type="hidden" value="">	
		<div class="md-content" style="height:120px;">			
			<div>
				<p class="text-center">你确定删除么?</p>
				<p class="text-center">
					<a class="btn btn-default md-close">取消</a>
					<a href="javascript:void(0)" id="deleteBtn" class="btn btn-danger btn-lifecycle md-close">确认删除</a>
				</p>
			</div>   
		</div>
	</form>
</div>
<!-- Begin page -->
<div id="wrapper"> 
  
	<%@include file="/pages/commons/common-top.jsp" %>
  
	<jsp:include page="/pages/commons/common-left.jsp">
		<jsp:param name="pagecode" value="ACL_AUTH_URL" />
	</jsp:include>
  
	<!-- Start right content -->
	<div class="content-page">
		<!-- 面包屑 -->
	    <ol class="breadcrumb">
			<li><a href="javaScript:void(0);">权限管理</a></li>
			<li class="active"><a href="javaScript:void(0);">URL管理</a></li>
	    </ol>
	    
<!--主要内容开始部分 START-->     
<!-- ============================================================== --> 
<!-- Start Content here --> 
<!-- ============================================================== -->
<div class="content">
	<div class="row">
 		<div class="col-md-4">
        	<h3 class="marign-none">URL管理</h3>
        </div>
        <div class="col-md-8">
            <pri:add acl="ACL_AUTH_URL">
            	<div class="toolbar-btn-action">	 
            		<a class="btn btn-success btn-add" data-toggle="modal" data-target="#saveModal"><i class="fa fa-plus-circle"></i>新增</a>
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
							<form id="queryForm" method="post" role="form" action="${staticbase}/auth/url/list">
								<input type="hidden" value="${pagination.currentPage}"  name="page" class="startPage">
                                <input type="hidden" name="sort" value="${param.sort }"/>
								<div class="table-responsive">
									<table class="table table-hover" data-form="queryForm" data-sortable data-sortable-type="server" 
											data-page="${pagination.currentPage}" data-page-count="${pagination.totalPages}">
										<thead>
											<tr class="sort-header">
												<th data-sortable="false" width="45%">id</th>
												<th data-sortable="false" width="45%">受管控的URL</th>
												<th data-sortable="false" width="10%">操作</th>
											</tr>
											<tr class="filter-header">
												<th></th>												
												<th>
													<input type="text" name="q_string_url" value="${param.q_string_url }" class="form-control input-sm filter">	
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
													${item.url }
												</td>												
												<td>													
													<div class="btn-group btn-group-xs">
														<pri:update acl="ACL_AUTH_URL">
															<a type="button" class="btn btn-default btn-add" data-id="${item.id}" data-url="${item.url}" data-toggle="modal" data-target="#saveModal" href="javaScript:void(0);">
	                                                        	<i class="fa fa-edit"></i>编辑
	                                                       	</a>
                                                       	</pri:update>
                                                       	<pri:remove acl="ACL_AUTH_URL">
															<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
																<span class="caret"></span>
																<span class="sr-only">Toggle Dropdown</span>
															</button>
															<ul class="dropdown-menu" role="menu">
	                                                        	<li>
	                                                        		<a href="javaScript:void(0);" class="md-trigger state" data-modal="delete-modal" data-id="${item.id}" class="role-trash-o state"><i class="fa fa-trash-o"></i>删除</a>
	                                                        	</li>
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
<!-- 弹出窗体部分 START -->
<div id="saveModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-keyboard="true" data-backdrop="static">
    
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">新增/编辑URL</h4>
            </div>
            <div class="modal-body">
				<form class="form-horizontal" role="form" id="addForm" data-toggle="form-validator" method="post">
					<input id="urlId" name="id" type="hidden"/>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="label-url"><span class="glyphicon glyphicon-asterisk text-red-1"></span>URL</label>
						<div class="col-sm-10"> 
							<input type="text" id="label-url" placeholder="url" name="url" class="form-control" value="" required="required"/>
							<div class="help-block with-errors"></div>
						</div>
					</div>					
				</form>
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
<script src="${staticbase }/scripts/pages/auth/url/list.js?${version}"></script>

<script type="text/javascript">
	var maxPage="${param.pageCount}";
	var curPage="${param.currentPage}";
</script>
</body>
</html>