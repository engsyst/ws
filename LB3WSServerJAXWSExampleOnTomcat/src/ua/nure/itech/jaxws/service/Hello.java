package ua.nure.itech.jaxws.service;

import javax.jws.WebService;
<<<<<<< HEAD
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
=======
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
>>>>>>> branch 'master' of https://github.com/engsyst/ws.git

/**
 * Web-service class ua.nure.itech.jaxws.service.Hello
 * 
 * @author engsyst
 */

<<<<<<< HEAD
//@Path("hello/{userName")
=======
@Path("hello/{userName")
>>>>>>> branch 'master' of https://github.com/engsyst/ws.git
@WebService
public class Hello {
<<<<<<< HEAD
//	@GET
=======
	@GET
>>>>>>> branch 'master' of https://github.com/engsyst/ws.git
	public String hello(String name) {
		return "Hello " + name + "!";
	}
}
