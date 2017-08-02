<%@include file="/pages/commons/common.jsp" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="label.system.name"/> | <spring:message code="label.user.manager"/></title>
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
		<jsp:param name="pagecode" value="ACL_USER" />
	</jsp:include>
  
	<!-- Start right content -->
	<div class="content-page">
		<!-- 面包屑 -->
	    <ol class="breadcrumb">
			<li><a href="javaScript:void(0);"><spring:message code="label.store.manager"/></a></li>
			<li class="active"><a href="javaScript:void(0);"><spring:message code="label.user.manager"/></a></li>
	    </ol>
	    
<!--主要内容开始部分 START--> 
   
<!-- ============================================================== --> 
<!-- Start Content here --> 
<!-- ============================================================== -->
<div class="content">
	<div class="row">
 		<div class="col-md-4">
        	<h3 class="marign-none"><spring:message code="label.user.manager"/></h3>
        </div>
        <div class="col-md-8">
            <div class="toolbar-btn-action">
            	<pri:add acl="ACL_USER">	
					<a class="btn btn-success btn-add" data-toggle="modal" data-target="#addModal" onclick="addUser()"><i class="fa fa-plus-circle"></i> <spring:message code="label.user.add"/></a>
				</pri:add>
			</div>	
        </div>
	</div>
	<div class="row">
		<div class="col-md-12  portlets">
			<div class="widget">
				<div class="widget-content padding">
					<div class="row">
						<div class="col-md-12">
							<form id="queryForm" method="post" role="form" class="form-horizontal"  data-toggle="form-validator" action="${staticbase}/user/list">
								<input type="hidden" value="${pagination.currentPage}"  name="page" class="startPage">
                                <input type="hidden" name="sort" value="${param.sort }"/>
                   				<input type="hidden" name="sortDirection" value="${param.sortDirection }"/> 
								<div class="table-responsive">
									<table class="table table-hover" data-form="queryForm" data-sortable data-sortable-type="server" 
											data-page="${pagination.currentPage}" data-page-count="${pagination.totalPages}">
										<thead>
											<tr class="sort-header">
												<th data-sortable="false" width="12%"><spring:message code="label.user.loginname"/></th>
												<th data-sortable="false" width="12%"><spring:message code="label.user.username"/></th>
												<th data-sortable="false" width="12%"><spring:message code="label.user.storename"/></th>
												<th data-sortable="false" width="12%"><spring:message code="label.user.usertype"/></th>
												<th data-sortable="false" width="12%"><spring:message code="label.user.userstatus"/></th>
												<th data-sortable="false" width="2%"><spring:message code="label.operator"/></th>
											</tr>
											<tr class="filter-header">
												<th>
													<input type="text" name="q_sl_loginName" value="${param.q_sl_loginName }" class="form-control input-sm filter" style="width:150px">
												</th>
												<th>
													<input type="text" name="q_sl_userName" value="${param.q_sl_userName }" class="form-control input-sm filter" style="width:150px">
												</th>
												<th>
													<input type="text" name="q_sl_storeName" value="${param.q_sl_storeName }" class="form-control input-sm filter" style="width:150px">
												</th>
												<th>
													<select id="q_int_userType" name="q_int_userType" class="form-control input-sm filter" style="width:150px">
														<option value="2">-</option>
														<option value="0" <c:if test="${param.q_int_userType == 0}">selected</c:if>><spring:message code="label.user.usertype0"/></option>
														<option value="1" <c:if test="${param.q_int_userType == 1}">selected</c:if>><spring:message code="label.user.usertype1"/></option>
													</select>
												</th>
												<th>
													<select id="q_int_userStatus" name="q_int_userStatus" class="form-control input-sm filter" style="width:150px">
														<option value="3">-</option>
														<option value="1" <c:if test="${param.q_int_userStatus == 1}">selected</c:if>><spring:message code="label.on"/></option>
														<option value="2" <c:if test="${param.q_int_userStatus == 2}">selected</c:if>><spring:message code="label.off"/></option>
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
												<td>${item.loginName }</td>
												<td>${item.userName }</td>
												<td>${item.storeName }</td>
												<c:if test="${item.userType == 0 }">
													<td><spring:message code="label.user.usertype0"/></td>
												</c:if>
												<c:if test="${item.userType == 1 }">
													<td><spring:message code="label.user.usertype1"/></td>
												</c:if>
												<td id="user${item.userId }">
													<c:if test="${item.userStatus == 1 && item.userType == 0 }">
														<spring:message code="label.on"/>
													</c:if>
													<c:if test="${item.userStatus == 1 && item.userType == 1 }">
														<spring:message code="label.on"/>&nbsp;
														<a type="button" name="select" class="label label-success"  onclick="changeUserStatus('${item.userId}','2')"><spring:message code="label.store.off"/></a>
													</c:if>
													<c:if test="${item.userStatus == 2 && item.userType == 1 }">
														<spring:message code="label.off"/>&nbsp;
														<a type="button" name="select" class="label label-warning"  onclick="changeUserStatus('${item.userId}','1')"><spring:message code="label.store.on"/></a>
													</c:if>
												</td>
												<td>													
													<div class="btn-group btn-group-xs">
														<pri:update acl="ACL_USER">
															<a type="button" data-toggle="modal" data-target="#modifyModal" onclick="findUserById(${item.userId})" class="btn btn-default" href="javaScript:void(0);">
	                                                        	<i class="fa fa-search"></i><spring:message code="label.user.modify"/>
	                                                       	</a>
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

