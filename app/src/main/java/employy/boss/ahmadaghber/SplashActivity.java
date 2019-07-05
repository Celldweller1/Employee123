package employy.boss.ahmadaghber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity {
    ProgressBar loadingProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        loadingProgressBar = findViewById(R.id.loadingProgressBar);

       new Thread(new Runnable() {
            public void run() {
                //300 * 10 is 3000 milliseconds which is 3 seconds for each 300 millisecond move the progressbar
                for (int progress=0; progress<100; progress+=10) {
                    try {
                        Thread.sleep(300);
                        loadingProgressBar.setProgress(progress);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                startActivity(new Intent(SplashActivity.this , MainActivity.class));
                finish();
            }
        }).start();

    }
}
