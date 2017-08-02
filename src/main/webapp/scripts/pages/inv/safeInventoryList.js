/*****************************************************/
/***************1. 基本信息的设定***********************/
/** ************************************************** */
wms.addResource("zh-CN", {
	"empty_selected" : "请勾选需要删除的行",
	"mapping_reset" : "重推"
});

var tableExtraBatch = [
	{
		name : "库存清零",
		icon : "fa fa-cloud-upload",
		action : function(checked) {
			if (checked) {
				var rids = new Array()
				for (var i = 0; i < checked.length; i++) {
					rids[i] = $(checked[i]).data("itemid");
				}
				
				if (rids.length == 0) {
					wms.frame.notifyInfo(i18n.t("empty_selected"));
					return;
				}
				
				resetInvs(rids);
			}
		}
	} 
];

/** ************************************************** */
/** *************2. 页面加载时设定********************** */
/** ************************************************** */
wms.addReadyFunc(function() {
	
	$("#queryFormBtn").on("click", function() {
		search();
	});
	
	$("#edit-btn").on("click",function(){
		updateInv();
	});
    
});


/*****************************************************/
/***************3. FUNCTION 函数和方法*****************/
/*****************************************************/
function search() {
	$(".startPage").val("1");
	$("#queryForm").submit();
}

