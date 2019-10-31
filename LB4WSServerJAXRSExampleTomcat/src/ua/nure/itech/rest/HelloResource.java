package ua.nure.itech.rest;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("hello")
public class HelloResource {
    public static final String MESSAGE = "Hello World!";
    
    @GET
//    @Produces(MediaType.TEXT_PLAIN)
    public String getHello() {
    	return MESSAGE;
    }

    @POST
//    @Produces(MediaType.APPLICATION_JSON)
    public String hello(@FormParam("name") String name) {
    	System.out.println(name);
        return name;
    }

}
