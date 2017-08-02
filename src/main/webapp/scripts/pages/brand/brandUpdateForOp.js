/*****************************************************/
/***************1. 基本信息的设定***********************/
/** ************************************************** */
wms.addResource("zh-CN", {
	
});


/** ************************************************** */
/** *************2. 页面加载时设定********************** */
/** ************************************************** */
$(function(){
	
	
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
			$("#repushRuleHour").iCheck("check");
			$("#deliHour").attr("required","required");
			$("#deliHour").removeAttr("disabled");	
			$("#deliTimes").removeAttr("disabled","disabled");
			$("#isDeliConfig").show();				
		}else{			
			$("#deliMaxStore").attr("disabled","disabled");
			$("#deliExpire").attr("disabled","disabled");
			$("#deliCondition").attr("disabled","disabled");
			$(".repushRule").attr("disabled","disabled");
			$("#deliHour").attr("disabled","disabled");							
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
	
	/**提交表单*/
	$("#brandSubmit").on("click", function() {		
		$("#brandForm").submit();
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