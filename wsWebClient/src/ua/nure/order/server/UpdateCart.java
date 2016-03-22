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
import ua.nure.order.client.ReqParam;
import ua.nure.order.entity.book.Book;

/**
 * Servlet implementation class UpdateCart
 */
@WebServlet("/updatecart")
public class UpdateCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(UpdateCart.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.trace("doPost start");
		HttpSession session = request.getSession(false);
		@SuppressWarnings("unchecked")
		Cart<Book> cart = (Cart<Book>) session.getAttribute("cart");
		log.debug("Get cart from session --> " + cart);
		if (cart == null) {
			cart = new Cart<>();
			session.setAttribute("cart", cart);
			response.sendRedirect("listcart.jsp");
			log.debug("New Cart created. Redirect to listcart.jsp");
		}
		int id = 0;
		String uId = request.getParameter("update");
		log.debug("Update id --> " + uId);
		String rId = request.getParameter("remove");
		log.debug("Remove id --> " + uId);
		int count = 0;
		if (uId != null && !uId.trim().equals("")) {
			try {
				id = Integer.parseInt(uId);
				String sCount = request.getParameter("count");
				log.debug("Count of books --> " + sCount);
				count = Integer.parseInt(sCount);
				if (count <= 0) {
					session.setAttribute("error", "Не допустимое количество товара");
				} else {
					cart.put(cart.getItem(id), count);
					session.setAttribute("info", "Корзина обновлена");
				}
				log.debug("Cart updated --> " + cart);
			} catch (Exception e) {
				log.error("Unknown product. Operation update. Id: " + uId);
				throw new ServletException("Unknown product. Operation update. Id: " + uId);
			}
		}
		if (rId != null && !rId.trim().equals("")) {
			try {
				id = Integer.parseInt(rId);
				count = Integer.parseInt(request.getParameter("count"));
				cart.remove(cart.getItem(id));
				session.setAttribute("info", "Книга удалена из корзины");
				log.debug("Cart updated. Removed book --> " + rId);
			} catch (Exception e) {
				log.error("Unknown product. Operation remove. Id: " + uId);
				throw new ServletException("Unknown product. Operation remove. Id: " + rId);
			}
		}
		session.setAttribute("cart", cart);
		log.debug("Cart to session --> " + cart);
		ReqParam params = new ReqParam();
		params.setParams(request.getParameterMap());
		params.removeParam("update").removeParam("remove").removeParam("count");
		log.debug("Params --> " + params);
		response.sendRedirect("listcart.jsp?" + params);
		log.debug("Redirect to listcart.jsp");
		log.trace("doPost finish");
	}

}
