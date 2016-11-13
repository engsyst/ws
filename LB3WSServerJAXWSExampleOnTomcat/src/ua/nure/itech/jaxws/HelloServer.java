package ua.nure.itech.jaxws;

import java.util.Scanner;

import javax.xml.ws.Endpoint;

public class HelloServer{
	final static String address = "http://localhost:9090/hello";
	final static Object implementor = new ua.nure.itech.jaxws.service.Hello();
	final static String aAddress = "http://localhost:9090/ahello";
	final static Object aImplementor = new ua.nure.itech.jaxws.service.HelloAnnotated();

    public static void main(String args[]) throws Exception { 
        Endpoint.publish(address, implementor);
        System.out.println("Server ready at " + address + " ..."); 
        Endpoint.publish(aAddress, aImplementor);
        System.out.println("Server ready at " + aAddress + " ..."); 
        
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        sc.close();
//        Thread.sleep(3 * 60 * 1000); // wait before exit
        System.out.println("Server exiting");
        System.exit(0);
    }
}
