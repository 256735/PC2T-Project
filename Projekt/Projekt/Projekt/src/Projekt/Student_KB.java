package Projekt;
import java.util.List; 

public class Student_KB extends Student{
	
	public Student_KB(String name, String surname, int id, int birthyear, List<Integer>grades) {
		super(name, surname, id, birthyear, grades);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String skill(String kod) {
		return String.valueOf(kod.hashCode());
	}
	
}
