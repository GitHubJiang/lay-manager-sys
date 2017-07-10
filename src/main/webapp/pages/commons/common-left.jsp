<%@include file="/pages/commons/common.jsp" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!--侧栏-->
<div class="layui-side layui-bg-black" id="lay-side">
    <div class="layui-side-scroll">
        <ul class="layui-nav layui-nav-tree" lay-filter="test" id="left1">
            <li class="layui-nav-item layui-nav-itemed"><a href="javascript:;">管理</a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="javascript:ChangePage('G_title.html');"><i class="layui-icon">&#xe63a;</i> 网站信息设置</a></dd>
                    <dd>
                        <a href="javascript:ChangePage('G_links.html');"><i class="layui-icon">&#xe64c;</i> 友情链接管理</a></dd>
                    <dd>
                        <a href="javascript:ChangePage('G_slide.html');"><i class="layui-icon">&#xe634;</i> 幻灯片管理</a></dd>                    
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-nav-tree" lay-filter="test" id="left2" style="display: none;">
            <li class="layui-nav-item layui-nav-itemed"><a href="javascript:;">信息管理</a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="javascript:ChangePage('G_informationList.html?type=1&name=资质荣誉');"><i class="layui-icon">
                            &#xe602;</i> 管理资质荣誉</a></dd>
                    
                </dl>
            </li>
            <li class="layui-nav-item layui-nav-itemed"><a href="javascript:;">产品管理</a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="javascript:ChangePage('G_productadd.html');"><i class="layui-icon">&#xe61f;</i>
                            产品添加</a></dd>
                    <dd>
                        <a href="javascript:ChangePage('G_productList.html');"><i class="layui-icon">&#xe631;</i>
                            产品管理</a></dd>
                    <dd>
                        <a href="javascript:ChangePage('problemType.html');"><i class="layui-icon">&#xe62a;</i>
                            产品分类管理</a></dd>
                </dl>
            </li>
        </ul>
    </div>
</div>
<!--侧栏END-->