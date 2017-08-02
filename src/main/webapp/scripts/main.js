(function($){;var self = this;

this.wms = {

    readyfunc: [],

    i18nResources: {},

    defaultErrors: {
        "e-1":"error-message-sys-1",
        "e-4":"error-message-sys-4",
        "e-5":"error-message-sys-5",
        "e-403":"error-message-sys-403",
        "e-404":"error-message-sys-404",
        "e-500":"error-message-sys-500"
    },

    addReadyFunc: function(func){
        if(isString(func)) func = getFunction(func);
        this.readyfunc.push(func);
    },

    documentReady: function(){
        for(var i=0; i< this.readyfunc.length; i++){
            this.readyfunc[i]();
        }
    },

    addResource: function(locale, res){
        if(res){
            if(wms.i18nResources[locale]){
                $.extend(wms.i18nResources[locale]["translation"],res);
            }else{
                wms.i18nResources[locale] = {"translation": res};
            }
        }
    },

    encodeURL: function(url, withTimeStamp){
        if(withTimeStamp == undefined || withTimeStamp)
            return encURL(url,"tt");
        else
            return encURL(url);
    },

    sync: function(url, data, args, submitType){
        var _data, options = _ajaxOptions(url, data, args);
        $.extend(options, {
        	async: false,
        	success: function( data, textStatus, jqXHR){
                if(data && data.exception){
                    _data = {};
                    _data["exception"] = data.exception.message;
                } else {
                	_data = data;
                }
            },

            error: function(jqXHR, textStatus, errorThrown){
            	_data = {};
            	
                switch(jqXHR.status){
                    case 403:
                    	_data["exception"] = i18n.t("error-message-sys-403");
                        break;
                    case 404:
                    	_data["exception"] = i18n.t("error-message-sys-404");
                        break;
                    default:
                    	_data["exception"] = i18n.t("error-message-sys-500");
                }
            }
        });
        
        if(submitType){
            options.type = submitType;
        }
        
        $.ajax(options);
        
        return _data;
    },
    
    async: function(url, data, args, submitType){
        var options = _ajaxOptions(url, data, args);
        $.extend(options, {
            success: function( data, textStatus, jqXHR){
                if(data && data.exception){
                    if(wms.defaultErrors["e-"+data.exception.statusCode]){
                        wms.frame.notifyError(
                            data.exception.message || i18n.t(wms.defaultErrors["e-"+data.exception.statusCode])
                        );
                    }else{
                        wms.frame.notifyWarning(data.exception.message);
                    }
                }else if(this.successHandler){
                    hitch(this,"successHandler")(data, textStatus, jqXHR);
                }else{
                    console.log("no handler defined for success");
                }
            },

            error: function(jqXHR, textStatus, errorThrown){
                if(this.errorHandler){
                    hitch(this,"errorHandler")(jqXHR, textStatus, errorThrown);
                }else{
                    switch(jqXHR.status){
                    case 403:
                        wms.frame.notifyError(i18n.t("error-message-sys-403"));
                        break;
                    case 404:
                        wms.frame.notifyError(i18n.t("error-message-sys-404"));
                        break;
                    default:
                        wms.frame.notifyError(i18n.t("error-message-sys-500"));
                    }
                }
            }
        });
        if(submitType){
            options.type = submitType;
        }
        $.ajax(options);
    },

    asyncGet: function(url, data, args){
        this.async(url,data,args,"GET");
    },

    asyncPost: function(url, data, args){
        this.async(url,data,args,"POST");
    },

    syncGet: function(url, data, args) {
        return this.sync(url, data, args, "GET");
    },

    syncPost: function(url, data, args) {
        return this.sync(url, data, args, "POST");
    },

    initTable: function(context){
        if(window.Sortable == undefined){
            console.log("Sortable is not loaded");
            return;
        }
        var sortable = window.Sortable;
        context = context || document;
        $(context).find("table[data-sortable]").each(function(){
            var $t = $(this), $tcontainer = $(this).parent();
            sortable.initTable(this);
            //add bottom bar
            var $bbar = $('<div class="row table-bottom-bar"><div class="col-md-6 table-bottom-bar-left"></div><div class="col-md-6 table-bottom-bar-right"></div></div>').appendTo($tcontainer);
            //batch operation
            if($t.find("th[data-batch]").length > 0){
                var $th = $t.find("th[data-batch]");
                var $batch = $('<div class="btn-group">' +
                    '<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i class="icon-down-open"></i></button>' +
                    '<ul class="dropdown-menu" role="menu">' +
                    '<li><a class="btn-selectall" href="javascript:void(0);"><i class="icon-check-1"></i> '+ i18n.t("sortable-select-all") + '</a></li>' +
                    '<li><a class="btn-deselectall" href="javascript:void(0);"><i class="icon-check-empty"></i> '+ i18n.t("sortable-unselect-all") + '</a></li></ul></div>').appendTo($th);
                var $batchBottom = $('<div class="btn-group btn-group-sm dropup">' +
                    '<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i class="icon-list"></i>' + i18n.t("sortable-batch-action") + '</button>'+
                    '<ul class="dropdown-menu" role="menu">' +
                    '<li><a class="btn-selectall" href="javascript:void(0);"><i class="icon-check-1"></i> '+ i18n.t("sortable-select-all") + '</a></li>' +
                    '<li><a class="btn-deselectall" href="javascript:void(0);"><i class="icon-check-empty"></i> '+ i18n.t("sortable-unselect-all") + '</a></li></ul></div>').appendTo($bbar.find(".table-bottom-bar-left"));

                if($th.data("extra-batch") && self[$th.data("extra-batch")]){
                    var extraBatch = self[$th.data("extra-batch")];
                    if($.isArray(extraBatch) && extraBatch.length > 0){
                        $batch.find("ul").append('<li class="divider"></li>');
                        $batchBottom.find("ul").append('<li class="divider"></li>');
                        for(var i=0; i< extraBatch.length; i++){
                            var b = extraBatch[i];
                            $batch.find("ul").append('<li><a class="btn-extra" href="javascript:void(0);"><i class="'+ b.icon +'"></i> '+ i18n.t(b.name) +'</a></li></ul></div>');
                            $batchBottom.find("ul").append('<li><a class="btn-extra" href="javascript:void(0);"><i class="'+ b.icon +'"></i> '+ i18n.t(b.name) +'</a></li></ul></div>');
                        }

                        $batch.find(".btn-extra").each(function(i){
                            $(this).click(function(){
                                var checked = [];
                                $t.find("tr").each(function(){
                                    $("td:eq(0) input[type='checkbox']:checked", $(this)).each(function(){
                                        checked.push(this);
                                    });
                                });
                                extraBatch[i].action(checked);
                            });
                        });
                        $batchBottom.find(".btn-extra").each(function(i){
                            $(this).click(function(){
                                var checked = [];
                                $t.find("tr").each(function(){
                                    $("td:eq(0) input[type='checkbox']:checked", $(this)).each(function(){
                                        checked.push(this);
                                    });
                                });
                                extraBatch[i].action(checked);
                            });
                        });
                    }
                }

                $batch.find(".btn-selectall").click(function(){
                    $t.find("tr").each(function(){
                        $("td:eq(0) input[type='checkbox']", $(this)).each(function(){
                            $(this).iCheck('check');
                        });
                    });
                });

                $batch.find(".btn-deselectall").click(function(){
                    $t.find("tr").each(function(){
                        $("td:eq(0) input[type='checkbox']", $(this)).each(function(){
                            $(this).iCheck('uncheck');
                        });
                    });
                });

                $batchBottom.find(".btn-selectall").click(function(){
                    $t.find("tr").each(function(){
                        $("td:eq(0) input[type='checkbox']", $(this)).each(function(){
                            $(this).iCheck('check');
                        });
                    });
                });

                $batchBottom.find(".btn-deselectall").click(function(){
                    $t.find("tr").each(function(){
                        $("td:eq(0) input[type='checkbox']", $(this)).each(function(){
                            $(this).iCheck('uncheck');
                        });
                    });
                });

                //register iCheck Event
                $t.find("tr").each(function(){
                    $("td:eq(0) input[type='checkbox']", $(this)).on('ifChanged', function(evt){
                        var input = evt.target, checked = $(input).is(":checked");
                        if(checked){
                            $t.trigger("rowselected.sortable", [input, $(input).parents("tr")]);
                        }else{
                            $t.trigger("rowunselected.sortable", [input, $(input).parents("tr")]);
                        }

                    });
                });

            }

            //page
            function getPageList(cp, count){
                if(count == 0) return [];
                var start = cp - 2, end = cp + 2;
                start = start >0 ? start: 1;
                end = end > count ? count: end;
                var ret = [];
                for(var i=start; i<=end; i++)
                    ret.push(i);
                return ret;
            }
            if($t.data("page") && $t.data("sortable-type") == "server"){
                var cp = integer($t.data("page")), count = integer($t.data("page-count")),
                    itemcount = integer($t.data("count")), plist = getPageList(cp, count);

                if(count >1){
                    var $pager = $('<div class="toolbar-btn-action"><div class="pager btn-group btn-group-sm"></div></div>').appendTo($bbar.find(".table-bottom-bar-right")),
                        $pcontent = $pager.find("div.pager"),
                        isFirst = (cp ==1),
                        isLast = (cp == count),
                        prev = isFirst? 1: cp - 1,
                        next = isLast? count: cp + 1;

                    $pcontent.append('<button data-original-title="'+ i18n.t("pager-first") +'" data-toggle="tooltip" data-page="1" type="button" class="btn btn-default btn-page-first"><i class="icon-angle-double-left"></i></button>');
                    $pcontent.append('<button data-original-title="'+ i18n.t("pager-prev") +'" data-toggle="tooltip" data-page="'+ prev +'" type="button" class="btn btn-default btn-page-prev"><i class="icon-left-open"></i></button>');

                    for(var i=0; i< plist.length; i++){
                        $pcontent.append('<button data-original-title="'+ plist[i] +'" data-toggle="tooltip" data-page="'+ plist[i] +'" type="button" class="btn btn-default btn-page-'+ plist[i] +'">'+ plist[i] +'</button>');
                    }
                    $pcontent.append('<button data-original-title="'+ i18n.t("pager-next") +'" data-toggle="tooltip" data-page="'+ next +'" type="button" class="btn btn-default btn-page-next"><i class="icon-right-open"></i></button>');
                    $pcontent.append('<button data-original-title="'+ i18n.t("pager-last") +'" data-toggle="tooltip" data-page="'+ count +'" type="button" class="btn btn-default btn-page-last"><i class="icon-angle-double-right"></i></button>');

                    $pager.prepend('<span class="page-indicator">'+ cp +'/' + count + (itemcount?'(' + itemcount + ')':'') + '</span>');
                    $pager.find(".btn-page-"+cp).addClass("active");

                    if(isFirst){
                        $pcontent.find(".btn-page-first").addClass("disabled");
                        $pcontent.find(".btn-page-prev").addClass("disabled");
                    }

                    if(isLast){
                        $pcontent.find(".btn-page-next").addClass("disabled");
                        $pcontent.find(".btn-page-last").addClass("disabled");
                    }

                    $pcontent.find(".btn[data-page]").click(function(e){
                        if($(this).hasClass("active")){
                            e.preventDefault();
                            return;
                        }

                        var formName = $t.data("form");
                        if(formName){
                            var $f = $("#"+formName);

                            $f.find("input[name='page']").val($(this).data("page"));
                            $f.submit();
                        }else{
                            $t.trigger("pagechanged.sortable", $(this).data("page"));
                        }
                    });
                }
            }
            $t.on("sort.sortable", function(evt, th, sort){
                function getSort(th, sort){
                    var s = (sort == "ascending"?"asc":"desc"), sortName="sort-name-" + s,
                        ret = $(th).data(sortName);

                    if(!ret){
                        ret = $(th).data("sort-name")||"";
                        if(ret){
                            ret = ret + " " + s;
                        }
                    }
                    return ret;
                }

                var formName = $(this).data("form"), s= getSort(th,sort);
                if(formName){
                    var $f = $("#"+formName);

                    $f.find("input[name='sort']").val(s);
                    $f.find("input[name='sortDirection']").val($(th).data("sort-name")+ "@" + sort);
                    $f.submit();
                }else{
                    $(this).trigger("sortchanged.sortable", [s,$(th).data("sort-name")+ "@" + sort]);
                }
            });
        });
    }
};

wms.addResource("zh-CN",{
    'error-message-sys-1': '系统异常，请稍候再试或联系系统管理员',
    'error-message-sys-4': '数据绑定异常',
    'error-message-sys-5': '数据验证失败，请检查输入后重试',
    'error-message-sys-403': '您无权访问，请联系系统管理员',
    'error-message-sys-404': '请求页面未找到',
    'error-message-sys-500': '系统异常，请稍候再试或联系系统管理员',
    'pager-first': '第一页',
    'pager-last': '末页',
    'pager-prev': '前一页',
    'pager-next': '后一页',
    'sortable-select-all': '全选',
    'sortable-unselect-all': '取消选择',
    'sortable-batch-action': '批量操作'
});

$(document).ready(function(){
    if(pageRes = self.pageI18nResources){
        for(key in pageRes)
            wms.addResource(key, pageRes[key]);
    }

    i18n.init({lng: pageLocale(), resStore: wms.i18nResources});
    bootbox.setDefaults({locale: pageLocale().replace('-','_')});

    if(self.loadMenu)
        self.loadMenu();

    FastClick.attach(document.body);

    $('.animate-number').each(function(){
        $(this).animateNumbers($(this).attr("data-value"), true, parseInt($(this).attr("data-duration")));
    });

    //SELECT
    $('.selectpicker').selectpicker();


    //FILE INPUT
    $('input[type=file]').fileinput();


    //DATE PICKER
    $('.datepicker-input').datepicker();

    // IOS7 SWITCH
    $(".ios-switch").each(function(){
        mySwitch = new Switch(this);
    });

    //MULTI SELECT
    $("select[multiple='multiple']").multiSelect();

    //TOOLTIP
    $('body').tooltip({
        selector: "[data-toggle=tooltip]",
        container: "body"
    });

    $('.input-group.date').each(function(){
        var min = $(this).data("min"), max = $(this).data("max"),
            defaults = {
                format: "yyyy-mm-dd",
                weekStart: 1,
                clearBtn: true,
                language: pageLocale(),
                autoclose: true,
                todayHighlight: true
            };
        if(min) defaults.startDate = min;
        if(max) defaults.endDate = max;
        $(this).datepicker(defaults);
    });

    $('.input-group.datetime').each(function(){
        var min = $(this).data("min"), max = $(this).data("max"),
            defaults = {
                format: "YYYY-MM-DD HH:mm",
                showClear: true,
                locale: pageLocale(),
                widgetPositioning: {
                    horizontal: 'left'
                }
            };
        if(min) defaults.minDate = min;
        if(max) defaults.maxDate = max;
        $(this).datetimepicker(defaults);
        var _this = this;
        $(this).find("input").on("focus", function(){
            $(_this).data("DateTimePicker").show();
        });
    });

    wms.initTable();

    wms.documentReady();

    //ICHECK
    $('input:not(.ios-switch-success)').iCheck({
        checkboxClass: 'icheckbox_square-aero',
        radioClass: 'iradio_square-aero',
        increaseArea: '20%' // optional
    });
});;var wfm = wms.frame = {
    w: 0,
    h: 0,
    dw: 0,
    dh: 0,

    resizefunc: [],

    notifyOptions: {
        style: 'metro',
        /*globalPosition:'top center',*/
        hideAnimation: "fadeOut",
        showDuration: 0,
        hideDuration: 1000,
        autoHideDelay: 15000,
        autoHide: true,
        clickToHide: true
    },

    addResizeFunc: function(func){
        if(isString(func)) func = getFunction(func);
        this.resizefunc.push(func);
        return this;
    },

    afterResize: function(){
        //do resize
        for(var i=0; i< wfm.resizefunc.length; i++){
            wfm.resizefunc[i]();
        }
    },

    initscrolls: function(){
        if(jQuery.browser.mobile !== true){
            //SLIM SCROLL
            $('.slimscroller').slimscroll({
                height: 'auto',
                size: "5px"
            });

            $('.slimscrollleft').slimScroll({
                height: 'auto',
                position: 'left',
                size: "5px",
                color: '#7A868F'
            });
        }
    },

    togglescroll: function(item){
        if($("#wrapper").hasClass("enlarged")){
            $(item).css("overflow","inherit").parent().css("overflow","inherit");
            $(item). siblings(".slimScrollBar").css("visibility","hidden");
        }else{
            $(item).css("overflow","hidden").parent().css("overflow","hidden");
            $(item). siblings(".slimScrollBar").css("visibility","visible");
        }
    },

    frameChange: function(){
        wfm.w = $(window).width();
        wfm.h = $(window).height();
        wfm.dw = $(document).width();
        wfm.dh = $(document).height();

        if(jQuery.browser.mobile === true){
            $("body").addClass("mobile").removeClass("fixed-left");
        }

        if(!$("#wrapper").hasClass("forced")){
            if(wfm.w > 990){
                $("body").removeClass("smallscreen").addClass("widescreen");
                $("#wrapper").removeClass("enlarged");
            }else{
                $("body").removeClass("widescreen").addClass("smallscreen");
                $("#wrapper").addClass("enlarged");
                $(".left ul").removeAttr("style");
            }
            if($("#wrapper").hasClass("enlarged") && $("body").hasClass("fixed-left")){
                $("body").removeClass("fixed-left").addClass("fixed-left-void");
            }else if(!$("#wrapper").hasClass("enlarged") && $("body").hasClass("fixed-left-void")){
                $("body").removeClass("fixed-left-void").addClass("fixed-left");
            }

        }
        wfm.togglescroll(".slimscrollleft");
    },

    niftyAlert: function(effect,header,text){

        var randLetter = String.fromCharCode(65 + Math.floor(Math.random() * 26));
        var uniqid = randLetter + Date.now();

        $modal =  '<div class="md-modal '+effect+'" id="'+uniqid+'">';
        $modal +=    '<div class="md-content">';
        $modal +=      '<h3>'+header+'</h3>';
        $modal +=      '<div class="md-modal-body">'+text;
        $modal +=      '</div>';
        $modal +=    '</div>';
        $modal +=  '</div>';

        $("body").prepend($modal);

        window.setTimeout(function () {
            $("#"+uniqid).addClass("md-show");
            $(".md-overlay,.md-close").click(function(){
                $("#"+uniqid).removeClass("md-show");
                window.setTimeout(function () {$("#"+uniqid).remove();},500);
            });
        },100);

        return false;
    },

    notifyError: function(title, text){
        if(text == undefined) {
            text = title;
            title = null;
        }
        var n = {
            text: text,
            image: "<i class='icon-alert'></i>"
        };
        if(title)
            $.extend(n,{title:title});
        $.notify(n, $.extend({className: 'error'},wms.frame.notifyOptions));
    },

    notifyWarning: function(title, text){
        if(text == undefined) {
            text = title;
            title = null;
        }
        var n = {
            text: text,
            image: "<i class='icon-attention'></i>"
        };
        if(title)
            $.extend(n,{title:title});
        $.notify(n, $.extend({className: 'warning'},wms.frame.notifyOptions));
    },

    notifyInfo: function(title, text){
        if(text == undefined) {
            text = title;
            title = null;
        }
        var n = {
            text: text,
            image: "<i class='icon-info-circled'></i>"
        };
        if(title)
            $.extend(n,{title:title});
        $.notify(n, $.extend({className: 'info'},wms.frame.notifyOptions));
    },

    notifySuccess: function(title, text){
        if(text == undefined) {
            text = title;
            title = null;
        }
        var n = {
            text: text,
            image: "<i class='icon-info-circled'></i>"
        };
        if(title)
            $.extend(n,{title:title});
        $.notify(n, $.extend({className: 'success'},wms.frame.notifyOptions));
    },

    blockUIItem: function(item) {
        item = item || "#wrapper";
        $(item).block({
            message: '<div class="loading"></div>',
            css: {
                border: 'none',
                width: '14px',
                backgroundColor: 'none'
            },
            overlayCSS: {
                backgroundColor: '#fff',
                opacity: 0.4,
                cursor: 'wait'
            }
        });
    },

    unblockUIItem: function(item) {
        item = item || "#wrapper";
        $(item).unblock();
    },

    //learn from http://davidwalsh.name/fullscreen
    fullScreen: function(element) {
        if(element.requestFullscreen) {
            element.requestFullscreen();
        } else if(element.mozRequestFullScreen) {
            element.mozRequestFullScreen();
        } else if(element.webkitRequestFullscreen) {
            element.webkitRequestFullscreen();
        } else if(element.msRequestFullscreen) {
            element.msRequestFullscreen();
        }
    },

    exitFullscreen: function() {
        if(document.exitFullscreen) {
            document.exitFullscreen();
        } else if(document.mozCancelFullScreen) {
            document.mozCancelFullScreen();
        } else if(document.webkitExitFullscreen) {
            document.webkitExitFullscreen();
        }
    },

    toggleFullscreen: function(){
        if(this.isFullScreenEnabled()){
            if(!this.getFullScreenElement()) {
                this.fullScreen(document.documentElement);
            }else{
                this.exitFullscreen();
            }
        }
    },

    getFullScreenElement: function(){
        return document.fullscreenElement || document.mozFullScreenElement || document.webkitFullscreenElement || document.msFullscreenElement;
    },

    isFullScreenEnabled: function(){
        return document.fullscreenEnabled || document.mozFullScreenEnabled || document.webkitFullscreenEnabled;
    }

}

wfm.addResizeFunc(wfm.initscrolls).addResizeFunc(wfm.frameChange);
wms.addResource("zh-CN",{
    'widget-remove-confirm': '确定要移除此组件么？'
});

wms.addReadyFunc(function(){
    console.debug("wfm ready function part...");
    $(window).resize(debounce(wfm.afterResize,100));
    $(".modal").on("shown.bs.modal",function(){
    	var _vform = $(this).find("form").data('bs.validator');
    	if(_vform) {
    		_vform.validate().clearErrors($(".form-group"));
    	}
	});
  //左边菜单上部展开收缩按钮的控制
    $(".open-left").click(function(e){
        e.stopPropagation();
        $("#wrapper").toggleClass("enlarged");
        $("#wrapper").addClass("forced");

        if($("#wrapper").hasClass("enlarged") && $("body").hasClass("fixed-left")){
            $("body").removeClass("fixed-left").addClass("fixed-left-void");
        }else if(!$("#wrapper").hasClass("enlarged") && $("body").hasClass("fixed-left-void")){
            $("body").removeClass("fixed-left-void").addClass("fixed-left");
        }
        if($("#wrapper").hasClass("enlarged")){
            $(".left ul").removeAttr("style");
        }else{
            $(".subdrop").siblings("ul:first").show();
        }
        wfm.togglescroll(".slimscrollleft");
        $("body").trigger("resize");
    });

    // LEFT SIDE MAIN NAVIGATION
    $("#sidebar-menu a").on('click',function(e){
        if(!$("#wrapper").hasClass("enlarged")){

            if($(this).parent().hasClass("has_sub")) {
                e.preventDefault();
            }

            if(!$(this).hasClass("subdrop")) {
                // hide any open menus and remove all other classes
                $("ul",$(this).parents("ul:first")).slideUp(350);
                $("a",$(this).parents("ul:first")).removeClass("subdrop");
                $("#sidebar-menu .pull-right i").removeClass("fa-angle-down").addClass("fa-angle-up");

                // open our new menu and add the open class
                $(this).next("ul").slideDown(350);
                $(this).addClass("subdrop");
                $(".pull-right i",$(this).parents(".has_sub:last")).removeClass("fa-angle-down").addClass("fa-angle-up");
                $(".pull-right i",$(this).siblings("ul")).removeClass("fa-angle-up").addClass("fa-angle-down");
            }else if($(this).hasClass("subdrop")) {
                $(this).removeClass("subdrop");
                $(this).next("ul").slideUp(350);
                $(".pull-right i",$(this).parent()).removeClass("fa-angle-up").addClass("fa-angle-down");
                //$(".pull-right i",$(this).parents("ul:eq(1)")).removeClass("fa-chevron-down").addClass("fa-chevron-left");
            }
        }
    });

    // NAVIGATION HIGHLIGHT & OPEN PARENT
    $("#sidebar-menu ul li.has_sub a.active").parents("li:last").children("a:first").addClass("active").trigger("click");

    //WIDGET ACTIONS
    $(".widget-header .widget-close").on("click",function(event){
        event.preventDefault();
        $item = $(this).parents(".widget:first");
        bootbox.confirm(i18n.t("widget-remove-confirm"), function(result) {
            if(result === true){
                $item.addClass("animated bounceOutUp");
                window.setTimeout(function () {
                    if($item.data("is-app")){

                        $item.removeClass("animated bounceOutUp");
                        if($item.hasClass("ui-draggable")){
                            $item.find(".widget-popout").click();
                        }
                        $item.hide();
                        $("a[data-app='"+$item.attr("id")+"']").addClass("clickable");
                    }else{
                        $item.remove();
                    }
                }, 300);
            }
        });
    });

    $(document).on("click", ".widget-header .widget-toggle", function(event){
        event.preventDefault();
        $(this).toggleClass("closed").parents(".widget:first").find(".widget-content").slideToggle();
    });

    $(document).on("click", ".widget-header .widget-popout", function(event){
        event.preventDefault();
        var widget = $(this).parents(".widget:first");
        if(widget.hasClass("modal-widget")){
            $("i",this).removeClass("icon-window").addClass("icon-publish");
            widget.removeAttr("style").removeClass("modal-widget");
            widget.find(".widget-maximize,.widget-toggle").removeClass("nevershow");
            widget.draggable("destroy").resizable("destroy");
        }else{
            widget.removeClass("maximized");
            widget.find(".widget-maximize,.widget-toggle").addClass("nevershow");
            $("i",this).removeClass("icon-publish").addClass("icon-window");
            var w = widget.width();
            var h = widget.height();
            widget.addClass("modal-widget").removeAttr("style").width(w).height(h);
            $(widget).draggable({ handle: ".widget-header",containment: ".content-page" }).css({"left":widget.position().left-2,"top":widget.position().top-2}).resizable({minHeight: 150,minWidth: 200});
        }
        window.setTimeout(function () {
            $("body").trigger("resize");
        },300);
    });

    $("a[data-app]").each(function(e){
        var app = $(this).data("app");
        var status = $(this).data("status");
        $("#"+app).data("is-app",true);
        if(status == "inactive"){
            $("#"+app).hide();
            $(this).addClass("clickable");
        }
    });

    $(document).on("click", "a[data-app].clickable", function(event){
        event.preventDefault();
        $(this).removeClass("clickable");
        var app = $(this).data("app");
        $("#"+app).show();
        $("#"+app+" .widget-popout").click();
        topd = $("#"+app).offset().top - $(window).scrollTop();
        $("#"+app).css({"left":"10","top":-(topd-60)+"px"}).addClass("fadeInDown animated");
        window.setTimeout(function () {
            $("#"+app).removeClass("fadeInDown animated");
        }, 300);
    });

    $(document).on("click", ".widget", function(){
        if($(this).hasClass("modal-widget")){
            $(".modal-widget").css("z-index",5);
            $(this).css("z-index",6);
        }
    });

    $(document).on("click", '.widget .reload', function (event) {
        event.preventDefault();
        var el = $(this).parents(".widget:first");
        wfm.blockUIItem(el);
        window.setTimeout(function () {
            wfm.unblockUIItem(el);
        }, 1000);
    });

    $(document).on("click", ".widget-header .widget-maximize", function(event){
        event.preventDefault();
        $(this).parents(".widget:first").removeAttr("style").toggleClass("maximized");
        $("i",this).toggleClass("icon-resize-full-1").toggleClass("icon-resize-small-1");
        $(this).parents(".widget:first").find(".widget-toggle").toggleClass("nevershow");
        $("body").trigger("resize");
        return false;
    });

    $( ".portlets" ).sortable({
        connectWith: ".portlets",
        handle: ".widget-header",
        cancel: ".modal-widget",
        opacity: 0.5,
        dropOnEmpty: true,
        forcePlaceholderSize: true,
        receive: function(event, ui) {$("body").trigger("resize")}
    });

    //Full Screen Toggle
    $("#fs-switch").click(function(evt){
        evt.preventDefault();
        wfm.toggleFullscreen();
    });
    
    // 弹出框居中显示，-- Ray
    $('.modal').on('show.bs.modal', centerModal);
    
    $(window).on("resize", function () {
        $('.modal:visible').each(centerModal);
    });

});;var wv = wms.validator = {
    SUCCESS: "validator.SUCCESS",
    ERROR: "validator.ERROR",

    options: {
        custom: {
            numberex: function(element, e){
                var $e = $(element), datatype = $e.data("numberex"),
                    min = hitch(datatype)($e.data("min")),
                    max = hitch(datatype)($e.data("max")), decimal = integer($e.data("decimal"));
                var result = wms.validator.numberValidator($e.val(),min,max,decimal);
                if(result.startsWith(wms.validator.SUCCESS)){
                    if(result.length > 18 && e.type != "input")
                        $e.val(result.substring(18));
                    return true;
                }else{
                    $(element).data("numberex-error",result);
                    return false;
                }
            },
            custom: function(element, e){
                if(!$.trim($(element).val())) return true;
                var chklist = $(element).data("custom").split(" "),
                    result = wms.validator.SUCCESS, newValue = null;
                if(chklist.length > 0){
                    for(var i=0; i< chklist.length; i++){
                        var f = wms.validator.getValidator(chklist[i]);
                        if(f != null)
                            result = f(element, newValue);
                        else
                            console.log("Definition of validator: " + chklist[i] + "is not found");
                        var cr = wms.validator.chkResult(result);
                        if(cr == null)
                            break;
                        if(cr.length > 0) newValue = cr;
                    }
                }else{
                    throw "Incorrect Custom Check";
                }
                var cr = wms.validator.chkResult(result);
                if(cr == null){
                    $(element).data("custom-error",result);
                    return false;
                }else if(cr.length > 0 && e.type !="input"){
                    $(element).val(cr);
                }
                return true;
            }
        },

        errors: {
            minlength: "validate-minlength",
            numberex: "error",
            custom: "error"
        }
    },

    numberValidator: function(value, min, max, decimal){
        var value = $.trim(value);
        if(!value) return wms.validator.SUCCESS;
        var prefix = value.charAt(0);
        if(prefix === "+" || prefix === "-"){
            value = value.substring(1);
        }else
            prefix = "";
        value = value.replace(/^(0(?=\d))+/,"");

        var regex = new RegExp("^\\d+$");
        if(decimal){
            if(decimal > 0)
                regex = new RegExp("^\\d+\\.?\\d{0," + decimal + "}$");
            else
                regex = new RegExp("^\\d+\\.?\\d*$");
        }else{
            decimal = 0;
        }
        if(!regex.test(value))
            return i18n.t('validate-invalid-number');

        value = value.replace(/^\./,"0.");
        value = value.replace(/\.$/,".0");
        value = prefix + value;

        var v = float(value);
        if((min != null && v < min) || (max != null && v > max))
            return i18n.t('validate-invalid-number-range',{ postProcess: 'sprintf', sprintf: [min, max] });

        if(decimal)
            return wms.validator.SUCCESS + "^" + v.toFixed(decimal);
        else
            return wms.validator.SUCCESS;
    },

    getValidator: function(name){
        if(!name) return null;
        var fname = "check" + capitaliseFirstLetter(name);
        if(wms.validator[fname])
            return wms.validator[fname];
        else if(self[fname])
            return self[fname];
        return null;
    },

    chkResult: function(result){
        if(result == undefined) return null;
        if(result.startsWith(wms.validator.SUCCESS)){
            if(result.length > 18){
                return result.substring(18);
            }else{
                return "";
            }
        }
        return null;
    },

    getOptions: function(options){
        options = options || {};
        $.extend(options, wms.validator.options);

        for(var k in options.errors){
            options.errors[k] = i18n.t(options.errors[k]);
        }
        return options;
    }

};

var wform = wms.form = {
    val : function(element, value){
        if(value == undefined){
            return $(element).val();
        }else{
            $(element).val(value).trigger("change.bs.validator");
        }
    }
};

wms.addResource("zh-CN",{
    'validate-minlength': '输入长度不足',
    'validate-invalid-number': '不是一个合法的数字或精度要求不符合要求',
    'validate-invalid-number-range': '数据超出范围，只能在%s和%s之间'
});

wms.addReadyFunc(function(){
    $('form[data-toggle="form-validator"]').each(function () {
        var $form = $(this), options = self[($form.attr("id")||"") + "Check"];
        $form.validator(wms.validator.getOptions(options));

        $form.on("postinvalid.bs.validator", function(e){
            var $el = $(e.target);
            var errorMsgs = $el.data("bs.validator.posterrors");
            $.each(errorMsgs, function(i,t){
                wms.frame.notifyError(t);
            })
        });
    });
});;this["wms"]["hbs"] = this["wms"]["hbs"] || {};

this["wms"]["hbs"]["hbs/menu.hbs"] = Handlebars.template({"1":function(depth0,helpers,partials,data) {
    var stack1, helper, functionType="function", helperMissing=helpers.helperMissing, escapeExpression=this.escapeExpression, buffer = "    <li class='has_sub'><a href='javascript:void(0);'><i class='"
        + escapeExpression(((helper = (helper = helpers.icon || (depth0 != null ? depth0.icon : depth0)) != null ? helper : helperMissing),(typeof helper === functionType ? helper.call(depth0, {"name":"icon","hash":{},"data":data}) : helper)))
        + "'></i><span>"
        + escapeExpression(((helper = (helper = helpers.name || (depth0 != null ? depth0.name : depth0)) != null ? helper : helperMissing),(typeof helper === functionType ? helper.call(depth0, {"name":"name","hash":{},"data":data}) : helper)))
        + "</span> <span class=\"pull-right\"><i class=\"fa fa-angle-down\"></i></span></a>\n        <ul>\n";
    stack1 = helpers.each.call(depth0, (depth0 != null ? depth0.submenus : depth0), {"name":"each","hash":{},"fn":this.program(2, data),"inverse":this.noop,"data":data});
    if (stack1 != null) { buffer += stack1; }
    return buffer + "        </ul>\n    </li>\n";
},"2":function(depth0,helpers,partials,data) {
    var helper, functionType="function", helperMissing=helpers.helperMissing, escapeExpression=this.escapeExpression;
    return "            <li><a href='"
        + escapeExpression(((helper = (helper = helpers.url || (depth0 != null ? depth0.url : depth0)) != null ? helper : helperMissing),(typeof helper === functionType ? helper.call(depth0, {"name":"url","hash":{},"data":data}) : helper)))
        + "' mid='"
        + escapeExpression(((helper = (helper = helpers.menuId || (depth0 != null ? depth0.menuId : depth0)) != null ? helper : helperMissing),(typeof helper === functionType ? helper.call(depth0, {"name":"menuId","hash":{},"data":data}) : helper)))
        + "'><span>"
        + escapeExpression(((helper = (helper = helpers.name || (depth0 != null ? depth0.name : depth0)) != null ? helper : helperMissing),(typeof helper === functionType ? helper.call(depth0, {"name":"name","hash":{},"data":data}) : helper)))
        + "</span></a></li>\n";
},"compiler":[6,">= 2.0.0-beta.1"],"main":function(depth0,helpers,partials,data) {
    var stack1, buffer = "<ul>\n    <li><a href='index.html' mid=\"000\"><i class='icon-home'></i><span>仪表盘</span></a></li>\n";
    stack1 = helpers.each.call(depth0, depth0, {"name":"each","hash":{},"fn":this.program(1, data),"inverse":this.noop,"data":data});
    if (stack1 != null) { buffer += stack1; }
    return buffer + "</ul>";
},"useData":true});

var wmenu = wms.frame.menu = {

    loadMenu: function(data){
        $('#sidebar-menu').html(wms.hbs["hbs/menu.hbs"](data));
    }
}

wms.addReadyFunc(function(){

});;})(jQuery);;var _self = this;
//获得当前页面的Locale,或者设置Locale
function pageLocale(locale){
    if(locale === undefined)
        return $("body").attr("locale")||"zh-CN";
    else
        $("body").attr("locale",locale);
}

