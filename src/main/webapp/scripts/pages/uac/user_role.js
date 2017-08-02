/*****************************************************/
/***************1. 基本信息的设定***********************/
/*****************************************************/
  
wms.addResource("zh-CN",{
    'user-info': '提示信息',
    'user-edit-error': '编辑失败',
    'query-power':'查看权限',
    'add-role-error':'增加权限失败',
    'remove-role-error':'增加权限失败',
    'add-power':'新增权限',
    'user-select':'请选择',
    'error-add-user':'请先添加用户',
    'select-orgId':'请选择组织类型',
    'select-ouId':'请选择组织',
    'select-role':'请选择对应的角色'
    	
});

/*****************************************************/
/***************2. 页面加载时设定***********************/
/*****************************************************/
function spliceOpTypeUnit(array,html,index) {
	index = index || 0;
	html = html || "";
	for(var i = 0;i< array.length;i++) {
		var _item = array[i];
		html += "<option value='"+_item["id"]+"'>";
		for(var _i=0;_i<index;_i++) {
			html +="&nbsp;&nbsp;";
		}
		html += _item["text"]+"</option>";
		if(_item["nodes"]) {
			html += spliceOpTypeUnit(_item["nodes"], "", index + 1);
		}
	}
	return html;
}

function addUnit(ouTypeId) {
	var select = $(".permission-modal-lg select[name='unit']");
	select.html("<option value=''>请选择</option>");
	if(ouTypeId && ouTypeId!="") {
		/*wms.asyncPost(pagebase+"/uac/opUnit/tree", {ouTypeId:ouTypeId,appKey:appKey},{successHandler:function(data, textStatus){
			var html = spliceOpTypeUnit(data);
			select.append(html);
			select.val(ouTypeId);
			randForm();
		}});*/
		wms.asyncPost(pagebase+"/uac/opUnit/select", {ouTypeId:ouTypeId},{successHandler:function(data, textStatus){
			for(var i=0;i<data.length;i++) {
				select.append("<option value='"+data[i]["id"]+"'>"+data[i]["name"]+"</option>");
			}
			select.val("");
			randForm();
		}});
	}
}
function addRole(ouTypeId) {
	
	var select = $(".permission-modal-lg select[name='role']");
	select.html("<option value=''>请选择</option>");
	if(ouTypeId && ouTypeId!="") {
		wms.asyncPost(pagebase+"/uac/role/select", {ouTypeId:ouTypeId},{successHandler:function(data, textStatus){
			for(var i=0;i<data.length;i++) {
				select.append("<option value='"+data[i]["id"]+"'>"+data[i]["name"]+"</option>");
			}
			randForm();
			changeFun();
		}});
	}
}
function changeFun(){
	var roleId = $(".permission-modal-lg select[name='role']").val();
	if(roleId && roleId) {
		asyncXhr(staticbase+'/uac/user/ouType/role',{'roleId':roleId,'appKey':appKey}, {
			type : "POST",
			success : function(data,textStatus){
				if(data){
					$("#pri tr:not(:first)").remove();
					var str = "<tbody>";
					$.each(data.dataPris,function(n,value) {
						str +="<tr>";
						str +="<td>"+value.name+"</td>";
						$.each(data.pdts,function(n1,pdt) {
							str +="<td><input type='checkbox' data-acl='"+value.acl+"'  data-funcode='"+pdt.dicValue+"'  class='rows-check' data-role='"+pdt.dicValue+"'></td>";
						});
						str += "</tr>";
					});
					str += "</tbody>";
					$("#pri").append(str);
					
					$("#groupFun").empty();
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
								groupStr +="<td class='col-xs-1 col-md-1'><input type='checkbox' data-acl='"+command.acl+"'   data-funcode='"+pdt.dicValue+"' class='rows-check' data-role='"+pdt.dicValue+"'></td>";
							});
							groupStr +="</tr>";
						});
	                      groupStr +="</tbody>";
	                      groupStr +="</table>";
					});
					$("#groupFun").append(groupStr);
					
					$.each(data.roleCommand.rps,function(n,value) {
						$(".form-group-Privilege").find("input[data-acl='"+value.acl+"'][data-funcode='"+value.funCode+"']").attr("checked","checked");
					});
					$(".form-group-Privilege").find("input[type='checkbox']").attr("disabled","disabled");
					
					//ICHECK
				    $('input.rows-check').iCheck({
				        checkboxClass: 'icheckbox_square-aero',
				        radioClass: 'iradio_minimal',
				        increaseArea: '20%'
				    });
					
				}
			}
		});
	}
	$('.selectpicker').selectpicker('refresh');
}
function initOpType() {
	if(appKey && appKey!="") {
		wms.asyncPost(pagebase+"/uac/opTypeUnit/tree", {appKey:appKey},{successHandler:function(data, textStatus){
			if(data) {
				var html = spliceOpTypeUnit(data);
				$(".organization-type").html(html);
			} else {
				$(".organization-type").html('<option value="-1">请选择</option>');
			}
			randForm();
			addUnit($(".organization-type").val());
			addRole($(".organization-type").val());
		}});
	}
}

