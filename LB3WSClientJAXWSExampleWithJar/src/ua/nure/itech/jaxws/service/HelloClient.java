package ua.nure.itech.jaxws.service;

import javax.xml.ws.Holder;

import ua.nure.itech.jaxws.service.handled.Hello;
import ua.nure.itech.jaxws.service.handled.HelloUser;
import ua.nure.itech.jaxws.service.handled.Hello_Service;
import ua.nure.itech.jaxws.service.handled.SecurityHeader;

public class HelloClient {

	public static void main(String[] args) {
		Hello port = new Hello_Service().getHelloPort();
		HelloUser user = new HelloUser();
		user.setUserName("User");
		SecurityHeader clientToken = new SecurityHeader();
		clientToken.setToken("Client");
		System.out.println(port.helloUser(user, clientToken, new Holder<SecurityHeader>()).getReturn());
	}

}
