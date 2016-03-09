<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="ua.nure.order.entity.book.Book"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ru">
<head>
<title>Книги</title>
<%@ include file="jspf/headtag.jspf" %>
</head>
<body>
<%@ include file="jspf/menu.jspf" %>

<%-- PAGE VARIABLES --%>

	<jsp:useBean id="lbp" class="ua.nure.order.shared.Pagination" scope="session" >
		<jsp:setProperty property="dao" name="lbp" value="${applicationScope.BookDao }"/>
		<jsp:setProperty property="ascending" name="lbp" value="true"/>
		<jsp:setProperty property="sortField" name="lbp" value="title"/>
	</jsp:useBean>
	<c:if test="${!empty param.search }">
		<jsp:setProperty property="ascending" name="lbp" param="ascending" />
	</c:if>
	<c:if test="${!empty param.search }">
		<jsp:setProperty property="sortField" name="lbp" value="field"/>
	</c:if>
	<c:if test="${!empty param.search }">
		<jsp:setProperty property="search" name="lbp" param="search" />
	</c:if>
	<c:if test="${!empty param.page }">
		<jsp:setProperty property="page" name="lbp" param="page" />
	</c:if>

	<c:set var="books" value="${lbp.items }" scope="page" />
	<%-- <jsp:useBean id="books" type="java.util.List<ua.nure.order.entity.book.Book>"></jsp:useBean> --%>

<%-- CONTENT --%>

	<div class="section main-content" >
		<div class="container">

		 	<c:choose>
		 	<c:when test="${empty books }">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
				 		<h1>No such books</h1>
					</div>
				</div>
		 	</c:when>
			<c:otherwise>
			<div class="row">
				<h3>Книги ${empty lbp.search ? '' : 'по запросу \"'.concat(lbp.search).concat('\"') }</h3>
			</div>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">

					<table class="table table-bordered table-striped">
						<thead class="text-center">
							<tr>
								<th>#</th>
								<th>
									<div class="btn-group drop${lbp.ascending ? 'down' : 'up' }">
										<a href="?ascending=${lbp.ascending ? false : true }&field=title">Название<b class="caret"></b></a>
									</div>
								</th>
								<th>Авторы</th>
								<th>Цена</th>
								<th>Кол-во</th>
								<th>В корзину</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="k" value="0" />
							<c:forEach var="book" items="${books }">
								<tr>
									<c:set var="k" value="${k + 1}" />
									<td><c:out value="${k}" /></td>
									<td><a href="ViewBook?id=${book.id}">${book.title}</a></td>
									<td>
										<c:forEach var="a" items="${book.author}">
											${a.title}<br/>
										</c:forEach>
									</td>
									<td>${book.price}</td>
									<td>${book.count}</td>
									<td>
										<form action="addtocart" method="post">
											<button type="submit" name="tocart" id="${book.id }" title="Добавить в корзину"
												class="btn btn-success" value="${book.id }">
												<i class="glyphicon glyphicon-shopping-cart"></i>
											</button>
 										</form>
									</td>
								</tr>
							</c:forEach>			
						</tbody>
					</table>
				</div>
			</div>
			
			<c:set value="${lbp }" var="paging" scope="request" />
			<jsp:include page="jspf/pagination.jsp" />

	</c:otherwise>
	</c:choose>
		</div>
	</div>

<%@ include file="jspf/bootstrap.jspf" %>
</body>
</html>