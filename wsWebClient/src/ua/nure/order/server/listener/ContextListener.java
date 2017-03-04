package ua.nure.order.server.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ua.nure.order.server.dao.DAOFactory;

/**
 * Application Lifecycle Listener implementation class ContextListener
 *
 */
@WebListener
public class ContextListener implements ServletContextListener {
	private static final Logger log = Logger.getLogger(ContextListener.class);

    /**
     * Default constructor. 
     */
    public ContextListener() {
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event)  { 
    	ServletContext ctx = event.getServletContext();
    	initLog4J(ctx);
    	ctx.setAttribute("UserDao", DAOFactory.getDAOFactory(DAOFactory.MYSQL).getUserDAO());
    	log.trace(ctx.getAttribute("UserDao").toString());
    	ctx.setAttribute("BookDao", DAOFactory.getDAOFactory(DAOFactory.MYSQL).getBookDAO());
    	log.trace(ctx.getAttribute("BookDao").toString());
    	ctx.setAttribute("OrderDao", DAOFactory.getDAOFactory(DAOFactory.MYSQL).getOrderDAO());
    	log.trace(ctx.getAttribute("OrderDao").toString());
    	
    }
	
	/**
	 * Initializes log4j framework.
	 * 
	 * @param servletContext
	 */
	private void initLog4J(ServletContext servletContext) {
		log("Log4J initialization started");
		try {
			String log4jConfigFilePath = "WEB-INF/log4j.properties";
			String log4jRealFilePath = servletContext.getRealPath(log4jConfigFilePath);
			log(log4jRealFilePath);
			PropertyConfigurator.configure(log4jRealFilePath);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		log("Log4J initialization finished");
	}

	private void log(String msg) {
		System.out.println("[ContextListener] " + msg);
	}
}
