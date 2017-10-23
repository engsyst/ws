package ua.nure.itech.jaxws.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface Messenger {
	@WebMethod
	String echo(@WebParam(name="msg") String msg);
}
