/****************************************************/
/***************1. 基本信息的设定***********************/
/*****************************************************/

var pageI18nResources = {
    'zh-CN': {
        'dashboard_welcome': '欢迎使用WMS'
    }
};

function setActiveMenu() {
    $("a[mid='000']").addClass("active");
}

/*****************************************************/
/***************2. 页面加载时设定***********************/
/*****************************************************/
$(document).ready(function() {

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
        $.notify({
        title: '你确认要这么干吗?!',
        text: '这么做会佩迈墨宦拔权电差误?<div class="clearfix"></div><br><a class="btn btn-sm btn-default yes">是</a> <a class="btn btn-sm btn-danger no">不</a>',
        image: "<i class='fa fa-warning'></i>"
    }, {
        style: 'metro',
        className: "cool",
        showAnimation: "show",
        globalPosition:"t,r",
        showDuration: 0,
        hideDuration: 0,
        autoHide: false,
        clickToHide: false
    });
    })
});



/*****************************************************/
/***************3. FUNCTION 函数和方法*****************/
/****************************************************/