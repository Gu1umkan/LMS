package lms.models.role;

import lms.Main;
import lms.models.Student;
import lms.service.esceptions.MyException;

import java.util.Objects;
import java.util.Scanner;

public class Admin {
    private final String firstName = "Peaksoft";
    private String lastName = "House";
    private String email = "peaksoft@gmail.com";
    private String password = "peaksoft2023";

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = Student.chekPassword(password);
    }

    public boolean login() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Почтаңызды жазыңыз: ");
                String email = Main.chekScanner(scanner.nextLine());
                if (Objects.equals(this.email, email)) {
                    System.out.print("Паролду киргизиңиз: ");
                    String password = Main.chekScanner(Main.chekScanner(scanner.nextLine()));
                    if (Objects.equals(this.password, password)) {
                        return true;
                    } else throw new MyException("Пароль туура эмес!");
                } else throw new MyException("Почта туура эмес!");
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void updatePassword() {
        Scanner scanner = new Scanner(System.in);
        outloop:
        while (true) {
            try {
                System.out.print("Пароль өзгөртүү үчүн почтаңызды жазыңыз: ");
                String email = Main.chekScanner(scanner.nextLine());
                if (Objects.equals(this.email, email)) {
                    System.out.print("Жаңы паролду киргизиңиз: ");
                    this.password = Main.chekScanner(scanner.nextLine());
                    break outloop;
                } else throw new MyException("Почта туура эмес!");
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public String toString() {
        return "Admin{" +
                "\nfirstName: " + firstName +
                "\nlastName: " + lastName +
                "\nemail: " + email +
                "\npassword: " + password +
                " }";
    }
}
