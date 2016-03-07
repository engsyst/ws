package ua.nure.order.shared;

import java.util.List;

import ua.nure.order.client.Paginable;
import ua.nure.order.client.SQLCountWrapper;
import ua.nure.order.server.dao.DAOException;

public class Pagination {
	private static final int DEFAULT_ITEM_COUNT = 10;
	private static final int DEFAULT_SIZE = 5;
	
	private int start;
	private int end;
	private int max;
	private int page;
	private int size = DEFAULT_SIZE;
	private int count = DEFAULT_ITEM_COUNT;
	private Integer total = 0;
	private String search;
	private String sortField;
	private boolean ascending = true;
	private List<?> items;
	private Paginable<?> dao;

	public Pagination() {
	}

	public int getStart() {
		start = page - page % size;
		return start;
	}

//	public void setStart(int start) {
//		this.start = start;
//	}

	public int getEnd() {
		int e = getStart() + size;
		end = e > getMax() ? max : e;
		return end;
	}

//	public void setEnd(int end) {
//		this.end = end;
//	}

	public int getMax() {
		int t = (total / count);
		max = t * count < total ? t + 1 : t;
		return max;
	}

//	public void setMax(int max) {
//		this.max = max;
//	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
//		if (search == null || search.trim().length() == 0) {
		this.search = search;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public boolean isAscending() {
		return ascending;
	}

	public void setAscending(boolean accending) {
		this.ascending = accending;
	}
	
	public Paginable<?> getDao() {
		return dao;
	}

	public void setDao(Paginable<?> dao) {
		this.dao = dao;
	}

	public int size() {
		return items.size();
	}

	public List<?> getItems() {
		try {
			SQLCountWrapper wrapper = new SQLCountWrapper();
			List<?> items = dao.list(getSearch(), getSortField(), isAscending(), 
					getPage() * getCount(), getCount(), wrapper);
			total = wrapper.getCount();
			return items;
		} catch (DAOException e) {
			items = null;
		}
		return items;
	}

	public List<?> next() {
		page++;
		return getItems();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pagination [start=");
		builder.append(start);
		builder.append(", end=");
		builder.append(end);
		builder.append(", max=");
		builder.append(max);
		builder.append(", page=");
		builder.append(page);
		builder.append(", count=");
		builder.append(count);
		builder.append(", total=");
		builder.append(total);
		builder.append(", search=");
		builder.append(search);
		builder.append(", sortField=");
		builder.append(sortField);
		builder.append(", accending=");
		builder.append(ascending);
		builder.append(", dao=");
		builder.append(dao);
		builder.append("]");
		return builder.toString();
	}
	
	

}
