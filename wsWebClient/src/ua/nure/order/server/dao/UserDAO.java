package ua.nure.order.server.dao;

import ua.nure.order.entity.user.User;

/**
 * <p>
 * DAO for User domain.
 * </p>
 * <p>
 * Each concrete DAO must implements these methods
 * </p>
 * 
 * @author engsyst
 *
 */
public interface UserDAO {

	/**
	 * Get user form database using unique login
	 * 
	 * @param login
	 * 
	 * @throws DAOException
	 */
	User getUser(String login) throws DAOException;

	/**
	 * Insert new User into database
	 * 
	 * @param user
	 * @return Obtained id
	 * @throws DAOException
	 *             if login exist
	 */
	int addUser(User user) throws DAOException;

	/**
	 * Updates user data into database
	 * 
	 * @param user
	 * @return
	 * @throws DAOException
	 */
	void updateUser(User user) throws DAOException;
}
