package lms.service;

import lms.models.Group;
import lms.models.Student;

public interface StudentService {
    Student createStudent(Student student,long id);
    void  updateStudent(long id);
    void findStudentByFirstName(String firstname);
    Student[] getAllStudentsByGroupName(Group group);
    void  deleteStudentByEmail(String email);

}
