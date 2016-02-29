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
<%@ include file="/WEB-INF/jspf/search.jspf" %>
	<hr/>
	<table id="main-container">
		<tr>
			<td class="content">
			<%-- CONTENT --%>

			<c:choose>
			<c:when test="${fn:length(books) == 0}">No such books</c:when>
	
			<c:otherwise>
				<table id="list_book_table">
					<thead>
						<tr><th>No</th><th>Name</th><th>Author</th><th>Price</th><th>Count</th></tr>
					</thead>
					<c:set var="k" value="0" />
					<c:forEach var="book" items="${books}">
						<tr>
							<c:set var="k" value="${k + 1}" />
							<td><c:out value="${k}" /></td>
							<td><a href="ViewBook?id=${book.id}">${book.title}</a></td>
							<td>
								<c:forEach var="a" items="${book.author}">
									${a}<br/>
								</c:forEach>
							</td>
							<td>${book.price}</td>
							<td>${book.count}</td>
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