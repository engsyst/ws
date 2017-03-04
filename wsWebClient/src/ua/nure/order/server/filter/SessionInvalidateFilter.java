package ua.nure.order.server.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * Forwards to login page when the current session is invalidate.
 */
public class SessionInvalidateFilter implements Filter {

	private static final Logger log = Logger
			.getLogger(SessionInvalidateFilter.class);

	public void destroy() {
		// do nothing
	}

	/**
	 * Forwards to login page when the current session is invalidate.
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		log.debug("Filter starts");

		// request is the HTTP request at this point
		// cast it to HttpServletRequest to get possibility to obtain the HTTP session
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		// if there is no a valid session
		if (httpServletRequest.getSession(false) == null) {
			// create the new session
			httpServletRequest.getSession(true);

			// and forward to the login page
			httpServletRequest.getRequestDispatcher("/login.jsp").forward(
					request, response);
			return;
		}

		// otherwise go further
		chain.doFilter(request, response);
		log.debug("Filter finished");
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// do nothing
	}

}
