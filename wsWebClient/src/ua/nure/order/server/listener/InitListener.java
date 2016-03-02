package ua.nure.order.server.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import ua.nure.order.server.dao.DAOFactory;

/**
 * Application Lifecycle Listener implementation class InitListener
 *
 */
@WebListener
public class InitListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public InitListener() {
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
    	ctx.setAttribute("UserDao", DAOFactory.getDAOFactory(DAOFactory.MYSQL).getUserDAO());
    }
	
}
