<%@page import="com.baozun.scm.shopdog.constants.AuthConstants"%>
<%@include file="/pages/commons/common.jsp" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="label.system.name"/> | <spring:message code="label.brand.manager"/></title>
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
		<jsp:param name="pagecode" value="ACL_BRAND" />
	</jsp:include>
  
	<!-- Start right content -->
	<div class="content-page">
		<!-- 面包屑 -->
	    <ol class="breadcrumb">
			<li><a href="javaScript:void(0);"><spring:message code="label.brand.manager"/></a></li>
			<li class="active"><a href="javaScript:void(0);"><spring:message code="label.brand.manager"/></a></li>
	    </ol>
	    
<!--主要内容开始部分 START-->     
<!-- ============================================================== --> 
<!-- Start Content here --> 
<!-- ============================================================== -->
<div class="content">
	<div class="row">
 		<div class="col-md-4">
        	<h3 class="marign-none"><spring:message code="label.brand.manager"/></h3>
        </div>
        <div class="col-md-8">
            <pri:add acl="ACL_BRAND">
	            <div class="toolbar-btn-action">	                
	                <a href="${staticbase}/brand/toAdd" class="btn btn-success" ><i class="fa fa-plus-circle"></i><spring:message code="label.operator.add"/></a>
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
							<form id="queryForm" method="post" role="form" action="${staticbase}/brand/list">
								<input type="hidden" value="${pagination.currentPage}"  name="page" class="startPage">
                                <input type="hidden" name="sort" value="${param.sort }"/>
                   				<input type="hidden" name="sortDirection" value="${param.sortDirection }"/>
								<div class="table-responsive">
									<table class="table table-hover" data-form="queryForm" data-sortable data-sortable-type="server" 
											data-page="${pagination.currentPage}" data-page-count="${pagination.totalPages}">
										<thead>
											<tr class="sort-header">
												<th data-sortable="false" width="15%"><spring:message code="label.brand.brandcode"/></th>
												<th data-sortable="false" width="15%"><spring:message code="label.brand.isselfpick"/></th>
												<th data-sortable="false" width="15%"><spring:message code="label.brand.isorder"/></th>
												<th data-sortable="false" width="15%"><spring:message code="label.brand.isreturn"/></th>
												<th data-sortable="false" width="15%"><spring:message code="label.brand.isdeli"/></th>
												<th data-sortable="false" width="10%"><spring:message code="label.operator"/></th>
											</tr>
											<tr class="filter-header">												
												<th>
													<c:if test="${organizationCode=='ROOT'}">
														<input type="text" name="q_string_brandCode" value="${param.q_string_brandCode }" class="form-control input-sm filter">
													</c:if>			
												</th>
												<th>													
													<select name="q_int_isSelfPick" class="form-control input-sm filter">
														<option value="">-</option>
														<option value="0" <c:if test="${param.q_int_isSelfPick == 0 && param.q_int_isSelfPick!=''}">selected</c:if>><spring:message code="label.not"/></option>
														<option value="1" <c:if test="${param.q_int_isSelfPick == 1 }">selected</c:if>><spring:message code="label.is"/></option>
													</select>
												</th>
												<th>
													<select name="q_int_isOrder" class="form-control input-sm filter">
														<option value="">-</option>
														<option value="0" <c:if test="${param.q_int_isOrder == 0 && param.q_int_isOrder != ''}">selected</c:if>><spring:message code="label.not"/></option>
														<option value="1" <c:if test="${param.q_int_isOrder == 1 }">selected</c:if>><spring:message code="label.is"/></option>
													</select>
												</th>
												<th>
													<select name="q_int_isReturn" class="form-control input-sm filter">
														<option value="">-</option>
														<option value="0" <c:if test="${param.q_int_isReturn == 0 && param.q_int_isReturn != ''}">selected</c:if>><spring:message code="label.not"/></option>
														<option value="1" <c:if test="${param.q_int_isReturn == 1 }">selected</c:if>><spring:message code="label.is"/></option>
													</select>
												</th>
												<th>
													<select name="q_int_isDeli" class="form-control input-sm filter">
														<option value="">-</option>
														<option value="0" <c:if test="${param.q_int_isDeli == 0 && param.q_int_isDeli != ''}">selected</c:if>><spring:message code="label.not"/></option>
														<option value="1" <c:if test="${param.q_int_isDeli == 1 }">selected</c:if>><spring:message code="label.is"/></option>
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
												<td>${item.brandCode }</td>
												<td>
													<c:if test="${item.isSelfPick == 0 }"><spring:message code="label.not"/></c:if>
													<c:if test="${item.isSelfPick == 1 }"><spring:message code="label.is"/></c:if>
												</td>
												<td>
													<c:if test="${item.isOrder == 0 }"><spring:message code="label.not"/></c:if>
													<c:if test="${item.isOrder == 1 }"><spring:message code="label.is"/></c:if>
												</td>
												<td>
													<c:if test="${item.isReturn == 0 }"><spring:message code="label.not"/></c:if>
													<c:if test="${item.isReturn == 1 }"><spring:message code="label.is"/></c:if>
												</td>
												<td>
													<c:if test="${item.isDeli == 0 }"><spring:message code="label.not"/></c:if>
													<c:if test="${item.isDeli == 1 }"><spring:message code="label.is"/></c:if>
												</td>
												<td>
													
													<div class="btn-group btn-group-xs">
														<pri:view acl="ACL_BRAND">
															<a type="button" class="btn btn-default" href="${staticbase}/brand/brandDetail/${item.id}">
	                                                        	<i class="fa fa-search"></i><spring:message code="label.operator.query"/>
	                                                       	</a>
                                                       	</pri:view>
                                                       	<pri:update acl="ACL_BRAND">
															<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
																<span class="caret"></span>
																<span class="sr-only">Toggle Dropdown</span>
															</button>
															<ul class="dropdown-menu" role="menu">
	                                                        	<li><a href="${staticbase}/brand/toUpdate/${item.id}"><i class="fa fa-edit"></i><spring:message code="label.operator.edit"/></a></li>
															</ul>
														</pri:update>
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
<script src="${staticbase }/scripts/pages/brand/brandList.js?${version}"></script>

<script type="text/javascript">
	var maxPage="${param.pageCount}";
	var curPage="${param.currentPage}";
</script>
</body>
</html>