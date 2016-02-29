package ua.nure.order.server.dao.inmemory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ua.nure.order.entity.book.Book;
import ua.nure.order.entity.book.Category;
import ua.nure.order.server.dao.BookDAO;
import ua.nure.order.server.dao.DAOException;

public class InMemoryBookDAO implements BookDAO {
	private static HashMap<Integer, Book> books = new HashMap<Integer, Book>();
	private static int bookIndex;
	
	private static InMemoryBookDAO dao;
	
	private InMemoryBookDAO() {
		initBooks();
	}
	
	public static synchronized InMemoryBookDAO getInstance() {
		if (dao == null)
			dao = new InMemoryBookDAO();
		return dao;
	}


	@Override
	public int addBook(Book item) throws DAOException {
		if (item == null) 
			throw new DAOException("Oreder can not ba a null");
		item.setId(++bookIndex);
		books.put(bookIndex, item);
		return bookIndex;
	}

	@Override
	public Book deleteBook(int id) throws DAOException {
		return books.remove(id);
	}

	@Override
	public boolean updateBookCount(int id, int count) throws DAOException {
		if (count < 1)
			throw new DAOException("Count must be greater 0");
		Book o = books.get(id);
		if (o == null || o.getCount() < count)
			throw new DAOException("Not found or count more then exist");
		o.setCount(o.getCount() - count);
		return true;
	}

	@Override
	public Collection<Book> findByTitle(String pattern) {
		if (pattern == null || "".equals(pattern)) 
			return books.values();
		ArrayList<Book> found = new ArrayList<Book>();
		StringBuffer sb = new StringBuffer(".*");
		sb.append(pattern);
		sb.append(".*");
		Pattern p = Pattern.compile(sb.toString(), Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
		for (Book o : books.values()) {
			Matcher m = p.matcher(o.getTitle());
			if (m.matches()) {
				found.add(o);
			}
		}
		return found;
	}

	@Override
	public Collection<Book> findByAuthor(String pattern) {
		if (pattern == null || "".equals(pattern)) 
			return books.values();
		ArrayList<Book> found = new ArrayList<Book>();
		StringBuffer sb = new StringBuffer(".*");
		sb.append(pattern);
		sb.append(".*");
		Pattern p = Pattern.compile(sb.toString(), Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
		for (Book o : books.values()) {
			for (String a : o.getAuthor()) {
				Matcher m = p.matcher(a);
				if (m.matches()) {
					found.add(o);
				}
			}
		}
		return found;
	}

	@Override
	public Collection<Book> listBooks() {
		return books.values();
	}

	@Override
	public Book findById(Integer id) throws DAOException {
		Book b = books.get(id);
		if (b == null)
			throw new DAOException("Книга не найдена");
		return b;
	}

	private void initBooks() {
		Book[] books = new Book[] {
				new Book("Божественная комедия", 
						Arrays.asList(new String[] {"Данте Алигьери"}),
						"ISBN-01234-0123", 125.0, Category.LOVE_NOVEL, 3),
				new Book("Сказки",
						Arrays.asList(new String[] {"Ханс Кристиан Андерсен"}), 
						"ISBN-01234-0124", 300.0, Category.ACTION, 12),
				new Book("И пришло разрушение",
						Arrays.asList(new String[] {"Чинуа Ачебе"}), 
						"ISBN-01234-0125", 245.5, Category.ACTION, 4),
				new Book("Отец Горио",
						Arrays.asList(new String[] {"Оноре де Бальзак"}), 
						"ISBN-01234-0126", 45.5, Category.LOVE_NOVEL, 5),
				new Book("Вымыслы",
						Arrays.asList(new String[] {"Хорхе Луис Борхес"}), 
						"ISBN-01234-0127", 118.3, Category.FANTASY, 8),
				new Book("Декамерон",
						Arrays.asList(new String[] {"Джованни Боккаччо"}), 
						"ISBN-01234-0128", 148.7, Category.LOVE_NOVEL, 7),
				};
		for (int i = 0; i < books.length; i++) {
			try {
				addBook(books[i]);
			} catch (DAOException e) {
				throw new RuntimeException("Can not init");
			}
		}
	}

}
