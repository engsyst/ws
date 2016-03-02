package ua.nure.order.entity.user;

import ua.nure.order.entity.Entity;

public class User extends Entity {
	private String login;
	private String pass;
	private Role role;
	
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
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
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
		if (pass == null) {
			if (other.pass != null)
				return false;
		} else if (!pass.equals(other.pass))
			return false;
		if (role != other.role)
			return false;
		return true;
	}
	
	
}
