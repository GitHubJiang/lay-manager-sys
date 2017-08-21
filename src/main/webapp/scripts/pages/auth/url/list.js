/** ************************************************** */
/** *************1. 页面加载时设定********************** */
/** ************************************************** */
$(function(){
	 $("#queryFormBtn").on("click", function() {
		 search();
	 });
	 
	 //删除相关
	 $(".state").on("click",function() {
	 	var id = $(this).attr("data-id");
	 	$("#url-id").val(id);
	 });
	 
	 $("#deleteBtn").on("click",function() {
		 	$("#deleteForm").submit();
	 });
	 
	//关闭弹窗
	 $("#closeBtn").on("click",function() {
	 	$(".modal-lifecycle form input[name='id']").val("");
	 });
	 
	 $(".btn-add").on("click",function() {
		$("#urlId").val($(this).attr("data-id"));
	 	$("#label-url").val($(this).attr("data-url"));
	 });
	 
	 var flag = false;
	 $("#submitBtn").on("click",function(){
		 	var check = $("#addForm").data("bs.validator").checkValid();
		    if(!check){
		    	return "";
		    }
		 	//防止表单重复提交
			if(flag){
				wms.frame.notifyError("不能重复提交");
				return ;
			}
			flag = true;
			var form = $("#addForm");
			var data = form.serializeArray();		

			wms.asyncPost(pagebase+"/auth/url/update", data,{successHandler:function(data, textStatus){
				if(data){
		 			if('1'==data.code) {
						wms.frame.notifySuccess("提示信息", data["msg"]);
		 				setTimeout('eval($("#queryForm").submit())',10);
					} else {
						wms.frame.notifyError("提示信息",data["msg"]);
					}
				} else {
					wms.frame.notifyError("提示信息",data["msg"]);
				}
			}});
			
		});
});

/*****************************************************/
/***************2. FUNCTION 函数和方法*****************/
/*****************************************************/
function search() {
	$(".startPage").val("1");
	$("#queryForm").submit();
}


