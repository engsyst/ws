package ua.nure.order;

import java.util.Locale;
import java.util.Map.Entry;
import java.util.Properties;

public class Test {
	public static void main(String[] args) {
		Properties props = System.getProperties();
		for (Entry<Object, Object> p : props.entrySet()) {
			System.out.println("Key --> " + p.getKey() + ", Value --> " + p.getValue());
		}
		Locale locale =Locale.getDefault();
	}
}
