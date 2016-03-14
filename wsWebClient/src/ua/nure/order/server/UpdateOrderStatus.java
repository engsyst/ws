package ua.nure.order.server;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.order.entity.order.Order;
import ua.nure.order.entity.order.OrderStatus;
import ua.nure.order.server.dao.DAOException;
import ua.nure.order.server.dao.OrderDAO;

/**
 * Servlet implementation class UpdateOrderStatus
 */
@WebServlet("/updateorderstatus")
public class UpdateOrderStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDAO orderService = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateOrderStatus() {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String sId = null;
		int id = 0;
		OrderStatus status = null;
		for (OrderStatus s : OrderStatus.values()) {
			sId = request.getParameter(s.toString());
			if (sId != null && !sId.isEmpty()) {
				status = s;
				break;
			}
		}
		try {
			id = Integer.parseInt(sId);
		} catch (NumberFormatException e) {
			throw new ServletException("Unknown id");
		}
		synchronized (this) {
			Order order = null;
			try {
				order = orderService.getOrder(id);
				order.setStatus(status);
				orderService.updateStatus(id, status);
			} catch (DAOException e1) {
				request.getSession().setAttribute("error", "Не существует заказа с id: " + sId 
						+ " Или невозможно обновить его статус.");
				response.sendRedirect("orders.jsp");
				return;
			} catch (IllegalArgumentException e) {
				request.getSession().setAttribute("error", "Не допустимый статус. "
						+ "Текущий: " + order.getStatus() + " Будущий: " + status);
				response.sendRedirect("orders.jsp");
				return;
			}
		}
		response.sendRedirect("orders.jsp");
		
	}

}
