<%@include file="/pages/commons/common.jsp" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="label.system.name"/>| <spring:message code="label.store.manager"/></title>
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
		<jsp:param name="pagecode" value="ACL_STORE" />
	</jsp:include>
  
	<!-- Start right content -->
	<div class="content-page">
		<!-- 面包屑 -->
	    <ol class="breadcrumb">
			<li><a href="#"><spring:message code="label.store.sys"/></a></li>
			<li class="active"><a href="#"><spring:message code="label.store.manager"/></a></li>
	    </ol>
	    
<!--主要内容开始部分 START--> 
   
<!-- ============================================================== --> 
<!-- Start Content here --> 
<!-- ============================================================== -->
<div class="content">
	<div class="col-md-4">
		<h3 class="marign-none"><spring:message code="label.store.manager"/></h3>
	</div>
	<div class="col-md-8">
	<div class="col-md-8"></div>
	<div class="toolbar-btn-action">
		<div class="btn-toolbar" role="toobar">
		<div class="btn-group">
			<pri:add acl="ACL_STORE">
				<a href="#" data-toggle="modal" data-target=".modal-import" class="btn btn-success btn-import"><i class="fa fa-plus-circle"></i> <spring:message code="label.store.bulkimport"/></a>
			</pri:add>
			</div>
			<div class="btn-group">
			<pri:add acl="ACL_STORE"><a class="btn btn-success btn-add" data-toggle="modal" data-target="#myModalAdd" onclick="addStore()"><i class="fa fa-plus-circle"></i> <spring:message code="label.store.add"/></a></pri:add>
			</div>
			<div class="btn-group">
			<pri:view acl="ACL_STORE"><a class="btn btn-success" id="exportStoreButton btn-export" onclick="exportStore()"><spring:message code="label.store.import"/></a></pri:view>
			</div>
				
		</div>
	</div>
	</div>
	<!-- 导出门店表单 -->
	<form id="exportStoreForm" method="post" action="${staticbase}/store/exportStore">
		<input type="hidden" name="q_sl_storeCode" value="${param.q_sl_storeCode }" /> 
		<input type="hidden" name="q_sl_storeName" value="${param.q_sl_storeName }" />
		<input type="hidden" name="q_sl_province" value="${param.q_sl_province }" />
		<input type="hidden" name="q_sl_district" value="${param.q_sl_district }" /> 
		<input type="hidden" name="q_sl_city" value="${param.q_sl_city }" />
		<input type="hidden" value="${param.q_sl_contactPerson }" name="q_sl_contactPerson">  
	</form> 
	<div class="row">
		<div class="col-md-12  portlets">
			<div class="widget">
				<div class="widget-content padding">
					<div class="row">
						<div class="col-md-12">
							<form id="queryForm" method="post" role="form" action="${staticbase}/store/list">
								<input type="hidden" value="${pagination.currentPage}" name="page" class="startPage">
								<input type="hidden" name="sort" value="${param.sort }" /> 
								<input type="hidden" name="sortDirection" value="${param.sortDirection }" />
								<div class="table-responsive">
									<table class="table table-hover" data-form="queryForm"
										data-sortable="data-sortable" data-sortable-type="server"
										data-page="${pagination.currentPage}" data-page-count="${pagination.totalPages}">
										<thead>
											<tr class="sort-header">
<!-- 												<th data-batch data-extra-batch="tableExtraBatch" -->
<!-- 													data-sortable="false" width="5%"></th> -->
												<th data-sort-name="brandCode"><spring:message code="label.store.brandcode"/></th>
												<th data-sort-name="storeCode" ><spring:message code="label.store.storecode"/></th>
												<th data-sort-name="storeName" ><spring:message code="label.store.storename"/></th>												
<!-- 												<th data-sort-name="country" width="4%">国家</th> -->
												<th data-sort-name="province" width="8%"><spring:message code="label.store.province"/></th>
												<th data-sort-name="city" width="8%"><spring:message code="label.store.city"/></th>
												<th data-sort-name="district" width="10%"><spring:message code="label.store.district"/></th>
<!-- 												<th data-sortable="address" width="12%">地址</th> -->
<!-- 												<th data-sort-name="lang">语言</th>												 -->
<!-- 												<th data-sort-name="contactPhone">联系电话</th> -->
												<th data-sort-name="deliSwitch" width="12%"><spring:message code="label.store.deliswitch"/></th>
												<th data-sort-name="isSyncFull" width="12%"><spring:message code="label.store.issyncfull"/></th>
												<th data-sort-name="contactPerson"><spring:message code="label.store.contactperson"/></th>
												<th data-sort-name="createTime" width="10%"><spring:message code="label.store.createtime"/></th>
