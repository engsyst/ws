package ua.nure.order.server;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.order.client.Cart;
import ua.nure.order.entity.book.Book;
import ua.nure.order.server.dao.BookDAO;
import ua.nure.order.server.dao.Card;
import ua.nure.order.server.dao.CardImpl;
import ua.nure.order.server.dao.DAOException;
import ua.nure.order.shared.CountValidator;

/**
 * Servlet implementation class BayBook
 */
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(AddToCart.class);
	private BookDAO bookService = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddToCart() {
		super();
	}

	@Override
	public void init() {
    	log.trace("init start");
    	bookService = (BookDAO) getServletContext().getAttribute("BookDao");
    	log.debug("Get BookDao from context -- > " + bookService);
    	log.trace("init finish");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		log.trace("doPost start");
		Map<String, String> err = new CountValidator().validate(request.getParameter("count"));
		HttpSession session = request.getSession(false);
		String sid = request.getParameter("tocart");
		Cart<Book> cart = (Cart<Book>) session.getAttribute("cart");
		if (cart == null) {
			log.debug("Cart not found. Create new.");
			cart = new Cart<Book>();
		}
		try {
			int count;
			try {
				count = Integer.parseInt(request.getParameter("count"));
				log.debug("Get parameter count --> " + count);
			} catch (Exception e) {
				count = 1;
				log.debug("Set count --> " + count);
			}
			int id = Integer.parseInt(sid);
			cart.add(bookService.getBook(id), count);
			log.debug("Book added to cart --> " + cart);
			request.setAttribute("info", "Вы купили книгу");
		} catch (NumberFormatException e) {
			log.debug("Wrong book id --> " + sid);
			request.setAttribute("error", "Неизвестная книга");
		} catch (DAOException e) {
			log.debug("Wrong number of books");
			request.setAttribute("error", "Не достаточно книг в наличии");
		}
		session.setAttribute("cart", cart);
		response.sendRedirect("list.jsp");
		log.debug("Redirect to list.jsp");
		log.trace("doPost finish");
	}

}
