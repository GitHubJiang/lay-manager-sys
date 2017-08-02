/**************************************************/
/***************1. 基本信息的设定***********************/
/*****************************************************/

wms.addResource("zh-CN",{
    'user-info': '提示信息',
    'user-edit-error': '编辑失败',
    'user-block':'批量停用',
    'user-start':'批量启用',
    'user-select-rm-block': '请选择需要停用的员工',
    'user-select-rm-start': '请选择需要启用的员工',
    'user-type-block':'无效',
    'user-type-start':'有效'
    	
});


var tableExtraBatch = [
                       {name: '批量停用',
                        icon: "icon-trash-empty",
                        action: function(checked){
                           if(checked){
                        	   var userIds = "";
                               for(var i=0; i< checked.length; i++){
                            	   userIds = userIds+$(checked[i]).attr("userId")+",";
                               }
                               if(userIds==""){
                           		wms.frame.notifyError(i18n.t("user-info"),i18n.t("user-select-rm-block"));
                           		return;
                           	   }
                               userIds =userIds.substring(0, userIds.length-1);
                               
                               var data={
                       				"ids":userIds,
                       				"lifeCycle":0
                       			};
                    	       wms.asyncPost(pagebase+"/auth/oper/user/block", data,{successHandler:function(data, textStatus){
                    	   				window.location.href=pagebase +"/auth/oper/user/list";
                    	       }});
                           }
                        }},
                        {name: '批量启用',
                            icon: "icon-yen",
                               action: function(checked){
                                   if(checked){
                                	   var userIds = "";
                                       for(var i=0; i< checked.length; i++){
                                    	   userIds = userIds+$(checked[i]).attr("userId")+",";
                                       }
                                       if(userIds==""){
                                   		wms.frame.notifyError(i18n.t("user-info"),i18n.t("user-select-rm-start"));
                                   		return;
                                   	   }
                                       userIds =userIds.substring(0, userIds.length-1);
                                       
                                       var data={
                               				"ids":userIds,
                               				"lifeCycle":1
                               			};
                            	       wms.asyncPost(pagebase+"/auth/oper/user/start", data,{successHandler:function(data, textStatus){
                            	   				window.location.href=pagebase +"/auth/oper/user/list";
                            	       }});
                                   }
                               }}
                   ];

function setActiveMenu() {
    $("a[mid='000']").addClass("active");
}

function pagination(pageNo){
	$("#pageNo").val(pageNo);
	//$j("#user-list").submit();
}

//
function updateUserType(type,userId){
	var data={
			"type":type,
			"userId":userId
		};
    wms.asyncPost("/auth/oper/user/edit", data,{successHandler:function(data, textStatus){
		if (data) {
			 if(type == 1){
				 $("#user"+userId).html("<span class='label label-success'>"+i18n.t('user-type-start')+"</span>");
			 }else{
				 $("#user"+userId).html("<span class='label label-warning'>"+i18n.t('user-type-block')+"</span>");
			 }
		}else{
			isLocation  = true;
			errorNotify(i18n.t("user-info"),i18n.t("user-edit-error"));
		}
	}});
}

/*****************************************************/
/***************2. 页面加载时设定***********************/
/*****************************************************/
wms.addReadyFunc(function(){

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
        if ($(this).val() == 3) {
            $("[data-role=logistics]").show();
        };
        if ($(this).val() == 2) {
            $("[data-role=logistics]").show();
        };
        if ($(this).val() == 1) {
            $("[data-role=logistics]").hide();
        };
    });
    $(".logistics-select").change(function(){
        $("[data-role=hz-warehouse]").show();
        // if ($(this).val() == 1 || $(this).val() == 3) {
        // }else{
        //     $("[data-role=hz-warehouse]").hide();
        // };
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
        $("#delModal").data("targetTr", parentTrId);
    })

    // wms-employee-manage-add-s.html
    $("[data-role=del-btn-ok]").click(function(){
        $("tr[data-id=" + $("#delModal").data("targetTr") + "]").remove();
    });

    $(".icon-radio-check").click(function(){
    	 var userId = $(this).parent().attr("id");
    	 var type = 0;
        if($(this).hasClass("icon-ok")){
        	//停用
            $(this).removeClass("icon-ok").removeClass("icon-ok-newstyle").addClass("icon-cancel-2").addClass("icon-cancel-newstyle");
        }else{
        	//启用
        	type = 1;
            $(this).addClass("icon-ok").addClass("icon-ok-newstyle").removeClass("icon-cancel-2").removeClass("icon-cancel-newstyle");
        }
        
        
        var data={
				"type":type,
				"userId":userId
			};
        wms.asyncPost("/auth/oper/user/edit", data,{successHandler:function(data, textStatus){
			if (data) {
			}else{
				isLocation  = true;
				errorNotify(i18n.t("user-info"),i18n.t("user-edit-error"));
			}
    	}});
        
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
    
    
    
    $(".btn-primary").click(function() {
    	$(".startPage").val("1");
    	$("#user-list").submit();
    })
});



/*****************************************************/
/***************3. FUNCTION 函数和方法*****************/
/**************************************************/