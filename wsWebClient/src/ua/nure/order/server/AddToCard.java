package ua.nure.order.server;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.order.server.dao.BookDAO;
import ua.nure.order.server.dao.Card;
import ua.nure.order.server.dao.CardImpl;
import ua.nure.order.server.dao.DAOException;
import ua.nure.order.shared.CountValidator;

/**
 * Servlet implementation class BayBook
 */
public class AddToCard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static BookDAO bookService = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddToCard() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Map<String, String> err = new CountValidator().validate(request.getParameter("count"));
		HttpSession session = request.getSession();
		String sid = request.getParameter("id");
		String page = "ViewBook?id=" + sid;
		Card card = (Card) session.getAttribute("card");
		if (card == null)
			card = new CardImpl();
		if (err.size() == 0) {
			try {
				int count = Integer.parseInt(request.getParameter("count"));
				int id = Integer.parseInt(sid);
				bookService.updateBookCount(id, count);
				card.addBook(bookService.getBook(id), count);
				request.setAttribute("error", "Вы купили книгу");
				page = "SearchBook";
			} catch (NumberFormatException e) {
				request.setAttribute("error", "Не верное количество книг");
			} catch (DAOException e) {
				request.setAttribute("error", "Не достаточно книг в наличии");
			}
		}
		session.setAttribute("card", card);
		response.sendRedirect(page);
		return;
//		request.setAttribute("errors", err);
//		RequestDispatcher rd = request.getRequestDispatcher("ViewBook");
//		rd.forward(request, response);
	}

}
