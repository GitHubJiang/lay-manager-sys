<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="pagebase" scope="request"><%=request.getContextPath()%></c:set>
<c:set var="staticbase" scope="request"><%=request.getContextPath()%></c:set>
<c:set var="uploadbase" scope="request"><%=request.getContextPath()%></c:set>
<c:set var="version" scope="request">version=000001</c:set>
<c:set var="title" scope="application">LayManager</c:set>