/**************************************************/
/***************1. 基本信息的设定***********************/
/*****************************************************/

wms.addResource("zh-CN",{
    'user-info': '提示信息',
    'user-edit-error': '编辑失败',
    'query-power':'查看权限',
    'add-power':'新增权限',
    'user-select':'请选择',
    'error-add-user':'请先添加用户',
    'select-orgId':'请选择组织类型',
    'select-ouId':'请选择组织',
    'select-house':'请组织下的仓库',
    'select-role':'请选择对应的角色'
    	
});

function setActiveMenu() {
    $("a[mid='000']").addClass("active");
}

function findRoleById(id,roleId,ouTypeId){
	$("#myLargeModalLabel").html(i18n.t("query-power"))
	if (ouTypeId == 3) {
            $("[data-role=logistics]").show();
            $("[data-role=hz-warehouse]").show();
            getRoles(ouTypeId);
            var parentOuId = $("#parentOuId"+id).val();
            getUntis(2,null,parentOuId);
        };
        
        if (ouTypeId == 2) {
            $("[data-role=logistics]").show();
            $("[data-role=hz-warehouse]").hide();
            getRoles(ouTypeId);
            var ouId = $("#ouId"+id).val();
            getUntis(ouTypeId,null,ouId);
        };
        if (ouTypeId == 1) {
            $("[data-role=logistics]").hide();
            getRoles(ouTypeId);
    };
	
	$("#saveRoleUser").hide();
	changeFun(roleId,ouTypeId);
	//设置基础组织
	$("#ouTypeId").val(ouTypeId);
	
	
	name = $("#roleName"+id).val();
	//设置角色
	$("#role").empty();
	var selObj = $("#role");  
	selObj.append("<option value='"+roleId+"'>"+name+"</option>");
	
	
	if(ouTypeId == 3){
		$("#house").empty();
		var house = $("#house");  
		var ouName = $("#ouId"+id).attr("ouName");
		var ouId = $("#ouId"+id).val();
		house.append("<option value='"+ouId+"'>"+ouName+"</option>");
	}
	if(ouTypeId == 2){
		$("#ouId").empty();
		var ou = $("#ouId");
		var ouName = $("#ouId"+id).attr("ouName");
		var ouId = $("#ouId"+id).val();
		ou.append("<option value='"+ouId+"'>"+ouName+"</option>");
	}
	$('.selectpicker').selectpicker('refresh');
}

function addRole(){
	$("#myLargeModalLabel").html(i18n.t("add-power"))
	$("#saveRoleUser").show();
	
	$("#ouTypeId").val(-1);
	
	 $("[data-role=logistics]").hide();
     $("[data-role=hz-warehouse]").hide();
     $("#house").empty();
     var house = $("#house");  
     house.append("<option value='-1'>"+i18n.t("user-select")+"</option>");
     
    $("#role").empty();
	var selObj = $("#role");  
	selObj.append("<option value='-1'>"+i18n.t("user-select")+"</option>");
	$("#pri tr:not(:first)").remove();
	$("#groupFun").empty();
	$('.selectpicker').selectpicker('refresh');
}




