<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<jsp:useBean id="util" class="ua.nure.order.client.Util"></jsp:useBean>
<% response.setHeader("Cache-Control","max-age=0"); %>

<div class="modal-content">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h4 class="modal-title">${param.action=='confirm' ? 'Подтвердите отмену заказа' : 'Детали заказа' }</h4>
	</div>
	
	<div class="modal-body">
		<c:choose>
			<c:when test="${empty error }">
				<!-- <div class="row col-md-12"> -->
					<p class="lead">${empty order.title ? 'unregistered' : order.title }</p>
					<form class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-2 control-label">Имя</label>
							<div class="col-sm-10">
								<p class="form-control-static">${order.delivery.name }</p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Телефон</label>
							<div class="col-sm-10">
								<p class="form-control-static">${order.delivery.phone }</p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Email</label>
							<div class="col-sm-10">
								<p class="form-control-static">${order.delivery.email }</p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Адрес</label>
							<div class="col-sm-10">
								<p class="form-control-static">${order.delivery.address }</p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Описание</label>
							<div class="col-sm-10">
								<p class="form-control-static">${order.delivery.description }</p>
							</div>
						</div>
					</form>
				<!-- </div>
				<div class="row col-md-12"> -->
					<table class="table table-bordered table-striped">
						<colgroup class="col-position" />
						<colgroup class="col-title" />
						<colgroup class="col-count" />
						<colgroup class="col-price" />
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
									<td>
										<%-- <c:out value="${k}" /> --%>
									</td>
									<td>${book.key.title}</td>
									<td><p class="text-center">${book.value}</p></td>
									<td><p class="text-right">${book.key.price}</p></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<p class="lead text-right">Total: ${order.price }</p>
				<!-- </div> -->
			</c:when>
			<c:otherwise>
				<h4>Такой заказ не найден</h4>
				<p>${requestScope.error }</p>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="modal-footer">
		<c:choose>
			<c:when test="${param.action=='confirm' }">
				<form id="confirm" action="${context }/order/updateorderstatus"
					method="post"></form>
				<button class="btn btn-success" type="submit" form="confirm"
					name="rejected" value="${order.id }">Подтвердить</button>
				<button class="btn btn-danger" type="button" data-dismiss="modal">Отмена</button>
			</c:when>
			<c:otherwise>
				<button class="btn btn-primary" type="button" data-dismiss="modal">Закрыть</button>
			</c:otherwise>
		</c:choose>
	</div>
</div>
