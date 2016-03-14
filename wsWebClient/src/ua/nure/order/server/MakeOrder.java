package ua.nure.order.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.order.client.Cart;
import ua.nure.order.entity.Product;
import ua.nure.order.entity.book.Book;
import ua.nure.order.entity.order.Order;
import ua.nure.order.server.dao.DAOException;
import ua.nure.order.server.dao.OrderDAO;

/**
 * Servlet implementation class MakeOrder
 */
@WebServlet("/makeorder")
public class MakeOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		orderService = (OrderDAO) getServletContext().getAttribute("OrderDao");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Cart<Product> cart = (Cart<Product>) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart<>();
			session.setAttribute("cart", cart);
			session.setAttribute("error", "");
			response.sendRedirect("listcart.jsp");
			return;
		}
		try {
			int orderId = orderService.makeOrder(cart, 1);
			Order order = new Order(orderId, cart);
			session.setAttribute("order", order);
			cart = new Cart<>();
		} catch (DAOException e) {
			session.setAttribute("error", "Невозможно оформить заказ. "
					+ "Не достаточно книг в наличии.");
			response.sendRedirect("listcart.jsp");
			return;
		}
		
		session.setAttribute("cart", cart);
		response.sendRedirect("vieworder.jsp");
	}

}
