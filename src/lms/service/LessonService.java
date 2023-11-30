package lms.service;

import lms.models.Group;
import lms.models.Lesson;

public interface LessonService {
    Lesson newLesson(Lesson lesson,long id);
    Lesson getLessonByName(String name);
    Lesson[]  getLessonByGroupName(Group group);
    void  deleteLessonById(long id);

    void getAllLessonStudents(String name);
}
