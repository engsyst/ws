package ua.nure.order.client;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * <p>
 * Utility class for manipulation of request Parameters.
 * </p>
 * <p>
 * Override {@code toString()}, so that returns parameters as in URL format. Many methods can be used as chain calls.
 * </p>
 * <p>
 * Result will <b>NOT</b> UrlEncoded.
 * </p>
 * <p>
 * <pre>
 * ReqParam params = new ReqParam();
 * println(params.setParam("param", "value 1", "value 2").setParam("param2", "value 3"));
 * // Output --> param=value 1&amp;param=value 2&amp;param2=value 3
 * println(params.removeParam("param").setParam("param3", "value 4"));
 * // Output --> param2=value 3&amp;param3=value 4
 * </pre>
 * </p>
 * <p>
 * </p>
 * 
 * @author engsyst
 *
 */
public class ReqParam {
	protected LinkedHashMap<String, String[]> params = new LinkedHashMap<>();

	/**
	 * Accessor method
	 * 
	 * @return parameters as Map
	 */
	public Map<String, String[]> getParams() {
		return params;
	}

	/**
	 * Accessor method
	 */
	public void setParams(Map<String, String[]> params) {
		this.params.clear();
		addParams(params);
	}
	
	/**
	 * Add new parameters. If some key already exist their value will overwrites.
	 * 
	 * @param params
	 * @return
	 */
	public ReqParam addParams(Map<String, String[]> params) {
		this.params.putAll(params);
		return this;
	}
	
	public String[] getParam(String key) {
		return params.get(key);
	}
	
	/**
	 * Add new or replace request parameter. If {@code value} equal {@code null}
	 * do nothing.
	 * <p>
	 * This method can execute in chain.
	 * </p>
	 * @param key Parameter name
	 * @param value Parameter values
	 * @return {@link ReqParam}
	 */
	public ReqParam setParam(String key, String... value) {
		if (value != null)
			this.params.put(key, value);
		return this;
	}
	
	/**
	 * Add new values to the existing parameter. If parameter not exist add new
	 * parameter.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public ReqParam addParam(String key, String... value) {
		String[] values = params.get(key);
		if (values == null) 
			return setParam(key, value);
		if (value == null)
			return this;
		String[] newValues = new String[values.length + value.length];
		System.arraycopy(values, 0, newValues, 0, values.length);
		System.arraycopy(value, 0, newValues, values.length, value.length);
		this.params.put(key, newValues);
		return this;
	}

	/**
	 * Remove parameter
	 * 
	 * @param key Parameter name to remove
	 * @return ReqParam for chain call
	 */
	public ReqParam removeParam(String key) {
		this.params.remove(key);
		return this;
	}
	
	public String toString() {
		if (params.size() == 0) 
			return "";
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
