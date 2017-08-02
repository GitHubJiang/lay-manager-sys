<%@include file="/pages/commons/common.jsp" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>error | WMS</title>
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
      <li class="active"><a href="#">error</a></li>
    </ol>
    <!-- ============================================================== --> 
    <!-- Start Content here --> 
    <!-- ============================================================== -->
    <div class="content"> 
      <!--主要内容开始部分 START-->
      <div class="wms-wrpper">
      <c:if test="${exception.statusCode != null}">
     	  	<p>
	     	[${exception.statusCode}] [${exception.message}]
	     	<a id="error" errorCode="${exception.statusCode}" error="${exception.message}"></a>

	       	</p>
	       
		    <div class="error-detail">
	     		
		    	${exception.stackTrace}
	    	</div>
	    </c:if>
	    <c:if test="${exception.statusCode == null}">
	    	<p>
	     		[system error]
	     		<a id="error" errorCode="1" error="system error"></a>
		    </p>
		    <div class="error-detail">
	        	<div class="clear-line height1"></div>
		    	${pageContext.exception}
	    	</div>
	    </c:if>
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
<div class="md-overlay"></div>
<%@include file="/pages/commons/common-menu-template.jsp" %>
<!-- End of eoverlay modal --> 
<script>
		var resizefunc = [];
</script> 
 <%@include file="/pages/commons/common-script.jsp" %>

<!-- Page Specific JS Libraries --> 
<%-- <script src="${staticbase }/assets/js/wmspages/demo.js?${version}"></script> --%>

<script type="text/javascript">
var maxPage="20";
var curPage="1";
</script>
</body>
</html>