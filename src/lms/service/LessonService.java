package lms.service;

import lms.models.Group;
import lms.models.Lesson;

import java.util.LinkedHashSet;

public interface LessonService {
    Lesson newLesson(Lesson lesson,long id);
    Lesson getLessonByName(String name);
    LinkedHashSet<Lesson>  getLessonByGroupName(Group group);
    boolean  deleteLessonById(long id);

    LinkedHashSet<Lesson> getAllLessonStudents(String name);
}
