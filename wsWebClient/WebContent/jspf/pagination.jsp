<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="params" class="ua.nure.order.client.ReqParam" scope="page">
	<jsp:setProperty property="params" name="params" value="${paramValues }"/>
</jsp:useBean>

<!-- 

!!! IMPORTANT !!!
Required attribute named "paging" in scope 

 -->

<div class="row">
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
		<ul class="pagination">
		<%-- <!-- -- Debug output --  --!>
		<div>Total data rows: ${paging.total } Page no: ${paging.page } Count on page: ${paging.count } 
			Max data pages: ${paging.max } Start page no: ${paging.paging.start } 
			End page No ${paging.paging.end } Data search param: ${paging.search }</div> 
		--%>
		<c:choose>
		<c:when test="${paging.page == 0 }">
			<li class="disabled"><a href="?${params.setParam('page', paging.page).toString() }"><i class="glyphicon glyphicon-backward"></i></a></li>
			<li class="disabled"><a href="?${params.setParam('page', paging.page).toString() }"><i class="glyphicon glyphicon-chevron-left"></i></a></li>
		</c:when>
		<c:otherwise>
			<li><a href="?${params.setParam('page', 0).toString() }"><i class="glyphicon glyphicon-backward"></i></a></li>
			<li><a href="?${params.setParam('page', paging.page - 1).toString() }"><i class="glyphicon glyphicon-chevron-left"></i></a></li>
		</c:otherwise>
		</c:choose>
		<c:set var="i" value="${paging.start }" />
		<c:forEach begin="${paging.start }" end="${paging.end - 1 }" step="${1 }" >
			<c:choose>
			<c:when test="${paging.page == i }">
				<li class="active"><a href="?${params.setParam('page', i).toString() }">${i + 1 }</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="?${params.setParam('page', i).toString() }">${i + 1 }</a></li>
			</c:otherwise>
			</c:choose>
			<c:set var="i" value="${i + 1 }" />
		</c:forEach>
		<c:choose>
		<c:when test="${paging.page >= paging.max - 1 }"> 
			<li class="disabled"><a href="?${params.setParam('page', paging.page).toString() }"><i class="glyphicon glyphicon-chevron-right"></i></a></li>
			<li class="disabled"><a href="?${params.setParam('page', paging.page).toString() }"><i class="glyphicon glyphicon-forward"></i></a></li>
		</c:when>
		<c:otherwise>
			<li><a href="?${params.setParam('page', paging.page + 1).toString() }"><i class="glyphicon glyphicon-chevron-right"></i></a></li>
			<li><a href="?${params.setParam('page', paging.max - 1).toString() }"><i class="glyphicon glyphicon-forward"></i></a></li>
		</c:otherwise>
		</c:choose>
		</ul>
	</div>
</div>