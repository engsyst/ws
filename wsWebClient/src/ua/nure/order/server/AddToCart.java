package ua.nure.order.server;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.order.client.Cart;
import ua.nure.order.entity.book.Book;
import ua.nure.order.server.dao.BookDAO;
import ua.nure.order.server.dao.Card;
import ua.nure.order.server.dao.CardImpl;
import ua.nure.order.server.dao.DAOException;
import ua.nure.order.shared.CountValidator;

/**
 * Servlet implementation class BayBook
 */
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static BookDAO bookService = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddToCart() {
		super();
	}

	@Override
	public void init() {
		bookService = (BookDAO) getServletContext().getAttribute("BookDao");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Map<String, String> err = new CountValidator().validate(request.getParameter("count"));
		HttpSession session = request.getSession();
		String sid = request.getParameter("tocart");
		Cart<Book> cart = (Cart<Book>) session.getAttribute("cart");
		if (cart == null)
			cart = new Cart<Book>();
		try {
			int count;
			try {
				count = Integer.parseInt(request.getParameter("count"));
			} catch (Exception e) {
				count = 1;
			}
			int id = Integer.parseInt(sid);
			cart.add(bookService.getBook(id), 1);
			request.setAttribute("info", "Вы купили книгу");
		} catch (NumberFormatException e) {
			request.setAttribute("error", "Неверное количество книг");
		} catch (DAOException e) {
			request.setAttribute("error", "Не достаточно книг в наличии");
		}
		session.setAttribute("cart", cart);
		response.sendRedirect("list.jsp");
		return;
//		request.setAttribute("errors", err);
//		RequestDispatcher rd = request.getRequestDispatcher("ViewBook");
//		rd.forward(request, response);
	}

}
