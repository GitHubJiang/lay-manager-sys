/*****************************************************/
/***************1. 基本信息的设定***********************/
/** ************************************************** */
wms.addResource("zh-CN", {
	"empty_selected" : "请勾选需要删除的行",
	"mapping_reset" : "重推"
});

var tableExtraBatch = [
	{
		name : "重推",
		icon : "fa fa-cloud-upload",
		action : function(checked) {
			if (checked) {
				var rids = new Array()
				for (var i = 0; i < checked.length; i++) {
					rids[i] = $(checked[i]).data("itemid");
				}
				
				if (rids.length == 0) {
					wms.frame.notifyInfo(i18n.t("empty_selected"));
					return;
				}
				
				resetMappings(rids);
			}
		}
	} 
];

/** ************************************************** */
/** *************2. 页面加载时设定********************** */
/** ************************************************** */
$(function(){
	 $("#select1").multipleSelect({
		 name: "你好",
		isOpen: false,
		placeholder: "请输入",
		filter: true,
		multipleWidth: 55,
		width: "100%",
		selectAll: false,
		isShowButton: false,
		single: true,
		noMatchesFound: "没有找到匹配项"
	 });
	 
});


/*****************************************************/
/***************3. FUNCTION 函数和方法*****************/
/*****************************************************/
function search() {
	$(".startPage").val("1");
	$("#queryForm").submit();
}

function resetMappings(ids) {
	wms.asyncPost(pagebase + "/sys/mapping/reset", { "ids":ids }, {
		successHandler : function(data, textStatus) {
			search();
		}
	});
}

function findMappingById(id) {
	wms.asyncGet(pagebase + "/sys/mapping/find/" + id, {}, {
		successHandler : function(data, textStatus) {
			$("#brandCodeDetail").val(data.brandCode);
			$("#keyCodeDetail").val(data.keyCode);
			$("#typeDetail").val(data.type);
			$("#contentDetail").text(data.content);
			
			if (data.processStatus == 0) {
				$("#processStatusDetail").html("<span class='label label-default'>待处理</span>");
			} else if (data.processStatus == 2) {
				$("#processStatusDetail").html("<span class='label label-warning'>异常</span>");
			} else {
				$("#processStatusDetail").html("<span class='label label-success'>已处理</span>");
			}
		}
	});
}

function checkUniqueCode(e, nv) {
	var data = wms.syncPost(pagebase + "/sys/mapping/checkcode/", { "code":$("#keyCodeDetail").val() });
	if (data) {
		return wms.validator.SUCCESS;
	}
	
	return "此编码已经存在";
}
