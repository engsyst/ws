package ua.nure.order.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.order.entity.book.Book;
import ua.nure.order.entity.book.Category;
import ua.nure.order.server.dao.BookDAO;
import ua.nure.order.server.dao.DAOException;
import ua.nure.order.server.dao.InsertException;
import ua.nure.order.server.dao.UpdateException;
import ua.nure.order.shared.Util;

/**
 * Get book from web-form and updates it in database
 * 
 * @author engsyst
 *
 */
@WebServlet("/book/update")
public class UpdateBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(UpdateBook.class);
	private BookDAO bookService = null;
       
    @Override
	public void init() {
    	log.trace("init start");
    	bookService = (BookDAO) getServletContext().getAttribute("BookDao");
    	log.debug("Get BookDao from context -- > " + bookService);
    	log.trace("init finish");
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Book book = createBook(request);
		log.debug("Book --> " + book);
		try {
			if (book.getId() == null) {
				book.setId(bookService.addBook(book));
			} else {
				bookService.updateBook(book);
			}
		} catch (InsertException e) {
			log.error(e.getMessage(), e.getCause());
			request.setAttribute("error", "Невозможно добавить книгу. Повторите запрос позже.");
		} catch (UpdateException e) {
			log.error(e.getMessage(), e.getCause());
			request.setAttribute("error", "Невозможно обновить книгу. Повторите запрос позже.");
		} catch (DAOException e) {
			log.error(e.getMessage(), e.getCause());
			request.setAttribute("error", "Ошибка доступа базе данных. Повторите запрос позже.");
		}
		request.setAttribute("book", book);
		response.sendRedirect("list.jsp");
	}

	private Book createBook(HttpServletRequest request) {
		Book book = new Book();
		try {
			String param = request.getParameter("id");
			log.trace("param id --> " + param);
			book.setId(Util.getIntOrElse(param, null));
			param = request.getParameter("title");
			log.trace("param title --> " + param);
			book.setTitle(param);
			param = request.getParameter("author");
			log.trace("param author --> " + param);
			book.setAuthor(param);
			param = request.getParameter("isbn");
			log.trace("param isbn --> " + param);
			book.setIsbn(param);
			param = request.getParameter("category");
			log.trace("param category --> " + param);
			book.setCategory(Category.fromValue(param));
			param = request.getParameter("price");
			log.trace("param price --> " + param);
			book.setPrice(Util.getDoubleOrElse(param, null));
			param = request.getParameter("count");
			log.trace("param count --> " + param);
			book.setCount(Util.getIntOrElse(param, null));
			param = request.getParameter("description");
			log.trace("param description --> " + param);
			book.setDescription(param);
			param = request.getParameter("cover");
			log.trace("param cover --> " + param);
			book.setCover(request.getParameter("cover"));
		} catch (Exception e) {
			// do nothing
		}
		return book;
	}

	private Book updateBook(Book book, HttpServletRequest request) {
		String param = Util.getOrElse(request.getParameter("id"), "");
//		if (param.isEmpty())
//			crea
//		if (book.getId() == null) {
//			book.setId(Util.getIntOrElse(param, null));
//		}
		param = request.getParameter("title");
		if (book.getTitle() == null) {
			book.setTitle(param);
		} else if (param != null) {
			book.setTitle(param);
		}
		param = request.getParameter("author");
		if (book.getAuthor() == null) {
			book.setAuthor(param);
		} else if (param != null) {
			book.setAuthor(param);
		}
		param = request.getParameter("isbn");
		if (book.getIsbn() == null) {
			book.setIsbn(param);
		} else if (param != null) {
			book.setIsbn(param);
		}
		param = request.getParameter("price");
		Double p = Util.getDoubleOrElse(request.getParameter("price"), null);
		if (book.getPrice() == 0.) {
			book.setPrice(p);
		} else if (p != null) {
			book.setPrice(p);
		}
		param = request.getParameter("price");
		Integer c = Util.getIntOrElse(request.getParameter("count"), null);
		if (book.getCount() == 0) {
			book.setPrice(c);
		} else if (c != null) {
			book.setPrice(c);
		}
		param = request.getParameter("description");
		if (book.getDescription() == null) {
			book.setDescription(param);
		} else if (param != null) {
			book.setDescription(param);
		}
		// book.setCover(request.getParameter("cover"));
		return book;
	}
	
}
