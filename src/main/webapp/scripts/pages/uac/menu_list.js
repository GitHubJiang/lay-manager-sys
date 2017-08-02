/*****************************************************/
/***************1. 基本信息的设定***********************/
/*****************************************************/

wms.addResource("zh-CN", {
    'info': '提示信息',
    'edit-f': '编辑失败',
    'regerr': '编码由 长度为2~50个字符的英文字母，数字或_.- 组成',
    'select-rm-delete': '请选择需要删除的菜单',
    'select-rm-banned': '请选择需要禁用的菜单',
    'select-rm': '请选择需要启用的菜单',
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
	wms.asyncPost(pagebase+"/uac/menu/tree", {},{successHandler:function(data, textStatus){
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
					$(".add-model-form select[name='parentId']").closest(".form-group").show();
					$(".add-model-form select[name='url']").closest(".form-group").show();
					$(".add-model-form select[name='acl']").closest(".form-group").show();
				}
			}
			,items: {
				"parent": {name: "新增一级菜单", icon: "add",callback:function(key, options){
					var form = $(".add-model-form form");
					form[0].reset();
					queryPrivilege();
					$(".add-model-form input[name='id']").removeAttr("value");
					$(".add-model-form select[name='parentId']").multipleSelect('setSelects', [""]);
					$(".add-model-form select[name='parentId']").closest(".form-group").hide();
					$(".add-model-form select[name='url']").multipleSelect('setSelects',[]);
					$(".add-model-form select[name='url']").closest(".form-group").hide();
					$(".add-model-form select[name='acl']").closest(".form-group").hide();
					$(".form-title").html("新增一级菜单");
					$('.add-model-form').modal('show');
				}}
	            ,"add": {name: "新增", icon: "add",callback:function(key, options){
	            	var _nodeId = Number(options.$trigger.attr("data-nodeid"));
	            	var _item = $('#tree-container').treeview('getNode',_nodeId);
					var form = $(".add-model-form form");
					form[0].reset();
					queryPrivilege();
					//queryParent(_item.id);
					//新增显示同级父级菜单
					wms.asyncPost(pagebase+"/uac/menu/get", {id:_item.id},{successHandler:function(data, textStatus){
						if(data){
							queryParentOnly(data["parentId"],_item.id);
						} else {
							wms.frame.notifyError(i18n.t("info"),i18n.t("edit-err"));
						}
					}});
					
					setTimeout(function() {
						$(".form-title").html("新增菜单");
						$(".add-model-form select[name='url']").multipleSelect('setSelects',[]);
						$(".add-model-form input[name='id']").removeAttr("value");
						$('.add-model-form').modal('show');},500);
				},visible:function(key, opt) {
					return opt.selector != ".widget-content";
				}}
	            ,"edit": {name: "编辑", icon: "edit",callback:function(key, options){
	            	var _nodeId = Number(options.$trigger.attr("data-nodeid"));
					var _item = $('#tree-container').treeview('getNode',_nodeId);
					$(".form-title").html("修改菜单");
					
					wms.asyncPost(pagebase+"/uac/menu/get", {id:_item.id},{successHandler:function(data, textStatus){
						if(data){
							var form = $(".add-model-form form");
							form[0].reset();
							form.fill(data);
							queryPrivilege(data["acl"]);
							queryParent(data["parentId"]);
							createUrl(data["acl"]);
							setTimeout(function() {
								$(".add-model-form select[name='url']").multipleSelect('setSelects',[data["url"]]);
								$(".add-model-form select[name='parentId']").closest(".form-group").hide();
								if(data["parentId"]=='' || typeof(data["parentId"])=='undefined') {
									$(".add-model-form select[name='url']").closest(".form-group").hide();
									$(".add-model-form select[name='acl']").closest(".form-group").hide();
								}
								$('.add-model-form').modal('show');
							},500);
						} else {
							wms.frame.notifyError(i18n.t("info"),i18n.t("edit-err"));
						}
					}});
				},visible:function(key, opt) {
					return opt.selector != ".widget-content";
				}}
	            ,"delete": {name: "删除", icon: "delete",callback:function(key, options){
	            	var _nodeId = Number(options.$trigger.attr("data-nodeid"));
					var _item = $('#tree-container').treeview('getNode',_nodeId);
					wms.asyncPost(pagebase+"/uac/menu/delete", {ids:_item["id"]},{successHandler:function(data, textStatus){
						if(data){
							if(data.isSuccess) {
								wms.frame.notifySuccess(data["description"]);
	                            setTimeout('eval(location = location)',1000);
							} else {
								wms.frame.notifyError(i18n.t("code-info"),data["description"]);
							}
						} else {
							wms.frame.notifyError(i18n.t("code-info"),i18n.t("error"));
						}
					}});
				},visible:function(key, opt) {
					return opt.selector != ".widget-content";
				}}
	        }
		});
	}});
}
function queryPrivilege(acl) {
	acl = acl || "";
	wms.asyncPost(pagebase+"/uac/privilege/list", {},{successHandler:function(data, textStatus){
		if(data) {
			html = "<option value=''>请选择</option>";
			for(var i = 0;i< data.length;i++) {
				var _item = data[i];
				html += "<option value='"+_item["acl"]+"'>" + _item["name"]+"("+_item["acl"]+")"+"</option>";
			}
			$(".add-model-form select[name='acl']").html(html);
			$(".add-model-form select[name='acl']").val(acl);
			$(".add-model-form select[name='acl']").multipleSelect('refresh');
		}
	}});
}

