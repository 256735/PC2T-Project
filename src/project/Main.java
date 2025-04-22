package project;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    	
        MyDatabase db = new MyDatabase();
        Scanner sc = new Scanner(System.in);
        String prvek;
        DBSql dbsql = new DBSql();
        if (dbsql.connect()) {
            dbsql.createTables();
            dbsql.loadStudents(db);
            for (Student s : db.getSortedStudents()) {
                dbsql.loadGrades(s.getID(), db);
            }
            dbsql.disconnect();
            System.out.println("Data byla načtena z databáze.");
        

        while (true) {
        	System.out.println("Zadejte příkaz:");
        	System.out.println("1 - Přidat studenta");
        	System.out.println("2 - Přidat známku studentovi");
        	System.out.println("3 - Spustit dovednost studenta");
        	System.out.println("4 - Smazat studenta");
        	System.out.println("5 - Vypsat informace o studentovi");
        	System.out.println("6 - Obecný studijní průměr oboru TLI/KB");
        	System.out.println("7 - Seřazený výpis všech studentů podle příjmení");
        	System.out.println("8 - Výpis počtů studentů");
        	System.out.println("9 - Ulozeni dat do txt.souboru");
        	System.out.println("10 - Nacteni dat z txt.souboru");
        	System.out.println("11 - Ukončit program");
            prvek = sc.nextLine();

            switch (prvek) {
            case "1":
                System.out.println("Zadej obor (TLI/KB):");
                String type = sc.nextLine();
                System.out.println("Zadej jméno:");
                String name = sc.nextLine();
                System.out.println("Zadej příjmení:");
                String surname = sc.nextLine();
                System.out.println("Zadej rok narození:");
                int birthYear = Integer.parseInt(sc.nextLine());

                if (type.equalsIgnoreCase("TLI")) {
                    db.addStudentTLI(name, surname, birthYear, new ArrayList<>());
                    System.out.println("Student TLI byl přidán.");
                } else if (type.equalsIgnoreCase("KB")) {
                    db.addStudentKB(name, surname, birthYear, new ArrayList<>());
                    System.out.println("Student KB byl přidán.");
                } else {
                    System.out.println("Neznámý obor!");
                }
                break;

            case "2":
                System.out.println("Zadej ID studenta:");
                int id = Integer.parseInt(sc.nextLine());

                System.out.println("Zadej známku (1–5):");
                try {
                    int grade = Integer.parseInt(sc.nextLine());
                    if (grade >= 1 && grade <= 5) {
                        db.addGrade(id, grade);
                    } else {
                        System.out.println("Chyba: Známka musí být v rozsahu 1 až 5.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Takto to nepůjde ! : Známka musí být jak ve škole (-_-) ");
                }
                break;

            case "3":
                System.out.println("Zadej ID studenta pro spuštění dovednosti:");

                try {
                    int skillId = Integer.parseInt(sc.nextLine());
                    Student student = db.getStudent(skillId);

                    if (student != null) {
                        String vysledek = student.skill("");
                        System.out.println("Výsledek dovednosti: " + vysledek);
                    } else {
                        System.out.println("Student s tímto ID nebyl nalezen.");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Tak to fakt ne.... Takto ID nevypadá");
                }
                break;

            case "4":
                System.out.println("Zadej ID studenta ke smazání:");
                int deleteId = Integer.parseInt(sc.nextLine());

                
                db.deleteStudent(deleteId);

                
                if (dbsql.connect()) {
                    dbsql.deleteStudentFromDB(deleteId);
                    dbsql.disconnect();
                }
                break;

            case "5":
                System.out.println("Zadej ID studenta pro výpis:");
                int infoId = Integer.parseInt(sc.nextLine());
                Student s = db.getStudent(infoId);

                if (s != null) {
                	System.out.println("Jméno:");
                	System.out.println(s.getName());

                	System.out.println("Příjmení:");
                	System.out.println(s.getSurname());

                	System.out.println("Obor:");
                	System.out.println(s.getObor());

                	System.out.println("Studijní průměr:");
                	System.out.println(s.getAverageGrade());
                } else 
                {
                    System.out.println("Studenta s tímto ID jsem nenašel :(");
                }
                break;
                
                
                
            case "6":
                System.out.println("Zadej obor (TLI/KB):");
                String selectedObor = sc.nextLine();

                if (selectedObor.equalsIgnoreCase("TLI")) {
                    System.out.println("Studijní průměr TLI: " + db.getAverageTLI());
                } else if (selectedObor.equalsIgnoreCase("KB")) {
                    System.out.println("Studijní průměr KB: " + db.getAverageKB());
                } else {
                    System.out.println("Tento obor neznám :(");
                }
                break;
                
                
            case "7":
                ArrayList<Student> studentiTLI = new ArrayList<>();
                ArrayList<Student> studentiKB = new ArrayList<>();

                for (Student k : db.getSortedStudents()) {
                    if (k.getObor().equalsIgnoreCase("TLI")) {
                        studentiTLI.add(k);
                    } else if (k.getObor().equalsIgnoreCase("KB")) {
                        studentiKB.add(k);
                    }
                }

                
                studentiTLI.sort(new StudentComparator());
                studentiKB.sort(new StudentComparator());

                System.out.println("--- Studenti TLI ---");
                for (Student s1 : studentiTLI) {
                    System.out.println("ID: " + s1.getID());
                    System.out.println("Jméno: " + s1.getName());
                    System.out.println("Příjmení: " + s1.getSurname());
                    System.out.println("Rok narození: " + s1.getBirthYear());
                    System.out.println("Studijní průměr: " + s1.getAverageGrade());
                    System.out.println("---------------");
                }

                System.out.println("--- Studenti KB ---");
                for (Student s2 : studentiKB) {
                    System.out.println("ID: " + s2.getID());
                    System.out.println("Jméno: " + s2.getName());
                    System.out.println("Příjmení: " + s2.getSurname());
                    System.out.println("Rok narození: " + s2.getBirthYear());
                    System.out.println("Studijní průměr: " + s2.getAverageGrade());
                    System.out.println("---------------");
                }
                break;
                
            case "8":
                System.out.println("Počet studentů TLI: " + db.getPocetTLI());
                System.out.println("Počet studentů KB: " + db.getPocetKB());
                break;
                
                
                
            case "9":
                DBFile.saveToTXT(db, "Database.txt");
                   System.out.println("Soubor úspěšně uložen");
                   break;

            case "10":
                DBFile.loadFromTXT(db, "Database.txt");
                System.out.println("Databáze ze souboru úspěšně načtena");
                break;

            case "11":
                System.out.println("Program ukončen.");
                if (dbsql.connect()) {
                    dbsql.createTables(); 
                    for (Student stud : db.getSortedStudents()) {
                        dbsql.addStudent(stud.getID(), stud.getName(), stud.getSurname(), stud.getBirthYear(), stud.getObor());
                        dbsql.addGrades(stud.getID(), new ArrayList<>(stud.getGrades()));
                    }
                    dbsql.disconnect();
                }
                sc.close();
                return;
                
            

            default:
                System.out.println("Tento příkaz neznám...Zkus to znovu :)");
        }
        }
    }
    }
    }
