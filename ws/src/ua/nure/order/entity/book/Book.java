//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.11 at 02:54:52 PM EET 
//


package ua.nure.order.entity.book;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ua.nure.order.entity.Entity;


/**
 * <p>Java class for Book complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Book">
 *   &lt;complexContent>
 *     &lt;extension base="{http://order.nure.ua/entity/}Entity">
 *       &lt;sequence>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="author" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="isbn" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="ISBN-[0-9]{5,5}-[0-9]{4,4}"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="price" type="{http://order.nure.ua/entity/book/}Price"/>
 *         &lt;element name="category" type="{http://order.nure.ua/entity/book/}Category"/>
 *         &lt;element name="count">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;minInclusive value="0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Book", propOrder = {
    "title",
    "author",
    "isbn",
    "price",
    "category",
    "count"
})
public class Book extends Entity {

	@XmlElement(required = true)
	protected String title;
	@XmlElement(required = true)
	protected List<String> author;
	protected String isbn;
	protected double price;
	@XmlElement(required = true, defaultValue = "none")
	protected Category category;
	@XmlElement(defaultValue = "0")
	protected int count;

	public Book() {
		super();
	}

	public Book(String title, List<String> author, String isbn, double price,
			Category category, int count) {
		super();
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.price = price;
		this.category = category;
		this.count = count;
	}

	public Book(String title, List<String> author, String isbn, double price,
			Category category, int count, int id) {
		super();
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.price = price;
		this.category = category;
		this.count = count;
		this.id = id;
	}
	
	/**
	 * Gets the value of the title property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the value of the title property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTitle(String value) {
		this.title = value;
	}

	/**
	 * Gets the value of the author property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the author property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getAuthor().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link String }
	 * 
	 * 
	 */
	public List<String> getAuthor() {
		if (author == null) {
			author = new ArrayList<String>();
		}
		return this.author;
	}

	/**
	 * Gets the value of the isbn property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * Sets the value of the isbn property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIsbn(String value) {
		this.isbn = value;
	}

	/**
	 * Gets the value of the price property.
	 * 
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the value of the price property.
	 * 
	 */
	public void setPrice(double value) {
		this.price = value;
	}

	/**
	 * Gets the value of the category property.
	 * 
	 * @return possible object is {@link Category }
	 * 
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * Sets the value of the category property.
	 * 
	 * @param value
	 *            allowed object is {@link Category }
	 * 
	 */
	public void setCategory(Category value) {
		this.category = value;
	}

	/**
	 * Gets the value of the count property.
	 * 
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Sets the value of the count property.
	 * 
	 */
	public void setCount(int value) {
		this.count = value;
	}

}
