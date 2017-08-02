<%@include file="/pages/commons/common.jsp" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>首页 | WMS</title>
<%@include file="/pages/commons/common-meta.jsp" %>
<%@include file="/pages/commons/common-css.jsp" %>
<%@include file="/pages/commons/common-compatible.jsp" %>
<%@include file="/pages/commons/common-icon.jsp" %>

</head>
<body class="fixed-left" locale="zh-cn">
<%@include file="/pages/commons/common-layer.jsp" %>
<!-- Begin page -->
<div id="wrapper"> 
  
  <%@include file="/pages/commons/common-top.jsp" %>
  
  
  <jsp:include page="/pages/commons/common-left.jsp">
  	<jsp:param name="pagecode" value="" />
  </jsp:include>
  
  <!-- Start right content -->
  <div class="content-page">
    <ol class="breadcrumb">
      <li class="active"><a href="#">仪表盘</a></li>
    </ol>
    <!-- ============================================================== --> 
    <!-- Start Content here --> 
    <!-- ============================================================== -->
    <div class="content"> 
      <!--主要内容开始部分 START-->
      
             <div class="row">
                    <div class="col-md-12  portlets">
                         <div class="widget-newall">

                             <div class="row">
                                 <div class="col-md-4">
                                         <h3 class="marign-none">角色列表</h3>
                                      </div>
                                      <div class="col-md-8">
                                          <div class="toolbar-btn-action">
                                              <a class="btn btn-success" href="wms-role-manage-add-s.html"><i class="fa fa-plus-circle"></i> 新建角色</a>
                                          </div>
                                      </div> 
                       		</div>
                			<div class="widget widget-content padding">
                			
                			<div class="row">
                            <div class="col-md-12"> 
                                								
									<form id="queryForm" role="form">
		                                <input type="hidden" name="sort" value=""/>
		                                <input type="hidden" name="page" value=""/>
		                            <div class="table-responsive">
		                            <table class="table table-hover" data-sortable data-sortable-type="server" data-page="1" data-page-count="50">
                                            <thead>
                                            <tr class="sort-header">
                                                <th data-batch data-extra-batch="tableExtraBatch" data-sortable="false"> </th>
                                                <th data-sort-name="id">id</th>
                                                <th data-sort-name-asc="name, pos desc" data-sort-name-desc="name desc, pos desc">员工姓名</th>
                                                <th data-sort-name="empno" data-sorted-direction="descending">工号</th>
                                                <th data-sort-name="pos">职位</th>
                                                <th data-sortable="false" style="width: 80px;">有效</th>
                                                <th data-sortable="false">操作</th>
                                            </tr>
                                            <tr class="filter-header">
                                                <th> </th>
                                                <th> </th>
                                                <th><input type="text" value="" name="" class="form-control input-sm filter"></th>
                                                <th><input type="text" value="" name="" class="form-control input-sm filter"></th>
                                                <th><input type="text" value="" name="" class="form-control input-sm filter"></th>
                                                <th> <select name="" class="form-control input-sm filter">
                                                    <option value="">-</option>
                                                    <option value="1">是</option>
                                                    <option value="0">否</option>
                                                </select>
                                                </th>
                                                <th> <button class="btn btn-primary btn-sm" name="" type="submit"> <i class="icon-search"></i> 搜索 </button>
                                                </th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <td><input type="checkbox" class="checkbox checkbox-inline"/></td>
                                                <td><strong>001</strong></td>
                                                <td>土老师</td>
                                                <td>1366</td>
                                                <td>高级总监</td>
                                                <td><span class="label label-success">有效</span></td>
                                                <td><div class="btn-group btn-group-xs">
                                                    <a class="btn btn-default" href="#">
                                                        <i class="fa fa-cog"></i> 修改权限
                                                    </a>
                                                    <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                                                        <span class="caret"></span>
                                                        <span class="sr-only">Toggle Dropdown</span>
                                                    </button>
                                                    <ul class="dropdown-menu pull-right" role="menu">
                                                        <li><a href="#fakelink">设置有效</a></li>
                                                        <li><a href="#fakelink">设置无效</a></li>
                                                        <li class="divider"></li>
                                                        <li><a href="#fakelink">更改头像</a></li>
                                                    </ul>
                                                </div></td>
                                            </tr>
                                            <tr>
                                                <td><input type="checkbox" class="checkbox checkbox-inline"/></td>
                                                <td><strong>002</strong></td>
                                                <td>牛人</td>
                                                <td>1900</td>
                                                <td>技术主管</td>
                                                <td><span class="label label-success">有效</span></td>
                                                <td><div class="btn-group btn-group-xs">
                                                    <a class="btn btn-default" href="#">
                                                        <i class="fa fa-cog"></i> 修改权限
                                                    </a>
                                                    <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                                                        <span class="caret"></span>
                                                        <span class="sr-only">Toggle Dropdown</span>
                                                    </button>
                                                    <ul class="dropdown-menu pull-right" role="menu">
                                                        <li><a href="#fakelink">设置有效</a></li>
                                                        <li><a href="#fakelink">设置无效</a></li>
                                                        <li class="divider"></li>
                                                        <li><a href="#fakelink">更改头像</a></li>
                                                    </ul>
                                                </div></td>
                                            </tr>
                                            <tr>
                                                <td><input type="checkbox" class="checkbox checkbox-inline"/></td>
                                                <td><strong>003</strong></td>
                                                <td>三金</td>
                                                <td>2600</td>
                                                <td>出库管理员</td>
                                                <td><span class="label label-warning">失效</span></td>
                                                <td><div class="btn-group btn-group-xs">
                                                    <a class="btn btn-default" href="#">
                                                        <i class="fa fa-cog"></i> 修改权限
                                                    </a>
                                                    <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                                                        <span class="caret"></span>
                                                        <span class="sr-only">Toggle Dropdown</span>
                                                    </button>
                                                    <ul class="dropdown-menu pull-right" role="menu">
                                                        <li><a href="#fakelink">设置有效</a></li>
                                                        <li><a href="#fakelink">设置无效</a></li>
                                                        <li class="divider"></li>
                                                        <li><a href="#fakelink">更改头像</a></li>
                                                    </ul>
                                                </div></td>
                                            </tr>
                                            </tbody>
                                        </table>
		                            </div>
		                            </form>
                                </div>
                                
                                   

                                </div>
                                </div>
                                
                            </div>
                        </div>
                    </div>
            





      </div>

      <!--主要内容开始部分 END--> 
     <%@include file="/pages/commons/common-footer.jsp" %>
    </div>
    <!-- ============================================================== --> 
    <!-- End content here --> 
    <!-- ============================================================== --> 
    
  </div>
  <!-- End right content --> 
  
</div>

<!-- End of page --> 
<!-- the overlay modal element -->

<%@include file="/pages/commons/common-modal-footer.jsp" %>
<!-- End of eoverlay modal --> 
<script>
		var resizefunc = [];
</script> 
 <%@include file="/pages/commons/common-script.jsp" %>

<!-- Page Specific JS Libraries --> 
<script src="${staticbase }/scripts/pages/demo.js?${version}"></script>

<script type="text/javascript">
var maxPage="${param.pageCount}";
var curPage="${param.currentPage}";
</script>
</body>
</html>