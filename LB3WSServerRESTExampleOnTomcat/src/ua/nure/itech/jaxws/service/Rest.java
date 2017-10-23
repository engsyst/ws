package ua.nure.itech.jaxws.service;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * If this class can not compiled download jersey (https://jersey.github.io)
 * and put *.jars onto /WEB-INF/lib
 * 
 * @author engsyst
 *
 */
public class Rest extends ResourceConfig {
	public Rest() {
		packages("ua.nure.itech.jaxws.service");
	}
}
