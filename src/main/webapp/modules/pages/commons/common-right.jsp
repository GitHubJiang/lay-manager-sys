<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="larry-title-box">
    <div class="go-left key-press pressKey" id="titleLeft" title="滚动至最右侧"><i class="larry-icon larry-weibiaoti6-copy"></i> </div>
    <ul class="layui-tab-title" lay-allowClose="true" id="layui-tab-title" lay-filter="subadd">
       <li class="layui-this" id="admin-home"  lay-id="0" fresh=1>
           <i class="larry-icon larry-houtaishouye"></i><em>后台首页</em>
       </li>
    </ul>
    <div class="title-right" id="titleRbox">
       <div class="go-right key-press pressKey" id="titleRight" title="滚动至最左侧"><i class="larry-icon larry-right"></i></div> 
       <div class="refresh key-press" id="refresh_iframe"><i class="larry-icon larry-shuaxin2"></i><cite>刷新</cite></div>
       <div class="often key-press" lay-filter='larryOperate' id="buttonRCtrl">
           <ul class="layui-nav posr">
               <li class="layui-nav-item posb">
                 <a class="top"><i class="larry-icon larry-caozuo"></i><cite>常用操作</cite></a>
                 <dl class="layui-nav-child">
                     <dd>
                         <a  data-eName="closeCurrent"><i class="larry-icon larry-guanbidangqianye"></i>关闭当前选项卡</a>
                     </dd>
                     <dd>
                         <a  data-eName="closeOther"><i class="larry-icon larry-guanbiqita"></i>关闭其他选项卡</a>
                     </dd>
                     <dd>
                         <a  data-eName="closeAll"><i class="larry-icon larry-guanbiquanbufenzu"></i>关闭全部选项卡</a>
                     </dd>
                     <dd>
                         <a  data-eName="refreshAdmin"><i class="larry-icon larry-kuangjia_daohang_shuaxin"></i>刷新最外层框架</a>
                     </dd>
                   </dl>
               </li>
           </ul>
       </div>       
    </div>
</div>