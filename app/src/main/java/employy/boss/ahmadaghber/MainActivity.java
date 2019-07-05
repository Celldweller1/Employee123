package employy.boss.ahmadaghber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText loginEmailEditText,loginPasswordEditText;
    private Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        loginButton = findViewById(R.id.loginButton);
        loginEmailEditText = findViewById(R.id.loginEmailEditText);
        loginPasswordEditText = findViewById(R.id.loginPasswordEditText);




        new FirebaseDatabaseCollector().readEmployee(new FirebaseDatabaseCollector.DataStatus() {
            @Override
            public void dataIsLoaded(final ArrayList<Employee> employees, List<String> keys) {
                loginButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        for(int index=0 ; index<employees.size();index++){
                            if(employees.get(index).getEmail().equals(loginEmailEditText.getText().toString())){
                                Log.i("hellooooo" ,"it';s done email");
                            }
                        }

                        for(int index=0 ; index<employees.size();index++){
                            if(employees.get(index).getPassword().equals(loginPasswordEditText.getText().toString())){
                                Log.i("hellooooo" ,"it';s done password");
                            }
                        }
                    }
                });



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

    public void signUp(View view) {
        startActivity(new Intent(MainActivity.this , SignupActivity.class));
    }
}
