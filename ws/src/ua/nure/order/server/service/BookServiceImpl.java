package ua.nure.order.server.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import javax.jws.WebService;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import ua.nure.order.entity.book.Book;
import ua.nure.order.server.dao.BookDAO;
import ua.nure.order.server.dao.BookDAOInMemory;
import ua.nure.order.server.dao.DAOException;

@ApplicationPath(value="/books")
@WebService(targetNamespace = "http://service.server.order.nure.ua/", 
	endpointInterface = "ua.nure.order.server.service.BookService", 
	portName = "BookServicePort", 
	serviceName = "BookService")
public class BookServiceImpl extends Application implements BookService {
	
	public BookServiceImpl() {
	}
	
	private static BookDAO bookDao = BookDAOInMemory.getInstance();

	@GET
	@Path(value="/{id}")
	@Produces(MediaType.APPLICATION_XML)
	@Override
	public Book getBook(@PathParam(value="id") int id) throws DAOException {
		return bookDao.findById(id);
	}

	@Path(value="/list/{pattern}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Override
	public Collection<Book> listBooks(@PathParam(value="pattern") String pattern) {
		HashSet<Book> books = new HashSet<Book>();
		books.addAll(bookDao.findByTitle(pattern));
		books.addAll(bookDao.findByAuthor(pattern));
		return books;
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Book[] listAll() {
		return bookDao.listBooks().toArray(new Book[0]);
	}
	
	@Path(value="/add")
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_XML)
	@Override
	public Book addBook(Book book) throws DAOException {
		book.setId(bookDao.addBook(book));
		return book;
	}

	@Path(value="/delete/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_XML)
	@Override
	public Book deleteBook(@PathParam(value="id") int id) throws DAOException {
		return bookDao.deleteBook(id);
	}

	@POST
	@Override
	public Book updateBook(Book book) throws DAOException {
		bookDao.deleteBook(book.getId());
		book.setId(bookDao.addBook(book));
		return book;
	}

	@POST
	@Override
	public boolean updateBookCount(int id, int count) throws DAOException {
		return bookDao.updateBookCount(id, count);
	}

}
