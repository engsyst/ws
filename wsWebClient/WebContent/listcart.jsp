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

	<%-- <jsp:useBean id="cart" 
		class="ua.nure.order.client.Cart<ua.nure.order.entity.book.Book>" 
		type="ua.nure.order.client.Cart<ua.nure.order.entity.book.Book>" scope="session" /> --%>
	<jsp:useBean id="cartDao" class="ua.nure.order.client.CartBookDAO" scope="page">
		<jsp:setProperty property="cart" name="cartDao" value="${sessionScope.cart }"/>
	</jsp:useBean>
	<jsp:useBean id="lcp" class="ua.nure.order.shared.Pagination" scope="session" >
		<jsp:setProperty property="ascending" name="lcp" value="true"/>
		<jsp:setProperty property="sortField" name="lcp" value="title"/>
	</jsp:useBean>
	<jsp:setProperty property="dao" name="lcp" value="${cartDao }"/>
	<c:if test="${!empty param.search }">
		<jsp:setProperty property="ascending" name="lcp" param="ascending" />
	</c:if>
	<c:if test="${!empty param.search }">
		<jsp:setProperty property="sortField" name="lcp" param="field"/>
	</c:if>
	<c:if test="${!empty param.search }">
		<jsp:setProperty property="search" name="lcp" param="search" />
	</c:if>
	<c:if test="${!empty param.page }">
		<jsp:setProperty property="page" name="lcp" param="page" />
	</c:if>

	<c:set var="books" value="${lcp.items }" scope="page" />

	<div class="section main-content" >
		<%@ include file="jspf/showmsg.jspf" %>
	
		<div class="container">
		 	<c:choose>
		 	<c:when test="${empty cart }">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
				 		<h1>Корзина пуста</h1>
					</div>
				</div>
		 	</c:when>
			<c:otherwise>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
					<h1>Ваша корзина</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">

					<table class="table table-bordered table-striped">
						<col class="col-position" />
						<col class="col-title" />
						<col class="col-author" />
						<col class="col-price" />
						<col class="col-count" />
						<col class="col-form-action" />
						<thead class="text-center">
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
								<th>В корзину</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="k" value="0" />
							<c:forEach var="book" items="${books }">
								<%-- <tr ${cart.get(book) > book.count ? "class='row-warning' title='Не достаточно книг.'" :'' }> --%>
								<tr class="${cart.get(book) > book.count ? 'alert-danger' : '' }" title="В наличии: ${book.count }">
									<c:set var="k" value="${k + 1}" />
									<td><c:out value="${k}" /></td>
									<td><a href="${context }/ViewBook?id=${book.id}">${book.title}</a></td>
									<td>
										<c:forEach var="a" items="${book.author}">
											${a.title}<br/>
										</c:forEach>
									</td>
									<td>${book.price }</td>
									<td>${cart.get(book) }</td>
									<td>
										<form action="updatecart" method="post">
										<div class="form-group">
											<span class="col-xs-6">
												<input type="number" name="count" value="${cart.get(book) }" 
													class="form-control" min="0" max="${book.count }">
											</span>
											<button type="submit" name="update" 
												title="Обновить корзину"
												class="btn btn-success" value="${book.id }">
												<i class="glyphicon glyphicon-shopping-cart"></i>
											</button>
											<button type="submit" name="remove" 
												title="Удалить из корзины"
												class="btn btn-danger" value="${book.id }">
												<i class="glyphicon glyphicon-remove"></i>
											</button>
										</div>
 										</form>
									</td>
								</tr>
							</c:forEach>			
						</tbody>
					</table>
				</div>
			</div>
			
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-right">
					<p><span>Всего: </span><span>${sessionScope.cart.getPrice() }</span></p>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
                <a href="filldelivery.jsp" class="btn btn-primary btn-block">Оформить заказ</a>
				<!-- <form action="filldelivery.jsp" method="get">
	                 <div class="form-group">
	                      <button type="submit" name="buy" class="btn btn-primary btn-block">Оформить заказ</button>
	                 </div>
				</form> -->
				</div>
			</div>

			<%-- <div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
					<form action="${context }/makeorder" method="post">
                   <div class="form-group">
                        <button type="submit" name="buy" class="btn btn-primary btn-block">Оформить заказ</button>
                    </div>
					</form>
				</div>
			</div> --%>

			
			
			<c:set value="${lcp }" var="paging" scope="request" />
			<jsp:include page="jspf/pagination.jsp" />

	</c:otherwise>
	</c:choose>
		</div>
	</div>

<%@ include file="jspf/bootstrap.jspf" %>
</body>
</html>