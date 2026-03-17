public class Book extends LibraryItem {

    private String author;
    private boolean isIssued;

    public Book(int id, String title, String author) {
        super(id, title);
        this.author = author;
        this.isIssued = false;
    }

    public void issueBook() {
        isIssued = true;
    }

    public void returnBook() {
        isIssued = false;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public int getId() {
        return id;
    }

    @Override
    public void display() {
        System.out.println(id + " | " + title + " | " + author + " | Issued: " + isIssued);
    }
}