//首字母大写
function capitaliseFirstLetter(str) {
    return str.charAt(0).toUpperCase() + str.slice(1);
}

//判断对象是否是字符串
function isString(obj){
    return typeof obj === "string" || obj instanceof String;
}

//根据名称获得一个函数。函数名中可以用‘.’去调用context中子对象的方法，context默认是页面对象
function getFunction(funcname, context){
    context = context || _self;
    var namespaces = funcname.split(".");
    for(var i=0; i< namespaces.length; i++){
        context = context(namespaces[i]);
        if(context == undefined)
            throw "Function is undefined:" + funcname;
    }
    return context;
}

function integer(value){
    var v = parseInt(value,10);
    return isNaN(v)? null : v;
}

function float(value){
    var v = parseFloat(value);
    return isNaN(v)? null : v;
}

//调用方法
function hitch(scope, method){
    if(!method){
        method = scope;
        scope = null;
    }
    if(isString(method)){
        scope = scope || _self;
        if(!scope[method]){ throw(['hitch: scope["', method, '"] is null (scope="', scope, '")'].join('')); }
        return function(){ return scope[method].apply(scope, arguments || []); }; // Function
    }
    return !scope ? method : function(){ return method.apply(scope, arguments || []); };
}

//获取对象值
function getObject(propName, context){
    context = context || _self;
    var parts = propName.split(".");
    for(var i=0, pn; context &&(pn = parts[i]); i++){
        context = (pn in context ? context[pn] : undefined);
    }
    return context;
}

