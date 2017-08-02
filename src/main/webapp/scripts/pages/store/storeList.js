/*****************************************************/
/***************1. 基本信息的设定***********************/
/** ************************************************** */

var tableExtraBatch = [
{
	name : "重推",
	icon : "fa fa-cloud-upload",
	action : function(checked) {
		if (checked) {
			var rids = new Array()
			for (var i = 0; i < checked.length; i++) {
				rids[i] = $(checked[i]).data("itemid");
			}
			
			if (rids.length == 0) {
				wms.frame.notifyInfo(i18n.t("empty_selected"));
   					return;
   				}
   				
   				resetMappings(rids);
   			}
   		}
   	} 
   ];

/** ************************************************** */
/** *************2. 页面加载时设定********************** */
/** ************************************************** */
wms.addReadyFunc(function(){
    setup();   
    console.log("ready function in header");
    
    //ajax异步刷新
//    $("a[name='select']").click(function() {
//        $.ajax({
//            type : "GET",
//            url : "/store/refresh",
//            cache: false, 
////            data : "idDeli=0",
//            dataType: 'Json',
//            async : true,
//            success : function(data) {
//                alert(data);
//                if(data.idDeli==1){
//                	$("td[name='tdDeliSwitch']").html(""+i18n.t("store-on")+"<a type='button' name='select' class='label label-warning'  onclick='test()'>"+i18n.t("store-off")+"</a>");
//                }else{
//                	$("td[name='tdDeliSwitch']").html(""+i18n.t("store-off")+"<a type='button' name='select' class='label label-warning'  onclick='test()'>"+i18n.t("store-on")+"</a>");
//                }
//            },
//            error : function() {
//                alert('请求有误');
//            }
//        });
//    });
    
    $('#orgpicker-modal').on('shown.bs.nifty-modal', function(e){
        $('#org-tree').treeview({data: prepareTreeStyle(orgtree),
            levels:99,
            nodeIcon: 'icon-layers',
            showBorder: false,
            enableLinks:true,
            onNodeSelected: function(event, node){
                $("input[name='orgpicker.org.id']").val(node.id);
                $("input[name='orgpicker.org.name']").val(node.text);
            }});
    });

    $('#orgpicker-modal .btn-primary').click(function(evt){
        evt.preventDefault();
        var id = $("input[name='orgpicker.org.id']").val();
        if(id != ""){

            $("input[name='orgpicker.org.id']").val("");
            $("input[name='orgpicker.org.name']").val("");
            $('#orgpicker-modal button.md-close').trigger("click");
        }else{
            $.notify({
                //title: '必须选择一个和现有组织不同的组织',
                text: i18n.t("frame_mustchooseorg"),
                image: "<i class='fa fa-exclamation'></i>"
            }, {
                style: 'metro',
                className: 'error',
                /*globalPosition:'top center',*/
                hideAnimation: "fadeOut",
                showDuration: 0,
                hideDuration: 1000,
                autoHideDelay: 3000,
                autoHide: true,
                clickToHide: true
            });
        }
    });
});

//Functions
function prepareTreeStyle(tree){
    var result = [];
    if(tree != undefined){

        if(!$.isArray(tree)){
            result.push(_prepareTreeItem(tree));
        }else{
            $.each(tree, function(){
                result.push(_prepareTreeItem(this));
            });
        }
    }
    return result;
}

function _prepareTreeItem(treeItem){
    if(treeItem){
        if(treeItem.selectable != undefined && treeItem.selectable == false)
            $.extend(treeItem, {color: "#ABB7B7"});
        if($.isArray(treeItem.nodes)){
            $.extend(treeItem, {icon: 'fa fa-home'});
            treeItem.nodes = prepareTreeStyle(treeItem.nodes);
        }
    }
    return treeItem;
}

/** ************************************************** */
/** *************2. 页面加载时设定********************** */
/** ************************************************** */
$(function(){	
	 $("#queryFormBtn").on("click", function() {
		 search();
	 });
});


/*****************************************************/
/***************3. FUNCTION 函数和方法*****************/
/*****************************************************/
function search() {
	$(".startPage").val("1");
	$("#queryForm").submit();
}

