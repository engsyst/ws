package ua.nure.order.client;

import javax.servlet.http.HttpServletRequest;

public class Delivery {
	protected String name;
	protected String phone;
	protected String email;
	protected String address;
	protected String description;

	public Delivery() {
		super();
	}

	public Delivery(HttpServletRequest request) {
		super();
		setName(request.getParameter("name"));
		setPhone(request.getParameter("phone"));
		setEmail(request.getParameter("email"));
		setAddress(request.getParameter("address"));
		setDescription(request.getParameter("description"));
	}
	
	public void setDelivery(HttpServletRequest request) {
		setName(request.getParameter("name"));
		setPhone(request.getParameter("phone"));
		setEmail(request.getParameter("email"));
		setAddress(request.getParameter("address"));
		setDescription(request.getParameter("description"));
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean validate() {
		if ((phone == null || phone.isEmpty()) && (email == null || email.isEmpty()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Delivery [name=");
		builder.append(name);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", email=");
		builder.append(email);
		builder.append(", address=");
		builder.append(address);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}
}
