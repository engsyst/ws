package ua.nure.itech.jaxws.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
<<<<<<< HEAD
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
=======
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
>>>>>>> branch 'master' of https://github.com/engsyst/ws.git
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * Web-service class ua.nure.itech.jaxws.service.HelloAnnotated
 * 
 * Remove all annotation and it parameters exclude @WebService to restore default wsdl generation
 * 
 * @author engsyst
 */

<<<<<<< HEAD
//@Path("rest")
=======
@Path("rest")
>>>>>>> branch 'master' of https://github.com/engsyst/ws.git
@WebService(portName="hello", serviceName="HelloAnnotated")
public class HelloAnnotated {
<<<<<<< HEAD
//	@GET
//	@Path("hello/{userName}")
=======
	@GET
	@Path("hello/{userName}")
>>>>>>> branch 'master' of https://github.com/engsyst/ws.git
	@WebMethod(operationName = "hello", action = "urn:HelloAnnotated")
	@RequestWrapper(className = "ua.nure.itech.jaxws.service.jaxws.HelloAnnotated", localName = "helloAnnotated", targetNamespace = "http://service.jaxws.itech.nure.ua/")
	@ResponseWrapper(className = "ua.nure.itech.jaxws.service.jaxws.HelloAnnotatedResponse", localName = "helloAnnotatedResponse", targetNamespace = "http://service.jaxws.itech.nure.ua/")
	@WebResult(name = "return")
	public String hello(
			@WebParam(name = "userName") 
<<<<<<< HEAD
//			@PathParam("userName")
=======
			@PathParam("userName")
>>>>>>> branch 'master' of https://github.com/engsyst/ws.git
			String name) {
		return "HelloAnnotated " + name + "!";
	}
}
