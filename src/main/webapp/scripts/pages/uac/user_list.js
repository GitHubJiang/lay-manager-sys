/**************************************************/
/***************1. 基本信息的设定***********************/
wms.addResource("zh-CN",{
	'info':'提示信息',
	'type-enable':'正常',
	'type-disable':'禁用',
	'resetPwd-suc':'重置密码成功，最新密码已发送至用户邮箱',
	'resetPwd-fail':'重置密码失败',
	'resetPwd-newPwd':'新密码不能为空',
	'resetPwd-length':'密码 由长度为8~50个字符组成',
	'resetPwd-rnewPwd':'重复新密码不能为空',
	'resetPwd-error':'两次输入密码不一致',
	'resetPwd-success':'重置密码成功',
	'syncInfo-suc':'同步用户信息成功',
	'lockUser-suc':'解锁用户成功',
	'lockUser-fail':'解锁用户失败',
	'select-rm':'请选择需删除的用户',
	'select-enable':'请选择需启用的用户',
	'select-disable':'请选择需禁用的用户',
	'select-sync':'请选择需同步的用户',
	'select-lockUser':'请选择需解锁的用户',
	'del-suc':'删除成功',
	'del-fail':'删除失败',
	'commonUserUpload-info':'提示信息',
	'commonUserUpload-select-noFile':'请选择文件',
	'commonUserUpload-select-fileErr':'文件格式错误，请上传csv文件'
});

/*****************************************************/
/***************2. 页面加载时设定***********************/
/*****************************************************/
$(document).ready(function(){
	$("form .btn-sm:submit").attr("type","button").on("click",function(){
		var _form = $(this).closest("form");
		_form.find("input[name='page']").removeAttr("value");
		_form.submit();
	});
	$(".closeBtn").on("click",function(){
		$(this).closest(".modal").modal('hide');
	});
	$(".state").on("click",function() {
		var lifecycle = $(this).attr("state");
		var id = $(this).closest("tr").find("input[type='checkbox']:first").attr("cuid");
		showStateWindow(id, lifecycle);
	});
	$(".btn-lifecycle").on("click",function() {
		var data = $(this).closest("form").serializeArray();
		wms.asyncPost(pagebase+"/uac/user/update/lifecycle", data,{successHandler:function(data, textStatus){
			if(data){
				if(data.isSuccess) {
					wms.frame.notifySuccess(i18n.t("info"), data["description"]);
					setTimeout('eval($("#queryForm").submit())',1000);
				} else {
					wms.frame.notifyError(i18n.t("info"),data["description"]);
				}
			} else {
				wms.frame.notifyError(i18n.t("info"),i18n.t("error"));
			}
		}});
	});
	
	$(".btn-resetPwd").on("click",function() {
		var id = $(this).closest("tr").find("input[type='checkbox']:first").attr("cuid");
		wms.asyncPost(pagebase+"/uac/user/resetPwd", {id:id},{successHandler:function(data, textStatus){
			if(data){
				if(data.isSuccess) {
					wms.frame.notifySuccess(i18n.t("info"), data["description"]);
				} else {
					wms.frame.notifyError(i18n.t("info"),data["description"]);
				}
			} else {
				wms.frame.notifyError(i18n.t("info"),i18n.t("error"));
			}
		}});
	});
	
	$(".btn-syncInfo").on("click",function() {
		var id = $(this).closest("tr").find("input[type='checkbox']:first").attr("cuid");
		syncInfo(id);
	});
	
	$(".btn-unlockUser").on("click",function() {
		var id = $(this).closest("tr").find("input[type='checkbox']:first").attr("cuid");
		unlockUser(id);
	});
	
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
function syncInfo(ids) {
	wms.asyncPost(pagebase+"/uac/user/batchSyncInfo", {ids:ids},{successHandler:function(data, textStatus){
		if(data){
			if(data.isSuccess) {
				wms.frame.notifySuccess(i18n.t("info"), data["description"]);
			} else {
				wms.frame.notifyError(i18n.t("info"),data["description"]);
			}
		} else {
			wms.frame.notifyError(i18n.t("info"),i18n.t("error"));
		}
	}});
}
function unlockUser(ids) {
	wms.asyncPost(pagebase+"/uac/user/unlockUser", {ids:ids},{successHandler:function(data, textStatus){
		if(data){
			if(data.isSuccess) {
				wms.frame.notifySuccess(i18n.t("info"), data["description"]);
			} else {
				wms.frame.notifyError(i18n.t("info"),data["description"]);
			}
		} else {
			wms.frame.notifyError(i18n.t("info"),i18n.t("error"));
		}
	}});
}
function showStateWindow(ids,lifecycle) {
	if(lifecycle == 0) {
		$(".modal-lifecycle .text-title").html("确定禁用吗？");
	}
	if(lifecycle == 1) {
		$(".modal-lifecycle .text-title").html("确定启用吗？");
	}
	if(lifecycle == 2) {
		$(".modal-lifecycle .text-title").html("确定删除吗？");
	}
	$(".modal-lifecycle form input[name='ids']").val(ids);
	$(".modal-lifecycle form input[name='lifecycle']").val(lifecycle);
	$('.modal-lifecycle').modal('show');
}

var tableExtraBatch = [{
    name: "删除",
    icon: "icon-trash-empty",
    action: function(checked) {
        if (checked) {
        	var arr = new Array();
            for (var i = 0; i < checked.length; i++) {
            	arr.push($(checked[i]).attr("cuid"));
            }
            if (arr.length == 0) {
                wms.frame.notifyError(i18n.t("info"), i18n.t("select-rm"));
                return;
            } else {
            	showStateWindow(arr.join(), 2);
            }
        }
    }
}, {
    name: "禁用",
    icon: "icon-lock",
    action: function(checked) {
    	if (checked) {
        	var arr = new Array();
            for (var i = 0; i < checked.length; i++) {
            	arr.push($(checked[i]).attr("cuid"));
            }
            if (arr.length == 0) {
                wms.frame.notifyError(i18n.t("info"), i18n.t("select-disable"));
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
            	arr.push($(checked[i]).attr("cuid"));
            }
            if (arr.length == 0) {
                wms.frame.notifyError(i18n.t("info"), i18n.t("select-enable"));
                return;
            } else {
            	showStateWindow(arr.join(), 1);
            }
        }
    }
}, {
    name: "同步信息",
    icon: "icon-download",
    action: function(checked) {
    	if (checked) {
        	var arr = new Array();
            for (var i = 0; i < checked.length; i++) {
            	arr.push($(checked[i]).attr("cuid"));
            }
            if (arr.length == 0) {
                wms.frame.notifyError(i18n.t("info"), i18n.t("select-sync"));
                return;
            } else {
            	syncInfo(arr.join());
            }
        }
    }
}, {
    name: "解除锁定",
    icon: "icon-download",
    action: function(checked) {
    	if (checked) {
        	var arr = new Array();
            for (var i = 0; i < checked.length; i++) {
            	arr.push($(checked[i]).attr("cuid"));
            }
            if (arr.length == 0) {
                wms.frame.notifyError(i18n.t("info"), i18n.t("select-lockUser"));
                return;
            } else {
            	unlockUser(arr.join());
            }
        }
    }
}];

