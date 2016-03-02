package ua.nure.order.server.dao;

import ua.nure.order.entity.user.User;

public interface UserDao {
	User getUser(String login) throws DAOException;
}
