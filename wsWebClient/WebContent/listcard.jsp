<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<title>Все книги</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/menu.jspf" %>
	<table>
		<tbody>
			<tr>
				<td><p>Введите имя книги</p>
					<form action="SearchBook" method="post">
						<input type="search" name="title" /> <input type="submit" />
					</form></td>
				<td><p>Введите автора книги</p>
					<form action="SearchBook" method="post">
						<input type="search" name="author" /> <input type="submit" />
					</form></td>
		</tbody>
	</table>
	<form></form>

	<hr/>
	<table id="main-container">
		<tr>
			<td class="content">
			<%-- CONTENT --%>
			
			<c:set var="orders" value="${sessionScope.card.orders}"></c:set>

			<c:choose>
			<c:when test="${fn:length(orders) == 0}">Корзина пуста</c:when>
	
			<c:otherwise>
				<table id="list_order_table">
					<thead>
						<tr><th>No</th><th>Name</th><th>Author</th><th>Price</th><th>Count</th></tr>
					</thead>
					<c:set var="k" value="0" />
					<c:forEach var="order" items="${orders}">
						<tr>
							<c:set var="k" value="${k + 1}" />
							<td><c:out value="${k}" /></td>
							<td><a href="ViewBook?id=${order.book.id}">${order.book.title}</a></td>
							<td>
								<c:forEach var="a" items="${order.book.author}">
									${a}<br/>
								</c:forEach>
							</td>
							<td>${order.book.price}</td>
							<td>${order.count}</td>
						</tr>
					</c:forEach>			
				</table>
			</c:otherwise>
			</c:choose>
						
			<%-- CONTENT --%>
			</td>
		</tr>
	</table>
</body>
</html>