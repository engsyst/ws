package ua.nure.order.server.dao.mysql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ua.nure.order.entity.book.Author;
import ua.nure.order.entity.book.Book;
import ua.nure.order.entity.book.Category;
import ua.nure.order.server.dao.BookDAO;
import ua.nure.order.server.dao.DAOException;
import ua.nure.order.server.dao.DAOFactory;

public class TestDB {
	static BookDAO bdao = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getBookDAO();
	
	static void testAddBook(Book item) throws DAOException {
		bdao.addBook(item);
	}
	public static void main(String[] args) throws DAOException {
//		System.out.println(bdao.listBooks(null));
		
		for (int i = 2; i < 22; i++) {
			Author author = new Author("author" + i);
			List<Author> authors = new ArrayList<>();
			authors.add(author);
			testAddBook(new Book("Название" + i, 
					authors, "ISBN-01234-0133", 10.0 + i, Category.NONE, 5));
			authors.clear();
			
		}
//		testAddBook(new Book("Вопрос о воде и земле", 
//				Arrays.asList(new String[] {"Данте Алигьери"}),
//				"ISBN-01234-0133", 125.0, Category.NONE, 3));
//		testAddBook(new Book("Божественная комедия", 
//				Arrays.asList(new String[] {"Данте Алигьери"}),
//				"ISBN-01234-0123", 125.0, Category.LOVE_NOVEL, 3));
//		testAddBook(new Book("Сказки",
//				Arrays.asList(new String[] {"Ханс Кристиан Андерсен"}), 
//				"ISBN-01234-0124", 300.0, Category.ACTION, 12));
//		testAddBook(new Book("И пришло разрушение",
//				Arrays.asList(new String[] {"Чинуа Ачебе"}), 
//				"ISBN-01234-0125", 245.5, Category.ACTION, 4));
//		testAddBook(new Book("Отец Горио",
//				Arrays.asList(new String[] {"Оноре де Бальзак"}), 
//				"ISBN-01234-0126", 45.5, Category.LOVE_NOVEL, 5));
//		testAddBook(new Book("Вымыслы",
//				Arrays.asList(new String[] {"Хорхе Луис Борхес"}), 
//				"ISBN-01234-0127", 118.3, Category.FANTASY, 8));
//		testAddBook(new Book("Декамерон",
//				Arrays.asList(new String[] {"Джованни Боккаччо"}), 
//				"ISBN-01234-0128", 148.7, Category.LOVE_NOVEL, 7));
	}
	
}
