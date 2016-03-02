package ua.nure.order.server.dao.mysql;

public interface Querys {
	String SQL_GET_USER = "SELECT id, login, password, role FROM user WHERE login = ?"; 
}
