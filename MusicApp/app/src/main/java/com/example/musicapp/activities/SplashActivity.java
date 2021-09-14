package com.example.musicapp.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.musicapp.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    SplashActivity objSplashActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_main);
        objSplashActivity = this;
        Timer objTimer = new Timer();
        TimerTask objTimerTask = new TimerTask() {
            @Override
            public void run() {
                Intent objIntent = new Intent(objSplashActivity, MainTabActivity.class);
                objSplashActivity.startActivity(objIntent);
            }
        };
        objTimer.schedule(objTimerTask , 3000);
    }
}
