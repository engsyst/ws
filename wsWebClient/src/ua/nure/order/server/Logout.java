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

/**
 * Servlet implementation class Login
 */
@WebServlet(urlPatterns = { "/logout" })
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(Logout.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void init() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	doPost(request, response);
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		log.trace("doPost start");
		HttpSession session = request.getSession();
		session.invalidate();
		log.trace("session invalidated");
		response.sendRedirect("list.jsp");
		log.trace("doPost finish");
	}
}
