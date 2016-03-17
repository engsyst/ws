package ua.nure.order.server;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.order.entity.user.Role;
import ua.nure.order.entity.user.User;
import ua.nure.order.server.dao.UserDAO;
import ua.nure.order.shared.Hash;
import ua.nure.order.shared.UserValidator;
import ua.nure.order.shared.Util;

/**
 * Servlet implementation class Login
 */
/*@WebServlet(
		urlPatterns = { "/login" }, 
		initParams = { 
				@WebInitParam(name = "loginPattern", value = ".{1,20}"), 
				@WebInitParam(name = "errLoginMsg", value = "Login or password incorrect"), 
		})*/
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(Login.class);
	private static UserValidator<User> validator = new UserValidator<>();
	private UserDAO dao = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }
    
    @Override
	public void init() {
    	log.trace("Login init start");
    	ServletContext ctx = getServletContext();
    	dao = (UserDAO) getServletContext().getAttribute("UserDao");
    	ServletConfig conf = getServletConfig();
    	String param = conf.getInitParameter("loginPattern");
    	log.debug("loginPattern --> " + param);
    	if (param != null)
    		validator.loginPattern = param;
    	param = ctx.getInitParameter("passPattern");
    	log.debug("passPattern --> " + param);
    	if (param != null)
    		validator.passPattern = param;
    	param = ctx.getInitParameter("errLoginMsg");
    	log.debug("errLoginMsg --> " + param);
    	if (param != null)
    		validator.errLoginMsg = param;
    	param = ctx.getInitParameter("errPassMsg");
    	log.debug("errPassMsg --> " + param);
    	if (param != null)
    		validator.errPassMsg = param;
    	log.trace("Login init finish");
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		log.trace("doPost start");
		HttpSession session = request.getSession();
		String login = Util.getOrElse(request.getParameter("login"), "").trim();
		log.debug("login --> " + login);

		String pass = Util.getOrElse(request.getParameter("password"), "");
		// TODO remove in PRODUCTION
		log.debug("password --> " + pass);
		
		User user = new User(login, pass);
		Map<String, String> errors = validator.validate(user);
		if (!errors.isEmpty()) {
			goBack(request, response, user, errors);
			return;
		}
		try {
			User u = dao.getUser(login.trim());
			if (u == null) {
				errors.put("login", "Login or password incorrect");
				goBack(request, response, user, errors);
				return;
			}
			String hash = Hash.encode(pass);
			if (!hash.equals(u.getPass())) {
				log.debug("password incorrect --> " + hash + " | " + u.getPass());
				u.setPass(null);
				goBack(request, response, user, errors);
				return;
			}
			user = u;
		} catch (Exception e) {
			log.debug("DAOException", e);
			throw new ServletException(e.getMessage());
		} 
		session.removeAttribute("errors");
		session.setAttribute("user", user);
		log.debug("Set session attribute user --> " + user);
		if (user.getRole().equals(Role.client)) {
			log.debug("Redirect to --> list.jsp");
			response.sendRedirect("list.jsp");
		} else {
			log.debug("Redirect to --> orders.jsp");
			response.sendRedirect("order/orders.jsp");
		}
	}
	
	private void goBack(HttpServletRequest request, HttpServletResponse response, User user, Map<String, String> errors)
			throws IOException {
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		session.setAttribute("errors", errors);
		log.debug("Redirect --> login.jsp");
		response.sendRedirect("login.jsp");
	}
}
