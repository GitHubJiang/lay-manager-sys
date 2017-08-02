//打开遮罩
function openLoading(hight,width,vaule){
    $.fn.jqLoading({ height: hight, width: width, text: vaule});
};

//关闭遮罩
function closeLoading(){
    $(this).jqLoading("destroy");
}