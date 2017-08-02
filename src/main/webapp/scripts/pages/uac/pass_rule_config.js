/**************************************************/
/***************1. 基本信息的设定***********************/
wms.addResource("zh-CN",{
	'passRuleConfigList-info':'提示信息',
	'passRuleConfigList-success' : '密码规则设置成功',
	'passRuleConfigList-fail' : '密码规则设置失败',
	'passRuleConfigList-prcHowLong-err' : '口令使用期限不能为空',
	'passRuleConfigList-prcRepeSet-err' : '保留口令次数不能为空',
	'passRuleConfigList-pcAlertDays-err' : '口令过期天数不能为空',
	'passRuleConfigList-prcUnlocakType-err' : '口令解锁时间不能为空',
	'passRuleConfigList-prcUnlocakCount-err' : '口令错误次数导致锁定不能为空'
});

/*****************************************************/
/***************2. 页面加载时设定***********************/
/*****************************************************/
$(document).ready(function(){
	
	$(".role-save").click(function(){
		var checkboxStr = "";
		var radioStr = "";
		
		//获取选中的 checkbox
		$("input:checkbox[name='check_name']:checked").each(function(i){
			if(0 == i){
				checkboxStr = $(this).val();
			}else{
				checkboxStr += ","+ $(this).val();
			}
		});
		//获取选中的radio
		$("input:radio:checked").each(function(i){
			if(0 == i){
				radioStr = $(this).val();
			}else{
				radioStr += ","+ $(this).val();
			}
		});
		
		//选中的选项
		var selectStr = "";
		if(checkboxStr.length == 0 && checkboxStr.length > 0){
			selectStr = radioStr;
		}else if(checkboxStr.length > 0 && checkboxStr.length == 0){
			selectStr = checkboxStr;
		}else if(checkboxStr.length > 0 && checkboxStr.length > 0){
			selectStr = checkboxStr + "," + radioStr;
		}
		
		//选中的选项值,字段间用 | 分隔，字段里用 : 分隔
		var selectValue = "";
		
		//口令使用期限
		var prcHowLong = "";
		if($("div[name='prcHowLong'] > div").find("input").attr("checked") == 'checked'){
			prcHowLong = $("#prcHowLong").val();
			if(prcHowLong.length == 0){
				$("#prcHowLong").focus();
				wms.frame.notifyError(i18n.t("passRuleConfigList-info"),i18n.t("passRuleConfigList-prcHowLong-err"));
				return ;
			}
			//加入选项值
			if(selectValue.length > 0){
				selectValue += ",PRC_HOW_LONG:" + prcHowLong;
			}else{
				selectValue = "PRC_HOW_LONG:" + prcHowLong;
			}
		}
		//保留几次密码
		var prcRepeSet = "";
		if($("div[name='prcRepeSet'] > div").find("input").attr("checked") == 'checked'){
			prcRepeSet = $("#prcRepeSet").val();
			if(prcRepeSet.length == 0){
				$("#prcRepeSet").focus();
				wms.frame.notifyError(i18n.t("passRuleConfigList-info"),i18n.t("passRuleConfigList-prcRepeSet-err"));
				return ;
			}
			//加入选项值
			if(selectValue.length > 0){
				selectValue += ",PRC_REPE_SET:" + prcRepeSet;
			}else{
				selectValue = "PRC_REPE_SET:" + prcRepeSet;
			}
		}
		//过期多少天告警
		var pcAlertDays = "";
		if($("div[name='pcAlertDays'] > div").find("input").attr("checked") == 'checked'){
			pcAlertDays = $("#pcAlertDays").val();
			if(pcAlertDays.length == 0){
				$("#pcAlertDays").focus();
				wms.frame.notifyError(i18n.t("passRuleConfigList-info"),i18n.t("passRuleConfigList-pcAlertDays-err"));
				return ;
			}
			//加入选项值
			if(selectValue.length > 0){
				selectValue += ",PRC_ALERT_DAYS:" + pcAlertDays;
			}else{
				selectValue = "PRC_ALERT_DAYS:" + pcAlertDays;
			}
		}
		//解锁时间
		var prcUnlocakType = "";
		if($("div[name='prcUnlocakType'] > div").find("input").attr("checked") == 'checked'){
			prcUnlocakType = $("#prcUnlocakType").val();
			if(prcUnlocakType.length == 0){
				$("#prcUnlocakType").focus();
				wms.frame.notifyError(i18n.t("passRuleConfigList-info"),i18n.t("passRuleConfigList-prcUnlocakType-err"));
				return ;
			}
			//加入选项值
			if(selectValue.length > 0){
				selectValue += ",PRC_UNLOCAK_TYPE:" + prcUnlocakType;
			}else{
				selectValue = "PRC_UNLOCAK_TYPE:" + prcUnlocakType;
			}
		}
		//错误次数
		var prcUnlocakCount = "";
		if($("div[name='prcUnlocakCount'] > div").find("input").attr("checked") == 'checked'){
			prcUnlocakCount = $("#prcUnlocakCount").val();
			if(prcUnlocakCount.length == 0){
				$("#prcUnlocakCount").focus();
				wms.frame.notifyError(i18n.t("passRuleConfigList-info"),i18n.t("passRuleConfigList-prcUnlocakCount-err"));
				return ;
			}
			//加入选项值
			if(selectValue.length > 0){
				selectValue += ",PRC_UNLOCAK_COUNT:" + prcUnlocakCount;
			}else{
				selectValue = "PRC_UNLOCAK_COUNT:" + prcUnlocakCount;
			}
		}
		
		var data = {"selectStr":selectStr,"selectValue":selectValue};
		
		wms.asyncPost(staticbase + "/uac/pwd/role/update", data,{
			type : "POST",
			successHandler : function(data,textStatus){
				wms.frame.unblockUIItem("div.content");
				if(data.isSuccess){
					wms.frame.notifySuccess(i18n.t("passRuleConfigList-info"),i18n.t("passRuleConfigList-success"));
				}else{
					wms.frame.notifyError(i18n.t("passRuleConfigList-info"),i18n.t("passRuleConfigList-fail"));
				}
			},
			errorHandler : function(textStatus){
				wms.frame.unblockUIItem("div.content");
				wms.frame.notifyError(i18n.t("passRuleConfigList-info"),i18n.t("passRuleConfigList-fail"));
			}
		});
		
	});
	
});

/*****************************************************/
/***************3. FUNCTION 函数和方法*****************/
/**************************************************/
