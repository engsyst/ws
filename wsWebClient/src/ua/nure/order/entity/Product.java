package ua.nure.order.entity;

public abstract class Product extends Entity {
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

	public double getPrice() {
		return price;
	}

	public Product() {
		super();
	}

	public Product(Integer id) {
		super(id);
	}
}
