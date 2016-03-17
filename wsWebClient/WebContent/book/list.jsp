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
<%@ include file="../jspf/headtag.jspf" %>
</head>
<body>
<%@ include file="../jspf/menu.jspf" %>

<%-- PAGE VARIABLES --%>

	<jsp:useBean id="lbp" class="ua.nure.order.shared.Pagination" scope="session" >
		<jsp:setProperty property="dao" name="lbp" value="${applicationScope.BookDao }"/>
		<jsp:setProperty property="ascending" name="lbp" value="true"/>
		<jsp:setProperty property="sortField" name="lbp" value="title"/>
	</jsp:useBean>
		<jsp:setProperty property="ascending" name="lbp" param="ascending" />
		<jsp:setProperty property="sortField" name="lbp" param="field"/>
	<c:if test="${!empty param.search }">
	</c:if>
	<c:if test="${!empty param.search }">
	</c:if>
	<c:if test="${!empty param.search }">
		<jsp:setProperty property="search" name="lbp" param="search" />
	</c:if>
	<c:if test="${!empty param.page }">
		<jsp:setProperty property="page" name="lbp" param="page" />
	</c:if>

	<c:set var="books" value="${lbp.items }" scope="page" />

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
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">

					<table class="table table-bordered table-striped">
						<colgroup class="col-position"/>
						<colgroup class="col-title"/>
						<colgroup class="col-title"/>
						<colgroup class="col-count"/>
						<colgroup class="col-price"/>
						<colgroup class="col-action"/>
						<thead class="text-center">
							<tr>
								<th>#</th>
								<th><div class="block-inline">
									<div class="block-inline text-center btn-group drop${lbp.ascending ? 'up' : 'down' }">
										<a href="?ascending=${lbp.ascending ? false : true }&field=title">Название<b class="caret"></b></a>
									</div>
								</div>
								</th>
								<th><p class="text-center">Авторы</p></th>
								<th><p class="text-center">Цена</p></th>
								<th><p class="text-center">Кол-во</p></th>
								<th><p class="text-center">В корзину</p></th>
							</tr>
						</thead>
						<tbody>
							<c:set var="k" value="0" />
							<c:forEach var="book" items="${books }">
								<tr>
									<c:set var="k" value="${k + 1}" />
									<td><p class="text-center"><c:out value="${k}" /></p></td>
									<td><p class="text-left"><a href="../viewbook?id=${book.id}">${book.title}</a></p></td>
									<td><p class="text-left">
										<c:forEach var="a" items="${book.author}">
											${a.title}<br/>
										</c:forEach></p>
									</td>
									<td><p class="text-right">${book.price}</p></td>
									<td><p class="text-right">${book.count}</p></td>
									<td><span class="text-center">
											<a href="editbook?id=${book.id }" 
												class="btn btn-primary" data-toggle="modal" data-target="#myModal" >
												<i class="glyphicon glyphicon-edit"></i>
											</a>
										</span>
									</td>
								</tr>
							</c:forEach>			
						</tbody>
					</table>
				</div>
			</div>
			<!-- Modal -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			 	<div class="modal-dialog">
					<div class="modal-content">
					</div>
				</div>
			</div><!-- /.modal -->			
			<c:set value="${lbp }" var="paging" scope="request" />
			<jsp:include page="../jspf/pagination.jsp" />

	</c:otherwise>
	</c:choose>
		</div>
	</div>

<%@ include file="../jspf/bootstrap.jspf" %>
</body>
</html>