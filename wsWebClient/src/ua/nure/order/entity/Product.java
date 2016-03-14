package ua.nure.order.entity;

import ua.nure.order.client.Priceable;

public class Product extends Entity implements Priceable {
	protected String title;
	protected double price;
	
	public Product() {
		super();
	}
	
	public Product(Integer id) {
		super(id);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}
}
