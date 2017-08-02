/*(function($){
    ”use strict“;

    var defaultOptions = {
        customFilters: []
    };

    function TagFilter(element, options) {

    }

    TagFilter.prototype = {

    };

    $.fn.tagfilter = function(arg1, arg2){
        var rtn = [];
        this.each(function(){
            var tagfilter = $(this).data('tagfilter');
            if(!tagfilter){
                tagfilter = new TagFilter(this,arg1);
                $(this).data('tagfilter',tagfilter);
                rtn.push(tagfilter);
            } else if(!arg1 && !arg2){
                rtn.push(tagfilter);
            } else if(tagfilter[arg1] !== undefined){
                var retVal = tagfilter[arg1](arg2);
                if(retVal !== undefined)
                    rtn.push(retVal);
            }
        });

        if(typeof arg1 == 'string') {
            return rtn.length > 1 ? rtn: rtn[0];
        } else {
            return rtn;
        }
    };

    $.fn.tagfilter.Constructor = TagFilter;
})(jQuery);*/

(function($){
    $.fn.tagFilter = function(options){

        return this.each(function (index, self) {

            var config = $.extend({
                            dataFilter:[]
                            , condition:{
                                            name: {
                                                type: 'input', value: 'name', text: '员工姓名'
                                                , operator: [{value: 'like', text: '包含'}, {value: 'start', text: '开始于'}, {value: 'end', text: '结束于'}]
                                            }
                                        }
                            , activeClass: 'active'
                            , callBack: {
                                /**
                                  * 保存按钮执行的方法，返回 Object，包含以下返回值
                                  * @ self              ：点击元素的本身
                                  * @ activeElem        ：当前停留的 tag 标签
                                  * @ activeElemIndex   ：当前停留的 tag 标签的下标
                                  * @ isAdvancedSearch  ：是否是高级搜索 tag
                                  * @ initConElem       ：需删除的搜索条件容器
                                  * @ filterConElem     ：所有 tag 的搜索条件容器
                                  * @ refreshFilter     ：刷新结构
                                  * @ savaData          ：返回数据
                                  * @ text              ：返回条件名称
                                  * @ tagFilter         ：返回所在容器
                                  */ 
                                save: null
                                /**
                                  * 删除按钮执行的方法，返回 Object，包含以下返回值
                                  * @ self              ：点击元素的本身
                                  * @ li                ：点击元素父容器 li
                                  * @ sibLi             ：点击元素父容器同级 li
                                  * @ liIndex           ：点击元素父容器的下标
                                  * @ initConElem       ：需重置的搜索条件容器
                                  * @ filterConElem     ：所有 tag 的搜索条件容器
                                  * @ isVisible         ：是否含有 active 类
                                  * @ isAdvancedSearch  ：是否是高级搜索 tag
                                  * @ tagFilter         ：返回所在容器
                                  */
                                , tagRemove: null
                                , search: null
                                , editCondition: null
                            }
                        }, (options||{}) );
            var cdt = config.condition;
            var init = {
                getLiHtml: function(list){
                    return $.map(list, function(val, key){
                        return '<li><a>'+val.name+'</a><i class="icon-cancel"></i></li>';
                    }).join('\n');
                }
                , getTags: function(list){
                    return $.map(list, function(val, key){
                        return '<span data-field="' + val.field + '" data-operator="' + val.operator + '" data-value="'+val.value+'" class="tag label label-info">' + val.text + '<span data-role="remove"></span></span>';
                    }).join('\n');
                }
                , getConditionHtml: function(){
                    return $.map(cdt, function(val, key){
                        return '<option value="' + val.value + '">' + val.text + '</option>';
                    }).join('\n');
                }
                , getSaveDropdown: function(){
                    return '<div class="filter-save-dropdown">\
                                <div class="panel panel-default">\
                                  <div class="panel-heading">保存条件名称</div>\
                                  <div class="panel-body">\
                                    <input type="text" class="form-control" placeholder="保存条件名称" />\
                                    <button class="btn btn-primary btn-filter-save-ok" type="button">确认</button>\
                                    <button class="btn btn-default btn-filter-save-close" type="button">取消</button>\
                                  </div>\
                                </div>\
                            </div>';
                }
                , getFilterContentHtml: function(list){
                    return $.map(list, function(val, key){
                                return '<div class="filter-content">\
                                            <div class="row">\
                                                <div class="col-md-10 col-xs-12">\
                                                    <select class="form-control">' + init.getConditionHtml() + '</select>\
                                                </div>\
                                                <div class="col-md-2 col-xs-12">\
                                                    <div class="filter-text">\
                                                        <div class="inline-block">\
                                                            <a class="increase-condition"><u>增加条件</u></a>\
                                                        </div>\
                                                        <div class="inline-block">\
                                                            <a class="save-condition"><u>保存条件</u></a>' + init.getSaveDropdown() + '</div>\
                                                    </div>\
                                                </div>\
                                            </div>\
                                            <div class="bootstrap-tagsinput">' + init.getTags(val.content) + '</div>\
                                        </div>';
                    }).join('\n');
                }
                , getNavTabs: function(){
                    return '<ul class="nav nav-tabs">\
                                <li class="active"><a>搜索</a></li>' 
                                    + init.getLiHtml( config.dataFilter ) + 
                                '<li class="advanced-search-text"><a>高级搜索</a><i class="icon-cancel"></i></li>\
                            </ul>';
                }
                , getFilterContentWrap: function(){
                    return '<div class="filter-content-wrap">\
                                <div class="filter-content">\
                                    <div class="input-group">\
                                        <input type="text" class="form-control">\
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span>\
                                    </div>\
                                    <div class="filter-text text-left">\
                                        <a class="advanced-search"><u>高级搜索</u></a>\
                                    </div>\
                                </div>' 
                                    + init.getFilterContentHtml( config.dataFilter ) + 
                                '<div class="filter-content">\
                                    <div class="row">\
                                        <div class="col-md-10 col-xs-12">\
                                            <select class="form-control">' + init.getConditionHtml() + '</select>\
                                        </div>\
                                        <div class="col-md-2 col-xs-12">\
                                            <div class="filter-text">\
                                                <div class="inline-block">\
                                                    <a class="increase-condition"><u>增加条件</u></a>\
                                                </div>\
                                                <div class="inline-block">\
                                                    <a class="save-condition"><u>保存条件</u></a>' + init.getSaveDropdown() + '</div>\
                                            </div>\
                                        </div>\
                                    </div>\
                                    <div class="bootstrap-tagsinput"></div>\
                                </div>\
                            </div>';
                }
                , getEditFilterPane: function(){
                    return '<div class="filter-pane form-horizontal">\
                                        <h3 class="filter-header"></h3>\
                                        <div class="form-group row">\
                                            <div class="col-sm-10 filter-contents">\
                                                <div class="row">\
                                                    <div class="col-sm-2">\
                                                        <select class="form-control"></select>\
                                                    </div>\
                                                    <div class="col-sm-10">\
                                                        <input type="text" data-name="" placeholder="" class="form-control" />\
                                                    </div>\
                                                </div>\
                                            </div>\
                                        </div>\
                                        <div class="filter-footer">\
                                            <button type="button" class="btn btn-primary filter-ok">确认</button>\
                                            <button type="button" class="btn btn-default filter-close">取消</button>\
                                        </div>\
                                    </div>';
                }
                , getDefaultFilter: function(){
                    $tagFilter.html( init.getNavTabs() + '\n' + init.getFilterContentWrap() + '\n' + init.getEditFilterPane() );
                }
                , refreshFilter: function( list ){
                    var tagsFilter = init.getLiHtml( list );
                    $('.nav-tabs li:last', $tagFilter).before( tagsFilter );

                    var contentFilter = init.getFilterContentHtml( list );
                    $('.filter-content-wrap .filter-content:last', $tagFilter).before( contentFilter );
                }
                , $refreshTagSpan: null
                , $refreshTagInput: null
                , refreshTags: function(data){
                    var html = data.html + '<span data-role="remove"></span>';
                    if($refreshTagSpan.length == 0){
                        var listStr = '<span class="tag label label-info" data-field="' + data.field + '" data-operator="' + data.operator + '" data-value="'+data.value+'">' + html + '</span>';
                        $refreshTagInput.append(listStr).show();
                    }else{
                        $refreshTagSpan.attr({'data-field': data.field, 'data-operator': data.operator, 'data-value': data.value }).html( html );
                    }
                }
            }

            var $tagFilter = $(self);

            //if(config.dataFilter.length == 0) return false;
            init.getDefaultFilter();
            
            //添加条件模块
            var $pane = $('.filter-pane', $tagFilter)
                //添加条件标题
                , $paneHeader = $('.filter-header', $tagFilter)
                //添加条件内容
                , $paneContents = $('.filter-contents', $tagFilter)
                //添加条件确认按钮
                , $paneOk = $('.filter-ok', $tagFilter)
                //添加条件取消按钮
                , $paneClose = $('.filter-close', $tagFilter)
                //搜索条件 tag 内容
                , $filterContent = $('.filter-content', $tagFilter)
                //高级搜索 tag
                , $advancedSearchText = $('.advanced-search-text', $tagFilter);

            var active = config.activeClass;

            var tf = {
                //获取 option 结构
                getOption: function(value, list){
                    return $.map(list, function(val, key){
                        var selected = (value == key);
                        return '<option value="' + key + '"'+ ( selected ? ' selected' : '' ) +'>' + val + '</option>';
                    }).join('\n');
                }
                //获取增加条件结构
                , getFilterContents: function(data){
                    var str = '', sType = cdt[data.field].type || 'input';
                    if(!cdt[data.field].type){
                        cdt[data.field].type = 'input';
                    }
                    if(cdt[data.field].html){
                        str = cdt[data.field].html;
                    }else if(sType == 'date'){
                        str = '<div class="input-group date">\
                                    <input type="text" id="filter-date" placeholder="' + data.name + '" class="form-control" value="' + data.value + '" />\
                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>\
                                </div>';
                    }else{
                        str = '<input type="text" placeholder="' + data.name + '" class="form-control" value="' + data.value + '" />';
                    }
                    return '<div class="row">\
                                <div class="col-sm-2">\
                                    <select class="form-control">' + tf.getOption(data.operator, data.optionlist) + '</select>\
                                </div>\
                                <div class="col-sm-10">' + str + '</div>\
                            </div>';
                }
                //获取 option 的 value 和 text
                , getOptionValue: function(option){
                    return {val: option.attr('value'), text: option.html()};
                }
                //重置搜索面板
                , initPane: function(option){
                    var _optionlist = {};
                    $.each(cdt[option.val[0]].operator, function(index, self){
                        _optionlist[self.value] = self.text;
                    });
                    $paneHeader.html( option.html + ' ' + (_optionlist[option.operator] || '') + ' ' + option.val[1] );
                    $paneOk.attr('data-field', option.val[0]);
                    $paneContents.html( tf.getFilterContents({
                            name:option.html
                            , field:option.val[0]
                            , value:option.val[1]
                            , operator: option.operator
                            , optionlist: _optionlist
                        }) );
                    $paneContents.find('.form-control:eq(1)').attr('data-name', option.html);
                    $pane.show();
                    config.callBack.editCondition && $.isFunction(config.callBack.editCondition) && config.callBack.editCondition(
                        {
                            type: cdt[option.val[0]].type
                            , pane: $pane
                            , self: option.self
                            , tagFilter: $tagFilter
                        }
                    );
                }
                , getSearchOpt: function(){
                    var label = $('.label-info', $('.bootstrap-tagsinput', $('.filter-content:visible', $tagFilter)));
                    var obj = {};                    
                    $.each(label, function(index, self){
                        if(!obj[index]){
                            obj[index] = {};
                        }
                        obj[index].field = $(self).attr('data-field');
                        obj[index].operator = $(self).attr('data-operator');
                        obj[index].value = $(self).attr('data-value');
                        obj[index].text = $(self).text();
                    });
                    return obj;
                }
                , saveDropdownHide: function(){
                    $('.filter-save-dropdown', $tagFilter).hide();
                }
            };
            $tagFilter.undelegate();
            //tab切换
            $tagFilter.delegate('li', 'click', function(e){
                var _self = $(this)
                    , _index = _self.index()
                    , $filterContent = $('.filter-content', $tagFilter);
                $pane.hide();
                if(e.target.tagName == 'I'){
                    var I = $('i', _self);
                    config.callBack.tagRemove && $.isFunction(config.callBack.tagRemove) && config.callBack.tagRemove(
                        {
                            self: I
                            , li: _self
                            , sibLi: _self.siblings()
                            , liIndex: _index
                            , initConElem: $filterContent.eq(_index)
                            , filterConElem: $filterContent
                            , isVisible: _self.hasClass(active)
                            , isAdvancedSearch: _self.hasClass('advanced-search-text')
                            , tagFilter: $tagFilter
                        }
                    );
                }else{
                    $filterContent.hide();
                    $filterContent.eq(_index).show();
                    _self.addClass(active).siblings().removeClass(active);
                    config.callBack.search && $.isFunction(config.callBack.search) && config.callBack.search(
                        {
                            type: 'tagChange'
                            , data: tf.getSearchOpt(  )
                            , refreshDate: tf.getSearchOpt
                            , tagFilter: $tagFilter
                        }
                    );
                }
                tf.saveDropdownHide();
            });
            //高级搜索
            $tagFilter.delegate('.advanced-search', 'click', function(){
                $filterContent.hide().last().show();
                $advancedSearchText.show();
                $('li', $tagFilter).removeClass(active).last().addClass(active);
            });
            //增加条件
            //var curOptVal = '';
            $tagFilter.delegate('.increase-condition', 'click', function(){
                var option = tf.getOptionValue( $(this).closest('.row').find('select.form-control option:checked') )
                    , optionVal = option.val
                    , optionHtml = option.text;
                if(!optionVal) return false;
                //curOptVal = optionVal;

                tf.initPane( { html: optionHtml, val: [optionVal, ''], operator:'', self: $(this) } );
                tf.saveDropdownHide();
            });
            //保存条件
            $tagFilter.delegate('.save-condition', 'click', function(){
                var curLi = $('li.' + active, $tagFilter);
                $(this).closest('div').find('.form-control').val( (curLi.hasClass('advanced-search-text') ? '' : curLi.text()) );
                $(this).closest('div').find('.filter-save-dropdown').show();
                $pane.hide();
                /*if(curLi.hasClass('advanced-search-text')){
                    $('.filter-content:last .bootstrap-tagsinput', $tagFilter).html('');
                }*/
            });
            //保存条件层提交
            $tagFilter.delegate('.btn-filter-save-ok', 'click', function(){
                var curLi = $('li.' + active, $tagFilter)
                    , _index = curLi.index()
                    , $filterContent = $('.filter-content', $tagFilter)
                    , _self = $(this);

                var obj = {};
                obj.name = _self.prev().val();
                obj.content = [];

                $.each(tf.getSearchOpt(), function(i, j){
                    obj.content.push(j);
                });

                config.callBack.save && $.isFunction(config.callBack.save) && config.callBack.save(
                    {
                        self: _self
                        , activeElem: curLi
                        , activeElemIndex: _index
                        , isAdvancedSearch: curLi.hasClass('advanced-search-text')
                        , initConElem: $filterContent.eq(_index)
                        , filterConElem: $filterContent
                        , refreshFilter: init.refreshFilter
                        , savaData: [obj]
                        , text: obj.name
                        , tagFilter: $tagFilter
                    }
                );
            });
            //保存条件层关闭
            $tagFilter.delegate('.btn-filter-save-close', 'click', function(){
                $(this).closest('.filter-save-dropdown').hide();
            });
            $tagFilter.delegate('.filter-ok', 'click', function(){
                var self = $(this)
                    , dataField = self.attr('data-field')
                    , $pane = self.closest('.filter-pane')
                    , option = tf.getOptionValue( $pane.find('.form-control:eq(0) option:checked') )
                    , optionVal = option.val
                    , optionHtml = option.text
                    , $input = $pane.find('.form-control:eq(1)')
                    , dataName = $input.attr('data-name')
                    , val = $input.val()
                    , inputVal = $input[0].tagName == 'SELECT' ? $('option:checked', $input).html() : $input.val()
                    , $tagsInput = $('.bootstrap-tagsinput', $('.filter-content:visible', $tagFilter))
                    , $span = $tagsInput.find('>span[data-field='+dataField+']')
                    , html = dataName + ' ' + optionHtml + ' ' + inputVal + '<span data-role="remove"></span>';
                
                $refreshTagSpan = $span;
                $refreshTagInput = $tagsInput;
                if(cdt[dataField].type == 'input' || cdt[dataField].type == 'select' || cdt[dataField].type == 'date'){
                    init.refreshTags(
                        {
                            field: dataField//curOptVal
                            , operator: optionVal
                            , value: val
                            , html: dataName + ' ' + optionHtml + ' ' + inputVal
                        }
                    );
                    $pane.hide();
                }
                config.callBack.search && $.isFunction(config.callBack.search) && config.callBack.search(
                    {
                        type: 'cdtEdit'
                        , self: self
                        , data: tf.getSearchOpt(  )
                        , refreshDate: tf.getSearchOpt
                        , pane: $pane
                        , fieldType: cdt[dataField].type
                        , refreshTags: init.refreshTags
                        , tagFilter: $tagFilter
                    }
                );
            });
            $tagFilter.delegate('.filter-close', 'click', function(){
                $pane.hide();
            });
            //搜索的条件
            $tagFilter.delegate('.label-info', 'click', function(e){
                if(e.target.getAttribute('data-role') == 'remove'){
                    $(e.target).parent().remove();
                    ($(e.target).parent().attr('data-field') == $paneOk.attr('data-field')) && $pane.hide();

                    config.callBack.search && $.isFunction(config.callBack.search) && config.callBack.search(
                        {
                            type: 'cdtRemove'
                            , data: tf.getSearchOpt(  )
                            , refreshDate: tf.getSearchOpt
                            , tagFilter: $tagFilter
                        }
                    );
                }else{
                    var self = $(this)
                        , dataField = self.attr('data-field')
                        , text = self.text().split(' ')
                        , value = self.attr('data-operator');//self.attr('data-msg').split('-')[1];

                    tf.initPane( { html: text[0], val: [dataField, text[2]], operator:value, self: self } );
                    //curOptVal = dataField;
                }
                tf.saveDropdownHide();
            });
            //默认搜索
            $tagFilter.delegate('.input-group-addon', 'click', function(){
                var self = $(this);
                config.callBack.search && $.isFunction(config.callBack.search) && config.callBack.search(
                    {
                        type: 'default'
                        , tagFilter: $tagFilter
                        , self: self
                        , text: self.prev().val()
                    }
                );
            });
    
            //each end
        });
        
    }

})(jQuery);