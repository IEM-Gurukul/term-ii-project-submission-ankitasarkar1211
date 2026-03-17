
public abstract class LibraryItem {
  protected int id;
  protected String title;

  public LibraryItem(int id, String title) {
    this.id = id;
    this.title = title;
  }

  public abstract void display();
}
