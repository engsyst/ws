package ua.nure.order.shared;

import java.util.HashMap;
import java.util.Map;

public class CountValidator implements Validator<String> {

	@Override
	public Map<String, String> validate(String obj) {
		Map<String, String> errors = new HashMap<>();
		try {
			int res = Integer.parseInt(obj);
			if (res <= 0) {
				errors.put("count", "Negative count");
			}
		} catch (Exception e) {
			errors.put("count", "Not valid number");
		}
		return errors;
	}
}
