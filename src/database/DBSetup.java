package database;

import java.sql.Connection;
import java.sql.Statement;

public class DBSetup {

    public static void init() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            st.executeUpdate("CREATE TABLE IF NOT EXISTS books (" +
                    "id INTEGER PRIMARY KEY, " +
                    "title TEXT, " +
                    "author TEXT, " +
                    "isIssued INTEGER)");

            st.executeUpdate("CREATE TABLE IF NOT EXISTS students (" +
                    "id INTEGER PRIMARY KEY, " +
                    "name TEXT)");

            System.out.println("Database Ready!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}