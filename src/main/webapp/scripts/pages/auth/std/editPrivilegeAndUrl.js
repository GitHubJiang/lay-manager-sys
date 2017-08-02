/*****************************************************/
/***************1. 基本信息的设定***********************/
/*****************************************************/
  
wms.addResource("zh-CN",{
   'url-msg':'提示信息',
   'url-save-success':'修改成功',
   'url-save-error':'修改失败'
});

/*****************************************************/
/***************2. 页面加载时设定***********************/
/*****************************************************/
wms.addReadyFunc(function(){
	$(".url-save").click(function(){
		var url ="/auth/url/add";
		var rowId=$(this).attr("row-id");
		var textarea=$("textarea[textarea-id='"+rowId+"']");
		var acl=textarea.attr("data-acl");
		var type=textarea.attr("data-type");
		var urlStr=textarea.val();
		var data={
			'acl':acl,
			'type':type,
			'url':urlStr
		};
		$.ajax({
			type : "post",
			url : url,
			dataType:"json",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify(data),
			success : function(data) {
				wms.frame.unblockUIItem("div.content");
				 if(data && data.exception){
	                    if(wms.defaultErrors["e-"+data.exception.statusCode]){
	                        wms.frame.notifyError(
	                            data.exception.message || i18n.t(wms.defaultErrors["e-"+data.exception.statusCode])
	                        );
	                    }else{
	                        wms.frame.notifyWarning(data.exception.message);
	                    }
	                    return;
	              }
				if (data.isSuccess) {
					wms.frame.notifySuccess(i18n.t("url-msg"),i18n.t("url-save-success"));
				}else{
					wms.frame.notifySuccess(i18n.t("url-msg"),i18n.t("url-save-error"));
				}
			},
			error : function(data) {
				wms.frame.notifySuccess(i18n.t("url-msg"),i18n.t("url-save-error"));
			}
		});
	})
});


