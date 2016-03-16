package ua.nure.order.server;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.order.entity.book.Book;
import ua.nure.order.server.dao.BookDAO;
import ua.nure.order.server.dao.DAOException;
import ua.nure.order.shared.Pagination;

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

/*	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Collection<Book> books = null;
		HttpSession session = request.getSession();
		String search = request.getParameter("search");
		String sortOrder = request.getParameter("sortOrder");
		if (search != null) {
			Pagination pagination = new Pagination();
			pagination.setSearch(search);
			session.setAttribute("pagination", pagination);
		}
		Pagination pagination = (Pagination) session.getAttribute("pagination");
		try {
			pagination.setPage(Integer.parseInt(request.getParameter("page")));
		} catch (NumberFormatException en) {
			pagination.setPage(0);
		}
		if (sortOrder != null) {
			
		}
		try {
			books = bookService.listBooks(search);
			pagination.setTotal(books.size());
		} catch (DAOException ed) {
			throw new ServletException(ed.getCause().getMessage());
		}
		request.setAttribute("books", books);
		RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
		rd.forward(request, response);
	}
*/}
