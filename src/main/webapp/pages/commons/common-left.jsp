<%@include file="/pages/commons/common.jsp" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- Left Sidebar Start -->
  <div class="left side-menu">
    <div class="sidebar-inner slimscrollleft"> 
      <!-- Search form -->
      <form role="search" class="navbar-form">
        <div class="form-group">
          <input type="text" placeholder='搜索' class="form-control">
          <button type="submit" class="btn search-button"><i class="fa fa-search"></i></button>
        </div>
      </form>
      <div class="clearfix"></div>
      <!--- Organization Picker -->
      <div class="profile-info">
        <div class="col-xs-12">
          <div class="profile-text"><spring:message code="系统"/></div>
          <div class="profile-buttons"> <a class="md-trigger" data-modal="orgpicker-modal"  data-toggle="tooltip" data-placement="bottom" data-original-title="菜单切换"><i class="fa fa-location-arrow text-blue-1"></i></a> <a class="md-trigger" data-modal="logout-modal"  data-toggle="tooltip" data-placement="bottom" data-original-title="退出"><i class="fa fa-power-off text-red-1"></i></a> </div>
        </div>
      </div>
      <!--- Divider -->
      <div class="clearfix"></div>
      <hr class="divider" />
      <div class="clearfix"></div>
      <!--- Divider -->
      <div id="sidebar-menu"><baozun:menu pageCode="${param.pagecode}"/></div>
      <div class="clearfix"></div>
      <br>
      <br>
      <br>
    </div>
  </div>
  <!-- Left Sidebar End --> 