package ua.nure.order.parser;

import java.io.File;

import javax.xml.bind.JAXB;

import ua.nure.order.entity.order.Orders;

public class JAXBParser {
	private String file;

	public JAXBParser(String fname) {
		file = new File(fname).getAbsolutePath();
	}

	public Orders parse() {
		return JAXB.unmarshal(file, Orders.class);
	}

	public void write(Orders orders) {
		System.out.println();
		JAXB.marshal(orders, file);
	}
	
}
