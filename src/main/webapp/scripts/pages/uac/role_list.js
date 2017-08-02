/*****************************************************/
/***************1. 基本信息的设定***********************/
/*****************************************************/
  
wms.addResource("zh-CN",{
    'info': '提示信息'
    ,'edit-f': '编辑失败'
    ,'regerr':'编码由 长度为2~50个字符的英文字母，数字或_.- 组成'
    ,'select-rm-delete': '请选择需要删除的角色'
    ,'select-rm-banned': '请选择需要禁用的角色'
    ,'select-rm': '请选择需要启用的角色'
    ,'ms': '删除'
    ,'error':'系统错误异常'
    ,'type-start':'有效'
    ,'type-block':'失效'
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
            	arr.push($(checked[i]).attr("tid"));
            }
            if (arr.length == 0) {
                wms.frame.notifyError(i18n.t("info"), i18n.t("select-rm-banned"));
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
                wms.frame.notifyError(i18n.t("info"), i18n.t("select-rm"));
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

function changeFun(appId,priFunMap) {
	appId = appId || $(".add-model-form select[name='appId']").val();
	var ouTypeId = $(".add-model-form select[name='ouTypeId']").val();
	console.log(ouTypeId);
	$("#groupFun").empty();
	$("#pri tr:not(:first)").remove();
	if(ouTypeId && ouTypeId) {
		asyncXhr(staticbase+'/uac/privilege/ouType/role',{'ouTypeId':ouTypeId,'appId':appId}, {
			type : "POST",
			success : function(data,textStatus){
				if(data){
					var str = "<tbody>";
					$.each(data.dataPris,function(n,value) {
						str +="<tr>";
						str +="<td>"+value.name+"</td>";
						str += "<td><input disabled='disabled' type='checkbox' name='rpsArray' value=\"{acl:'"+value.acl+"'}\" data-acl='"+value.acl+"' data-funcode=''  class='rows-check' data-role=''><input type='hidden' name='rpsArray'></td>";;
						$.each(data.pdts,function(n1,pdt) {
							str +="<td><input type='checkbox' name='rpsArray' value=\"{funCode:'"+pdt.dicValue+"',acl:'"+value.acl+"'}\" data-acl='"+value.acl+"' data-funcode='"+pdt.dicValue+"'  class='rows-check' data-role='"+pdt.dicValue+"'></td>";
						});
						str +="<td><input type=\"checkbox\" class=\"row-all\"></td>";
						str += "</tr>";
					});
					str += "</tbody>";
					$("#pri").append(str);
					
					var groupStr = "";
					$.each(data.groupCommands,function(n,value) {
						groupStr +="<table data-sortable class='table table-hover  table-check-bbottom'>";
						groupStr +="<thead>";
						groupStr +="<tr class='formth-Privilege-bck-t'>";
						groupStr +="<th colspan='3'>分类"+value.groupName+"功能</th>";
						groupStr +="</tr>";
						groupStr +="</thead>";
						groupStr +="<tbody>";
						$.each(value.queryCommands,function(i,command) {
							groupStr +="<tr>";
							groupStr +="<td class='col-xs-2 col-md-2'>"+command.name+"功能</td>";
							$.each(data.pops,function(n1,pdt) {
								groupStr +="<td class='col-xs-1 col-md-1'><input type='checkbox' name='rpsArray' value=\"{funCode:'"+pdt.dicValue+"',acl:'"+command.acl+"'}\" data-acl='"+command.acl+"' data-funcode='"+pdt.dicValue+"' class='rows-check' data-role='"+pdt.dicValue+"'></td>";
							});
							groupStr +="</tr>";
						});
	                      groupStr +="</tbody>";
	                      groupStr +="</table>";
					});
					$("#groupFun").append(groupStr);
					
					var checkboxs = $(".add-model-form table tbody input:checkbox");
					checkboxs.iCheck({
				        checkboxClass: 'icheckbox_square-aero',
				        radioClass: 'iradio_minimal',
				        increaseArea: '20%'
				    });
					if(window.priFunMap) {
						for(var i=0;i<window.priFunMap.length;i++) {
							var _item = window.priFunMap[i];
							checkboxs.filter("[data-acl="+_item["acl"]+"][data-funcode="+_item["funCode"]+"]").iCheck("check");
						}
					}
				}
			}
		});
	}
	$('.selectpicker').selectpicker('refresh');
}

function queryRrivilege() {
	var ouTypeId = $(".add-model-form select[name='ouTypeId']").val();
	if(ouTypeId) {
		changeFun();
	} else {
		$("#pri tr:not(:first)").remove();
    	$("#groupFun").empty();
	}
}

function queryType(appKey,ouTypeId) {
	ouTypeId = ouTypeId || $(".add-model-form select[name='ouTypeId']").val();
	appKey = appKey || $(".add-model-form select[name='appId'] :selected").attr("appKey");
	wms.asyncPost(pagebase+"/uac/opTypeUnit/tree", {appKey:appKey},{successHandler:function(data, textStatus){
		if(data) {
			var html = "<option value=''>请选择</option>";
			html += spliceOpTypeUnit(data);
			$(".add-model-form select[name='ouTypeId']").html(html);
			$(".add-model-form select[name='ouTypeId']").val(ouTypeId);
			$(".add-model-form select[name='ouTypeId']").multipleSelect('refresh');
		}
		queryRrivilege();
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
		$("#pri tr:not(:first)").remove();
    	$("#groupFun").empty();
    	var id = $(this).closest("tr").find("input[type='checkbox']:first").attr("tid");
		if(id) {
			$(".form-title").html("修改角色");
		} else {
			$(".form-title").html("新增角色");
		}
		wms.asyncPost(pagebase+"/uac/role/get", {id:id},{successHandler:function(data, textStatus){
			if(data){
				form.fill(data);
				$(".add-model-form select[name='appId']").multipleSelect('refresh');
				$(".add-model-form select[name='ouTypeId']").multipleSelect('refresh');
				window.priFunMap = data["rps"];
				queryType();
				$('.add-model-form').modal('show');
			} else {
				wms.frame.notifyError(i18n.t("info"),i18n.t("edit-err"));
			}
		}});
		
	});
	$(".subBtn").on("click",function(){
		var form = $(this).closest("form");
		var ouTypeId = $(".add-model-form select[name='ouTypeId']").val();
		if(ouTypeId == '' || ouTypeId == null) {
			wms.frame.notifyError(i18n.t("提示"),i18n.t("组织类型必填！"));
		
		}
		if(!form.validator().data('bs.validator').validate().isIncomplete()) {
			var data = form.serializeArray();
			wms.asyncPost(pagebase+"/uac/role/add", data,{successHandler:function(data, textStatus){
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
		var lifecycle = $(this).attr("state");
		var id = $(this).closest("tr").find("input[type='checkbox']:first").attr("tid");
		showStateWindow(id, lifecycle);
	});
	
	$(".btn-lifecycle").on("click",function() {
		var data = $(this).closest("form").serializeArray();
		wms.asyncPost(pagebase+"/uac/role/update/lifecycle", data,{successHandler:function(data, textStatus){
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
	$(".btn-query").on("click",function() {
		$("#queryForm").submit();
	});
	
	$(".add-model-form").on("ifUnchecked",".all",function() {
		$(this).closest("table").find("input:checkbox").filter(":not(.all)").iCheck("uncheck");
	});
	$(".add-model-form").on("ifChanged",".all",function() {
		$(this).closest("table").find("input:checkbox").filter(":not(.all)").iCheck("check");
	});
	$(".add-model-form").on("ifChanged",".col-all",function() {
		var $index = $(this).closest("th").index();
		$(this).closest("table").find("tbody tr").each(function(){
			$(this).find("td:eq("+$index+")").find(":checkbox").iCheck("check");
		});
	});
	$(".add-model-form").on("ifUnchecked",".col-all",function() {
		var $index = $(this).closest("th").index();
		$(this).closest("table").find("tbody tr").each(function(){
			$(this).find("td:eq("+$index+")").find(":checkbox").iCheck("uncheck");
		});
	});
	$(".add-model-form").on("ifChanged",".row-all",function() {
		$(this).closest("tr").find("input:checkbox:not(.row-all)").iCheck("check");
	});
	$(".add-model-form").on("ifUnchecked",".row-all",function() {
		$(this).closest("tr").find("input:checkbox:not(.row-all)").iCheck("uncheck");
	});
	//单个按钮 unchecked 事件 
	$(".add-model-form").on("ifUnchecked",".rows-check",function() {
		var flag = false;

		$(this).closest("tr").find("td").each(function(index,element){
			if(index!=1) {				
				var isChecked = $(this).find("input:checkbox:not(.row-all)").is(':checked');
				if(isChecked) {
					flag = true;
				}
			}
		})
		
		if(flag) {
				return;
			}else {
				$(this).closest("tr").find("td").each(function(index,element){
					if(index==1) {
						$(this).find("input:checkbox:not(.row-all)").iCheck("uncheck");
						$(this).find("input:hidden").val(null);
						return;
					}
				})
			}
		
	});
	//checked事件
	$(".add-model-form").on("ifChecked",".rows-check",function() {
		var flag = false;

		$(this).closest("tr").find("td").each(function(index,element){
			
			var flag = $(this).find("input:checkbox:not(.row-all)").is(':checked');
			if(flag) {
				$(this).closest("tr").find("td").each(function(index,element){
					if(index==1) {
						$(this).find("input:checkbox:not(.row-all)").iCheck("check");
						//checkbox disable form表单不能获取值  用隐藏域
						$(this).find("input:hidden").val($(this).find("input:checkbox:not(.row-all)").iCheck("check").val());
						return;
					}
				})
			}
		})
	});
	
});
(function(className){
	setTimeout(function(){
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
	    		}
	    	}
		});
		
		$(".add-model-form select[name='ouTypeId']").multipleSelect({
	    	filter: true
			,selectAll: false
			,single: true
			,isShowButton: false
			,multipleWidth: 55
			,noMatchesFound: "没有找到匹配项"
			,width: "100%"
	    	,onClick: function(view) {
	    		$(".add-model-form select[name='ouTypeId']").val(view.value);
    			changeFun();
	    	}
		});
		
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
