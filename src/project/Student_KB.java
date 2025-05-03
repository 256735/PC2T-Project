package project;

import java.util.List;

public class Student_KB extends Student {

	public Student_KB(String name, String surname, int id, int birthyear, List<Integer> grades) {
		super(name, surname, id, birthyear, grades, "KB");
		
	}

	@Override
	public String skill(String kod) {
	    String input = getName() + " " + getSurname();
	    try {
	        java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-256");
	        byte[] hashBytes = digest.digest(input.getBytes(java.nio.charset.StandardCharsets.UTF_8));

	        String hash = "";
	        for (byte b : hashBytes) {
	            hash += String.format("%02x", b);
	        }
	        return hash;

	    } catch (java.security.NoSuchAlgorithmException e) {
	        return "Chyba při generování hashe";
	    }
	}

}
