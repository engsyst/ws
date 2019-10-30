package ua.nure.itech.jaxws.service;

import java.net.MalformedURLException;
import java.net.URL;

import ua.nure.itech.jaxws.service.inheritance.Hello;
import ua.nure.itech.jaxws.service.inheritance.Hello_Service;

public class HelloClient {

	// this string must be equal server url
	final static String url = "http://localhost:9090/hello?wsdl";
	private static final int SIZE = 10;

	public static void main(String[] args) throws MalformedURLException {
		// uncomment two lines above if you want log messages to the System.out on the client side
//		System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true");
//		System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");		

		// Create service object
		Hello_Service service = new Hello_Service(new URL(url));

		// Get client for concrete PORT of HelloService
		Hello client = service.getHelloImplPort();

		// call remote operation on service as local method
		for (int i = 0; i < SIZE; i++) {
			System.out.println(client.helloUser("User " + i));
		}
	}
}
