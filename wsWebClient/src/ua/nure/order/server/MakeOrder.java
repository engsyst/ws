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
import ua.nure.order.entity.book.Book;
import ua.nure.order.entity.order.Order;
import ua.nure.order.server.dao.DAOException;
import ua.nure.order.server.dao.OrderDAO;
import ua.nure.order.server.filter.SecurityFilter;

/**
 * Servlet implementation class MakeOrder
 */
@WebServlet("/makeorder")
public class MakeOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(MakeOrder.class);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakeOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	private static OrderDAO orderService = null;
	
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
		
		Cart<Product> cart = (Cart<Product>) session.getAttribute("cart");
		log.debug("Get attribute cart --> " + cart);
		if (cart == null) {
			cart = new Cart<>();
			session.setAttribute("cart", cart);
			response.sendRedirect("listcart.jsp");
			log.debug("Cart is empty. Redirect to listcart.jsp");
			return;
		}
		try {
			int orderId = orderService.makeOrder(cart, 1);
			Order order = new Order(orderId, cart);
			session.setAttribute("order", order);
			log.debug("Order created --> " + order);
			cart = new Cart<>();
		} catch (DAOException e) {
			session.setAttribute("error", "Невозможно оформить заказ. "
					+ "Не достаточно книг в наличии.");
			response.sendRedirect("listcart.jsp");
			log.debug("Not enouth books. Redirect to listcart.jsp");
			return;
		}
		
		session.setAttribute("cart", cart);
		response.sendRedirect("vieworder.jsp");
		log.debug("Redirect to vieworder.jsp");
		log.trace("doPost finish");
	}

}