//设置对象值
function setObject(propName, value, context){
    context = context || _self;
    var parts = propName.split(".");
    var p = parts.pop();
    for(var i=0, pn; context &&(pn = parts[i]); i++){
        context = (pn in context ? context[pn] : context[pn]={});
    }
    return (context && p ? (context[p]=value) : undefined);
}

//返回延时调用函数
function debounce(func, wait, immediate) {
    var timeout, result;
    return function() {
        var args = arguments;
        var later = function() {
            timeout = null;
            if (!immediate) result = func(args);
        };
        var callNow = immediate && !timeout;
        clearTimeout(timeout);
        timeout = setTimeout(later, wait);
        if (callNow) result = func(args);
        return result;
    };
}

//为URL加上时间戳，时间戳的名称可以自己定义，默认是timeflag
function tURL(url, flag){
    flag=flag||"timeflag";
    var iTime=(new Date()).getTime(), pattern=new RegExp(flag+"=\\d{13}");
    if (url.indexOf(flag + "=") >= 0 ){
        url = url.replace(pattern, flag+"="+iTime.toString());
        return url ;
    }
    url+=(/\?/.test(url)) ? "&" : "?";
    return (url+flag+"="+iTime.toString());
}

//encode URL
function encURL(url, flag){
    var index = url.indexOf("?");
    if (index === -1)
        return flag? tURL(url,flag): url;

    var result = url.substring(0, index + 1),
        params = url.substring(index + 1).split("&");

    for (var i=0; i < params.length; i++){
        if (i > 0) result += "&";
        var param = params[i].split("=");
        result += param[0] + "=" + encodeURIComponent(param[1]);
    }

    return flag? tURL(result,flag): result;
}

