<%@include file="/pages/commons/common.jsp" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>格林斯顿|组织管理</title>
<%@include file="/pages/commons/common-meta.jsp" %>
<%@include file="/pages/commons/common-css.jsp" %>
<%@include file="/pages/commons/common-compatible.jsp" %>
<%@include file="/pages/commons/common-icon.jsp" %>
<%@include file="/pages/commons/common-script.jsp" %>

</head>
<body class="fixed-left" locale="${locale }" onload="prettyPrint()">

<div class="md-modal md-3d-slit" id="delete-modal">
	<form id="deleteForm" action="${staticbase}/auth/pri/del">	
		<input name="id" id="acl-id" type="hidden" value="">	
		<div class="md-content" style="height:120px;">			
			<div>
				<p class="text-center">你确定删除么?</p>
				<p class="text-center">
					<a class="btn btn-default md-close">取消</a>
					<a href="javascript:void(0)" id="deleteBtn" class="btn btn-danger btn-lifecycle md-close">确认删除</a>
				</p>
			</div>   
		</div>
	</form>
</div>

<%@include file="/pages/commons/common-layer.jsp" %>
<!-- Begin page -->
<div id="wrapper"> 
  
	<%@include file="/pages/commons/common-top.jsp" %>
  
	<jsp:include page="/pages/commons/common-left.jsp">
		<jsp:param name="pagecode" value="ACL_AUTH_OPERATION_UNIT" />
	</jsp:include>
  
	<!-- Start right content -->
	<div class="content-page">
		<!-- 面包屑 -->
	    <ol class="breadcrumb">
			<li><a href="javaScript:void(0);">权限管理</a></li>
			<li class="active"><a href="javaScript:void(0);">组织管理</a></li>
	    </ol>
	    
<!--主要内容开始部分 START-->     
<!-- ============================================================== --> 
<!-- Start Content here --> 
<!-- ============================================================== -->
    		<div class="content"> 
    	     <!--主要内容开始部分 START-->
    				<h3>新增</h3>
    				<div class="row">
    				<div class="col-md-12 portlets">
    					<div class="widget padding">
    						<div class="widget-content padding">
    							<form action="${staticbase}/org/add" class="form-horizontal" method="post" id="orgForm" role="form" data-toggle="form-validator">
    								<div class="form-group">
    									<label class="col-sm-2 control-label" for="brandCode"><span class="glyphicon glyphicon-asterisk text-red-1"></span>上级组织</label>
    									<div class="col-sm-4">
    										<sys:treeselect id="parentId" name="parentId" value="${area.parent.id}" labelName="parent.name" labelValue="${area.parent.name}"
												title="上级组织" url="/auth/org/treeData" extId="${area.id}" cssClass="" allowClear="true"/>
    										<div class="help-block with-errors"></div>
    									</div>									
    								</div>
    								<div class="form-group">
    									<label class="col-sm-2 control-label" for="unitCode"><span class="glyphicon glyphicon-asterisk text-red-1"></span>组织编码</label>
    									<div class="col-sm-4">
    										<input placeholder="组织编码" data-custom="uniqueCode" maxlength="50" id="unitCode" name="code" type="text" class="form-control" required/>
    										<div class="help-block with-errors"></div>
    									</div>									
    								</div>
    								<div class="form-group">
    									<label class="col-sm-2 control-label" for="unitName">
    										<span class="glyphicon glyphicon-asterisk text-red-1"></span>组织名称
    									</label>
    									<div class="col-sm-4">
    										<input placeholder="组织名称"  maxlength="50" id="unitName" name="ouTypeId" type="text" class="form-control" required/>
    										<div class="help-block with-errors"></div>
    									</div>
    								</div>	 
    								<div class="form-group">
    									<label class="col-sm-2 control-label">
    										<span class="glyphicon glyphicon-asterisk text-red-1"></span>组织类型
    									</label>
    									<div class="col-sm-4">
    										<select id="unitType"></select>
    										<div class="help-block with-errors"></div>
    									</div>
    								</div>  
    							</form>
    							<div class="row">
    								<div class="col-md-12 border-top-solid savepadding-rig-style mg-top10 mg-bottom20">
    									<button class="btn  btn-primary pull-right role-save" id="brandSubmit" type="submit"><i class="fa fa-save"></i> 保存</button>
    									<a class="btn btn-default" href="${staticbase}/brand/list"><i class="icon-left-open"></i>返回</a>
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

<script src="${staticbase }/scripts/pages/auth/org/add.js?${version}"></script>
</body>
</html>
