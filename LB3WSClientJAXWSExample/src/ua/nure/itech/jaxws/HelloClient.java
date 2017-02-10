package ua.nure.itech.jaxws;

import java.net.MalformedURLException;
import java.net.URL;

import ua.nure.itech.jaxws.service.Hello;
import ua.nure.itech.jaxws.service.HelloService;

public class HelloClient {

	final static String url = "file:///D:/Develop/wsps/my/LB3WSClientJAXWSExample/HelloService.wsdl";
//	final static String url = "http://localhost:19090/HelloPort?wsdl";
	private static final int SIZE = 10;

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		// Create service object
		HelloService service = new HelloService(new URL(url));
		// Get client for concrete PORT of HelloService
		Hello client = service.getHelloPort();
		// call remote operation on service as local method
		for (int i = 0; i < SIZE; i++) {
			System.out.println(client.hello("User " + i));
			Thread.sleep(5);
		}
	}
}
