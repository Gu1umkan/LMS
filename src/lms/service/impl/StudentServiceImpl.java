package lms.service.impl;

import lms.Main;
import lms.database.Database;
import lms.models.Group;
import lms.models.Student;
import lms.service.StudentService;

import java.util.Arrays;
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
        for (int i = 0; i < Database.groups.length; i++) {
            for (int k = 0; k < Database.groups[i].getStudents().length; k++) {
                if (Objects.equals(Database.groups[i].getStudents()[k].getId(), id)) {
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
                                Student student = Database.groups[i].getStudents()[k];
                                student.setName(scanner.nextLine());
                                System.out.println("Ийгиликтүү жаңыланды ");
                            }
                            case "2" ->{
                                System.out.print("Жаңы фамилиясын киргизиңиз: ");
                                Database.groups[i].getStudents()[k].setLastName(Main.chekScanner(scanner.nextLine()));
                                System.out.println("Ийгиликтүү жаңыланды ");
                            }
                            case "3" -> {
                                System.out.println("Жаңылоо аяктады"+ Database.groups[i].getStudents()[k]);
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
    public void findStudentByFirstName(String firstname) {
        for (int i = 0; i < Database.groups.length; i++) {
            Student[] students = Database.groups[i].getStudents();
            for (Student student : students) {
                if (student.getName().equals(firstname)) {
                    System.out.println(student);
                }
            }
        }
    }

    @Override
    public Student[] getAllStudentsByGroupName(Group group) {
        return group.getStudents();
    }

    @Override
    public void deleteStudentByEmail(String email) {
        boolean have = false;
        for (int i = 0; i < Database.groups.length; i++) {
            Student[] students = Database.groups[i].getStudents();
            for (int j = 0; j < students.length; j++) {
                if (students[j].getEmail().equals(email)) {
                    for (int k = j; k < students.length - 1; k++) {
                        students[k] = students[k + 1];
                    }
                    have = true;
                }
            }
            if (have) {
                students = Arrays.copyOf(students, students.length - 1);
                Database.groups[i].setStudents(students);
                System.out.println("Ийгиликтүү өчүрүлдү");
            }
        }
        if (!have) {
            System.out.println(email + " почтасы табылган жок");
        }
    }
}