<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h4 class="modal-title" id="myModalLabel">Заказ № ${id }</h4>
		</div>
		<div class="modal-body">
			<c:choose>
			<c:when test="${empty error }">
				<h4>${order.title }</h4>
				<table class="table table-bordered table-striped">
					<colgroup class="col-position"/>
					<colgroup class="col-title"/>
					<colgroup class="col-count"/>
					<colgroup class="col-price"/>
					<thead class="text-center">
						<tr>
							<th>#</th>
							<th>Название</th>
							<th>Кол-во</th>
							<th>Цена</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="book" items="${order.items }">
						<tr>
							<c:set var="k" value="${k + 1}" />
							<td><%-- <c:out value="${k}" /> --%></td>
							<td>${book.key.title}</td>
							<td><p class="text-center">${book.value}</p></td>
							<td><p class="text-right">${book.key.price}</p></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				<p class="lead text-right">Total: ${order.price }</p>
			</c:when>
			<c:otherwise>
				<h4>Такой заказ не найден</h4>
			</c:otherwise>
			</c:choose>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="button" class="btn btn-primary">Save changes</button>
		</div>
	</div>	<!-- /.modal-content -->
</div>	<!-- /.modal-dialog -->