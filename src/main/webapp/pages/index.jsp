<%@include file="/pages/commons/common.jsp" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>格林斯顿 | 首页</title>
<%@include file="/pages/commons/common-meta.jsp" %>
<%@include file="/pages/commons/common-css.jsp" %>
<%@include file="/pages/commons/common-compatible.jsp" %>
<%@include file="/pages/commons/common-icon.jsp" %>
</head>
<body class="fixed-left" locale="${locale }">
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
      <div class="wms-wrpper"> </div>
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
<script src="${staticbase }/scripts/pages/dashboard.js?${version}"></script>
</body>
</html>