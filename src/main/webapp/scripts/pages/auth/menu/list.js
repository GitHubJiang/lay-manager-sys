/*****************************************************/
/***************1. 基本信息的设定***********************/
/** ************************************************** */

/** ************************************************** */
/** *************2. 页面加载时设定********************** */
/** ************************************************** */
$(function(){	
	 $("#queryFormBtn").on("click", function() {
		 search();
	 });	 
	 //allMenu(); 无法控制编辑删除按钮
	//以下为初始化表格样式
     var option = {
     	theme:'default',
        expandLevel: 2
     };
     $('#treeTable').treeTable(option);
});


/*****************************************************/
/***************3. FUNCTION 函数和方法*****************/
/*****************************************************/
function search() {
	$(".startPage").val("1");
	$("#queryForm").submit();
}
var showContent = "";//添加内容变量
function allMenu(){
	$.ajax({
        type: "post",
        url: pagebase+"/auth/menu/allMenu", //Servlet请求地址
        dataType: "json",
        success: function (data) {           
            buildTree(data);
            $("#treeTableList").append(showContent);
       	 	//以下为初始化表格样式
            var option = {
            	theme:'default',
                expandLevel: 2
            };
            $('#treeTable').treeTable(option);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(errorThrown);
            console.info("数据请求异常 请查看控制台错误 或者检查servlet配置")
        }
    });
}

function buildTree(data) {
	
	for (var i = 0, l = data.length; i < l; i++) {
		var menu = data[i];
		if(menu.parentId == null || menu.nodes !=''){
			showContent += "<tr id='" + menu.id + "'>";
			showContent += "<td><span controller='true'>" + menu.text + "</span></td>";
	        showContent += "<td>" + menu.acl + "</td>";
	        showContent += "<td>" + menu.url + "</td>";
	        showContent += "<td></td>";
	        showContent += "</tr>";
	        if(menu.nodes !=''){
	           buildTree(menu.nodes);
	        }
		} else {
			showContent += "<tr id='" + menu.id + "' pid='" + menu.parentId + "'>";
			showContent += "<td>" + menu.text + "</td>";
	        showContent += "<td>" + menu.acl + "</td>";
	        showContent += "<td>" + menu.url + "</td>";
	        showContent += "<td></td>";
	        showContent += "</tr>";	        
		}
    }
	
}

