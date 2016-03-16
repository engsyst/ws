<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="ua.nure.order.entity.book.Book"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
<title>Заказы</title>
<%@ include file="../jspf/headtag.jspf" %>
</head>
<body>
<%@ include file="../jspf/menu.jspf" %>

<%-- PAGE VARIABLES --%>

	<jsp:useBean id="odp" class="ua.nure.order.shared.Pagination" scope="page" >
		<jsp:setProperty property="dao" name="odp" value="${applicationScope.OrderDao }"/>
		<jsp:setProperty property="ascending" name="odp" value="true"/>
		<jsp:setProperty property="sortField" name="odp" value="login"/>
	</jsp:useBean>
	<jsp:setProperty property="*" name="odp" />

	<c:set var="search" value="${odp.search }"></c:set>
	<c:set var="orders" value="${odp.items }" scope="session" />

<%-- CONTENT --%>

	<div class="section main-content" >
		<div class="container">
		<%-- ${odp } --%>
 	
			<div class="row">
				<h3>Заказы ${empty search ? '' : 'по запросу \"'.concat(odp.search).concat('\"') }</h3>
			</div>
			
			
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="tabs">
						<ul class="nav nav-tabs">
							<li ${search.equals("newed") ? 'class="active"' : '' }><a href="${context }/order/orders.jsp?search=newed">Новые</a></li>
							<li ${search.equals("inprogress") ? 'class="active"' : '' }><a href="${context }/order/orders.jsp?search=inprogress">В обработке</a></li>
							<li ${search.equals("completed") ? 'class="active"' : '' }><a href="${context }/order/orders.jsp?search=completed">Выполненные</a></li>
							<li ${search.equals("rejected") ? 'class="active"' : '' }><a href="${context }/order/orders.jsp?search=rejected">Отклоненные</a></li>
							<li ${(empty search) ? 'class="active"' : '' }><a href="${context }/order/orders.jsp">Все</a></li>
						</ul>
						<div class="tab-content">
						 	<c:choose>
						 	<c:when test="${empty orders }">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
									 		<h1>No such orders</h1>
										</div>
									</div>
						 	</c:when>
							<c:otherwise>
								<jsp:include page="../jspf/orders_tab.jsp" />
								<c:set value="${odp }" var="paging" scope="request" />
								<jsp:include page="../jspf/pagination.jsp" />
							</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

<%@ include file="../jspf/bootstrap.jspf" %>
    <script src="${context }/js/confirm.js"></script>
</body>
</html>