$(document).ready(function(){
	//绑定导航数据
	var $menu = $('#menu');
	$.getJSON('/sys/user/menu?t='+new Date(), {
	}, function(result) {
		var html = getHtml(result);
		$menu.prepend(html); 
		$menu.find("li").eq(0).addClass('layui-this');
	});	
	$menu.find('li.layui-nav-item').each(function() {
		var $that = $(this);
		//绑定一级导航的点击事件
		$that.on('click', function() {
			var id = $that.data('pid');
			$.getJSON('/sys/user/menu?t='+new Date(), {
				pid: id
			}, function(result) {
				var html = getHtml(result);
				$('#left-menu').prepend(html); 
			});
		});

	});
}); 

function getHtml(data){
    var ulHtml ='';
    for (var i = 0; i < data.length; i++) {
    	if(data[i].pid !== false && data[i].pid !== 'undefined'){
    		ulHtml += '<li class="layui-nav-item" data-pid="'+data[i].pid+'"">';
    	}else if (data[i].spread ) {
           ulHtml += '<li class="layui-nav-item">';
       } else {
           ulHtml += '<li class="layui-nav-item">';
       }
       if(data[i].children !== undefined && data[i].children !== null && data[i].children.length > 0){
       	ulHtml += '<a>';
       	ulHtml += '<cite>' + data[i].title + '</cite>';
       	ulHtml += '</a>';
       	ulHtml += '<dl class="layui-nav-child">';
       	for(var j = 0; j < data[i].children.length; j++){
       		ulHtml += '<dd>';
       		ulHtml += '<a data-url="' + data[i].children[j].href + '">';
       		ulHtml += '<cite>' + data[i].children[j].title + '</cite>';
       		ulHtml += '</a>';
       		ulHtml += '</dd>';
       	}
       	ulHtml += '</dl>';
       } else{
           var dataUrl = (data[i].href !== undefined && data[i].href !== '') ? 'data-url="' + data[i].href + '"' : '';
           ulHtml += '<a ' + dataUrl + '>';                
			ulHtml += '<cite>' + data[i].title + '</cite>';
			ulHtml += '</a>';
       }
       ulHtml += '</li>';
    }
    return ulHtml;
}