/*****************************************************/
/***************1. 基本信息的设定***********************/
/** ************************************************** */
wms.addResource("zh-CN", {
	"empty_selected" : "请勾选需要删除的行",
	"mapping_reset" : "重推"
});

var tableExtraBatch = [
	{
		name : "重推",
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
				
				resetMappings(rids);
			}
		}
	} 
];

/** ************************************************** */
/** *************2. 页面加载时设定********************** */
/** ************************************************** */
wms.addReadyFunc(function(){
	$("#queryFormBtn").on("click", function() {
		 search();
	 });
	
	//删除相关
	 $(".state").on("click",function() {
	 	var id = $(this).attr("data-id");
	 	$("#user-id").val(id);
	 });
	 
	 $("#deleteBtn").on("click",function() {
		 $("#deleteForm").submit();
	 });
});

/*****************************************************/
/***************3. FUNCTION 函数和方法*****************/
/*****************************************************/
function search() {
	$(".startPage").val("1");
	$("#queryForm").submit();
}

function changeUserStatus(id,status){
	wms.asyncGet(pagebase + "/auth/user/changeStatus/" + id + "/"+ status, {}, {
		successHandler : function(data, textStatus) {
			if(data){
				setTimeout('eval($("#queryForm").submit())',1000);
			}			
		}
	});
}
