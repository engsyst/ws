package ua.nure.order.shared;

import java.util.List;

import ua.nure.order.client.Paginable;
import ua.nure.order.client.SQLCountWrapper;
import ua.nure.order.server.dao.DAOException;

/**
 * Encapsulate pagination mechanism. Use
 * {@link Paginable#list(String, String, boolean, int, int, SQLCountWrapper)}
 * to access DataSource.
 * 
 * @author engsyst
 *
 */
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

	/**
	 * @return Start page of visible range
	 */
	public int getStart() {
		start = page - page % size;
		return start;
	}

	/**
	 * @return End page of visible range
	 */
	public int getEnd() {
		int e = getStart() + size;
		end = e > getMax() ? max : e;
		return end;
	}

	/**
	 * @return Max pages need to represent data
	 */
	public int getMax() {
		int t = (total / count);
		max = t * count < total ? t + 1 : t;
		return max;
	}

	/**
	 * @return Current page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page Current page
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return Count of items represented at one page
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count Count of items represented at one page
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return Current count of pages in widget
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size Count of pages in widget
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return Total items of data
	 */
	public Integer getTotal() {
		return total;
	}

	/**
	 * @param total Total items of data
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}

	/**
	 * @return Filter pattern
	 */
	public String getSearch() {
		return search;
	}

	/**
	 * @param search Filter pattern
	 */
	public void setSearch(String search) {
		this.search = search;
	}

	/**
	 * @return Current field to sort
	 */
	public String getSortField() {
		return sortField;
	}

	/**
	 * @param sortField Field to sort
	 */
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	/**
	 * @return Sort order
	 */
	public boolean isAscending() {
		return ascending;
	}

	/**
	 * @param accending Sort order
	 */
	public void setAscending(boolean accending) {
		this.ascending = accending;
	}

	/**
	 * @return Current DataSource
	 */
	public Paginable<?> getDao() {
		return dao;
	}

	/**
	 * @param dao Current DataSource
	 */
	public void setDao(Paginable<?> dao) {
		this.dao = dao;
	}

	/**
	 * @return Size of data items represented at current page
	 */
	public int size() {
		return items.size();
	}

	/**
	 * Get data from DataSource
	 * 
	 * @return Items to represent at page. 
	 */
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

	/**
	 * Get next page. If next page will out of max pages needed to represent
	 * data then get data from DataSource for current page.
	 * 
	 * @return
	 */
	public List<?> next() {
		if (page == max - 1)
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
		builder.append("]\n");
		return builder.toString();
	}

}
