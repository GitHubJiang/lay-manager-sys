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
			<li class="active"><a href="javaScript:void(0);"><spring:message code="label.operator.add"/></a></li>
	    </ol>
	    
<!--主要内容开始部分 START--> 
   
<!-- ============================================================== --> 
<!-- Start Content here --> 
<!-- ============================================================== -->
		<div class="content"> 
	     <!--主要内容开始部分 START-->
				<h3><spring:message code="label.operator.add"/></h3>
				<div class="row">
				<div class="col-md-12 portlets">
					<div class="widget padding">
						<div class="widget-content padding">
							<form action="${staticbase}/brand/add" class="form-horizontal" method="post" id="brandForm" role="form" data-toggle="form-validator">
								<div class="form-group">
									<label class="col-sm-2 control-label" for="brandCode"><span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.brandcode"/></label>
									<div class="col-sm-4">
										<input placeholder="<spring:message code="label.brand.brandcode"/>" data-custom="uniqueBrandCode" maxlength="10" id="brandCode" name="brandCode" type="text" class="form-control" required/>
										<div class="help-block with-errors"></div>
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
											<input type="radio" name="isSelfPick" value="1" id="isSelfPickYes" checked="checked" aria-labelledby="isSelfPick-label" /> <spring:message code="label.is"/>
										</label> 
										<label class="radio-inline"> 
											<input type="radio" name="isSelfPick" value="0" id="isSelfPickNo" aria-labelledby="isSelfPick-label" /> <spring:message code="label.not"/>
										</label>
									</div>
								</div>
								<!-- 自提子项 -->
								<div class="row" id="isSelfPickRow">
									<div class="col-sm-12">
										<div class="form-group">
											<label class="col-sm-3 control-label" for="packageHoldDays">
												<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.packageholddays"/>
											</label>
											<div class="col-sm-4">
												<input id="packageHoldDays" name="packageHoldDays" data-custom="positiveInteger" type="text" class="form-control" required/>
												<div class="help-block with-errors"></div>												
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
													<input type="radio" name="expiredMode" value="2" id="expiredModeYes" checked="checked" aria-labelledby="expiredMode-lable" /> <spring:message code="label.is"/>
												</label> 
												<label class="radio-inline"> 
													<input type="radio" name="expiredMode" value="1" id="expiredModeNo" aria-labelledby="expiredMode-lable" /> <spring:message code="label.not"/>
												</label>
											</div>
											<div class="col-sm-1 form-icon">
												<i class="icon-help-circled" title="<spring:message code="label.brand.expiredmode.description"/>"></i>
											</div>
										</div>
										<div class="form-group" id="expiredDelayDaysId">
											<label class="col-sm-3 control-label" for="expiredDelayDays">
												<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.expireddelaydays"/>
											</label>
											<div class="col-sm-4">
												<input id="expiredDelayDays" name="expiredDelayDays" type="text" class="form-control" data-custom="positiveInteger" required/>
												<div class="help-block with-errors"></div>
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
												<input id="pickEmailTcode" name="pickEmailTcode" type="text" class="form-control"/>
												<div class="help-block with-errors"></div>
											</div>											
										</div>
										<div class="form-group" id="pickSmsTcodeId">
											<label class="col-sm-3 control-label" for="pickSmsTcode">
												<spring:message code="label.brand.picksmstcode"/>
											</label>
											<div class="col-sm-4">
												<input id="pickSmsTcode" name="pickSmsTcode" type="text" class="form-control"/>
												<div class="help-block with-errors"></div>
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
											<input type="radio" name="isReturn" value="1" checked="checked" aria-labelledby="isReturn-lable" /> <spring:message code="label.is"/>
										</label> 
										<label class="radio-inline"> 
											<input type="radio" name="isReturn" value="0" aria-labelledby="isReturn-lable" /> <spring:message code="label.not"/>
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
											<input type="radio" name="isOrder" value="1" id="isOrderYes" checked="checked" aria-labelledby="isOrder-label" /><spring:message code="label.is"/>
										</label> 
										<label class="radio-inline"> 
											<input type="radio" name="isOrder" value="0" id="isOrderNo" aria-labelledby="isOrder-label" /><spring:message code="label.not"/>
										</label>
									</div>
								</div>
								<div class="row" id="isOrder">
									<div class="col-sm-12">
										<div class="form-group" id="memberEmailTcodeId">
											<label class="col-sm-3 control-label" for="memberEmailTcode">
												<spring:message code="label.brand.memberemailtcode"/>
											</label>
											<div class="col-sm-4">
												<input id="memberEmailTcode" name="memberEmailTcode" type="text" class="form-control"/>
												<div class="help-block with-errors"></div>
											</div>											
										</div>
										<div class="form-group" id="memberSmsTcodeId">
											<label class="col-sm-3 control-label" for="memberSmsTcode">
												<spring:message code="label.brand.membersmstcode"/>
											</label>
											<div class="col-sm-4">
												<input id="memberSmsTcode" name="memberSmsTcode" type="text" class="form-control"/>
												<div class="help-block with-errors"></div>
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
											<input type="radio" name="isDeli" value="1" id="isDeliYes" checked="checked" aria-labelledby="isDeli-lable" /> <spring:message code="label.is"/>
										</label> 
										<label class="radio-inline"> 
											<input type="radio" name="isDeli" value="0"  id="isDeliNo" aria-labelledby="isDeli-lable" /> <spring:message code="label.not"/>
										</label>
									</div>
								</div>
								<div class="row" id="isDeliConfig">									
									<div class="col-sm-12">
										<div class="form-group">												
											<div class="col-sm-12">
												<label class="col-sm-3 control-label" >
													<span class="glyphicon glyphicon-asterisk text-red-1"></span>														
												</label>
												<div class="col-sm-7">
													<label class="radio-inline">													
														<input type="radio" name="deliLoopRule" class="isDeliClass" value="1" id="deliLoopRuleY" checked="checked" /><spring:message code="label.brand.delilooprule.many"/>
													</label> 
													<label class="radio-inline"> 
														<input type="radio" name="deliLoopRule" class="isDeliClass" value="2"  id="deliLoopRuleN" /><spring:message code="label.brand.delilooprule.one"/>
													</label>
												</div>
											</div>
										</div>
										<div class="row" id="deliLoopRuleId">										
											<div class="col-sm-12">
												<div class="form-group">
													<label class="col-sm-5 control-label" for="deliMaxStore">
														<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.delimaxstore"/>
													</label>
													<div class="col-sm-2">
														<input id="deliMaxStore" name="deliMaxStore" type="text" data-custom="positiveInteger" class="form-control deliLoopRuleClass isDeliClass" required/>
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
															<input type="radio" name="deliIsRepush" class="deliLoopRuleClass isDeliClass" value="1" id="deliIsRepushYes" checked="checked" aria-labelledby="deliIsRepush-lable" /><spring:message code="label.is"/>
														</label> 
														<label class="radio-inline"> 
															<input type="radio" name="deliIsRepush" class="deliLoopRuleClass isDeliClass" value="0" id="deliIsRepushNo" aria-labelledby="deliIsRepush-lable" /><spring:message code="label.not"/>
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
												<input id="deliExpire" name="deliExpire" type="text"  data-numberex="integer" data-min="1" data-max="23" class="form-control isDeliClass" required/>
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
												<select name="deliCondition" id="deliCondition" required class="form-control input-sm filter isDeliClass">
													<option value="">-</option>
													<option value="3;4" ><spring:message code="label.brand.distance"/></option>
													<option value="2;4" ><spring:message code="label.brand.inventory"/></option>
													<option value="2;3;4"><spring:message code="label.brand.distanceandinventory"/></option>
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
							                		<input type="radio" id="repushRuleHour" checked="checked" class="repushRule"/>
							                		<spring:message code="label.brand.repushrulehour"/>
							           			</label>
							           			<div class="col-sm-3" id="deliHourId">
													<input id="deliHour" name="deliHour" type="text" class="form-control  input-sm filter" data-custom="positiveInteger" required="required"/>
													<div class="help-block with-errors"></div>												
												</div>
												<div class="col-sm-1 form-icon" id="deliHourIcon">
													<i class="icon-help-circled" title="<spring:message code="label.brand.repushrulehour.description"/>"></i>
												</div>
											</div>											
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label"></label>
											<div class="col-sm-10">
												<label class="col-sm-3 control-label">
							                		<input type="radio" id="repushRuleTimes"  class="repushRule"/>
							                		<spring:message code="label.brand.repushruletimes"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;					                		
							            		</label>
							            		<div class="col-sm-3" id="deliTimesId" style="display: none">
													<input id="deliTimes" name="deliTimes" type="text" class="form-control  input-sm filter" data-custom="positiveInteger" disabled/>
													<div class="help-block with-errors"></div>
												</div>
												<div class="col-sm-1 form-icon" id="deliTimesIcon" style="display: none">
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
											<select name="printType" id="printType" required class="form-control input-sm filter required">
												<option value="1" ><spring:message code="label.brand.printtype.one"/></option>
												<option value="2" ><spring:message code="label.brand.printtype.two"/></option>
												<option value="3" ><spring:message code="label.brand.printtype.three"/></option>
											</select>
											<div class="help-block with-errors"></div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label">
											<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.sfaccountno"/>
										</label>
										<div class="col-sm-4">
											<input id="sfAccountNo" name="sfAccountNo" type="text" class="form-control" required/>
											<div class="help-block with-errors"></div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label">
											<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.sfprintsize"/>
										</label>
										<div class="col-sm-4">
											<select name="sfPrintSize" id="sfPrintSize" required class="form-control input-sm filter required">
												<option value="1" ><spring:message code="label.brand.sfprintsize.one"/></option>
												<option value="2" ><spring:message code="label.brand.sfprintsize.two"/></option>
											</select>
											<div class="help-block with-errors"></div>
										</div>
									</div>							
									<div class="form-group">
										<label class="col-sm-2 control-label">
											<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.sfheadcode"/>
										</label>
										<div class="col-sm-4">
											<input id="sfHeadcode" name="sfHeadcode" type="text" class="form-control" required/>
											<div class="help-block with-errors"></div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label">
											<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.sfcheckword"/>
										</label>
										<div class="col-sm-4">
											<input id="sfCheckword" name="sfCheckword" type="text" class="form-control" required/>
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
											<input type="radio" name="isSyncFull" value="1" checked="checked" aria-labelledby="isSyncFull-lable" /><spring:message code="label.is"/>
										</label> 
										<label class="radio-inline"> 
											<input type="radio" name="isSyncFull" value="0" aria-labelledby="isSyncFull-lable" /><spring:message code="label.not"/>
										</label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" id="isSyncInc-label">
										<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.issyncinc"/>
									</label>
									<div class="col-sm-6">
										<label class="radio-inline"> 
											<input type="radio" name="isSyncInc" value="1" checked="checked" aria-labelledby="isSyncInc-label" /><spring:message code="label.is"/>
										</label> 
										<label class="radio-inline"> 
											<input type="radio" name="isSyncInc" value="0" aria-labelledby="isSyncInc-label" /> <spring:message code="label.not"/>
										</label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" id="isHasSub-label">
										<span class="glyphicon glyphicon-asterisk text-red-1"></span><spring:message code="label.brand.ishassub"/>
									</label>
									<div class="col-sm-6">
										<label class="radio-inline"> 
											<input type="radio" name="isHasSub" id="isHasSubYes" value="1" aria-labelledby="isHasSub-label" /><spring:message code="label.is"/>
										</label> 
										<label class="radio-inline"> 
											<input type="radio" name="isHasSub" id="isHasSubNo" value="0" checked="checked" aria-labelledby="isHasSub-label" /><spring:message code="label.not"/>
										</label>
									</div>
								</div>
								<div class="row" id="isHasSubId">
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
													        			<input type="text" class="form-control" id="invRangePreW" data-custom="invRangePre positiveInteger"  value="" required="required">
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
																      		<input class="form-control" id="invPercentPre" type="text" data-numberex="integer" data-min="0" data-max="100" required="required">
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
												        	<input  type="text" class="form-control" id="invRangePreR" value="" name="" disabled>
												        </td>
												        <td class="text-center col-sm-4">
												        	<div class="row">
												        		<div class="col-sm-12">
												        			<div class="col-sm-1"></div>
													        		<div class="form-group col-sm-10">	
												        				<input type="text" class="form-control" id="invRangeSuffixW" data-custom="invRangeSuffix positiveInteger"  value=""  required="required">
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
																	      		<input class="form-control" id="invPercentMid" type="text" data-numberex="integer" data-min="0" data-max="100" required="required">
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
												        	<input  type="text" class="form-control" id="invRangeSuffixR" disabled>
												        </td>
												        <td class="text-center col-sm-4">∞</td>
												        <td class="text-center col-sm-4">
													        <div class="row">
												        		<div class="col-sm-12">
												        			<div class="col-sm-1"></div>
													        		<div class="form-group col-sm-10">
															        	<div class="input-group">											      
																      		<input class="form-control" id="invPercentSuffix" data-numberex="integer" data-min="0" data-max="100" type="text" required="required" >
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
										    		<th colspan="5">
														<button id="lineAdd" class="btn btn-primary btn-sm" name="" type="button"><i class="fa fa-plus-circle"></i> <spring:message code="label.operator.add" /></button>
													</th>
										    	</tr>
											    <tr>
											        <th class="text-center"><spring:message code="label.brand.channelconfig.channelcode"/></th>
											        <th class="text-center"><spring:message code="label.brand.channelconfig.channelname"/></th>
											        <th class="text-center"><spring:message code="label.brand.channelconfig.sourcesys"/></th>
											        <th class="text-center"><spring:message code="label.brand.channelconfig.isdefault"/></th>
											        <th class="text-center"><spring:message code="label.operator" /></th>
											    </tr>
											</thead>
											<tbody>
											    <tr>
											        <td class="text-center">
												        <div class="row">
											        		<div class="col-sm-12">
											        			<div class="col-sm-1"></div>
												        		<div class="form-group col-sm-10">											        	
												        			<input type="text" name="channelCode" class="form-control" required="required">
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
												        			<input type="text" name="channelName" class="form-control" required="required">
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
												        			<input type="text" name="sourceSys" class="form-control" required="required">
												        			<div class="help-block with-errors"></div>
												        		</div>
											        		</div>
											        	</div>											        	
											        </td>
											        <td class="text-center">
											        	<input type="radio" value="1" checked="checked" name="isDefault"/>	
													</td>
													<td class="text-center">
											        	<div class="row" style="display:none" >
											        		<div class="col-sm-12">
											        			<div class="col-sm-1"></div>
												        		<div class="form-group col-sm-10">											        	
												        			<button class="btn btn-primary btn-sm"  onclick="deleteCurrentRow(this);" <i class="fa fa-minus-circle"></i> <spring:message code="label.operator.delete"/></button>
												        		</div>
											        		</div>
											        	</div>	
													</td>          
											    </tr>												
										   </tbody>   
										</table>
									</div>															
								</div>
								<input type="hidden" name="invRange" value="" id="invRange"/>		
								<input type="hidden" name="invPercent" value="" id="invPercent"/>	
								<input type="hidden" name="channelInfo" id="channelInfo" value="">		   
							</form>
							<div class="row">
								<div class="col-md-12 border-top-solid savepadding-rig-style mg-top10 mg-bottom20">
									<button class="btn  btn-primary pull-right role-save" id="brandSubmit" type="submit"><i class="fa fa-save"></i> <spring:message code="label.operator.save"/></button>
									<a class="btn btn-default" href="${staticbase}/brand/list"><i class="icon-left-open"></i><spring:message code="label.operator.return"/></a>
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
<script src="${staticbase }/scripts/pages/brand/brandAdd.js?${version}"></script>

</body>
</html>