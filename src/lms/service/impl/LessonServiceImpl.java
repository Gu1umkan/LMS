package lms.service.impl;

import lms.Main;
import lms.database.Database;
import lms.models.Group;
import lms.models.Lesson;
import lms.models.Student;
import lms.service.LessonService;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Scanner;

public class LessonServiceImpl implements LessonService {

    @Override
    public Lesson newLesson(Lesson lesson, long id) {
        System.out.print("Сабактын атын киргиз: ");
        lesson.setName(Main.chekScanner(new Scanner(System.in).nextLine()));
        System.out.print("Сабактын суроттомосу: ");
        lesson.setDescription(Main.chekScanner(new Scanner(System.in).nextLine()));
        lesson.setId(id);
        return lesson;
    }

    @Override
    public Lesson getLessonByName(String name) {
        for (Group group : Database.groups) {
            for (Lesson lesson : group.getLessons()) {
                if (lesson.getName().equals(name)) {
                    return lesson;
                }
            }
        }
        return null;
    }

    @Override
    public LinkedHashSet<Lesson> getLessonByGroupName(Group group) {
        return group.getLessons();
    }

    @Override
    public boolean deleteLessonById(long id) {
        for (Group group : Database.groups) {
            for (Lesson lesson : group.getLessons()) {
                if (lesson.getId() == id)
                    return group.getLessons().remove(lesson);
            }
        }return false;
    }
    @Override
    public LinkedHashSet<Lesson> getAllLessonStudents(String name) {
        boolean have = false;
        for (Group group : Database.groups) {
            for (Student student : group.getStudents()) {
                if (student.getName().equalsIgnoreCase(name)) {
                    have = true;
                    return group.getLessons();
                }
            }
        }
        if (!have) System.out.println("Мындай студент жок❗️");
        return null;
    }

}
