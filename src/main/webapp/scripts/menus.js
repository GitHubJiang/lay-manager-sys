var wmsmenus = {

    "system": [
        {icon:"icon-feather", name: "基础信息管理", submenus: [
            {menuId:"001",url:"wms-organization-config.html", name:"组织配置"},
            {menuId:"002",url:"#", name:"物理仓配置"},
            {menuId:"003",url:"wms-role-manage-s.html", name:"标准角色"},
            {menuId:"004",url:"wms-employee-manage-s.html", name:"标准员工"},
            {menuId:"005",url:"wms-role-manage.html", name:"操作角色"},
            {menuId:"006",url:"wms-employee-manage.html", name:"操作员工"}

        ]}
    ],
    "document": [
        {icon:"icon-feather", name: "前端开发环境说明", submenus: [
            {menuId:"001",url:"index2.html", name:"目录结构和文件结构"},
            {menuId:"002",url:"doc-plugins.html", name:"插件说明"}
        ]},
        {icon:"icon-feather", name: "开发帮助文档", submenus: [
            {menuId:"003",url:"doc-icons.html", name:"图标集"},
            {menuId:"004",url:"doc-buttons.html", name:"按钮使用"},
            {menuId:"005",url:"doc-layout.html", name:"常用布局"},
            {menuId:"006",url:"doc-form.html", name:"Form以及对应元素使用"},
            {menuId:"007",url:"doc-table.html", name:"表格使用"},
            {menuId:"008",url:"doc-tree.html", name:"树的使用"},
            {menuId:"009",url:"doc-dialog.html", name:"对话框的使用"}
        ]},
    ]
}
function loadMenu(){
    /*wms.frame.menu.loadMenu((typeof menuItems == 'string')?eval(menuItems):menuItems);
    $("#sidebar-menu a[mid='" + $("body").attr("data-menu-id") + "']").addClass("active");*/
}