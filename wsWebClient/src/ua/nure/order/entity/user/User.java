package ua.nure.order.entity.user;

import ua.nure.order.entity.Entity;

/**
 * <p>Entity for store user data
 * 
 * @author admin
 *
 */
@SuppressWarnings("serial")
public class User extends Entity {
	
	private String name;
	private String login;
	private String pass;
	private Role role;
	private String email;
	private String phone;
	private String address;
	private String avatar;
	private String description;
	
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String l, String p) {
		login = l;
		pass = p;
	}
	
	public User(String login, String pass, String role) {
		super();
		this.login = login;
		this.pass = pass;
		this.role = Role.valueOf(role);
	}

	public User(int id, String login, String pass, String role) {
		super(id);
		this.login = login;
		this.pass = pass;
		this.role = Role.valueOf(role);
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public void setRole(String role) {
		this.role = Role.valueOf(role);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [login=");
		builder.append(login);
		builder.append(", pass=");
		builder.append(pass);
		builder.append(", role=");
		builder.append(role);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (role != other.role)
			return false;
		return true;
	}
	
	
}