function findStoreByStoreId(storeId) {
	wms.asyncGet(pagebase + "/store/find/" + storeId, {}, {
		successHandler : function(data, textStatus) {
			$("#brandCodeDetail").val(data.brandCode);
			$("#storeNameDetail").val(data.storeName);
			$("#storeCodeDetail").val(data.storeCode);
			$("#storeUserDetail").val(data.storeCode);
			$("#addressDetail").val(data.address);
			$("#contactPersonDetail").val(data.contactPerson);
			$("#contactPhoneDetail").val(data.contactPhone);
			$("#extCodeDetail").val(data.extCode);
			opt0 = [data.province,data.city,data.district];
			
			if (data.deliSwitch == 1) {
				$("#deliSwitchDetail").html("<select  class='form-control input-sm filter' disabled='disabled'><option value='1'>"+i18n.t("store-on-succeed")+"</option></select>");
			} else {
				$("#deliSwitchDetail").html("<select  class='form-control input-sm filter' disabled='disabled'><option value='0'>"+i18n.t("store-off-succeed")+"</option></select>");
			}
			
			if (data.isSyncFull == 1) {
				$("#isSyncFullDetail").html("<select  class='form-control input-sm filter' disabled='disabled'><option value='1'>"+i18n.t("store-on-succeed")+"</option></select>");
			} else {
				$("#isSyncFullDetail").html("<select  class='form-control input-sm filter' disabled='disabled'><option value='0'>"+i18n.t("store-off-succeed")+"</option></select>");
			}
			//加载地址
			setupDetail();
			
		}
	});
}
function findStoreByStoreIdModify(storeId) {
	wms.asyncGet(pagebase + "/store/find/" + storeId, {}, {
		successHandler : function(data, textStatus) {
			$("#brandCodeModify").val(data.brandCode);
			$("#storeNameModify").val(data.storeName);
			$("#storeCodeModify").val(data.storeCode);
			$("#storeUserModify").val(data.storeCode);
			$("#addressModify").val(data.address);
			$("#contactPersonModify").val(data.contactPerson);
			$("#contactPhoneModify").val(data.contactPhone);
			$("#extCodeModify").val(data.extCode);
			$("#storeIdModify").val(data.storeId);
			opt0 = [data.province,data.city,data.district];
			if (data.isSyncFull == 1) {
				$("#isSyncFullModify").html("<select  class='form-control input-sm filter' name='isSyncFull' ><option value='1'>"+i18n.t("store-on-succeed")+"</option><option value='0'>"+i18n.t("store-off-succeed")+"</option></select>");
			} else {
				$("#isSyncFullModify").html("<select  class='form-control input-sm filter' name='isSyncFull' ><option value='0'>"+i18n.t("store-off-succeed")+"</option><option value='1'>"+i18n.t("store-on-succeed")+"</option></select>");
			}
			if (data.deliSwitch == 1) {
				$("#deliSwitchModify").html("<select  class='form-control input-sm filter' name='deliSwitch' ><option value='1'>"+i18n.t("store-on-succeed")+"</option><option value='0'>"+i18n.t("store-off-succeed")+"</option></select>");
			} else {
				$("#deliSwitchModify").html("<select  class='form-control input-sm filter' name='deliSwitch' ><option value='0'>"+i18n.t("store-off-succeed")+"</option><option value='1'>"+i18n.t("store-on-succeed")+"</option></select>");
			}
			
			//加载地址
			setupModify();
			
		}
	});
}
function findUserByStoreIdModify(storeId){
	wms.asyncGet(pagebase + "/store/find/" + storeId,{},{
		successHandler : function(data,textStatus){
			$("#storeUserLoginNameModify").val(data.storeCode);
			$("#storeIdModifyPassword").val(storeId);
		}
	});
	
}
function addStore() {
	wms.asyncGet(pagebase + "/store/currentou", {}, {
		successHandler : function(data, textStatus) {
			$("select[name=isSyncFull]").find("option[value='1']").prop("selected",true);
			$("select[name=deliSwitch]").find("option[value='1']").prop("selected",true);
			$("input[name='brandCode']").val(data.code);
			opt0 = ["-","-","-"];
			//加载地址
			setupAdd();
		}
	});
	
}
//刷新开关 type=1 门店配货 type=2 库存同步
function isStatusChange(storeId,status,type) {
	wms.asyncGet(pagebase + "/store/refresh/" + storeId+"/"+status+"/"+type, {}, {
		successHandler : function(data, textStatus) {
			var typeName="";
			var newStatus=0;
			if(type==1){
				typeName="deli";
				newStatus=data.deliSwitch;
			}else{
				typeName="sync";
				newStatus=data.isSyncFull;
			}
	        if(newStatus==1){
	        	$("td[name='"+typeName+storeId+"']").html(""+i18n.t("store-on")+"&nbsp;<a type='button' name='select' class='label label-success'  onclick='isStatusChange(\""+storeId+"\",\""+newStatus+"\",\""+type+"\")'>"+i18n.t("store-off-succeed")+"</a>");
	        }else{
	        	$("td[name='"+typeName+storeId+"']").html(""+i18n.t("store-off")+"&nbsp;<a type='button' name='select' class='label label-warning'  onclick='isStatusChange(\""+storeId+"\",\""+newStatus+"\",\""+type+"\")'>"+i18n.t("store-on-succeed")+"</a>");
	        }
		}
	});
}

