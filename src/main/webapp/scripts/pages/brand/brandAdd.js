/*****************************************************/
/***************1. 基本信息的设定***********************/
/** ************************************************** */
wms.addResource("zh-CN", {
	
});


/** ************************************************** */
/** *************2. 页面加载时设定********************** */
/** ************************************************** */
$(function(){
	
	$("input[name=isSelfPick]").on("ifChecked", function() {

		if($("input[name=isSelfPick]:checked").attr("id")=='isSelfPickYes'){
			$("#expiredModeYes").iCheck("check");
			$("#packageHoldDays").attr("required","required");
			$("#expiredDelayDays").attr("required","required");	
			$("input[name=expiredMode]").removeAttr("disabled");
			$("#packageHoldDays").removeAttr("disabled");
			$("#expiredDelayDays").removeAttr("disabled");
			$("#pickEmailTcode").removeAttr("disabled");
			$("#pickSmsTcode").removeAttr("disabled");
			$("#isSelfPickRow").show();					
		}else{			
			$("#packageHoldDays").removeAttr("required");
			$("#expiredDelayDays").removeAttr("required");	
			$("#expiredModeYes").iCheck("uncheck");
			$("input[name=expiredMode]").attr("disabled","disabled");
			$("#packageHoldDays").attr("disabled","disabled");
			$("#expiredDelayDays").attr("disabled","disabled");
			$("#pickEmailTcode").attr("disabled","disabled");
			$("#pickSmsTcode").attr("disabled","disabled");
			$("#isSelfPickRow").hide();
		}
	});
	
	///是否支持下单
	$("input[name=isOrder]").on("ifChecked", function() {

		if($("input[name=isOrder]:checked").attr("id")=='isOrderYes'){
			$("#isOrderYes").iCheck("check");
			$("#memberEmailTcode").removeAttr("disabled");
			$("#memberSmsTcode").removeAttr("disabled");	
			$("#isOrder").show();					
		}else{			
			$("#isOrderYes").iCheck("uncheck");
			$("#memberEmailTcode").attr("disabled","disabled");
			$("#memberSmsTcode").attr("disabled","disabled");	
			$("#isOrder").hide();
		}
	});
	
	$("input[name=expiredMode]").on("ifChecked", function() {
		
		if($("input[name=expiredMode]:checked").attr("id")=='expiredModeYes'){
			$("#expiredDelayDays").attr("required","required");	
			$("#expiredDelayDays").removeAttr("disabled");	
			$("#expiredDelayDaysId").show();					
		}else{			
			$("#expiredDelayDays").removeAttr("required","required");
			$("#expiredDelayDays").attr("disabled","disabled");	
			$("#expiredDelayDaysId").hide();
		}
	});
	
	$("input[name=isDeli]").on("ifChecked", function() {
		if($("input[name=isDeli]:checked").attr("id")=='isDeliYes'){			
			$("#deliExpire").removeAttr("disabled");	
			$("#deliExpire").attr("required","required");	
			$("#deliCondition").removeAttr("disabled");	
			$("#deliCondition").attr("required","required");
			$("#deliLoopRuleY").iCheck("check");
			$("#deliMaxStore").removeAttr("disabled");
			$("#deliMaxStore").attr("required","required");	
			$(".repushRule").removeAttr("disabled");
			$("#deliHour").removeAttr("disabled");	
			$("#deliTimes").removeAttr("disabled");			
			$("#isDeliConfig").show();					
		}else{			
			$("#deliMaxStore").attr("disabled","disabled");
			$("#deliExpire").attr("disabled","disabled");
			$("#deliCondition").attr("disabled","disabled");
			$(".repushRule").attr("disabled","disabled");
			$("#deliHour").attr("disabled","disabled");	
			$("#deliTimes").attr("disabled","disabled");			
			$("#isDeliConfig").hide();
		}
	});
	
	$("input[name=deliLoopRule]").on("ifChecked", function() {
		if($("input[name=deliLoopRule]:checked").attr("id")=='deliLoopRuleY'){			
			$("#deliLoopRuleY").iCheck("check");
			$("#deliMaxStore").removeAttr("disabled");
			$("#deliMaxStore").attr("required","required");			
			$("#deliLoopRuleId").show();					
		}else{			
			$("#deliMaxStore").attr("disabled","disabled");
			$("#deliMaxStore").removeAttr("required");
			$("#deliLoopRuleId").hide();
		}
	});
	
	$("#repushRuleHour").on("ifChecked", function() {
		$("#deliHourId").show();
		$("#deliHourIcon").show();
		$("#deliHour").attr("required","required");			
		$("#deliHour").removeAttr("disabled");	
		$("#deliTimesId").hide();
		$("#deliTimesIcon").hide();
		$("#deliTimes").attr("disabled","disabled");
		$("#deliTimes").removeAttr("required");	
		$("#repushRuleTimes").iCheck("uncheck");
	});
	
	$("#repushRuleTimes").on("ifChecked", function() {
		$("#deliTimesId").show();
		$("#deliTimesIcon").show();
		$("#deliTimes").attr("required","required");
		$("#deliTimes").removeAttr("disabled");
		$("#deliHourId").hide();
		$("#deliHourIcon").hide();
		$("#deliHour").attr("disabled","disabled");			
		$("#deliHour").removeAttr("required");
		$("#repushRuleHour").iCheck("uncheck");
	});
	
	$("#invRangePreW").on("change", function() {		
		$("#invRangePreR").val($("#invRangePreW").val());		
	});
	
	$("#invRangeSuffixW").on("change", function() {		
		$("#invRangeSuffixR").val($("#invRangeSuffixW").val());
	});
	
	$("input[name=isHasSub]").on("ifChecked", function() {
		if($("input[name=isHasSub]:checked").attr("id")=='isHasSubNo'){			
			$("#isHasSubNo").iCheck("check");
			$("#invRangePreW").removeAttr("disabled");
			$("#invRangePreW").attr("required","required");
			$("#invPercentPre").removeAttr("disabled");
			$("#invPercentPre").attr("required","required");
			$("#invRangeSuffixW").removeAttr("disabled");
			$("#invRangeSuffixW").attr("required","required");
			$("#invPercentMid").removeAttr("disabled");
			$("#invPercentMid").attr("required","required");
			$("#invPercentSuffix").removeAttr("disabled");
			$("#invPercentSuffix").attr("required","required");
			$("#isHasSubId").show();					
		}else{			
			$("#isHasSubYes").iCheck("check");
			$("#invRangePreW").removeAttr("required");
			$("#invRangePreW").attr("disabled");
			$("#invPercentPre").removeAttr("required");
			$("#invPercentPre").attr("disabled");
			$("#invRangeSuffixW").removeAttr("required");
			$("#invRangeSuffixW").attr("disabled");
			$("#invPercentMid").removeAttr("required");
			$("#invPercentMid").attr("disabled");
			$("#invPercentSuffix").removeAttr("required");
			$("#invPercentSuffix").attr("disabled");
			$("#isHasSubId").hide();
		}
	});
	
	/**提交表单*/
	$("#brandSubmit").on("click", function() {			
		var channelInfo = "";/* $("#channelInfo").val*/
		var trList = $("#auBrand").find("tbody").find("tr");
		for(var i=0 ;i<trList.length;i++){
			var tdArr = trList.eq(i).find("td");
			var channelCode = tdArr.eq(0).find("input[name=channelCode]").val();
			var channelName = tdArr.eq(1).find("input[name=channelName]").val();
			var sourceSys = tdArr.eq(2).find("input[name=sourceSys]").val();
			var isDefault = 0;
			if(tdArr.eq(3).find("input[name=isDefault]").is(":checked")){
				isDefault = 1;
			}
			channelInfo = channelInfo + channelCode+";"+channelName+";"+sourceSys+";"+isDefault+"#";
		}
		if(channelInfo != ""&&channelInfo!=null){
			$("#channelInfo").val(channelInfo.substring(0,channelInfo.length-1));
		}
		if($("input[name=isHasSub]:checked").attr("id")=='isHasSubNo'){
			$("#invPercent").val($("#invPercentPre").val()/100+";"+$("#invPercentMid").val()/100+";"+$("#invPercentSuffix").val()/100);		
			$("#invRange").val($("#invRangePreW").val()+";"+$("#invRangeSuffixW").val());				
		}		
		$("#brandForm").submit();
	});
	/**新增品牌渠道编码*/
	$("#lineAdd").on("click", function() {		
		var trHtml = "<tr><td class='text-center'><div class='row'><div class='col-sm-12'><div class='col-sm-1'></div><div class='form-group col-sm-10'><input type='text' name='channelCode' class='form-control' required>"
			+"<div class='help-block with-errors'></div></div></div></div></td><td class='text-center'><div class='row'><div class='col-sm-12'>"
			+"<div class='col-sm-1'></div><div class='form-group col-sm-10'><input type='text' name='channelName' class='form-control' required>"
			+"<div class='help-block with-errors'></div></div></div></div></td>" 
			+"<td class='text-center'><div class='row'><div class='col-sm-12'><div class='col-sm-1'></div><div class='form-group col-sm-10'><input type='text' name='sourceSys' class='form-control' required='required'><div class='help-block with-errors'></div></div></td>"
			+"<td class='text-center'><input type='radio' value='1' name='isDefault'/>"
			+"</td><td class='text-center'><div class='row'><div class='col-sm-12'><div class='col-sm-1'></div><div class='form-group col-sm-10'>"											        	
			+"<button class='btn btn-primary btn-sm' onclick='deleteCurrentRow(this);' <i class='fa fa-minus-circle'></i> 删除</button></div></div></div></td></tr>";
		
		$("#auBrand").append(trHtml);
		$("input[name=isDefault]").iCheck({
	        checkboxClass: 'icheckbox_square-aero',
	        radioClass: 'iradio_square-aero',
	        increaseArea: '20%' // optional
	    });
		if($("#auBrand").children()[1].children.length==2){
			$("#auBrand").children()[1].children[0].children[4].children[0].style.display="block";;
		}		
	});
});


