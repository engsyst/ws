package ua.nure.order.shared;

import java.util.HashMap;
import java.util.Map;

public class Validator {

	public static Map<String, String> validateBay(String count) {
		HashMap<String, String> err = new HashMap<String, String>();
		if (count == null || "".equals(count) || count.contains("!\\d"))
			err.put("count", "Количество покупаемых книг должно быть больше нуля");
			
//		try {
//			System.err.println(count);
//			Integer.parseInt(count);
//		} catch (NumberFormatException e) {
//			err.add("count");
//		}
		return err;
	}
}
