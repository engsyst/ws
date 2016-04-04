package ua.nure.order.server;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.order.entity.book.Book;
import ua.nure.order.server.dao.BookDAO;

/**
 * Servlet implementation class ViewBook
 */
public class ViewBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ViewBook.class);
	private BookDAO bookService = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewBook() {
        super();
    }

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
		log.trace("doPost start");
		RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
		String id = request.getParameter("id");
		Book book = null;
		if (id != null)
			try {
				book = bookService.getBook(Integer.parseInt(id));
				log.debug("Get Book from dao -- > " + book);
			} catch (Exception e) {
				log.error("Book with id = " + id + " not found", e.getCause());
				rd.forward(request, response);
			}
		rd = request.getRequestDispatcher("viewbook.jsp");
		request.setAttribute("book", book);
		rd.forward(request, response);
		log.debug("Forvard to viewbook.jsp");
		log.trace("doPost finish");
	}
	
}