<!-- 												<th data-sort-name="lbsPoiId">地图编码</th> -->
<!-- 												<th data-sort-name="isLbsInfo">是否已录入</th> -->
												<th data-sortable="false" width="8%"><spring:message code="label.operator"/></th>
											</tr>
											<tr class="filter-header">
												<th>
<!-- 													<input type="text" value="" name="q_string_brandCode"	class="form-control input-sm filter"> -->
												</th>
												<th>
													<input type="text" value="${param.q_sl_storeCode }" name="q_sl_storeCode" class="form-control input-sm filter">
												</th>
												<th>
													<input type="text" value="${param.q_sl_storeName }" name="q_sl_storeName" class="form-control input-sm filter">
												</th>
												<th>
													<select class="form-control"
														aria-labelledby="birth-label" id="province" name="q_sl_province" runat="server">
													</select>
												</th>
												<th>
													<select class="form-control"
														aria-labelledby="birth-label"  runat="server"  id="city" 
														name="q_sl_city">
													</select>
												</th>
												<th>
													<select class="form-control"
														aria-labelledby="birth-label" runat="server" id="district" 
														name="q_sl_district" style="width:100px">
													</select>
												</th>
												<th>
													<select id="q_int_deliSwitch" name="q_int_deliSwitch" class="form-control" style="width:100px">
														<option value="2">-</option>
														<option value="0" <c:if test="${param.q_int_deliSwitch == 0}">selected</c:if>><spring:message code="label.store.off"/></option>
														<option value="1" <c:if test="${param.q_int_deliSwitch == 1}">selected</c:if>><spring:message code="label.store.on"/></option>
													</select>
												</th>
												<th>
													<select id="q_int_isSyncFull" name="q_int_isSyncFull" class="form-control" style="width:100px">
														<option value="2">-</option>
														<option value="0" <c:if test="${param.q_int_isSyncFull == 0}">selected</c:if>><spring:message code="label.store.off"/></option>
														<option value="1" <c:if test="${param.q_int_isSyncFull == 1}">selected</c:if>><spring:message code="label.store.on"/></option>
													</select>
												</th>
												<th>
													<input type="text" value="${param.q_sl_contactPerson }" name="q_sl_contactPerson" class="form-control input-sm filter">
												</th>
												<th></th>
												<th>
													<button id="queryFormBtn" class="btn btn-primary btn-sm" name="" type="button"><i class="icon-search"></i> <spring:message code="label.operator.search"/></button>
												</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pagination.items }" var="item">
											<tr id="1">
<%-- 												<td><input data-itemid="${item.storeId }"  type="checkbox" class="rows-check"></td> --%>
												<td>${item.brandCode }</td>
												<td>${item.storeCode }</td>
												<td>${item.storeName }</td>												
<%-- 												<td>${item.country }</td> --%>
												<td>${item.province }</td>
												<td>${item.city }</td>
												<td>${item.district }</td>
<%-- 												<td>${item.address }</td> --%>
<%-- 												<td>${item.lang }</td>												 --%>
<%-- 												<td>${item.contactPhone }</td> --%>
												<td name="deli${item.storeId}">
													<c:choose>
														<c:when test="${item.deliSwitch == 0 }">
															 <spring:message code="label.off"/>
															<a type="button" name="select" class="label label-warning"  onclick="isStatusChange('${item.storeId}','${item.deliSwitch}','1')">
																<spring:message code="label.store.on"/>
															</a>
														</c:when>
														<c:when test="${item.deliSwitch == 1 }">
															<spring:message code="label.on"/>
															<a type="button" name="select" class="label label-success"  onclick="isStatusChange('${item.storeId}','${item.deliSwitch}','1')">
																<spring:message code="label.store.off"/>
															</a>
														</c:when>
														<c:otherwise>
															<spring:message code="label.on"/>
															<a type="button" name="select"  class="label label-success" onclick="isStatusChange('${item.storeId}','${item.deliSwitch}','1')">
																<spring:message code="label.store.off"/>
															</a>
														</c:otherwise>
													</c:choose>
												</td>
												<td name="sync${item.storeId}">
													<c:choose>
														<c:when test="${item.isSyncFull == 0 }">
															 <spring:message code="label.off"/>
															<a type="button" name="select" class="label label-warning"  onclick="isStatusChange('${item.storeId}','${item.isSyncFull}','2')">
																<spring:message code="label.store.on"/>
															</a>
														</c:when>
														<c:when test="${item.isSyncFull == 1 }">
															<spring:message code="label.on"/>
															<a type="button" name="select" class="label label-success"  onclick="isStatusChange('${item.storeId}','${item.isSyncFull}','2')">
																<spring:message code="label.store.off"/>
															</a>
														</c:when>
														<c:otherwise>
															<spring:message code="label.on"/>
															<a type="button" name="select"  class="label label-success" onclick="isStatusChange('${item.storeId}','${item.isSyncFull}','2')">
																<spring:message code="label.store.off"/>
															</a>
														</c:otherwise>
													</c:choose>
												</td>
												<td>${item.contactPerson }</td>
												<td><fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd HH:mm"/></td>
