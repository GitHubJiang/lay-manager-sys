/*****************************************************/
/***************1. 基本信息的设定***********************/
/** ************************************************** */
wms.addResource("zh-CN",{
	'info':'提示信息',
	'loginName-err':'登录名不能为空',
	'loginName-regErr':'登录名 由长度为5~50个字符的英文字母，数字或_.-组成，必须以英文字母，数字开头',
	'loginNameRepeat-err':'登录名已存在',
	'userName-err':'用户姓名不能为空',
	'ouId-err':'所属组织不能为空',
	'jobNumber-err':'员工工号不能为空',
	'jobNumber-regErr':'员工工号  由长度为2~50个字符的英文字母，数字或_.- 组成',
	'password-err':'密码不能为空',
	'password-regErr':'密码 由长度为8~50个字符组成，不能包含汉字',
	'passwordRule-regErr':'密码 不符合密码规则',
	'repassword-err':'确认密码不能为空',
	'repassword-compareErr':'两次输入密码不一致',
	'email-err':'邮箱不能为空',
	'email-regErr':'邮箱格式错误',
	'phone-regErr':'手机 由长度为5~11个字符的数字组成',
	'addApp-suc':'添加用户应用权限信息成功',
	'addApp-noCkeck':'请勾选需要的应用权限',
	'addApp-fail':'添加用户应用权限信息失败',
	'delApp-suc':'删除用户应用权限信息成功',
	'delApp-fail':'删除用户应用权限信息失败',
	'addUser-suc':'保存用户信息成功，但不包括用户应用权限信息',
	'addUser-fail':'保存用户信息失败'
});
/** ************************************************** */
/** *************2. 页面加载时设定********************** */
/** ************************************************** */
wms.addReadyFunc(function(){
	$(".modal-select").selectpicker({
		title : '',
		size : 5,//多少行会出现滑动栏
		dropupAuto : false,//下拉框显示在下方展开
		style : null,
		liveSearch : false
	});
	//添加成功标识
	var id =  $("#user_id").val()||"";
	if("" != id ) {
		$("#roleManager").removeClass("disabled");
		$("#loginName").attr("readonly","readonly");
		getUserInfo(id);
	}
	
	$("#userInfoSubmit").on("click",function() {
		var check = $("#userInfoForm").data("bs.validator").checkValid();
	    if(!check){
	    	return "";
	    }	    
	 	var id = $("#user_id").val();	 	
	 	var data = $("#userInfoForm").serializeArray();
	 	var url = pagebase+"/auth/user/add";
	 	var jumpUrl =  pagebase+"/auth/user/toAdd";
	 	wms.asyncPost(url,data,{
			type: "POST",
			successHandler:function(data, textStatus){				
				if(data.code=='1'){
					wms.frame.notifySuccess("提示信息","保存用户信息成功，但不包括用户角色信息");
					window.location.href = jumpUrl+'?id='+data.data+'&addFlag=true';
				}
				//如果有错误信息，则显示错误信息
				else{
					wms.frame.notifyError("提示信息","保存用户信息失败");
				}
			}
		});
	 });
	
	$("#submitBtn").on('click',function(){
		saveUserRole();
	});
	/**角色弹窗*/
	$("#roleManager").on('click',function(){		
		$("#label-ouType").removeAttr("disabled");
		$("#label-ouId").removeAttr("disabled");
		$("#label-role").removeAttr("disabled");
		$('#submitBtn').show();
		buildModalData();
	});
	
	$("#label-ouType").on('change',function(){
		var ouType = $("#label-ouType").val();
		buildOpUnitSelect(ouType);
		buildRoleSelect(ouType);
		buildRoleAcl(ouType);
	});
	
});

/*****************************************************/
/***************3. FUNCTION 函数和方法*****************/
/*****************************************************/

