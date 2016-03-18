package ua.nure.order.server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.order.entity.book.Book;
import ua.nure.order.server.dao.BookDAO;
import ua.nure.order.shared.Util;

/**
 * Servlet implementation class UpdateBook
 */
@WebServlet("/book/updatebook")
public class UpdateBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(UpdateBook.class);
	private BookDAO bookService = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBook() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
	public void init() {
    	log.trace("init start");
    	bookService = (BookDAO) getServletContext().getAttribute("BookDao");
    	log.debug("Get BookDao from context -- > " + bookService);
    	log.trace("init finish");
    }

    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
		} catch (Exception e) {
			// TODO: handle exception
		}
		request.setAttribute("book", book);
		response.sendRedirect("list.jsp");
	}

	private Book createBook(HttpServletRequest request) {
		Book book = new Book();
		try {
			book.setId(Util.getIntOrElse(request.getParameter("id"), null));
			book.setTitle(request.getParameter("title"));
			book.setAuthor(request.getParameter("author"));
			book.setIsbn(request.getParameter("isbn"));
			book.setPrice(Util.getDoubleOrElse(request.getParameter("price"), null));
			book.setCount(Util.getIntOrElse(request.getParameter("count"), null));
			book.setDescription(request.getParameter("description"));
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
