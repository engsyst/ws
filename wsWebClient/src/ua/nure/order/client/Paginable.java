package ua.nure.order.client;

import ua.nure.order.server.dao.DAOException;

/**
 * Define DataSource access with limitation of returned data.
 * 
 * @author engsyst
 *
 * @param <T>
 */
public interface Paginable<T> {
	/**
	 * @param pattern String representation for data filtering.
	 * @param orderColumn Sorting field.
	 * @param ascending Sort order. true - ascending order, false - descending order.
	 * @param start Start index of data.
	 * @param count Count of returned data.
	 * @param total Total count of data.
	 * @return List of data
	 * @throws DAOException
	 * @see {@link SQLCountWrapper}
	 */
	java.util.List<T> list(String pattern, String orderColumn, boolean ascending, int start, int count,
			SQLCountWrapper total) throws DAOException;
}
