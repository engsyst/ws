package ua.nure.order.parser;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import ua.nure.order.entity.book.Book;
import ua.nure.order.entity.book.Category;
import ua.nure.order.entity.order.OrderItem;
import ua.nure.order.entity.order.Orders;

public class JDOMParser {
	public static final String FEATURE__TURN_VALIDATION_ON = 
			"http://xml.org/sax/features/validation";
	public static final String FEATURE__TURN_SCHEMA_VALIDATION_ON = 
			"http://apache.org/xml/features/validation/schema";
	
	private String file;
	
	public JDOMParser(String fname) {
		this.file = fname;
	}
	public Orders parse() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		dbf.setValidating(true);
		dbf.setFeature(FEATURE__TURN_VALIDATION_ON, true);
		dbf.setFeature(FEATURE__TURN_SCHEMA_VALIDATION_ON, true);
		dbf.setXIncludeAware(true);
		DocumentBuilder db = dbf.newDocumentBuilder();

		db.setErrorHandler(new DefaultHandler() {
			@Override
			public void error(SAXParseException e) throws SAXException {
				throw e; // <-- throw exception if XML document is NOT valid
			}
		});

		Document document = db.parse(file); // <-- parse XML document
		Element root = document.getDocumentElement(); // <-- get root element
		NodeList bNodes = root.getChildNodes();
		
		Orders orders = new Orders();
		for (int i = 1; i < bNodes.getLength(); i++) {
			Node node = bNodes.item(i);
			if ("orderItem".equals(node.getLocalName())) {
				// TODO Correct with orders.xsd
				//orders.getOrderItem().add(parseOrderItem(node));
			}
		}
		return orders;
	}
	
	private OrderItem parseOrderItem(Node e) {
		OrderItem oi = new OrderItem();
		NodeList nl = e.getChildNodes();
		for (int i = 1; i < nl.getLength(); i++) {
			Node node = nl.item(i);
			if ("count".equals(node.getLocalName())) {
				oi.setCount(Integer.parseInt(node.getTextContent()));
			} else if ("book".equals(node.getLocalName())) {
				oi.setBook(parseBook(node));
			}
		}
		
		return oi;
		
	}

	/**
	 * <book id = ""> 
	 * <title></title> 
	 * <author></author> 
	 * <ISBN></ISBN>
	 * <price></price> 
	 * <category></category> 
	 * </book>
	 */
	private Book parseBook(Node e) {
		Book book = new Book();
		NamedNodeMap attrs = e.getAttributes();
		try {
			int id = Integer.parseInt(attrs.getNamedItem("id").getTextContent());
			book.setId(id);
		} catch (Exception e2) {
			System.err.println("Can no get id");
		}
		NodeList nl = e.getChildNodes();
		for (int i = 1; i < nl.getLength(); i++) {
			Node node = nl.item(i);
			if ("title".equals(node.getLocalName())) {
				book.setTitle(node.getTextContent());
			}  else if ("author".equals(node.getLocalName())) {
				book.getAuthor().add(node.getTextContent());
			} else if ("ISBN".equals(node.getLocalName())) {
				book.setIsbn(node.getTextContent());
			} else if ("price".equals(node.getLocalName())) {
					book.setPrice(Double.parseDouble(node.getTextContent()));
			} else if ("category".equals(node.getLocalName())) {
				book.setCategory(Category.fromValue(node.getTextContent()));
			}
		}
		return book;
	}
}
