<%@include file="/pages/commons/common.jsp" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="label.system.name"/> | <spring:message code="label.deli.manager"/></title>
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
		<jsp:param name="pagecode" value="ACL_RETURN" />
	</jsp:include>
  
	<!-- Start right content -->
	<div class="content-page">
		<!-- 面包屑 -->
	    <ol class="breadcrumb">
			<li><a href="javaScript:void(0);"><spring:message code="label.deli.manager"/></a></li>
			<li class="active"><a href="javaScript:void(0);"><spring:message code="label.return.storereturn"/></a></li>
	    </ol>
	    
<!--主要内容开始部分 START--> 
   
<!-- ============================================================== --> 
<!-- Start Content here --> 
<!-- ============================================================== -->
<div class="content">
	<div class="row">
 		<div class="col-md-4">
        	<h3 class="marign-none"><spring:message code="label.return.storereturn"/></h3>
        </div>
        <div class="col-md-8">
        </div>
	</div>
	<div class="row">
		<div class="col-md-12  portlets">
			<div class="widget">
				<div class="widget-content padding">
					<div class="row">
						<div class="col-md-12">
							<form id="queryForm" method="post" role="form" class="form-horizontal"  data-toggle="form-validator" action="${staticbase}/return/list">
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
											data-page="${pagination.currentPage}" data-page-count="${pagination.totalPages}">
										<thead>
											<tr class="sort-header">
												<th data-sortable="false" width="8%"><spring:message code="label.deli.channelcode"/></th>
												<th data-sortable="false" width="12%"><spring:message code="label.deli.storename"/></th>
												<th data-sortable="false" width="8%"><spring:message code="label.deli.storecode"/></th>
												<th data-sortable="false" width="12%"><spring:message code="label.deli.code"/></th>
												<th data-sortable="false" width="12%"><spring:message code="label.deli.ordercode"/></th>
                                                <th data-sortable="false" width="8%"><spring:message code="label.return.status"/></th>
                                                <th data-sortable="false" width="12%"><spring:message code="label.deli.paymenttime"/></th>
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
													<input type="text" name="q_string_orderCode" value="${param.q_string_orderCode }" class="form-control input-sm filter">
												</th>
												<th>
													<select id="q_int_status" name="q_int_status" class="form-control input-sm filter">
													<option value="0" <c:if test="${param.q_int_status != 5 && param.q_int_status == 6}">selected</c:if>>-</option>
														<option value="5" <c:if test="${param.q_int_status == 5 }">selected</c:if>><spring:message code="label.return.status.wexpresspick"/></option>
														<option value="6" <c:if test="${param.q_int_status == 6 }">selected</c:if>><spring:message code="label.return.status.expresspicked"/></option>														
													</select>
												</th>
                                                <th>
                                                </th>
												<th>
													<pri:view acl="ACL_RETURN">
														<button id="queryFormBtn" class="btn btn-primary btn-sm" type="button"><i class="icon-search"></i><spring:message code="label.operator.search"/></button>
													</pri:view>
												</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pagination.items }" var="item">
											<tr>												
												<td>${item.channelCode }</td>
												<td><shopdog:textformat value="${item.storeName }" maxSize="10"/></td>
												<td><shopdog:textformat value="${item.storeCode }" maxSize="10"/></td>
												<td>${item.code }</td>
												<td>${item.orderCode }</td>
												<td>
													<c:choose>
														<c:when test="${item.status == 5 }">
															<span><spring:message code="label.return.status.wexpresspick"/></span>
														</c:when>
														<c:when test="${item.status == 6 }">
															<span><spring:message code="label.return.status.expresspicked"/></span>
														</c:when>
													</c:choose>
												</td>
												<td><fmt:formatDate value="${item.paymentTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												<td>													
													<div class="btn-group btn-group-xs">
														<pri:view acl="ACL_RETURN">
															<a type="button" data-toggle="modal" data-target="#myModal" onclick="findOrderDetailById(${item.id})" class="btn btn-default" href="javaScript:void(0);">
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
                <h4 class="modal-title" id="myModalLabel"><spring:message code="label.deli.orderdetail"/></h4>
            </div>
            <div class="modal-body">            	
				<form  class="form-horizontal" role="form">					
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message code="label.deli.code"/></label>						
						<span id="code" class="col-sm-2 control-label"></span>						
						<label class="col-sm-4 control-label"><spring:message code="label.deli.ordercode"/></label>
						<span id="orderCode" class="col-sm-2 control-label"></span>											
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><spring:message code="label.return.track_no"/></label>
						<span id="trackNo" class="col-sm-2 control-label"></span>	
						<label class="col-sm-4 control-label" ><spring:message code="label.deli.status"/></label>						
						<span id="status" class="col-sm-2 control-label"></span>	
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message code="label.deli.address"/></label>
						<span id="address" class="col-sm-6 control-label"></span>						
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message code="label.deli.channelcode"/></label>
						<span id="channelCode" class="col-sm-2 control-label"></span>			
						<label class="col-sm-4 control-label"><spring:message code="label.deli.storename"/></label>
						<span id="storeName" class="col-sm-2 control-label"></span>					
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message code="label.deli.storecode"/></label>
						<span id="storeCode" class="col-sm-2 control-label"></span>
					</div>
					
					<table class="table table-hover" id="orderLine">
					    <thead>
						    <tr>
						        <th data-sortable="false"><spring:message code="label.deli.skucode"/></th>
						        <th data-sortable="false"><spring:message code="label.deli.goodsname"/></th>
						        <th data-sortable="false"><spring:message code="label.deli.goodsprice"/></th>
						        <th data-sortable="false"><spring:message code="label.deli.goodsquantity"/></th>
						        <th data-sortable="false"><spring:message code="label.deli.linetotal"/></th>
						    </tr>					    
					    </thead>
					    <tbody>						    				    
					    </tbody>
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
<script src="${staticbase }/scripts/pages/deli/returnList.js?${version}"></script>

<script type="text/javascript">
	var maxPage="${param.pageCount}";
	var curPage="${param.currentPage}";
</script>
</body>
</html>