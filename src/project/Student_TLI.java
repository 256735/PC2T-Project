package project;

import java.util.List;

public class Student_TLI extends Student {

	public Student_TLI(String name, String surname, int id, int birthyear, List<Integer> grades) {
		super(name, surname, id, birthyear, grades, "TLI");
		// TODO Auto-generated constructor stub
	}

	@Override
	public String skill(String kod) {
	    String input = (getName() + " " + getSurname()).toLowerCase();
	    String morse = "";

	    java.util.Map<Character, String> morseMap = new java.util.HashMap<>();
	    morseMap.put('a', ".-");
	    morseMap.put('b', "-...");
	    morseMap.put('c', "-.-.");
	    morseMap.put('d', "-..");
	    morseMap.put('e', ".");
	    morseMap.put('f', "..-.");
	    morseMap.put('g', "--.");
	    morseMap.put('h', "....");
	    morseMap.put('i', "..");
	    morseMap.put('j', ".---");
	    morseMap.put('k', "-.-");
	    morseMap.put('l', ".-..");
	    morseMap.put('m', "--");
	    morseMap.put('n', "-.");
	    morseMap.put('o', "---");
	    morseMap.put('p', ".--.");
	    morseMap.put('q', "--.-");
	    morseMap.put('r', ".-.");
	    morseMap.put('s', "...");
	    morseMap.put('t', "-");
	    morseMap.put('u', "..-");
	    morseMap.put('v', "...-");
	    morseMap.put('w', ".--");
	    morseMap.put('x', "-..-");
	    morseMap.put('y', "-.--");
	    morseMap.put('z', "--..");
	   
	    morseMap.put(' ', "/");

	    for (char c : input.toCharArray()) {
	        if (morseMap.containsKey(c)) {
	            morse += morseMap.get(c) + " ";
	        } else 
	        	{
	            morse += "? ";
	        }
	    }

	    return morse.trim();
	}

}
