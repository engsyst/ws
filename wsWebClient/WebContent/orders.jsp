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
<title>Заказы</title>
<%@ include file="jspf/headtag.jspf" %>
</head>
<body>
<%@ include file="jspf/menu.jspf" %>

<%-- PAGE VARIABLES --%>

	<jsp:useBean id="obp" class="ua.nure.order.shared.Pagination" scope="session" >
		<jsp:setProperty property="dao" name="obp" value="${applicationScope.BookDao }"/>
		<jsp:setProperty property="ascending" name="obp" value="true"/>
		<jsp:setProperty property="sortField" name="obp" value="title"/>
	</jsp:useBean>
	<c:if test="${!empty param.search }">
		<jsp:setProperty property="search" name="obp" param="search" />
	</c:if>
	<c:if test="${!empty param.page }">
		<jsp:setProperty property="page" name="obp" param="page" />
	</c:if>

	<c:set var="books" value="${obp.items }" scope="page" />
	<%-- <jsp:useBean id="books" type="java.util.List<ua.nure.order.entity.book.Book>"></jsp:useBean> --%>

<%-- CONTENT --%>

	<div class="section main-content" >
		<div class="container">
<%--   	<p>Page: ${obp.page }</p>
  	<p>Start: ${obp.start }</p>
  	<p>End: ${obp.page }</p>
  	<p>Max: ${obp.max }</p>
 	<p>Count: ${obp.count }</p>
 	<p>Total: ${obp.total }</p>
 	<p>Search: ${obp.search }</p>
 	<p>SortField: ${obp.sortField }</p>
 	<p>Acsending: ${obp.ascending }</p>
 	<p>Params: ${param }</p> --%>

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
				<h3>Заказы ${empty obp.search ? '' : 'по запросу \"'.concat(obp.search).concat('\"') }</h3>
			</div>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
					<div class="tabs">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#">Новые</a></li>
							<li><a href="#">В обработке</a></li>
							<li><a href="#">Выполненные</a></li>
							<li><a href="#">Отклоненные</a></li>
						</ul>
						<div class="tab-content">
							<div class="tabe-pane active">
								<%-- <table class="table table-bordered table-striped">
									<thead class="text-center">
										<tr>
											<th>#</th>
											<th>
												<div class="btn-group drop${obp.ascending ? 'down' : 'up' }">
													<a href="?ascending=${obp.ascending ? false : true }&amp;field=title">Название<b class="caret"></b></a>
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
														${a.title}<br>
													</c:forEach>
												</td>
												<td>${book.price}</td>
												<td>${book.count}</td>
												<td>
													<form action="addtocard" method="post">
														<button type="submit" name="tocart" id="${book.id }" title="Добавить в корзину" class="btn btn-success"><i class="glyphicon glyphicon-shopping-cart"></i></button>
			 										</form>
												</td>
											</tr>
										</c:forEach>			
									</tbody>
								</table> --%>
							</div>
							<div class="tabe-pane">
							</div>
							<div class="tabe-pane">
							</div>
							<div class="tabe-pane">
							</div>
						</div>
						
					</div>

					
				</div>
			</div>
			
			
			
	</c:otherwise>
	</c:choose>
		</div>
	</div>

<%@ include file="jspf/bootstrap.jspf" %>
</body>
</html>