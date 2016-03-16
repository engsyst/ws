package ua.nure.order.server;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.order.server.dao.Card;

/**
 * Servlet implementation class ListOrder
 */
public class ListCard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListCard() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
/*	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/listcard.jsp");
		HttpSession session = request.getSession(false);
		if (session == null) 
			throw new ServletException("Session not created");
		Card card = (Card) session.getAttribute("card");
		if (card == null)
			throw new ServletException("No card");
		rd.forward(request, response);
	}
*/
}
