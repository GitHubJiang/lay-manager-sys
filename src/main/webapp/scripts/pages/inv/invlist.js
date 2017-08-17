
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

function exportInventory(){
	var storeNum = $("#dataTable tbody tr").length;
	if(storeNum == 0){
		wms.frame.notifyError(i18n.t("zero-data"));
	}else{
		$("#exportInvForm").submit();
	}
}



