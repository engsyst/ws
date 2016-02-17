package ua.nure.order.server;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.order.entity.book.Book;
import ua.nure.order.server.service.BookService;

/**
 * Servlet implementation class SearchBook
 */
public class SearchBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static BookService bookService = new BookServiceDAOImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchBook() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Collection<Book> books = null;
		RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
		if (request.getParameterMap().containsKey("title")) {
			books = bookService.searchByTitle(request.getParameter("title"));
		} else if (request.getParameterMap().containsKey("author")) {
			books = bookService.searchByAuthor(request.getParameter("author"));
		} else {
			books = bookService.searchByAuthor(null);
		}
		request.setAttribute("books", books);
		rd = request.getRequestDispatcher("list.jsp");
		rd.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Collection<Book> books = bookService.listBooks();
		request.setAttribute("books", books);
		RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
		rd.forward(request, response);
	}
	
}
