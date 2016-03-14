<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<jsp:useBean id="params" class="ua.nure.order.client.ReqParam" scope="page">
		<jsp:setProperty property="params" name="params" value="${paramValues }"/>
	</jsp:useBean>
<!DOCTYPE html>
<html lang="ru">
<head>
<title>Корзина</title>
<%@ include file="../jspf/headtag.jspf" %>
</head>
<body>
	<c:set var="k" value="3" />
	<p><c:out value="${params}"></c:out></p>
	<p><c:out value="${params.setParam('c', 5) }"></c:out></p>
	<p><c:out value="${params.setParam('b', k, k + 1) }"></c:out></p>
<c:out value="${pageContext.request.queryString}"></c:out>
</body>
</html>