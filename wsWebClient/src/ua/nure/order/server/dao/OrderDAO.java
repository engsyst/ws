package ua.nure.order.server.dao;

import java.util.List;
import java.util.Map;

import ua.nure.order.client.Paginable;
import ua.nure.order.entity.Product;
import ua.nure.order.entity.order.Order;
import ua.nure.order.entity.order.OrderStatus;

public interface OrderDAO extends Paginable<Order> {
	int makeOrder(Map<Product, Integer> items, int userId) throws DAOException;
	List<Order> getOrders() throws DAOException;
	Order getOrderDetal(int id) throws DAOException;
	Order getOrderStatus(int id) throws DAOException;
	void updateStatus(int id, OrderStatus status) throws DAOException;
}
