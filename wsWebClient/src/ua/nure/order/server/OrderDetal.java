package ua.nure.order.server;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.order.entity.order.Order;
import ua.nure.order.server.dao.DAOException;
import ua.nure.order.server.dao.OrderDAO;

/**
 * Servlet implementation class OrderDetal
 */
@WebServlet("/order/detal")
public class OrderDetal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDAO orderService = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderDetal() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() {
		ServletContext ctx = getServletContext();
		orderService = (OrderDAO) ctx.getAttribute("OrderDao");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Order order = orderService.getOrder(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("order", order);
		} catch (NumberFormatException | DAOException e) {
			request.setAttribute("error", "Order not found. Id: " + request.getParameter("id"));
		}
		request.getRequestDispatcher("oderdetal.jsp");
	}
}
