package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import database.DBConnection;
import models.Book;

public class LibraryService {

    public void addBook(Book book) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO books VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, book.getId());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.setInt(4, 0);

            ps.executeUpdate();
            System.out.println("Book Added!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

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
            System.out.println(e.getMessage());
        }
    }

    public void issueBook(int id) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "UPDATE books SET isIssued = 1 WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Book Issued!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void returnBook(int id) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "UPDATE books SET isIssued = 0 WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Book Returned!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}