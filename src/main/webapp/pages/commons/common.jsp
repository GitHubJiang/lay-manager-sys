<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="page" uri="http://www.baozun.cn/pagetaglib" %>
<%@taglib prefix="pri" uri="http://www.baozun.cn/acl" %>
<%@taglib prefix="baozun" uri="http://www.baozun.cn/baozun" %>
<%@taglib prefix="shopdog" uri="http://www.baozun.cn/shopdog" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="pagebase" scope="request"><%=request.getContextPath()%></c:set>
<c:set var="staticbase" scope="request"><%=request.getContextPath()%></c:set>
<c:set var="uploadbase" scope="request"><%=request.getContextPath()%></c:set>
<c:set var="version" scope="request">version=000003</c:set>
<c:set var="title" scope="application">shopdog</c:set>
<c:set var="lang" scope="request">zh-CN</c:set>