package lms.service.impl;

import lms.Main;
import lms.database.Database;
import lms.models.Group;
import lms.models.Lesson;
import lms.models.Student;
import lms.service.LessonService;

import java.util.Arrays;
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
            Lesson[] lessons = group.getLessons();
            for (Lesson lesson : lessons) {
                if (lesson.getName().equals(name)) {
                    return lesson;
                }
            }
        }
        return null;
    }

    @Override
    public Lesson[] getLessonByGroupName(Group group) {
            return group.getLessons();
    }

    @Override
    public void deleteLessonById(long id) {
        boolean have = false;
        for (int i = 0; i < Database.groups.length; i++) {
            Lesson[] lessons = Database.groups[i].getLessons();
            for (int j = 0; j < lessons.length; j++) {
                if (lessons[j].getId() == id) {
                    for (int k = j; k < lessons.length - 1; k++) {
                        lessons[k] = lessons[k + 1];
                    }
                    have = true;
                    System.out.println("Ийгиликтүү өчүрүлдү");
                }
            }
            if (have) {
                lessons = Arrays.copyOf(lessons, lessons.length - 1);
                Database.groups[i].setLessons(lessons);
            } else System.out.println("Бул id де сабак жок");
        }
    }


    @Override
    public void getAllLessonStudents(String name) {
        boolean have = true;
        for (int i = 0; i < Database.groups.length; i++) {
            for (Student student : Database.groups[i].getStudents()) {
                if (Objects.equals(student.getName(), name)) { have = false;
                    if (Database.groups[i].getLessons().length != 0){
                    System.out.println(Arrays.toString((Database.groups[i].getLessons())));
                   }
                    else System.out.println("Студенттин сабактары азырынча жок ");
                }
            }
        }
        if (have) {
            System.out.println("Мындай студент жок");
        }
    }

}
