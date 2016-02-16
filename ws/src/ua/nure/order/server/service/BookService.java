package ua.nure.order.server.service;

import java.util.Collection;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import ua.nure.order.entity.book.Book;
import ua.nure.order.server.dao.DAOException;

@WebService(name = "BookService", targetNamespace = "http://service.server.order.nure.ua/")
public interface BookService {

	@WebMethod(operationName = "getBook", action = "urn:GetBook")
	@RequestWrapper(className = "ua.nure.order.server.service.jaxws.GetBook", localName = "getBook", targetNamespace = "http://service.server.order.nure.ua/")
	@ResponseWrapper(className = "ua.nure.order.server.service.jaxws.GetBookResponse", localName = "getBookResponse", targetNamespace = "http://service.server.order.nure.ua/")
	@WebResult(name = "return")
	public abstract Book getBook(
			@WebParam(name = "id") int id)
			throws DAOException;

	@WebMethod(operationName = "addBook", action = "urn:AddBook")
	@RequestWrapper(className = "ua.nure.order.server.service.jaxws.AddBook", localName = "addBook", targetNamespace = "http://service.server.order.nure.ua/")
	@ResponseWrapper(className = "ua.nure.order.server.service.jaxws.AddBookResponse", localName = "addBookResponse", targetNamespace = "http://service.server.order.nure.ua/")
	@WebResult(name = "return")
	public abstract Book addBook(
			@WebParam(name = "book") Book book)
			throws DAOException;

	@WebMethod(operationName = "updateBook", action = "urn:UpdateBook")
	@RequestWrapper(className = "ua.nure.order.server.service.jaxws.UpdateBook", localName = "updateBook", targetNamespace = "http://service.server.order.nure.ua/")
	@ResponseWrapper(className = "ua.nure.order.server.service.jaxws.UpdateBookResponse", localName = "updateBookResponse", targetNamespace = "http://service.server.order.nure.ua/")
	@WebResult(name = "return")
	public abstract Book updateBook(
			@WebParam(name = "book") Book book)
					throws DAOException;
	
	@WebMethod(operationName = "updateBookCount", action = "urn:UpdateBookCount")
	@RequestWrapper(className = "ua.nure.order.server.service.jaxws.UpdateBookCount", localName = "updateBookCount", targetNamespace = "http://service.server.order.nure.ua/")
	@ResponseWrapper(className = "ua.nure.order.server.service.jaxws.UpdateBookCountResponse", localName = "updateBookCountResponse", targetNamespace = "http://service.server.order.nure.ua/")
	@WebResult(name = "return")
	public abstract boolean updateBookCount(
			@WebParam(name = "id") int id,
			@WebParam(name = "count") int count)
					throws DAOException;
	
	@WebMethod(operationName = "deleteBook", action = "urn:DeleteBook")
	@RequestWrapper(className = "ua.nure.order.server.service.jaxws.DeleteBook", localName = "deleteBook", targetNamespace = "http://service.server.order.nure.ua/")
	@ResponseWrapper(className = "ua.nure.order.server.service.jaxws.DeleteBookResponse", localName = "deleteBookResponse", targetNamespace = "http://service.server.order.nure.ua/")
	@WebResult(name = "return")
	public abstract Book deleteBook(
			@WebParam(name = "id") int id)
					throws DAOException;
	
	@WebMethod(operationName = "listBooks", action = "urn:ListBooks")
	@RequestWrapper(className = "ua.nure.order.server.service.jaxws.ListBooks", localName = "listBooks", targetNamespace = "http://service.server.order.nure.ua/")
	@ResponseWrapper(className = "ua.nure.order.server.service.jaxws.ListBooksResponse", localName = "listBooksResponse", targetNamespace = "http://service.server.order.nure.ua/")
	@WebResult(name = "return")
	public abstract Collection<Book> listBooks(
			@WebParam(name = "pattern") String pattern);

}