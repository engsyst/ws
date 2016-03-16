package ua.nure.order.shared;

public class Util {
	public static <T> T getOrElse(T obj, T result) {
		return obj == null ? result : obj;
	}
	public static boolean isEmpty(String value) {
		return value == null || value.isEmpty() ? true : false;
	}
}