/**校验门店编码唯一性*/
function checkUniqueStoreCode(e, nv) {
	var data = wms.syncPost(pagebase + "/store/checkStoreCodeUnique", { "storeCode":$("input[name='storeCode']").val(),"brandCode":$("input[name='brandCode']").val()  });
	if (data) {
		return wms.validator.SUCCESS;
	}	
	return i18n.t("store-checkuniquestorecode");
}
/**校验新密码必须含有至少一个字母*/
function checkIncludingLetter(e,nv){
	var newPassword = e.val();
	if(/[a-z]/i.test(newPassword)){
		return wms.validator.SUCCESS;
	}else{
		return i18n.t("store-checkincludingletterstring");
	}
}

//affirm delete
function affirmDelete(storeId) {
	wms.asyncGet(pagebase + "/store/find/" + storeId, {}, {
		successHandler : function(data, textStatus) {
			$("#brandCodeDelete").val(data.brandCode);
			$("#lbsPoiIdDelete").val(data.lbsPoiId);
			$("#storeIdDelete").val(storeId);
		}
	});

	
}

//form submit
function modifyFormSubmit() {
	if($("#cityModify").val()=="-"||$("#provinceModify").val()=="-"||$("#districtModify").val()=="-"){
		wms.frame.notifyError(i18n.t("store-modifyformcpd"));
	}else{
		$('#modifyForm').submit();
	}
}
function modifyUserFormSubmit(){
	$("#userModifyForm").submit();
}
function deleteFormSubmit() {
	$('#deleteForm').submit();
}
function addFormSubmit() {
	if($("#cityAdd").val()=="-"||$("#provinceAdd").val()=="-"||$("#districtAdd").val()=="-"){
		wms.frame.notifyError(i18n.t("store-modifyformcpd"));
	}else{
		$('#addForm').submit();
	}
	
}

function addFormClear() {
	$(':input','#addForm')    
	 .not(':button, :submit, :reset, :hidden')    
	 .val('')    
	 .removeAttr('checked')    
	 .removeAttr('selected');
}

function uploadFormSubmit() {
	var form = $(this).closest("form");
	$('#uploadForm').ajaxForm({
		success: function (data) {
			$(".blockUI").remove();
			if(data=="success") {		
				$("#errorsBatchUp").html("<label ><font color='green'>"+i18n.t("store-uploadsucess")+"</font></label>");
			}else{
				$("#errorsBatchUp").html("<label ><font color='red'>"+data+"</font></label>");
			}
        }
	});
	$('#uploadForm').submit();
}
function uploadFormClose() {
	location.href ="/store/list";
}

function useStoreCodeAsUserName(){
	var storeCode = $("#storeCodeAdd").val();
	$("#storeUserAdd").val(storeCode);
}

function exportStore(){
	var storeNum = $("table tbody tr").length;
	if(storeNum == 0){
		wms.frame.notifyError(i18n.t("store-exportstorestring"));
	}else{
		$("#exportStoreForm").submit();
	}
}

function cleanUserModifyForm(){
	$("#storeUserPassword").val("");
}



