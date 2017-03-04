<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ru">
<head>
<title>View book</title>
<%@ include file="jspf/headtag.jspf" %>
</head>
<body>
<%@ include file="jspf/menu.jspf" %>
	
	<div class="section main-content" >
		<div class="container">
			<div class="row">
				<h2>${book.title}</h2>
			</div>
			<div class="row">
				<div class="col-md-3">
					<img alt="" src="">
				</div>
				<div class="col-md-9">
					<ul>
						<li>
							<c:forEach var="a" items="${book.author }">
								${a.title }<br/>
							</c:forEach>
						</li>
						<li>${book.isbn}</li>
						<li>${book.price}</li>
						<li>${book.count}</li>
					</ul>
				</div>
			</div>
			<c:if test="${user.role == null || user.role != util.getRole('admin') }">
			<div class="row">
				<div class="col-md-3">
					<img alt="" src="">
				</div>
				<div class="col-md-9">
					<form action="${context }/addtocart" method="post">
						<input type="hidden" name="id" value="${book.id}"/>
						<input type="number" min="1" max="${book.count}" name="count" value="1"/>
						<button type="submit" name="tocart" id="${book.id }" title="Добавить в корзину"
							class="btn btn-success" value="${book.id }">
							<i class="glyphicon glyphicon-shopping-cart"></i>
						</button>
					</form>					
				</div>
			</div>
			</c:if>
		</div>
	</div>


<%@ include file="jspf/bootstrap.jspf" %>
</body>
</html>