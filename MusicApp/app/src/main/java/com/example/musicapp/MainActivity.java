package com.example.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.musicapp.activities.LogoActivity;
import com.example.musicapp.ui.main.SectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        //ViewPager viewPager = findViewById(R.id.view_pager);
        //viewPager.setAdapter(sectionsPagerAdapter);
        //TabLayout tabs = findViewById(R.id.tabs);
        //tabs.setupWithViewPager(viewPager);

        Intent objIntent = new Intent( this, LogoActivity.class);
        startActivity(objIntent);


    }
}