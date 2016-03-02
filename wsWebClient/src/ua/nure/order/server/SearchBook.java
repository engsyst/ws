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
import ua.nure.order.server.service.BookServiceClient;

/**
 * Servlet implementation class SearchBook
 */
public class SearchBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static BookService bookService = new BookServiceClient().getBookServicePort();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Collection<Book> books = null;
		if (request.getParameterMap().containsKey("title")) {
			books = bookService.listBooks(request.getParameter("title"));
		} else {
			books = bookService.listBooks(null);
		}
		request.setAttribute("books", books);
		RequestDispatcher rd = request.getRequestDispatcher("/list.jsp");
		rd.forward(request, response);
	}
}
