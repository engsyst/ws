<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<div class="modal-content">

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h4 class="modal-title">Книга</h4>
	</div>
	<div class="modal-body">
		<div class="row">
		<form class="form-horizintal" action="update" id="editbook" method="post">
			<!-- <fieldset> -->
	
				<!-- Form Name -->
				<!-- <legend>Книга</legend> -->
	
				<!-- Text input-->
				<div class="form-group required">
					<label class="col-sm-2 control-label" for="title">Название<!-- <span class="asteriskField"> * </span> --></label>
					<div class="col-sm-10">
						<input id="textinput" name="title" placeholder="Название"
							class="form-control input-md" required="required" type="text"
							value="${book.title }">
						<span class="help-block">Название книги</span>
					</div>
				</div>
	
				<!-- Appended Input-->
				<div class="form-group required">
					<label class="col-sm-2 control-label" for="author">Авторы</label>
					<div class="col-sm-10">
						<div class="input-group">
							<input id="author" name="author" class="form-control"
								placeholder="Автор" required="required" type="text"
								value="${book.authors() }">
								<span class="input-group-addon"> </span>
						</div>
						<p class="help-block">Введите авторов через запятую</p>
					</div>
				</div>
				
				<div class="col-sm-6">
				<!-- Text input-->
				<div class="form-group required">
					<label class="col-sm-12 control-label requiredField" for="price">Цена</label>
					<div class="col-sm-12">
						<input id="price" name="price" placeholder="цена"
							class="form-control input-md" type="number"
							min="0" step="0.01" value="${book.price }">
							<span class="help-block">Цена за книгу</span>
					</div>
				</div>
				</div>
	
				<div class="col-sm-6">
				<!-- Text input-->
				<div class="form-group required">
					<label class="col-sm-12 control-label" for="count">Количество</label>
					<div class="col-sm-12">
						<input id="count" name="count" placeholder="Количество"
							class="form-control input-md" required="required" 
							type="number" min="0" value="${book.count }">
						<span class="help-block">На складе</span>
					</div>
				</div>
				</div>

				<!-- Text input-->
				<div class="form-group">
					<label class="col-sm-2 control-label" for="isbn">ISBN</label>
					<div class="col-sm-10">
						<input id="isbn" name="isbn" placeholder="ISBN-12345-1234"
							class="form-control input-md" type="text" 
							value="${book.isbn }">
							<span class="help-block">Формат: ISBN-12345-1234</span>
					</div>
				</div>
	
				<!-- Select Basic -->
				<div class="form-group">
					<label class="col-sm-2 control-label" for="category">Категория</label>
					<div class="col-sm-10">
						<select id="category" name="category" class="form-control">
							<c:forEach var="cat" items="${categories }">
								<c:set var="c" value="${book.category.ordinal() }" ></c:set>
								<option ${cat.key == c ? 'selected="selected"' : '' } 
									value="${cat.value }" >${cat.value }</option>
							</c:forEach>
						</select>
						<span class="help-block">Выберите одну категорию</span>
					</div>
				</div>

				<!-- Textarea -->
				<div class="form-group">
					<label class="col-sm-2 control-label" for="description">Описание</label>
					<div class="col-sm-10">
						<textarea class="form-control" id="description"
							name="description">${book.description }</textarea>
						<span class="help-block">Добавьте аннотацию к книге</span>
					</div>
				</div>
	
				<!-- File Button -->
				<div class="form-group">
					<label class="col-sm-2 control-label" for="cover">Обложка</label>
					<div class="col-sm-10">
						<input id="cover" name="cover" class="input-file" type="file" disabled="disabled">
					</div>
				</div>
	
			<!-- </fieldset> -->
		</form>
		</div>
	</div>
	<div class="modal-footer">
		<button class="btn btn-success" type="submit" form="editbook" name="id" value="${book.id }">Подтвердить</button>
		<button class="btn btn-danger" type="button" data-dismiss="modal">Отмена</button>
	</div>
</div>
