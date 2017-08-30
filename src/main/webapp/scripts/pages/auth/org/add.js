/*****************************************************/
/***************1. 基本信息的设定***********************/
/** ************************************************** */

/** ************************************************** */
/** *************2. 页面加载时设定********************** */
/** ************************************************** */
$(function(){	

});


/**校验ACL的唯一性*/
function checkUniqueCode(e, nv) {
	var data = wms.syncPost(pagebase + "/check/checkUniqueCode", { "table":"au_operation_unit","fieldValue":$("#unitCode").val(),"id":$("#unitId").val(),"fieldName":"code" });
	if (data == true) {
		return wms.validator.SUCCESS;
	}	
	return "此组织编码已经存在";
}