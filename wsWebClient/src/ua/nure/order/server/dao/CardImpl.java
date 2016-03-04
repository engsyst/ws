package ua.nure.order.server.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ua.nure.order.entity.OrderItem;
import ua.nure.order.entity.book.Author;
import ua.nure.order.entity.book.Book;

@SuppressWarnings("serial")
public class CardImpl implements Card, Serializable {
	private HashMap<Integer, OrderItem> orders = new HashMap<Integer, OrderItem>();
	private int oiIndex;
//	private HashMap<Integer, Book> books =  new HashMap<Integer, Book>();
//	private static int bookIndex;
	
	public CardImpl() {
		initOrders();
	}
	
	/* (non-Javadoc)
	 * @see ua.nure.order.server.dao.Card#add(ua.nure.order.entity.OrderItem)
	 */
	private int add(OrderItem item) {
		if (item == null) 
			throw new IllegalArgumentException("Order can not be a null");
		item.setId(++oiIndex);
		orders.put(oiIndex, item);
		return oiIndex;
	}

	@Override
	public OrderItem getByBook(Book book) {
			for (OrderItem item : orders.values()) {
				if (item.getBook().equals(book))
					return item;
			}
			return null;
	}
	
	@Override
	public int addBook(Book item, Integer count) {
		synchronized (CardImpl.class) {
			OrderItem oItem = getByBook(item);
			if (oItem == null) {
				oItem = new OrderItem(item, count, null);
				oItem.setId(add(oItem));
			} else {
				updateOrderItemCount(oItem.getId(), oItem.getCount() 
						+ ((count < 0) ? -count : count));
			}
			return oItem.getId();
		}
	}

	@Override
	public int removeBook(Book item, Integer count) throws DAOException {
		synchronized (CardImpl.class) {
			OrderItem oItem = getByBook(item);
			if (oItem == null) {
				throw new IllegalArgumentException("Книга не найдена");
			} 
			if (count == null) {
				orders.remove(oItem);
				return oItem.getId();
			} 
			int c = oItem.getCount() - (count > 0 ? -count : count);
			if (c > 0) {
				updateOrderItemCount(oItem.getId(), oItem.getCount());
			} else {
				orders.remove(oItem);
			}
			return oItem.getId();
		}
	}

	private boolean updateOrderItemCount(int id, int count) {
		if (count < 0)
			throw new IllegalArgumentException("Количество должно быть больше или равно 0");
		OrderItem o = orders.get(id);
		o.setCount(count);
		return true;
	}

	/* (non-Javadoc)
	 * @see ua.nure.order.server.dao.Card#findByTitle(java.lang.String)
	 */
	@Override
	public Collection<OrderItem> findByTitle(String pattern) {
		if (pattern == null || "".equals(pattern)) 
			return orders.values();
		ArrayList<OrderItem> found = new ArrayList<OrderItem>();
		StringBuffer sb = new StringBuffer(".*");
		sb.append(pattern);
		sb.append(".*");
		Pattern p = Pattern.compile(sb.toString(), Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
		for (OrderItem o : orders.values()) {
			Matcher m = p.matcher(o.getBook().getTitle());
			if (m.matches()) {
				found.add(o);
			}
		}
		return found;
	}

	/* (non-Javadoc)
	 * @see ua.nure.order.server.dao.Card#findByAuthor(java.lang.String)
	 */
	@Override
	public Collection<OrderItem> findByAuthor(String pattern) {
		if (pattern == null || "".equals(pattern)) 
			return orders.values();
		ArrayList<OrderItem> found = new ArrayList<OrderItem>();
		StringBuffer sb = new StringBuffer(".*");
		sb.append(pattern);
		sb.append(".*");
		Pattern p = Pattern.compile(sb.toString(), Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
		for (OrderItem o : orders.values()) {
			for (Author a : o.getBook().getAuthor()) {
				Matcher m = p.matcher(a.getTitle());
				if (m.matches()) {
					found.add(o);
				}
			}
		}
		return found;
	}

	/* (non-Javadoc)
	 * @see ua.nure.order.server.dao.Card#listOrders()
	 */
	@Override
	public Collection<OrderItem> getOrders() {
		return orders.values();
	}
	
	/* (non-Javadoc)
	 * @see ua.nure.order.server.dao.Card#findById(int)
	 */
	@Override
	public OrderItem findById(int id) {
		return orders.get(id);
	}

	private void initOrders() {
/*		OrderItem[] order = new OrderItem[] {
			new OrderItem(
				new Book("Божественная комедия", 
						Arrays.asList(new String[] {"Данте Алигьери"}),
						"ISBN-01234-0123", 125.0, Category.LOVE_NOVEL, ++bookIndex),
				3, 0),
			new OrderItem(
				new Book("Сказки",
						Arrays.asList(new String[] {"Ханс Кристиан Андерсен"}), 
						"ISBN-01234-0124", 300.0, Category.ACTION, ++bookIndex),
				12, 0),
			new OrderItem(
				new Book("И пришло разрушение",
						Arrays.asList(new String[] {"Чинуа Ачебе"}), 
						"ISBN-01234-0125", 245.5, Category.ACTION, ++bookIndex),
				4, 0),
			new OrderItem(
				new Book("Отец Горио",
						Arrays.asList(new String[] {"Оноре де Бальзак"}), 
						"ISBN-01234-0126", 245.5, Category.LOVE_NOVEL, ++bookIndex),
				5, 0),
			new OrderItem(
				new Book("Вымыслы",
						Arrays.asList(new String[] {"Хорхе Луис Борхес"}), 
						"ISBN-01234-0127", 118.3, Category.FANTASY, ++bookIndex),
				8, 0),
			new OrderItem(
				new Book("Декамерон",
						Arrays.asList(new String[] {"Джованни Боккаччо"}), 
						"ISBN-01234-0128", 148.7, Category.LOVE_NOVEL, ++bookIndex),
				2, 0),
				};
		for (int i = 0; i < order.length; i++) {
			try {
				add(order[i]);
			} catch (DAOException e) {
				throw new RuntimeException("Can not init");
			}
		}
*/	}
}
