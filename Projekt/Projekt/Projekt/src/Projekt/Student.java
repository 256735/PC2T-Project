package Projekt;
import java.util.List;
public abstract class Student{
	private String name;
	private String surname;
	private int id;
	private int birthYear;
	private List<Integer> grades;
	
	public Student(String name, String surname, int id, int birthyear, List<Integer> grades) {
		this.grades=grades;
	} 
	
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	
	public void addGrade(int grade) {
		grades.add(grade);
	}
	
	public String getFullName() {
		return name+" "+surname;
	}
	
	public abstract String skill(String kod);
		
	public int getID() {
		return id;
	}
	
	public float getAverageGrade() {
		float sum = 0;
		for (int grade : grades) {
			sum += grade;
		}
		
		return sum/grades.size();
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", surname=" + surname + ", birthYear=" + birthYear
				+ ", grade average=" + this.getAverageGrade() + "]";
	}
	
	
	
}
