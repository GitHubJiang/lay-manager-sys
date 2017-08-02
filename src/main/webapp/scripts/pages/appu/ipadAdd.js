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
	
	$('#uploadForm').ajaxForm({
		success: function (data) {
			$("#appFilePath").val(data);
			wms.frame.notifySuccess(i18n("appu-upload-success") + ":" + $("#appFilePath").val());
			$("div.content").unblock();
        }
	});
	
	$("#uploadSubmit").on("click", function() {
		uploadPadFile();
	});
	
});


/*****************************************************/
/***************3. FUNCTION 函数和方法*****************/
/*****************************************************/
function uploadPadFile() {
	$("#uploadForm").submit();
}

function checkUniqueVersionNo(e, nv) {
	var data = wms.syncPost(pagebase + "/appu/pad/ckversion/", { "brandCode":$("#brandCode").val(), "versionNo":$("#versionNo").val() });
	if (data) {
		return wms.validator.SUCCESS;
	}
	
	return i18n.t("appu-version-exist");
}

function checkAppuForm(form) {
	var result = [];
	
	if ($("#appFilePath").val()) {
		return result;
	}
    
    result.push(i18n.t("appu-upload-first"));
    return result;
}