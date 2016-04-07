package ua.nure.order.server;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import ua.nure.order.entity.book.Book;
import ua.nure.order.server.dao.BookDAO;
import ua.nure.order.server.dao.DAOException;

/**
 * Get book from database by id and forward to edit book form
 * @param id in request
 * 
 * @author engsyst
 *
 */
@WebServlet("/book/get")
public class GetBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(GetBook.class);
	private BookDAO bookService = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBook() {
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
		String sId = request.getParameter("id");
		
		Book book = null;
		Map<Integer, String> categories = null;
		try {
			categories = bookService.getCategories();
			log.debug("Found categories -- > " + categories);
			request.setAttribute("categories", categories);
			if (sId == null || sId.isEmpty()) {
				book = new Book();
				log.debug("Create new Book -- > ");
			} else {
				book = bookService.getBook(Integer.parseInt(sId));
				log.debug("Found Book -- > " + book);
			}
			request.setAttribute("book", book);
		} catch (DAOException e) {
			log.error("DB access error --> ", e.getCause());
			request.setAttribute("error", e.getMessage());
		} catch (NumberFormatException e) {
			log.error("Unknown id --> " + sId);
			throw new ServletException("Unknown id -->" + sId);
		}
		request.getRequestDispatcher("bookform.jsp").forward(request, response);
		log.debug("Forward to -- > bookform.jsp");
		log.trace("Finish");
	}
}
