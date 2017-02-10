<%@ page pageEncoding="UTF-8" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.PrintWriter" %>
	<c:set var="context" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>

<c:set var="title" value="Error" scope="page" />
<body>
	<table id="main-container">
		<tr >
			<td class="content">
			<%-- CONTENT --%>
				
				<h2 class="error"><img alt="" src="${context }/img/error.gif">
					The following error occurred
				</h2>
				<hr/>
			
				<%-- this way we get the error information (error 404)--%>
				<c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
				<c:set var="message" value="${requestScope['javax.servlet.error.message']}"/>
				<c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>
				
				<c:if test="${not empty code}">
					<h3>Error code: <c:out value="${code}"/></h3>
				</c:if>			
				
				<c:if test="${not empty message}">
					<h3><c:out value="${message}"/></h3>
				</c:if>
				
				<%-- <c:if test="${not empty exception}">
					<h3><c:out value="${exception}"/></h3>
				</c:if> --%>
				
				<%-- if get this page using forward --%>
				<c:if test="${not empty error}">
					<p><c:out value="${error}"/></p>
				</c:if>	
				
				<%-- this way we print exception stack trace --%>
				<%--
					if (exception != null) {
						exception.printStackTrace(new PrintWriter(out));
					}
				--%>
				
			<%-- CONTENT --%>
			</td>
		</tr>
	</table>
</body>
</html>