package ua.nure.order.client;

import ua.nure.order.server.dao.DAOException;

public interface Paginable<T> {
	java.util.List<T> list(String pattern, String orderColumn, boolean ascending, int start, int count,
			SQLCountWrapper total) throws DAOException;
}