function getUserInfo(id){
	$("#password").removeAttr("required");
	$("#repassword").removeAttr("required");
	$("#password").attr("disabled","disabled");
	$("#repassword").attr("disabled","disabled");
	$("#password-hid").hide();	
	$("#repassword-hid").hide();
	wms.asyncGet(pagebase + "/auth/user/getUserInfo", {"id":id}, {
		successHandler : function(data, textStatus) {
			if(data.code=='1'){
				$("#userInfoForm").fill(data.data)
				var htmlTd='';
				$.each(data.data.list, function(index, item){
	                htmlTd+='<tr><td style="width:25%">'+item.ouTypeName+'</td>'+
	                '<td style="width:15%">'+item.ouName+'</td>'+
	                '<td style="width:15%">'+item.roleName+'</td>'+
	                '<td style="width:15%"><div class="btn-group btn-group-xs"><a type="button" data-ouTypeId="'+item.ouTypeId+'" data-roleId='+item.roleId+' data-ouTypeName="'+item.ouTypeName+'" data-roleName="'+item.roleName+'" data-ouName="'+item.ouName+'" data-id="'+item.id+
	                '" class="btn btn-default viewRoleUser" data-toggle="modal" data-target="#roleModal" href="javaScript:void(0);">'+
	                '<i class="fa fa-search"></i>查看</a><button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">'+
	                '<span class="caret"></span><span class="sr-only">Toggle Dropdown</span></button>'+
	                '<ul class="dropdown-menu" role="menu"><li><a href="javaScript:void(0);"  data-id="'+item.id+
	                '"  class="md-trigger deleteRoleUser"> <i class="fa fa-trash-o"></i>删除</a></li></ul></div></td></tr>';                      
				})
				$('#tbody').html(htmlTd);
				$(".viewRoleUser").bind('click',function(){
					
					var ouTypehtml = '<option value="'+$(this).attr("data-ouTypeId")+'">'+$(this).attr("data-ouTypeName")+'</option>';				
					$("#label-ouType").append(ouTypehtml);
					$("#label-ouType").attr("disabled","disabled");
					$('#label-ouType').selectpicker('render');
		            $('#label-ouType').selectpicker('refresh');
					var ouNamehtml = '<option>'+$(this).attr("data-ouName")+'</option>';				
					$("#label-ouId").append(ouNamehtml);
					$("#label-ouId").attr("disabled","disabled");
					$('#label-ouId').selectpicker('render');
		            $('#label-ouId').selectpicker('refresh');
					var roleHtml = '<option value="'+$(this).attr("data-roleId")+'">'+$(this).attr("data-roleName")+'</option>';				
					$("#label-role").append(roleHtml);	
					$("#label-role").attr("disabled","disabled");
					$('#label-role').selectpicker('render');
		            $('#label-role').selectpicker('refresh');
					buildRoleAcl($(this).attr("data-ouTypeId"));
					
					$('#submitBtn').hide();
				});
				$(".deleteRoleUser").on('click',function(){
					wms.asyncGet(pagebase + "/auth/user/removeUserRole", {"id":$(this).attr("data-id")}, {
						successHandler : function(data, textStatus) {
							if(data.code=='1'){
								window.location.href = pagebase+'/auth/user/toAdd?id='+$("#user_id").val()+'&addFlag=true';
							}else{
								wms.frame.notifyError("提示信息","保存用户信息失败");
							}
						}
					});
				});
			}else{
				wms.frame.notifyError("提示信息","获取用户信息失败");
			}
		}
	});
}

/**校验用户名的唯一性*/
function checkUniqueLoginName(e, nv) {
	var data = wms.syncPost(pagebase + "/check/checkUniqueCode", { "table":"au_user","fieldValue":$("#loginName").val(),"id":$("#user_id").val(),"fieldName":"login_name"  });
	if (data==true) {
		return wms.validator.SUCCESS;
	}
	return "登录名不能重复";
}

function checkLoginName(){
	var loginName=$("#loginName").val();
	var reg = /^[A-Za-z0-9][A-Za-z0-9-_\.]{4,29}$/;
	if(!reg.test(loginName)){		
		return '由长度为5~30个字符的英文字母，数字或_.-组成，必须以英文字母，数字开头';
	}
	return wms.validator.SUCCESS;
}

//校验密码是否重复
function checkRePassword(e){
	var password=$("#password").val();
 	var repassword=$("#repassword").val();
 	if(password != repassword){
		return "两次密码不一致";
	}
    return wms.validator.SUCCESS;
}

function checkPassword(){
	var loginName=$("#loginName").val();
	var password=$("#password").val();
	var reg = /^[A-Za-z0-9][A-Za-z0-9-_\.]{4,19}$/;
	if(!reg.test(password)){
		return '由长度为5~20个字符的英文字母，数字或_.-组成，必须以英文字母，数字开头';
	}
	if(password == loginName){
		return "密码不能与用户名相同";
	}
	return wms.validator.SUCCESS;
}

function checkEmail(){
	var email = $("#email").val();
	var pPattern = /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/;
	if(!pPattern.test(email)){
		return '邮箱格式不正确'
	}
	return wms.validator.SUCCESS;
}

/**选中option*/
function option(ouId){
	var select = $("#ou_id option");
    for (var i = 0; i < select.length; i++){  
        if (select[i].value == ouId){  
            select[i].selected = true;  
            break;  
        }
    } 
}

