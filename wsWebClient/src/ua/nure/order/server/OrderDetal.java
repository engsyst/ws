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
 * Get order from database by id and forward to view order page
 * @param id in the request
 * 
 * @author engsyst
 *
 */
@WebServlet("/order/orderdetal")
public class OrderDetal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDAO orderService = null;

	public void init() {
		ServletContext ctx = getServletContext();
		orderService = (OrderDAO) ctx.getAttribute("OrderDao");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Cache-control", "no-cache");
		try {
			Order order = orderService.getOrderDetal(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("order", order);
		} catch (NumberFormatException | DAOException e) {
			request.setAttribute("error", "Order not found. Id: " + request.getParameter("id"));
		}
		request.getRequestDispatcher("orderdetal.jsp").forward(request, response);
	}
}
