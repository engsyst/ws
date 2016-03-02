package ua.nure.order.shared;

import java.util.Map;

public interface Validator<T> {
	Map<String, String> validate(T obj);
}
