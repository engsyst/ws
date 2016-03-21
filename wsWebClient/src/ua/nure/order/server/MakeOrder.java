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
import ua.nure.order.client.Delivery;
import ua.nure.order.entity.Product;
import ua.nure.order.entity.book.Book;
import ua.nure.order.entity.order.Order;
import ua.nure.order.entity.user.User;
import ua.nure.order.server.dao.DAOException;
import ua.nure.order.server.dao.OrderDAO;
import ua.nure.order.server.dao.UserDAO;
import ua.nure.order.server.filter.SecurityFilter;
import ua.nure.order.shared.Util;

/**
 * Servlet implementation class MakeOrder
 */
@WebServlet("/makeorder")
public class MakeOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(MakeOrder.class);
	private static Integer unregUserId;
	private static OrderDAO orderService = null;
	private static UserDAO userService = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakeOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	@Override
	public void init() {
		log.trace("Init start");
		orderService = (OrderDAO) getServletContext().getAttribute("OrderDao");
		userService = (UserDAO) getServletContext().getAttribute("UserDao");
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
		Delivery delivery = new Delivery(request);
		log.debug("Get Delivery from request --> " + delivery);
		session.setAttribute("delivery", delivery);
		log.debug("Set Delivery to the session.");
		String updateProfile = request.getParameter("updateprofile");
		if (updateProfile != null && updateProfile.equals("1")) {
			log.debug("Update profile");
			User user = (User) session.getAttribute("user");
			log.debug("User from session --> " + user);
			user.setName(Util.getOrElse(delivery.getName(), user.getName()));
			user.setPhone(Util.getOrElse(delivery.getPhone(), user.getPhone()));
			user.setEmail(Util.getOrElse(delivery.getEmail(), user.getEmail()));
			user.setAddress(Util.getOrElse(delivery.getAddress(), user.getAddress()));
			log.debug("User filled from delivery --> " + user);
			session.setAttribute("user", user);
		}
		if (!delivery.validate()) {
			response.sendRedirect("filldelivery.jsp");
			log.debug("Delivery not valid. Redirect to filldelivery.jsp");
			return;
		}
		User user = (User) session.getAttribute("user");
		log.debug("Get User from session --> " + user);
		Integer userId = null;
		if (user != null)
			userId = user.getId();
		try {
			log.debug("Try make order.");
			int orderId = orderService.makeOrder(cart, delivery, userId);
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
