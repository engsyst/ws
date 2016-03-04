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

import ua.nure.order.entity.user.User;
import ua.nure.order.server.dao.DAOException;
import ua.nure.order.server.dao.UserDao;
import ua.nure.order.shared.Hash;
import ua.nure.order.shared.UserValidator;

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
	private UserDao dao = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void init() {
    	ServletContext ctx = getServletContext();
    	dao = (UserDao) getServletContext().getAttribute("UserDao");
    	String param = ctx.getInitParameter("loginPattern");
    	if (param != null)
    		UserValidator.loginPattern = param;
    	param = ctx.getInitParameter("passPattern");
    	if (param != null)
    		UserValidator.passPattern = param;
    	param = ctx.getInitParameter("errLoginMsg");
    	if (param != null)
    		UserValidator.errLoginMsg = param;
    	param = ctx.getInitParameter("errPassMsg");
    	if (param != null)
    		UserValidator.errPassMsg = param;
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String login = request.getParameter("login");
		if (login == null || login.trim().length() == 0) {
			response.sendRedirect("login.jsp");
			return;
		}
		String pass = request.getParameter("password");
		User user = new User(login, pass);
		User u;
		Map<String, String> errors = new UserValidator<User>().validate(user);
		try {
			u = dao.getUser(login.trim());
			if (!u.getPass().equals(Hash.encode(pass))) {
				u.setPass(null);
				throw new DAOException("Invalid password");
			}
		} catch (DAOException e) {
			errors = new UserValidator<User>().validate(user);
			session.setAttribute("user", user);
			session.setAttribute("errors", errors);
			response.sendRedirect("login.jsp");
			return;
		} catch (NoSuchAlgorithmException e) {
			throw new ServletException(e.getMessage());
		}
		session.removeAttribute("errors");
		session.setAttribute("user", u);
		response.sendRedirect("list");
	}
}
