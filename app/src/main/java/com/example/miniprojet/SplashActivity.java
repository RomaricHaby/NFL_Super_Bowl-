package com.example.miniprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;

import com.example.miniprojet.API.Async_task_data;
import com.example.miniprojet.Model.SuperBowl;

import java.util.ArrayList;

public class SplashActivity extends Activity {
    public static ArrayList<SuperBowl> superBowlArrayList = new ArrayList<>();

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 3500;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash);

        new Async_task_data().execute(superBowlArrayList);


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this,MainActivity.class);
                mainIntent.putExtra("list", superBowlArrayList);

                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}