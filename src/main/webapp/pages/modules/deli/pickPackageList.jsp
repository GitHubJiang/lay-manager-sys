<%@include file="/pages/commons/common.jsp" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="label.deli.manager"/>  | <spring:message code="label.ppackage"/> </title>
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
		<jsp:param name="pagecode" value="ACL_PICKPACKAGE" />
	</jsp:include>
  
	<!-- Start right content -->
	<div class="content-page">
		<!-- 面包屑 -->
	    <ol class="breadcrumb">
			<li><a href="javaScript:void(0);"><spring:message code="label.deli.manager"/></a></li>
			<li class="active"><a href="javaScript:void(0);"><spring:message code="label.ppackage"/></a></li>
	    </ol>
	    
<!--主要内容开始部分 START--> 
   
<!-- ============================================================== --> 
<!-- Start Content here --> 
<!-- ============================================================== -->
<div class="content">
	<div class="row">
 		<div class="col-md-4">
        	<h3 class="marign-none"><spring:message code="label.ppackage"/></h3>
        </div>
	</div>
	<div class="row">
		<div class="col-md-12  portlets">
			<div class="widget">
				<div class="widget-content padding">
					<div class="row">
						<div class="col-md-12">
							<form id="queryForm" method="post" role="form" class="form-horizontal"  data-toggle="form-validator" action="${staticbase}/deli/pickpackage/list">
								<input type="hidden" value="${pagination.currentPage}"  name="page" class="startPage">
                                <input type="hidden" name="sort" value="${param.sort }"/>
                   				<input type="hidden" name="sortDirection" value="${param.sortDirection }"/> 
                   				<div class="form-group">
						            <label class="col-sm-1 control-label" id="datetime-label"><spring:message code="label.deli.paymenttime"/></label>
						            <div class="col-sm-10">							                
					                    <div class="col-sm-3">
					                        <div class="input-group datetime">
					                            <input type="text" id="q_time_paymentTimeSta" name="q_time_paymentTimeSta" value="${param.q_time_paymentTimeSta }" class="form-control" aria-labelledby="datetime-label"/>
					                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span>						                            							                            
					                        </div>
					                        <div class="help-block with-errors"></div>
					                    </div>						                  	
					                    <div class="col-sm-3">
					                        <div class="input-group datetime">
					                            <input type="text" id="q_time_paymentTimeEnd" name="q_time_paymentTimeEnd" value="${param.q_time_paymentTimeEnd }" class="form-control" aria-labelledby="datetime-label"/>
					                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span>						                            
					                        </div>
					                        <div class="help-block with-errors"></div>
					                    </div>
						          	</div>
							    </div>
								<div class="table-responsive">
									<table class="table table-hover" data-form="queryForm" data-sortable data-sortable-type="server" 
											data-page="${pagination.currentPage}" data-page-count="${pagination.totalPages}" 
											data-count="${pagination.count}">
										<thead>
											<tr class="sort-header">
												<th data-sortable="false" width="8%"><spring:message code="label.ppackage.channelcode"/></th>
												<th data-sortable="false" width="8%"><spring:message code="label.ppackage.storename"/></th>
												<th data-sortable="false" width="12%"><spring:message code="label.ppackage.storecode"/></th>
												<th data-sortable="false" width="8%"><spring:message code="label.ppackage.code"/></th>
												<th data-sortable="false" width="12%"><spring:message code="label.ppackage.ordercode"/></th>
												<th data-sortable="false" width="8%"><spring:message code="label.ppackage.status"/></th>
                                                <th data-sortable="false" width="12%"><spring:message code="label.ppackage.platcreatetime"/></th>
												<th data-sortable="false" width="8%"><spring:message code="label.operator"/></th></th>
											</tr>
											<tr class="filter-header">
												<th>
												</th>
												<th>
													<input type="text" name="q_sl_storeName" value="${param.q_sl_storeName }" class="form-control input-sm filter">													
												</th>
												<th>
													<input type="text" name="q_string_storeCode" value="${param.q_string_storeCode }" class="form-control input-sm filter">
												</th>
												<th>
													<input type="text" name="q_string_code" value="${param.q_string_code }" class="form-control input-sm filter">
												</th>
												<th>
													<input type="text" name="q_string_orderCode" value="${param.q_string_orderCode }" class="form-control input-sm filte">
												</th>												
												<th>
													<select id="q_int_status" name="q_int_status" class="form-control input-sm filter">
														<option value="0" <c:if test="${param.q_int_status == 0 }">selected</c:if>></option>
														<option value="1" <c:if test="${param.q_int_status == 1 }">selected</c:if>><spring:message code="label.ppackage.status.create"/></option>														
														<option value="2" <c:if test="${param.q_int_status == 2 }">selected</c:if>><spring:message code="label.ppackage.status.ssign"/></option>														
														<option value="3" <c:if test="${param.q_int_status == 3 }">selected</c:if>><spring:message code="label.ppackage.status.csign"/></option>
														<option value="4" <c:if test="${param.q_int_status == 4 }">selected</c:if>><spring:message code="label.ppackage.status.expired"/></option>
														<option value="7" <c:if test="${param.q_int_status == 5 }">selected</c:if>><spring:message code="label.ppackage.status.return"/></option>
														<option value="8" <c:if test="${param.q_int_status == 8 }">selected</c:if>><spring:message code="label.ppackage.status.wreturn"/></option>														
													</select>												
												</th>	
												<th>													
												</th>										
												<th>
													<button id="queryFormBtn" class="btn btn-primary btn-sm" type="button"><i class="icon-search"></i><spring:message code="label.operator.search"/></button>
												</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pagination.items }" var="item">
											<tr>												
												<td>${item.channelCode }</td>
												<td><shopdog:textformat value="${item.storeName}" maxSize="10"/></td>
												<td><shopdog:textformat value="${item.storeCode }" maxSize="10"/></td>
												<td>${item.code }</td>		
												<td>${item.orderCode }</td>											
												<td>
													<c:choose>
														<c:when test="${item.status == 0 }">
															<span><spring:message code="label.ppackage.status"/></span>
														</c:when>
														<c:when test="${item.status == 1 }">
															<span><spring:message code="label.ppackage.status.create"/></span>
														</c:when>
														<c:when test="${item.status == 2 }">
															<span><spring:message code="label.ppackage.status.ssign"/></span>
														</c:when>
														<c:when test="${item.status == 3 }">
															<span><spring:message code="label.ppackage.status.csign"/></span>
														</c:when>
														<c:when test="${item.status == 4 }">
															<span><spring:message code="label.ppackage.status.expired"/></span>
														</c:when>
														<c:when test="${item.status == 7 }">
															<span><spring:message code="label.ppackage.status.return"/></span>
														</c:when>
														<c:when test="${item.status == 8 }">
															<span><spring:message code="label.ppackage.status.wreturn"/></span>
														</c:when>
													</c:choose>
												</td>												
                                                <td><fmt:formatDate value="${item.platCreateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>                                                
												<td>													
													<div class="btn-group btn-group-xs">
														<pri:view acl="ACL_PICKPACKAGE">
															<a type="button" data-toggle="modal" data-target="#myModal" onclick="findPickPackageDetailById(${item.id})" class="btn btn-default" href="javaScript:void(0);">
	                                                        	<i class="fa fa-search"></i><spring:message code="label.operator.query"/>
	                                                       	</a>
                                                       	</pri:view>
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
<div id="myModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-keyboard="true" data-backdrop="static">
    
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel"><spring:message code="label.ppackage.detail"/></h4>
            </div>
            <div class="modal-body">            	
				<form  class="form-horizontal" role="form">					
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message code="label.ppackage.code"/></label>						
						<span id="code" class="col-sm-2 control-label"></span>						
						<label class="col-sm-4 control-label"><spring:message code="label.ppackage.ordercode"/></label>
						<span id="orderCode" class="col-sm-2 control-label"></span>											
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><spring:message code="label.ppackage.status"/></label>
						<span id="status" class="col-sm-2 control-label"></span>
						<label class="col-sm-4 control-label" ><spring:message code="label.ppackage.detail.pgcode"/></label>						
						<span id="pgCode" class="col-sm-2 control-label"></span>		
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message code="label.ppackage.detail.receiver"/></label>
						<span id="receiver" class="col-sm-2 control-label"></span>	
						<label class="col-sm-4 control-label" ><spring:message code="label.ppackage.detail.phone"/></label>						
						<span id="phone" class="col-sm-2 control-label"></span>		
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message code="label.ppackage.detail.address"/></label>
						<span id="address" class="col-sm-8 control-label" text-align="left"></span>	
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message code="label.ppackage.channelcode"/></label>
						<span id="channelCode" class="col-sm-2 control-label"></span>	
						<label class="col-sm-4 control-label"><spring:message code="label.ppackage.storename"/></label>
						<span id="storeName" class="col-sm-2 control-label"></span>						
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message code="label.ppackage.storecode"/></label>
						<span id="storeCode" class="col-sm-2 control-label"></span>						
					</div>					
					
					<table class="table table-hover" id="packageLine">
					    <thead>
						    <tr>
						        <th data-sortable="false"  width="20%"><spring:message code="label.ppackage.detail.skucode"/></th>
						        <th data-sortable="false"  width="50%"><spring:message code="label.ppackage.detail.skuname"/></th>
						        <th data-sortable="false"  width="10%"><spring:message code="label.ppackage.detail.unitprice"/></th>
						        <th data-sortable="false"  width="10%"><spring:message code="label.ppackage.detail.quantity"/></th>
						        <th data-sortable="false"  width="10%"><spring:message code="label.ppackage.detail.totalprice"/></th>
						    </tr>					    
					    </thead>
					    <tbody>						    				    
					    </tbody>
					    
					</table>
					<table class="table table-hover">
						    <tr>
						      <th data-sortable="false"  width="20%"></th>
						      <th data-sortable="false"  width="50%"></th>
						      <th data-sortable="false"  width="10%"></th>
						      <th data-sortable="false"  width="10%"><spring:message code="label.ppackage.detail.alltotalprice"/></th>
						      <th data-sortable="false"  width="10%"><span id="totalPrice"></span></th>
						    </tr>					    
					</table>
					
				</form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="label.operator.colse"/></button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
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
<script src="${staticbase }/scripts/pages/deli/pickPackageList.js?${version}"></script>

<script type="text/javascript">
	var maxPage="${param.pageCount}";
	var curPage="${param.currentPage}";
</script>
</body>
</html>