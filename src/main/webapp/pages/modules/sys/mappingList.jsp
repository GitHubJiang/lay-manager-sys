<%@include file="/pages/commons/common.jsp" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>驻店宝 | Mapping</title>
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
			<li><a href="#">系统管理</a></li>
			<li class="active"><a href="#">订单Mapping状态处理</a></li>
	    </ol>
	    
<!--主要内容开始部分 START--> 
   
<!-- ============================================================== --> 
<!-- Start Content here --> 
<!-- ============================================================== -->
<div class="content">
	<h3>订单Mapping状态处理</h3>

	<div class="row">
		<div class="col-md-12  portlets">
			<div class="widget">
				<div class="widget-content padding">
					<div class="row">
						<div class="col-md-12">
							<form id="queryForm" method="post" role="form" action="${staticbase}/sys/mapping/list">
								<input type="hidden" value="${pagination.currentPage}"  name="page" class="startPage">
                                <input type="hidden" name="sort" value="${param.sort }"/>
                   				<input type="hidden" name="sortDirection" value="${param.sortDirection }"/>
								<div class="table-responsive">
									<table class="table table-hover" data-form="queryForm" data-sortable data-sortable-type="server" 
											data-page="${pagination.currentPage}" data-page-count="${pagination.totalPages}" 
											data-count="${pagination.count}">
										<thead>
											<tr class="sort-header">
												<th data-batch data-extra-batch="tableExtraBatch" data-sortable="false"></th>
												<th data-sortable="false" >品牌</th>
												<th data-sortable="false" >订单号</th>
												<th data-sortable="false" >类型</th>
												<th data-sortable="false" >状态</th>
												<th data-sortable="false" >创建时间</th>
                                                <th data-sortable="false" >处理时间</th>
                                                <th data-sortable="false" width="10%">备注</th>
												<th data-sortable="false" width="8%">操作</th>
											</tr>
											<tr class="filter-header">
												<th></th>
												<th>
													<input type="text" name="q_string_brandCode" value="${param.q_string_brandCode }" class="form-control input-sm filter">
												</th>
												<th>
													<input type="text" name="q_string_keyCode" value="${param.q_string_keyCode }" class="form-control input-sm filter">
												</th>
												<th>
													<select name="q_string_type" class="form-control input-sm filter">
														<option value="">-</option>
														<option value="O_DELI" <c:if test="${param.q_string_type == 'O_DELI' }">selected</c:if>>O_DELI</option>
														<option value="O_OUTBOUND" <c:if test="${param.q_string_type == 'O_OUTBOUND' }">selected</c:if>>O_OUTBOUND</option>
													</select>
												</th>
												<th>
													<select name="q_string_processStatus" class="form-control input-sm filter">
														<option value="">-</option>
														<option value="0" <c:if test="${param.q_string_processStatus == 0 }">selected</c:if>>待处理</option>
														<option value="2" <c:if test="${param.q_string_processStatus == 2 }">selected</c:if>>异常</option>
														<option value="1" <c:if test="${param.q_string_processStatus == 1 }">selected</c:if>>已处理</option>
													</select>
												</th>
												<th>
	                                                <div class="input-group datetime" data-min="2016-01-01" data-max="2017-12-31">
	                                                    <input type="text" name="q_time_createTime" value="${param.q_time_createTime }" class="form-control"/>
	                                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
	                                                </div>
                                                </th>
                                                <th>
                                                    <div class="input-group datetime" data-min="2016-01-01" data-max="2017-12-31">
                                                        <input type="text" name="q_time_processTime" value="${param.q_time_processTime }" class="form-control"/>
                                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                                    </div>
                                                </th>
                                                <th></th>
												<th>
													<button id="queryFormBtn" class="btn btn-primary btn-sm" name="" type="button"><i class="icon-search"></i> 搜索</button>
												</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pagination.items }" var="item">
											<tr id="1">
												<td><input data-itemid="${item.id }" type="checkbox" class="rows-check"></td>
												<td>${item.brandCode }</td>
												<td>${item.keyCode }</td>
												<td>${item.type }</td>
												<td>
												<c:choose>
													<c:when test="${item.processStatus == 0 }">
														<span class="label label-default">待处理</span>
													</c:when>
													<c:when test="${item.processStatus == 2 }">
														<span class="label label-warning">异常</span>
													</c:when>
													<c:otherwise>
														<span class="label label-success">已处理</span>
													</c:otherwise>
												</c:choose>
												</td>
												<td><fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd HH:mm"/></td>
                                                <td><fmt:formatDate value="${item.processTime }" pattern="yyyy-MM-dd HH:mm"/></td>
                                                <td>${item.remark }</td>
												<td>
													
													<div class="btn-group btn-group-xs">
														<a type="button" class="btn btn-default" href="mapping_set.html">
                                                        	<i class="fa fa-cloud-upload"></i>重推
                                                       	</a>
                                                       	<pri:update acl="ACL_MAPPING">
															<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
																<span class="caret"></span>
																<span class="sr-only">Toggle Dropdown</span>
															</button>
															<ul class="dropdown-menu" role="menu">
	                                                        	<li><a data-toggle="modal" data-target="#myModal" onclick="findMappingById(${item.id})"><i class="fa fa-edit"></i>编辑</a></li>
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

<!-- 弹出窗体部分 START -->
<div id="myModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-keyboard="true" data-backdrop="static">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">

            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">对话框标题</h4>
            </div>

            <div class="modal-body">
				<form class="form-horizontal" role="form" data-toggle="form-validator">
					<div class="form-group">
						<label class="col-sm-2 control-label" for="input-text">品牌</label>
						<div class="col-sm-10">
							<select id="select1" class="ms-select filter">
								<option value="1">蕾蕾</option>
								<option value="2">虹虹</option>
								<option value="3">雯雯</option>
								<option value="4">龙龙</option>
								<option value="5">辉辉</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">订单号</label>
						<div class="col-sm-8">
							<input id="keyCodeDetail" type="text" class="form-control" data-custom="uniqueCode"/>
							<div class="help-block with-errors"></div>
						</div>
						<div class="col-sm-1 form-icon">
							<i class="icon-help-circled" title="订单推送到门店后的超时时间：10:00-18:00的订单，超时时间=推送时间+配置时间；&#13;18:00以后的订单，超时时间=第二天10:00+配置时间"></i>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" >类型</label>
						<div class="col-sm-10">
							<input id="typeDetail" type="text" class="form-control"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">状态</label>
						<div id="processStatusDetail" class="col-sm-10">
							<span class="label label-success">正常</span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">内容</label>
						<div class="col-sm-10">
							<pre id="contentDetail"></pre>
						</div>
					</div>
				</form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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
<script src="${staticbase }/scripts/pages/sys/mappingList.js?${version}"></script>

<script type="text/javascript">
	var maxPage="${param.pageCount}";
	var curPage="${param.currentPage}";
</script>
</body>
</html>