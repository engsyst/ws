<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ru">
<head>
<title>Регистрация</title>
<%@ include file="jspf/headtag.jspf" %>
</head>
<body>
<%@ include file="jspf/menu.jspf" %>

	<div class="container main-content">
		<div class="row">
			<div class="col-lg-4 col-md-4 col-sm-4"></div>
			<div class="col-lg-4 col-md-4 col-sm-4">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<header>
								<h1 class="center-text">Регистрация</h1>
							</header>
							<form method="post" action="adduser">
								<c:if test="${!empty errors.get('name') }">
								<div class="alert alert-danger">
									<a href="#" class="close" data-dismiss="alert">×</a>
									${errors.get('name') }
								</div>
								</c:if>
								<div class="form-group ">
									<label class="control-label" for="name">Имя</label> 
									<input type="text" class="form-control" id="name" name="name"
										placeholder="Имя" value="${user.name }" />
								</div>
								<c:if test="${!empty errors.get('login') }">
								<div class="alert alert-danger">
									<a href="#" class="close" data-dismiss="alert">×</a>
									${errors.get('login') }
								</div>
								</c:if>
								<div class="form-group ">
									<label class="control-label requiredField" for="login">Имя
										<span class="asteriskField"> * </span></label> 
									<input type="text" class="form-control" id="login" name="login"
										placeholder="Логин"  value="${user.login }" />
								</div>
								<c:if test="${!empty errors.get('password') }">
								<div class="alert alert-danger">
									<a href="#" class="close" data-dismiss="alert">×</a>
									${errors.get('password') }
								</div>
								</c:if>
								<div class="form-group">
									<label class="control-label requiredField" for="password">Пароль
										<span class="asteriskField"> * </span>
									</label> <input type="password" class="form-control" id="password" name="password"
										placeholder="пароль" />
								</div>
								<c:if test="${!empty errors.get('password2') }">
								<div class="alert alert-danger">
									<a href="#" class="close" data-dismiss="alert">×</a>
									${errors.get('password2') }
								</div>
								</c:if>
								<div class="form-group">
									<label class="control-label requiredField" for="password">Подтверждение пароля
										<span class="asteriskField"> * </span>
									</label> <input type="password" class="form-control" id="password2" name="password2"
										placeholder="подтверждение" />
								</div>
								<div class="form-group">
									<div>
										<button class="btn btn-primary " name="submit" type="submit">Зарегистрироваться</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-4 col-md-4 col-sm-4"></div>
		</div>
	</div>

	<% session.removeAttribute("error"); %>
	<%@ include file="jspf/bootstrap.jspf" %>
</body>
</html>