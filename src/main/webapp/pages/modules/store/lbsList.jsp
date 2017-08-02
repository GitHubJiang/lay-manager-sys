<%@include file="/pages/commons/common.jsp" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="label.system.name"/> | <spring:message code="label.store.lbs"/></title>
<%@include file="/pages/commons/common-meta.jsp" %>
<%@include file="/pages/commons/common-css.jsp" %>
<%@include file="/pages/commons/common-compatible.jsp" %>
<%@include file="/pages/commons/common-icon.jsp" %>

</head>
<body class="fixed-left" locale="zh-cn" onload="prettyPrint()">
<%@include file="/pages/commons/common-layer.jsp" %>
<!-- Begin page -->
<div id="wrapper"> 
  
	<%@include file="/pages/commons/common-top.jsp" %>
  
	<jsp:include page="/pages/commons/common-left.jsp">
		<jsp:param name="pagecode" value="ACL_MAPPING" />
	</jsp:include>
  
	<!-- Start right content -->
	<div class="content-page">
		<!-- 面包屑 -->
	    <ol class="breadcrumb">
			<li><a href="#"><spring:message code="label.store.sys"/></a></li>
			<li class="active"><a href="#"><spring:message code="label.store.lbs"/></a></li>
	    </ol>
	    
<!--主要内容开始部分 START--> 
   