<%-- 												<td>${item.lbsPoiId }</td> --%>
<!-- 												<td> -->
<%-- 													<c:choose> --%>
<%-- 														<c:when test="${item.isLbsInfo == 0 }"> --%>
<!-- 															<span class="label label-warning">未上传</span> -->
<%-- 														</c:when> --%>
<%-- 														<c:when test="${item.isLbsInfo == 1 }"> --%>
<!-- 															<span class="label label-success">已上传</span> -->
<%-- 														</c:when> --%>
<%-- 														<c:otherwise> --%>
<!-- 															<span class="label label-warning">未上传</span> -->
<%-- 														</c:otherwise> --%>
<%-- 													</c:choose> --%>
<!-- 												</td> -->
												<td>
													<div class="btn-group btn-group-xs">
														<pri:view acl="ACL_STORE">
															<a type="button" class="btn btn-default"  data-toggle="modal" data-target="#myModalCheck"
															 onclick="findStoreByStoreId('${item.storeId}')"> <i class="fa fa-search"></i><spring:message code="label.operator.query"/>
															</a>
														</pri:view>
														<pri:or acl="ACL_STORE">
															<button type="button"
																class="btn btn-default dropdown-toggle"
																data-toggle="dropdown">
																<span class="caret"></span> <span class="sr-only">Toggle Dropdown</span>
															</button>
														</pri:or>
														<ul class="dropdown-menu" role="menu">
															<pri:update acl="ACL_STORE">
																<li><a data-toggle="modal" data-target="#myModalModify" onclick="findStoreByStoreIdModify('${item.storeId}')"><i
																	class="fa fa-edit"></i><spring:message code="label.operator.edit"/></a></li>
																<li><a data-toggle="modal" data-target="#myModalModifyPassword" onclick="findUserByStoreIdModify('${item.storeId}')"><i
																	class="fa fa-edit"></i><spring:message code="label.store.resetpsw"/></a></li>
															</pri:update>
															<pri:remove acl="ACL_STORE">
																<li><a data-toggle="modal" data-target="#myModalDelete" onclick="affirmDelete('${item.storeId}')"><i class="fa fa-trash-o"></i><spring:message code="label.operator.delete"/></a></li>
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
<div id="myModalCheck" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	data-keyboard="true" data-backdrop="static">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel"><spring:message code="label.store.modal.check"/></h4>
			</div>

			<div class="modal-body">
				<form class="form-horizontal" role="form" data-toggle="form-validator">
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.brandcode"/></label>
						<div class="col-sm-10">
							<input type="text" placeholder="<spring:message code="label.store.brandcode"/>" id="brandCodeDetail" class="form-control" value=""  disabled/>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.storename"/> </label>
						<div class="col-sm-10">
							<input type="text" placeholder="<spring:message code="label.store.storename"/>" id="storeNameDetail" class="form-control" value=""  disabled/>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.storecode"/></label>
						<div class="col-sm-10">
							<input type="text" placeholder="<spring:message code="label.store.storecode"/>" id="storeCodeDetail" class="form-control" value=""   disabled/>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.user"/></label>
						<div class="col-sm-10">
							<input type="text" placeholder="<spring:message code="label.store.user"/>" id="storeUserDetail" class="form-control" value=""   disabled/>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><spring:message code="label.store.extcode"/></label>
						<div class="col-sm-10">
							<input type="text" placeholder="<spring:message code="label.store.extcode"/>"  id="extCodeDetail" class="form-control" value="" name="extCode" disabled/>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.deliswitch"/></label>
						<div id="deliSwitchDetail" class="col-sm-10">
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.issyncfull"/></label>
						<div id="isSyncFullDetail" class="col-sm-10">
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.modal.province"/></label>
						<div class="col-sm-10">
							<select class="form-control" aria-labelledby="birth-label" id="provinceDetail" runat="server" name="province" disabled>
							</select>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.modal.city"/></label>
						<div class="col-sm-10">
							<select class="form-control" aria-labelledby="birth-label" id="cityDetail" runat="server" name="city" disabled>
							</select>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.modal.district"/></label>
						<div class="col-sm-10">
							<select class="form-control" aria-labelledby="birth-label" id="districtDetail" runat="server" name="district" disabled>
							</select>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.fulladdress"/></label>
						<div class="col-sm-10">
							<input type="text" placeholder="<spring:message code="label.store.fulladdress"/>"  id="addressDetail" class="form-control" value=""   disabled/>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.contactperson"/></label>
						<div class="col-sm-10">
							<input type="text" placeholder="<spring:message code="label.store.contactperson"/>" id="contactPersonDetail" class="form-control" value="" disabled/>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.contactphone"/></label>
						<div class="col-sm-10">
							<input type="text" placeholder="<spring:message code="label.store.contactphone"/>"  id="contactPhoneDetail" class="form-control" value="" disabled/>
							<div class="help-block with-errors"></div>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">< <spring:message code="label.operator.return"/></button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<div id="myModalAdd" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-keyboard="true"  data-toggle="form-validator" data-backdrop="static">
	<div class="modal-dialog modal-lg"  style="height:90%">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel"><spring:message code="label.store.modal.add"/></h4>
			</div>
			<!-- TODO -->

			<div class="modal-body">
				<form class="form-horizontal" role="form" data-toggle="form-validator" id="addForm" method="post" action ="${staticbase}/store/add" >
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.brandcode"/></label>
						<div class="col-sm-10">
							<input type="text" placeholder="<spring:message code="label.store.brandcode"/>" id="brandCodeAdd" name="brandCode" class="form-control" value="" readonly="readonly" />
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.storename"/></label>
						<div class="col-sm-10">
							<input type="text" placeholder="<spring:message code="label.store.storename"/>" id="storeNameAdd" name="storeName" class="form-control" value=""  required/>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.storecode"/></label>
						<div class="col-sm-10">
							<input type="text"  data-custom="uniqueStoreCode" maxlength="10"  minlength="3"  placeholder="<spring:message code="label.store.storecode"/>" id="storeCodeAdd" name="storeCode" class="form-control" value="" onblur="useStoreCodeAsUserName()" onkeyup="this.value=this.value.replace(/^ +| +$/g,'')" required/>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.user"/></label>
						<div class="col-sm-10">
							<input type="text" maxlength="10"  minlength="3"  placeholder="<spring:message code="label.store.user"/>" id="storeUserAdd" name="loginName" class="form-control" value=""  readonly="readonly"/>
							<div class="help-block with-errors"></div>
					</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><spring:message code="label.store.extcode"/></label>
						<div class="col-sm-10">
							<input type="text" placeholder="<spring:message code="label.store.extcode"/>"  class="form-control" value=""  name="extCode"/>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.deliswitch"/></label>
						<div id="deliSwitchAdd" class="col-sm-10">
							<select  class='form-control input-sm filter' name='deliSwitch' >
								<option value='1'><spring:message code="label.store.on"/></option>
								<option value='0'><spring:message code="label.store.off"/></option>
							</select>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.issyncfull"/></label>
						<div id="isSyncFullAdd" class="col-sm-10">
							<select  class='form-control input-sm filter' name='isSyncFull' >
								<option value='1'><spring:message code="label.store.on"/></option>
								<option value='0'><spring:message code="label.store.off"/></option>
							</select>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.modal.province"/></label>
						<div class="col-sm-10">
							<select class="form-control" aria-labelledby="birth-label" id="provinceAdd"  runat="server" name="province" >
							</select>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.modal.city"/></label>
						<div class="col-sm-10">
							<select class="form-control" aria-labelledby="birth-label" id="cityAdd"  runat="server" name="city" >
							</select>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.modal.district"/></label>
						<div class="col-sm-10">
							<select class="form-control" aria-labelledby="birth-label" id="districtAdd"   runat="server" name="district" >
							</select>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.fulladdress"/></label>
						<div class="col-sm-10">
							<input type="text" placeholder="<spring:message code="label.store.fulladdress"/>"  id="addressAdd" name="address"  class="form-control" value=""   required/>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.contactperson"/></label>
						<div class="col-sm-10">
							<input type="text" placeholder="<spring:message code="label.store.contactperson"/>" id="contactPersonAdd" name="contactPerson"  class="form-control" value="" required/>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.contactphone"/></label>
						<div class="col-sm-10">
							<input type="text" placeholder="<spring:message code="label.store.contactphone"/>"  id="contactPhoneAdd" name="contactPhone"  class="form-control" value="" required/>
							<div class="help-block with-errors"></div>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<div class="col-sm-3">
					<button type="button" class="btn btn-default" data-dismiss="modal"  onclick="addFormClear()">< <spring:message code="label.operator.return"/></button>
				</div>
				<div class="col-sm-9">
					<button class="btn  btn-primary pull-right role-save" onclick="addFormSubmit()"><i class="fa fa-floppy-o"></i><spring:message code="label.operator.save"/></button>
				</div>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<div id="myModalModify" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	data-keyboard="true" data-backdrop="static">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel"><spring:message code="label.store.modal.modify"/></h4>
			</div>

			<div class="modal-body">
				<form class="form-horizontal" role="form" data-toggle="form-validator" id="modifyForm" method="post" action ="${staticbase}/store/modify" >
					<input type="hidden" name="storeId" id="storeIdModify" class="form-control" value="" /> 
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.brandcode"/></label>
						<div class="col-sm-10">
							<input type="text" placeholder="<spring:message code="label.store.brandcode"/>" id="brandCodeModify" name="brandCode" class="form-control" value=""  disabled/>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.storename"/></label>
						<div class="col-sm-10">
							<input type="text" placeholder="<spring:message code="label.store.storename"/>" id="storeNameModify" name="storeName" class="form-control" value=""  required/>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.storecode"/></label>
						<div class="col-sm-10">
							<input type="text" placeholder="<spring:message code="label.store.storecode"/>" id="storeCodeModify" name="storeCode" class="form-control" value=""   disabled/>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.user"/></label>
						<div class="col-sm-10">
							<input type="text" placeholder="<spring:message code="label.store.user"/>" id="storeUserModify" name="storeUser" class="form-control" value=""   disabled/>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><spring:message code="label.store.extcode"/></label>
						<div class="col-sm-10">
							<input type="text" placeholder="<spring:message code="label.store.extcode"/>"  id="extCodeModify" name="extCode" class="form-control" value=""  />
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.deliswitch"/></label>
						<div id="deliSwitchModify" class="col-sm-10">
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.issyncfull"/></label>
						<div id="isSyncFullModify" class="col-sm-10">
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.modal.province"/></label>
						<div class="col-sm-10">
							<select class="form-control" aria-labelledby="birth-label" id="provinceModify"  runat="server" name="province" >
							</select>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.modal.city"/></label>
						<div class="col-sm-10">
							<select class="form-control" aria-labelledby="birth-label" id="cityModify"  runat="server" name="city" >
							</select>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.modal.district"/></label>
						<div class="col-sm-10">
							<select class="form-control" aria-labelledby="birth-label" id="districtModify"   runat="server" name="district" >
							</select>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.fulladdress"/></label>
						<div class="col-sm-10">
							<input type="text" placeholder="<spring:message code="label.store.fulladdress"/>"  id="addressModify" name="address"  class="form-control" value=""   required/>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.contactperson"/></label>
						<div class="col-sm-10">
							<input type="text" placeholder="<spring:message code="label.store.contactperson"/>" id="contactPersonModify" name="contactPerson"  class="form-control" value="" required/>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.contactphone"/></label>
						<div class="col-sm-10">
							<input type="text" placeholder="<spring:message code="label.store.contactphone"/>"  id="contactPhoneModify" name="contactPhone"  class="form-control" value="" required/>
							<div class="help-block with-errors"></div>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<div class="col-sm-3">
					<button type="button" class="btn btn-default" data-dismiss="modal">< <spring:message code="label.operator.return"/></button>
				</div>
				<div class="col-sm-9">
					<button class="btn  btn-primary pull-right role-save"  onclick="modifyFormSubmit()"><i class="fa fa-floppy-o"></i><spring:message code="label.operator.save"/></button>
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
				<h4 class="modal-title" id="myModalLabel"><spring:message code="label.store.isdelete"/></h4>
				<form class="form-horizontal" role="form" data-toggle="form-validator"  id="deleteForm" method="post" action="${staticbase}/store/delete">
					<input type="hidden"  name="storeId" id="storeIdDelete" value="" /> 
					<input type="hidden"  name="brandCode" id="brandCodeDelete" value="" /> 
					<input type="hidden"  name="lbsPoiId" id="lbsPoiIdDelete" value="" /> 
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-power-off"></i><spring:message code="tip.user.org.change.no"/></button>
				<button class="btn  btn-primary pull-right role-save" data-dismiss="modal" onclick="deleteFormSubmit()"><i class="fa fa-check"></i><spring:message code="tip.user.org.change.yes"/></button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

