package ua.nure.itech.jaxws;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.ws.Holder;

import ua.nure.itech.jaxws.service.ExchangeException_Exception;
import ua.nure.itech.jaxws.service.HelloMessenger;
import ua.nure.itech.jaxws.service.HelloService;

public class HelloClient {

	/**
	 * For console clients only
	 */
	final static String url = "file:///" + new File("").getAbsolutePath() + "/META-INF/wsdl/hello.wsdl";
//	final static String url = "http://localhost:19090/HelloPort?wsdl";
	private static final int SIZE = 10;

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		// Create service object
		HelloService helloSEI = new HelloService(new URL(url));
		// Get client for concrete PORT of HelloService
		HelloMessenger helloMessengerImpl = helloSEI.getHelloPort();
		// call remote operation on service as local method
		Holder<String> currency = new Holder<String>("Taler");
		try {
			System.out.println(helloMessengerImpl.exchange(5, "USD", currency) + " " + currency.value);
		} catch (ExchangeException_Exception e) {
			System.out.println(e.getMessage());
		}
//		for (int i = 0; i < SIZE; i++) {
//			System.out.println(helloMessengerImpl.hello("User " + i));
//			Thread.sleep(5);
//		}
	}
}
