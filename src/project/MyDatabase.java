package project;

import java.util.ArrayList;
import java.util.List;


public class MyDatabase {
	ArrayList<Student> students;
	int highestID =0 ;
	public MyDatabase() {
		this.students  = new ArrayList<Student>();
	}
	
	public Student getStudent(int id) {
		for(int i=0;i<students.size(); i++) {
			if(students.get(i).getID()==id) {
				return students.get(i);
			}
		}
		
		return null;
	}
	
	public void addStudentTLI(String name, String surname, int birthyear, List<Integer> grades) {
		if (grades == null) {
			grades = new ArrayList<Integer>();
		}
		Student student = new Student_TLI(name, surname, highestID++, birthyear, grades);
		
		students.add(student);
	}
	
	public void addStudentKB(String name, String surname, int birthyear, List<Integer> grades) {
		if (grades == null) {
			grades = new ArrayList<Integer>();
		}
		Student student = new Student_KB(name, surname, highestID++, birthyear, grades);
		
		students.add(student);
	}
	
	public void deleteStudentTLI(int id) {
		for(int i=0; i<students.size();i++) {
			if(students.get(i).getID()==id) {
				students.remove(i);
			}
			
			System.out.println("Student smazán.");
			return;
		}
		
		System.out.println("Student nenalezen!!");
	}
	
	public ArrayList<Student> getSortedStudents(){
		students.sort(new StudentComparator());
		return students;
	}
	
	public float getAverageKB() {
	   float kbSum=0;
	   int countStudent=0;
	   for(Student student: students) {
		   if(student instanceof Student_KB) {
			   kbSum+= student.getAverageGrade();
			   countStudent++;
		   }
	   }
	   return kbSum/countStudent;
	}
	
	public float getAverageTLI() {
		float tliSum=0;
		   int countStudent=0;
		   for(Student student: students) {
			   if(student instanceof Student_TLI) {
				   tliSum+= student.getAverageGrade();
				   countStudent++;
			   }
		   }
		   return tliSum/countStudent;
	}
	
	public void addGrade(int id, int grade) {
	    Student student = getStudent(id);
	    if (student != null) {
	        student.addGrade(grade);
	        System.out.println("Známka přidána.");
	    } else {
	        System.out.println("Student s tímto ID nenalezen.");
	    }
	}
}
