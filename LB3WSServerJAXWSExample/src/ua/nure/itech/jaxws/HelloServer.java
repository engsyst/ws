package ua.nure.itech.jaxws;

import java.util.Scanner;

import javax.xml.ws.Endpoint;

public class HelloServer{
	final static String address = "http://localhost:9090/hello";
	final static Object implementor = new ua.nure.itech.jaxws.service.Hello();

//	final static String address = "http://localhost:9090/ahello";
//	final static Object implementor = new ua.nure.itech.jaxws.service.HelloAnnotated();

    public static void main(String args[]) throws Exception { 
		// uncomment two lines above if you want log messages to the System.out on the server side
//    	System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dump", "true");
//    	System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");
    	
        Endpoint.publish(address, implementor);
        System.out.println("Server ready at " + address + "?wsdl ...");
        
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        sc.close();
        System.out.println("Server exiting");
        System.exit(0);
    }
}
