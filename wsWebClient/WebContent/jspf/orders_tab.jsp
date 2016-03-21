<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
	
<jsp:useBean id="util" class="ua.nure.order.client.Util" scope="page" />

<div class="tabe-pane active">
	<table class="table table-bordered">
		<colgroup class="col-position"/>
		<colgroup class="col-status"/>
		<colgroup class="col-title"/>
		<colgroup class="col-count"/>
		<colgroup class="col-price"/>
		<colgroup class="col-action" align="center"/>
		<thead class="text-center">
			<tr>
				<th>#</th>
				<th>Состояние</th>
				<th>
					<div class="btn-group drop${ascending ? 'down' : 'up' }">
						<a href="?ascending=${ascending ? false : true }&amp;field=title">Клиент<b class="caret"></b></a>
					</div>
				</th>
				<th>Кол-во</th>
				<th>Цена</th>
				<th>Действия</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="order" items="${orders}" >
			<tr class="row-normal">
				<c:set var="k" value="${order.id}" />
				<td><c:out value="${k}" /></td>
				<td class="text-center">
					<a href="orderdetal?action=view&id=${order.id }" 
						class="btn status-${order.status }" data-toggle="modal" data-target="#myModal" >
						<i class="glyphicon ${util.statusIconName(util.get(order.status)) }"></i>
					</a>
					<%-- ${order.status == util.get("newed") ? 
					order.status == util.get("inprogress") ? 
						'<div class="btn btn-primary"><i class="glyphicon glyphicon-forward"></i></div>' : 
					order.status == util.get("completed") ? 
						'<div class="btn btn-success"><i class="glyphicon glyphicon-saved"></i></div>' : 
					order.status == util.get("rejected") ? 
						'<div class="btn btn-danger"><i class="glyphicon glyphicon-remove"></i></div>' : '' }
					
				</td> --%>
				<td colspan="2"><p class="lead">${empty order.title ? "unregistered" : order.title }</p></td>
				<td><p class="text-right lead">${order.price}</p></td>
				<td>
				<c:if test="${order.status != util.get('rejected') && (order.status != util.get('completed')) }">
					<form action="updateorderstatus" method="post">
					<span>
						<button type="submit" name="${util.nextStatus(order.status) }" id="${order.id }" 
							title="Принять" class="btn status-${util.nextStatus(order.status) }" 
							value="${order.id }">
							<i class="glyphicon ${util.statusIconName(util.nextStatus(order.status)) }"></i>
						</button>
					</span>
					<span>
						<a href="orderdetal?action=confirm&id=${order.id }" 
							class="btn status-rejected" data-toggle="modal" data-target="#myModal" >
							<i class="glyphicon ${util.statusIconName(util.get('rejected')) }"></i>
						</a>
					</span>
					</form>
				</c:if>
				</td>
			</tr>
		<c:set var="k" value="0" />
		<c:forEach var="book" items="${order.items }">
			<tr>
				<c:set var="k" value="${k + 1}" />
				<td><%-- <c:out value="${k}" /> --%></td>
				<td></td>
				<td>${book.key.title}</td>
				<td><p class="text-center">${book.value}</p></td>
				<td><p class="text-right">${book.key.price}</p></td>
			</tr>
		</c:forEach>			
		</c:forEach>			
		</tbody>
	</table>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
 	<div class="modal-dialog">
		<div class="modal-content">
		</div>
	</div>
</div><!-- /.modal -->