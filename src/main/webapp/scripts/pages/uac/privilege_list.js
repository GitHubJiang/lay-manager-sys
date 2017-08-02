/*****************************************************/
/***************1. 基本信息的设定***********************/
/*****************************************************/
  
wms.addResource("zh-CN",{
    'info': '提示信息'
    ,'edit-f': '编辑失败'
    ,'select-rm-delete': '请选择需要删除的权限'
    ,'ms': '删除'
    ,'error':'系统错误异常'
    ,'error':'操作失败'
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
                wms.frame.notifyError(i18n.t("info"), i18n.t("select-rm-delete"));
                return;
            } else {
            	showStateWindow(arr.join());
            }
        }
    }
}];

function showStateWindow(ids) {
	$(".modal-lifecycle .text-title").html("确定删除吗？");
	$(".modal-lifecycle form input[name='ids']").val(ids);
	$('.modal-lifecycle').modal('show');
}


/*****************************************************/
/***************2. 页面加载时设定***********************/
/*****************************************************/
wms.addReadyFunc(function(){
	$("form .btn-sm:submit").attr("type","button").on("click",function(){
		var _form = $(this).closest("form");
		_form.find("input[name='page']").removeAttr("value");
		_form.submit();
	});
	$(".closeBtn").on("click",function(){
		$(this).closest(".modal").modal('hide');
	});
	$(".btn-add").on("click",function(){
		var form = $(".add-model-form form");
		form[0].reset();
    	var id = $(this).closest("tr").find("input[type='checkbox']:first").attr("tid");
		if(id) {
			$(".form-title").html("修改权限");
			$(".add-model-form select[name='appId']").closest(".form-group").hide();
			$("#ouTypeId_Msg").show();
		} else {
			$(".form-title").html("添加权限");
			$(".add-model-form select[name='appId']").closest(".form-group").show();
			$("#ouTypeId_Msg").hide();
		}
		wms.asyncPost(pagebase+"/uac/privilege/get", {id:id},{successHandler:function(data, textStatus){
			if(data){
				form.fill(data);
				$(".add-model-form select[name='appId']").multipleSelect('refresh');
				var _appkey = $(".add-model-form select[name='appId'] option:selected").attr("appkey");
	    		if(_appkey) {
	    			queryType(_appkey,data["ouTypeId"]);
	    		}
				randerUrlTable(false,data["priFunMap"]);
				$('.add-model-form').modal('show');
			} else {
				wms.frame.notifyError(i18n.t("info"),i18n.t("edit-f"));
			}
		}});
	});
	
	$(".subBtn").on("click",function(){
		var form = $(this).closest("form");
		var ouTypeId = $('.add-model-form select[name="ouTypeId"]').val();
		if(ouTypeId == '' || ouTypeId == null) {
			wms.frame.notifyError(i18n.t("提示"),i18n.t("组织类型必选！"));
		}
		if(!form.validator().data('bs.validator').validate().isIncomplete()) {
			var data = form.serializeArray();
			wms.asyncPost(pagebase+"/uac/privilege/add", data,{successHandler:function(data, textStatus){
				if(data){
					if(data.isSuccess) {
						wms.frame.notifySuccess(i18n.t("info"), data["description"]);
						setTimeout('eval($("#queryForm").submit())',1000);
					} else {
						wms.frame.notifyError(i18n.t("info"),data["description"]);
					}
				} else {
					wms.frame.notifyError(i18n.t("info"),i18n.t("edit-f"));
				}
			}});
		}
	});
	$(".state").on("click",function() {
		var id = $(this).closest("tr").find("input[type='checkbox']:first").attr("tid");
		showStateWindow(id); 
	});
	$(".btn-lifecycle").on("click",function() {
		var data = $(this).closest("form").serializeArray();
		wms.asyncPost(pagebase+"/uac/privilege/delete", data,{successHandler:function(data, textStatus){
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
	//编辑时 组织类型变更提示
	/*$(".add-model-form select[name='ouTypeId']").on('change',function() {
		var id = $(this).closest("form").find("input[name='id']").val();
		if(id!='' && id!=null) {
			alert('修改原有组织类型，将不会影响原有角色和权限关系，需在角色管理界面重新配置角色和权限之间的关系!');
		}
	})*/
	$(".add-model-form").on("ifUnchecked",".all",function() {
		$(".add-model-form table input:checkbox").filter(":not(.all)").iCheck("uncheck");
	});
	$(".add-model-form").on("ifChanged",".all",function() {
		$(".add-model-form table input:checkbox").filter(":not(.all)").iCheck("check");
	});
	$(".add-model-form").on("ifChanged",".col-all",function() {
		var $index = $(this).closest("th").index();
		$(".add-model-form .table-responsive tbody tr").each(function(){
			$(this).find("td:eq("+$index+")").find(":checkbox").iCheck("check");
		});
	});
	$(".add-model-form").on("ifUnchecked",".col-all",function() {
		var $index = $(this).closest("th").index();
		$(".add-model-form .table-responsive tbody tr").each(function(){
			$(this).find("td:eq("+$index+")").find(":checkbox").iCheck("uncheck");
		});
	});
	$(".add-model-form").on("ifChanged",".row-all",function() {
		$(this).closest("tr").find("input:checkbox:not(.row-all)").iCheck("check");
	});
	$(".add-model-form").on("ifUnchecked",".row-all",function() {
		$(this).closest("tr").find("input:checkbox:not(.row-all)").iCheck("uncheck");
	});
});
function queryType(appKey,ouTypeId) {
	ouTypeId = ouTypeId || $(".add-model-form select[name='ouTypeId']").val();
	wms.asyncPost(pagebase+"/uac/opTypeUnit/tree", {appKey:appKey},{successHandler:function(data, textStatus){
		if(data) {
			var html = "<option value=''>请选择</option>";
			html += spliceOpTypeUnit(data,"",0);
			$(".add-model-form select[name='ouTypeId']").html(html);
			$(".add-model-form select[name='ouTypeId']").val(ouTypeId);
			$(".add-model-form select[name='ouTypeId']").multipleSelect('refresh');
		}
	}});
}
function spliceOpTypeUnit(array,html,index) {
	index = index || 0;
	html = html || "";
	for(var i = 0;i< array.length;i++) {
		var _item = array[i];
		html += "<option value='"+_item["id"]+"'>";
		for(var _i=0;_i<index;_i++) {
			html +="&nbsp;&nbsp;&nbsp;&nbsp;";
		}
		html += _item["text"]+"</option>";
		if(_item["nodes"]) {
			html += spliceOpTypeUnit(_item["nodes"], "", index + 1);
		}
	}
	return html;
}
function randerUrlTable(appId,priFunMap) {
	appId = appId || $(".add-model-form select[name='appId']").val();
	$.post(pagebase+"/uac/privilege/url/list",{appId:appId},function(html){
		$(".add-model-form .table-responsive tbody").html(html);
		
		var checkboxs = $(".add-model-form table tbody input:checkbox");
		checkboxs.iCheck({
	        checkboxClass: 'icheckbox_square-aero',
	        radioClass: 'iradio_minimal',
	        increaseArea: '20%'
	    });
		
		if(priFunMap) {
			for(var key in priFunMap) {
				var _item = priFunMap[key];
				for(var i=0;i<_item.length;i++) {
					checkboxs.filter("[data-id="+key+"][data-role="+_item[i]+"]").iCheck("check");
				}
			}
		}
	});
}
(function(className){
	$(".add-model-form select[name='appId']").multipleSelect({
    	filter: true
		,selectAll: false
		,single: true
		,isShowButton: false
		,multipleWidth: 55
		,noMatchesFound: "没有找到匹配项"
		,width: "100%"
    	,onClick: function(view) {
    		var _appkey = $(".add-model-form select[name='appId'] option[value="+view.value+"]").attr("appkey");
    		if(_appkey) {
    			queryType(_appkey);
    			randerUrlTable(view.value);
    		}
    	}
	});
	
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
