package employy.boss.ahmadaghber;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Settings_Activity extends AppCompatActivity {

    private TextView logoutTextView;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        //for each item selected from navigation
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.ic_home)
                {
                    startActivity(new Intent(Settings_Activity.this , Home_ScreenActivity.class));
                }
                else if(menuItem.getItemId() == R.id.ic_search){

                }
                return false;
            }
        });
    }

    public void logoutClick(View view) {
        logoutTextView= view.findViewById(R.id.logoutTextView);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        //logout from database
        FirebaseAuth.getInstance().signOut();
        Intent intent =new Intent(Settings_Activity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }
}
