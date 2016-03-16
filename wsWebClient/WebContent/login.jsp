<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ru">
<head>
<title>Login</title>
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
								<h1 class="center-text">Login</h1>
							</header>
							<form method="post" action="${context }/login">
								<div class="form-group ">
									<label class="control-label requiredField" for="login">
										Имя <span class="asteriskField"> * </span>
									</label> <input type="text" class="form-control" id="login" name="login"
										placeholder="login" type="text" />
								</div>
								<div class="form-group ">
									<label class="control-label requiredField" for="password">
										Пароль <span class="asteriskField"> * </span>
									</label> <input type="password" class="form-control" id="password" name="password"
										placeholder="пароль" type="text" />
								</div>
<!-- 								<div class="form-group ">
									<div class="checkbox">
										<label class="checkbox"> <input name="remember"
											type="checkbox" value="Запомнить меня" /> Запомнить меня
										</label>
									</div>
								</div>
 -->								<div class="form-group">
									<div>
										<button class="btn btn-primary " name="submit" type="submit">
											Войти</button>
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