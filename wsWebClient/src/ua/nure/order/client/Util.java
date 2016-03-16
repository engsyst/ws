package ua.nure.order.client;

import ua.nure.order.entity.order.OrderStatus;
import ua.nure.order.entity.user.Role;

public class Util {
	public boolean equals(OrderStatus o1, OrderStatus o2) {
		return o1 == o2;
	}
	public boolean equals(OrderStatus o1, String o2) {
		return o1 == OrderStatus.valueOf(o2);
	}
	public OrderStatus get(String e) {
		return OrderStatus.valueOf(e);
	}
	public OrderStatus get(int i) {
		return OrderStatus.values()[i];
	}
	
	public String nextStatus(OrderStatus o) {
		if (o == OrderStatus.completed || o == OrderStatus.rejected)
			return o.toString();
		return OrderStatus.values()[o.ordinal() + 1].toString();
	}
	
	public String statusIconName(OrderStatus o) {
		switch (o) {
		case newed:
			return "glyphicon-flash";
		case inprogress:
			return "glyphicon-forward";
		case completed:
			return "glyphicon-saved";
		case rejected:
			return "glyphicon-remove";
		}
		return "";
	}
	
	public Role getRole(String name) {
		return Role.valueOf(name);
	}
}
