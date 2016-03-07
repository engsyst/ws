package ua.nure.order;

public class Test {
	public static void main(String[] args) {
		
		int t = 98;
		int count = 10;
		for (int p = 0; p < 20; p++) {
			int start = p - p % 5;
			int e = p + 5 - p % 5;
			int end = e > t / count ? t / count : e;
			System.out.println("p = " + p + "\tStart " + start 
					+ " \tEnd " + end 
					+ "\tpage " + (t / count) * p
					
					 );
		}
	}
}
