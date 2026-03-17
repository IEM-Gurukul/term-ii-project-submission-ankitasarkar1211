import java.util.ArrayList;

public class Library {

    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void displayBooks() {
        for (Book b : books) {
            b.display();
        }
    }

    public Book findBook(int id) {
        for (Book b : books) {
            if (b.getId() == id) return b;
        }
        return null;
    }

    public void issueBook(int id) {
        Book b = findBook(id);
        if (b != null && !b.isIssued()) {
            b.issueBook();
            System.out.println("Book issued successfully");
        } else {
            System.out.println("Book not available");
        }
    }

    public void returnBook(int id) {
        Book b = findBook(id);
        if (b != null && b.isIssued()) {
            b.returnBook();
            System.out.println("Book returned successfully");
        }
    }
}