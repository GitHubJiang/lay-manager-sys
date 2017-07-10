layui.use(['element', 'layer'], function () {
            var element = layui.element()
            , layer = layui.layer;
        });
/**显示或隐藏左边菜单栏*/
function showHiddenLeft() {

	var sideWidth = $('#lay-side').width();
	if (sideWidth === 200) {
		$('#lay-body').animate({
			left : '0'
		});
		$('#lay-side').animate({
			width : '0'
		});
	} else {
		$('#lay-body').animate({
			left : '200px'
		});
		$('#lay-side').animate({
			width : '200px'
		});
	}
}
