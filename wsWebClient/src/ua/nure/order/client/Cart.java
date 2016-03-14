package ua.nure.order.client;

import java.util.Hashtable;
import java.util.Set;

import ua.nure.order.entity.Product;

public class Cart<T extends Product> extends Hashtable<T, Integer> implements Priceable {

	/**
	 * Adds one more product to the cart. If there are items in the cart, it
	 * increases the amount of at 1.
	 * 
	 * @param item
	 * @return Returns the <b>current</b> number of this product in the cart.
	 */
	public Integer add(T item) {
		return add(item, 1);
	}

	/**
	 * Adds more product to the cart. If there are items in the cart, it
	 * increases the amount of at {@code count}.
	 * 
	 * @param item
	 * @return Returns the <b>current</b> number of this product in the cart.
	 */
	public Integer add(T item, Integer count) {
		Integer sum = getOrElse(item, 0) + count;
		put(item, count);
		return sum;
	}

	/**
	 * Puts product to the cart. If there are items in the cart, it
	 * <b>replace</b> the amount of at {@code count}.
	 * 
	 * @param item
	 * @param count
	 * @return Returns the <b>previous</b> number of this product in the cart.
	 */
	public Integer put(T item, Integer count) {
		return super.put(item, count);
	}

	/**
	 * Get the <b>current</b> number of this product in the cart. If product not
	 * in the cart return {@code value} placed as parameter.
	 * 
	 * @param item
	 * @param value
	 * @return Returns the <b>current</b> number of this product in the cart or
	 *         {@code value}
	 */
	public Integer getOrElse(T item, Integer value) {
		Integer v = get(item);
		if (v == null)
			return value;
		return v;
	}

	/**
	 * <p>
	 * If real count of product less or equal {@code count} parameter removes
	 * product from cart.
	 * </p>
	 * <p>
	 * Otherwise decrease count of product in the cart.
	 * </p>
	 * 
	 * @param item
	 * @param count
	 * @return null if product removed or real count of product in the cart.
	 */
	public Integer remove(T item, Integer count) {
		if (getOrElse(item, 0) - count <= 0) {
			return super.remove(item);
		} else {
			return add(item, -count);
		}
	}
	
	@Override
	public double getPrice() {
		double total = 0.;
		for(T e : keySet())
			total += e.getPrice() * get(e);
		return total;
	}
	
	public T getItem(Integer id) {
		for (T e : keySet()) {
			if (e.getId().equals(id)) 
				return e;
		}
		return null;
	}

}
