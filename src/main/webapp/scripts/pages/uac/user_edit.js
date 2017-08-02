/**************************************************/
/***************1. 基本信息的设定***********************/
wms.addResource("zh-CN",{
	'commonUserEdit-info':'提示信息',
	'commonUserEdit-loginName-err':'登录名不能为空',
	'commonUserEdit-loginName-regErr':'登录名 由长度为5~50个字符的英文字母，数字或_.-组成，必须以英文字母，数字开头',
	'commonUserEdit-loginNameRepeat-err':'登录名已存在',
	'commonUserEdit-userName-err':'用户姓名不能为空',
	'commonUserEdit-jobNumber-err':'员工工号不能为空',
	'commonUserEdit-jobNumber-regErr':'员工工号  由长度为2~50个字符的英文字母，数字或_.- 组成',
	'commonUserEdit-password-err':'密码不能为空',
	'commonUserEdit-password-regErr':'密码 由长度为8~50个字符组成，不能包含汉字',
	'commonUserEdit-passwordRule-regErr':'密码 不符合密码规则',
	'commonUserEdit-repassword-err':'确认密码不能为空',
	'commonUserEdit-repassword-compareErr':'两次输入密码不一致',
	'commonUserEdit-email-err':'邮箱不能为空',
	'commonUserEdit-email-regErr':'邮箱格式错误',
	'commonUserEdit-phone-regErr':'手机 由长度为5~11个字符的数字组成',
	'commonUserEdit-addApp-suc':'添加用户应用权限信息成功',
	'commonUserEdit-addApp-noCkeck':'请勾选需要的应用权限',
	'commonUserEdit-addApp-fail':'添加用户应用权限信息失败',
	'commonUserEdit-delApp-suc':'删除用户应用权限信息成功',
	'commonUserEdit-delApp-fail':'删除用户应用权限信息失败',
	'commonUserEdit-addUser-suc':'保存用户信息成功，但不包括用户应用权限信息',
	'commonUserEdit-addUser-fail':'保存用户信息失败'
});