/*****************************************************/
/***************3. FUNCTION 函数和方法*****************/
/*****************************************************/
/**校验品牌编码唯一性*/
function checkUniqueBrandCode(e, nv) {
	var data = wms.syncPost(pagebase + "/brand/checkBrandCodeUnique", { "brandCode":$("#brandCode").val() });
	if (data) {
		return wms.validator.SUCCESS;
	}	
	return i18n.t("brand-code-exist");
}

function checkInvRangePre(e,newValue) {
	var invRangePre = $("#invRangePreW").val();
	var invRangeSuffix = $("#invRangeSuffixW").val();
	if($("#invRangeSuffixW").val()==null||$("#invRangeSuffixW").val()==""){
		return wms.validator.SUCCESS;
	}
	if(parseInt(invRangeSuffix) <= parseInt(invRangePre)){
		return i18n.t("brand-inv-range-less");
	}
	return wms.validator.SUCCESS;
}

function checkInvRangeSuffix(e,newValue) {
	var invRangePre = $("#invRangePreW").val();
	var invRangeSuffix = $("#invRangeSuffixW").val();
	if($("#invRangePreW").val()==null||$("#invRangePreW").val()==""){
		return wms.validator.SUCCESS;
	}
	if(parseInt(invRangePre) >= parseInt(invRangeSuffix)){
		return i18n.t("brand-inv-range-over");
	}
	return wms.validator.SUCCESS;
}

//非负整数校验
function checkPositiveInteger(e,val){
	var reg=/^\d+$/;
	if(reg.test(e.val())){
		if(e.val().length>1){
			e.val(e.val().replace(/\b(0+)/gi,""));
		}
		if(e.attr("id")=="invRangePreW"&&e.val()==0){
			return i18n.t("brand-inv-range-over");
		}
		return wms.validator.SUCCESS;
	}
	return i18n.t("brand-number-range");	
}

function deleteCurrentRow(obj){
	var tr=obj.parentNode.parentNode.parentNode.parentNode.parentNode;
	var tbody=tr.parentNode;	
	tbody.removeChild(tr);
	if(tbody.rows.length==1){
		tbody.rows[0].children[4].children[0].style.display="none";
	}
}