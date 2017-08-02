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
		<jsp:param name="pagecode" value="ACL_BRAND_Y" />
	</jsp:include>
  
	<!-- Start right content -->
	<div class="content-page">
		<!-- 面包屑 -->
	    <ol class="breadcrumb">
			<li><a href="javaScript:void(0);"><spring:message code="label.brand.manager"/></a></li>
			<li class="active"><a href="javaScript:void(0);"><spring:message code="label.brand.edit"/></a></li>
	    </ol>
	    
<!--主要内容开始部分 START--> 
   
<!-- ============================================================== --> 
<!-- Start Content here --> 
<!-- ============================================================== -->
		<div class="content"> 
	     <!--主要内容开始部分 START-->
				<h3><spring:message code="label.brand.edit"/></h3>
				<div class="row">
				<div class="col-md-12 portlets">
					<div class="widget padding">
						<div class="widget-content padding">
							<form action="${staticbase}/brand/y/update" class="form-horizontal" method="post" id="brandForm" role="form" data-toggle="form-validator">
								<div class="form-group">
									<label class="col-sm-2 control-label" for="brandCode"><span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.brandcode"/></label>
									<div class="col-sm-4">
										<input name="brandCode" value="${webBrandConfigCommand.brandCode}" class="form-control" readonly/>										
									</div>									
								</div>
								<hr/>								
								<div class="form-group">
									<label class="col-sm-2 control-label" id="isDeli-lable">
										<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.isdeli"/>
									</label>
									<div class="col-sm-10">
										<label class="radio-inline"> 
											<input type="radio" name="isDeli" <c:if test="${webBrandConfigCommand.isDeli==1}">checked='checked'</c:if> value="1" id="isDeliYes" checked="checked" aria-labelledby="isDeli-lable" /> <spring:message code="label.is"/>
										</label> 
										<label class="radio-inline"> 
											<input type="radio" name="isDeli" <c:if test="${webBrandConfigCommand.isDeli==0}">checked='checked'</c:if> value="0"  id="isDeliNo" aria-labelledby="isDeli-lable" /> <spring:message code="label.not"/>
										</label>
									</div>
								</div>
								<div class="row" id="isDeliConfig" <c:if test="${webBrandConfigCommand.isDeli==0}">style="display: none"</c:if>>
									<div class="col-sm-12">
										<div class="form-group">												
											<div class="col-sm-12">
												<label class="col-sm-3 control-label" >
													<span class="glyphicon glyphicon-asterisk text-red-1"></span>														
												</label>
												<div class="col-sm-7">
													<label class="radio-inline">													
														<input type="radio" name="deliLoopRule" <c:if test="${webBrandConfigCommand.deliLoopRule==1}">checked='checked'</c:if> class="isDeliClass" value="1" id="deliLoopRuleY" />
														<spring:message code="label.brand.delilooprule.many"/>
													</label> 
													<label class="radio-inline"> 
														<input type="radio" name="deliLoopRule" <c:if test="${webBrandConfigCommand.deliLoopRule==2}">checked='checked'</c:if> class="isDeliClass" value="2"  id="deliLoopRuleN" />
														<spring:message code="label.brand.delilooprule.one"/>
													</label>
												</div>
											</div>
										</div>
										<div class="row" id="deliLoopRuleId" <c:if test="${webBrandConfigCommand.deliLoopRule==2}">style="display: none"</c:if>>										
											<div class="col-sm-12">
												<div class="form-group">
													<label class="col-sm-5 control-label" for="deliMaxStore">
														<span class="glyphicon glyphicon-asterisk text-red-1"></span>
														<spring:message code="label.brand.delimaxstore"/>
													</label>
													<div class="col-sm-2">
														<input id="deliMaxStore" name="deliMaxStore" type="text" value="${webBrandConfigCommand.deliMaxStore}" data-custom="positiveInteger" class="form-control deliLoopRuleClass isDeliClass" <c:if test="${webBrandConfigCommand.deliLoopRule==1}">required</c:if>/>
														<div class="help-block with-errors"></div>
													</div>
													<div class="col-sm-1 form-icon">
														<i class="icon-help-circled" title="<spring:message code="label.brand.delimaxstore.description"/>"></i>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-5 control-label" id="deliIsRepush-lable">
														<span class="glyphicon glyphicon-asterisk text-red-1"></span>
														<spring:message code="label.brand.deliisrepush"/>
													</label>
													<div class="col-sm-2">
														<label class="radio-inline"> 
															<input type="radio" name="deliIsRepush" <c:if test="${webBrandConfigCommand.deliIsRepush==1}">checked='checked'</c:if> class="deliLoopRuleClass isDeliClass" value="1" id="deliIsRepushYes" checked="checked" aria-labelledby="deliIsRepush-lable" /> 
															<spring:message code="label.is"/>
														</label> 
														<label class="radio-inline"> 
															<input type="radio" name="deliIsRepush" <c:if test="${webBrandConfigCommand.deliIsRepush==0}">checked='checked'</c:if> class="deliLoopRuleClass isDeliClass" value="0" id="deliIsRepushNo" aria-labelledby="deliIsRepush-lable" />
															<spring:message code="label.not"/>
														</label>														
													</div>
													<div class="col-sm-1 form-icon">
														<i class="icon-help-circled" title="<spring:message code="label.brand.deliisrepush.description"/>"></i>
													</div>												
												</div>	
											</div>			
										</div>					
										<div class="form-group">
											<label class="col-sm-3 control-label"  for="deliExpire">
												<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.deliexpire"/>
											</label>
											<div class="col-sm-4">
												<input id="deliExpire" name="deliExpire" type="text" value="${webBrandConfigCommand.deliExpire}" data-numberex="integer" data-min="1" data-max="23" class="form-control" <c:if test="${webBrandConfigCommand.isDeli==1}">required</c:if>/>
												<div class="help-block with-errors"></div>
											</div>
											<div class="col-sm-1 form-icon">
												<i class="icon-help-circled" title="<spring:message code="label.brand.deliexpire.description"/>"></i>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label" for="deliCondition">
												<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.delicondition"/>
											</label>
											<div class="col-sm-4">
												<select name="deliCondition" id="deliCondition" <c:if test="${webBrandConfigCommand.isDeli==1}">required</c:if> class="form-control input-sm filter">
													<option value="">-</option>
													<option value="3;4" <c:if test="${webBrandConfigCommand.deliCondition=='3;4'}">selected</c:if>><spring:message code="label.brand.distance"/></option>
													<option value="2;4" <c:if test="${webBrandConfigCommand.deliCondition=='2;4'}">selected</c:if>><spring:message code="label.brand.inventory"/></option>
													<option value="2;3;4" <c:if test="${webBrandConfigCommand.deliCondition=='2;3;4'}">selected</c:if>><spring:message code="label.brand.distanceandinventory"/></option>
												</select>
												<div class="help-block with-errors"></div>
											</div>
											<div class="col-sm-1 form-icon">
												<i class="icon-help-circled" title="<spring:message code="label.brand.delicondition.description"/>"></i>
											</div>
										</div>	
										<div class="form-group">
											<label class="col-sm-3 control-label">
												<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.repushrule"/>
											</label>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label"></label>
											<div class="col-sm-10">
												<label class="col-sm-3 control-label">
							                		<input type="radio" id="repushRuleHour" <c:if test="${webBrandConfigCommand.deliHour!=null}">checked='checked'</c:if> class="repushRule"/>
							                		<spring:message code="label.brand.repushrulehour"/>
							           			</label>
							           			<div class="col-sm-3" id="deliHourId" <c:if test="${webBrandConfigCommand.deliHour==null}">style="display: none"</c:if>>
													<input id="deliHour" name="deliHour" value="${webBrandConfigCommand.deliHour}" type="text" class="form-control  input-sm filter" data-custom="positiveInteger"/>
													<div class="help-block with-errors"></div>													
												</div>
												<div class="col-sm-1 form-icon" id="deliHourIcon" <c:if test="${webBrandConfigCommand.deliHour==null}">style="display: none"</c:if>>
													<i class="icon-help-circled" title="<spring:message code="label.brand.repushrulehour.description"/>"></i>
												</div>
											</div>
											
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label"></label>
											<div class="col-sm-10">
												<label class="col-sm-3 control-label">
							                		<input type="radio" id="repushRuleTimes" <c:if test="${webBrandConfigCommand.deliTimes!=null}">checked='checked'</c:if> class="repushRule"/>
							                		<spring:message code="label.brand.repushruletimes"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;					                		
							            		</label>
							            		<div class="col-sm-3" id="deliTimesId" <c:if test="${webBrandConfigCommand.deliTimes==null}">style="display: none"</c:if>>
													<input id="deliTimes" name="deliTimes" value="${webBrandConfigCommand.deliTimes}" type="text" class="form-control  input-sm filter"  data-custom="positiveInteger"/>
													<div class="help-block with-errors"></div>
												</div>
												<div class="col-sm-1 form-icon" id="deliTimesIcon" <c:if test="${webBrandConfigCommand.deliTimes==null}">style="display: none"</c:if>>
													<i class="icon-help-circled" title="<spring:message code="label.brand.repushruletimes.description"/>"></i>
												</div>	
											</div>											
										</div>										
									</div>
								</div>
								<hr/>
								<div class="form-group">
									<label class="col-sm-2 control-label" id="isSyncFull-lable">
										<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.issyncfull"/>
									</label>
									<div class="col-sm-6">
										<label class="radio-inline"> 
											<input type="radio" name="isSyncFull" <c:if test="${webBrandConfigCommand.isSyncFull==1}">checked='checked'</c:if> value="1" checked="checked" aria-labelledby="isSyncFull-lable" /> <spring:message code="label.is"/>
										</label> 
										<label class="radio-inline"> 
											<input type="radio" name="isSyncFull" <c:if test="${webBrandConfigCommand.isSyncFull==0}">checked='checked'</c:if> value="0" aria-labelledby="isSyncFull-lable" /> <spring:message code="label.not"/>
										</label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" id="isSyncInc-label">
										<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.issyncinc"/>
									</label>
									<div class="col-sm-6">
										<label class="radio-inline"> 
											<input type="radio" name="isSyncInc" <c:if test="${webBrandConfigCommand.isSyncInc==1}">checked='checked'</c:if> value="1" checked="checked" aria-labelledby="isSyncInc-label" /> <spring:message code="label.is"/>
										</label> 
										<label class="radio-inline"> 
											<input type="radio" name="isSyncInc" <c:if test="${webBrandConfigCommand.isSyncInc==0}">checked='checked'</c:if> value="0" aria-labelledby="isSyncInc-label" /> <spring:message code="label.not"/>
										</label>
									</div>
								</div>
								<input type="hidden" name="id" value="${webBrandConfigCommand.id}"/>
							</form>
							<div class="row">
								<div class="col-md-12 border-top-solid savepadding-rig-style mg-top10 mg-bottom20">
									<button class="btn  btn-primary pull-right role-save" id="brandSubmit" type="submit"><i class="fa fa-save"></i> <spring:message code="label.operator.save"/></button>
									<a class="btn btn-default" href="${staticbase}/brand/y/list"><i class="icon-left-open"></i><spring:message code="label.operator.return"/></a>
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
<script src="${staticbase }/scripts/pages/brand/brandUpdateForOp.js?${version}"></script>

</body>
</html>