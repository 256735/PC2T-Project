package Projekt;

import java.util.Comparator;

public class StudentComparator implements Comparator<Student>{

	@Override
	public int compare(Student student1, Student student2) {
	    int surnameComparison = student1.getSurname().compareTo(student2.getSurname());
	    if (surnameComparison != 0) {
	        return surnameComparison;
	    }
	    return student1.getName().compareTo(student2.getName());
	}
}