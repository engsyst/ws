package ua.nure.order.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.order.client.Cart;
import ua.nure.order.entity.Product;
import ua.nure.order.entity.order.Delivery;
import ua.nure.order.entity.order.Order;
import ua.nure.order.entity.user.User;
import ua.nure.order.server.dao.DAOException;
import ua.nure.order.server.dao.OrderDAO;
import ua.nure.order.shared.Util;

/**
 * Servlet implementation class MakeOrder
 */
@WebServlet("/makeorder")
public class MakeOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(MakeOrder.class);
	private static OrderDAO orderService = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakeOrder() {
        super();
    }

	
	@Override
	public void init() {
		log.trace("Init start");
		orderService = (OrderDAO) getServletContext().getAttribute("OrderDao");
		log.debug("Get order service --> " + orderService);
		log.trace("Init finish");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.trace("doPost start");
		
		HttpSession session = request.getSession(false);
		log.debug("Get session --> " + session);
		
		@SuppressWarnings("unchecked")
		Cart<Product> cart = (Cart<Product>) session.getAttribute("cart");
		log.debug("Get attribute cart --> " + cart);
		if (cart == null) {
			cart = new Cart<>();
			session.setAttribute("cart", cart);
			response.sendRedirect("listcart.jsp");
			log.debug("Cart is empty. Redirect to listcart.jsp");
			return;
		} 
		Delivery delivery = (Delivery) session.getAttribute("delivery");
		log.debug("Get Delivery from session --> " + delivery);
		if (delivery == null) {
			response.sendRedirect("filldelivery.jsp");
			log.debug("Delivery is empty. Redirect to filldelivery.jsp");
			return;
		}
		if (!delivery.validate()) {
			session.setAttribute("error", "Некорректно заполнены поля формы.");
			response.sendRedirect("filldelivery.jsp");
			log.debug("Delivery invalid. Redirect to filldelivery.jsp");
			return;
		}
		User user = (User) session.getAttribute("user");
		log.debug("Get User from session --> " + user);
		Integer userId = null;
		if (user != null)
			userId = user.getId();
		try {
			log.debug("Try make order.");
			int orderId = orderService.makeOrder(userId, cart, delivery);
			Order order = new Order(orderId, cart, delivery);
			session.setAttribute("order", order);
			log.debug("Order created --> " + order);
			cart = new Cart<>();
			log.debug("Create new cart --> " + order);
			session.setAttribute("cart", cart);
		} catch (DAOException e) {
			session.setAttribute("error", "Невозможно оформить заказ. "
					+ "Не достаточно книг в наличии.");
			response.sendRedirect("listcart.jsp");
			log.debug("Not enouth books. Redirect to listcart.jsp");
			return;
		}
		
		response.sendRedirect("vieworder.jsp");
		log.debug("Redirect to vieworder.jsp");
		log.trace("doPost finish");
	}

}
