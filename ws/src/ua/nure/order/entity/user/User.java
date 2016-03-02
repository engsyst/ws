package ua.nure.order.entity.user;

import ua.nure.order.entity.Entity;

public class User extends Entity {
	private String login;
	private String pass;
	private Role role;
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
}
