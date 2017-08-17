<%@include file="/pages/commons/common.jsp" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>格林斯顿|库存管理</title>
<%@include file="/pages/commons/common-meta.jsp" %>
<%@include file="/pages/commons/common-css.jsp" %>
<%@include file="/pages/commons/common-compatible.jsp" %>
<%@include file="/pages/commons/common-icon.jsp" %>

</head>
<body class="fixed-left" locale="${locale }" onload="prettyPrint()">
<!-- /.modal-dialog -->
<!-- /.modal -->
<div id="myModalBatchUp" class="modal fade modal-import" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-keyboard="true" data-backdrop="static">
  <div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-body">
				<div class="col-md-12">					
					<h4 class="marign-none form-title">库存导入</h4>
				</div>
				 <div class="widget-content padding form-horizontal">
					<form class="form-horizontal" role="form" data-toggle="form-validator" id="uploadForm" novalidate="true" action="${staticbase}/inv/inventory/importInv" method="post" enctype="multipart/form-data">
	                	<div class="bck-white-all overflow">
	                		<div class="content-all-padding">
	                			<div class="form-group">
			                        <label class="col-sm-2 control-label" for="invFile"><span class="glyphicon glyphicon-asterisk text-red-1"></span> <span> 上传文件</span></label>
			                        <div class="col-sm-6">
			                          <input type="file" id="invFile" class="file-loading" type="file" multiple name="invFile"/>			                          
			                          <div class="help-block with-errors"></div>
			                        </div>
			                        <div class="col-sm-4">
			                          <a class="btn btn-default btn-primary" href="${staticbase}/inv/inventory/invImportTemplate" target="_blank">模板下载</a>
			                        </div>
			                    </div>
			                     
								<div class="form-group">
									<div class="pull-right">
										<button class="btn btn-primary btn-upload" type="button"><i class="fa fa-upload"></i> 上传</button>
										&nbsp;&nbsp;
										<button class="btn btn-primary closeBtn" type="button" data-dismiss="modal"><i class="fa fa-close"></i> 关闭</button>
									</div>
								</div>
							</div>
						</div>	
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- /.modal -->
<!-- 弹出窗体部分 END -->


<%@include file="/pages/commons/common-layer.jsp" %>
<!-- Begin page -->
<div id="wrapper"> 
  
	<%@include file="/pages/commons/common-top.jsp" %>
  
	<jsp:include page="/pages/commons/common-left.jsp">
		<jsp:param name="pagecode" value="ACL_INV_FULL" />
	</jsp:include>
  
	<!-- Start right content -->
	<div class="content-page">
		<!-- 面包屑 -->
	    <ol class="breadcrumb">
			<li><a href="#">库存管理</a></li>
			<li class="active">商品库存</a></li>
	    </ol>
	    
<!--主要内容开始部分 START--> 
   
<!-- ============================================================== --> 
<!-- Start Content here --> 
<!-- ============================================================== -->
<div class="content">
	<div class="col-md-4">
		<h3 class="marign-none">商品库存</h3>
	</div>
	<div class="col-md-8">
		<div class="toolbar-btn-action">	 
			<pri:add acl="ACL_INV_FULL"><a href="#" data-toggle="modal" data-target=".modal-import" class="btn btn-success btn-import"><i class="fa fa-plus-circle"></i>全量库存导入</a></pri:add>
	   		<pri:view acl="ACL_INV_FULL"><a class="btn btn-success" id="exportStoreButton btn-export" onclick="exportInventory()">导出库存</a></pri:view>
	   	</div>
	</div>
	<form action="${staticbase}/inv/inventory/exportInv" id="exportInvForm" method="post">
		<input type="hidden" value="${param.skuCode }" name="skuCode" >
        <input type="hidden" value="${param.brandCode }" name="brandCode">
	</form>

	<div class="row">
		<div class="col-md-12  portlets">
			<div class="widget">
				<div class="widget-content padding">
					<div class="row">
						<div class="col-md-12">
							<form id="queryForm" method="post" role="form" action="${staticbase}/inv/inventory/list">
								<input type="hidden" value="${pagination.currentPage}"  name="page" class="startPage">
                                <input type="hidden" name="sort" value="${param.sort }"/>
                   				<input type="hidden" name="sortDirection" value="${param.sortDirection }"/>
								<div class="table-responsive">
									<table id="dataTable" class="table table-hover" data-form="queryForm" data-sortable data-sortable-type="server" 
											data-page="${pagination.currentPage}" data-page-count="${pagination.totalPages}">
										<thead>
											<tr class="sort-header">
												<th data-sort-name="false">品牌编码</th>
                                                <th data-sort-name="false">商品编码</th>
                                                <th data-sort-name="false">商品库存</th>
                                                <th>操作</th>
											</tr>
											<tr class="filter-header">
												<th>
													<input type="text" value="${param.brandCode }" name="brandCode" class="form-control input-sm filter">
												</th>
												<th>
                                                    <input type="text" value="${param.skuCode }" name="skuCode" class="form-control input-sm filter">
                                                </th>
                                                <th>                                                	
                                                </th>
                                                <th>
                                                   <button class="btn btn-primary btn-sm" id="queryFormBtn"><i class="icon-search"></i>搜索</button>
                                                </th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pagination.items }" var="item">
												<tr>
													<td>${item.brandCode }</td>
													<td>${item.skuCode }</td>
													<td>${item.quantity }</td>
													<td></td>																							
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
<script src="${staticbase }/scripts/pages/inv/invlist.js?${version}"></script>

<script type="text/javascript">
	var maxPage="${param.pageCount}";
	var curPage="${param.currentPage}";	
</script>
</body>
</html>