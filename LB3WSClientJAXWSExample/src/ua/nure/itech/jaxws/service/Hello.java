
package ua.nure.itech.jaxws.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Hello", targetNamespace = "http://service.jaxws.itech.nure.ua/")
@XmlSeeAlso({ ObjectFactory.class })
public interface Hello {

	/**
	 * 
	 * @param arg0
	 * @return returns java.lang.String
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "hello", targetNamespace = "http://service.jaxws.itech.nure.ua/", className = "ua.nure.itech.jaxws.service.Hello_Type")
	@ResponseWrapper(localName = "helloResponse", targetNamespace = "http://service.jaxws.itech.nure.ua/", className = "ua.nure.itech.jaxws.service.HelloResponse")
	@Action(input = "http://service.jaxws.itech.nure.ua/Hello/helloRequest", output = "http://service.jaxws.itech.nure.ua/Hello/helloResponse")
	public String hello(@WebParam(name = "arg0", targetNamespace = "") String arg0);

}
