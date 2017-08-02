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
			<li><a href="${staticbase}/appu/pad/list"><spring:message code="label.appu.ipadupdate"></spring:message></a></li>
			<li class="active"><a href="#"><spring:message code="label.appu.addversion"></spring:message></a></li>
	    </ol>
	    
<!--主要内容开始部分 START--> 
   
<!-- ============================================================== --> 
<!-- Start Content here --> 
<!-- ============================================================== -->
<div class="content">
	<h3><spring:message code="label.appu.updateversion"></spring:message></h3>

	<div class="row">
		<div class="col-md-12  portlets">
			<div class="widget">
				<div class="widget-content padding">
					<div class="row">
						<div class="col-md-12">
							<form class="form-horizontal" role="form" data-toggle="form-validator"
									method="post" action="${staticbase}/appu/update">
								<input name="id" type="hidden" value="${item.id }" />
								<div class="form-group">
									<label class="col-sm-2 control-label" for="brandCode">
										<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.appu.brandcode"></spring:message>
									</label>
									<div class="col-sm-6">
										<input id="brandCode" name="brandCode" type="text" class="form-control" value="${item.brandCode }" readonly="readonly"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="versionNo">
										<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.appu.version"></spring:message>
									</label>
									<div class="col-sm-6">
										<input id="versionNo" name="versionNo" type="text" class="form-control" value="${item.versionNo }" readonly="readonly"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="fileName">
										<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.appu.upload"></spring:message>
									</label>
									<div class="col-sm-6">
										<input id="fileName" name="fileName" type="text" class="form-control" value="${item.fileName }" readonly="readonly"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" id="updateStrategy">
										<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.appu.isforceupdate"></spring:message>
									</label>
									<div class="col-sm-6">
										<input id="updateStrategy" name="updateStrategy" type="text" class="form-control" disabled="disabled"
											value="<c:choose><c:when test='${item.updateStrategy == 1 }'>是</c:when><c:otherwise>否</c:otherwise></c:choose>" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="message">
										<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.appu.updates_zhcn"></spring:message>
									</label>
									<div class="col-sm-6">
										<input id="message" name="message" value="${item.message }" type="text" class="form-control" required/>
										<div class="help-block with-errors"></div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="messageTW">
										<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.appu.updates_zhtw"></spring:message>
									</label>
									<div class="col-sm-6">
										<input id="messageTW" name="messageTW" value="${item.messageTW }" type="text" class="form-control" required/>
										<div class="help-block with-errors"></div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="messageEn">
										<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.appu.updates_en"></spring:message>
									</label>
									<div class="col-sm-6">
										<input id="messageEn" name="messageEn" value="${item.messageEn }" type="text" class="form-control" required/>
										<div class="help-block with-errors"></div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-12 border-top-solid savepadding-rig-style mg-top10 mg-bottom20">
										<button class="btn  btn-primary pull-right role-save" id="brandSubmit" type="submit"><i class="fa fa-save"></i><spring:message code="label.operator.save"></spring:message></button>
										<a class="btn btn-default" href="${staticbase}/appu/pad/list"><i class="icon-left-open"></i><spring:message code="label.operator.return"></spring:message></a>
									</div>
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
<script src="${staticbase }/scripts/pages/appu/ipadUpdate.js?${version}"></script>

<script type="text/javascript">
	var maxPage="${param.pageCount}";
	var curPage="${param.currentPage}";
</script>
</body>
</html>