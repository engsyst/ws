package ua.nure.order.parser;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import ua.nure.order.entity.order.Orders;

public class ParseDemo {
	public static final String FILE_NAME = "xsd/order.xml";
	public static void main(String[] args) {
		JDOMParser jdom = new JDOMParser(FILE_NAME);
		System.out.println("<<-- JDOMParser -->>");
		Orders orders = null;
		try {
			orders = jdom.parse();
		} catch (ParserConfigurationException e) {
			System.err.println("Can not validate");;
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Can not read file");
			e.printStackTrace();
		}
		System.out.println(orders);
		System.out.println("<<-- JAXBParser -->>");
		orders = new JAXBParser(FILE_NAME).parse();
		System.out.println(orders);
	}
}
