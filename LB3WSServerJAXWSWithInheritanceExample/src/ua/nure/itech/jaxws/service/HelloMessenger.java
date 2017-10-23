package ua.nure.itech.jaxws.service;

import javax.jws.WebService;

@WebService
public interface HelloMessenger extends Messenger {
	String hello(String name);
}
