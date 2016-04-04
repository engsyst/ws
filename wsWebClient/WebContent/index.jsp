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
<title>Добро пожаловать</title>
<%@ include file="jspf/headtag.jspf" %>
</head>
<body>
<%@ include file="jspf/menu.jspf" %>

<%-- CONTENT --%>

	<div class="" >
	<div id="carousel" class="carousel slide" >
	
		<!-- Slide-indicators -->
		<ol class="carousel-indicators" >
			<li data-target="#carousel" data-slide-to="0" class="active" ></li>
			<li data-target="#carousel" data-slide-to="1" ></li>
			<li data-target="#carousel" data-slide-to="2" ></li>
		</ol>
	
		<!-- Slide-content -->
		<div class="carousel-inner" >
			<div class="item active" >
				<img alt="" src="img/quill-and-parchment.jpg">
				<div class="carousel-caption" >
					<h3>One</h3>
					<p>One</p>
				</div>
			</div>
			<div class="item" >
				<img alt="" src="img/artleo.com-78172.jpg">
				<div class="carousel-caption" >
					<h3>Two</h3>
					<p>Two</p>
				</div>
			</div>
			<div class="item" >
				<img alt="" src="img/gallery_source_1512-5102.jpg">
				<div class="carousel-caption" >
					<h3>Three</h3>
					<p>Three</p>
				</div>
			</div>
		</div>
		
		<!-- Slide-control -->
		<a href="#carousel" class="left carousel-control" data-slide="prev">
			<span class="glyphicon glyphicon-chevron-left"></span>
		</a>
		<a href="#carousel" class="right carousel-control" data-slide="next">
			<span class="glyphicon glyphicon-chevron-right"></span>
		</a>
	</div>
	</div>

<%@ include file="jspf/bootstrap.jspf" %>
    <%-- <script src="${context }/js/confirm.js"></script> --%>
</body>
</html>