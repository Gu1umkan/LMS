package lms.service;

import lms.models.Group;
import lms.models.Lesson;
import lms.models.Student;

import java.util.LinkedHashSet;

public interface GroupService {

   Group newGroup(Group group,long id);

   void addNewGroup(Group newGroup) ;
   Group getGroupByName(String name);
   void updateGroupName(String  name);
   LinkedHashSet<Group> getAllGroups();
   void addNewStudentToGroup(Student student,Group group);
   void  addNewLessonToGroup(Lesson lesson,Group group);
   boolean  deleteGroupByName(String name);


}
