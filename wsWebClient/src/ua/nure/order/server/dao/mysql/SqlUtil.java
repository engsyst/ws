package ua.nure.order.server.dao.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SqlUtil {
	public static <T> String listToValues(Collection<T> list) {
		StringBuilder sb = new StringBuilder();
		String start, end;
		start = "('";
		end = "'),";
		for (T a : list) {
			sb.append(start);
			sb.append(a);
			sb.append(end);
		}
		sb.replace(sb.length() - 1, sb.length(), ";");
		return sb.toString();
	}

	public static <T> String listToIN(Collection<T> list) {
		StringBuilder sb = new StringBuilder("(");
		String start, end;
		start = "'";
		end = "',";
		for (T a : list) {
			sb.append(start);
			sb.append(a);
			sb.append(end);
		}
		sb.replace(sb.length() - 1, sb.length(), ")");
		return sb.toString();
	}
	
	public static <T, R> String pairToValues(List<T> fst, R sec) {
		StringBuilder sb = new StringBuilder();
		String start, middle, end;
		T temp = fst.get(0);
		if (temp instanceof String) {
			start = "('";
			middle = "',";
		} else {
			start = "(";
			middle = ",";
		}
		if (!(sec instanceof String)) {
			end = "),";
		} else {
			middle = middle + "'";
			end = "'),";
		}
		for (T t : fst) {
			sb.append(start);
			sb.append(t);
			sb.append(middle);
			sb.append(sec);
			sb.append(end);
		}
		sb.replace(sb.length() - 1, sb.length(), ";");
		return sb.toString();
	}
	
	public static List<Integer> unmapIdList(ResultSet rs) throws SQLException {
		List<Integer> list = new ArrayList<>();
		while (rs.next()) {
			list.add(rs.getInt(1));
		}
		return list;
	}
	
	public static List<Integer> unmapIdList(ResultSet rs, int column) throws SQLException {
		List<Integer> list = new ArrayList<>();
		while (rs.next()) {
			list.add(rs.getInt(column));
		}
		return list;
	}
}
