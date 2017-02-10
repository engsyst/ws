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
<%@ include file="jspf/headtag.jspf" %>
</head>
<body>
<%@ include file="jspf/menu.jspf" %>

<%-- PAGE VARIABLES --%>
	<c:set var="context" value="${pageContext.request.contextPath}" />

<%-- CONTENT --%>

	<div class="section main-content" >
		<%@ include file="jspf/showmsg.jspf" %>
		<div class="container">
			<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="col-md-3">
				</div>
				<div class="col-md-6">
				
					<%@ include file="jspf/deliveryform.jspf" %>
					
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<!-- Checkboxes -->
						<div class="form-group">
							<label class="col-md-4 control-label" for="updateprofile"></label>
							<div class="col-md-8">
								<div class="checkbox">
									<label for="updateprofile"> 
									<input name="updateprofile" id="updateprofile" value="1" 
										type="checkbox" checked="checked">Обновить профиль</label>
								</div>
							</div>
						</div>
						<!-- Button (Double) -->
						<div class="form-group text-right">
							<label class="col-md-4 control-label" for="confirm"></label>
							<div class="col-md-12">
								<button class="btn btn-success" 
									id="confirm" 
									name="makeorder" 
									formaction="filldelivery" 
									form="delivery">Оформить</button>
								<button class="btn btn-success" 
									id="continue" 
									name="continue" 
									formaction="filldelivery" 
									form="delivery">Запомнить</button>
								<button class="btn btn-danger" 
									id="cancel" 
									formaction="listcart.jsp" 
									form="delivery">Отменить</button>
							</div>
						</div>
					</div>
					
				</div>
				<div class="col-md-3">
				</div>
			</div>
			</div>
		</div>
	</div>

<% session.removeAttribute("error"); %>
<%@ include file="jspf/bootstrap.jspf" %>
</body>
</html>