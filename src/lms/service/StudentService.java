package lms.service;

import lms.models.Group;
import lms.models.Student;

import java.util.LinkedHashSet;

public interface StudentService {
    Student createStudent(Student student,long id);
    void  updateStudent(long id);
    Student findStudentByFirstName(String firstname);
    LinkedHashSet<Student> getAllStudentsByGroupName(Group group);
    boolean  deleteStudentByEmail(String email);

}
