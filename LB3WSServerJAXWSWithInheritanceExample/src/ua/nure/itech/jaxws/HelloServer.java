package ua.nure.itech.jaxws;

import java.util.Scanner;

import javax.xml.ws.Endpoint;

public class HelloServer{
	final static String address = "http://localhost:9090/hello";
	final static Object implementor = new ua.nure.itech.jaxws.service.Hello();
	final static String addressB = "http://localhost:9090/b";
	final static Object implementorB = new ua.nure.itech.jaxws.service.B();

    public static void main(String args[]) throws Exception { 
        Endpoint endpoint = Endpoint.publish(address, implementor);
        System.out.println("Server ready at " + address + " ..."); 
        Endpoint endpointB = Endpoint.publish(addressB, implementorB);
        System.out.println("Server ready at " + addressB + " ..."); 
        
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        sc.close();
        System.out.println("Server exiting");
        endpoint.stop();
        endpointB.stop();
    }
}
