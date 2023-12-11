package lms.service.impl;

import lms.Main;
import lms.database.Database;
import lms.models.Group;
import lms.models.Student;
import lms.service.StudentService;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Scanner;

public class StudentServiceImpl implements StudentService {


    @Override
    public Student createStudent(Student student, long id) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Студенттин атын киргизиңиз: ");
        student.setName(Main.chekScanner(scanner.nextLine()));
        System.out.print("Студенттин фамилиясын киргизиңиз: ");
        student.setLastName(Main.chekScanner(scanner.nextLine()));
        System.out.print("Студенттин почтасын киргизииз: ");
        student.setEmail(Main.chekScanner(scanner.nextLine()));
        System.out.print("Студенттин паролу: ");
        student.setPassword(Main.chekScanner(scanner.nextLine()));
        System.out.print("Gender: ");
        student.setGender(Main.chekScanner(scanner.nextLine()));
        student.setId(id);
        return student;
    }

    @Override
    public void updateStudent(long id) {
        Scanner scanner = new Scanner(System.in);
        for (Group group : Database.groups) {
            for (Student student : group.getStudents()) {
                if (student.getId() == id) {
                    outloop:
                    while (true) {
                        System.out.println(STR."""
                                1.Атын жаңылоо
                                2.Фамилиясын жаңылоо
                                3.Жаңылоону аяктоо
                                Сиздин тандоо: 
                                """);
                        switch (scanner.nextLine()) {
                            case "1" -> {
                                System.out.print("Студенттин жаңы атын киргизиңиз: ");
                                student.setName(scanner.nextLine());
                                System.out.println("Ийгиликтүү жаңыланды ");
                            }
                            case "2" -> {
                                System.out.print("Жаңы фамилиясын киргизиңиз: ");
                                student.setLastName(Main.chekScanner(scanner.nextLine()));
                                System.out.println("Ийгиликтүү жаңыланды ");
                            }
                            case "3" -> {
                                System.out.println("Жаңылоо аяктады" + student);
                                break outloop;
                            }
                            default -> System.out.println("туура эмес тандоо");
                        }
                    }
                }
                System.out.printf("Бул id де студент жок");
            }
        }
    }

    @Override
    public Student findStudentByFirstName(String firstname) {
        for (Group group : Database.groups) {
            for (Student student : group.getStudents()) {
                if (student.getName().equals(firstname)) {
                    return student;
                }
            }
        }
        return null;
    }

    @Override
    public LinkedHashSet<Student> getAllStudentsByGroupName(Group group) {
        return group.getStudents();
    }

    @Override
    public boolean deleteStudentByEmail(String email) {
        for (Group group : Database.groups) {
            for (Student student : group.getStudents()) {
                if (student.getEmail().equals(email)) {
                    return group.getStudents().remove(student);
                }
            }

        }
        return false;
    }
}