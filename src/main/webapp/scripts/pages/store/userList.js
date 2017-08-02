/** ************************************************** */
/** *************2. 页面加载时设定********************** */
/** ************************************************** */
wms.addReadyFunc(function() {
	
	$("#queryFormBtn").on("click", function() {
		search();
	});
	$("#storeSelect").selectpicker({
		title : '-',
		size : 5,//多少行会出现滑动栏
		dropupAuto : false,//下拉框显示在下方展开
		style : null,
		liveSearch : true
	});
	
});


/*****************************************************/
/***************3. FUNCTION 函数和方法*****************/
/*****************************************************/
function search() {
	$(".startPage").val("1");
	$("#queryForm").submit();
}

function findUserById(userId){
	wms.asyncGet(pagebase + "/user/find/" + userId, {}, {
		successHandler : function(data, textStatus) {
			$("#userIdModify").val(data.userId);
			$("#loginNameModify").val(data.loginName);
			$("#userNameModify").val(data.userName);
			$("#storeNameModify").val(data.storeName);
		}
	});
}

function modifyFormSubmit(){
	$("#modifyForm").submit();
}

function addFormSubmit(){
	if($("#storeSelect").val()=="-"){
		wms.frame.notifyError(i18n.t("user-choose-store"));
	}else{
		$('#addForm').submit();
	}
}

function checkOnlyNumberOrLetter(e,nv){
	var password = e.val();
	if(/.*[\u4e00-\u9fa5]+.*$/.test(password)){
		return i18n.t("user-only-number");
	}else{
		return wms.validator.SUCCESS;
	}
}

function checkConfirmPassword(e,nv){
	var password = $("#passwordModify").val();
	var confirmPassword = e.val(); 
	if(confirmPassword == password){
		return wms.validator.SUCCESS;
	}else{
		return i18n.t("user-confirm-password");
	}
}

function changeUserStatus(userId,userStatus){
	var userOnEd = i18n.t("common-on");
	var userOffEd = i18n.t("common-off");
	var userOn = i18n.t("store-on-succeed");
	var userOff = i18n.t("store-off-succeed");
	wms.asyncGet(pagebase + "/user/changeStatus/" + userId + "/"+ userStatus, {}, {
		successHandler : function(data, textStatus) {
			var userTd = "#user" + userId;
			if(userStatus == 1){
				$(userTd).html(userOnEd + "&nbsp;<a type='button' name='select' class='label label-success'  onclick='changeUserStatus(" + userId + ", 2)'" + ">" + userOff + "</a>");
			}else{
				$(userTd).html(userOffEd + "&nbsp;<a type='button' name='select' class='label label-warning'  onclick='changeUserStatus(" + userId + ", 1)'" + ">" + userOn + "</a>");
			}
		}
	});
}

function addFormClear() {
	$(':input','#addForm')    
	 .not(':button, :submit, :reset, :hidden')    
	 .val('')    
	 .removeAttr('checked')    
	 .removeAttr('selected');
}

function addUser(){
	wms.asyncGet(pagebase + "/user/store", {}, {
		successHandler : function(data, textStatus) {
			var storeOptions = "<option>-</option>";
			for(var i = 0;i < data.length;i++){
				storeOptions += "<option value='" + data[i].storeCode+ ","+ data[i].storeId + "'>" + data[i].storeName + "</option>";
			}
			$("#storeSelect").empty();
			$("#storeSelect").append(storeOptions);
			$('#storeSelect').selectpicker('render');
            $('#storeSelect').selectpicker('refresh');
		}
	});
}

function checkUniqueUser(e,nv){
	var loginName = e.val();
	var data = wms.syncPost(pagebase + "/user/unique",{'loginName':loginName},{});
	if(data){
		return wms.validator.SUCCESS;
	}else{
		return i18n.t("user-exist");
	}
}
