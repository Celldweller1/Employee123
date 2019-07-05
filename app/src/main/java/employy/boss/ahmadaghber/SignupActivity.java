package employy.boss.ahmadaghber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SignupActivity extends AppCompatActivity {
    private EditText firstnameEditText,
            lastnameEditText,
            signupEmailEditText,
            signupPasswordEditText;
    private Button signupButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firstnameEditText = findViewById(R.id.firstnameEditText);
        lastnameEditText = findViewById(R.id.lastnameEditText);
        signupEmailEditText = findViewById(R.id.signupEmailEditText);
        signupPasswordEditText = findViewById(R.id.signupPasswordEditText);
        signupButton = findViewById(R.id.signupButton);



        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Employee employee = new Employee();

                if(isValidEmail(signupEmailEditText.getText().toString()))
                    employee.setEmail(signupEmailEditText.getText().toString());
                else {
                    Toast.makeText(SignupActivity.this, "please enter correct email", Toast.LENGTH_LONG).show();
                    signupEmailEditText.setText("");
                    return ;
                }

                employee.setFirstName(firstnameEditText.getText().toString());
                employee.setLastName(lastnameEditText.getText().toString());

                if(signupPasswordEditText.getText().toString().length()>=7)
                    employee.setPassword(signupPasswordEditText.getText().toString());
                else {

                    Toast.makeText(SignupActivity.this, "please password length more than 7", Toast.LENGTH_LONG).show();
                    signupPasswordEditText.setText("");
                    return ;
                }

                new FirebaseDatabaseCollector().add(employee, new FirebaseDatabaseCollector.DataStatus() {
                    @Override
                    public void dataIsLoaded(ArrayList<Employee> employees, List<String> keys) {

                    }

                    @Override
                    public void dataIsInserted() {
                        Toast.makeText(SignupActivity.this,"Created",Toast.LENGTH_LONG).show();

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


        signupEmailEditText.setText("");
        signupPasswordEditText.setText("");
        firstnameEditText.setText("");
        lastnameEditText.setText("");



    }
    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
