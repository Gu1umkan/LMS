package lms.models;

import lms.Main;
import lms.database.Database;
import lms.service.esceptions.MyException;

import java.util.*;

public class Group {
    private long id;
    private String name;
    private String description;
    private LinkedHashSet<Student> students = new LinkedHashSet<>();
    private LinkedHashSet<Lesson> lessons = new LinkedHashSet<>();

    public Group() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = unikName(name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LinkedHashSet<Student> getStudents() {
        return students;
    }

    public void setStudents(LinkedHashSet<Student> students) {
        this.students = students;
    }

    public LinkedHashSet<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(LinkedHashSet lessons) {
        this.lessons = lessons;
    }

    private String unikName(String name) {
        while (true) {
            int counter = 0;
            try {
                for (Group group : Database.groups) {
                    if (Objects.equals(group.getName(), name)) {
                        counter++;
                    }
                }
                if (counter == 0) {
                    return name;
                } else throw new MyException("Группанын аты уникалдуу болушу керек");
            } catch (MyException e) {
                System.out.println(e.getMessage());
                name = Main.chekScanner(new Scanner(System.in).nextLine());
            }
        }
    }

    @Override
    public String toString() {
        return STR. "\nGroup{ " +
                "id: " + id +
                "     name: " + name +
                "     description: " + description +
                "\nstudents: " + students +
                "\nlessons: " + lessons +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(name, group.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
