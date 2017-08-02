<%@include file="/pages/commons/common.jsp" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<title>文件服务 | 文件上传</title>
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
			<li class="active"><a href="#">文件上传</a></li>
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
									<h3 class="marign-none">文件上传测试</h3>
								</div>
								<div class="col-md-8">
									<div class="toolbar-btn-action">
									</div>
								</div> 
							</div>
							<div class="widget widget-content padding">
								<div class="row">
									<div class="col-md-12"> 
										<form class="form-horizontal" role="form" data-toggle="form-validator" method="POST" action="${pagebase}/file/upload" novalidate="true" enctype="multipart/form-data">
              						  	<div class="content-all-padding">
              						  	 <div class="form-group">
						                        <label class="col-sm-2 control-label" for="appKey">AppKey</label>
						                         <div class="col-sm-10">
						                         	<input type="text" placeholder="" name="appKey" maxlength="128" class="form-control" required>
						                        </div>
					                      </div>
					                      <div class="form-group">
						                        <label class="col-sm-2 control-label" for="secret">secret</label>
						                         <div class="col-sm-10">
						                         	<input type="text" placeholder="" name="secret" maxlength="128" class="form-control" required>
						                        </div>
					                      </div>
					                      	
											<div class="form-group">
									        <label class="col-sm-2 control-label" for="body">上传的文件</label>
									        <div class="col-sm-10">
									        	<input type="file" name="file" maxlength="128" class="form-control" required>
									        </div>
											</div>
											
											
											<div class="form-group">
						                        <label class="col-sm-2 control-label" for="ext">后缀名</label>
						                         <div class="col-sm-10">
						                         	<input type="text" placeholder="" name="ext" maxlength="128" class="form-control" required>
						                        </div>
					                      </div>
											
											<div class="form-group">
								                <label class="col-sm-2 control-label">文件类型</label>
								                <input type="hidden" id="timeDiv"/>
								                <div class="col-sm-10">
													<select  class="form-control time-type"  name="type" required>
														<option value="1">公共</option>
														<option value="2">私有</option>
													</select>
								                          
												</div>
								              </div>
								              
								              
              						  </div>
		                            </form>
		                            <div class="content-all-padding">
		                           <c:if test="${ not empty data}">
										<div class="form-group">
						                        <label class="col-sm-2 control-label" >访问地址</label>
						                         <div class="col-sm-10">
						                         	<input type="text" placeholder="" value="${data}" maxlength="128" class="form-control" required>
						                        </div>
					                      </div>
					                      
					                      <div class="form-group">
						                        <label class="col-sm-2 control-label" >编码</label>
						                         <div class="col-sm-10">
						                         	<input type="text" placeholder="" value="${data}" maxlength="128" class="form-control" required>
						                        </div>
					                      </div>
							
							
							</c:if>  
							</div>
		                            
									</div>
								</div>
							</div>
							
							
							
							
							<div class="row page-title-1">
								<div class="col-sm-4">
								</div>
								<div class="col-sm-8">
									<div class="toolbar-btn-action" > 
										<a href="javascript:void(0);" class="btn btn-success pull-right" id="sendBut" title="测试发送短信！"><i class="fa fa-plus-circle"  title="测试发送短信！"></i> 上传</a>
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