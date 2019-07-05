package employy.boss.ahmadaghber;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class SignupActivity extends AppCompatActivity {
    private EditText firstnameEditText,
            lastnameEditText,
            signupEmailEditText,
            signupPasswordEditText;
    private Button signupButton;
    private ProgressBar signupProgressBar;
    private FirebaseAuth emailandpasswordAuthentication ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupProgressBar = findViewById(R.id.signupProgressBar);
        firstnameEditText = findViewById(R.id.firstnameEditText);
        lastnameEditText = findViewById(R.id.lastnameEditText);
        signupEmailEditText = findViewById(R.id.signupEmailEditText);
        signupPasswordEditText = findViewById(R.id.signupPasswordEditText);
        signupButton = findViewById(R.id.signupButton);
        emailandpasswordAuthentication = FirebaseAuth.getInstance();

        signupProgressBar.setVisibility(View.GONE);


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signupProgressBar.setVisibility(View.VISIBLE);

                final Employee employee = new Employee();


                emailandpasswordAuthentication.createUserWithEmailAndPassword(
                        signupEmailEditText.getText().toString()
                          ,signupPasswordEditText.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //if registration is successful then add the data to database
                        if(task.isSuccessful()){

                            signupProgressBar.setVisibility(View.GONE);

                            //add employee to Realtime database
                            employee.setFirstName(firstnameEditText.getText().toString());
                            employee.setLastName(lastnameEditText.getText().toString());
                            employee.setEmail(signupEmailEditText.getText().toString());
                            employee.setPassword(signupPasswordEditText.getText().toString());




                            new FirebaseDatabaseCollector().add(employee, new FirebaseDatabaseCollector.DataStatus() {
                                @Override
                                public void dataIsLoaded(ArrayList<Employee> employees, List<String> keys) {

                                }

                                @Override
                                public void dataIsInserted() {

                                    //if there is data added to database then notify the user about it
                                    Toast.makeText(SignupActivity.this,"Registered successfully",Toast.LENGTH_LONG).show();

                                    signupEmailEditText.setText("");
                                    signupPasswordEditText.setText("");
                                    firstnameEditText.setText("");
                                    lastnameEditText.setText("");

                                    //move to login activity
                                    startActivity(new Intent(SignupActivity.this,MainActivity.class));
                                }

                                @Override
                                public void dataIsUpdated() {

                                }

                                @Override
                                public void dataIsDeleted() {

                                }
                            });

                        }
                        else
                        {
                            //if registration is not successful then send message
                            signupProgressBar.setVisibility(View.GONE);

                            Toast.makeText(SignupActivity.this
                                    ,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                });


            }
        });



    }
}
