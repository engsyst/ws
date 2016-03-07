package ua.nure.order.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ua.nure.order.entity.book.Book;
import ua.nure.order.server.dao.DAOException;

public class CartBookDAO implements Paginable<Book> {
	
	Cart<Book> cart;
	
	public Cart<Book> getCart() {
		return cart;
	}

	public void setCart(Cart<Book> cart) {
		this.cart = cart;
	}

	Comparator<Book> title  = new Comparator<Book>() {

		@Override
		public int compare(Book o1, Book o2) {
			return o1.getTitle().compareTo(o2.getTitle());
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
			comparator = title;
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
			comparator = title;
			break;
		}
		Set<Book> b = cart.keySet();
		ArrayList<Book> a = new ArrayList<>();
		for (Book book : b) {
			a.add(book);
		}
		total.setCount(a.size());
//		Book[] books = (Book[]) cart.keySet().toArray();
//		Arrays.sort(books, comparator);
//		return Arrays.asList(books);
		return a;
	}
	
}
