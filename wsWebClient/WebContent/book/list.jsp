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
	<jsp:useBean id="params" class="ua.nure.order.client.ReqParam" scope="page">
		<jsp:setProperty property="params" name="params" value="${paramValues }"/>
	</jsp:useBean>
	
	<jsp:useBean id="pag" class="ua.nure.order.shared.Pagination" scope="request" >
		<jsp:setProperty property="dao" name="pag" value="${applicationScope.BookDao }"/>
		<jsp:setProperty property="ascending" name="pag" value="true"/>
		<jsp:setProperty property="sortField" name="pag" value="title"/>
	</jsp:useBean>
		<jsp:setProperty property="*" name="pag" />

	<c:set var="books" value="${pag.items }" scope="page" />

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
				<h3>Книги ${empty pag.search ? '' : 'по запросу \"'.concat(pag.search).concat('\"') }</h3>
			</div>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">

					<table class="table table-bordered table-striped">
						<colgroup class="col-position"/>
						<colgroup class="col-title"/>
						<colgroup class="col-title"/>
						<colgroup class="col-price"/>
						<colgroup class="col-count"/>
						<colgroup class="col-action"/>
						<thead class="text-center">
							<tr class="text-center">
								<th class="text-center">#</th>
								<th>
									<div class="center-block btn-group drop${pag.ascending ? 'up' : 'down' }">
										<p class="text-center">
										<a href="?${params.setParam('ascending', !pag.ascending).setParam('field', 'title').toString() }">Название<b class="caret"></b></a>
										<%-- <a href="?ascending=${!pag.ascending }&field=title">Название<b class="caret"></b></a> --%>
										</p>
									</div>
								</th>
								<th><p class="text-center">Авторы</p></th>
								<th><p class="text-center">Цена</p></th>
								<th><p class="text-center">Кол-во</p></th>
								<th class="text-center">
									<a href="get" 
										class="btn btn-success" 
										data-toggle="modal" 
										data-target="#myModal" 
										title="Добавить новую книгу">
										<i class="glyphicon glyphicon-new-window"></i>
									</a>
								</th>
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
									<td class="text-center"><span>
											<a href="get?id=${book.id }" 
												class="btn btn-primary" 
												data-toggle="modal" 
												data-target="#myModal" 
												title="Редактировать">
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
			<c:set value="${pag }" var="paging" scope="request" />
			<jsp:include page="../jspf/pagination.jsp" />

	</c:otherwise>
	</c:choose>
		</div>
	</div>

<%@ include file="../jspf/bootstrap.jspf" %>
    <script src="${context }/js/confirm.js"></script>

</body>
</html>