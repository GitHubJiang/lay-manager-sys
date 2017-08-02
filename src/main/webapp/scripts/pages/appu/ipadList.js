/*****************************************************/
/***************1. 基本信息的设定***********************/
/** ************************************************** */
wms.addResource("zh-CN", {
	"empty_selected" : "请勾选需要删除的行",
	"mapping_reset" : "重推"
});

/** ************************************************** */
/** *************2. 页面加载时设定********************** */
/** ************************************************** */
$(function(){

	$("#queryFormBtn").on("click", function(){
		search();
	});
	
});


/*****************************************************/
/***************3. FUNCTION 函数和方法*****************/
/*****************************************************/
function search() {
	$(".startPage").val("1");
	$("#queryForm").submit();
}

function updateUpdateStrategy(id, updateStrategy) {
	$.blockUI();
	wms.asyncPost(pagebase + "/appu/strategy", { "id":id, "updateStrategy":updateStrategy }, {
		successHandler : function(data, textStatus) {
			wms.frame.notifySuccess(i18n.t("appu-operate-success"));
			$.unblockUI();
			search();
		}
	});
}