package lms.models;

import lms.database.Database;
import lms.models.enums.Gender;
import lms.service.esceptions.MyException;

import java.util.Objects;
import java.util.Scanner;

public class Student {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private Gender gender;
    private  long id;

    public Student() {
    }

    public Student(String name, String lastName, String email, String password, Gender gender,long id) {
        this.name = name;
        this.lastName = lastName;
        this.email = chekEmail(email);
        this.password = chekPassword(password);
        this.gender = gender;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = chekEmail(email);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = chekPassword(password);
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = chekGender(gender);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String chekEmail(String email) {
        while (true) {
            try {
                if (isValidEmail(email) && isEmailUnique(email)) {
                    return email;
                } else if (!isValidEmail(email)) {
                    throw new MyException("email должно заканчиваться на \"@gmail.com\"");
                } else {
                    throw new MyException("email уже используется.\n Пожалуйста, введите другой email : ");
                }
            } catch (MyException e) {
                System.out.println(e.getMessage());
                email = new Scanner(System.in).nextLine();
            }
        }
    }

    private boolean isValidEmail(String email) {
        return email.endsWith("@gmail.com") && email.length() > "@gmail.com".length();
    }

    private boolean isEmailUnique(String email) {
        for (int i = 0; i < Database.groups.length; i++) {
            Student[] students = Database.groups[i].getStudents();
            for (Student student : students) {
                if (Objects.equals(student.getEmail(), email)) {
                    return false;
                }
            }
        }
        return true;
    }
    private Gender chekGender(String gender) {
        while (true) {
            if (gender.equals("male")) {
                return Gender.MALE;
            } else if (gender.equals("female")) {
                return Gender.FEMALE;
            } else {
                System.out.println("Error gender");
                System.out.print("Кайрадан киргиз: ");
                gender = new Scanner(System.in).nextLine();
            }
        }
    }
    public static String chekPassword(String password) {
        while (true) {
            if (password.length() > 3){
                return password;
            }
            else {
                System.out.println("Пароль 4төн көп 12 ден аз символду камтыш керек❗️");
                password = new Scanner(System.in).nextLine();
            }
        }

    }

    @Override
    public String toString() {
        return "\nStudent{ "+
                " id: " + id +
                "     name: " + name +
                "     lastName: " + lastName +
                "     email: " + email +
                "     password: " + password +
                "     gender: " + gender +
                '}';
    }
}
