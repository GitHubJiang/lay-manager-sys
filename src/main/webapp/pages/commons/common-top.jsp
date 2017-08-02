<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <!-- Top Bar Start -->
  <div class="topbar">
    <div class="topbar-left">
      <div class="logo">
        <h1><a href="${pagebase }/index"><img src="${staticbase }/images/icon/logo.png?${version}" alt="Logo"></a></h1>
      </div>
      <button class="button-menu-mobile open-left"> <i class="fa fa-bars" data-toggle="tooltip" data-placement="bottom" data-original-title="菜单切换"></i> </button>
    </div>
    <!-- Button mobile view to collapse sidebar menu -->
    <div class="navbar navbar-default" role="navigation">
      <div class="container">
        <div class="navbar-collapse2">
          <ul class="nav navbar-nav navbar-right top-navbar">
            <li class="dropdown topbar-profile"> 
	            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
	            	<span class="rounded-image topbar-profile-image"><img src="${staticbase }/images/users/default-user.png?${version}"></span>
	            	<sec:authentication property="principal.displayName"/><i class="fa fa-caret-down"></i>
	            </a>
				<ul class="dropdown-menu">
	                <li><a href="#"><i class="icon-help-2"></i> 帮助</a></li>
	                <li><a class="md-trigger" data-modal="logout-modal"><i class="icon-logout-1"></i> 退出</a></li>
				</ul>
            </li>
            <li class="topbar-profile hide-phone"> 
	            <a href="#" class="md-trigger" data-modal="orgpicker-modal"> 
		            <span data-toggle="tooltip" data-placement="bottom" data-original-title="切换组织">
		            	<sec:authentication property="principal.currentOu.name"/>
		            </span>
		            <i class="fa fa-caret-down"></i>
	            </a> 
            </li>
            <li class="iconify"><a href="#" id="fs-switch"><i class="icon-resize-full" data-toggle="tooltip" data-placement="bottom" data-original-title="全屏"></i></a></li>
          </ul>
        </div>
        <!--/.nav-collapse --> 
      </div>
    </div>
  </div>
  <!-- Top Bar End --> 