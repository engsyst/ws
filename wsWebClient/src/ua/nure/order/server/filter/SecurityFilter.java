package ua.nure.order.server.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.order.entity.user.Role;
import ua.nure.order.entity.user.User;

/**
 * Servlet Filter implementation class SecurityFilter
 */
@WebFilter(
		dispatcherTypes = {DispatcherType.REQUEST }, 
		urlPatterns = { "/*" }, 
		initParams = { 
				@WebInitParam(name = "client", value = "/profile"),
				@WebInitParam(name = "admin", value = "/order/*,/profile*"),
				@WebInitParam(name = "userAttribute", value = "user"),
		})
public class SecurityFilter implements Filter {
	private static final Logger log = Logger.getLogger(SecurityFilter.class);
	
	private static LinkedHashMap<String, String[]> resourses = new LinkedHashMap<>();
	private static String ua = null;
	private static String contextPath = null;
	
	private static final Comparator<String> pathComparator = new Comparator<String>() {
	
		@Override
		public int compare(String o1, String o2) {
			return Integer.compare(o1.split("/").length, o1.split("/").length) ;
		}
	};

    /**
     * Default constructor. 
     */
    public SecurityFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		log.trace("SecurityFilter destroy start");
		// do nothing
		log.trace("SecurityFilter destroy finish");
	}
	
	private boolean accept(Role role, String res) {
		
		String sRole = role == null ? null : role.toString();
		
		// resource unrestricted
		if (!contains(res)) {
			log.debug("Accept access to unrestricted resource --> " + res);
			return true;
		} 
		// unknown user go to the restricted resource
		if (!resourses.containsKey(sRole) ) {
			log.debug("Deny access for Unknown user to the restricted resource --> " + res);
			return false;
		} 
		// known user go to the restricted resource
		if (contains(res, resourses.get(sRole))) {
			log.debug("Accept access for Known user to the restricted resource --> " + res);
			return true;
		}
		return false;
	}
	
	private boolean contains(String res) {
		for (Entry<String, String[]> e : resourses.entrySet()) {
			for (String r : e.getValue()) {
				log.debug("Matches --> " + r + " Resource --> " + res);
				if (res.matches(r)) {
					log.debug("Found matches --> " + r);
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean contains(String res, String[] resourses) {
		for (String r : resourses) {
			if (res.matches(r)) {
				log.debug("Found matches --> " + res + " in --> " + resourses);
				return true;
			}
		}
		return false;
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// place your code here
		log.trace("SecurityFilter filter start");

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		User user = null;
		if (session.isNew()) {
			user = new User();
			session.setAttribute(ua, user);
		}
		user = (User) session.getAttribute(ua);
		log.debug("User --> " + user);
		
		String contextPath = req.getServletContext().getContextPath();
		log.debug("ContextPath --> " + contextPath);
		
		String resourse = req.getRequestURI().substring(contextPath.length());
		log.debug("Resourse --> " + resourse);
		
		if (!accept(user.getRole(), resourse)) {
			((HttpServletResponse) response).sendRedirect(req.getContextPath() + "/login.jsp");
			return;
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
		
		log.trace("SecurityFilter finish");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		log.trace("SecurityFilter init start");
		contextPath = fConfig.getServletContext().getContextPath();
		log.debug("contextPath -->" + contextPath);
		Enumeration<String> roles = fConfig.getInitParameterNames();
		while (roles.hasMoreElements()) {
			String role = (String) roles.nextElement();
			if (role.equals("userAttribute")) {
				ua = fConfig.getInitParameter(role);
				continue;
			}
			String[] path = fConfig.getInitParameter(role).split(",");
			Arrays.sort(path);
			for (int i = 0; i < path.length; i++) {
				path[i] = path[i].replace("*", ".*");
			}
			resourses.put(role, path);
			log.debug("deny --> " + role + Arrays.toString(path));
		}
		log.trace("SecurityFilter init finish");
	}

}
