package ua.nure.order.entity;

import ua.nure.order.client.Priceable;

public abstract class Product extends Entity implements Priceable {
	private String title;
	private double price;
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public abstract double getPrice();

	public Product() {
		super();
	}

	public Product(Integer id) {
		super(id);
	}
}
