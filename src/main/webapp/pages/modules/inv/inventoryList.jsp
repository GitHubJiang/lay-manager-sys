<%@include file="/pages/commons/common.jsp" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="label.system.name"></spring:message> | <spring:message code="label.inv.check"></spring:message></title>
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
		<jsp:param name="pagecode" value="ACL_INV" />
	</jsp:include>
  
	<!-- Start right content -->
	<div class="content-page">
		<!-- 面包屑 -->
	    <ol class="breadcrumb">
			<li><a href="#"><spring:message code="label.inv.manager"></spring:message></a></li>
			<li class="active"><a href="#"><spring:message code="label.inv.check"></spring:message></a></li>
	    </ol>
	    
<!--主要内容开始部分 START--> 
   
<!-- ============================================================== --> 
<!-- Start Content here --> 
<!-- ============================================================== -->
<div class="content">
	<div class="col-md-4">
		<h3 class="marign-none"><spring:message code="label.inv.check"/></h3>
	</div>
	<div class="col-md-8">
	<div class="col-md-11"></div>
			<div class="btn-group">
			<pri:view acl="ACL_INV"><a class="btn btn-success" id="exportStoreButton btn-export" onclick="exportInventory()"><spring:message code="label.inv.importinv"></spring:message></a></pri:view>
			</div>
	</div>
	<form action="${staticbase}/inv/exportInv" id="exportInvForm" method="post">
		<input type="hidden" value="${param.q_string_skuCode }" name="q_string_skuCode" >
        <input type="hidden" value="${param.q_string_extCode1 }" name="q_string_extCode1">
        <input type="hidden" value="${param.q_string_extCode2 }" name="q_string_extCode2">
        <input type="hidden" value="${param.q_string_storeCode }" name="q_string_storeCode">
        <input type="hidden" value="${param.q_sl_storeName }" name="q_sl_storeName">
	</form>

	<div class="row">
		<div class="col-md-12  portlets">
			<div class="widget">
				<div class="widget-content padding">
					<div class="row">
						<div class="col-md-12">
							<form id="queryForm" method="post" role="form" action="${staticbase}/inv/list">
								<input type="hidden" value="${pagination.currentPage}"  name="page" class="startPage">
                                <input type="hidden" name="sort" value="${param.sort }"/>
                   				<input type="hidden" name="sortDirection" value="${param.sortDirection }"/>
								<div class="table-responsive">
									<table id="dataTable" class="table table-hover" data-form="queryForm" data-sortable data-sortable-type="server" 
											data-page="${pagination.currentPage}" data-page-count="${pagination.totalPages}">
										<thead>
											<tr class="sort-header">
												<th data-sort-name="false">SKU CODE</th>
                                                <th data-sort-name="false"><spring:message code="label.inv.extcode1"></spring:message></th>
                                                <th data-sort-name="false"><spring:message code="label.inv.extcode2"></spring:message></th>
                                                <th data-sort-name="false"><spring:message code="label.inv.storecode"></spring:message></th>
                                                <th data-sort-name="false"><spring:message code="label.inv.storename"></spring:message></th>
                                                <th data-sort-name="false"><spring:message code="label.inv.supplierskucode"></spring:message></th>
                                                <th data-sort-name="false"><spring:message code="label.inv.skuproperties"></spring:message></th>
                                                <th data-sort-name="false"><spring:message code="label.inv.quantity"></spring:message></th>
                                                <th data-sortable="false"><spring:message code="label.operator"></spring:message></th>
											</tr>
											<tr class="filter-header">
												<th>
													<input type="text" value="${param.q_string_skuCode }" name="q_string_skuCode" class="form-control input-sm filter">
												</th>
												<th>
                                                    <input type="text" value="${param.q_string_extCode1 }" name="q_string_extCode1" class="form-control input-sm filter">
                                                 </th>
                                                 <th>
                                                    <input type="text" value="${param.q_string_extCode2 }" name="q_string_extCode2" class="form-control input-sm filter">
                                                 </th>
                                                 <th>
                                                    <input type="text" value="${param.q_string_storeCode }" name="q_string_storeCode" class="form-control input-sm filter">
                                                 </th>
                                                 <th>
                                                    <input type="text" value="${param.q_sl_storeName }" name="q_sl_storeName" class="form-control input-sm filter">
                                                  </th>
                                                  <th></th>
                                                  <th></th>
                                                  <th></th>
                                                  <th>
                                                     <button class="btn btn-primary btn-sm" id="queryFormBtn"><i class="icon-search"></i> <spring:message code="label.operator.search"></spring:message></button>
                                                  </th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pagination.items }" var="item">
											<tr id="${item.invId }">
												<td>${item.skuCode }</td>
												<td>${item.extCode1 }</td>
												<td>${item.extCode2 }</td>
												<td>${item.storeCode }</td>
												<td>${item.storeName }</td>
												<td>${item.supplierSkuCode }</td>
												<td>${item.skuProperties }</td>
												<td>${item.quantity }</td>
																								
											</tr>
											</c:forEach>
											<c:if test="${quantityCount != null }">
												<tr>
													<td colspan="8">
														<div class="col-md-11"></div>
														<div class="col-md-1">
															<h5><b><spring:message code="label.inv.qtycount"></spring:message>:${quantityCount }</b></h5>
														</div>
													</td>
												</tr>
											</c:if>
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
<script src="${staticbase }/scripts/pages/inv/inventoryList.js?${version}"></script>

<script type="text/javascript">
	var maxPage="${param.pageCount}";
	var curPage="${param.currentPage}";
</script>
</body>
</html>