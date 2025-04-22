package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBSql {
	private Connection conn;

    public boolean connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:students.db");
            return true;
        } catch (Exception e) {
            System.err.println("Chyba připojení: " + e.getMessage());
            return false;
        }
    }

    public void disconnect() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.err.println("Chyba při odpojení: " + e.getMessage());
        }
    }

    public boolean createTables() {
        String sqlStudents = "CREATE TABLE IF NOT EXISTS students (" +
                "id INTEGER PRIMARY KEY," +
                "name TEXT," +
                "surname TEXT," +
                "birthYear INTEGER," +
                "obor TEXT);";

        String sqlGrades = "CREATE TABLE IF NOT EXISTS grades (" +
                "id INTEGER," +
                "grade INTEGER," +
                "seq INTEGER PRIMARY KEY AUTOINCREMENT," +
                "FOREIGN KEY(id) REFERENCES students(id) ON DELETE CASCADE);";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sqlStudents);
            stmt.execute(sqlGrades);
            return true;
        } catch (SQLException e) {
            System.err.println("Chyba při vytváření tabulek: " + e.getMessage());
            return false;
        }
    }

    public void addStudent(int id, String name, String surname, int birthYear, String obor) {
        String sql = "INSERT OR REPLACE INTO students(id,name,surname,birthYear,obor) VALUES (?,?,?,?,?);";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, surname);
            ps.setInt(4, birthYear);
            ps.setString(5, obor);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Chyba při ukládání studenta: " + e.getMessage());
        }
    }

    public void addGrades(int id, ArrayList<Integer> grades) {
        String sql = "INSERT INTO grades(id, grade) VALUES (?, ?);";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int grade : grades) {
                ps.setInt(1, id);
                ps.setInt(2, grade);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Chyba při ukládání známek: " + e.getMessage());
        }
    }

    public void loadStudents(MyDatabase db) {
        String sql = "SELECT * FROM students;";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                int birthYear = rs.getInt("birthYear");
                String obor = rs.getString("obor");
                db.setPrvkyDatabaze(id, name, surname, birthYear, obor);
            }
        } catch (SQLException e) {
            System.err.println("Chyba při načítání studentů: " + e.getMessage());
        }
    }

    public void loadGrades(int id, MyDatabase db) {
        String sql = "SELECT grade FROM grades WHERE id = ?;";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    db.addGrade(id, rs.getInt("grade"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Chyba při načítání známek: " + e.getMessage());
        }
    }
    
    
    
    public void deleteStudentFromDB(int id) {
        String sqlGrades = "DELETE FROM grades WHERE id = ?;";
        String sqlStudent = "DELETE FROM students WHERE id = ?;";

        try (PreparedStatement psGrades = conn.prepareStatement(sqlGrades);
             PreparedStatement psStudent = conn.prepareStatement(sqlStudent)) {

            psGrades.setInt(1, id);
            psGrades.executeUpdate();

            psStudent.setInt(1, id);
            psStudent.executeUpdate();

            System.out.println("Student byl odstraněn z databáze.");

        } catch (SQLException e) {
            System.err.println("Chyba při mazání studenta z databáze: " + e.getMessage());
        }
    }
}
