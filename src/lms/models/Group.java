package lms.models;

import lms.Main;
import lms.database.Database;
import lms.service.esceptions.MyException;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Group {
    private long id;
    private String name;
    private String description;
    private Student[] students = new Student[0];
    private Lesson[] lessons = new Lesson[0];

    public Group() {
    }

    public Group(long id, String name, String description, Student[] students, Lesson[] lessons) {
        this.id = id;
        this.name = unikName(name);
        this.description = description;
        this.students = students;
        this.lessons = lessons;
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

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }

    public Lesson[] getLessons() {
        return lessons;
    }

    public void setLessons(Lesson[] lessons) {
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
        return "\nGroup{ " +
                "id: " + id +
                "     name: " + name +
                "     description: " + description +
                "\nstudents: " + Arrays.toString(students) +
                "\nlessons: " + Arrays.toString(lessons) +
                '}';
    }
}
