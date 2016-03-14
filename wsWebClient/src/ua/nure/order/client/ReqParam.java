package ua.nure.order.client;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ReqParam {
	protected LinkedHashMap<String, String[]> params = new LinkedHashMap<>();

	public Map<String, String[]> getParams() {
		return params;
	}

	public void setParams(Map<String, String[]> params) {
		this.params.putAll(params);
	}
	
	public String[] getParam(String key) {
		return params.get(key);
	}
	
	public String setParam(String key, String... value) {
		if (value[0] == null || value[0].equals("")) 
			this.params.remove(key);
		else 
			this.params.put(key, value);
		return toString();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Entry<String, String[]> e : params.entrySet()) {
			for (String s : e.getValue()) {
				sb.append(e.getKey());
				sb.append("=");
				sb.append(s);
				sb.append("&");
			}
		}
		return sb.length() == 0 ? "" : sb.substring(0, sb.length()-1);
	}
}
