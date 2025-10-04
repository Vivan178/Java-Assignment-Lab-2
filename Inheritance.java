import java.util.*;

abstract class Person {

    protected String name;
    protected String email;

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public abstract void displayInfo();
}

class Student extends Person {

    private int rollNo;
    private String course;
    private double marks;
    private String grade;
    private String researchArea;

    public Student(int rollNo, String name, String email, String course, double marks, String grade) {
        super(name, email);
        this.rollNo = rollNo;
        this.course = course;
        this.marks = marks;
        this.grade = grade;
    }

    public Student(int rollNo, String name, String email, String course, double marks, String grade, String researchArea) {
        this(rollNo, name, email, course, marks, grade);
        this.researchArea = researchArea;
    }

    @Override
    public void displayInfo() {
        System.out.println("Student Info:");
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Course: " + course);
        if (researchArea != null) {
            System.out.println("Research Area: " + researchArea);
        }
        System.out.println();
    }

    public void displayInfo(boolean simple) {
        if (simple) {
            System.out.println("Student Info:");
            System.out.println("Roll No: " + rollNo);
            System.out.println("Name: " + name);
            System.out.println("Email: " + email);
            System.out.println("Course: " + course);
            System.out.println();
        } else {
            displayInfo();
        }
    }

    public final void showFinalMessage() {
        System.out.println("This is a final method in a final class.");
    }

    // finalize method
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalize method called before object is garbage collected.");
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}

// Interface RecordActions
interface RecordActions {

    void addStudent(Student student);

    void deleteStudent(int rollNo);

    void updateStudent(int rollNo, String course, double marks, String grade);

    Student searchStudent(int rollNo);

    List<Student> viewAllStudents();
}

class StudentManager implements RecordActions {

    private Map<Integer, Student> studentMap;

    public StudentManager() {
        studentMap = new HashMap<>();
    }

    @Override
    public void addStudent(Student student) {
        if (studentMap.containsKey(student.getRollNo())) {
            System.out.println("Student with Roll No " + student.getRollNo() + " already exists!");
        } else {
            studentMap.put(student.getRollNo(), student);
            System.out.println("Student added successfully.");
        }
    }

    @Override
    public void deleteStudent(int rollNo) {
        if (studentMap.remove(rollNo) != null) {
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    @Override
    public void updateStudent(int rollNo, String course, double marks, String grade) {
        Student student = studentMap.get(rollNo);
        if (student != null) {
            student.setCourse(course);
            student.setMarks(marks);
            student.setGrade(grade);
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    @Override
    public Student searchStudent(int rollNo) {
        return studentMap.get(rollNo);
    }

    @Override
    public List<Student> viewAllStudents() {
        return new ArrayList<>(studentMap.values());
    }
}

public class Inheritance {

    public static void main(String[] args) {
        StudentManager manager = new StudentManager();

        Student s1 = new Student(101, "Namrata", "Namrata071@mail.com", "BCA", 95, "A+");
        Student s2 = new Student(102, "Vivan", "vivan123@mail.com", "B.Tech", 80, "A", "AI");

        manager.addStudent(s1);
        manager.addStudent(s2);

        for (Student s : manager.viewAllStudents()) {
            s.displayInfo();
            
        }

        System.out.println("[Note] Overloaded display method:");
        s1.displayInfo(true);

        s1.showFinalMessage();

        Student searchResult = manager.searchStudent(102);
        if (searchResult != null) {
            System.out.println("\nFound Student:");
            searchResult.displayInfo();
        }

        manager.updateStudent(101, "B.Tech CSE", 88, "A+");

        manager.deleteStudent(102);

        System.out.println("\nFinal Student List:");
        for (Student s : manager.viewAllStudents()) {
            s.displayInfo();
        }

        s1 = null;
        s2 = null;
        System.gc();
    }
}