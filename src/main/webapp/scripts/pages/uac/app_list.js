/*****************************************************/
/***************1. 基本信息的设定***********************/
/*****************************************************/
  
wms.addResource("zh-CN",{
	'info':'提示信息'
	,'type-enable':'正常'
	,'type-disable':'禁用'
	,'select-rm':'请选择需要删除的应用'
    ,'select-open':'请选择需要启用的应用'
    ,'select-lock':'请选择需要禁用的应用'
	,'state-err':'修改失败'
    ,'edit-err':'编辑失败'
});


var tableExtraBatch = [{
    name: "禁用",
    icon: "icon-lock",
    action: function(checked) {
        if (checked) {
            var arr = new Array();
            for (var i = 0; i < checked.length; i++) {
                arr.push($(checked[i]).attr("tid"));
            }
            if (arr.length == 0) {
                wms.frame.notifyError(i18n.t("info"), i18n.t("select-lock"));
                return;
            } else {
                showStateWindow(arr.join(), 0);
            }
        }
    }
}, {
    name: "正常",
    icon: "icon-lock-open",
    action: function(checked) {
        if (checked) {
            var arr = new Array();
            for (var i = 0; i < checked.length; i++) {
                arr.push($(checked[i]).attr("tid"));
            }
            if (arr.length == 0) {
            	wms.frame.notifyError(i18n.t("info"), i18n.t("select-open"));
                return;
            } else {
                showStateWindow(arr.join(), 1);
            }
        }
    }
}];

/*****************************************************/
/***************2. 页面加载时设定***********************/
/*****************************************************/


function showStateWindow(ids, lifecycle) {
    if (lifecycle == 0) {
        $(".modal-lifecycle .text-title").html("确定禁用吗？");
    }
    if (lifecycle == 1) {
        $(".modal-lifecycle .text-title").html("确定启用吗？");
    }
    $(".modal-lifecycle form input[name='ids']").val(ids);
    $(".modal-lifecycle form input[name='lifecycle']").val(lifecycle);
    $('.modal-lifecycle').modal('show');
}
function showMessage(message) {
	$(".modal").modal("hide");
	$('.modal-message .text-title').html(message);
	$('.modal-message').modal('show');
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
		$("#pri tr:not(:first)").remove();
    	$("#groupFun").empty();
    	var id = $(this).closest("tr").find("input[type='checkbox']:first").attr("tid");
		if(id) {
			$(".form-title").html("修改应用");
		} else {
			$(".form-title").html("新增应用");
		}

		wms.asyncPost(pagebase+"/uac/app/get", {id:id},{successHandler:function(data, textStatus){
			if(data){
				form.fill(data);
				var registerByUser = data["registerByUser"] == true?1:0;
				$('.add-model-form [name=registerByUser]').removeAttr("checked").iCheck('uncheck').filter("[value="+registerByUser+"]").iCheck('check');
				
				$('.add-model-form').modal('show');
				
			} else {
				wms.frame.notifyError(i18n.t("info"),i18n.t("edit-err"));
			}
		}});
	});
	$(".subBtn").on("click",function(){
		var form = $(this).closest("form");
		var appKey = form.find("input[name='appkey']").val();
		var rg = /^[A-Za-z0-9_-]{2,50}$/g;
		if(!rg.test(appKey)) {
			wms.frame.notifyError(i18n.t("提示"),i18n.t("appkey格式错误"));
			return;
		}
		var rgs = /^[A-Za-z0-9_-]{8,50}$/g;
		var secret = form.find("input[name='secret']").val();
		if(!rgs.test(secret)) {
			wms.frame.notifyError(i18n.t("提示"),i18n.t("secret格式错误"));
			return;
		}
		if(!form.validator().data('bs.validator').validate().isIncomplete()) {
			var data = form.serializeArray();
			wms.asyncPost(pagebase+"/uac/app/add", data,{successHandler:function(data, textStatus){
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
		wms.asyncPost(pagebase+"/uac/app/state", data,{successHandler:function(data, textStatus){
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