<!-- 弹出窗体部分 START -->
<div id="modifyModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-keyboard="true" data-backdrop="static">
    
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel"><spring:message code="label.user.modify"/></h4>
            </div>
            			<div class="modal-body">
				<form class="form-horizontal" role="form" data-toggle="form-validator" action="${staticbase}/user/modify" id="modifyForm" method="post">
				<input type="hidden" id="userIdModify" name="userId">
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.user.loginname"/></label>
						<div class="col-sm-10">
							<input type="text" placeholder="<spring:message code="label.user.loginname"/>" id="loginNameModify" class="form-control" value=""  disabled/>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.user.username"/></label>
						<div class="col-sm-10">
							<input type="text" placeholder="<spring:message code="label.user.username"/>" id="userNameModify" class="form-control" value=""  disabled/>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.user.storename"></spring:message></label>
						<div class="col-sm-10">
							<input type="text" placeholder="<spring:message code="label.user.storename"/>" id="storeNameModify" class="form-control" value=""   disabled/>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.user.password"/></label>
						<div class="col-sm-10">
							<input type="password"  data-custom="onlyNumberOrLetter" placeholder="<spring:message code="label.user.password"/>" id="passwordModify" maxlength="18" data-minlength="8" name="password" class="form-control" value="" required="required"/>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.user.confirmpassword"/></label>
						<div class="col-sm-10">
							<input type="password"  data-custom="confirmPassword" placeholder="<spring:message code="label.user.confirmpassword"/>"  id="passwordModifyConfirm" class="form-control" value="" required="required"/>
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
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div id="addModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-keyboard="true" data-backdrop="static">
    
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel"><spring:message code="label.user.add"></spring:message></h4>
            </div>
            			<div class="modal-body">
				<form class="form-horizontal" role="form" data-toggle="form-validator" action="${staticbase}/user/add" id="addForm" method="post">
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.user.loginname"></spring:message></label>
						<div class="col-sm-10">
							<input type="text" placeholder="<spring:message code="label.user.loginname"></spring:message>" id="loginNameAdd" name="loginName" class="form-control" value="" data-custom="onlyNumberOrLetter uniqueUser" required="required"/>
							<div id='errorDiv' class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.user.username"></spring:message></label>
						<div class="col-sm-10"> 
							<input type="text" placeholder="<spring:message code="label.user.username"></spring:message>" name="userName" class="form-control" value="" required="required"/>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="form-group">
   						<label class="col-sm-2 control-label" for="state"><font color="red">*</font><spring:message code="label.user.storename"></spring:message></label>
   							<div class="col-sm-3">
     							<select name="storeCode" id="storeSelect" class="selectPicker">
    							</select>
    							<div class="help-block with-errors"></div>
  							</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" ><font color="red">*</font><spring:message code="label.user.password"></spring:message></label>
						<div class="col-sm-10">
							<input type="password" placeholder="<spring:message code="label.user.password"></spring:message>" name="password" data-custom="onlyNumberOrLetter" class="form-control" value="" maxlength="18" data-minlength="8" required="required"/>
							<div class="help-block with-errors"></div>
						</div>
					</div>
				</form>
			</div>

           <div class="modal-footer">
				<div class="col-sm-3">
					<button type="button" class="btn btn-default" data-dismiss="modal" onclick="addFormClear()">< <spring:message code="label.operator.return"></spring:message></button>
				</div>
				<div class="col-sm-9">
					<button class="btn  btn-primary pull-right role-save"  onclick="addFormSubmit()"><i class="fa fa-floppy-o"></i><spring:message code="label.operator.save"></spring:message></button>
				</div>
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
<script src="${staticbase }/scripts/pages/store/userList.js?${version}"></script>

<script type="text/javascript">
	var maxPage="${param.pageCount}";
	var curPage="${param.currentPage}";
</script>
</body>
</html>