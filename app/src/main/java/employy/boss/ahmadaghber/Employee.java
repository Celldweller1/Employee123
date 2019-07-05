package employy.boss.ahmadaghber;


import java.util.ArrayList;

public class Employee {
    private String firstName,
    lastName,
    email,
    password;
    private ArrayList<String> task ;

    //constructor with parameters
    public Employee(String firstName, String lastName, String email, String password, ArrayList<String> task) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.task = task;
    }


    //constructor
    public Employee() {
        task  = new ArrayList<String>();
        // array of  tasks
        String [] tasks ={"do android project" , "do firebase android project" , "upload your presentation"
                , "make sure to like TTI pages" , "go to an event" , "go and follow TTI on twitter" , "go and check TTI website"
                , "go and subscribe to TTI on youtube"};

        //random variable
        int rand;

        for(int i=0;i<4;i++){
            rand = (int) (Math.random() * tasks.length) + 0;
            //add tasks[random variable]  to task
            if(tasks[rand]!= " ")
            task.add(tasks[rand]);
            tasks[rand]=" ";
        }
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
