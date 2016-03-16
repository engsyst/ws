package ua.nure.order.server.dao;

import ua.nure.order.entity.user.User;

public interface UserDAO {
	User getUser(String login) throws DAOException;

	int addUser(User user) throws DAOException;
}
