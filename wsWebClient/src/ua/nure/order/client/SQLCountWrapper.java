package ua.nure.order.client;

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
