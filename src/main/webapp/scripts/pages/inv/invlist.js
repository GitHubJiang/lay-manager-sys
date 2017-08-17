
/** ************************************************** */
/** *************2. 页面加载时设定********************** */
/** ************************************************** */
wms.addReadyFunc(function() {
	
	$("#queryFormBtn").on("click", function() {
		search();
	});
    
	$(".btn-upload").on("click",function(){
		doUpload();
	});
});

$("#invFile").fileinput({
    language: 'zh', //设置语言
    showPreview :true, //是否显示预览
    uploadAsync: false, //默认异步上传
    showUpload:false, //是否显示上传按钮
    showRemove :false, //显示移除按钮
    allowedFileExtensions: ['xlsx', 'xls']//接收的文件后缀
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
		wms.frame.notifyError("没有数据");
	}else{
		$("#exportInvForm").submit();
	}
}

function doUpload() {  
    var formData = new FormData($('#uploadForm')[0]);  
    $.ajax({  
         url: pagebase+'/inv/inventory/importInv' ,  
         type: 'POST',  
         data: formData,  
         async: false,  
         cache: false,  
         contentType: false,  
         processData: false,  
         success: function (returndata) { 
        	 if(returndata.code=='1'){
        		 wms.frame.notifySuccess("提示信息","库存数据导入成功");
        		 setTimeout('search()',100);
        	 }else{
        		 wms.frame.notifyError("提示信息",returndata.msg);
        	 }
        	 
         },  
         error: function (returndata) {  
        	 wms.frame.notifyError("提示信息",returndata);
         }  
    });  
} 