function randForm() {
	if(window.display && window.display.data) {
		$("#addRoleForm").fill(window.display.data);
	}
}
wms.addReadyFunc(function(){
	
	$(".organization-type").on("change",function(){
		addUnit($(this).val());
		addRole($(this).val());
	});
	$(".permission-modal-lg select[name='role']").on("change",function(){
		changeFun();
	});
	
	/**
     * 添加用户角色信息
     */
    $("#saveRoleUser").on("click",function(){
    	var form = $("#addRoleForm");
		if(!form.validator().data('bs.validator').validate().isIncomplete()) {
			var data = form.serializeArray();
			wms.asyncPost(pagebase+"/uac/user/role/add", data,{successHandler:function(data, textStatus){
				if(data){
					if(data.isSuccess) {
						window.location.href = pagebase +"/uac/user/role?id="+id+"&appKey="+appKey;
					} else {
						wms.frame.notifyError(i18n.t("add-role-error"),data["returnUrl"]);
					}
				} else {
					wms.frame.notifyError(i18n.t("add-role-error"));
				} 
			}});
		}
    	
    });
    
    $(".del-btn").on("click",function(){
		var urId = $(this).closest("tr").attr("urId");
		wms.asyncPost(pagebase+"/uac/user/role/remove", {ids:urId},{successHandler:function(data, textStatus){
			if(data){
				if(data.isSuccess) {
					window.location.href = pagebase +"/uac/user/role?id="+id+"&appKey="+appKey;
				} else {
					wms.frame.notifyError(i18n.t("remove-role-error"),data["description"]);
				}
			} else {
				wms.frame.notifyError(i18n.t("remove-role-error"));
			}
		}});
	});
    
    $(".dispaly-btn").on("click",function(){
    	$('.permission-modal-lg').modal('show');
    	$("#myLargeModalLabel").html(i18n.t("query-power"));
    	$("#saveRoleUser").hide();
    	$(".permission-modal-lg select[name='role']").html("<option value=''>请选择</option>");
    	$(".permission-modal-lg select[name='unit']").html("<option value=''>请选择</option>");
    	$("#pri tr:not(:first)").remove();
    	$("#groupFun").empty();
    	initOpType();
    	var tr = $(this).closest("tr")
    	,ouTypeId = tr.attr("ouTypeId")
    	,roleId = tr.attr("roleId")
    	,ouId = tr.attr("ouId")
    	,data={
    		role:roleId
    		,unit:ouId
    		,ouTypeId:ouTypeId
    	};
		$("#addRoleForm")[0].reset();
		window.display = {
			data : data
		};
		
		$("#addRoleForm select").attr("disabled","disabled");
    });
    
    $(".btn-sm").on("click",function(){
    	window.display = null;
    	$("#myLargeModalLabel").html(i18n.t("add-power"));
    	$("#saveRoleUser").show();
    	$("#addRoleForm select").removeAttr("disabled");
    	$(".permission-modal-lg select[name='role']").html("<option value=''>请选择</option>");
    	$(".permission-modal-lg select[name='unit']").html("<option value=''>请选择</option>");
    	initOpType();
    	$("#pri tr:not(:first)").remove();
    	$("#groupFun").empty();
    	$('.permission-modal-lg').modal('show');
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
