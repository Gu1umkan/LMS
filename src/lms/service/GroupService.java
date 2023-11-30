package lms.service;

import lms.models.Group;
import lms.models.Lesson;
import lms.models.Student;

public interface GroupService {

   Group newGroup(Group group,long id);

   void addNewGroup(Group newGroup) ;
   Group getGroupByName(String name);
   void updateGroupName(String  name);
   Group[] getAllGroups();
   void addNewStudentToGroup(Student student,Group group);
   void  addNewLessonToGroup(Lesson lesson,Group group);
   void  deleteGroupByName(String name);
  // Group searchGroup(String name);


}
