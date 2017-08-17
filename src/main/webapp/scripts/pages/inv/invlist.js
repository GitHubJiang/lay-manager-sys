
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
	
	
	$("#invFile").fileinput({
        language: 'zh', //设置语言
        //uploadUrl:"http://127.0.0.1/testDemo/fileupload/upload.do", //上传的地址
        allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
       //uploadExtraData:{"id": 1, "fileName":'123.mp3'},
        uploadAsync: false, //默认异步上传
        showUpload:false, //是否显示上传按钮
        showRemove :false, //显示移除按钮
        showPreview :false, //是否显示预览
        showCaption:false,//是否显示标题
        browseClass:"btn btn-primary", //按钮样式    
       dropZoneEnabled: false//是否显示拖拽区域
       //minImageWidth: 50, //图片的最小宽度
       //minImageHeight: 50,//图片的最小高度
       //maxImageWidth: 1000,//图片的最大宽度
       //maxImageHeight: 1000,//图片的最大高度
        //maxFileSize:0,//单位为kb，如果为0表示不限制文件大小
       //minFileCount: 0,
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



