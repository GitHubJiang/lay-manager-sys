/*****************************************************/
/***************1. 基本信息的设定***********************/
/*****************************************************/

wms.addResource("zh-CN", {
    'info': '提示信息',
    'edit-f': '编辑失败',
    'regerr': '编码由 长度为2~50个字符的英文字母，数字或_.- 组成',
    'select-rm-delete': '请选择需要删除的组织',
    'select-rm-banned': '请选择需要禁用的组织',
    'select-rm': '请选择需要启用的组织',
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
	wms.asyncPost(pagebase+"/uac/opUnit/tree", {},{successHandler:function(data, textStatus){
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
						var _item = $('#tree-container').treeview('getNode',_nodeId);
						this.data('startVisible', _item.lifecycle == 0).data('stopVisible', _item.lifecycle == 1);
					}
				}
			}
			,items: {
				"parent": {name: "新增一级组织", icon: "add",callback:function(key, options){
					var form = $(".add-model-form form");
					form[0].reset();
					
					queryTypeOnly(null);
					
					setTimeout(function() {
						$(".add-model-form input[name='id']").removeAttr("value");
						$(".add-model-form select[name='parentOuId']").multipleSelect('setSelects', [""]);
						$(".add-model-form select[name='parentOuId']").closest(".form-group").hide();
						$(".form-title").html("新增一级组织");
						$('.add-model-form').modal('show');
					}, 500 );
					
						
				}}
	            ,"add": {name: "新增", icon: "add",callback:function(key, options){
	            	var _nodeId = Number(options.$trigger.attr("data-nodeid"));
	            	var _item = $('#tree-container').treeview('getNode',_nodeId);
					var form = $(".add-model-form form");
					form[0].reset();
					//根据组织id 获取组织对象，获取到组织的组织类型id
					wms.asyncPost(pagebase+"/uac/opUnit/get", {id:_item.id},{successHandler:function(data, textStatus){
						if(data){
							queryTypeOnly(data["ouTypeId"]);
							//queryParent(_item.id);
							//生成同级的副组织
							queryParentOnly(data["parentOuId"],_item.id);
							$(".form-title").html("新增组织");
							$(".add-model-form input[name='id']").removeAttr("value");
							$('.add-model-form').modal('show');
							
						} else {
							wms.frame.notifyError(i18n.t("info"),i18n.t("edit-err"));
						}
					}});
			
				},visible:function(key, opt) {
					return opt.selector != ".widget-content";
				}}
	            ,"edit": {name: "编辑", icon: "edit",callback:function(key, options){
	            	
	            	var _nodeId = Number(options.$trigger.attr("data-nodeid"));
					var _item = $('#tree-container').treeview('getNode',_nodeId);
					$(".form-title").html("修改组织");
					
					wms.asyncPost(pagebase+"/uac/opUnit/get", {id:_item.id},{successHandler:function(data, textStatus){
						if(data){
							var form = $(".add-model-form form");
							form[0].reset();
							form.fill(data);
							var ouTypeId = data["ouTypeId"];
							//queryType(data["ouTypeId"]);
							//1.根据组织的组织类型 查询 组织类型的父节点
							//2.父节点生成所有子节点的下拉选项 
							wms.asyncPost(pagebase+"/uac/opTypeUnit/get", {id:ouTypeId},{successHandler:function(data, textStatus){
								if(data){
									queryTypeOnly(data["parentOutId"]);
								} else {
									wms.frame.notifyError(i18n.t("info"),i18n.t("edit-err"));
								}
							}});
							
							queryParent(data["parentOuId"]);
							setTimeout(function() {
								$(".add-model-form select[name='parentOuId']").closest(".form-group").hide();
								$(".add-model-form select[name='ouTypeId']").val(ouTypeId);
								$(".add-model-form select[name='ouTypeId']").multipleSelect('refresh');
								$('.add-model-form').modal('show');
							}, 500 );
							
						} else {
							wms.frame.notifyError(i18n.t("info"),i18n.t("edit-err"));
						}
					}});
				},visible:function(key, opt) {
					return opt.selector != ".widget-content";
				}}
	            ,"stop": {name: "停用", icon: "quit",visible: function(key, options){
					return this.data('stopVisible');
				},callback:function(key, options){
					var _nodeId = Number(options.$trigger.attr("data-nodeid"));
					var _item = $('#tree-container').treeview('getNode',_nodeId);
					wms.asyncPost(pagebase+"/uac/opUnit/update", {ids:_item["id"],lifecycle:0},{successHandler:function(data, textStatus){
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
				}}
				,"start": {name: "启用", icon: "paste",visible: function(key, options){
					return this.data('startVisible');
				},callback:function(key, options){
					var _nodeId = Number(options.$trigger.attr("data-nodeid"));
					var _item = $('#tree-container').treeview('getNode',_nodeId);
					wms.asyncPost(pagebase+"/uac/opUnit/update", {ids:_item["id"],lifecycle:1},{successHandler:function(data, textStatus){
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
				}}
	        }
		});
	}});
}
//父组织change
$(".add-model-form select[name='parentOuId']").on('change',function() {
	var parentOuId = $(".add-model-form select[name='parentOuId']").val();
	if(parentOuId=='' || parentOuId==null) {
		queryTypeOnly();
		return;
	}
	wms.asyncPost(pagebase+"/uac/opUnit/get", {id:parentOuId},{successHandler:function(data, textStatus){
		if(data){
			queryTypeOnly(data["ouTypeId"]);
		} else {
			wms.frame.notifyError(i18n.t("info"),i18n.t("edit-err"));
		}
	}})

})
function queryType(ouTypeId) {
	ouTypeId = ouTypeId || "";
	wms.asyncPost(pagebase+"/uac/opTypeUnit/tree", {},{successHandler:function(data, textStatus){
		if(data) {
			html = spliceOpTypeUnit(data);
			$(".add-model-form select[name='ouTypeId']").html(html);
			$(".add-model-form select[name='ouTypeId']").val(ouTypeId);
			$(".add-model-form select[name='ouTypeId']").multipleSelect('refresh');
		}
	}});
}

function queryParent(parentOuId) {
	parentOuId = parentOuId || "";
	wms.asyncPost(pagebase+"/uac/opUnit/tree", {},{successHandler:function(data, textStatus){
		if(data) {
			html = "<option value=''>请选择</option>";
			html = spliceOpTypeUnit(data,html,0);
			$(".add-model-form select[name='parentOuId']").html(html);
			$(".add-model-form select[name='parentOuId']").val(parentOuId);
			$(".add-model-form select[name='parentOuId']").closest(".form-group").show();
			$(".add-model-form select[name='parentOuId']").multipleSelect('refresh');
		}
	}});
}
function queryParentOnly(parentOuId,id) {
	parentOuId = parentOuId || "";
	wms.asyncPost(pagebase+"/uac/opUnit/tree", {},{successHandler:function(data, textStatus){
		if(data) {
			html = "<option value=''>请选择</option>";
			html = spliceOpTypeUnitOnly(data,html,parentOuId);
			$(".add-model-form select[name='parentOuId']").html(html);
			$(".add-model-form select[name='parentOuId']").val(id);
			$(".add-model-form select[name='parentOuId']").closest(".form-group").show();
			$(".add-model-form select[name='parentOuId']").multipleSelect('refresh');
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
//参数为组织类型id，生成此组织下的同级子节点，若参数为空，则生成所有父级组织类型
function queryTypeOnly(ouTypeId) {
	ouTypeId = ouTypeId || "";
	wms.asyncPost(pagebase+"/uac/opTypeUnit/tree", {},{successHandler:function(data, textStatus){
		if(data) {
			html = "<option value=''>请选择</option>";
			html = spliceOpTypeUnitOnly(data,html,ouTypeId);
			$(".add-model-form select[name='ouTypeId']").html(html);
			$(".add-model-form select[name='ouTypeId']").val(ouTypeId);
			$(".add-model-form select[name='ouTypeId']").multipleSelect('refresh');
		}
	}});
}

function spliceOpTypeUnitOnly(array,html,ouTypeId) {
	ouTypeId = ouTypeId || 0;
	html = html || "";
	for(var i = 0;i< array.length;i++) {
		var _item = array[i];
		var _nodes = _item["nodes"];
		
		if(ouTypeId==0) {
			html += "<option value='"+_item["id"]+"'>" + _item["text"] + "</option>";			
		}
		
		if(_item["id"]==ouTypeId && ouTypeId != 0) {
			if(_nodes) {
				for(var _i=0;_i<_nodes.length;_i++) {
					html += "<option value='"+_nodes[_i]["id"]+"'>";
					html += _nodes[_i]["text"]+"</option>";
				}
				return html;
			}
		}
		if(_nodes) {
			if(ouTypeId != 0) {
				htmls = spliceOpTypeUnitOnly(_nodes,html,ouTypeId);
				if(html != htmls) {
					return htmls;
				}
			}
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
			window.location = pagebase+"/uac/opUnit?q_string_appkey="+key;
		}
		,items: $appMenu
	});

    $(".closeBtn").on("click", function() {
        $(this).closest(".modal").modal('hide');
    });
    $(".subBtn").on("click", function() {
    	
    	var ouTypeId = $('#ouTypeId').val();
    	if(ouTypeId==null || ouTypeId=='') {
    		wms.frame.notifyError(i18n.t("提示"),i18n.t("组织类型必填！"));
    	}
    	
        var form = $(this).closest("form");
        if (!form.validator().data('bs.validator').validate().isIncomplete()) {
            var data = form.serializeArray();
            wms.asyncPost(pagebase + "/uac/opUnit/add", data, {
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