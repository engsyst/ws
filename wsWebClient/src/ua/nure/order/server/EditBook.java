package ua.nure.order.server;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.order.entity.book.Book;
import ua.nure.order.server.dao.BookDAO;
import ua.nure.order.server.dao.DAOException;

/**
 * Servlet implementation class EditBook
 */
@WebServlet("/book/editbook")
public class EditBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(EditBook.class);
	private BookDAO bookService = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditBook() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    @Override
	public void init() {
    	log.trace("init start");
    	bookService = (BookDAO) getServletContext().getAttribute("BookDao");
    	log.debug("Get BookDao from context -- > " + bookService);
    	log.trace("init finish");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.trace("Start");
		bookService = (BookDAO) getServletContext().getAttribute("BookDao");
		String sId = request.getParameter("id");
		Book book = null;
		try {
			book = bookService.getBook(Integer.parseInt(sId));
			log.debug("Found Book -- > " + book);
			request.setAttribute("book", book);
		} catch (DAOException e) {
			log.error("Unknown id --> " + sId, e);
			request.setAttribute("error", e.getMessage());
		} catch (NumberFormatException e) {
			log.error("Unknown id --> " + sId);
			throw new ServletException("Unknown id -->" + sId);
		}
		request.getRequestDispatcher("bookform.jsp").forward(request, response);
		log.debug("redirect to -- > bookform.jsp");
		log.trace("init finish");
	}
}
