import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Person {
    protected String name;
    protected int rollNumber;

    public Person(String name, int rollNumber) {
        this.name = name;
        this.rollNumber = rollNumber;
    }

    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Roll Number: " + rollNumber);
    }
}

class Student extends Person {
    private Map<String, Integer> marks;

    public Student(String name, int rollNumber) {
        super(name, rollNumber);
        this.marks = new HashMap<>();
    }

    public void addMark(String subject, int mark) {
        if (mark >= 0 && mark <= 100) {  
            marks.put(subject, mark);
        } else {
            System.out.println("Invalid mark for " + subject + ". Please enter a mark between 0 and 100.");
        }
    }

    public Map<String, Integer> getMarks() {
        return marks;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Marks:");
        for (Map.Entry<String, Integer> entry : marks.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}


class MarkCalculation {
    public static int calculateTotal(Map<String, Integer> marks) {
        int total = 0;
        for (int mark : marks.values()) {
            total += mark;
        }
        return total;
    }

    public static double calculateAverage(Map<String, Integer> marks) {
        if (marks.isEmpty()) return 0;
        return calculateTotal(marks) / (double) marks.size();
    }

    public static void displayStudentMarks(Student student) {
        student.displayInfo();
        System.out.println("Total Marks: " + calculateTotal(student.getMarks()));
        System.out.println("Average Marks: " + calculateAverage(student.getMarks()));
    }
}


public class InternalMarkCalculationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of students: ");
        int numStudents = scanner.nextInt();
        scanner.nextLine(); 

        String[] subjects = {"DAA", "DPV", "JAVA", "ML", "EVS", "DMLA", "CSD"};
        Student[] students = new Student[numStudents];

        
        for (int i = 0; i < numStudents; i++) {
            System.out.println("\nEnter details for Student " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Roll Number: ");
            int rollNumber = scanner.nextInt();
            scanner.nextLine(); 

            Student student = new Student(name, rollNumber);

            System.out.println("Enter marks for the subjects:");
            for (String subject : subjects) {
                System.out.print(subject + ": ");
                int mark = scanner.nextInt();
                student.addMark(subject, mark);
            }
            scanner.nextLine(); 
            students[i] = student;
        }

        
        for (Student student : students) {
            System.out.println("\n--- Student Details ---");
            MarkCalculation.displayStudentMarks(student);
        }

        scanner.close();
    }
}