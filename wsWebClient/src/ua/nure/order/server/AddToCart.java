package ua.nure.order.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.order.client.Cart;
import ua.nure.order.client.ReqParam;
import ua.nure.order.entity.book.Book;
import ua.nure.order.server.dao.BookDAO;
import ua.nure.order.server.dao.DAOException;

/**
 * Add product to the {@link Cart}. Cart must be in session as Attribute 'cart'.
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		log.trace("doPost start");
		HttpSession session = request.getSession(false);
		log.debug("Params --> " + request.getParameterMap());
		
		String sid = request.getParameter("tocart");
		@SuppressWarnings("unchecked")
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
				if (count < 0) count = 1;
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
		// For return to the refered page
		ReqParam params = new ReqParam();
		params.setParams(request.getParameterMap());
		params.removeParam("tocart");
		params.removeParam("count");
		response.sendRedirect("list.jsp?" + params);
		log.debug("Params --> " + params);
		log.debug("Redirect to list.jsp");
		log.trace("doPost finish");
	}

}