<div id="myModalModifyPassword" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	data-keyboard="true" data-backdrop="static">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel"><spring:message code="label.store.resetpsw"/></h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form" data-toggle="form-validator" id="userModifyForm" method="post" action ="${staticbase}/store/resetPassword" >
						<input type="hidden" name="storeId" id="storeIdModifyPassword" class="form-control" value="" /> 
						<div class="form-group">
							<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.user"/></label>
							<div class="col-sm-10">
								<input type="text" placeholder="<spring:message code="label.store.user"/>" id="storeUserLoginNameModify" class="form-control" value=""  disabled/>
								<div class="help-block with-errors"></div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.store.newpsw"/></label>
							<div class="col-sm-10">
								<input type="text"  data-custom="includingLetter" placeholder="<spring:message code="label.store.newpsw"/>" maxlength="30" data-minlength="8" id="storeUserPassword" name="storeUserPassword" class="form-control" value=""  required/>
								<div class="help-block with-errors"></div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
				<div class="col-sm-3">
					<button type="button" class="btn btn-default" data-dismiss="modal" onclick="cleanUserModifyForm()">< <spring:message code="label.operator.return"/></button>
				</div>
				<div class="col-sm-9">
					<button class="btn  btn-primary pull-right role-save"  onclick="modifyUserFormSubmit()"><i class="fa fa-floppy-o"></i><spring:message code="label.operator.save"/></button>
				</div>
			</div>
				
			</div>
		</div>
		
	</div>
	
	<!-- /.modal-dialog -->