//数据Ajax提交相关方法
/*used in building ajax data object from one form*/
function ajaxFormToObj(form){
    if(!form) return {};
    form = isString(form) ? $("#" + form).get(0) : form;
    var ret = {},
        exclude = "file|submit|image|reset|button|";

    function _ajaxSetValue(obj, name, value){
        if(value === null) return;
        var val = obj[name];
        if(isString(val)){
            obj[name] = [val, value];
        }else if($.isArray(val)){
            obj[name].push(value);
        }else{
            obj[name] = value;
        }
    }

    function _ajaxFieldValue(domNode){
        var ret = null,
            type = (domNode.type||"").toLowerCase();
        if(domNode.name && type && !domNode.disabled){
            if(type === "radio" || type === "checkbox"){
                if(domNode.checked){ ret = domNode.value }
            }else if(domNode.multiple){
                ret = [];
                $("option",domNode).each(function(){
                    if(this.selected)
                        ret.push(this.value);
                });
            }else{
                ret = domNode.value;
            }
        }
        return ret;
    }

    $.each(form.elements,function(i,e){
        var name = e.name,
            type = (e.type||"").toLowerCase();
        if(name && type && exclude.indexOf(type) === -1 && !e.disabled){
            _ajaxSetValue(ret, name, _ajaxFieldValue(e));
        }
    });
    return ret;
}

