<%@include file="/pages/commons/common.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--头部-->
<div class="layui-header" style="background: #393d49;">
	<a href="javascript:" class="logo">
		<i class="layui-icon" style="font-size: 42px;">&#xe62e;</i>
		<span>后台管理系统</span>
	</a>
	<button class="layui-btn layui-btn-mini MyYinCang" title="隐藏/显示" onclick="javascript:showHiddenLeft()">〓</button>
	<ul class="layui-nav" style="float: right;">
        <!-- <li class="layui-nav-item layui-this">
        	<a href="javascript:switchMy(1);">系统配置</a>
        </li>
        <li class="layui-nav-item ">
        	<a href="javascript:switchMy(2);">库存管理</a>
        </li> -->
       	<li class="layui-nav-item ">
       		<a href="javascript:;" class="admin-header-user">
           	<img src="images/Head.jpg" width="40" height="40" class="layui-circle" />
           	<span>&nbsp;管理员</span></a>
          		<dl class="layui-nav-child">
               	<dd>
                   	<a href="javascript:ChangePage('adminUser.html');"><i class="layui-icon">&#xe612;</i>
                       	管理员管理</a>
               	</dd>
                <dd>
                    <a href="Default.html"><i class="layui-icon">&#xe609;</i> 注销</a>
                </dd>
            </dl>
        </li>
    </ul>
</div>
<!--头部END-->