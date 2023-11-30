package lms.models;

import java.util.Arrays;

public class Lesson {
    private  String name;
    private String description;
    private  long id;

    public Lesson() {
    }
    public Lesson(String name, String description, long id) {
        this.name = name;
        this.description = description;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "\nLesson{" +
                "id: " + id+
                "     name: " + name +
                "     description='" + description +
                '}';
    }
}
