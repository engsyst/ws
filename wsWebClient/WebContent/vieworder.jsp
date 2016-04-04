<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
<title>Корзина</title>
<%@ include file="jspf/headtag.jspf" %>
</head>
<body>
<%@ include file="jspf/menu.jspf" %>

	<c:set var="books" value="${order.items }" scope="page" />


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
				<h3>Ваш заказ # <span style="text-decoration: underline>">${order.id }</span></h3>
			</div>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">

					<table class="table table-bordered table-striped">
						<col/>
						<col class="tab-title-col" />
						<col class="tab-author-col" />
						<col class="tab-price-col" />
						<col class="tab-count-col" />
						<thead>
							<tr>
								<th>#</th>
								<th>
									<div class="btn-group drop${lcp.ascending ? 'down' : 'up' }">
										<a href="?ascending=${lcp.ascending ? false : true }&field=title">Название<b class="caret"></b></a>
									</div>
								</th>
								<th>
									<div class="btn-group drop${lcp.ascending ? 'down' : 'up' }">
										<a href="?ascending=${lcp.ascending ? false : true }&field=author">Авторы<b class="caret"></b></a>
									</div>
								</th>
								<th>
									<div class="btn-group drop${lcp.ascending ? 'down' : 'up' }">
										<a href="?ascending=${lcp.ascending ? false : true }&field=price">Цена<b class="caret"></b></a>
									</div>
								</th>
								<th>
									<div class="btn-group drop${lcp.ascending ? 'down' : 'up' }">
										<a href="?ascending=${lcp.ascending ? false : true }&field=count">Кол-во<b class="caret"></b></a>
									</div>
								</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="k" value="0" />
							<c:forEach var="book" items="${books }">
								<tr>
									<c:set var="k" value="${k + 1}" />
									<td><c:out value="${k}" /></td>
									<td>${book.key.title}</td>
									<td>
										<c:forEach var="a" items="${book.key.author}">
											${a.title}<br/>
										</c:forEach>
									</td>
									<td>${book.key.price}</td>
									<td>${book.value}</td>
								</tr>
							</c:forEach>			
						</tbody>
					</table>
				</div>
			</div>
			
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-right">
					<p class="lead">Всего: ${order.getPrice() }</p>
				</div>
			</div>

			</c:otherwise>
			</c:choose>
		</div>
	</div>
	
	${session.removeAttribute("order") }

<%@ include file="jspf/bootstrap.jspf" %>
</body>
</html>