function queryParent(parentId) {
	parentId = parentId || "";
	wms.asyncPost(pagebase+"/uac/menu/tree", {},{successHandler:function(data, textStatus){
		if(data) {
			var html = "<option value=''>请选择</option>";
			html += spliceOptions(data);
			$(".add-model-form select[name='parentId']").html(html);
			$(".add-model-form select[name='parentId']").val(parentId);
			$(".add-model-form select[name='parentId']").multipleSelect('refresh');
		}
	}});
}
function queryParentOnly(parentId,id) {
	parentId = parentId || "";
	wms.asyncPost(pagebase+"/uac/menu/tree", {},{successHandler:function(data, textStatus){
		if(data) {
			var html = "<option value=''>请选择</option>";
			html = spliceOptionsOnly(data,html,parentId);
			$(".add-model-form select[name='parentId']").html(html);
			$(".add-model-form select[name='parentId']").val(id);
			$(".add-model-form select[name='parentId']").closest(".form-group").show();
			$(".add-model-form select[name='parentId']").multipleSelect('refresh');
		}
	}});
}

function spliceOptions(array,html,index) {
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
			html += spliceOptions(_item["nodes"], "", index + 1);
		}
	}
	return html;
}
function spliceOptionsOnly(array,html,parentId) {
	parentId = parentId || 0;
	html = html || "";
	for(var i = 0;i< array.length;i++) {
		var _item = array[i];
		var _nodes = _item["nodes"];
		
		if(parentId==0) {
			html += "<option value='"+_item["id"]+"'>" + _item["text"] + "</option>";			
		}
		
		if(_item["id"]==parentId && parentId != 0) {
			if(_nodes) {
				for(var _i=0;_i<_nodes.length;_i++) {
					html += "<option value='"+_nodes[_i]["id"]+"'>";
					html += _nodes[_i]["text"]+"</option>";
				}
				return html;
			}
		}
		if(_nodes) {
			if(parentId != 0) {
				htmls = spliceOptionsOnly(_nodes,html,parentId);
				if(html != htmls) {
					return htmls;
				}
			}
		}
	}
	return html;
}
//acl 级联url
function createUrl(acl) {
	wms.asyncPost(pagebase+"/uac/url/queryUrlByAcl", {acl:acl},{successHandler:function(data, textStatus){
		if(data!=null) {
			var html = "<option value=''>请选择</option>";
			for(var i = 0;i< data.length;i++) {
				var _item = data[i];
				html += "<option value='"+_item["url"]+"'>";
				html += _item["url"]+"</option>";
			}
			
			$("#url_select").html(html);
			$("#url_select").multipleSelect('refresh');
		}
	}});
}

//acl change事件  级联
function aclChange() {
	var acl = $('#acl_select').val();
	if(acl == null) return;
	createUrl(acl);
}

wms.addReadyFunc(function() {
    initTree();
	
	$.contextMenu({
		selector: '#a-app-select'
		,trigger: 'left'
		,callback:function(key, options){
			window.location = pagebase+"/uac/menu?q_string_appkey="+key;
		}
		,items: $appMenu
	});


    $(".closeBtn").on("click", function() {
        $(this).closest(".modal").modal('hide');
    });
    $(".subBtn").on("click", function() {
    	var form = $(this).closest("form");
        if (!form.validator().data('bs.validator').validate().isIncomplete()) {
        	form.find("input[name='appId']").val($appId);
            var data = form.serializeArray();
            wms.asyncPost(pagebase + "/uac/menu/add", data, {
                successHandler: function(data, textStatus) {
                    if (data) {
                        if (data.isSuccess) {
                            wms.frame.notifySuccess(data["description"]);
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