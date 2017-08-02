(function ($) {
    "use strict";

    var defaultOptions = {
        /** Locale */
        locale: 'zh-cn',
        /** 现有已存储的过滤项
         *  Filter 结构
         *  @ name 过滤项名称
         *  @ conditions 过滤项明细
         *  Filter.conditions中 Condition 的结构
         *  @ id Condition标识
         *  @ field 过滤参数名，和输入元素名称一致
         *  @ op 过滤操作符
         *  @ values 过滤参数值，对象数组
         */
        filters: [],
        advanceFilter: {conditions:[]},
        /** 过滤参数候选项列表, key是field，value是Criteria
         * Criteria结构
         * @ id Condition标识
         * @ field 过滤参数名，和输入元素名称一致
         * @ fieldName 过滤参数显示名
         * @ criteriaClass 过滤类型
         * @ fieldParams 过滤项所需其他参数（如候选值列表等）
         * */
        criterias: {},
        /** 当前激活Tag的样式 */
        activeTagClass: "active",
        /** 回调方法 */
        callBack: {},
        /** 回调方法的调用后处理方式, true 代表同步, false 代表异步*/
        syncCallBack : {}
    };

    /** 默认回调方法*/
    var defaultCallBack= {
        /** resultCode: 自定义返回码
         *  resultParams: 对应返回参数集，配合返回码提供所有返回信息*/
        "removeFilter" : function(filter) {
            console.log("Remove Filter:" + filter);
            return {result: true, resultCode: null, resultParams:[]};
        },
        /** resultCode: 自定义返回码
         *  resultParams: 对应返回参数集，配合返回码提供所有返回信息*/
        "addFilter" : function(filter) {
            console.log("Add Filter:" + filter);
            return {result: true, resultCode: null, resultParams:[]};
        },
        /** 参数filter: 如果为空则是为高级搜索增加条件，否则为对应名称的搜索增加条件
         *  resultCode: 自定义返回码
         *  resultParams: 对应返回参数集，配合返回码提供所有返回信息*/
        "addFilterCondition": function(filter, condition){
            console.log("Add Filter Condition for:" + (filter||"高级搜索"));
            console.log(condition);
            return {result: true, resultCode: null, resultParams:[null]};
        },
        /** 参数filter: 如果为空则是为高级搜索增加条件，否则为对应名称的搜索增加条件
         *  resultCode: 自定义返回码
         *  resultParams: 对应返回参数集，配合返回码提供所有返回信息*/
        "updateFilterCondition": function(filter, condition){
            console.log("Update Filter Condition for:" + (filter||"高级搜索"));
            console.log(condition);
            return {result: true, resultCode: null, resultParams:[null]};
        },
        /** 参数filter: 如果为空则是为高级搜索增加条件，否则为对应名称的搜索增加条件
         *  resultCode: 自定义返回码
         *  resultParams: 对应返回参数集，配合返回码提供所有返回信息*/
        "deleteFilterCondition": function(filter, condition){
            console.log("Delete Filter Condition for:" + (filter||"高级搜索"));
            console.log(condition);
            return {result: true, resultCode: null, resultParams:[null]};
        }
    };
    /** 默认回调方法的调用后处理方式, true 代表同步, false 代表异步*/
    var defaultSyncCallBack = {
        "removeFilter" : true
    }

    var tf = $.tagFilter = {

        tabType: {
            DEFAULT_TAB: 0,         //默认搜索Tab
            ADV_SEARCH_TAB: 1,      //高级搜索Tab
            SAVED_FILTER_TAB: 2     //普通存储搜索Tab
        },

        locale: 'zh-cn',
        i18n: {
            'en':{
                "search": "Search",
                "adv-search": "Advance Search",
                "add-criteria": "Add New Criteria",
                "filter-options": "Filters",
                "save-search": "Save",
                "saveas-search": "Clone",
                "search-conditions": "Search",
                "save-search-title": "Name of Search",
                "save-search-placeholder": "Name of Search",
                "confirm-delete": "Confirm?",
                "button-ok": "OK",
                "button-cancel": "Cancel"
            },
            'zh-cn':{
                "search": "搜索",
                "adv-search": "高级搜索",
                "add-criteria": "新增条件",
                "filter-options": "搜索条件",
                "save-search": "保存",
                "saveas-search": "复制",
                "search-conditions": "搜索过滤项",
                "save-search-title": "新增搜索",
                "save-search-placeholder": "为新增的搜索定义一个名称",
                "confirm-delete": "确定删除么?",
                "button-ok": "确定",
                "button-cancel": "取消"
            }
        },
        t: function(res){
            return this.i18n[this.locale][res];
        },
        baseCriteriaClass: {
            /** 获得操作符的描述文本 */
            getOpText: function(operator, locale){
                var op = this.op[operator], txt ="";
                if(op){
                    txt = op.res[locale];
                }
                return txt||operator;
            },
            /** 获得值的显示文本 */
            getValText: function(values, fieldParams){
                if($.isArray(values)){
                    var t = values.join(',');
                    if(t) t = t.substring(0, t.length);
                    return "[" + t + "]";
                }
                return values;
            },
            /** 获得操作选择模块的HTML代码，默认是一个下拉框 */
            getOperatorsHTML: function(locale){
                var r = "";
                for(var key in this.op){
                    r += '<option value="' + key + '">' + this.op[key].res[locale] + '</option>';
                }
                return r;
            },
            /** 获得整个内容区的HTML代码，默认会把操作选择模块放进去*/
            getContentHTML: function(field,locale, fieldParams){
                return '<div class="row">\
                            <div class="col-sm-2">\
                                <select class="form-control">'
                                + this.getOperatorsHTML(locale) +
                                '</select>\
                            </div>\
                            <div class="col-sm-10">\
                                <input type="text" data-name="'
                                + field +
                            '" placeholder="" class="form-control" />\
                            </div>\
                        </div>';
            },
            /** 初始化内容区，确保内容区内的模块都可以成功加载，以及增加相关的事件响应*/
            initContent: function(pane){
                //do nothing
            },
            /** 获得当前过滤选项对应的Field*/
            getFieldName: function(pane, criterias){
                return criterias[$(".modal-title", pane).attr("data-field")].fieldName;
            },
            /** 获得当前过滤项选择的过滤操作*/
            getSelectedOperator: function(pane){
                return pane.find("select").val();
            },
            /** 获得当前过滤项设置的过滤值*/
            getValues: function(pane){
                return pane.find("input").val();
            },
           /** 根据目前存储的过滤值回填界面*/
            setPaneValue: function(pane, val, criterias){
                $(".modal-title", pane).attr("data-field", val.field);
                $(".modal-title", pane).text(criterias[val.field].fieldName);
                $("select", pane).val(val.op);
                $("input", pane).val(val.values);
            }
        },
        registNewFilterClass: function(name, filter){
            tf.filters[name] = filter;
        },
        getFilterClass: function(type){
            return this.criterias[type];
        }
    };

    var tfc = tf.criterias = {
        "string": $.extend({}, tf.baseCriteriaClass,{ op: {
            "equals": { res: {
                'en': "Equals",
                'zh-cn': "等于"
            }},

            "contains": { res: {
                'en': "Contains",
                'zh-cn': "包含"
            }},

            "start": { res: {
                'en': "Starts with",
                'zh-cn': "开始于"
            }},

            "end": { res: {
                'en': "Ends with",
                'zh-cn': "结束于"
            }}
        }}),
        "date": $.extend({}, tf.baseCriteriaClass,{ op: {
            "equals": { res: {
                'en': "Equals",
                'zh-cn': "等于"
            }},

            "contains": { res: {
                'en': "Range",
                'zh-cn': "范围"
            }},

            "start": { res: {
                'en': "Starts with",
                'zh-cn': "开始于"
            }},

            "end": { res: {
                'en': "Ends with",
                'zh-cn': "结束于"
            }}
        },
        getContentHTML: function(field,locale){
            return '<div class="row">\
                    <div class="col-sm-2">\
                        <select class="form-control">'
                + this.getOperatorsHTML(locale) +
                '</select>\
            </div>\
            <div class="col-sm-4">\
                <div class="input-group date">\
                    <input type="text" data-idx="1" placeholder="" class="form-control" />\
                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>\
                </div>\
            </div>\
            <div class="col-sm-4">\
                <div class="input-group date">\
                    <input type="text" data-idx="2" placeholder="" class="form-control" />\
                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>\
                </div>\
            </div>';
        },
        initContent: function(pane){
            $('.input-group.date', pane).each(function(){
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
            if($("select", pane).val() != "contains"){
                $(pane).find("input[data-idx='2']").prop("disabled",true);
            }
            $("select", pane).change(function(e){
                console.log("change");
                if($(this).val() == "contains"){
                    $(pane).find("input[data-idx='2']").prop("disabled",false);
                }else{
                    $(pane).find("input[data-idx='2']").val("").prop("disabled",true);
                }
            });
        },
        getValues: function(pane){
            if($("select", pane).val() == "contains"){
                return [$(pane).find("input[data-idx='1']").val(),
                    $(pane).find("input[data-idx='2']").val()];
            }else{
                return $(pane).find("input[data-idx='1']").val();
            }

        },
        setPaneValue: function(pane, val, criterias){
            $(".modal-title", pane).attr("data-field", val.field);
            $(".modal-title", pane).text(criterias[val.field].fieldName);
            $("select", pane).val(val.op);
            if($.isArray(val.values)){
                $(pane).find("input[data-idx='1']").val(val.values[0]);
                $(pane).find("input[data-idx='2']").val(val.values[1]);
            }else
                $(pane).find("input[data-idx='1']").val(val.values);
        }
        }),
        "datetime": $.extend({}, tf.baseCriteriaClass,{ op: {
            "equals": { res: {
                'en': "Equals",
                'zh-cn': "等于"
            }},

            "contains": { res: {
                'en': "Range",
                'zh-cn': "范围"
            }},

            "start": { res: {
                'en': "Starts with",
                'zh-cn': "开始于"
            }},

            "end": { res: {
                'en': "Ends with",
                'zh-cn': "结束于"
            }}
        },
        getContentHTML: function(field,locale){
            return '<div class="row">\
                <div class="col-sm-2">\
                    <select class="form-control">'
                + this.getOperatorsHTML(locale) +
                '</select>\
            </div>\
            <div class="col-sm-4">\
                <div class="input-group datetime">\
                    <input type="text" data-idx="1" placeholder="" class="form-control" />\
                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>\
                </div>\
            </div>\
            <div class="col-sm-4">\
                <div class="input-group datetime">\
                    <input type="text" data-idx="2" placeholder="" class="form-control" />\
                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>\
                </div>\
            </div>';
        },
        initContent: function(pane){
            $('.input-group.datetime', pane).each(function(){
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
            if($("select", pane).val() != "contains"){
                $(pane).find("input[data-idx='2']").prop("disabled",true);
            }
            $("select", pane).change(function(e){
                console.log("change");
                if($(this).val() == "contains"){
                    $(pane).find("input[data-idx='2']").prop("disabled",false);
                }else{
                    $(pane).find("input[data-idx='2']").val("").prop("disabled",true);
                }
            });
        },
        getValues: function(pane){
            if($("select", pane).val() == "contains"){
                return [$(pane).find("input[data-idx='1']").val(),
                    $(pane).find("input[data-idx='2']").val()];
            }else{
                return $(pane).find("input[data-idx='1']").val();
            }

        },
        setPaneValue: function(pane, val, criterias){
            $(".modal-title", pane).attr("data-field", val.field);
            $(".modal-title", pane).text(criterias[val.field].fieldName);
            $("select", pane).val(val.op);
            if($.isArray(val.values)){
                $(pane).find("input[data-idx='1']").val(val.values[0]);
                $(pane).find("input[data-idx='2']").val(val.values[1]);
            }else
                $(pane).find("input[data-idx='1']").val(val.values);
        }
        }),
        "singleselect": $.extend({}, tf.baseCriteriaClass,{ op: {
            "equals": { res: {
                'en': "Equals",
                'zh-cn': "等于"
            }}
        },
        _convertParams: function(arr){
            var r = {};
            for(var i=0; i< arr.length; i++)
                r[arr[i]["key"]] = arr[i]["value"];
            return r;
        },
        getContentHTML: function(field,locale,fieldParams){
            var c = '<div class="row"><div class="col-sm-12">';
            if(fieldParams){
                if($.isArray(fieldParams)){
                    return this.getContentHTML(field, locale, this._convertParams(fieldParams));
                }else{
                    for(var k in fieldParams){
                        c += '<label class="radio-inline">\
                            <input type="radio" name="input-radio" value="' + k + '"/>' + fieldParams[k] +
                        '</label>';
                    }
                }
            }
            c += '</div></div>';
            return c;
        },
        initContent: function(pane){
            $('input[name="input-radio"]',pane).iCheck({
                checkboxClass: 'icheckbox_square-aero',
                radioClass: 'iradio_square-aero',
                increaseArea: '20%' // optional
            });
        },
        getSelectedOperator: function(pane){
            return "equals";
        },
        getValText: function(values, fieldParams){
            if($.isArray(fieldParams)){
                return this._convertParams(fieldParams)[values];
            }else
                return fieldParams[values];
        },
        getValues: function(pane){
            return $('input[name="input-radio"]:checked',pane).val();
        },
        setPaneValue: function(pane, val, criterias){
            $(".modal-title", pane).attr("data-field", val.field);
            $(".modal-title", pane).text(criterias[val.field].fieldName);
            $('.modal-body input[value="' + val.values + '"]',pane).prop("checked",true);
        }
        }),
        "multiselect": $.extend({}, tf.baseCriteriaClass,{ op: {
            "contains": { res: {
                'en': "Contains",
                'zh-cn': "包含"
            }}
        },
        _convertParams: function(arr){
            var r = {};
            for(var i=0; i< arr.length; i++)
                r[arr[i]["key"]] = arr[i]["value"];
            return r;
        },
        getContentHTML: function(field,locale,fieldParams){
            var c = '<div class="row"><div class="col-sm-12">';
            if(fieldParams){
                if($.isArray(fieldParams)){
                    return this.getContentHTML(field, locale, this._convertParams(fieldParams));
                }else{
                    for(var k in fieldParams){
                        c += '<label class="checkbox-inline">\
                        <input type="checkbox" name="input-checkbox" value="' + k + '"/>' + fieldParams[k] +
                            '</label>';
                    }
                }
            }
            c += '</div></div>';
            return c;
        },
        initContent: function(pane){
            $('input[name="input-checkbox"]',pane).iCheck({
                checkboxClass: 'icheckbox_square-aero',
                radioClass: 'iradio_square-aero',
                increaseArea: '20%' // optional
            });
        },
        getSelectedOperator: function(pane){
            return "contains";
        },
        getValText: function(values, fieldParams){
            if($.isArray(fieldParams)){
                return this._convertParams(fieldParams)[values];
            }else {
                var rv = [];
                for(var i=0; i< values.length; i++)
                    rv.push(fieldParams[values[i]]);

                var t = rv.join(',');
                if(t) t = t.substring(0, t.length);
                return "[" + t + "]";
            }
        },
        getValues: function(pane){
            var rv = [];
            $('input[name="input-checkbox"]:checked',pane).each(function(){
                rv.push($(this).val());
            });
            return rv;
        },
        setPaneValue: function(pane, val, criterias){
            $(".modal-title", pane).attr("data-field", val.field);
            $(".modal-title", pane).text(criterias[val.field].fieldName);
            for(var i=0; i< val.values.length; i++)
                $('.modal-body input[value="' + val.values[i] + '"]',pane).prop("checked",true);
        }
        }),
        "custom": {}
    };

    var tfv = tf.views = {
        getCriteriaListHTML: function(config){
            return $.map(config.criterias, function(val, key){
                return '<li><a href="javascript:void(0);" data-field="' + val.field + '">' + val.fieldName + '</a></li>';
            }).join('\n');
        },
        getNavFiltersHTML: function(config){
            return $.map(config.filters, function(val, key){
                return '<li data-filter="' + val.name + '"><a>'+val.name+'</a><i class="icon-cancel"></i></li>';
            }).join('\n');
        },
        getNavHTML: function(config){
            return '<ul class="nav nav-tabs">\
                                <li class="'+ config.activeTagClass +'"><a>' + tf.t("search") + '</a></li>'
                + this.getNavFiltersHTML(config) +
                '<li class="advanced-search-text"><a>' + tf.t("adv-search") + '</a></li>\
                </ul>';
        },
        getTagHTML: function(condition, criterias){
            var c = criterias[condition.field],
                fc = tf.getFilterClass(c.criteriaClass),
                txtVal = fc.getValText(condition.values, c.fieldParams),
                tagText = c.fieldName + ' ' + fc.getOpText(condition.op,tf.locale) + ' ' + txtVal;
            return '<span data-cid="' + condition.id + '" data-field="' + condition.field + '" data-operator="' + condition.op + '" data-value="'+ txtVal +'" class="tag label label-info">' + tagText + '<span data-role="remove"></span></span>';
        },
        getFilterTagsHTML: function(filter,criterias){
            return $.map(filter.conditions, function(val, key){
                return tfv.getTagHTML(val,criterias);
            }).join('\n');
        },
        getOneContentHTML: function(filter, config){
            return '<div class="filter-content" data-filter="' + filter.name +'">\
                    <div class="row">\
                        <div class="col-md-2 col-xs-12">\
                            <div class="toolbar-btn-action filter-text">\
                                <a class="saveas-condition"><u>' + tf.t("saveas-search") + '</u></a><span>' + tf.t("search-conditions") + ':</span></div>\
                        </div>\
                        <div class="col-md-10 col-xs-12">\
                            <div class="bootstrap-tagsinput">' + tfv.getFilterTagsHTML(filter, config.criterias) + '</div>\
                    </div></div></div>';
        },
        getContentHTML: function(config){
            var r = [];
            for(var f,i=0; f=config.filters[i]; i++)
                r.push(this.getOneContentHTML(f, config));
            return r.join('\n');
        },
        getContentWrapHTML: function(config){
            return '<div class="filter-content-wrap">\
                                <div class="filter-search-pane">\
                                        <div class="input-group">\
                                            <div class="input-group-btn">\
                                                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">' + tf.t("filter-options") + '<span class="caret"></span></button>\
                                                    <ul class="dropdown-menu" role="menu">'
                                                        + this.getCriteriaListHTML(config) +
                                                    '</ul>\
                                            </div>\
                                            <input type="text" class="form-control" name="q_string_inputCommSearch" >\
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span>\
                                        </div>\
                                </div>\
                                <div class="filter-content"></div>'
                                + this.getContentHTML(config) +
                                '<div class="filter-content adv-filter-content">\
                                    <div class="row">\
                                        <div class="col-md-2 col-xs-12">\
                                            <div class="toolbar-btn-action filter-text">\
                                                <a class="save-condition"><u>' + tf.t("save-search") + '</u></a><span>' + tf.t("search-conditions") + ':</span></div>\
                                        </div>\
                                        <div class="col-md-10 col-xs-12">\
                                            <div class="bootstrap-tagsinput"></div>\
                                        </div>\
                                    </div>\
                                </div>\
                    </div>';
        },
        getSaveFilterDialogHTML: function(){
            return '<div class="modal fade" name="saveFilterDialog" tabindex="-1" role="dialog" aria-labelledby="saveFilterModalLabel">\
                <div class="modal-dialog" role="document">\
                <div class="modal-content">\
                    <div class="modal-header">\
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>\
                        <h4 class="modal-title" id="saveFilterModalLabel">' + tf.t("save-search-title") + '</h4>\
                    </div>\
                    <div class="modal-body">\
                        <input type="text" class="form-control" placeholder="' + tf.t("save-search-placeholder") + '" />\
                    </div>\
                    <div class="modal-footer">\
                        <button type="button" class="btn btn-primary">' + tf.t("button-ok") + '</button>\
                        <button type="button" class="btn btn-default" data-dismiss="modal">' + tf.t("button-cancel") + '</button>\
                    </div>\
                </div></div></div>';
        },
        getConditionDialogHTML: function(){
            return '<div class="modal fade" name="conditionDialog" tabindex="-1" role="dialog" aria-labelledby="conditionModalLabel">\
                <div class="modal-dialog modal-lg" role="document">\
                <div class="modal-content">\
                    <div class="modal-header">\
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>\
                        <h4 class="modal-title" id="conditionModalLabel"></h4>\
                    </div>\
                    <div class="modal-body">\
                        <div class="container-fluid"></div>\
                    </div>\
                    <div class="modal-footer">\
                        <button type="button" class="btn btn-primary">' + tf.t("button-ok") + '</button>\
                        <button type="button" class="btn btn-default" data-dismiss="modal">' + tf.t("button-cancel") + '</button>\
                    </div>\
                </div></div></div>';
        },
        getConfirmDialogHTML: function(){
            return '<div class="modal fade" name="confirmDialog" tabindex="-1" role="dialog" aria-labelledby="confirmModalLabel">\
                <div class="modal-dialog modal-sm" role="document">\
                <div class="modal-content">\
                    <div class="modal-header">\
                        <h4 class="modal-title" id="confirmModalLabel"></h4>\
                    </div>\
                    <div class="modal-footer">\
                        <button type="button" class="btn btn-primary">' + tf.t("button-ok") + '</button>\
                        <button type="button" class="btn btn-default" data-dismiss="modal">' + tf.t("button-cancel") + '</button>\
                    </div>\
                </div></div></div>';
        },
        getEditFilterPaneContentHTML: function(field, config){
            var c = config.criterias[field], cc = tf.getFilterClass(c.criteriaClass);
            return cc.getContentHTML(field,config.locale, c["fieldParams"]);
        }
    };

    function TagFilter(element, options) {
        this.element = element;
        $.extend(this.config, options);
        for(var c in defaultCallBack){
            if(!this.config.callBack[c]){
                this.config.callBack[c] = defaultCallBack[c];
            }
            if(this.config.syncCallBack[c] == undefined){
                this.config.syncCallBack[c] = defaultSyncCallBack[c];
            }
        }
        tf.locale = this.config.locale;
        this.init();
    }

    TagFilter.prototype = {
        element: undefined,
        config: defaultOptions,

        $defaultTab: undefined,
        $defaultContent: undefined,
        $advSearchTab: undefined,
        $conditionDialog: undefined,
        $filterSaveDialog: undefined,
        $confirmDialog: undefined,

        initElements: function(){
            var $e = $(this.element);
            this.$defaultTab = $("ul.nav li", $e).eq(0);
            this.$defaultContent = $(".filter-content", $e).eq(0);
            this.$advSearchTab = $("li.advanced-search-text", $e);
            this.$conditionDialog = $("div[name='conditionDialog']", $e);
            this.$filterSaveDialog = $("div[name='saveFilterDialog']", $e);
            this.$confirmDialog = $("div[name='confirmDialog']", $e);
        },

        init: function(){
            var $e = $(this.element);
            $e.html(tfv.getNavHTML(this.config) + tfv.getContentWrapHTML(this.config) +
                tfv.getSaveFilterDialogHTML() + tfv.getConditionDialogHTML() + tfv.getConfirmDialogHTML());

            this.initElements();

            var activeClass = this.config.activeTagClass;
            //Event Handling
            $e.off();

            //Tab点击切换和关闭
            var self = this;
            $e.on("click",".nav li", function(e){
                var _self = $(this),
                    isVisible = _self.hasClass(activeClass);
                if(e.target.tagName == 'I'){
                    self.$confirmDialog.data("tag",_self);
                    self.$confirmDialog.find(".modal-title").text(tf.t("confirm-delete"));
                    self.$confirmDialog.modal({});

                }else{
                    if(!isVisible)
                        self.switchTab(_self);
                }
            });

            this.$confirmDialog.on("click", "button.btn-primary", function(e){
                self.removeTab(self.$confirmDialog.data("tag"));
                self.$confirmDialog.modal('hide');
            });

            //打开增加条件面板
            $e.on("click", ".filter-search-pane ul.dropdown-menu a", function(e){
                self.$conditionDialog.data("tag",null);  //new Condition
                self.$conditionDialog.data("field",$(this).data("field"));  //set Condition Field
                self.$conditionDialog.modal({});
            });

            //点击查询条件
            $e.on("click", ".bootstrap-tagsinput .tag", function(e){
                var _self = this,
                    $tab = $e.find(".nav li.active"),
                    idx = $tab.index(),
                    field = $(this).attr("data-field"),
                    c = self.config.criterias[field],
                    cc = tf.getFilterClass(c.criteriaClass),
                    is_advSearch = $tab.hasClass("advanced-search-text"),
                    conditions = is_advSearch ? self.config.advanceFilter.conditions:
                        self.config.filters[idx-1].conditions,
                    cidx = $(_self).index(),
                    condition = conditions[cidx];

                if(e.target.getAttribute('data-role') == 'remove'){

                    self.deleteCondition($tab, cidx, condition);
                }else{
                    self.$conditionDialog.data("tag",_self);  //edit current Condition
                    self.$conditionDialog.data("field",field);  //set Condition Field
                    self.$conditionDialog.modal({});
                }
            });

            this.$conditionDialog.on('shown.bs.modal', function (event) {
                var modal = $(this),
                    tag = modal.data("tag"),
                    field = modal.data("field"),
                    c = self.config.criterias[field],
                    cc = tf.getFilterClass(c.criteriaClass),
                    cidx = $(tag).index();

                $(".modal-title", modal).text(self.config.criterias[field].fieldName).attr("data-field",field);
                $(".modal-body .container-fluid", modal).html(tfv.getEditFilterPaneContentHTML(field,self.config));

                if(tag){
                    //edit current Condition
                    var $tab = $e.find(".nav li.active"),
                        idx = $tab.index(),
                        is_advSearch = $e.find(".nav li.active").hasClass("advanced-search-text"),
                        conditions = is_advSearch ? self.config.advanceFilter.conditions:
                            self.config.filters[idx-1].conditions,
                        condition = conditions[cidx];
                    cc.setPaneValue(modal, condition, self.config.criterias);
                }
                cc.initContent($(".modal-body", modal));

            })

            //增加/更新条件
            this.$conditionDialog.on("click", "button.btn-primary", function(e){
                var modal = self.$conditionDialog,
                    tag = modal.data("tag"),
                    field = modal.data("field"),
                    c = self.config.criterias[field],
                    cc = tf.getFilterClass(c.criteriaClass),
                    $tab = $e.find(".nav li.active");

                var crit = {
                    "id": tag? $(tag).data("cid"):null,
                    "field":field,
                    "op": cc.getSelectedOperator(self.$conditionDialog),
                    "values": cc.getValues(self.$conditionDialog),
                    "criteriaClass":c.criteriaClass
                };

                if(crit.values){
                    if(!tag){
                        //add Condition
                        var rtn = self.addCondition($tab, crit);
                        if(rtn && rtn.result)
                            self.$conditionDialog.modal('hide');
                    }else{
                        var rtn = self.updateCondition($tab, $(tag).index(), crit);
                        if(rtn && rtn.result)
                            self.$conditionDialog.modal('hide');
                    }

                }else{
                    //do nothing
                }
            });


            //打开保存条件面板
            $e.on("click", ".save-condition,.saveas-condition", function(e){
                self.$filterSaveDialog.modal({});
            });

            this.$filterSaveDialog.on('shown.bs.modal', function (event) {
                var modal = $(this)
                modal.find('.modal-body input').val("").focus();
            })

            //增加条件
            this.$filterSaveDialog.on("click", "button.btn-primary", function(e){
                var $tab = $e.find(".nav li.active"),
                    name = $(".modal-body input",self.$filterSaveDialog).val();
                if(name){
                    var rtn = self.saveFilter($tab, name);
                    if(rtn && rtn.result){
                        self.$filterSaveDialog.modal('hide');
                    }
                }
            });

            //异步调用的时候使用
            $e.on("filterRemoved", function(e, obj){
                var filter = obj.filter,
                    $fcontent = $('.filter-content', $e),
                    $e = $(self.element),
                    $tab = $(".nav li[data-filter='" + filter.name + "']", $e),
                    idx = $tab.index(),
                    activeClass = self.config.activeTagClass,
                    isVisible = $tab.hasClass(activeClass);

                $tab.remove();
                $fcontent.eq(idx).remove();

                if(isVisible){
                    self.$defaultTab.addClass(activeClass);
                    self.$defaultContent.show();
                    $e.trigger("tagChanged", {
                        fromTabType: tf.tabType.SAVED_FILTER_TAB, fromFilter: filter,
                        toTabType: tf.tabType.DEFAULT_TAB, toFilter: null});
                }

                self.config.filters.splice(idx-1,1);
                $e.trigger("tagRemoved", obj);
            });
        },

        removeTab: function($tab){
            var $e = $(this.element),
                $fcontent = $('.filter-content', $e),
                activeClass = this.config.activeTagClass,
                idx = $tab.index(),
                isVisible = $tab.hasClass(activeClass),
                t = ct();

            //call Remove Callback
            var rtn = hitch(this.config.callBack, "removeFilter")($tab.data("filter"));
            if(this.config.syncCallBack["removeFilter"]){
                if(rtn && rtn.result){
                    $e.trigger("filterRemoved", {filter: $tab.data("filter"), timeExpended: ct(t)});
                }else{
                    $e.trigger("tagRemoveFailed", rtn);
                }
            }
        },

        switchTab: function($tab){
            var $e = $(this.element),
                $fcontent = $('.filter-content', $e),
                activeClass = this.config.activeTagClass,
                $ptab = $(".nav li." + activeClass, $e),
                idx = $tab.index(),
                pidx = $ptab.index(),
                pf = null,
                ptabType = tf.tabType.SAVED_FILTER_TAB,
                tabType = tf.tabType.SAVED_FILTER_TAB,
                f = null;

            if(pidx == 0) ptabType = tf.tabType.DEFAULT_TAB;
            else if($ptab.hasClass("advanced-search-text")){
                ptabType = tf.tabType.ADV_SEARCH_TAB;
                pf = this.config.advanceFilter;
            }else{
                pf = this.config.filters[pidx-1];
            }

            if(idx == 0) tabType = tf.tabType.DEFAULT_TAB;
            else if($tab.hasClass("advanced-search-text")){
                tabType = tf.tabType.ADV_SEARCH_TAB;
                f = this.config.advanceFilter;
            }else{
                f = this.config.filters[idx-1];
            }

            $fcontent.hide();
            $fcontent.eq(idx).show();

            $tab.addClass(activeClass).siblings().removeClass(activeClass);
            $e.trigger("tagChanged", {fromTabType: ptabType, fromFilter: pf,
                toTabType: tabType, toFilter: f});
        },

        addCondition: function($tab, condition){
            var $e = $(this.element),
                is_advSearch = $tab.hasClass("advanced-search-text"),
                filter = $tab.data("filter"),
                idx = $tab.index(),
                $fcontent = (idx==0?$('.filter-content', $e).last():$('.filter-content', $e).eq(idx)),
                t = ct();


            //call Add Condition Callback
            var rtn = hitch(this.config.callBack, "addFilterCondition")(filter, condition);

            if(rtn && rtn.result){
                condition.id = rtn.resultParams[0];
                //addCondition at first tab
                if(idx == 0){
                    this.config.advanceFilter.conditions = [condition];
                }else if(is_advSearch){
                    this.config.advanceFilter.conditions.push(condition);
                }else{
                    this.config.filters[idx-1].conditions.push(condition);
                }
                if(idx == 0){
                    this.$advSearchTab.show();
                    this.switchTab(this.$advSearchTab);
                    $fcontent.find(".bootstrap-tagsinput").html(tfv.getTagHTML(condition, this.config.criterias));
                }else{
                    $fcontent.find(".bootstrap-tagsinput").append(tfv.getTagHTML(condition, this.config.criterias));
                }
                $e.trigger("conditionAdded", {filter: filter, condition: condition, timeExpended: ct(t)});
                $e.trigger("conditionChanged", {filter: filter});

            }else{
                $e.trigger("conditionAddFailed", rtn);
            }
            return rtn;
        },

        updateCondition: function($tab, cidx, condition){
            var $e = $(this.element),
                is_advSearch = $tab.hasClass("advanced-search-text"),
                filter = $tab.data("filter"),
                idx = $tab.index(),
                $fcontent = $('.filter-content', $e).eq(idx),
                $tag = $('.bootstrap-tagsinput span.tag', $fcontent).eq(cidx),
                field = $tag.attr("data-field"),
                c = this.config.criterias[field],
                cc = tf.getFilterClass(c.criteriaClass),
                txtVal = cc.getValText(condition.values, c.fieldParams),
                tagText = c.fieldName + ' ' + cc.getOpText(condition.op,tf.locale) + ' ' + txtVal,
                t = ct();

            //call Add Condition Callback
            var rtn = hitch(this.config.callBack, "updateFilterCondition")(filter, condition);

            if(rtn && rtn.result){

                if(is_advSearch){
                    this.config.advanceFilter.conditions[cidx] = condition;
                }else{
                    this.config.filters[idx-1].conditions[cidx] = condition;
                }
                //$tag.attr("data-cid", condition.id);
                $tag.attr("data-field", condition.field);
                $tag.attr("data-operator", condition.op);
                $tag.attr("data-value", txtVal);


                $tag.html(tagText + '<span data-role="remove"></span>');

                $e.trigger("conditionUpdated", {filter: filter, cidx: cidx, condition: condition, timeExpended: ct(t)});
                $e.trigger("conditionChanged", {filter: filter});
            }else{
                $e.trigger("conditionUpdateFailed", rtn);
            }
            return rtn;
        },

        deleteCondition: function($tab, cidx, condition){
            var $e = $(this.element),
                is_advSearch = $tab.hasClass("advanced-search-text"),
                filter = $tab.data("filter"),
                idx = $tab.index(),
                $fcontent = $('.filter-content', $e).eq(idx),
                $tag = $('.bootstrap-tagsinput span.tag', $fcontent).eq(cidx),
                field = $tag.attr("data-field"),
                t = ct();

            //call Add Condition Callback
            var rtn = hitch(this.config.callBack, "deleteFilterCondition")(filter, condition);

            if(rtn && rtn.result){

                if(is_advSearch){
                    this.config.advanceFilter.conditions.splice(cidx,1);
                }else{
                    this.config.filters[idx-1].conditions.splice(cidx,1);
                }

                $tag.remove();

                $e.trigger("conditionDeleted", {filter: filter, condition: condition, timeExpended: ct(t)});
                $e.trigger("conditionChanged", {filter: filter});
            }else{
                $e.trigger("conditionDeleteFailed", rtn);
            }
            return rtn;
        },

        saveFilter: function($tab, name){
            var $e = $(this.element),
                is_advSearch = $tab.hasClass("advanced-search-text"),
                filter = $tab.data("filter"),
                idx = $tab.index(),
                $fcontent = $('.filter-content', $e).eq(idx),
                f = $.extend({},is_advSearch?this.config.advanceFilter:this.config.filters[idx-1],{"name":name}),
                t = ct();

            //call AddFilter Callback
            var rtn = hitch(this.config.callBack, "addFilter")(f);
            if(rtn && rtn.result){
                //Add new Tab & Switch to it
                $('<li data-filter="' + name + '"><a>'+name+'</a><i class="icon-cancel"></i></li>').insertBefore(this.$advSearchTab);
                this.config.filters.push(f);
                $(tfv.getOneContentHTML(f, this.config)).insertBefore($(".adv-filter-content",$e));
                $e.trigger("filterAdded", {filter: name, timeExpended: ct(t)});
                this.switchTab($('li[data-filter="'+ name +'"]',$e));
                return rtn;
            }else{
                $e.trigger("filterAddedFailed", rtn);
            }
        }
    };



    $.fn.tagfilter = function (arg1, arg2) {
        var rtn = [];
        this.each(function () {
            var tagfilter = $(this).data('tagfilter');
            if (!tagfilter) {
                tagfilter = new TagFilter(this, arg1);
                $(this).data('tagfilter', tagfilter);
                rtn.push(tagfilter);
            } else if (!arg1 && !arg2) {
                rtn.push(tagfilter);
            } else if (tagfilter[arg1] !== undefined) {
                var retVal = tagfilter[arg1](arg2);
                if (retVal !== undefined)
                    rtn.push(retVal);
            }
        });

        if (typeof arg1 == 'string') {
            return rtn.length > 1 ? rtn : rtn[0];
        } else {
            return rtn;
        }
    };

    $.fn.tagfilter.Constructor = TagFilter;
})(jQuery);