package ua.nure.order.shared;

public class Util {
	public static <T> T getOrElse(T obj, T result) {
		return obj == null ? result : obj;
	}
	public static boolean isEmpty(String value) {
		return value == null || value.isEmpty() ? true : false;
	}
	
	public static Integer getIntOrElse(String param, Integer result) {
		try {
			return Integer.parseInt(param);
		} catch (Exception e) {
			return result;
		}
	}
	
	public static Double getDoubleOrElse(String param, Double result) {
		try {
			return Double.parseDouble(param);
		} catch (Exception e) {
			return result;
		}
	}
	
}
