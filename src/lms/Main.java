package lms;

import lms.models.Group;
import lms.models.Lesson;
import lms.models.Student;
import lms.models.role.Admin;
import lms.service.GroupService;
import lms.service.LessonService;
import lms.service.StudentService;
import lms.service.impl.GroupServiceImpl;
import lms.service.impl.LessonServiceImpl;
import lms.service.impl.StudentServiceImpl;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.StringTemplate.STR;

public class Main {
    public static void main(String[] args) {
        GroupService groupService = new GroupServiceImpl();
        StudentService studentService = new StudentServiceImpl();
        LessonService lessonService = new LessonServiceImpl();
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin();
        currentTime();
        while (true) {
            System.out.print("Катталган болсөнүз 1 ди басыңыз,паролду унутуп калып аны өзгөртүүнү кааласаңыз 2 ни басыңыз:");
            switch (chekScanner(scanner.nextLine())) {
                case "1" -> {
                    if (admin.login()) {
                        System.out.println("Кош келиңиз!");
                        outloop:
                        while (true) {
                            System.out.println(STR."""
                                    ******* Бир команда тандаңыз! *******
                                    1  -> Add new group
                                    2  -> Get group by name
                                    3  -> Update group name
                                    4  -> Get all groups
                                    5  -> Add new student to group
                                    6  -> Update student
                                    7  -> Find student by first name
                                    8  -> Get all students by group name
                                    9  -> Get all student's lesson
                                    10 -> Delete student by email
                                    11 -> Add new lesson to group
                                    12 -> Get lesson by name
                                    13 -> Get all lesson by group name
                                    14 -> Delete lesson by id
                                    15 -> Delete group by name
                                    0  -> Exit
                                                        
                                    Команда :
                                    """);
                            switch (scanner.nextLine()) {
                                case "1" -> {
                                    Group group = groupService.newGroup(new Group(), MyGenareteId.generateIdForGroup());
                                    groupService.addNewGroup(group);
                                }
                                case "2" -> {
                                    System.out.print("Группанын атын киргизиңиз: ");
                                    Group group = groupService.getGroupByName(chekScanner(scanner.nextLine()));
                                    if (group != null) {
                                        System.out.println(group);
                                    } else System.out.println("Мындай группа жок");
                                }
                                case "3" -> {
                                    System.out.print("Группанын атын киргизиңиз: ");
                                    groupService.updateGroupName(chekScanner(scanner.nextLine()));
                                }
                                case "4" -> {
                                    System.out.println(Arrays.toString(groupService.getAllGroups()));
                                }
                                case "5" -> {
                                    System.out.print("Кайсы группага жаңы студент кошосуз: ");
                                    Group group = groupService.getGroupByName(chekScanner(scanner.nextLine()));
                                    if (group != null) {
                                        Student student = studentService.createStudent(new Student(), MyGenareteId.generateIdForStudent());
                                        groupService.addNewStudentToGroup(student, group);
                                    } else System.out.println("Мындай группа жок");
                                }
                                case "6" -> {
                                    System.out.print("Жаңылоо  керек болгон студенттин id-син киргиз: ");
                                    studentService.updateStudent(chekLong());
                                }
                                case "7" -> {
                                    System.out.println(" Студенттин атын киргиз: ");
                                    studentService.findStudentByFirstName(scanner.nextLine());

                                }
                                case "8" -> {
                                    System.out.print("Группанын атын киргиз: ");
                                    Group group = groupService.getGroupByName(chekScanner(scanner.nextLine()));
                                    if (group != null) {
                                        Student[] students = studentService.getAllStudentsByGroupName(group);
                                        if (students.length != 0) {
                                            System.out.println(Arrays.toString(students));
                                        } else System.out.println(" Бул группада студенттер жок");
                                    } else System.out.println("Мындай группа жок");
                                }
                                case "9" -> {
                                    System.out.print("Сабактарын көргүңүз келген студенттин атын киргизиңиз: ");
                                    lessonService.getAllLessonStudents(chekScanner(scanner.nextLine()));

                                }
                                case "10" -> {
                                    System.out.print("Өчүрүү керек болгон студенттин почтасын киргизиңиз: ");
                                    studentService.deleteStudentByEmail(chekScanner(scanner.nextLine()));
                                }
                                case "11" -> {
                                    System.out.print("Кайсы группага сабак кошкуңуз келет: ");
                                    Group group = groupService.getGroupByName((chekScanner(scanner.nextLine())));
                                    if (group != null) {
                                        Lesson lesson = lessonService.newLesson(new Lesson(), MyGenareteId.generateIdForLesson());
                                        groupService.addNewLessonToGroup(lesson, group);
                                    }
                                }
                                case "12" -> {
                                    System.out.print("Сабактын атын киргизиңиз:");
                                    Lesson lesson = lessonService.getLessonByName(chekScanner(scanner.nextLine()));
                                    if (lesson != null)
                                        System.out.println(lesson);
                                    else System.out.println("Мындай сабак жок");
                                }
                                case "13" -> {
                                    System.out.print("Кайсы группанын сабактарын көргүңүз келет: ");
                                    Group group = groupService.getGroupByName(chekScanner(scanner.nextLine()));
                                    System.out.println(Arrays.toString(lessonService.getLessonByGroupName(group)));
                                }
                                case "14" -> {
                                    System.out.print("Өчүрүү керек болгон сабактын id-син киргизиңиз: ");
                                    lessonService.deleteLessonById(chekLong());
                                }
                                case "15" -> {
                                    System.out.println("Өчүрүү керек болгон группаны киргизиңиз:");
                                    groupService.deleteGroupByName(chekScanner(scanner.nextLine()));
                                }
                                case "0" -> {
                                    System.out.println();
                                    break outloop;
                                }
                                default -> System.out.println("Туура эмес тандоо");
                            }

                        }
                    }
                }
                case "2" -> {
                    admin.updatePassword();
                    System.out.println(" Пароль ийгиликтуу жаңыланды\n" + admin);
                }
                default -> System.out.println("Туура эмес тандоо");
            }
        }
    }

    public static String chekScanner(String scannerWord) {
        while (true) {
            if (!scannerWord.isBlank()) {
                return scannerWord;
            } else {
                System.err.println(" Бош болуусу мүмкун эмес , маалымат киргизиңиз : ");
                scannerWord = new Scanner(System.in).nextLine();
            }
        }
    }

    public static long chekLong() {
        while (true) {
            try {
                long id = new Scanner(System.in).nextLong();
                return id;
            } catch (InputMismatchException e) {
                System.out.println("long тибинде id киргиз: ");
            }
        }
    }

    public static void currentTime() {
        ZonedDateTime currentTime = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        int currentTimeHour = currentTime.getHour();
        if (currentTimeHour >= 4 && currentTimeHour < 11) {
            System.out.println("*************** Кутман таң! саат -> " + currentTime.format(formatter)+" ***************");
        } else if (currentTimeHour >= 11 && currentTimeHour < 17) {
            System.out.println("*************** Кутман күн! саат -> " + currentTime.format(formatter)+" ***************");
        } else if (currentTimeHour >= 17 && currentTimeHour < 22) {
            System.out.println("*************** Кутман кеч! саат -> " + currentTime.format(formatter)+" ***************");
        }else {
            System.out.println("*************** Кутман түн! саат -> " + currentTime.format(formatter)+" ***************");
        }
    }
}