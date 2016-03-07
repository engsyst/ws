<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
			<li class="disabled"><a href="#"><i class="glyphicon glyphicon-backward"></i></a></li>
			<li class="disabled"><a href="#"><i class="glyphicon glyphicon-chevron-left"></i></a></li>
		</c:when>
		<c:otherwise>
			<li><a href="?search=${paging.search }&page=${0 }"><i class="glyphicon glyphicon-backward"></i></a></li>
			<li><a href="?search=${paging.search }&page=${paging.page - 1 }"><i class="glyphicon glyphicon-chevron-left"></i></a></li>
		</c:otherwise>
		</c:choose>
		<c:set var="i" value="${paging.start }" />
		<c:forEach begin="${paging.start }" end="${paging.end - 1 }" step="${1 }" >
			<c:choose>
			<c:when test="${paging.page == i }">
				<li class="active"><a href="?search=${paging.search }&page=${i }">${i + 1 }</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="?search=${paging.search }&page=${i }">${i + 1 }</a></li>
			</c:otherwise>
			</c:choose>
			<c:set var="i" value="${i + 1 }" />
		</c:forEach>
		<c:choose>
		<c:when test="${paging.page >= paging.max - 1 }"> 
			<li class="disabled"><a href="#"><i class="glyphicon glyphicon-chevron-right"></i></a></li>
			<li class="disabled"><a href="#"><i class="glyphicon glyphicon-forward"></i></a></li>
		</c:when>
		<c:otherwise>
			<li><a href="?search=${paging.search }&page=${paging.page + 1 }"><i class="glyphicon glyphicon-chevron-right"></i></a></li>
			<li><a href="?search=${paging.search }&page=${paging.max - 1 }"><i class="glyphicon glyphicon-forward"></i></a></li>
		</c:otherwise>
		</c:choose>
		</ul>
	</div>
</div>