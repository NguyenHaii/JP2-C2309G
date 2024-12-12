package test;

import entity.Student;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class StudentTest {
    public static void main(String[] args) throws IOException {
        List<Student> students = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            System.out.println("Enter details for student " + (i + 1));
            Student student = new Student();

            String rollNumber;
            do {
                System.out.print("Enter roll number (e.g., C0909G0908): ");
                rollNumber = reader.readLine();
            } while (!isValidRollNumber(rollNumber));
            student.setRollNumber(rollNumber);

            System.out.print("Enter name: ");
            student.setName(reader.readLine());

            System.out.print("Enter address: ");
            student.setAddress(reader.readLine());

            System.out.print("Enter age: ");
            student.setAge(Integer.parseInt(reader.readLine()));

            students.add(student);
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.bat"))) {
            oos.writeObject(students);
        }

        List<Student> readStudents;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student.bat"))) {
            readStudents = (List<Student>) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error reading students", e);
        }

        System.out.println("Students from file:");
        readStudents.forEach(System.out::println);

        System.out.println("Students under 18:");
        readStudents.stream()
                .filter(student -> student.getAge() < 18)
                .forEach(System.out::println);
    }

    private static boolean isValidRollNumber(String rollNumber) {
        String regex = "^[A-Za-z]{1,2}\\d{4}[A-Za-z]{0,2}\\d{4}$";
        return Pattern.matches(regex, rollNumber);
    }
}
