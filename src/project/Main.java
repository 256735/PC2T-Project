package project;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        MyDatabase db = new MyDatabase();
        Scanner sc = new Scanner(System.in);

        String prvek;

        while (true) {
            System.out.println("Zadejte příkaz:");
            System.out.println("1 - Přidat studenta");
            System.out.println("2 - Přidat známku studentovi");
        System.out.println("3 - Ukončit program");
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
                    System.out.println("Zadej známku (1-5):");
                    int grade = Integer.parseInt(sc.nextLine());
                    db.addGrade(id, grade);
                    break;

                case "3":
                    System.out.println("Program ukončen.");
                    sc.close();
                    return;

                default:
                    System.out.println("Neznámý příkaz. Zkus to znovu.");
            }
        }
    }
}