function changeFun(roleId,ouTypeId){
		var name = "";
		asyncXhr(staticbase+'/auth/std/user/role',{'ouTypeId':ouTypeId,'roleId':roleId}, {
			type : "POST",
			success : function(data,textStatus){
				if(data){
					name = data.roleCommand.name;
					$("#pri tr:not(:first)").remove();
					var str = "<tbody>";
					$.each(data.dataPris,function(n,value) {
						str +="<tr>";
						str +="<td>"+value.name+"</td>"
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


function getRoles(orgId){
	$("#role").empty();
	var selObj = $("#role");  
	
	wms.asyncPost(staticbase+'/auth/std/user/roles',{'ouTypeId':orgId}, {
			type : "POST",
			successHandler : function(data,textStatus){
				if(data){
					selObj.append("<option value='-1'>请选择</option>");
					$.each(data,function(n,value) {
						selObj.append("<option value='"+value.id+"'>"+value.name+"</option>");
					});
				}
				//$('.selectpicker').selectpicker('refresh');
			}
		});
}


function getUntis(orgId,ouId,selectId){
	if(ouId ==null){
		$("#ouId").empty();
		var selObj = $("#ouId"); 
		wms.asyncPost(staticbase+'/auth/std/user/units',{'ouTypeId':orgId}, {
				type : "POST",
				successHandler : function(data,textStatus){
					if(data){
						selObj.append("<option value='-1'>"+i18n.t("user-select")+"</option>");
						$.each(data,function(n,value) {
							if(value.id == selectId){
								selObj.append("<option value='"+value.id+"' selected = 'selected'>"+value.name+"</option>");
							}else{
								selObj.append("<option value='"+value.id+"'>"+value.name+"</option>");
							}
						});
					}
					//$('.selectpicker').selectpicker('refresh');
				}
			});
		
	}else{
		$("#house").empty();
		var selObj = $("#house"); 
		wms.asyncPost(staticbase+'/auth/std/user/units',{'ouTypeId':orgId,'parentOuId':ouId}, {
				type : "POST",
				successHandler : function(data,textStatus){
					if(data){
						selObj.append("<option value='-1'>"+i18n.t("user-select")+"</option>");
						$.each(data,function(n,value) {
							selObj.append("<option value='"+value.id+"'>"+value.name+"</option>");
						});
					}
					//$('.selectpicker').selectpicker('refresh');
				}
			});
	}
}


function roleChange(){
	var ouTypeId = $("#ouTypeId").val();
	var roleId = $("#role").val();
	changeFun(roleId,ouTypeId);
	$('.selectpicker').selectpicker('refresh');
}

function ouChange(obj){
	var ouTypeId = $("#ouTypeId").val();
	if(ouTypeId == 3){
		getUntis(ouTypeId,obj.value);
	}
}



/*****************************************************/
/***************2. 页面加载时设定***********************/
/*****************************************************/
wms.addReadyFunc(function(){
	
	
	$(".pull-right").on("click",function(){
		var chk = $("#edit-user").data("bs.validator").checkValid();
	    if(!chk){
	    	return;
	    }
		
		var userName = $("#userName").val();
		var jobNumber = $("#jobNumber").val();
		var userId = $("#userId").val();
		
		var data={
				"userName":userName,
				"jobNumber":jobNumber,
				"id":userId
			};
		wms.asyncPost(pagebase+"/auth/std/user/update", data,{successHandler : function(data,textStatus){
			if (data.isSuccess) {
				window.location.href=pagebase +"/auth/std/user/list";
			}else{
				wms.frame.notifyError(i18n.t("user-info"),i18n.t("user-edit-error"));
			}
	 }});
	});
	
	
	
	
    //wms-role-manage-add.html
    if (!!$('.selectpicker').attr("data-onChange")) {
        $('.selectpicker').change(function() {
            $($('.selectpicker').attr("data-onChange")).hide().eq($(this).val()).show();
        });
    }
    

    //ICHECK
    $('input.rows-check').iCheck({
        checkboxClass: 'icheckbox_minimal',
        radioClass: 'iradio_minimal',
        increaseArea: '20%' // optional
    });
    $(".check-all").on("ifChecked", function() {
        var target = $(this).attr("data-target");
        $("input[data-role=" + target + "]", $(this).parents(".widget")).iCheck("check");
    })
    $(".check-all").on("ifUnchecked", function() {
        var target = $(this).attr("data-target");
        $("input[data-role=" + target + "]", $(this).parents(".widget")).iCheck("uncheck");
    })
    $("input[data-role=all]").on("ifChecked", function(){
        $(this).parents("tr").find("input").iCheck("check");
    });
    $("input[data-role=all]").on("ifUnchecked", function(){
        $(this).parents("tr").find("input").iCheck("uncheck");
    });
    $("input[data-target=all]").on("ifChecked", function(){
        $(this).parents("tr").find("input").iCheck("check");
    });
    $("input[data-target=all]").on("ifUnchecked", function(){
        $(this).parents("tr").find("input").iCheck("uncheck");
    });

    //wms-role-manage-s.html
    $(".icon-check-all").click(function(e){
        e.stopPropagation();
        $(".icon-check-all").toggleClass("icon-check-empty");
        if ($(this).hasClass("icon-check-empty")) {
            $(".rows-check").iCheck("uncheck");
        }else{
            $(".rows-check").iCheck("check");
        };
    });
    $(".icon-check-reverse").click(function(e){
        e.stopPropagation();
        $(".rows-check").iCheck("toggle");
        $(".icon-check-reverse").toggleClass("icon-check-empty");
    });

    // wms-employee-manage-add-s.html

    $('.organization-type').change(function() {
    	$("#pri tr:not(:first)").remove();
    	$("#groupFun").empty();
    	var ouTypeId = $(this).val();
        if ($(this).val() == 3) {
            $("[data-role=logistics]").show();
            $("[data-role=hz-warehouse]").show();
            getRoles(ouTypeId);
            getUntis(2,null);
        };
        
        if ($(this).val() == 2) {
            $("[data-role=logistics]").show();
            $("[data-role=hz-warehouse]").hide();
            getRoles(ouTypeId);
            getUntis(ouTypeId,null);
        };
        if ($(this).val() == 1) {
            $("[data-role=logistics]").hide();
            getRoles(ouTypeId);
        };
    });
    $(".logistics-select").change(function(){
        var ouTypeId = $("#ouTypeId").val();
        if(ouTypeId == 3){
        	$("[data-role=hz-warehouse]").show();
        }
    });
    // notification

    $(document).on('click', '.notifyjs-metro-base .no', function() {
      //programmatically trigger propogating hide event
      $(this).trigger('notify-hide');
    });
    $(document).on('click', '.notifyjs-metro-base .yes', function() {
      //show button text
      // alert($(this).text() + " clicked!");
      //hide notification
      $(this).trigger('notify-hide');
    });

    $(".del-btn").click(function(){
        var parentTrId = $(this).parents("tr").attr("data-id");
        alert(parentTrId);
        $("#delModal").data("targetTr", parentTrId);
    })

    // wms-employee-manage-add-s.html
    $("[data-role=del-btn-ok]").click(function(){
    	var roleUserId = $("#delModal").data("targetTr");
    	var userId = $("#userId").val();
    	var data={
				"id":roleUserId
			};
    	wms.asyncPost(staticbase+"/auth/std/user/remove", data,{successHandler : function(data,textStatus){
			if (data) {
				$("tr[data-id=" + $("#delModal").data("targetTr") + "]").remove();
			}else{
				isLocation  = true;
				wms.frame.notifyError(i18n.t("user-info"),i18n.t("user-edit-error"));
			}
    	}});
    });

    $(".icon-radio-check").click(function(){
        if($(this).hasClass("icon-ok")){
            $(this).removeClass("icon-ok").removeClass("icon-ok-newstyle").addClass("icon-cancel-2").addClass("icon-cancel-newstyle");
        }else{
            $(this).addClass("icon-ok").addClass("icon-ok-newstyle").removeClass("icon-cancel-2").removeClass("icon-cancel-newstyle");
        }
    });


    //modal 居中
    function centerModal() {
        $(this).css('display', 'block');
        var $dialog = $(this).find(".modal-dialog");
        var offset = ($(window).height() - $dialog.height()) / 2;
        // Center modal vertically in window
        $dialog.css("margin-top", offset);
    }

    $('.modal').on('show.bs.modal', centerModal);
    $(window).on("resize", function () {
        $('.modal:visible').each(centerModal);
    });

    
    /**
     * 添加用户角色信息
     */
    $("#saveRoleUser").on("click",function(){
    	
    	var ouTypeId = $("#ouTypeId").val();
    	var userId = $("#userId").val();
    	var postOuId = 1;
    	if(ouTypeId == null || ouTypeId == '-1'){
    		wms.frame.notifyError(i18n.t("user-info"),i18n.t("select-orgId"));
    		return;
    	}
    	if(ouTypeId == '2' || ouTypeId == '3'){
    		var ouId = $("#ouId").val();
    		if(ouId == null || ouId == '-1'){
    			wms.frame.notifyError(i18n.t("user-info"),i18n.t("select-ouId"));
        		return;
    		}
    		postOuId = ouId;
    		if(ouTypeId == '3'){
    			var house = $("#house").val();
    			if(house == null || house == '-1'){
    				wms.frame.notifyError(i18n.t("user-info"),i18n.t("select-house"));
            		return;
        		} 
    			postOuId = house;
    		}
    	}
    	
    	var roleId = $("#role").val();
    	if(roleId == null || roleId == -1){
    		wms.frame.notifyError(i18n.t("user-info"),i18n.t("select-role"));
    		return;
    	}
    
    	var data={
				"userRole.roleId":roleId,
				"userRole.ouId":postOuId
			};
    	
    	wms.asyncPost(staticbase+'/auth/std/user/add',{'roleId':roleId,'ouId':postOuId,'userId':userId}, {
			type : "POST",
			successHandler : function(data,textStatus) {
				if(data){
					if(data.isSuccess){
						window.location.href=pagebase +"/auth/std/user/update?id="+userId;
					}else{
						wms.frame.notifySuccess(i18n.t('user-info'),data.returnUrl);
					}
				}else{
					wms.frame.notifySuccess(i18n.t('user-info'),i18n.t('user-edit-error'));
				}
			}
		});
    	
    });
    
});



/*****************************************************/
/***************3. FUNCTION 函数和方法*****************/
/**************************************************/