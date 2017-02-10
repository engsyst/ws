package ua.nure.order.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.order.entity.order.Delivery;
import ua.nure.order.entity.user.User;
import ua.nure.order.server.dao.DAOException;
import ua.nure.order.server.dao.UserDAO;
import ua.nure.order.shared.Util;

/**
 * <p>
 * Request handler for Fill Delivery form. Try set delivery information into
 * User stored in the session.
 * 
 * <p>
 * Servlet mapping use {@link javax.servlet.annotation.WebServlet} annotation,
 * according to the Servlet specification 3.0. Also it can be do in web.xml as
 * below. Definition in web.xml has more priority then annotation.
 * 
 * <pre>
 * &lt;servlet&gt;
 *     &lt;description&gt;&lt;/description&gt;
 *     &lt;display-name&gt;FillDelivery&lt;/display-name&gt;
 *     &lt;servlet-name&gt;FillDelivery&lt;/servlet-name&gt;
 *     &lt;servlet-class&gt;ua.nure.order.server.FillDelivery&lt;/servlet-class&gt;
 * &lt;/servlet&gt;
 * &lt;servlet-mapping&gt;
 *     &lt;servlet-name&gt;FillDelivery&lt;/servlet-name&gt;
 *     &lt;url-pattern&gt;/filldelivery&lt;/url-pattern&gt;
 * &lt;/servlet-mapping&gt;
 * </pre>
 * 
 * @author engsyst
 */
@WebServlet("/filldelivery")
public class FillDelivery extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(FillDelivery.class);
	private UserDAO userService = null;
       
	@Override
	public void init() throws ServletException {
		super.init();
		userService = (UserDAO) getServletContext().getAttribute("UserDao");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.trace("Start");
		HttpSession session = request.getSession(false);
		Delivery delivery = (Delivery) session.getAttribute("delivery");
		if (delivery == null) {
			delivery = new Delivery();
			log.debug("New deliver created");
		}
		delivery.setDelivery(request);
		log.debug("Delivery --> " + delivery);
		session.setAttribute("delivery", delivery);
		log.debug("Set delivery to the session");
		String updateProfile = request.getParameter("updateprofale");
		if (updateProfile != null && updateProfile.equals("1")) {
			log.debug("Update profile");
			User user = (User) session.getAttribute("user");
			log.debug("User from session --> " + user);
			user.setName(Util.getOrElse(delivery.getName(), user.getName()));
			user.setPhone(Util.getOrElse(delivery.getPhone(), user.getPhone()));
			user.setEmail(Util.getOrElse(delivery.getEmail(), user.getEmail()));
			user.setAddress(Util.getOrElse(delivery.getAddress(), user.getAddress()));
			log.debug("User filled from delivery --> " + user);
			session.setAttribute("user", user);
			// Update user profile in database
			try {
				userService.updateUser(user);
				log.debug("User profile updated in database");
			} catch (DAOException e) {
				log.debug("User profile can not updated in database");
			}
		}
		String action = request.getParameter("continue");
		log.debug("Action --> " + action);
		if (action != null) {
			response.sendRedirect("list.jsp");
			log.debug("Redirect to list.jsp");
			log.trace("Finish");
			return;
		} 
		action = request.getParameter("makeorder");
		log.debug("Action --> " + action);
		if (action != null) {
			request.getRequestDispatcher("makeorder").forward(request, response);
			log.debug("Forvard to makeorder");
			log.trace("Finish");
			return;
		} 
		response.sendError(404, "Unknown action");
	}
}