<!-- ============================================================== --> 
<!-- Start Content here --> 
<!-- ============================================================== -->
<div class="content">
	<h3><spring:message code="label.store.lbs.push"/></h3>

	<div class="row">
		<div class="col-md-12  portlets">
			<div class="widget">
				<div class="widget-content padding">
					<div class="row">
						<div class="col-md-12">
							<form id="queryForm" method="post" role="form" action="${staticbase}/store/lbs/list">					
								<input type="hidden" value="${pagination.currentPage}" name="page" class="startPage">
								<input type="hidden" name="sort" value="${param.sort }" /> 
								<input type="hidden" name="sortDirection" value="${param.sortDirection }" />
								<div class="table-responsive">
									<table class="table table-hover" data-form="queryForm"
										data-sortable="data-sortable" data-sortable-type="server"
										data-page="${pagination.currentPage}" data-page-count="${pagination.totalPages}">
										<thead>
											<tr class="sort-header">
												<th data-batch data-extra-batch="tableExtraBatch"
													data-sortable="false" width="5%"></th>
												<th data-sort-name="uid"><spring:message code="label.store.lbs.uid"/></th>
												<th data-sort-name="geotable_id"><spring:message code="label.store.lbs.geotableid"/></th>
												<th data-sort-name="title"><spring:message code="label.store.lbs.title"/></th>
												<th data-sort-name="address"><spring:message code="label.store.lbs.address"/></th>
												<th data-sort-name="province"><spring:message code="label.store.province"/></th>
												<th data-sort-name="city"><spring:message code="label.store.city"/></th>
												<th data-sort-name="district"><spring:message code="label.store.district"/></th>
												<th data-sortable="coord_type"><spring:message code="label.store.lbs.coordtype"/></th>
												<th data-sort-name="location" width="10%"><spring:message code="label.store.lbs.location"/></th>
												<th data-sort-name="tags"><spring:message code="label.store.lbs.tags"/></th>
												<th data-sortable="distance"><spring:message code="label.store.lbs.distance"/></th>
												<th data-sort-name="weight"><spring:message code="label.store.lbs.weight"/></th>
												<th data-sort-name="direction"><spring:message code="label.store.lbs.direction"/></th>
												<th data-sortable="false" width="8%"><spring:message code="label.role.edit.opt"/></th>
											</tr>
											<tr class="filter-header">
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th><input type="text" value=""
													name="q_string_brandCode"
													class="form-control input-sm filter"></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th>
													<button class="btn btn-primary btn-sm" name=""
														type="submit">
														<i class="icon-search"></i> <spring:message code="label.role.edit.search"/>
													</button>
												</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pagination.items }" var="item">
											<tr id="1">
												<td><input data-itemid="${item.uid }"  type="checkbox" class="rows-check"></td>
												<td>${item.uid }</td>
												<td>${item.geotable_id }</td>
												<td>${item.title }</td>
												<td>${item.address }</td>
												<td>${item.province }</td>
												<td>${item.city }</td>
												<td>${item.district }</td>
												<td>${item.coord_type }</td>
												<td>${item.location }</td>
												<td>${item.tags }</td>
												<td>${item.distance }</td>
												<td>${item.weight }</td>
												<td>${item.direction }</td>												
												<td>
													<div class="btn-group btn-group-xs">
														<a type="button" class="btn btn-default"> <i
															class="fa fa-bell-o"></i><spring:message code="label.role.edit.opt"/>
														</a>
														<button type="button"
															class="btn btn-default dropdown-toggle"
															data-toggle="dropdown">
															<span class="caret"></span> <span class="sr-only">Toggle
																Dropdown</span>
														</button>
														<ul class="dropdown-menu" role="menu">
															<li><a onclick="findStoreAddByStoreId('${item.storeId}')"><i class="fa fa-times" ></i><spring:message code="label.role.edit.fremove"/></a></li>
															<li><a data-toggle="modal" data-target="#myModal" onclick="findStoreAddByStoreId('${item.storeId}')"><i
																	class="fa fa-edit"></i><spring:message code="label.role.edit.add.update"/></a></li>
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
<div id="myModal" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	data-keyboard="true" data-backdrop="static">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">

			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel"><spring:message code="label.role.edit.add.update"/></h4>
			</div>

			<div class="modal-body">
				<form class="form-horizontal" role="form"
					data-toggle="form-validator">
					<div class="form-group">
						<label class="col-sm-2 control-label" for="input-text">poi的id</label>
						<div class="col-sm-4">
							<input type="text" value="1744082926" class="form-control"
								readonly />
						</div>
						<label class="col-sm-2 control-label" for="input-text">geotable_id</label>
						<div class="col-sm-4">
							<input type="text" value="145334" class="form-control"
								readonly />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="input-name"><spring:message code="label.store.lbs.title"/></label>
						<div class="col-sm-4">
							<input type="text" placeholder="名称" class="form-control"
								id="input-name" value="title" data-minlength="8"
								data-start="4" required />
							<div class="help-block with-errors"></div>
						</div>
						<label class="col-sm-2 control-label">coord_type</label>
						<div class="col-sm-4">
							<select class="form-control" aria-labelledby="birth-label">
								<option value="1"><spring:message code="label.store.lbs.locationgps"/>GPS经纬度坐标</option>
								<option value="2"><spring:message code="label.store.lbs.locationceju"/>测局加密经纬度坐标</option>
								<option value="3"><spring:message code="label.store.lbs.locationbaidu"/>百度加密经纬度坐标</option>
								<option value="4"><spring:message code="label.store.lbs.locationbaidumo"/>百度加密墨卡托坐标</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message code="label.store.lbs.address"/></label>
						<div class="col-sm-10">
							<input type="text" placeholder="地址" class="form-control"
								value="东方明珠3东方明珠3东方明珠3东方明珠3" data-minlength="8"
								data-start="4" required />
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message code="label.store.modal.province"/></label>
						<div class="col-sm-2">
							<select class="form-control" aria-labelledby="birth-label"
								name="province" id="province" runat="server">
							</select>
						</div>
						<label class="col-sm-2 control-label"><spring:message code="label.store.modal.city"/></label>
						<div class="col-sm-2">
							<select class="form-control" aria-labelledby="birth-label"
								id="city" runat="server" name="city">
							</select>
						</div>
						<label class="col-sm-2 control-label"><spring:message code="label.store.modal.district"/></label>
						<div class="col-sm-2">
							<select class="form-control" aria-labelledby="birth-label"
								id="county" runat="server" name="city">
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message code="label.store.lbs.longitude"/></label>
						<div class="col-sm-4">
							<input type="text" placeholder="名称" class="form-control"
								value="33.50647" readonly />
						</div>
						<label class="col-sm-2 control-label"><spring:message code="label.store.lbs.latitude"/></label>
						<div class="col-sm-4">
							<input type="text" placeholder="名称" class="form-control"
								value="33.50647" readonly />
						</div>
					</div>
				</form>

				<h4>*谨慎操作</h4>
			</div>

			<div class="modal-footer">
				<button type="button" class="btn btn-default"
					data-dismiss="modal"><spring:message code="label.operator.colse"/></button>
				<button type="button" class="btn btn-primary"><spring:message code="label.role.edit.save"/></button>
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
<script src="${staticbase }/scripts/pages/store/lbsList.js?${version}"></script>
<script type="text/javascript">
	var maxPage="${param.pageCount}";
	var curPage="${param.currentPage}";
</script>
</body>
</html>