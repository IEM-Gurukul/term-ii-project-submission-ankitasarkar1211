import database.DBSetup;
import ui.LibraryUI;

public class Main {
    public static void main(String[] args) {

        DBSetup.init();   // VERY IMPORTANT (keep this)

        new LibraryUI();  // launch Swing UI
    }
}