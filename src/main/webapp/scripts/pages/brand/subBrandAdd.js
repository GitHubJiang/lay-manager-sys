/*****************************************************/
/***************1. 基本信息的设定***********************/
/** ************************************************** */
wms.addResource("zh-CN", {
	
});


/** ************************************************** */
/** *************2. 页面加载时设定********************** */
/** ************************************************** */
$(function(){
		
	
	$("#invRangePreW").on("change", function() {		
		$("#invRangePreR").val($("#invRangePreW").val());		
	});
	
	$("#invRangeSuffixW").on("change", function() {		
		$("#invRangeSuffixR").val($("#invRangeSuffixW").val());
	});
	
	
	/**提交表单*/
	$("#brandSubmit").on("click", function() {			
		$("#invPercent").val($("#invPercentPre").val()/100+";"+$("#invPercentMid").val()/100+";"+$("#invPercentSuffix").val()/100);		
		$("#invRange").val($("#invRangePreW").val()+";"+$("#invRangeSuffixW").val());					
		$("#brandForm").submit();
	});

});


/*****************************************************/
/***************3. FUNCTION 函数和方法*****************/
/*****************************************************/
/**校验品牌编码+子品牌编码 唯一性*/
function checkUniqueSubCode(e, nv) {
	var data = wms.syncPost(pagebase + "/brand/sub/checkSubCodeUnique", { "subCode":$("#subCode").val()});
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
		return  i18n.t("brand-inv-range-over");
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
