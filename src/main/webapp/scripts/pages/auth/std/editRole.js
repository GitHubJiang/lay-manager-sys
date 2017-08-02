/*****************************************************/
/***************1. 基本信息的设定***********************/
/*****************************************************/
  
wms.addResource("zh-CN",{
    'role-info': '提示信息',
    'role-name-empty': '角色名称不能为空',
    'role-add-s': '新增成功',
    'role-update-s': '修改成功',
    'role-edit-f': '编辑失败',
    'role-update': '修改角色'
});

/*****************************************************/
/***************2. 页面加载时设定***********************/
/*****************************************************/
var isLocation = true;
var isAllCheck =true;
var isListCheck =true;
var isUnCheck =true;
var isListUnCheck =true;
wms.addReadyFunc(function(){
	
	//保存
	$(".role-save").on("click",function(){
		
        var chk = $("#role-form").data("bs.validator").checkValid();
        if(!chk){
        	return;
        }
		//获取名称
		var name = $(".role .name").val();
		//获取组织
		var orgId = $(".role .org-id").val();
		var curpri =null;
		$(".form-group-Privilege").each(function(){
			var me =$(this);
			if(me.css("display")=="block"){
				curpri = me;
			}
		});
		var pris = [];
		if(curpri!=null){
			curpri.find("tbody tr").each(function(i,dom){
				var me = $(dom);
				var acl = "";
				var pri={};
				var funcodes="";
				me.find(".rows-check").each(function(i,dom){
					var rme = $(dom);
					if(rme.get(0).checked){
						if(!(typeof(rme.attr("data-acl"))=='undefined')){
							acl= rme.attr("data-acl");
						}
						var funcode = rme.data("funcode");
						if(!(typeof(funcode)=='undefined')){
							funcodes = funcodes+funcode+",";
						}
					}
				});
				funcodes = funcodes.substring(0, funcodes.length-1);
				if(acl!=""){
					pri.acl=acl;
					pri.funcodes=funcodes;
					pris.push(pri);
				}
				
			});
			
		}
		
		var data={
				"name":name,
				"ouTypeId":orgId,
				"rolePris":pris
			};
		var msg = i18n.t("role-add-s");
		//获取id
		var id = $(".role .id").val();
		var url = pagebase+"/auth/std/role/add";
		if(id!=null && id!=""){
			data.id=id;
			msg=i18n.t("role-update-s");
			url = pagebase+"/auth/std/role/update";
		}
		wms.frame.blockUIItem("div.content");
		$.ajax({
			type : "POST",
			url : url,
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify(data),
			dataType : "json",
			success : function(data) {
				wms.frame.unblockUIItem("div.content");
				 if(data && data.exception){
	                    if(wms.defaultErrors["e-"+data.exception.statusCode]){
	                        wms.frame.notifyError(
	                            data.exception.message || i18n.t(wms.defaultErrors["e-"+data.exception.statusCode])
	                        );
	                    }else{
	                        wms.frame.notifyWarning(data.exception.message);
	                    }
	                    return;
	               }
				if (data.isSuccess && isLocation) {
					window.location.href=pagebase +"/auth/std/role/list";
				} else if(data.isSuccess) {
					$(".role .id").val(data.description.id);
					$(".role-title").html(i18n.t("role-update"));
					isLocation  = true;
					wms.frame.notifySuccess(i18n.t("role-info"),msg);
				}else{
					wms.frame.notifySuccess(i18n.t("role-info"),i18n.t("role-edit-f"));
				}
			},
			error : function(data) {
				
			}
		});
	});
	//保存本页
	$(".role-save-current").on("click",function(){
		isLocation  = false;
		$(".role-save").triggerHandler("click");
	});
	
	//取消
	$(".cancel").on("click",function(){
		window.location.href=pagebase +"/auth/std/role/list";
	});
	//初始化角色已有权限
	if(rps!=null && rps!=""){
		var objs = JSON.parse(rps); 
		for ( var i = 0; i < objs.length; i++) {
			var obj = objs[i];
			$(".form-group-Privilege").find("input[data-acl='"+obj.acl+"'][data-funcode='"+obj.funCode+"']").attr("checked","checked");
		}
	};
	
    //组织选择框
    $('.org-id').change(function() {
		var orgid =$(this).val();
    	$(".form-group-Privilege").each(function(){
    		var me =$(this);
    		if(me.attr("orgid")==orgid){
    			me.show();
    		}else{
    			me.hide();
    		}
    	});
		
    });
   
   var orgid = $('.org-id').attr("orgid");
   if(orgid!=null && orgid!=""){
	   $('.org-id').find("option[value='"+orgid+"']").attr("selected",true);	   
   }
   $(".check-all").on("ifChecked", function() {
       var target = $(this).attr("data-target");
       $("input[data-role=" + target + "]", $(this).parents(".widget")).iCheck("check");
       isListCheck = true;
   })
   $(".check-all").on("ifUnchecked", function() {
	   if(isListCheck){
		   isListUnCheck=false;
	       var target = $(this).attr("data-target");
	       $("input[data-role=" + target + "]", $(this).parents(".widget")).iCheck("uncheck");
	       isListUnCheck=true;
	   }else{
		   isListCheck = true;
	   }
   })
   $("input[data-role=all]").on("ifChecked", function(){
	   $(this).parents("tr").find("input").iCheck("check");
	   isAllCheck = true;
   });
   $("input[data-role=all]").on("ifUnchecked", function(){
	   if(isAllCheck){
		   isUnCheck=false;
		   $(this).parents("tr").find("input").iCheck("uncheck");
		   isUnCheck=true;
	   }else{
		   isAllCheck = true;
	   }
   });
   $("input[data-target=all]").on("ifChecked", function(){
       $(this).parents("tr").find("input").iCheck("check");
   });
   $("input[data-target=all]").on("ifUnchecked", function(){
       $(this).parents("tr").find("input").iCheck("uncheck");
   });
   
   $("input[data-single=check]").on("ifChecked", function(){
       $(this).parents("tr").find("input[data-funcode=view]").iCheck("check");
   });
   $("input[data-single=check]").on("ifUnchecked", function(){
	   if(isUnCheck){
		   isAllCheck = false;
		   $(this).parents("tr").find("input[data-role=all]").iCheck("uncheck");
	   }
	   
	   if(isListUnCheck){
		   isListCheck=false;
		   var fun=$(this).attr("data-funcode");
		   $("input[data-target="+fun+"]").iCheck("uncheck")
	   }
	   
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

});
