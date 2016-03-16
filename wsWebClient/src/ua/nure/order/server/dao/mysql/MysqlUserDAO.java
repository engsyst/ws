package ua.nure.order.server.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import ua.nure.order.entity.user.User;
import ua.nure.order.server.dao.DAOException;
import ua.nure.order.server.dao.UserDAO;

public class MysqlUserDAO implements UserDAO {
	private static final Logger log = Logger.getLogger(MysqlUserDAO.class);

	private static MysqlUserDAO dao;

	public static UserDAO getInstance() {
		if (dao == null)
			dao = new MysqlUserDAO();
		return dao;
	}

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

	User unmapUser(ResultSet rs) throws SQLException {
		return new User(rs.getInt("id"), rs.getString("login"), rs.getString("password"), rs.getString("role"));
	}

	@Override
	public int addUser(User user) throws DAOException {
		Connection con = null;
		int id = 0;
		try {
			con = MysqlDAOFactory.getConnection();
			id = addUser(con, user);
		} catch (SQLException e) {
			MysqlDAOFactory.roolback(con);
			log.error("getUser: Can to get user. ", e);
			throw new DAOException("Can to get user. " + e.getMessage(), e);
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
			con.commit();
		} finally {
			MysqlDAOFactory.closeStatement(st);
		}
		log.debug("Result --> " + id);
		log.trace("Finish");
		return id;
	}
	
}