<!-- /.modal -->
<div id="myModalBatchUp" class="modal fade modal-import" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-keyboard="true" data-backdrop="static">
  <div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-body">
				<div class="col-md-12">
					<button type="button" class="close" onclick="uploadFormClose()" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="marign-none form-title"><spring:message code="label.store.bulkiaddmport"/></h4>
				</div>
				 <div class="widget-content padding form-horizontal">
					<form class="form-horizontal" role="form" data-toggle="form-validator" id="uploadForm" novalidate="true" action="/store/upload" method="post" enctype="multipart/form-data">
	                	<div class="bck-white-all overflow">
	                		<div class="content-all-padding">
	                			<div class="form-group">
			                        <label class="col-sm-2 control-label" for="inputfile" style="width:auto"><span class="glyphicon glyphicon-asterisk text-red-1"></span> <span> <spring:message code="label.store.upoloadfile"/></span></label>
			                        <div class="col-sm-2" style="width:auto">
			                          <input type="file" name="file"/>
			                          <div id="errorsBatchUp"></div>
			                        </div>
			                        <div class="col-sm-2" style="width:auto">
			                          <a class="btn btn-default btn-xs" href="/store/exceldemo" target="_blank"><spring:message code="label.store.templatedown"/></a>
			                        </div>
			                      </div>		
	                        	 <div class="form-group  col-sm-8">
			                        <label></label>
			                        <div id="tips" style="color: red;font-size: 18px;">
			                        </div>
		                     	 </div>	                     
								<div class="form-group">
									<div class="pull-right">
										<button class="btn btn-primary btn-upload" type="button" onclick="uploadFormSubmit()"><i class="fa fa-upload"></i> <spring:message code="label.store.upoload"/></button>
										&nbsp;&nbsp;
										<button class="btn btn-primary closeBtn" type="button"  onclick="uploadFormClose()" data-dismiss="modal"><i class="fa fa-close"></i> <spring:message code="label.operator.colse"/></button>
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
<script src="${staticbase }/scripts/pages/store/storeList.js?${version}"></script>
<script src="${staticbase }/scripts/pages/store/jquery.cityselect.js?${version}"></script>


<script type="text/javascript">
	var maxPage="${param.pageCount}";
	var curPage="${param.currentPage}";
</script>
</body>
</html>