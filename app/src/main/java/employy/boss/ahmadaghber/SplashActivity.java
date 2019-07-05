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

        //handler for splash
        Handler splashHandler = new Handler();

        /*final int time = 3000;
        splashHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                //Intent to start activity from Splash to Main activity
                Intent splashIntent = new Intent(SplashActivity.this , MainActivity.class);

                startActivity(splashIntent);
                Log.i(String.valueOf(time),"hello");
                finish();

            }
            //delay 3 Secs
            }, time);
        int i=0;*/
       new Thread(new Runnable() {
            public void run() {
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