/*compose ajax call options*/
function _ajaxOptions(url, data, args){
    var options = {};
    if(arguments.length === 1)
        options = url;
    else{
        options = args || {};
        options["url"] = url;
        if(data){
            if(isString(data)){
                //data is a form id
                $.extend(options, {data: ajaxFormToObj(data)});
            }else
                $.extend(options,{data: data});
        }
    }
    //console.dir(options);
    return options;
}

/*ajax call
 * url ajax call url
 * data data object or form id
 * args other options*/
function asyncXhr(url, data, args){
    return $.ajax(_ajaxOptions(url, data, args));
}

/*ajax call with GET type*/
function asyncXhrGet(url, data, args){
    return $.ajax($.extend({"type":"GET"}, _ajaxOptions(url, data, args)));

}

/*ajax call with POST type*/
function asyncXhrPost(url, data, args){
    return $.ajax($.extend({"type":"POST"}, _ajaxOptions(url, data, args)));
}

/*ajax sync call*/
function syncXhr(url, data, args){
    var _data, options = _ajaxOptions(url, data, args);
    $.extend(options,{
        async : false,
        success : function(data, textStatus){
            _data = data;
        },
        error : function(XMLHttpRequest, textStatus, errorThrown){
            _data = {};
            var exception = {};
            exception["message"] = "Error occurs when fetching data from url:" + this.url;
            exception["cause"] = textStatus? textStatus : errorThrown;
            _data["exception"] = exception;
        }
    });
    $.ajax(options);
    //console.dir(_data);
    return _data;
}

/*ajax sync call with GET type*/
function syncXhrGet(url, data, args){
    if(arguments.length === 1)
        url["type"] = "GET";
    else{
        args = $.extend({},args,{type:"GET"});
    }
    return syncXhr(url, data, args);
}

/*ajax sync call with POST type*/
function syncXhrPost(url, data, args){
    if(arguments.length === 1)
        url["type"] = "POST";
    else{
        args = $.extend({},args,{type:"POST"});
    }
    return this.syncXhr(url, data, args);
}

function ct(ptime){
    if(ptime == undefined)
        return new Date().getTime();
    return new Date().getTime() - ptime;
}

//modal 居中
function centerModal() {
    $(this).css('display', 'block');
    var $dialog = $(this).find(".modal-dialog");
    var offset = ($(window).height() - $dialog.height()) / 2;
    // Center modal vertically in window
    $dialog.css("margin-top", offset);
}