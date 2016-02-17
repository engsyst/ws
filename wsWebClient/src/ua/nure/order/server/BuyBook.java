package ua.nure.order.server;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.order.server.dao.Card;
import ua.nure.order.server.dao.CardImpl;
import ua.nure.order.server.service.OrderService;
import ua.nure.order.shared.Validator;

/**
 * Servlet implementation class BayBook
 */
public class BuyBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BuyBook() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HashSet<String> err = new HashSet<String>(Validator.validateBay(request.getParameter("count")).values());
		HttpSession session = request.getSession(true);
		Card card = (Card) session.getAttribute("card");
		if (card == null)
			card = new CardImpl();
		if (err.size() == 0) {
			OrderService.getInstance().buyBook(card,
					Integer.parseInt(request.getParameter("id")), 
					Integer.parseInt(request.getParameter("count")));
			request.setAttribute("error", "Вы купили книгу");
			session.setAttribute("card", card);
//			response.sendRedirect("SearchBook");
//			return;
		}
		request.setAttribute("errors", err);
		RequestDispatcher rd = request.getRequestDispatcher("ViewBook");
		rd.forward(request, response);
	}

}
