package ua.nure.order.server.shared;

import java.util.HashMap;
import java.util.Map;

import ua.nure.order.entity.user.User;

public class UserValidator<T extends User> implements Validator<T> {

	public static String loginPattern = "[a-zA-Z0-9\\-_.]{4,20}";

	@Override
	public Map<String, String> validate(T obj) {
		Map<String, String> errors = new HashMap<>();
		if (obj.getLogin() == null || obj.getLogin().trim().length() < 4 || !obj.getLogin().matches(loginPattern)) {
			errors.put("login", "Login");
		}
		if (obj.getPass() == null || obj.getPass().length() < 4) {
			errors.put("login", "Login");
		}
		return errors;
	}
}
