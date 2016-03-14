package ua.nure.order.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.order.client.Cart;
import ua.nure.order.entity.book.Book;

/**
 * Servlet implementation class UpdateCart
 */
@WebServlet("/updatecart")
public class UpdateCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		HttpSession session = request.getSession(false);
		Cart<Book> cart = (Cart<Book>) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart<>();
			session.setAttribute("cart", cart);
			response.sendRedirect("listcart.jsp");
		}
		int id = 0;
		String uId = request.getParameter("update");
		String rId = request.getParameter("remove");
		int count = 0;
		if (uId != null && !uId.trim().equals("")) {
			try {
				id = Integer.parseInt(uId);
				count = Integer.parseInt(request.getParameter("count"));
				if (count <= 0) {
					session.setAttribute("error", "Не допустимое количество товара");
				} else {
					cart.put(cart.getItem(id), count);
					session.setAttribute("info", "Корзина обновлена");
				}
			} catch (Exception e) {
				throw new ServletException("Unknown product. Operation update. Id: " + uId);
			}
		}
		if (rId != null && !rId.trim().equals("")) {
			try {
				id = Integer.parseInt(rId);
				count = Integer.parseInt(request.getParameter("count"));
				cart.remove(cart.getItem(id));
				session.setAttribute("info", "Книга удалена из корзины");
			} catch (Exception e) {
				throw new ServletException("Unknown product. Operation remove. Id: " + rId);
			}
		}
		session.setAttribute("cart", cart);
		response.sendRedirect("listcart.jsp");
	}

}
