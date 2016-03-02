package ua.nure.order.shared;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
	public static String encode(String msg) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(msg.getBytes());
        return hash2str(digest.digest());
	}
	
	public static String hash2str(byte[] hash) {
		StringBuilder sb = new StringBuilder();
		for (byte b : hash) {
			sb.append(String.format("%1$02X", b & 0xFF));
		}
		return sb.toString().toUpperCase();
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(encode("admin"));
	}
}
