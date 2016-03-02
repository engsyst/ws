package ua.nure.order.server;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.order.server.dao.Card;
import ua.nure.order.server.dao.CardImpl;
import ua.nure.order.server.dao.DAOException;
import ua.nure.order.server.service.BookService;
import ua.nure.order.server.service.BookServiceClient;
import ua.nure.order.server.service.DAOException_Exception;
import ua.nure.order.shared.Validator;

/**
 * Servlet implementation class BayBook
 */
public class AddToCard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static BookService bookService = new BookServiceClient().getBookServicePort();

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
		HashSet<String> err = new HashSet<String>(Validator.validateBay(request.getParameter("count")).values());
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
			} catch (DAOException_Exception e) {
				request.setAttribute("error", "Не достаточно книг в наличии");
			} catch (DAOException e) {
				request.setAttribute("error", "Ошибка сервера. Повторите запрос позже.");
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
