package employy.boss.ahmadaghber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class Home_ScreenActivity extends AppCompatActivity {
    private Employee employee = new Employee();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__screen);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        new FirebaseDatabaseCollector().readEmployee(new FirebaseDatabaseCollector.DataStatus() {
            @Override
            public void dataIsLoaded(ArrayList<Employee> employees, List<String> keys) {
                for(int i=0;i<employees.size();i++){
                    if(user.getEmail().equals(employees.get(i).getEmail()))
                    {
                        employee = employees.get(i);
                    }
                }
            }

            @Override
            public void dataIsInserted() {

            }

            @Override
            public void dataIsUpdated() {

            }

            @Override
            public void dataIsDeleted() {

            }
        });


    }
}
