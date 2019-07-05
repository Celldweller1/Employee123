package employy.boss.ahmadaghber;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Home_ScreenActivity extends AppCompatActivity {
    private RecyclerView tasksRecyclerView;
    private RecyclerViewAdapter adapter;
    private SearchView taskSearchView;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__screen);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        taskSearchView = findViewById(R.id.taskSearchView);
        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);


        //to get database information
        new FirebaseDatabaseCollector().readEmployee(new FirebaseDatabaseCollector.DataStatus() {
            @Override
            public void dataIsLoaded(ArrayList<Employee> employees, List<String> keys) {
                //employees contain all employees in database
                for(int i=0;i<employees.size();i++){
                    //if current user email is equal any employee user email get it but it must be unique
                    if(user.getEmail().equals(employees.get(i).getEmail()))
                    {
                        adapter = new RecyclerViewAdapter(employees.get(i).getTask(),Home_ScreenActivity.this);
                        tasksRecyclerView.setAdapter(adapter);
                        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(Home_ScreenActivity.this));
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



        taskSearchView.setActivated(true);
        taskSearchView.setQueryHint("Task");
        taskSearchView.onActionViewExpanded();
        taskSearchView.setIconified(false);
        taskSearchView.clearFocus();


        taskSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);

                return false;
            }
        });

        //for each item selected from navigation
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.ic_home){

                }
                    else if(menuItem.getItemId()==R.id.ic_search){
                        startActivity(new Intent(Home_ScreenActivity.this, Settings_Activity.class));
                }
                return false;
            }
        });

    }
}