/*****************************************************/
/***************2. 页面加载时设定***********************/
/*****************************************************/
$(document).ready(function(){
	
	var loginNameFlag = false;
	var pwdFlag = false;
	
	//添加成功标识
	if("true" == $("#addFlag").val()){
		wms.frame.notifySuccess(i18n.t("commonUserEdit-info"),i18n.t("commonUserEdit-addUser-suc"));
	}
	
	//loginName唯一性校验
	$("#loginName").blur(function(){
		var userId = $("#userId").val();
		var loginName = $(this).val();
		var loginNameTemp = $("#loginNameTemp").val();
		if(loginName.length == 0){
			wms.frame.notifyError(i18n.t("commonUserEdit-info"),i18n.t("commonUserEdit-loginName-err"));
			return ;
		}
		var loginNameReg = /^[A-Za-z0-9][A-Za-z0-9-_\.]{4,49}$/;
		if(!loginNameReg.test(loginName)){
			wms.frame.notifyError(i18n.t("commonUserEdit-info"),i18n.t("commonUserEdit-loginName-regErr"));
			return ;
		}
		
		//如果是修改，并且loginName没有修改过，则不校验
		if(userId.length > 0){
			if(loginName == loginNameTemp){
				loginNameFlag = false;
				return ;
			}
		}
		
		wms.asyncPost(pagebase+'/uac/user/checkLoginName',
				{"loginName": loginName}, 
				{type : "POST",
				successHandler : function(data,textStatus){
					if(data.id && data.id != userId){
						loginNameFlag = true;
						wms.frame.notifyError(i18n.t("commonUserEdit-info"),i18n.t("commonUserEdit-loginNameRepeat-err"));
						return ;
					}else{
						loginNameFlag = false;
					}
				}
			});
		
	});
	
	//密码规则校验
	$("#password").blur(function(){
		var password = $(this).val();
		var loginName = $("#loginName").val();
		
		wms.asyncPost(pagebase+'/uac/user/checkPwdRule',
				{"loginName": loginName,"password":password}, 
				{type : "POST",
				successHandler : function(data,textStatus){
					if(data == "success"){
						pwdFlag = false;
						return ;
					}else{
						wms.frame.notifyError(i18n.t("commonUserEdit-info"),"密码 " + i18n.t(data));
						pwdFlag = true;
					}
				}
			});
		
	});
	
	//新增应用权限保存
	$("#saveCommonUser").click(function(){
		var ids = "";
		var aid = "";
		$("input[name='noAuthAp']:checked").each(function(){
			if($(this).parents("tr").css("display") != "none"){
				ids += $(this).val() + ",";
				aid += $(this).attr("id") + "|";
			}
		});
		if(0 == ids.length){
			wms.frame.notifyError(i18n.t("commonUserEdit-info"),i18n.t("commonUserEdit-addApp-noCkeck"));
			return;
		}
		if(ids.length > 0){
			$(".permission-modal-lg").modal('hide');
			ids = ids.substring(0, ids.length-1);
		}
		var userId = $("#userId").val();
		//ajax 保存
		wms.asyncPost(pagebase+'/uac/user/addAppUser',
				{"appIds": ids,"userId":userId}, 
				{type : "POST",
				successHandler : function(data,textStatus){
					if(data.isSuccess){
						wms.frame.notifySuccess(i18n.t("commonUserEdit-info"),i18n.t("commonUserEdit-addApp-suc"));
					}else{
						wms.frame.notifyError(i18n.t("commonUserEdit-info"),i18n.t("commonUserEdit-addApp-fail"));
						return ;
					}
				}
			});
		
		//权限显示加入选中应用
		var tableLen = $("#appTemp tr").length;
		var aids = aid.split("|");
		for(var i=0; i<aids.length-1; i++){
			
			//将弹出层选中应用隐藏
			$("tr[id='"+aids[i]+"']").hide();
			//将选中的应用勾选去除
			$("td[id='"+aids[i]+"'").attr("checked", false);
			$("input[id='"+aids[i]+"']").parents("div").removeClass("checked");
			//权限设置部分显示勾选权限
			var temp = aids[i].split(",");
			var temp1 = temp[0]+","+temp[3]+","+temp[1]+","+temp[2];
			$("#appTemp").append( "<tr id="+temp1+">"+
            "<td>"+ temp[1] +"</td>"+
            "<td>"+temp[2]+"</td>"+
            "<td>"+temp[3]+"</td>"+
            
            "<td><div class='btn-group btn-group-xs'>" +
            "<a type='button' class='btn btn-default' href='/uac/user/role?id="+userId+"&appKey="+temp[2]+"'>" +
			"	<i class='fa fa-cog'></i> 分配权限" +
			"</a>" +
			"<button type='button' class='btn btn-default dropdown-toggle' data-toggle='dropdown' aria-expanded='false'> <span class='caret'></span> <span class='sr-only'>Toggle Dropdown</span> </button>" +
			"<ul class='dropdown-menu dropdown-menu-minwidth76' role='menu'>" +
				"<li><a href='javascript:void(0)' class='role-trash-o' data-toggle='modal' data-target='#delModal' onclick='delApp(\""+temp1+"\")'><i class='fa fa-trash-o'></i>删除</a></li>" +
			"</ul>" +
			"</div>" +
          
            "</td></tr>");
		}
		
		
		//缓存已勾选后保存的应用id集合
//		$("input[name='ids']").val(ids);
	});
	
	//保存
	$("#saveCommonUserAndApp").click(function(){
//		缓存的应用Id集合
//		var ids = $("input[name='ids']").val();
		
		var id = $("#userId").val();
		//登录名
		var loginName = $("#loginName").val();
		if(loginName.length == 0){
			wms.frame.notifyError(i18n.t("commonUserEdit-info"),i18n.t("commonUserEdit-loginName-err"));
			return ;
		}
		var loginNameReg = /^[A-Za-z0-9][A-Za-z0-9-_\.]{4,49}$/;
		if(!loginNameReg.test(loginName)){
			wms.frame.notifyError(i18n.t("commonUserEdit-info"),i18n.t("commonUserEdit-loginName-regErr"));
			return ;
		}
		if(loginNameFlag){
			wms.frame.notifyError(i18n.t("commonUserEdit-info"),i18n.t("commonUserEdit-loginNameRepeat-err"));
			return ;
		}
		
		//用户名
		var userName = $("#userName").val();
		if(userName.length == 0){
			wms.frame.notifyError(i18n.t("commonUserEdit-info"),i18n.t("commonUserEdit-userName-err"));
			return ;
		}
		
		//工号
		var jobNumber = $("#jobNumber").val();
		if(jobNumber.length == 0){
			wms.frame.notifyError(i18n.t("commonUserEdit-info"),i18n.t("commonUserEdit-jobNumber-err"));
			return ;
		}
		var jobNumberReg = /^[A-Za-z0-9-_\.]{2,50}$/;
		if(!jobNumberReg.test(jobNumber)){
			wms.frame.notifyError(i18n.t("commonUserEdit-info"),i18n.t("commonUserEdit-jobNumber-regErr"));
			return ;
		}
		
		//密码设置方式
		var setPwdType = $("input[name='setPwdType']:checked").val();
		var password = "";
		if(setPwdType == 0){
			//密码
			password = $("#password").val();
			if(password.length == 0){
				wms.frame.notifyError(i18n.t("commonUserEdit-info"),i18n.t("commonUserEdit-password-err"));
				return ;
			}
			var passwordReg = /[\u4E00-\u9FA5]+/;
			if(passwordReg.test(password) || password.length < 8){
				wms.frame.notifyError(i18n.t("commonUserEdit-info"),i18n.t("commonUserEdit-password-regErr"));
				return ;
			}
			
			if(pwdFlag){
				wms.frame.notifyError(i18n.t("commonUserEdit-info"),i18n.t("commonUserEdit-passwordRule-regErr"));
				return ;
			}
			
			//确认密码
			var repassword = $("#repassword").val();
			if(repassword.length == 0){
				wms.frame.notifyError(i18n.t("commonUserEdit-info"),i18n.t("commonUserEdit-repassword-err"));
				return ;
			}
			if(password != repassword){
				wms.frame.notifyError(i18n.t("commonUserEdit-info"),i18n.t("commonUserEdit-repassword-compareErr"));
				return ;
			}
		}
		
		//email
		var email = $("#email").val();
		if(email.length == 0){
			wms.frame.notifyError(i18n.t("commonUserEdit-info"),i18n.t("commonUserEdit-email-err"));
			return ;
		}
		var emailReg = /^[a-zA-Z0-9-_\.]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
		if(!emailReg.test(email)){
			wms.frame.notifyError(i18n.t("commonUserEdit-info"),i18n.t("commonUserEdit-email-regErr"));
			return ;
		}
		
		//phone
		var phone = $("#phone").val();
		var numberReg = /^[0-9]{5,11}$/;
		if(phone.length != 0 && !numberReg.test(phone)){
			wms.frame.notifyError(i18n.t("commonUserEdit-info"),i18n.t("commonUserEdit-phone-regErr"));
			return ;
		}
		
		//有效期
		var expiryDate = $("#expiryDate").val();
		
		var data = {//"ids": ids, 
				"id": id, "loginName": loginName, "userName":userName, "jobNumber":jobNumber, 
				"password":password, "email":email, "phone":phone, "expiryDateString": expiryDate};
		
		var url = staticbase + "/uac/user/edit";
		var jumpUrl = staticbase + "/uac/user";
		if(id.length == 0){
			//添加操作，需将应用id集合传入，并和用户一起保存
			url = staticbase + "/uac/user/add";
			jumpUrl = staticbase + "/uac/user/edit";
			var appTr = $("#appTemp tr");
			var ids = "";
			for(var a=0; a<appTr.length;a++){
				if(a==0){
					ids = appTr.eq(a).attr("id").split(",")[0];
				}else{
					ids += "," + appTr.eq(a).attr("id").split(",")[0];
				}
			}
			data.ids = ids;
		}
		
		wms.frame.blockUIItem("div.content");
		wms.asyncPost(url,data,{
			type: "POST",
			successHandler:function(data, textStatus){
				wms.frame.unblockUIItem("div.content");
				if(data.isSuccess){
					wms.frame.notifySuccess(i18n.t("commonUserEdit-info"),i18n.t("commonUserEdit-addUser-suc"));
					window.location.href = jumpUrl+'?id='+data.description.id+'&addFlag=true';
				}
				//如果有错误信息，则显示错误信息
				else if(data.description.length > 0){
					wms.frame.notifyError(i18n.t("commonUserEdit-info"),data.description);
				}else{
					wms.frame.notifyError(i18n.t("commonUserEdit-info"),i18n.t("commonUserEdit-addUser-fail"));
				}
			}
		});
		wms.frame.unblockUIItem("div.content");
	});
	
	//删除应用
	$("#delAppBtn").click(function(){
		var appId = $("input[name='delAppId']").val();
		var userId = $("#userId").val();
		wms.frame.blockUIItem("div.content");
		wms.asyncPost(staticbase + "/uac/user/delAppUser",
			{"appId":appId.split(",")[0],
			"userId": userId },{
			type: "POST",
			successHandler:function(data, textStatus){
				wms.frame.unblockUIItem("div.content");
				if(data.isSuccess){
					wms.frame.notifySuccess(i18n.t("commonUserEdit-info"),i18n.t("commonUserEdit-delApp-suc"));
					$('.delModal').modal('hide');
				}else{
					wms.frame.notifyError(i18n.t("commonUserEdit-info"),i18n.t("commonUserEdit-delApp-fail"));
					return ;
				}
			}
		});
		//删除
		$("tr[id='"+appId+"']").remove();
		//添加
		var appArray = appId.split(",");
		var appTemp = appArray[0]+","+appArray[2]+","+appArray[3]+","+appArray[1];
		$("tr[id='"+appTemp+"']").show();
	});
	
	//密码设置方式，人工则显示密码框，随机则不显示密码框
	$("input[name='setPwdType']").on("ifChecked", function(){
		var pwdType = $(this).val();
		if(pwdType == 0){
			$("#setPwd").show();
		}
		if(pwdType == 1){
			$("#setPwd").hide();
		}
	});
	
	$('.modal').on('show.bs.modal', centerModal);
	
});

/*****************************************************/
/***************3. FUNCTION 函数和方法*****************/
/**************************************************/
//缓存要解除应用权限的id
function delApp(appId){
	$("input[name='delAppId']").val(appId);
	$('.delModal').modal('show');
}

//modal 居中
function centerModal() {
    $(this).css('display', 'block');
    var $dialog = $(this).find(".modal-dialog");
    var offset = ($(window).height() - $dialog.height()) / 2;
    // Center modal vertically in window
    $dialog.css("margin-top", offset);
}