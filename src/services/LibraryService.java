package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import database.DBConnection;
import models.Book;

public class LibraryService {

    // ADD BOOK
    public void addBook(Book book) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO books VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, book.getId());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.setInt(4, 0); // not issued

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error adding book: " + e.getMessage());
        }
    }

    // DISPLAY BOOKS (console - optional)
    public void displayBooks() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM books");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("title") + " | " +
                        rs.getString("author") + " | Issued: " +
                        rs.getInt("isIssued")
                );
            }

        } catch (Exception e) {
            System.out.println("Error displaying books: " + e.getMessage());
        }
    }

    // ✅ NEW METHOD → FOR UI
    public String getAllBooks() {
        StringBuilder result = new StringBuilder();

        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM books");

            while (rs.next()) {
                result.append(
                        rs.getInt("id") + " | " +
                        rs.getString("title") + " | " +
                        rs.getString("author") + " | Issued: " +
                        rs.getInt("isIssued") + "\n"
                );
            }

            if (result.length() == 0) {
                return "No books found!";
            }

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }

        return result.toString();
    }

    // ISSUE BOOK
    public void issueBook(int id) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "UPDATE books SET isIssued = 1 WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows == 0) {
                System.out.println("Book not found!");
            }

        } catch (Exception e) {
            System.out.println("Error issuing book: " + e.getMessage());
        }
    }

    // RETURN BOOK
    public void returnBook(int id) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "UPDATE books SET isIssued = 0 WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows == 0) {
                System.out.println("Book not found!");
            }

        } catch (Exception e) {
            System.out.println("Error returning book: " + e.getMessage());
        }
    }
}