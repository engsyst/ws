package ua.nure.order.server;

import java.util.Scanner;

import javax.xml.ws.Endpoint;

public class BookServer {
	public static final Object implementor = new ua.nure.order.server.service.BookServiceImpl();
	public static final String address = "http://localhost:9000/books";
	
	public BookServer() {
        System.out.println("Starting Server");
        Endpoint.publish(address, implementor);
	}
	
	public static void main(String[] args) throws InterruptedException {
		new BookServer();
		System.out.println("Server ready... at " + address);

		Scanner sc = new Scanner(System.in);
		sc.nextLine();
		sc.close();
		System.out.println("Server exit");
		System.exit(0);
	}
}
