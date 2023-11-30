package lms.service.impl;

import lms.Main;
import lms.database.Database;
import lms.models.Group;
import lms.models.Lesson;
import lms.models.Student;
import lms.service.GroupService;

import java.util.Arrays;
import java.util.Scanner;

public class GroupServiceImpl implements GroupService {


    @Override
    public Group newGroup(Group group, long id) {
        Scanner scanner = new Scanner(System.in);
        group.setId(id);
        System.out.print("Группанын атын жазыңыз: ");
        group.setName(Main.chekScanner(scanner.nextLine()));
        System.out.print("Группага суроттомо: ");
        group.setDescription(Main.chekScanner(scanner.nextLine()));
        return group;
    }

    @Override
    public void addNewGroup(Group newGroup) {
        Group[] groups = Arrays.copyOf(Database.groups, Database.groups.length + 1);
        groups[groups.length - 1] = newGroup;
        Database.groups = groups;
        System.out.println("Ийгиликтүү кошулду");
    }

    @Override
    public Group getGroupByName(String name) {
        for (Group group : Database.groups) {
            if (group.getName().equals(name)) {
                return group;
            }
        }
        return null;
    }

    @Override
    public void updateGroupName(String name) {
        for (Group group : Database.groups) {
            if (group.getName().equals(name)) {
                System.out.print("Жаңы ат киргизиңиз: ");
                group.setName(Main.chekScanner(new Scanner(System.in).nextLine()));
                System.out.println("Ийгиликтүү өзгөртүлдү");
            }
        }
    }

    @Override
    public Group[] getAllGroups() {
        return Database.groups;
    }

    @Override
    public void addNewStudentToGroup(Student student, Group group) {
        Student[] students = Arrays.copyOf(group.getStudents(), group.getStudents().length + 1);
        students[students.length - 1] = student;
        group.setStudents(students);
        System.out.println("Ийгиликтүү кошулду");
    }


    @Override
    public void addNewLessonToGroup(Lesson lesson, Group group) {
        Lesson[] lessons = Arrays.copyOf(group.getLessons(), group.getLessons().length + 1);
        lessons[lessons.length - 1] = lesson;
        group.setLessons(lessons);
        System.out.println("Ийгиликтүү кошулду");
    }


    @Override
    public void deleteGroupByName(String name) {
        Group[] groups = Database.groups;
        boolean has = false;
        for (int i = 0; i < groups.length; i++) {
            if (groups[i].getName().equals(name)) {
                for (int j = i; j < groups.length - 1; j++) {
                    groups[j] = groups[j + 1];
                }
                has = true;
                System.out.println("Ийгиликтүү өчүрүлдү");
            }
        }
        if (has) {
            Database.groups = Arrays.copyOf(groups, groups.length - 1);
        } else System.out.println("Мындай аттагы группа жок!");
    }


}


