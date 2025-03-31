package Projekt;
import java.util.List;
public class Student_TLI extends Student {
	
	public Student_TLI(String name, String surname, int id, int birthyear, List<Integer>grades) {
		super(name, surname, id, birthyear, grades);
		
	}

	@Override
	public String skill(String kod) {
		// DOPLNIT MORSEOVKU
		kod.replaceAll("[aA]", ".-");
		return null;
	}
	
	

}
