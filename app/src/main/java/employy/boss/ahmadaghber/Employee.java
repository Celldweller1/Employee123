package employy.boss.ahmadaghber;

import java.io.Serializable;
import java.util.ArrayList;

public class Employee implements Serializable {
    private String firstName,
    lastName,
    email,
    password;
    private ArrayList<String> task = new ArrayList<>();



    public Employee() {
        this.firstName = " ";
        this.lastName = " ";
        this.email = " ";
        this.password = " ";
        task.add("hello");
        task.add("no");
        task.add("yes");
    }

    public Employee(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public ArrayList<String> getTask() {
        return task;
    }

    public void setTask(ArrayList<String> task) {
        this.task = task;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
