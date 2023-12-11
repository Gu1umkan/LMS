package lms.service.impl;

import lms.Main;
import lms.database.Database;
import lms.models.Group;
import lms.models.Lesson;
import lms.models.Student;
import lms.service.GroupService;

import java.util.LinkedHashSet;
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
        Database.groups.add(newGroup);
        System.out.println("Ийгиликтүү кошулду ✅");
    }

    @Override
    public Group getGroupByName(String name) {
        for (Group group : Database.groups) {
            if (group.getName().equalsIgnoreCase(name)) {
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
                System.out.println("Ийгиликтүү өзгөртүлдү ✅");
            }
        }
    }

    @Override
    public LinkedHashSet<Group> getAllGroups() {
        return Database.groups;
    }

    @Override
    public void addNewStudentToGroup(Student student, Group group) {
        group.getStudents().add(student);
        System.out.println("Ийгиликтүү кошулду ✅");
    }


    @Override
    public void addNewLessonToGroup(Lesson lesson, Group group) {
        group.getLessons().add(lesson);
        System.out.println("Ийгиликтүү кошулду ✅");
    }


    @Override
    public boolean deleteGroupByName(String name) {
        for (Group group : Database.groups) {
            if (group.getName().equals(name)) {
                return Database.groups.remove(group);
            }
        }
        return false;
    }

}


