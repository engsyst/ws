<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<title>Add book</title>
<%@ include file="../jspf/headtag.jspf" %>
</head>
<body>
	<%@ include file="../jspf/menu.jspf" %>
	<jsp:useBean id="book" class="ua.nure.order.entity.book.Book" scope="session"/>
	<jsp:setProperty property="*" name="book"/>
	<div>
		<div id="form">
			<form action="addbook" method="post">
				<table>
					<caption>Add book</caption>
					<tr>
						<td colspan="2"><span id="formError"><c:out value="${errors['title'] }" /></span></td>
					</tr>
					<tr>
						<td><span id="formLabel">Title:</span></td>
						<td><input type="text" name="title" value="${book.title } " 
								autofocus="autofocus" required="required" /></td>
					</tr>
					<tr>
						<td colspan="2"><span id="formError"><c:out value="${errors['pass'] }" /></span></td>
					</tr>
					<tr>
						<td><span id="formLabel">ISBN:</span></td>
						<td><input type="text" name="isbn" value="${book.isbn }" 
								 pattern="ISBN-[0-9]{5,5}-[0-9]{4,4}" /></td>
					</tr>
					<tr>
						<td colspan="2"><span id="formError"><c:out value="${errors['count'] }" /></span></td>
					</tr>
					<tr>
						<td><span id="formLabel">Count:</span></td>
						<td><input type="number" name="count" value="${book.count }" 
								min="0" required="required" /></td>
					</tr>
					<tr>
						<td colspan="2"><span id="formError"><c:out value="${errors['author'] }" /></span></td>
					</tr>
					<c:set var="k" value="0"/>
					<c:if test="${fn:length(book.author) != 0 }">
						<c:forEach var="a" items="${book.author }">
							<tr>
								<td><span id="formLabel">Author:</span></td>
								<td><input type="text" name="author" value="${book.author[k] }" />
									<input type="submit" value="+" formaction="addauthor" /></td>
							</tr>
						<c:set var="k" value="${k + 1 }"/>
						</c:forEach>
					</c:if>
					<tr>
						<td><span id="formLabel">Author:</span></td>
						<td><input type="text" name="author" value="${book.author[k] }" />
							<input type="submit" value="+" formaction="addauthor" /></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Add"  /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>

<%@ include file="../jspf/bootstrap.jspf" %>
</body>
</html>
