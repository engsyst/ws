package ua.nure.order.shared;

import java.util.Map;

public interface Validator<T> {
	/**
	 * <p>
	 * Validate object.
	 * 
	 * @param obj
	 *            to validate
	 * @return {@code Map<String, String>} where {@code key} is field or
	 *         parameter name represented as {@link String} and {@code value}
	 *         message for output. If no errors found return empty Map.
	 */
	Map<String, String> validate(T obj);
}
