<%@include file="/pages/commons/common.jsp" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="label.system.name"/> | <spring:message code="label.brand.manager"/></title>
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
		<jsp:param name="pagecode" value="ACL_BRAND_I" />
	</jsp:include>
  
	<!-- Start right content -->
	<div class="content-page">
		<!-- 面包屑 -->
	    <ol class="breadcrumb">
			<li><a href="javaScript:void(0);"><spring:message code="label.brand.manager"/></a></li>
			<li class="active"><a href="javaScript:void(0);"><spring:message code="label.brand.invconfig"/></a></li>
	    </ol>
	    
<!--主要内容开始部分 START--> 
   
<!-- ============================================================== --> 
<!-- Start Content here --> 
<!-- ============================================================== -->
		<div class="content"> 
	     <!--主要内容开始部分 START-->
				<h3><spring:message code="label.brand.invconfig"/></h3>
				<div class="row">
				<div class="col-md-12 portlets">
					<div class="widget padding">
						<div class="widget-content padding">
							<form action="${staticbase}/brand/sub/update" class="form-horizontal" method="post" id="brandForm" role="form" data-toggle="form-validator">
								<div class="form-group">
									<label class="col-sm-2 control-label" for="brandCode"><span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.brandcode"/></label>
									<div class="col-sm-4">
										<input name="brandCode" value="${webSubBrandConfigCommand.brandCode}" class="form-control" readonly/>										
									</div>									
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="subCode"><span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.subcode"/></label>
									<div class="col-sm-4">
										<input name="subCode" id="subCode" type="text" value="${webSubBrandConfigCommand.subCode}" class="form-control" readonly/>										
									</div>									
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="subName"><span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.subname"/></label>
									<div class="col-sm-4">
										<input name="subName" id="subName" type="text" value="${webSubBrandConfigCommand.subName}" readonly class="form-control"/>										
									</div>									
								</div>
								<hr/>
								<div class="form-group">
									<label class="col-sm-2 control-label">
										<span class="glyphicon glyphicon-asterisk text-red-1"></span>
										<spring:message code="label.brand.inventoryconfig"/>
									</label>									
								</div>												
								<div class="row">
									<div class="col-sm-2"></div>
									<div class="col-sm-8">
										<table class="table table-bordered">
										    <thead>
											    <tr>
											        <th class="text-center col-sm-4"><spring:message code="label.brand.inventoryconfig.down"/></th>
												    <th class="text-center col-sm-4"><spring:message code="label.brand.inventoryconfig.up"/></th>
												    <th class="text-center col-sm-4"><spring:message code="label.brand.inventoryconfig.scale"/></th>
											    </tr>
											</thead>
											<tbody>
											    <tr>
											        <td class="text-center col-sm-4">0</td>
											        <td class="text-center col-sm-4">
											        	<div class="row">
											        		<div class="col-sm-12">
											        			<div class="col-sm-1"></div>
												        		<div class="form-group col-sm-10">											        	
												        			<input type="text" class="form-control" value="${webSubBrandConfigCommand.invRangePre}" id="invRangePreW" data-custom="invRangePre  positiveInteger"  value="" required>
												        			<div class="help-block with-errors"></div>
												        		</div>
											        		</div>
											        	</div>											        	
											        </td>
											        <td class="text-center col-sm-4">
											        	<div class="row">
											        		<div class="col-sm-12">
											        			<div class="col-sm-1"></div>
												        		<div class="form-group col-sm-10">
																	<div class="input-group">											      
															      		<input class="form-control" id="invPercentPre" value="${webSubBrandConfigCommand.invPercentPre}" type="text" data-numberex="integer" data-min="0" data-max="100" required>
															      		<div class="input-group-addon">%</div>
															    	</div>
															    	<div class="help-block with-errors"></div>
												        		</div>
											        		</div>
											        	</div>	
													</td>        
											    </tr>
												<tr>
											        <td class="text-center col-sm-4">
											        	<input  type="text" class="form-control" id="invRangePreR"  value="${webSubBrandConfigCommand.invRangePre}" disabled>
											        </td>
											        <td class="text-center col-sm-4">
											        	<div class="row">
											        		<div class="col-sm-12">
											        			<div class="col-sm-1"></div>
												        		<div class="form-group col-sm-10">	
											        				<input type="text" class="form-control" id="invRangeSuffixW" value="${webSubBrandConfigCommand.invRangeSuffix}" data-custom="invRangeSuffix  positiveInteger" value=""  required>
											        				<div class="help-block with-errors"></div>
												        		</div>
											        		</div>
											        	</div>				
											        </td>
											        <td class="text-center col-sm-4">
											        	<div class="row">
											        		<div class="col-sm-12">
											        			<div class="col-sm-1"></div>
													        		<div class="form-group col-sm-10">
															        	<div class="input-group">											      
																      		<input class="form-control" id="invPercentMid" value="${webSubBrandConfigCommand.invPercentMid}" type="text" data-numberex="integer" data-min="0" data-max="100" required>
																      		<div class="input-group-addon">%</div>
																    	</div>
													    			<div class="help-block with-errors"></div>
												        		</div>
											        		</div>
											        	</div>	
											        </td>        
											    </tr>
												<tr>
											        <td class="text-center col-sm-4">
											        	<input  type="text" class="form-control" id="invRangeSuffixR" value="${webSubBrandConfigCommand.invRangeSuffix}" disabled>
											        </td>
											        <td class="text-center col-sm-4">∞</td>
											        <td class="text-center col-sm-4">
												        <div class="row">
											        		<div class="col-sm-12">
											        			<div class="col-sm-1"></div>
												        		<div class="form-group col-sm-10">
														        	<div class="input-group">											      
															      		<input class="form-control" id="invPercentSuffix" value="${webSubBrandConfigCommand.invPercentSuffix}" data-numberex="integer" data-min="0" data-max="100" type="text" required>
															      		<div class="input-group-addon">%</div>
															    	</div>
											    					<div class="help-block with-errors"></div>
											        			</div>
											        		</div>
											        	</div>	
												    </td>        
											    </tr>
										   </tbody>   
										</table>
									</div>										
									<div class="col-sm-1 form-icon">
										<i class="icon-help-circled" title="<spring:message code="label.brand.inventoryconfig.discription"/>"></i>
									</div>								
								</div>
								<input type="hidden" name="id" value="${webSubBrandConfigCommand.id}"/>
								<input type="hidden" name="invRange" value="${webSubBrandConfigCommand.invRange}" id="invRange"/>
								<input type="hidden" name="invPercent" value="${webSubBrandConfigCommand.invPercent}" id="invPercent"/>
							</form>
							<div class="row">
								<div class="col-md-12 border-top-solid savepadding-rig-style mg-top10 mg-bottom20">
									<button class="btn  btn-primary pull-right role-save" id="brandSubmit" type="submit"><i class="fa fa-save"></i><spring:message code="label.operator.save"/></button>
									<a class="btn btn-default" href="${staticbase}/brand/sub/list"><i class="icon-left-open"></i><spring:message code="label.operator.return"/></a>
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
<script src="${staticbase }/scripts/pages/brand/subBrandUpdate.js?${version}"></script>

</body>
</html>