/**新增角色，初始化弹窗数据*/
function buildModalData(){
	var ouType = $("#ouType_id").val()||"";
	buildOuTypeSelect(ouType);
}
/**构造所属组织类型*/
function buildOuTypeSelect(ouType){	
	var html = "";
	wms.asyncGet(pagebase + "/auth/opt/allopt", {}, {
		successHandler : function(data, textStatus) {
			if(data){
				var html = '';
	            $.each(data, function(index, item){
	              html += '<option value='+item.id+'>'+item.name+'</option>';                                        
	            });	            
	            $('#label-ouType').empty();
	            $('#label-ouType').append(html);
	            var ouTypeId = $("#label-ouType").val();
	        	buildOpUnitSelect(ouTypeId);
	        	buildRoleSelect(ouTypeId);
			}else{
				wms.frame.notifyError("提示信息","查询组织类型列表失败");
			}
		}
	});
	
}
/**构造所属组织*/
function buildOpUnitSelect(ouTypeId){	
	var html = "";
	wms.asyncGet(pagebase + "/auth/org/operationUnitList", {"ouTypeId":ouTypeId}, {
		successHandler : function(data, textStatus) {
			if(data.code=='1'){
				var html = '';
	            $.each(data.data, function(index, item){
	              html += '<option value='+item.id+'>'+item.name+'</option>';                                        
	            });	            
	            $('#label-ouId').empty();
	            $('#label-ouId').append(html);
	            $('#label-ouId').selectpicker('render');
	            $('#label-ouId').selectpicker('refresh');
			}else{
				wms.frame.notifyError("提示信息","查询组织信息失败");
			}
		}
	});
}
/**构造角色列表*/
function buildRoleSelect(ouTypeId){	
	var html = "";
	wms.asyncGet(pagebase + "/auth/role/rolelist", {"ouTypeId":ouTypeId}, {
		successHandler : function(data, textStatus) {
			if(data.code=='1'){
				var html = '';
	            $.each(data.data, function(index, item){
	              html += '<option value='+item.id+'>'+item.name+'</option>';                                        
	            });	            
	            $('#label-role').empty();
	            $('#label-role').append(html);
	            $('#label-role').selectpicker('render');
	            $('#label-role').selectpicker('refresh');
			}else{
				wms.frame.notifyError("提示信息","查询角色信息失败");
			}
			buildRoleAcl(ouTypeId);			
		}
	});
}

/**构造角色对应的权限表格*/
function buildRoleAcl(ouTypeId){	
	wms.asyncPost(pagebase+"/auth/pri/allAcl",{"ouTypeId":ouTypeId},{successHandler:function(data, textStatus){
		if(data.code=='1'){	
			var htmlTd='';
            $.each(data.data, function(index, item){
                  htmlTd+='<tr><td style="width:25%">'+item.name+'</td>'+
                  '<td style="width:15%"><input class="rows-check" disabled="disabled" type="checkbox" data-role="view" data-id=\''+item.id+'\'name="rolePriList" value="{fun:\'view\',acl:\''+item.acl+'\'}"></td>'+
                  '<td style="width:15%"><input class="rows-check" disabled="disabled" type="checkbox" data-role="add"  data-id=\''+item.id+'\'name="rolePriList" value="{fun:\'add\',acl:\''+item.acl+'\'}"></td>'+
                  '<td style="width:15%"><input class="rows-check" disabled="disabled" type="checkbox" data-role="update" data-id=\''+item.id+'\'name="rolePriList" value="{fun:\'update\',acl:\''+item.acl+'\'}"></td>'+
                  '<td style="width:15%"><input class="rows-check" disabled="disabled" type="checkbox" data-role="remove" data-id=\''+item.id+'\'name="rolePriList" value="{fun:\'remove\',acl:\''+item.acl+'\'}"></td>'+
                  '</tr>';                      
            });

            $('#aclTBody').html(htmlTd);
            $("#aclTBody input:checkbox").iCheck({
    	        checkboxClass: 'icheckbox_square-aero',
    	        radioClass: 'iradio_minimal',
    	        increaseArea: '20%'
    	    });
		} else {
			wms.frame.notifyError("提示信息","查询权限信息失败");
		}
		/*checkRoleAcl();*/
	}});	
	
}
/**构造角色对应的权限表格*/
function checkRoleAcl(){	
	var id = $("#label-role").val();
	if(id){
		wms.asyncPost(pagebase+"/auth/role/get", {id:id},{successHandler:function(data, textStatus){
			var checkboxs = $("#aclTBody input:checkbox");
			if(data){
				var rolePriMap = data["rolePriMap"];
				if(rolePriMap) {
					for(var key in rolePriMap) {
						var _item = rolePriMap[key];						
						for(var i=0;i<_item.length;i++) {							
							checkboxs.filter("[data-id="+key+"][data-role="+_item[i]+"]").iCheck("check");
						}
					}
				}
			} else {
				wms.frame.notifyError("提示信息",i18n.t("edit-f"));
			}
		}});
	}	
}
function saveUserRole(){	
	var check = $("#roleForm").data("bs.validator").checkValid();
    if(!check){
    	return "";
    }
    var data = $("#roleForm").serializeArray();
    wms.asyncPost(pagebase+"/auth/user/saveUserRole", data,{successHandler:function(data, textStatus){
    	if(data.code=="0"){
    		window.location.href = pagebase+'/auth/user/toAdd?id='+$("#user_id").val()+'&addFlag=true';
		} else {
			wms.frame.notifyError("提示信息",i18n.t("edit-f"));
		}
	}});
}