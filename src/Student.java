public class Student extends User {

    public Student(int userId, String name) {
        super(userId, name);
    }

    public int getUserId() {
        return userId;
    }
}