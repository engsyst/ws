package ua.nure.order.server;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.order.entity.user.Role;
import ua.nure.order.entity.user.User;
import ua.nure.order.server.dao.DAOException;
import ua.nure.order.server.dao.UserDAO;
import ua.nure.order.server.listener.ContextListener;
import ua.nure.order.shared.Hash;
import ua.nure.order.shared.UserValidator;
import ua.nure.order.shared.Util;

/**
 * Servlet implementation class Login
 */
@WebServlet(
		urlPatterns = { "/login" }, 
		initParams = { 
				@WebInitParam(name = "loginPattern", value = "[a-zA-Z0-9\\-_.]{4,20}"), 
				@WebInitParam(name = "passPattern", value = ""),
				@WebInitParam(name = "errLoginMsg", value = "Login must be more then 4 symbols"), 
				@WebInitParam(name = "errPassMsg", value = "Pass must be more then 4 symbols"),
		})
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(Login.class);
	private UserDAO dao = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void init() {
    	log.trace("Login init start");
    	ServletContext ctx = getServletContext();
    	dao = (UserDAO) getServletContext().getAttribute("UserDao");
    	String param = ctx.getInitParameter("loginPattern");
    	log.trace("loginPattern --> " + param);
    	if (param != null)
    		UserValidator.loginPattern = param;
    	param = ctx.getInitParameter("passPattern");
    	log.trace("passPattern --> " + param);
    	if (param != null)
    		UserValidator.passPattern = param;
    	param = ctx.getInitParameter("errLoginMsg");
    	log.trace("errLoginMsg --> " + param);
    	if (param != null)
    		UserValidator.errLoginMsg = param;
    	param = ctx.getInitParameter("errPassMsg");
    	log.trace("errPassMsg --> " + param);
    	if (param != null)
    		UserValidator.errPassMsg = param;
    	log.trace("Login init finish");
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		log.trace("doPost start");
		HttpSession session = request.getSession();
		String login = request.getParameter("login");
		log.debug("login --> " + login);
		
		if (login == null || login.trim().length() == 0) {
			response.sendRedirect("login.jsp");
			return;
		}
		String pass = request.getParameter("password");
		// TODO remove in PRODUCTION
		log.debug("password --> " + pass);
		User user = new User(login, Util.getOrElse(pass, ""));
		Map<String, String> errors = new UserValidator<User>().validate(user);
		try {
			user = dao.getUser(login.trim());
			String hash = Hash.encode(pass);
			if (!user.getPass().equals(hash)) {
				log.debug("password incorrect --> " + hash + " | " + user.getPass());
				user.setPass(null);
				throw new DAOException("Invalid password");
			}
		} catch (DAOException e) {
			log.debug("DAOException", e);
		} catch (NoSuchAlgorithmException e) {
			throw new ServletException(e.getMessage());
		}
		session.removeAttribute("errors");
		session.setAttribute("user", user);
		log.debug("Set session attribute user --> " + user);
		if (user.getRole().equals(Role.client)) {
			String referer = request.getHeader("Referer");
			log.debug("Redirect to --> list.jsp");
			response.sendRedirect("list.jsp");
		} else {
			log.debug("Redirect to --> orders.jsp");
			response.sendRedirect("orders.jsp");
		}
	}
}
