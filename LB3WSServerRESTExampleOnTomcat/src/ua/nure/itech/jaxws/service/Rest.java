package ua.nure.itech.jaxws.service;

import org.glassfish.jersey.server.ResourceConfig;

public class Rest extends ResourceConfig {
	public Rest() {
		packages("ua.nure.itech.jaxws.service");
	}
}
