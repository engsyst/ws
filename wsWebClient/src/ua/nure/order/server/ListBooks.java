package ua.nure.order.server;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xml.internal.security.Init;

import ua.nure.order.entity.book.Book;
import ua.nure.order.server.dao.BookDAO;

/**
 * Servlet implementation class SearchBook
 */
public class ListBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static BookDAO bookService = null;
	
	@Override
	public void init() {
		bookService = (BookDAO) getServletContext().getAttribute("BookDao");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Collection<Book> books = null;
		String search = request.getParameter("search");
		books = bookService.listBooks(search);
		request.setAttribute("books", books);
		RequestDispatcher rd = request.getRequestDispatcher("/list.jsp");
		rd.forward(request, response);
	}
}
