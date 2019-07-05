package employy.boss.ahmadaghber;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText loginEmailEditText,
            loginPasswordEditText;
    private Button loginButton;
    private FirebaseAuth emailandpasswordAuthentication ;
    private ProgressBar loginProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginProgressBar = findViewById(R.id.loginProgressBar);
        emailandpasswordAuthentication = FirebaseAuth.getInstance();
        loginButton = findViewById(R.id.loginButton);
        loginEmailEditText = findViewById(R.id.loginEmailEditText);
        loginPasswordEditText = findViewById(R.id.loginPasswordEditText);

        loginProgressBar.setVisibility(View.GONE);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginProgressBar.setVisibility(View.VISIBLE);

                new FirebaseDatabaseCollector().readEmployee(new FirebaseDatabaseCollector.DataStatus() {
                    @Override
                    public void dataIsLoaded(final ArrayList<Employee> employees, List<String> keys) {
                        loginButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                emailandpasswordAuthentication.signInWithEmailAndPassword(
                                        loginEmailEditText.getText().toString()
                                         ,loginPasswordEditText.getText().toString())
                                          .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            loginProgressBar.setVisibility(View.GONE);

                                            startActivity(new Intent(MainActivity.this,Home_ScreenActivity.class));

                                        }
                                        else{
                                            loginProgressBar.setVisibility(View.GONE);
                                            Toast.makeText(MainActivity.this,
                                                    task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
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
        });

    }

    public void signUp(View view) {
        startActivity(new Intent(MainActivity.this , SignupActivity.class));
    }
}
