<%@include file="/pages/commons/common.jsp" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="label.system.name"></spring:message> | <spring:message code="label.appu.ipadupdate"></spring:message></title>
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
		<jsp:param name="pagecode" value="ACL_APPU_P" />
	</jsp:include>
  
	<!-- Start right content -->
	<div class="content-page">
		<!-- 面包屑 -->
	    <ol class="breadcrumb">
			<li><a href="#"><spring:message code="label.appu.appupdate"></spring:message></a></li>
			<li class="active"><a href="#"><spring:message code="label.appu.ipadupdate"></spring:message></a></li>
	    </ol>
	    
<!--主要内容开始部分 START--> 
   
<!-- ============================================================== --> 
<!-- Start Content here --> 
<!-- ============================================================== -->
<div class="content">
	<div class="row">
		<div class="col-md-4">
			<h3><spring:message code="label.appu.ipadupdate"></spring:message></h3>
		</div>
        <div class="col-md-8">
        	<pri:add acl="ACL_APPU_P">
	        	<div class="toolbar-btn-action">	                
	                <a href="${staticbase}/appu/toadd" class="btn btn-success" ><i class="fa fa-plus-circle"></i><spring:message code="label.appu.addversion"></spring:message></a>
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
							<form id="queryForm" method="post" role="form" action="${staticbase}/appu/pad/list">
								<input type="hidden" value="${pagination.currentPage}"  name="page" class="startPage">
                                <input type="hidden" name="sort" value="${param.sort }"/>
                   				<input type="hidden" name="sortDirection" value="${param.sortDirection }"/>
								<div class="table-responsive">
									<table class="table table-hover" data-form="queryForm" data-sortable data-sortable-type="server" 
											data-page="${pagination.currentPage}" data-page-count="${pagination.totalPages}" 
											data-count="${pagination.count}">
										<thead>
											<tr class="sort-header">
												<th data-sortable="false" width="6%"><spring:message code="label.appu.brandcode"></spring:message></th>
												<th data-sortable="false" width="6%"><spring:message code="label.appu.version"></spring:message></th>
												<th data-sortable="false" ><spring:message code="label.appu.filename"></spring:message></th>
												<th data-sortable="false" width="8%"><spring:message code="label.appu.updates_zhcn"></spring:message></th>
												<th data-sortable="false" width="8%"><spring:message code="label.appu.updates_zhtw"></spring:message></th>
                                                <th data-sortable="false" width="8%"><spring:message code="label.appu.updates_en"></spring:message></th>
                                                <th data-sortable="false" ><spring:message code="label.appu.isforceupdate"></spring:message></th>
                                                <th data-sortable="false" ><spring:message code="label.appu.uploadtime"></spring:message></th>
                                                <th data-sortable="false" ><spring:message code="label.appu.lastoperator"></spring:message></th>
												<th data-sortable="false" width="8%"><spring:message code="label.operator"></spring:message></th>
											</tr>
											<tr class="filter-header">
												<th>
													<input type="text" name="q_string_brandCode" value="${param.q_string_brandCode }" class="form-control input-sm filter">
												</th>
												<th>
													<input type="text" name="q_string_versionNo" value="${param.q_string_versionNo }" class="form-control input-sm filter">
												</th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
                                                <th></th>
												<th>
													<button id="queryFormBtn" class="btn btn-primary btn-sm" type="button"><i class="icon-search"></i> <spring:message code="label.operator.search"></spring:message></button>
												</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pagination.items }" var="item">
											<tr>
												<td>${item.brandCode }</td>
												<td>${item.versionNo }</td>
												<td>${item.fileName }</td>
												<td>${item.message }</td>
												<td>${item.messageTW }</td>
												<td>${item.messageEn }</td>
												<td>
												<c:choose>
													<c:when test="${item.updateStrategy == 1 }">
														<spring:message code="label.is"></spring:message>&nbsp;<button class="btn-xs btn-warning" onclick="updateUpdateStrategy(${item.id},2)"><spring:message code="label.off"></spring:message></button>
													</c:when>
													<c:otherwise>
														<spring:message code="label.not"></spring:message>&nbsp;<button class="btn-xs btn-success" onclick="updateUpdateStrategy(${item.id},1)"><spring:message code="label.on"></spring:message></button>
													</c:otherwise>
												</c:choose>
												</td>
												<td><fmt:formatDate value="${item.uploadTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												<td><fmt:formatDate value="${item.modifyTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												<td>
													<pri:update acl="ACL_APPU_P">
														<a type="button" class="btn-sm btn-default" href="${staticbase}/appu/toupdate?id=${item.id}">
	                                                       	<i class="fa fa-cog"></i><spring:message code="label.operator.edit"></spring:message>
	                                                   	</a>
													</pri:update>
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
<script src="${staticbase }/scripts/pages/appu/ipadList.js?${version}"></script>

<script type="text/javascript">
	var maxPage="${param.pageCount}";
	var curPage="${param.currentPage}";
</script>
</body>
</html>