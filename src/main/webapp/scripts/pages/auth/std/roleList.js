/*****************************************************/
/***************1. 基本信息的设定***********************/
/*****************************************************/
  
wms.addResource("zh-CN",{
    'role-info': '提示信息',
    'role-select-rm': '请选择需要删除的角色',
    'role-ms': '删除'
});

/*****************************************************/
/***************2. 页面加载时设定***********************/
/*****************************************************/

var tableExtraBatch = [
   {name: "role-ms",
    icon: "icon-trash-empty",
    action: function(checked){
       if(checked){
    	   var rids = "";
           for(var i=0; i< checked.length; i++){
        	   rids = rids+$(checked[i]).attr("rid")+",";
           }
           if(rids==""){
       		wms.frame.notifyError(i18n.t("role-info"),i18n.t("role-select-rm"));
       		return;
       	   }
           rids =rids.substring(0, rids.length-1);
	       //window.location.href=pagebase+"/auth/std/role/remove?ids="+rids;
	       wms.asyncPost(pagebase+"/auth/std/role/remove", {"ids":rids},{successHandler:function(data, textStatus){
	   				window.location.href=pagebase +"/auth/std/role/list";
	       }});
       }
    }}
];
wms.addReadyFunc(function(){
	
	//搜索
	$(".btn-search").on("click",function(){
		$(".startPage").val(1);
		var form =$(".search-form");
		submitForm(form,{});
	});
	
	$(".btn-search").on("click",function(){
		var ids="";
		$(".rows-check").each(function () {
			if($(this).attr("checked")==("checked")){
				$(this).removeAttr("checked");
				ids =ids+ $(this).attr("rid")+",";
			}
        });
		ids =ids.substring(0, ids.length-1);
	});
	$(".fa-edit").on("click",function(){
		window.location.href= pagebase+"/auth/std/role/update?id="+$(this).attr("rid");	
	});

	//ICHECK
    $('input.rows-check').iCheck({
        checkboxClass: 'icheckbox_minimal',
        radioClass: 'iradio_minimal',
        increaseArea: '20%'
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
    //全选
    $(".icon-check-all").click(function(e){
        e.stopPropagation();
        $(".icon-check-all").toggleClass("icon-check-empty");
        if ($(this).hasClass("icon-check-empty")) {
            $(".rows-check").iCheck("uncheck");
        }else{
            $(".rows-check").iCheck("check");
        };
    });
    //反选
    $(".icon-check-reverse").click(function(e){
        e.stopPropagation();
        $(".rows-check").iCheck("toggle");
        $(".icon-check-reverse").toggleClass("icon-check-empty");
    });
   //批量删除
    $(".icon-trash-batch").on("click",function(){
    	var rids = "";
    	$(".content-all-padding").find("tbody").find(".rows-check").each(function(){
    		var me = $(this);
    		if(me.get(0).checked){
    			rids = rids+me.attr("rid")+",";
    		}
    	});
    	if(rids==""){
    		wms.frame.notifyError(i18n.t("role-info"),i18n.t("role-select-rm"));
    		return;
    	}
    	rids =rids.substring(0, rids.length-1);
    	//window.location.href=pagebase+"/auth/std/role/remove?ids="+rids;
    	wms.asyncPost(pagebase+"/auth/std/role/remove", {"ids":rids},{successHandler:function(data, textStatus){
				window.location.href=pagebase +"/auth/std/role/list";
    	}});
    });
    $(".role-trash-o").on("click",function(){
    	var me = $(this);
    	var rid=me.attr("rid");
    	wms.asyncPost(pagebase+"/auth/std/role/remove", {"ids":rid},{successHandler:function(data, textStatus){
			window.location.href=pagebase +"/auth/std/role/list";
    	}});
    });

});
