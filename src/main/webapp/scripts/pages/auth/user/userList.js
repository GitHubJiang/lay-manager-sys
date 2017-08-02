
/**************************************************/
/***************1. 基本信息的设定***********************/
/*****************************************************/

wms.addResource("zh-CN",{
    'user-info': '提示信息',
    'user-edit-error': '编辑失败'
});


function setActiveMenu() {
    $("a[mid='000']").addClass("active");
}

function pagination(pageNo){
	$("#pageNo").val(pageNo);
	//$j("#user-list").submit();
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
    
    
    $("#confin").click(function() {
    	$(".startPage").val("1");
    	$("#user-list").submit();
    })
});



/*****************************************************/
/***************3. FUNCTION 函数和方法*****************/
/**************************************************/