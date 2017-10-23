package ua.nure.itech.jaxws.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Web-service class ua.nure.itech.jaxws.service.Hello
 * 
 * @author engsyst
 */
@WebService(endpointInterface="ua.nure.itech.jaxws.service.Messenger")
public class Hello {

	public String hello(String name) {
		return "Hello " + name + "!";
	}

	public String echo(String msg) {
		return msg;
	}
}
