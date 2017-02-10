package ua.nure.order.server;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.order.entity.order.Order;
import ua.nure.order.entity.order.OrderStatus;
import ua.nure.order.server.dao.DAOException;
import ua.nure.order.server.dao.OrderDAO;

/**
 * Get book from database by id and forward to edit book form
 * 
 * @param id
 *            in request
 * 
 * @author engsyst
 */
@WebServlet("/order/updateorderstatus")
public class UpdateOrderStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(UpdateOrderStatus.class);
	private OrderDAO orderService = null;

	public void init() {
		log.trace("init start");
		ServletContext ctx = getServletContext();
		orderService = (OrderDAO) ctx.getAttribute("OrderDao");
		log.debug("Get OrderDao form context --> " + orderService);
		log.trace("init finish");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("doPost start");
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
		log.debug("Get Order status --> " + status);
		try {
			id = Integer.parseInt(sId);
			log.debug("Get Order id --> " + id);
		} catch (NumberFormatException e) {
			log.error("Unknown id" + sId);
			throw new ServletException("Unknown id " + sId);
		}
		synchronized (this) {
			Order order = null;
			try {
				order = orderService.getOrderStatus(id);
				order.setStatus(status);
				orderService.updateStatus(id, status);
				log.debug("Order status updated --> " + order);
			} catch (DAOException e1) {
				request.getSession().setAttribute("error",
						"Не существует заказа с id: " + sId + " Или невозможно обновить его статус.");
				response.sendRedirect("orders.jsp");
				log.debug("Order not found --> " + sId);
				return;
			} catch (IllegalArgumentException e) {
				request.getSession().setAttribute("error",
						"Не допустимый статус. " + "Текущий: " + order.getStatus() + " Будущий: " + status);
				response.sendRedirect("orders.jsp");
				log.debug("Restricted status. Current --> " + order.getStatus() + ". Future --> " + status);
				return;
			}
		}
		String referer = request.getHeader("Referer");
		if (referer.isEmpty()) {
			log.debug("Redirect to orders.jsp");
			response.sendRedirect("orders.jsp");
		} else {
			log.debug("Redirect to referer --> " + referer);
			response.sendRedirect(referer);
			log.trace("doPost finish");
		}
	}

}
