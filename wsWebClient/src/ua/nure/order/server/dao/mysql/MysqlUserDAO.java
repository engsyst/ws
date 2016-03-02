package ua.nure.order.server.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import ua.nure.order.entity.user.User;
import ua.nure.order.server.dao.DAOException;
import ua.nure.order.server.dao.UserDao;

public class MysqlUserDAO implements UserDao {
	private static final Logger log = Logger.getLogger(MysqlUserDAO.class);

	private static MysqlUserDAO dao;

	public static UserDao getInstance() {
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
			} else {
				throw new SQLException("User not found. Login: " + login);
			}
		} finally {
			MysqlDAOFactory.closeStatement(st);
		}
		return user;
	}

	User unmapUser(ResultSet rs) throws SQLException {
		return new User(rs.getInt("id"), rs.getString("login"), rs.getString("password"), rs.getString("role"));
	}

}
