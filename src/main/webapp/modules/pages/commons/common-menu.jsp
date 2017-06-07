<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 顶部区域 -->
<div class="layui-header header-menu">
	<div class="logo posb" id="log"><img src="/common/images/logo.png"></div>
	<div class="layui-main posb">
	  	<!-- 左侧导航收缩开关 -->
	  	<div class="side-menu-switch posb" id="toggle"><span class="switch"  ara-hidden="true"></span></div>
           <!-- 顶级菜单 -->
           <div class="larry-top-menu posb">
           	<ul class="layui-nav clearfix" id="menu">
           	</ul>
           </div>
           <!-- 右侧常用菜单导航 -->
           <div class="larry-right-menu posb">
               <button class="layui-btn layui-btn-small" id="dianzhan">
               	<i class="larry-icon larry-dianzan"></i>
               	打赏作者
               </button>
               <ul class="layui-nav clearfix">
                   <li class="layui-nav-item">
                       <a class="onFullScreen" id="FullScreen"><i class="larry-icon larry-quanping"></i>全屏</a>
                   </li>
                   <li class="layui-nav-item">
                       <a id="lock"><i class="larry-icon larry-diannao5"></i>锁屏</a>
                   </li>
                   <li class="layui-nav-item">
                       <a id="clearCached"><i class="larry-icon larry-qingchuhuancun"></i>清除缓存</a>
                   </li>
                   <li class="layui-nav-item">
                       <a id="larryTheme"><i class="larry-icon larry-theme1"></i>设置主题</a>
                   </li>
                   <li class="layui-nav-item kjfs">
                       <a class="kuaijiefangshi"><i class="larry-icon larry-kuaijie"></i><cite>快捷方式</cite></a>
                       <dl class="layui-nav-child">
                           <dd>
                               <a href="http://www.larrycms.com/" target="_blank">网站主页</a>
                           </dd>
                           <dd>
                               <a href="http://blog.larrycms.com/" target="_blank">我的博客</a>
                           </dd>
                       </dl>
                   </li>         
                   <li class="layui-nav-item exit">
                       <a  id="logout"><i class="larry-icon larry-exit"></i><cite>退出</cite></a>
                   </li>
               </ul>
           </div>
	</div>
</div>
<!-- 左侧导航 -->
<div class="layui-side larrycms-left" id="larry-side">
	 <div class="layui-side-scroll" >
             <!-- 管理员信息      -->
             <div class="user-info">
                  <div class="photo">
                      <img src="/modules/images/user.jpg" alt="">
                  </div>
                  <p>admin您好！欢迎登录</p>
             </div>
             <!-- 系统菜单 -->
             <div class="sys-menu-box" >
                  <ul class="layui-nav layui-nav-tree" id="larrySideNav" lay-filter="side" >
                      
                  </ul>
             </div>
        </div>
</div>