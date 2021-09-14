package com.example.musicapp.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.musicapp.R;

import java.util.Timer;
import java.util.TimerTask;

public class LogoActivity extends AppCompatActivity {

    LogoActivity objLogoActivity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logo);
        objLogoActivity = this;

        Timer objTimer = new Timer();
        TimerTask objTimerTask = new TimerTask() {
            @Override
            public void run() {
                Intent objIntent = new Intent(objLogoActivity, MainTabActivity.class);
                objLogoActivity.startActivity(objIntent);
            }
        };
        objTimer.schedule(objTimerTask, 1000);
    }
}

