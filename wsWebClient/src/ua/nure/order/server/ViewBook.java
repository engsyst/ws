package ua.nure.order.server;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.order.entity.book.Book;
import ua.nure.order.server.dao.BookDAO;
import ua.nure.order.server.dao.UserDAO;

/**
 * Servlet implementation class ViewBook
 */
public class ViewBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDAO bookService = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewBook() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
	public void init() {
    	bookService = (BookDAO) getServletContext().getAttribute("BookDao");
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
		String id = request.getParameter("id");
		Book book = null;
		if (id != null)
			try {
				book = bookService.getBook(Integer.parseInt(id));
			} catch (Exception e) {
				System.err.println("Book with id = " + id + " not found");
				rd.forward(request, response);
			}
		rd = request.getRequestDispatcher("/viewbook.jsp");
		request.setAttribute("book", book);
		rd.forward(request, response);
	}
	
}
