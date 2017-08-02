<%@include file="/pages/commons/common.jsp" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<title>文件服务 | 文件删除</title>
	<%@include file="/pages/commons/common-meta.jsp" %>
	<%@include file="/pages/commons/common-css.jsp" %>
	<%@include file="/pages/commons/common-compatible.jsp" %>
	<%@include file="/pages/commons/common-icon.jsp" %>
</head>
<body class="fixed-left" locale="zh-cn">
	<%@include file="/pages/commons/common-layer.jsp" %>
	<!-- Begin page -->
	<div id="wrapper"> 
		<%@include file="/pages/commons/common-top.jsp" %>
		<jsp:include page="/pages/commons/common-left.jsp">
		<jsp:param name="pagecode" value="" />
		</jsp:include>
		<!-- Start right content -->
		<div class="content-page">
			<ol class="breadcrumb">
			<li class="active"><a href="#">通知服务</a></li>
			</ol>
			<!-- ============================================================== --> 
			<!-- Start Content here --> 
			<!-- ============================================================== -->
			<div class="content"> 
				<!--主要内容开始部分 START-->
				<div class="row">
					<div class="col-md-12  portlets">
						<div class="widget-newall">
							<div class="row">
								<div class="col-md-4">
									<h3 class="marign-none">文件删除</h3>
								</div>
								<div class="col-md-8">
									<div class="toolbar-btn-action">
									</div>
								</div> 
							</div>
							<div class="widget widget-content padding">
								<div class="row">
									<div class="col-md-12"> 
										<form class="form-horizontal" role="form" data-toggle="form-validator" method="POST" action="${pagebase}/file/remove" novalidate="true" enctype="multipart/form-data">
              						  	<div class="content-all-padding">
              						  		<div class="form-group">
						                        <label class="col-sm-2 control-label" for="appKey">AppKey</label>
						                         <div class="col-sm-10">
						                         	<input type="text" placeholder="" name="appKey" maxlength="128" class="form-control" >
						                        </div>
					                      </div>
					                      
					                      <div class="form-group">
						                        <label class="col-sm-2 control-label" for="urls">url</label>
						                         <div class="col-sm-10">
						                         	<input type="text" placeholder="" name="urls" maxlength="5000" class="form-control" >
						                        </div>
					                      </div>
              						  </div>
		                            </form>
									</div>
								</div>
							</div>
							<div class="row page-title-1">
								<div class="col-sm-4">
								</div>
								<div class="col-sm-8">
									<div class="toolbar-btn-action" > 
										<a href="javascript:void(0);" class="btn btn-success pull-right" id="sendBut" title="确定删除！"><i class="fa fa-plus-circle"  title="确定删除！"></i> 删除</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--主要内容开始部分 END--> 
			<%@include file="/pages/commons/common-footer.jsp" %>
		</div>
		<!-- ============================================================== --> 
		<!-- End content here --> 
		<!-- ============================================================== --> 
	</div>
	<!-- End of page --> 
	<!-- the overlay modal element -->
	<%@include file="/pages/commons/common-modal-footer.jsp" %>
	<!-- End of eoverlay modal --> 
	<script>
		var resizefunc = [];
	</script> 
	<%@include file="/pages/commons/common-script.jsp" %>

	<!-- Page Specific JS Libraries --> 
	<script src="${staticbase }/scripts/pages/notification/email/index.js?${version}"></script>

	<script type="text/javascript">
	$(function(){
		$("#sendBut").on("click",function() {
			$(".form-horizontal").submit();
		});
		
	});
	</script>
</body>
</html>