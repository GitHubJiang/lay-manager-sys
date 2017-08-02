//Resource
wms.addResource("zh-CN",{
    'frame_mustchooseorg': '必须选择一个和现有组织不同的组织'
});

//Variables
var orgtree= eval(unitTree);

//Ready Function
wms.addReadyFunc(function(){

    $('#orgpicker-modal').on('shown.bs.nifty-modal', function(e) {
        $('#org-tree').treeview({ 
    		data: prepareTreeStyle(orgtree),
        	levels:99,
            nodeIcon: 'icon-layers',
            showBorder: false,
            enableLinks:false,
            onNodeSelected: function(event, node) {
                $("input[name='orgpicker.org.id']").val(node.id);
                $("input[name='orgpicker.org.name']").val(node.text);
            }
        });
    });

    $('#orgpicker-modal .btn-primary').click(function(evt){
        var id = $("input[name='orgpicker.org.id']").val();
        window.location.href = pagebase + "/auth/org/change?ouId=" + id;
    });
});

//Functions

function prepareTreeStyle(tree){
    var result = [];
    if(tree != undefined){

        if(!$.isArray(tree)){
            result.push(_prepareTreeItem(tree));
        }else{
            $.each(tree, function(){
                result.push(_prepareTreeItem(this));
            });
        }
    }
    return result;
}

function _prepareTreeItem(treeItem){
    if(treeItem){
        if(treeItem.selectable != undefined && treeItem.selectable == false)
            $.extend(treeItem, {color: "#ABB7B7"});
        if($.isArray(treeItem.nodes)){
            $.extend(treeItem, {icon: 'fa fa-home'});
            treeItem.nodes = prepareTreeStyle(treeItem.nodes);
        }
    }
    return treeItem;
}


