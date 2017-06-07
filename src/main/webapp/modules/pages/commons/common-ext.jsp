<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- 主题配置 -->
<div class="larryThemeContent" id="LarryThemeSet" style="display:none;">
    <div class="larry-theme-form">
        <h3>系统主题预设</h3>
        <form action="" class="layui-form larry-theme-con">
            <div class="layui-form-item select-theme">
                <label class="layui-form-label">主题选择</label>
                <div class="layui-input-block">
                    <select lay-filter="larryTheme"  lay-verify="" id="themeName">
                      <option value="larry">LarryCMS默认主题</option>
                      <option value="A">LarryCMS深蓝主题</option>
                      <option value="B">LarryCMS墨绿主题</option>
                      <option value="larry_">更多主题以后添加</option>
                    </select>     
                </div>
            </div>
            <div class="layui-form-item fullscreen">
                <label class="layui-form-label">是否全屏</label>
                <div class="layui-input-block">
                    <input type="checkbox" lay-filter="fullscreen" lay-skin="switch"  value="1">
                </div>
            </div>
            <div class="layui-form-item submit-form">
                 <button class="layui-btn larry-button" lay-submit="" lay-filter="submitlocal">立即设置主题</button>
                 <button type="reset" class="layui-btn layui-btn-primary">重置当前设置</button>
            </div>
        </form>
    </div>
</div>
<!-- layui-body常用菜单定义 -->
<div class="rightMenu" id="rightMenu" style="display: none;">
   <ul>
        <li data-target="refreshCur">
            <i class="larry-icon " ></i>刷新当前页面
        </li>
        <li data-target="refreshKj">
            <i class="larry-icon " ></i>刷新外层框架
        </li>
        <li data-target="closeCurrent">
            <i class="larry-icon " ></i>关闭当前选项卡
        </li>
        <li data-target="closeOther">
            <i class="larry-icon " ></i>关闭其他选项卡
        </li>
        <li data-target="closeAll">
            <i class="larry-icon " ></i>全部关闭选项卡
        </li>
   </ul>
</div>
<!-- 屏幕锁屏 -->
<div class="lock-screen" style="display: none;">
   <div class="lock-wrapper" id="lock-screen">
        <div id="time"></div>
        <div class="lock-box">
             <img src="/modules/images/user.jpg" alt="用户头像">
             <h1>admin</h1>
             <form action="" class="layui-form lock-form">
                  <div class="layui-form-item">
                       <input type="password" name="lock_password" lay-verify="pass" placeholder="锁屏状态，请输入密码解锁" autocomplete="off" class="layui-input"  autofocus="">
                  </div>
                  <div class="layui-form-item">
                       <button class="layui-btn larry-btn" id="unlock">立即解锁</button>
                  </div>
             </form>
        </div>
   </div>
</div>