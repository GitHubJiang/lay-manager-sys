/*****************************************************/
/***************1. 基本信息的设定***********************/
/** ************************************************** */


/** ************************************************** */
/** *************2. 页面加载时设定********************** */
/** ************************************************** */

wms.addReadyFunc(function(){
	$("#queryFormBtn").on("click", function() {
		 search();
	 });
	
	$(".btn-add").on("click",function(){
		var form = $("#addForm");	
		wms.asyncPost(pagebase+"/auth/url/allUrl",{},{successHandler:function(data, textStatus){
			if(data){		
				var htmlTd='';
                $.each(data, function(index, item){
                      htmlTd+='<tr><td style="width:20%">'+item.url+'</td>'+
                      '<td style="width:20%"><input class="rows-check" type="checkbox" data-role="view" data-id=\''+item.id+'\'name="rps" value="{funCode:\'view\',urlId:\''+item.id+'\'}"></td>'+
                      '<td style="width:20%"><input class="rows-check" type="checkbox" data-role="add"  data-id=\''+item.id+'\'name="rps" value="{funCode:\'add\',urlId:\''+item.id+'\'}"></td>'+
                      '<td style="width:20%"><input class="rows-check" type="checkbox" data-role="update" data-id=\''+item.id+'\'name="rps" value="{funCode:\'update\',urlId:\''+item.id+'\'}"></td>'+
                      '<td style="width:20%"><input class="rows-check" type="checkbox" data-role="remove" data-id=\''+item.id+'\'name="rps" value="{funCode:\'remove\',urlId:\''+item.id+'\'}"></td></tr>';                      
                });
                $('#tbody').append(htmlTd);
                $("#tbody input:checkbox").iCheck({
        	        checkboxClass: 'icheckbox_square-aero',
        	        radioClass: 'iradio_minimal',
        	        increaseArea: '20%'
        	    });
			} else {
				wms.frame.notifyError("提示信息","系统异常");
			}
		}});
		
		wms.asyncPost(pagebase+"/auth/opt/allopt",{},{successHandler:function(data, textStatus){
			if(data){		
				var html='';
                $.each(data, function(index, item){
                      html+= '<option value="'+item.id+'">'+item.name+'</option>';            
                });
                $('#label-ouType').html(html);                
			} else {
				wms.frame.notifyError("提示信息","系统异常");
			}
		}});
		
		var id = $(this).attr("data-id");	
		if(id){
			wms.asyncPost(pagebase+"/auth/pri/get", {id:id},{successHandler:function(data, textStatus){
				var checkboxs = $("#tbody input:checkbox");
				if(data){
					form.fill(data);
					var priFunMap = data["priFunMap"];
					if(priFunMap) {
						for(var key in priFunMap) {
							var _item = priFunMap[key];						
							for(var i=0;i<_item.length;i++) {							
								checkboxs.filter("[data-id="+key+"][data-role="+_item[i]+"]").iCheck("check");
							}
						}
					}
				} else {
					wms.frame.notifyError("提示信息","系统异常");
				}
			}});
		}
	});	
	
	/**提交表单数据*/
	var flag = false;
	$("#subBtn").on("click",function(){
		//防止表单重复提交		
		var check = $("#addForm").data("bs.validator").checkValid();
	    if(!check){
	    	return "";
	    }
	    if(flag){
			wms.frame.notifyError("不能重复提交");
			return ;
		}
		flag = true;
		var form = $("#addForm");
		var data = form.serializeArray();
		wms.asyncPost(pagebase+"/auth/pri/add", data,{successHandler:function(data, textStatus){
			if(data){
				if(data.code==1) {
					wms.frame.notifySuccess("提示信息","成功");
					setTimeout('eval($("#queryForm").submit())',10);
				} else {
					wms.frame.notifyError("提示信息",data.msg);
					setTimeout('eval($("#queryForm").submit())',1000);
				}
			} else {
				wms.frame.notifyError("提示信息","系统异常");
			}
		}});		
	});
	
	/**关闭modal时清除数据*/
	$("#closeBtn").on("click",function(){
		$("#aclId").val("");
		$("#label-name").val("");		
		$("#label-acl").val("");
		$("#label-ouType").val("");
	});
	//删除相关
	$(".state").on("click",function() {
	 	var id = $(this).attr("data-id");
	 	$("#acl-id").val(id);
	 });
	$("#delete-modal").on("click",function() {
	 	$("#deleteForm").submit();
	});
	
});

/*****************************************************/
/***************3. FUNCTION 函数和方法*****************/
/*****************************************************/
function search() {
	$(".startPage").val("1");
	$("#queryForm").submit();
}

/**校验ACL的唯一性*/
function checkUniqueCode(e, nv) {
	var data = wms.syncPost(pagebase + "/check/checkUniqueCode", { "table":"au_privilege","fieldValue":$("#label-acl").val(),"id":$("#aclId").val(),"fieldName":"acl" });
	if (data == true) {
		return wms.validator.SUCCESS;
	}	
	return "ACL编码不允许重复";
}


