/*****************************************************/
/***************1. 基本信息的设定***********************/
/*****************************************************/
  
wms.addResource("zh-CN",{
	'info':'提示信息'
	,'type-enable':'正常'
	,'type-disable':'禁用'
	,'select-rm':'请选择需要删除的URL'
    ,'select-open':'请选择需要启用的URL'
    ,'select-lock':'请选择需要禁用的URL'
	,'state-err':'修改失败'
    ,'edit-err':'编辑失败'
    ,'commonUserUpload-info':'提示信息'
    ,'commonUserUpload-select-noFile':'请选择文件'
    ,'commonUserUpload-select-fileErr':'文件格式错误，请上传csv文件'
});


var tableExtraBatch = [{
    name: "删除",
    icon: "icon-trash-empty",
    action: function(checked) {
        if (checked) {
        	var arr = new Array();
            for (var i = 0; i < checked.length; i++) {
            	arr.push($(checked[i]).attr("tid"));
            }
            if (arr.length == 0) {
                wms.frame.notifyError(i18n.t("info"), i18n.t("select-rm"));
                return;
            } else {
            	showStateWindow(arr.join());
            }
        }
    }
}];

/*****************************************************/
/***************2. 页面加载时设定***********************/
/*****************************************************/

function showStateWindow(ids) {
	$(".modal-lifecycle .text-title").html("确定删除吗？");
	$(".modal-lifecycle form input[name='ids']").val(ids);
	$('.modal-lifecycle').modal('show');
}


wms.addReadyFunc(function(){
	$("form .btn-sm:submit").attr("type","button").on("click",function(){
		var _form = $(this).closest("form");
		_form.find("input[name='page']").removeAttr("value");
		_form.submit();
	});
	$(".closeBtn").on("click",function(){
		$(this).closest(".modal").modal('hide');
	});
	$(".btn-add").on("click",function() {
		var form = $(".add-model-form form");
		form[0].reset();
    	var id = $(this).closest("tr").find("input[type='checkbox']:first").attr("tid");
		if(id) {
			$(".form-title").html("修改URL");
		} else {
			$(".form-title").html("新增URL");
		}
		wms.asyncPost(pagebase+"/uac/url/get", {id:id},{successHandler:function(data, textStatus){
			if(data){
				form.fill(data);
				$('.add-model-form').modal('show');
			} else {
				wms.frame.notifyError(i18n.t("info"),i18n.t("edit-err"));
			}
		}});
	});
	$(".subBtn").on("click",function(){
		var form = $(this).closest("form");
		if(!form.validator().data('bs.validator').validate().isIncomplete()) {
			var data = form.serializeArray();
			wms.asyncPost(pagebase+"/uac/url/add", data,{successHandler:function(data, textStatus){
				if(data){
					if(data.isSuccess) {
						wms.frame.notifySuccess(i18n.t("info"), data["description"]);
						setTimeout('eval($("#queryForm").submit())',1000);
					} else {
						wms.frame.notifyError(i18n.t("info"),data["description"]);
					}
				} else {
					wms.frame.notifyError(i18n.t("info"),i18n.t("edit-err"));
				}
			}});
		}
	});
	$(".state").on("click",function() {
		var id = $(this).closest("tr").find("input[type='checkbox']:first").attr("tid");
		var lifecycle = $(this).attr("state");
		showStateWindow(id,lifecycle); 
	});
	$(".btn-lifecycle").on("click",function() {
		var data = $(this).closest("form").serializeArray();
		wms.asyncPost(pagebase+"/uac/url/state", data,{successHandler:function(data, textStatus){
			if(data){
				if(data.isSuccess) {
					wms.frame.notifySuccess(i18n.t("info"), data["description"]);
					setTimeout('eval($("#queryForm").submit())',1000);
				} else {
					wms.frame.notifyError(i18n.t("info"),data["description"]);
				}
			} else {
				wms.frame.notifyError(i18n.t("info"),i18n.t("state-err"));
			}
		}});
	});
	
    // modal 居中
    function centerModal() {
        $(this).css('display', 'block');
        var $dialog = $(this).find(".modal-dialog");
        var offset = ($(window).height() - $dialog.height()) / 2;
        $dialog.css("margin-top", offset);
    }

    $('.modal').on('show.bs.modal', centerModal);
    $(window).on("resize", function () {
        $('.modal:visible').each(centerModal);
    });
	
});
(function(className){
	setTimeout(function(){
		$(className).multipleSelect({
			filter: true,
			selectAll: false,
			single: true,
			isShowButton: false,
			multipleWidth: 55,
			noMatchesFound: "没有找到匹配项",
			width: "100%"
		});
	},1);
})(".ms-select");
$(".btn-import").on("click",function() {
	$("#tips").html("");
	$("#file").val("");
})
$(".btn-upload").on("click",function() {
	var form = $(this).closest("form");
	var fileName = form.find("input[name='file']").val();
	if(fileName.length == 0){
		wms.frame.notifyError(i18n.t("commonUserUpload-info"),i18n.t("commonUserUpload-select-noFile"));
		return ;
	}
	
	var fileReg = /^(\S|\s)*\.(xlsx|xls)$/;
	if(!fileReg.test(fileName)){
		wms.frame.notifyError(i18n.t("commonUserUpload-info"),i18n.t("commonUserUpload-select-fileErr"));
		return ;
	}
	form.ajaxForm({
		success: function (data) {
			$(".blockUI").remove();
			$("#tips").html(data["returnUrl"]);
			if(data.isSuccess) {
				setTimeout('eval($("#queryForm").submit())',1000);
			}
        }
	});
	form.submit();
});
