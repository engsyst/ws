package ua.nure.order.server.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import ua.nure.order.entity.user.User;
import ua.nure.order.server.dao.DAOException;
import ua.nure.order.server.dao.UserDAO;

/**
 * <p>
 * Concrete DAO for User domain. Singleton pattern. Can not be created from
 * application context.
 * </p>
 * <p>
 * Each interface method represent as 2 methods. 1-st public method - manage
 * connection, 2-nd use existed connection to obtain data.
 * </p>
 * <p>
 * Pattern for read operation can be represented as flowing
 * </p>
 * 
 * <pre>
 * {@code
 * 	Connection con = null;
 *  TransferObject obj = null;
 *  try {
 *      con = MysqlDAOFactory.getConnection();
 *      obj = getTransferObject(con, dataToFind);
 *  } catch (SQLException e) {
 *      log.error("Log error if Exception occurs", e);
 *      throw new DAOException("Can not get TransferObject. " + e.getMessage(), e);
 *  } finally {
 *      MysqlDAOFactory.close(con);
 *  }
 *  return obj;
 * }
 * </pre>
 * <p>
 * Pattern for <b>insert, update, delete</b> operations can be represented as flowing
 * </p>
 * 
 * <pre>
 * {@code
 *  Connection con = null;
 *  try {
 *      con = MysqlDAOFactory.getConnection();
 *      obj = updateTransferObject(con, dataToUpdate);
 *      
 *      // Commit changes if no errors occurs
 *      con.commit();
 *  } catch (SQLException e) {
 *      log.error("Log error if Exception occurs", e);
 *      
 *      // Rollback changes if Exception occurs
 *      MysqlDAOFactory.rollback(con);
 *      throw new DAOException("Can not update TransferObject. " + e.getMessage(), e);
 *  } finally {
 *      MysqlDAOFactory.close(con);
 *  }
 *  return obj;
 * }
 * </pre>
 * <p>
 * </p>
 * 
 * @author engsyst
 *
 */
public class MysqlUserDAO implements UserDAO {
	private static final Logger log = Logger.getLogger(MysqlUserDAO.class);

	private static MysqlUserDAO dao;

	public static UserDAO getInstance() {
		if (dao == null)
			dao = new MysqlUserDAO();
		return dao;
	}

	/**
	 * Get {@link User} from database by login
	 */
	@Override
	public User getUser(String login) throws DAOException {
		Connection con = null;
		User user = null;
		try {
			con = MysqlDAOFactory.getConnection();
			user = getUser(con, login);
		} catch (SQLException e) {
			log.error("getUser: Can to get user. ", e);
			throw new DAOException("Can to get user. " + e.getMessage(), e);
		} finally {
			MysqlDAOFactory.close(con);
		}
		return user;
	}

	/**
	 * Get {@link User} from database by login. 
	 * @param con Opened connection 
	 * @see MysqlDAOFactory#getConnection()
	 * @param login User login
	 * @return {@link User}
	 * @throws SQLException
	 */
	User getUser(Connection con, String login) throws SQLException {
		PreparedStatement st = null;
		User user = null;
		try {
			st = con.prepareStatement(Querys.SQL_GET_USER);
			st.setString(1, login);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				user = unmapUser(rs);
			} 
		} finally {
			MysqlDAOFactory.closeStatement(st);
		}
		return user;
	}

	/**
	 * Create User object from ResultSet
	 * @param rs ResultSet 
	 * @return User
	 * @throws SQLException
	 */
	User unmapUser(ResultSet rs) throws SQLException {
		User user = new User(rs.getInt("id"), rs.getString("login"), rs.getString("password"), rs.getString("role"));
		user.setAddress(rs.getString("address"));
		user.setAvatar(rs.getString("avatar"));
		user.setDescription(rs.getString("description"));
		user.setEmail(rs.getString("e-mail"));
		user.setPhone(rs.getString("phone"));
		user.setName(rs.getString("name"));
		return user;
	}
	
	@Override
	public void updateUser(User user) throws DAOException {
		Connection con = null;
		try {
			con = MysqlDAOFactory.getConnection();
			updateUser(con, user);
			con.commit();
		} catch (SQLException e) {
			MysqlDAOFactory.rollback(con);
			log.error("Can to update user. ", e);
			throw new DAOException("Can to update user. " + e.getMessage(), e);
		} finally {
			MysqlDAOFactory.close(con);
		}
	}


	public int updateUser(Connection con, User user) throws SQLException {
		log.trace("Start");
		PreparedStatement st = null;
		int id = 0;
		try {
			/* `login`,`password`,`role`,`e-mail`,`phone`,
			 * `name`,`address`,`avatar`,`description` WHERE `id` = ?
			*/
			st = con.prepareStatement(Querys.SQL_UPDATE_USER);
			int k = 0;
			st.setString(++k, user.getLogin());
			st.setString(++k, user.getPass());
			st.setString(++k, user.getRole().toString());
			st.setString(++k, user.getEmail());
			st.setString(++k, user.getPhone());
			st.setString(++k, user.getName());
			st.setString(++k, user.getAddress());
			st.setString(++k, user.getAvatar());
			st.setString(++k, user.getDescription());
			st.setInt(++k, user.getId());
			log.debug("Query --> " + st);
			st.executeUpdate();
		} finally {
			MysqlDAOFactory.closeStatement(st);
		}
		log.trace("Finish");
		return id;
	}
	@Override
	public int addUser(User user) throws DAOException {
		Connection con = null;
		int id = 0;
		try {
			con = MysqlDAOFactory.getConnection();
			id = addUser(con, user);
			con.commit();
		} catch (SQLException e) {
			MysqlDAOFactory.rollback(con);
			log.error("getUser: Can to add user. ", e);
			throw new DAOException("Can to add user. " + e.getMessage(), e);
		} finally {
			MysqlDAOFactory.close(con);
		}
		return id;
		
	}

	public int addUser(Connection con, User user) throws SQLException {
		log.trace("Start");
		PreparedStatement st = null;
		int id = 0;
		try {
			// (`login`,`password`,`role`,`e-mail`,`phone`,`name`,`address`,`avatar`,`description`)
			st = con.prepareStatement(Querys.SQL_ADD_USER, PreparedStatement.RETURN_GENERATED_KEYS);
			int k = 0;
			st.setString(++k, user.getLogin());
			st.setString(++k, user.getPass());
			st.setString(++k, user.getRole().toString());
			st.setString(++k, user.getEmail());
			st.setString(++k, user.getPhone());
			st.setString(++k, user.getName());
			st.setString(++k, user.getAddress());
			st.setString(++k, user.getAvatar());
			st.setString(++k, user.getDescription());
			log.debug("Query --> " + st);
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				log.error("Can not add user");
				throw new SQLException("Can not add user");
			}
		} finally {
			MysqlDAOFactory.closeStatement(st);
		}
		log.debug("Result --> " + id);
		log.trace("Finish");
		return id;
	}
	
}
