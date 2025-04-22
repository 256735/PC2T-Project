package project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DBFile {

    public static void saveToTXT(MyDatabase db, String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (Student student : db.getSortedStudents()) {
                writer.write(student.getID() + ";" +
                        student.getName() + ";" +
                        student.getSurname() + ";" +
                        student.getBirthYear() + ";" +
                        student.getObor());

                for (int grade : student.getGrades()) {
                    writer.write(";" + grade);
                }

                writer.newLine();
            }
            System.out.println("Databáze uložena do TXT souboru.");
        } catch (IOException e) {
            System.out.println("Chyba při ukládání: " + e.getMessage());
        }
    }

    public static void loadFromTXT(MyDatabase db, String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length < 5) continue;

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String surname = parts[2];
                int birthYear = Integer.parseInt(parts[3]);
                String obor = parts[4];

                ArrayList<Integer> grades = new ArrayList<>();
                for (int i = 5; i < parts.length; i++) {
                    grades.add(Integer.parseInt(parts[i]));
                }

                if (obor.equalsIgnoreCase("TLI")) {
                    db.addStudentTLI(name, surname, birthYear, grades);
                } else if (obor.equalsIgnoreCase("KB")) {
                    db.addStudentKB(name, surname, birthYear, grades);
                }
            }
            System.out.println("Databáze načtena z TXT souboru.");
        } catch (IOException e) {
            System.out.println("Chyba při načítání: " + e.getMessage());
        }
    }

    public static void saveStudent(MyDatabase db, int id, String path) {
        Student student = db.getStudent(id);
        if (student == null) {
            System.out.println("Student nenalezen.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(student.getID() + ";" +
                    student.getName() + ";" +
                    student.getSurname() + ";" +
                    student.getBirthYear() + ";" +
                    student.getObor());

            for (int grade : student.getGrades()) {
                writer.write(";" + grade);
            }

            writer.newLine();
            System.out.println("Student uložen do souboru.");
        } 
        
        catch (IOException e) {
            System.out.println("Chyba při ukládání studenta: " + e.getMessage());
        }
    }

    public static void loadStudent(MyDatabase db, String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line = reader.readLine();
            if (line == null) return;

            String[] parts = line.split(";");
            int id = Integer.parseInt(parts[0]);
            String name = parts[1];
            String surname = parts[2];
            int birthYear = Integer.parseInt(parts[3]);
            String obor = parts[4];

            ArrayList<Integer> grades = new ArrayList<>();
            for (int i = 5; i < parts.length; i++) {
                grades.add(Integer.parseInt(parts[i]));
            }

            if (obor.equalsIgnoreCase("TLI")) {
                db.addStudentTLI(name, surname, birthYear, grades);
            } else if (obor.equalsIgnoreCase("KB")) {
                db.addStudentKB(name, surname, birthYear, grades);
            }

            System.out.println("Student načten ze souboru.");
        } 
        catch (IOException e) {
            System.out.println("Chyba při načítání studenta: " + e.getMessage());
        }
    }
}