package ua.nure.order.server.shared;

import java.util.Map;

public interface Validator<T> {
	Map<String, String> validate(T obj);
}
