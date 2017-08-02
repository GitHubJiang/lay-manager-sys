/*****************************************************/
/***************1. 基本信息的设定***********************/
/*****************************************************/

wms.addResource("zh-CN", {
    'info': '提示信息',
    'edit-f': '编辑失败',
    'regerr': '编码由 长度为2~50个字符的英文字母，数字或_.- 组成',
    'select-rm-delete': '请选择需要删除的组织类型',
    'select-rm-banned': '请选择需要禁用的组织类型',
    'select-rm': '请选择需要启用的组织类型',
    'ms': '删除',
    'error': '系统错误异常',
    'type-start': '有效',
    'type-block': '失效',
    'error': '操作失败'
});

/*****************************************************/
/***************2. 页面加载时设定***********************/
/*****************************************************/

function initTree() {
	wms.asyncPost(pagebase+"/uac/opTypeUnit/tree", {},{successHandler:function(data, textStatus){
		if(data){
			$('#tree-container').treeview({
		        data: prepareTreeStyle(data),
		        levels: 99,
		        nodeIcon: 'icon-layers',
		        showBorder: false,
		        showTags: true,
		    });
		}
		
		$.contextMenu({
			selector: $(".list-group-item").length == 0?".widget-content":".list-group-item"
			,trigger: 'right'
			,events:{
				show:function(options){
					if(options.$trigger.attr("data-nodeid")) {
						var _nodeId = Number(options.$trigger.attr("data-nodeid"));
						$('#tree-container').treeview('selectNode', [ _nodeId, { silent: true } ]);
					}
				}
			}
			,items: {
				"parent": {name: "新增一级组织类型", icon: "add",callback:function(key, options){
					var form = $(".add-model-form form");
					form[0].reset();
					$(".add-model-form input[name='id']").removeAttr("value");
					$(".add-model-form select[name='parentOutId']").multipleSelect('setSelects', [""]);
					$(".add-model-form select[name='parentOutId']").closest(".form-group").hide();
					$(".form-title").html("新增一级组织类型");
					$('.add-model-form').modal('show');
				}}
	            ,"add": {name: "新增", icon: "add",callback:function(key, options){
	            	var _nodeId = Number(options.$trigger.attr("data-nodeid"));
					var _item = $('#tree-container').treeview('getNode',_nodeId);
					queryType(_item.id);
					var form = $(".add-model-form form");
					form[0].reset();
					$(".add-model-form input[name='id']").removeAttr("value");
					$(".form-title").html("新增组织类型");
					$('.add-model-form').modal('show');
				},visible:function(key, opt) {
					return opt.selector != ".widget-content";
				}}
	            ,"edit": {name: "编辑", icon: "edit",callback:function(key, options){
	            	var _nodeId = Number(options.$trigger.attr("data-nodeid"));
					var _item = $('#tree-container').treeview('getNode',_nodeId);
					$(".form-title").html("修改组织类型");
					
					wms.asyncPost(pagebase+"/uac/opTypeUnit/get", {id:_item.id},{successHandler:function(data, textStatus){
						if(data){
							var form = $(".add-model-form form");
							form[0].reset();
							form.fill(data);
							queryType(data["parentOutId"]);
							setTimeout(function() {
								$(".add-model-form select[name='parentOutId']").closest(".form-group").hide();
								$('.add-model-form').modal('show');
							}, 500 );
						} else {
							wms.frame.notifyError(i18n.t("info"),i18n.t("edit-err"));
						}
					}});
				},visible:function(key, opt) {
					return opt.selector != ".widget-content";
				}}
	        }
		});
	}});
}
function queryType(parentOutId) {
	parentOutId = parentOutId || "";
	wms.asyncPost(pagebase+"/uac/opTypeUnit/tree", {},{successHandler:function(data, textStatus){
		if(data) {
			html = "<option value=''>请选择</option>";
			html = spliceOpTypeUnit(data,html,0);
			$(".add-model-form select[name='parentOutId']").html(html);
			$(".add-model-form select[name='parentOutId']").closest(".form-group").show();
			$(".add-model-form select[name='parentOutId']").val(parentOutId);
			$(".add-model-form select[name='parentOutId']").multipleSelect('refresh');
		}
	}});
}
function spliceOpTypeUnit(array,html,index) {
	index = index || 0;
	html = html || "";
	for(var i = 0;i< array.length;i++) {
		var _item = array[i];
		html += "<option value='"+_item["id"]+"'>";
		for(var _i=0;_i<index;_i++) {
			html +="&nbsp;&nbsp;&nbsp;&nbsp;";
		}
		html += _item["text"]+"</option>";
		if(_item["nodes"]) {
			html += spliceOpTypeUnit(_item["nodes"], "", index + 1);
		}
	}
	return html;
}
wms.addReadyFunc(function() {
    initTree();
	
	$.contextMenu({
		selector: '#a-app-select'
		,trigger: 'left'
		,callback:function(key, options){
			window.location = pagebase+"/uac/opTypeUnit?q_string_appkey="+key;
		}
		,items: $appMenu
	});
	
    $(".closeBtn").on("click", function() {
        $(this).closest(".modal").modal('hide');
    });
    $(".subBtn").on("click", function() {
        var form = $(this).closest("form");
        if (!form.validator().data('bs.validator').validate().isIncomplete()) {
            var data = form.serializeArray();
            wms.asyncPost(pagebase + "/uac/opTypeUnit/add", data, {
                successHandler: function(data, textStatus) {
                    if (data) {
                        if (data.isSuccess) {
                        	wms.frame.notifySuccess(i18n.t("info"), data["description"]);
    						setTimeout('eval(location = location)',1000);
                        } else {
                            wms.frame.notifyError(i18n.t("info"), data["description"]);
                        }
                    } else {
                        wms.frame.notifyError(i18n.t("info"), i18n.t("edit-f"));
                    }
                }
            });
        }
    });

    // modal 居中

    function centerModal() {
        $(this).css('display', 'block');
        var $dialog = $(this).find(".modal-dialog");
        var offset = ($(window).height() - $dialog.height()) / 2;
        $dialog.css("margin-top", offset);
    }

    $('.modal').on('show.bs.modal', centerModal);
    $(window).on("resize", function() {
        $('.modal:visible').each(centerModal);
    });

});
(function(className){
	setTimeout(function(){
		$(className).multipleSelect({
			filter: true,
			selectAll: false,
			single: true,
			isShowButton: false,
			multipleWidth: 55,
			noMatchesFound: "没有找到匹配项",
			width: "100%"
		});
	},1);
})(".ms-select");