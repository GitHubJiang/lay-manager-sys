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
		<jsp:param name="pagecode" value="${acl }" />
	</jsp:include>
  
	<!-- Start right content -->
	<div class="content-page">
		<!-- 面包屑 -->
	    <ol class="breadcrumb">
			<li><a href="javaScript:void(0);"><spring:message code="label.brand.manager"/></a></li>
			<li class="active"><a href="javaScript:void(0);"><spring:message code="label.brand.detail"/></a></li>
	    </ol>
	    
<!--主要内容开始部分 START--> 
   
<!-- ============================================================== --> 
<!-- Start Content here --> 
<!-- ============================================================== -->
		<div class="content"> 
	     <!--主要内容开始部分 START-->
				<div class="row">
			 		<div class="col-md-4">
			        	<h3 class="marign-none"><spring:message code="label.brand.detail"/></h3>
			        </div>
			        <div class="col-md-8">
						 <pri:update acl="ACL_BRAND_B">
				            <div class="toolbar-btn-action">	                
				                <a class="btn btn-success" href="${staticbase}/brand/toUpdate/${webBrandConfigCommand.id}"><i class="fa fa-edit"></i><spring:message code="label.operator.edit"/></a>
				            </div>
						</pri:update>
			        </div>
				</div>
				<div class="row">
				<div class="col-md-12 portlets">
					<div class="widget padding">
						<div class="widget-content padding">
							<form  class="form-horizontal" role="form">
								<div class="form-group">
									<label class="col-sm-2 control-label" for="brandCode"><span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.brandcode"/></label>
									<div class="col-sm-4">
										<input value="${webBrandConfigCommand.brandCode}" disabled class="form-control"/>
									</div>									
								</div>
								<hr/>
								<!-- 自提头 -->
								<div class="form-group">
									<label class="col-sm-2 control-label" id="isSelfPick-label">
										<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.isselfpick"/>
									</label>
									<div class="col-sm-10">
										<label class="radio-inline"> 
											<input type="radio" <c:if test="${webBrandConfigCommand.isSelfPick==1}">checked='checked'</c:if> disabled/> <spring:message code="label.is"/>
										</label> 
										<label class="radio-inline"> 
											<input type="radio" <c:if test="${webBrandConfigCommand.isSelfPick==0}">checked='checked'</c:if> disabled/> <spring:message code="label.not"/>
										</label>
									</div>
								</div>
								<!-- 自提子项 -->
								<div class="row" id="isSelfPickRow" <c:if test="${webBrandConfigCommand.isSelfPick==0}">style="display: none"</c:if>>
									<div class="col-sm-12">
										<div class="form-group">
											<label class="col-sm-3 control-label" for="packageHoldDays">
												<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.packageholddays"/>
											</label>
											<div class="col-sm-4">
												<input value="${webBrandConfigCommand.packageHoldDays}" class="form-control" disabled/>											
											</div>
											<div class="col-sm-1 form-icon">
												<i class="icon-help-circled" title="<spring:message code="label.brand.packageholddays.description"/>"></i>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label" id="expiredMode-lable">
												<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.expiredmode"/>
											</label>
											<div class="col-sm-4">
												<label class="radio-inline"> 
													<input type="radio" <c:if test="${webBrandConfigCommand.expiredMode==2}">checked='checked'</c:if> disabled/> <spring:message code="label.is"/>
												</label> 
												<label class="radio-inline"> 
													<input type="radio" <c:if test="${webBrandConfigCommand.expiredMode==1}">checked='checked'</c:if> disabled/> <spring:message code="label.not"/>
												</label>
											</div>
											<div class="col-sm-1 form-icon">
												<i class="icon-help-circled" title="<spring:message code="label.brand.expiredmode.description"/>"></i>
											</div>
										</div>
										<div class="form-group" <c:if test="${webBrandConfigCommand.expiredMode==1}">style="display: none"</c:if>>
											<label class="col-sm-3 control-label" for="expiredDelayDays">
												<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.expireddelaydays"/>
											</label>
											<div class="col-sm-4">
												<input value="${webBrandConfigCommand.expiredDelayDays}" class="form-control" disabled/>
											</div>
											<div class="col-sm-1 form-icon">
												<i class="icon-help-circled" title="<spring:message code="label.brand.expireddelaydays.description"/>"></i>
											</div>
										</div>
										<div class="form-group" id="pickEmailTcodeId">
											<label class="col-sm-3 control-label" for="pickEmailTcode">
												<spring:message code="label.brand.pickemailtcode"/>
											</label>
											<div class="col-sm-4">
												<input value="${webBrandConfigCommand.pickEmailTcode}" type="text" class="form-control" disabled/>
											</div>											
										</div>
										<div class="form-group" id="pickSmsTcodeId">
											<label class="col-sm-3 control-label" for="pickSmsTcode">
												<spring:message code="label.brand.picksmstcode"/>
											</label>
											<div class="col-sm-4">
												<input value="${webBrandConfigCommand.pickSmsTcode}" type="text" class="form-control" disabled/>												
											</div>											
										</div>
									</div>
								</div>
								<hr/>
								<div class="form-group">
									<label class="col-sm-2 control-label" id="isReturn-lable">
										<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.isreturn"/>
									</label>
									<div class="col-sm-10">
										<label class="radio-inline"> 
											<input type="radio" <c:if test="${webBrandConfigCommand.isReturn==1}">checked='checked'</c:if> disabled/> <spring:message code="label.is"/>
										</label> 
										<label class="radio-inline"> 
											<input type="radio" <c:if test="${webBrandConfigCommand.isReturn==0}">checked='checked'</c:if> disabled/> <spring:message code="label.not"/>
										</label>
									</div>
								</div>
								<hr/>
								<div class="form-group">
									<label class="col-sm-2 control-label" id="isOrder-lable">
										<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.isorder"/>
									</label>
									<div class="col-sm-10">
										<label class="radio-inline"> 
											<input type="radio" <c:if test="${webBrandConfigCommand.isOrder==1}">checked='checked'</c:if> disabled/> <spring:message code="label.is"/>
										</label> 
										<label class="radio-inline"> 
											<input type="radio" <c:if test="${webBrandConfigCommand.isOrder==0}">checked='checked'</c:if> disabled/> <spring:message code="label.not"/>
										</label>
									</div>
								</div>
								<div class="row" id="isOrder" <c:if test="${webBrandConfigCommand.isOrder==0}">style="display: none"</c:if>>
									<div class="col-sm-12">
										<div class="form-group" id="memberEmailTcodeId">
											<label class="col-sm-3 control-label" for="memberEmailTcode">
												<spring:message code="label.brand.memberemailtcode"/>
											</label>
											<div class="col-sm-4">
												<input value="${webBrandConfigCommand.memberEmailTcode}" type="text" class="form-control" disabled/>
											</div>											
										</div>
										<div class="form-group" id="memberSmsTcodeId">
											<label class="col-sm-3 control-label" for="memberSmsTcode">
												<spring:message code="label.brand.membersmstcode"/>
											</label>
											<div class="col-sm-4">
												<input value="${webBrandConfigCommand.memberSmsTcode}" type="text" class="form-control" disabled/>
											</div>											
										</div>
									</div>
								</div>
								<hr/>
								<div class="form-group">
									<label class="col-sm-2 control-label" id="isDeli-lable">
										<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.isdeli"/>
									</label>
									<div class="col-sm-10">
										<label class="radio-inline"> 
											<input type="radio" <c:if test="${webBrandConfigCommand.isDeli==1}">checked='checked'</c:if> disabled/> 
											<spring:message code="label.is"/>
										</label> 
										<label class="radio-inline"> 
											<input type="radio" <c:if test="${webBrandConfigCommand.isDeli==0}">checked='checked'</c:if> disabled/> 
											<spring:message code="label.not"/>
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
														<input type="radio" id="deliLoopRuleY" name="deliLoopRule" <c:if test="${webBrandConfigCommand.deliLoopRule==1}">checked='checked'</c:if> disabled/> 
														<spring:message code="label.brand.delilooprule.many"/>
													</label> 
													<label class="radio-inline"> 
														<input type="radio" id="deliLoopRuleN" name="deliLoopRule" <c:if test="${webBrandConfigCommand.deliLoopRule==2}">checked='checked'</c:if> disabled/> 
														<spring:message code="label.brand.delilooprule.one"/>
													</label>
												</div>
											</div>
										</div>
										<div class="row" id="deliLoopRuleId" <c:if test="${webBrandConfigCommand.deliLoopRule==2}">style="display: none"</c:if>>										
											<div class="col-sm-12">
												<div class="form-group">
													<label class="col-sm-5 control-label" for="deliMaxStore">
														<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.delimaxstore"/>
													</label>
													<div class="col-sm-2">
														<input id="deliMaxStore" name="deliMaxStore" value="${webBrandConfigCommand.deliMaxStore}" type="text" data-custom="positiveInteger" class="form-control deliLoopRuleClass isDeliClass" disabled="disabled"/>
														<div class="help-block with-errors"></div>
													</div>
													<div class="col-sm-1 form-icon">
														<i class="icon-help-circled" title="<spring:message code="label.brand.delimaxstore.description"/>"></i>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-5 control-label" id="deliIsRepush-lable">
														<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.deliisrepush"/>
													</label>
													<div class="col-sm-2">
														<label class="radio-inline"> 
															<input type="radio" name="deliIsRepush" id="deliIsRepushYes" aria-labelledby="deliIsRepush-lable" <c:if test="${webBrandConfigCommand.deliIsRepush==1}">checked='checked'</c:if> disabled/> <spring:message code="label.is"/>
														</label> 
														<label class="radio-inline"> 
															<input type="radio" name="deliIsRepush" id="deliIsRepushNo" aria-labelledby="deliIsRepush-lable" <c:if test="${webBrandConfigCommand.deliIsRepush==0}">checked='checked'</c:if> disabled/> <spring:message code="label.not"/>
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
												<input value="${webBrandConfigCommand.deliExpire}" class="form-control" disabled/>												
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
												<select name="deliCondition" id="deliCondition" disabled class="form-control input-sm filter">
													<option value="">-</option>
													<option <c:if test="${webBrandConfigCommand.deliCondition=='3;4'}">selected</c:if>><spring:message code="label.brand.distance"/></option>
													<option <c:if test="${webBrandConfigCommand.deliCondition=='2;4'}">selected</c:if>><spring:message code="label.brand.inventory"/></option>
													<option <c:if test="${webBrandConfigCommand.deliCondition=='2;3;4'}">selected</c:if>><spring:message code="label.brand.distanceandinventory"/></option>
												</select>
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
							                		<input type="radio"  <c:if test="${webBrandConfigCommand.deliHour!=null}">checked='checked'</c:if> disabled/>
							                		<spring:message code="label.brand.repushrulehour"/>
							           			</label>
							           			<div class="col-sm-3" <c:if test="${webBrandConfigCommand.deliHour==null}">style="display: none"</c:if> disabled>
													<input value="${webBrandConfigCommand.deliHour}" class="form-control" disabled/>																									
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
							                		<input type="radio" <c:if test="${webBrandConfigCommand.deliTimes!=null}">checked='checked'</c:if> disabled/>
							                		<spring:message code="label.brand.repushruletimes"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;					                		
							            		</label>
							            		<div class="col-sm-3"  <c:if test="${webBrandConfigCommand.deliTimes==null}">style="display: none"</c:if>>													
													<input value="${webBrandConfigCommand.deliTimes}" class="form-control" disabled/>	
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
									<div class="form-group">
										<label class="col-sm-2 control-label">
											<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.printtype"/>
										</label>
										<div class="col-sm-4">
											<select name="printType" id="printType" disabled class="form-control input-sm filter">
												<option <c:if test="${webBrandConfigCommand.printType=='1'}">selected</c:if>><spring:message code="label.brand.printtype.one"/></option>
												<option <c:if test="${webBrandConfigCommand.printType=='2'}">selected</c:if> ><spring:message code="label.brand.printtype.two"/></option>
												<option <c:if test="${webBrandConfigCommand.printType=='3'}">selected</c:if> ><spring:message code="label.brand.printtype.three"/></option>
											</select>
											<div class="help-block with-errors"></div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label">
											<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.sfaccountno"/>
										</label>
										<div class="col-sm-4">
											<input id="sfAccountNo" value="${webBrandConfigCommand.sfAccountNo}" name="sfAccountNo" type="text" class="form-control" disabled/>
											<div class="help-block with-errors"></div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label">
											<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.sfprintsize"/>
										</label>
										<div class="col-sm-4">
											<select name="sfPrintSize" id="sfPrintSize" disabled class="form-control input-sm filter">
												<option <c:if test="${webBrandConfigCommand.sfPrintSize=='1'}">selected</c:if>><spring:message code="label.brand.sfprintsize.one"/></option>
												<option <c:if test="${webBrandConfigCommand.sfPrintSize=='2'}">selected</c:if> ><spring:message code="label.brand.sfprintsize.two"/></option>
											</select>
											<div class="help-block with-errors"></div>
										</div>
									</div>							
									<div class="form-group">
										<label class="col-sm-2 control-label">
											<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.sfheadcode"/>
										</label>
										<div class="col-sm-4">
											<input id="sfHeadcode" value="${webBrandConfigCommand.sfHeadcode}" name="sfHeadcode" type="text" class="form-control" disabled/>
											<div class="help-block with-errors"></div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label">
											<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.sfcheckword"/>
										</label>
										<div class="col-sm-4">
											<input id="sfCheckword" value="${webBrandConfigCommand.sfCheckword}" name="sfCheckword" type="text" class="form-control" disabled/>
											<div class="help-block with-errors"></div>
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
											<input type="radio" <c:if test="${webBrandConfigCommand.isSyncFull==1}">checked='checked'</c:if> disabled/> <spring:message code="label.is"/>
										</label> 
										<label class="radio-inline"> 
											<input type="radio" <c:if test="${webBrandConfigCommand.isSyncFull==0}">checked='checked'</c:if> disabled/> <spring:message code="label.not"/>
										</label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" id="isSyncInc-label">
										<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.issyncinc"/>
									</label>
									<div class="col-sm-6">
										<label class="radio-inline"> 
											<input type="radio" <c:if test="${webBrandConfigCommand.isSyncInc==1}">checked='checked'</c:if> disabled/> <spring:message code="label.is"/>
										</label> 
										<label class="radio-inline"> 
											<input type="radio" <c:if test="${webBrandConfigCommand.isSyncInc==0}">checked='checked'</c:if> disabled/> <spring:message code="label.not"/>
										</label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" id="isHasSub-label">
										<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.ishassub"/>
									</label>
									<div class="col-sm-6">
										<label class="radio-inline"> 
											<input type="radio" name="isHasSub" id="isHasSubYes" <c:if test="${webBrandConfigCommand.isHasSub==1}">checked='checked'</c:if> disabled aria-labelledby="isHasSub-label" /> <spring:message code="label.is"/>
										</label> 
										<label class="radio-inline"> 
											<input type="radio" name="isHasSub" id="isHasSubNo" <c:if test="${webBrandConfigCommand.isHasSub==0}">checked='checked'</c:if> disabled aria-labelledby="isHasSub-label" /> <spring:message code="label.not"/>
										</label>
									</div>
								</div>
								<div class="row" id="isHasSubId" <c:if test="${webBrandConfigCommand.isHasSub==1}">style="display: none"</c:if>>	
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
													        			<input value="${webBrandConfigCommand.invRangePre}" class="form-control" disabled>
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
																      		<input value="${webBrandConfigCommand.invPercentPre}" class="form-control" disabled>
																      		<div class="input-group-addon">%</div>
																    	</div>
													        		</div>
												        		</div>
												        	</div>	
														</td>        
												    </tr>
													<tr>
												        <td class="text-center col-sm-4">
												        	<input  type="text" class="form-control" value="${webBrandConfigCommand.invRangePre}" disabled>
												        </td>
												        <td class="text-center col-sm-4">
												        	<div class="row">
												        		<div class="col-sm-12">
												        			<div class="col-sm-1"></div>
													        		<div class="form-group col-sm-10">	
												        				<input type="text" class="form-control" value="${webBrandConfigCommand.invRangeSuffix}" disabled>
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
																	      		<input class="form-control" value="${webBrandConfigCommand.invPercentMid}" disabled>
																	      		<div class="input-group-addon">%</div>
																	    	</div>
													        		</div>
												        		</div>
												        	</div>	
												        </td>        
												    </tr>
													<tr>
												        <td class="text-center col-sm-4">
												        	<input  type="text" class="form-control" value="${webBrandConfigCommand.invRangeSuffix}"  disabled>
												        </td>
												        <td class="text-center col-sm-4">∞</td>
												        <td class="text-center col-sm-4">
													        <div class="row">
												        		<div class="col-sm-12">
												        			<div class="col-sm-1"></div>
													        		<div class="form-group col-sm-10">
															        	<div class="input-group">											      
																      		<input class="form-control" value="${webBrandConfigCommand.invPercentSuffix}" disabled >
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
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">
										<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.channelconfig"/>
									</label>									
								</div>	
								<div class="row">									
									<div class="col-sm-2"></div>
									<div class="col-sm-8">
										<table class="table table-bordered" id="auBrand">
										    <thead>
											    <tr>
											        <th class="text-center"><spring:message code="label.brand.channelconfig.channelcode"/></th>
											        <th class="text-center"><spring:message code="label.brand.channelconfig.channelname"/></th>
											        <th class="text-center"><spring:message code="label.brand.channelconfig.sourcesys"/></th>
											        <th class="text-center"><spring:message code="label.brand.channelconfig.isdefault"/></th>
											    </tr>
											</thead>
											<tbody>
												<c:forEach items="${webBrandConfigCommand.brandList}" var="item" >											
													<tr>
												        <td class="text-center">
													        <div class="row">
												        		<div class="col-sm-12">
												        			<div class="col-sm-1"></div>
													        		<div class="form-group col-sm-10">											        	
													        			<input type="text" value="${item.channelCode}" class="form-control" disabled>
													        			<div class="help-block with-errors"></div>
													        		</div>
												        		</div>
												        	</div>	
												        </td>
												        <td class="text-center">
												        	<div class="row">
												        		<div class="col-sm-12">
												        			<div class="col-sm-1"></div>
													        		<div class="form-group col-sm-10">											        	
													        			<input type="text" value="${item.name}" disabled class="form-control">
													        			<div class="help-block with-errors"></div>
													        		</div>
												        		</div>
												        	</div>											        	
												        </td>
												        <td class="text-center">
												        	<div class="row">
												        		<div class="col-sm-12">
												        			<div class="col-sm-1"></div>
													        		<div class="form-group col-sm-10">											        	
													        			<input type="text" value="${item.sourceSys}" disabled class="form-control">
													        			<div class="help-block with-errors"></div>
													        		</div>
												        		</div>
												        	</div>											        	
												        </td>
												        <td class="text-center">
												        	<input type="checkbox" <c:if test="${item.isDefault==1}">checked='checked'</c:if> disabled/>	
														</td>													          
												    </tr>	
												</c:forEach>											    											
										   </tbody>   
										</table>
									</div>															
								</div> 
							</form>
							<div class="row">
								<c:if test="${acl eq 'ACL_BRAND'}">
									<div class="col-md-12 border-top-solid savepadding-rig-style mg-top10 mg-bottom20">									
										<a class="btn btn-default" href="${staticbase}/brand/list"><i class="icon-left-open"></i><spring:message code="label.operator.return"/></a>
									</div>
								</c:if>
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
</body>
</html>