/*****************************************************/
/***************1. 基本信息的设定***********************/
/** ************************************************** */


/** ************************************************** */
/** *************2. 页面加载时设定********************** */
/** ************************************************** */
wms.addReadyFunc(function(){

	getAllopt();	
	
	//动态绑定
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
    
    $(".btn-add").on("click",function(){
		var form = $("#addForm");		
		wms.asyncPost(pagebase+"/auth/role/allPri",{},{successHandler:function(data, textStatus){
			if(data){			
		
				var htmlTd='';
                $.each(data, function(index, item){
                    
                      htmlTd+='<tr><td style="width:25%">'+item.name+'</td>'+
                      '<td style="width:15%"><input class="rows-check" type="checkbox" data-role="view" data-id=\''+item.acl+'\'name="rolePriList" value="{funCode:\'view\',acl:\''+item.acl+'\'}"></td>'+
                      '<td style="width:15%"><input class="rows-check" type="checkbox" data-role="add"  data-id=\''+item.acl+'\'name="rolePriList" value="{funCode:\'add\',acl:\''+item.acl+'\'}"></td>'+
                      '<td style="width:15%"><input class="rows-check" type="checkbox" data-role="update" data-id=\''+item.acl+'\'name="rolePriList" value="{funCode:\'update\',acl:\''+item.acl+'\'}"></td>'+
                      '<td style="width:15%"><input class="rows-check" type="checkbox" data-role="remove" data-id=\''+item.acl+'\'name="rolePriList" value="{funCode:\'remove\',acl:\''+item.acl+'\'}"></td>'+
                      '<td style="width:15%"><input class="row-all" type="checkbox"  data-id=\''+item.id+'\'name="rolePriList" value=""></td>'+
                      '</tr>';                      
                });

                $('#tbody').html(htmlTd);
                $("#tbody input:checkbox").iCheck({
        	        checkboxClass: 'icheckbox_square-aero',
        	        radioClass: 'iradio_minimal',
        	        increaseArea: '20%'
        	    });
			} else {
				wms.frame.notifyError("提示信息","调用接口失败");
			}
		}});
		
		var id = $(this).attr("data-id");	
		if(id){
			wms.asyncPost(pagebase+"/auth/role/get", {id:id},{successHandler:function(data, textStatus){
				var checkboxs = $("#tbody input:checkbox");
				if(data){
					form.fill(data);
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
					wms.frame.notifyError(i18n.t("info"),i18n.t("edit-f"));
				}
			}});
		}
	});	
	/**提交表单数据*/
	var flag = false;
	$("#subBtn").on("click",function(){
		
		var check = $("#addForm").data("bs.validator").checkValid();
	    if(!check){
	    	return "";
	    }
	  //防止表单重复提交
		if(flag){
			wms.frame.notifyError("不能重复提交");
			return ;
		}
		flag = true;
		var form = $("#addForm");
		var data = form.serializeArray();
		wms.asyncPost(pagebase+"/auth/role/add", data,{successHandler:function(data, textStatus){
			if(data){				
				if(data.code==1) {
					wms.frame.notifySuccess("提示信息","成功");
					setTimeout('eval($("#queryForm").submit())',1000);
				} else {
					wms.frame.notifyError('提示信息',data["msg"]);
					setTimeout('eval($("#queryForm").submit())',1000);
				}
			} else {
				wms.frame.notifyError(i18n.t("info"),i18n.t("edit-f"));
			}
		}});		
	});
	
	/**关闭modal时清除数据*/
	$("#closeBtn").on("click",function(){
		$(':input','#addForm')    
		 .not(':button, :submit, :reset, :hidden')    
		 .val('')    
		 .removeAttr('checked')    
		 .removeAttr('selected');
	});
    
});


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

function isStatusChange(id,status) {
	wms.asyncGet(pagebase + "/auth/role/refresh/" +id+"/"+status, {}, {
		successHandler : function(data, textStatus) {
			var typeName="lifecycle";
			var newStatus=data.lifecycle;
	        if(newStatus==2){
	        	$("td[name='"+typeName+id+"']").html(""+i18n.t("store-on")+"&nbsp;<a type='button' name='select' class='label label-success'  onclick='isStatusChange(\""+id+"\",\""+newStatus+"\")'>"+i18n.t("store-off-succeed")+"</a>");
	        }else{
	        	$("td[name='"+typeName+id+"']").html(""+i18n.t("store-off")+"&nbsp;<a type='button' name='select' class='label label-warning'  onclick='isStatusChange(\""+id+"\",\""+newStatus+"\")'>"+i18n.t("store-on-succeed")+"</a>");
	        }
		}
	});
}

//affirm delete
function affirmDelete(id) {
	$("#idDelete").val(id);
}

function deleteFormSubmit() {
	var form = $(this).closest("form");
	$('#deleteForm').ajaxForm({
		success: function (data) {
			$(".blockUI").remove();
			if(data!="SUCESS") {
				wms.frame.notifyError(data);
			}else{
				location.href ="/auth/role/list";
			}
        }
	});
	$('#deleteForm').submit();
	
}

/**获取组织类型*/
function getAllopt(){
	wms.asyncPost(pagebase+"/auth/opt/allopt",{},{successHandler:function(data, textStatus){
		if(data){		
			var html='<option></option>';
            $.each(data, function(index, item){
                  html+= '<option value="'+item.id+'">'+item.name+'</option>';            
            });
            $('#ouTypeId').html(html);
            $('#ouTypeAdd').html(html);
		} else {
			wms.frame.notifyError("提示信息","系统异常");
		}
	}});
}

/**校验角色名称的唯一性*/
function checkUniqueCode(e, nv) {
	var data = wms.syncPost(pagebase + "/check/checkUniqueCode", { "table":"au_role","fieldValue":$("#nameAdd").val(),"id":$("#idAdd").val(),"fieldName":"name" });
	if (data == true) {
		return wms.validator.SUCCESS;
	}	
	return "角色名称不允许重复";
}