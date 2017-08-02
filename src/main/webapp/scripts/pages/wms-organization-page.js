/*****************************************************/
/***************1. 基本信息的设定***********************/
/*****************************************************/
  
var pageI18nResources = {
    'zh-CN':{
        'dashboard_welcome': '欢迎使用WMS'
    }
};

function setActiveMenu(){
    $("a[mid='000']").addClass("active");
}

/*****************************************************/
/***************2. 页面加载时设定***********************/
/*****************************************************/
$(document).ready(function(){
   
  
});

/*****************************************************/
/***************3. FUNCTION 函数和方法*****************/
/*****************************************************/
$(function() {
    $('#org-tree-organization').treeview({
        data: loadOrganizationTree2(1),
        levels: 99,
        nodeIcon: 'icon-layers',
        showBorder: false,
        showTags: true,
        // enableLinks:true,
        onNodeSelected: function(event, node) {
            $("input[name='orgpicker.org.id']").val(node.id);
            $("input[name='orgpicker.org.name']").val(node.text);
            $(".organization-action-items").removeClass("show");
            $(".organization-action-type-"+ node.type).addClass("show");
        }
    });

    function loadOrganizationTree2(uid) {
        //get org tree with user id
        var tree = [{
            text: '运营中心1',
            id: 1,
            href: "wms-system.html",
            type: "1",
            nodes: [{
                text: '上海DC',
                id: 2,
                tags: ['test'],
                type: "2",
                nodes: [{
                    text: '上海飞利浦小件仓',
                    id: 21,
                    href: "index.html",
                    type: "3",
                    selectedPage: "index.html"
                }, {
                    text: '上海Nike仓',
                    type: "3",
                    id: 24
                }, {
                    text: '上海麦克疯仓',
                    type: "3",
                    id: 25
                }, ]
            }, {
                text: '北京DC',
                id: 3,
                type: "2",
                nodes: [{
                    text: '北京飞利浦小件仓',
                    type: "3",
                    id: 31
                }, {
                    text: '北京Nike仓',
                    type: "3",
                    id: 33
                }, ]
            }, {
                text: '广州DC',
                type: "2",
                id: 3,
                nodes: []
            }]
        }, {
            text: '运营中心2',
            type: "1",
            id: 5,
            href: "wms-operating.html",
            nodes: [

            ]
        }, {
            text: '运营中心3',
            type: "1",
            id: 6,
            href: "wms-ogic.html",
            nodes: [

            ]
        }];

        transformTreeData(tree);
        return prepareTreeStyle(tree);
    }
});