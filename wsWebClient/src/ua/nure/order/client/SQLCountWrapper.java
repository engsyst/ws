package ua.nure.order.client;

/**
 * Wrapper for obtain total elements in DataSource. Generally DataSource return
 * Collection that represents part of data. This wrapper can be placed as
 * additional parameter and DataSource must set to it total count of data.
 * 
 * @see Paginable
 * 
 * @author engsyst
 *
 */
public class SQLCountWrapper {
	int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SQLCountWrapper [count=");
		builder.append(count);
		builder.append("]");
		return builder.toString();
	}
	
}
