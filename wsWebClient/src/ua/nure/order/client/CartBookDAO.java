package ua.nure.order.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import ua.nure.order.entity.book.Book;
import ua.nure.order.server.dao.BookDAO;
import ua.nure.order.server.dao.DAOException;
import ua.nure.order.server.dao.DAOFactory;

/**
 * Wrapper class used to get data from the {@link Cart} at jsp.
 * @author engsyst
 *
 */
public class CartBookDAO implements Paginable<Book> {
	
	Cart<Book> cart;
	
	public Cart<Book> getCart() {
		return cart;
	}

	public void setCart(Cart<Book> cart) {
		this.cart = cart;
	}

	Comparator<Book> titleAsc  = new Comparator<Book>() {

		@Override
		public int compare(Book o1, Book o2) {
			return o1.getTitle().compareTo(o2.getTitle());
		}
	};

	Comparator<Book> titleDesc  = new Comparator<Book>() {
		
		@Override
		public int compare(Book o1, Book o2) {
			return o2.getTitle().compareTo(o1.getTitle());
		}
	};
	
	Comparator<Book> price  = new Comparator<Book>() {
		
		@Override
		public int compare(Book o1, Book o2) {
			return Double.compare(o1.getPrice(), o2.getPrice());
		}
	};
	
	Comparator<Book> countComp  = new Comparator<Book>() {
		
		@Override
		public int compare(Book o1, Book o2) {
			return Integer.compare(o1.getCount(), o2.getCount());
		}
	};
	
	Comparator<Book> isbn  = new Comparator<Book>() {
		
		@Override
		public int compare(Book o1, Book o2) {
			return o1.getIsbn().compareTo(o2.getIsbn());
		}
	};

	Comparator<Book> author  = new Comparator<Book>() {
		
		@Override
		public int compare(Book o1, Book o2) {
			return o1.getAuthor().get(0).getTitle().compareTo(o2.getAuthor().get(0).getTitle());
		}
	};
	
	@Override
	public List<Book> list(String pattern, String orderColumn, boolean ascending, int start, int count,
			SQLCountWrapper total) throws DAOException {
		Comparator<Book> comparator = null;
		switch (orderColumn) {
		case "title":
			comparator = ascending ? titleAsc : titleDesc;
			break;
		case "author":
			comparator = author;
			break;
		case "count":
			comparator = countComp;
			break;
		case "price":
			comparator = price;
			break;
		default:
			comparator = titleAsc;
			break;
		}
		if (cart == null) {
			total.setCount(0);
			return new ArrayList<Book>();
		}
		BookDAO bdao = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getBookDAO();
		bdao.getBooksCount(cart.keySet());
		Set<Book> b = cart.keySet();
		ArrayList<Book> a = new ArrayList<>();
		for (Book book : b) {
			a.add(book);
		}
		Collections.sort(a, comparator);
		total.setCount(a.size());
//		Book[] books = (Book[]) cart.keySet().toArray();
//		Arrays.sort(books, comparator);
//		return Arrays.asList(books);
		return a;
	}
	
}
