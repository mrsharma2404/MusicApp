package com.example.musicapp.activities;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.musicapp.LoadMp3Resources;
import com.example.musicapp.R;
import com.example.musicapp.services.SongServiceClass;
import com.example.musicapp.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainTabActivity extends AppCompatActivity {
    public String [] strPermission = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};

    //this is for setting array
    public  static  ArrayList<String> objSongPath;
    public  static  ArrayList<String> objSongName;
    public  static  ArrayList<String> objSongDuration;
    public  static  ArrayList<Bitmap> objSongThumnail;
    public  static  ArrayList<String> objSongBitrate;

    //this is for bottom bar
    public static TextView objcrrSongName;
    public static TextView objcrrSongTime;
    public static TextView objcrrSongend;
    public static ImageView objcrrSongThumnail;
    public static SeekBar objcrrSongSeekBar;




    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        objSongPath = new ArrayList<String>();
        objSongName = new ArrayList<String>();
        objSongBitrate = new ArrayList<String>();
        objSongDuration = new ArrayList<String>();
        objSongThumnail = new ArrayList<Bitmap>();

        //for bottom bar
        objcrrSongName = findViewById(R.id.crrsongname);
        objcrrSongThumnail = findViewById(R.id.crrsongimg);
        objcrrSongSeekBar = findViewById(R.id.progressbar);
        objcrrSongend = findViewById(R.id.crr2time);
        objcrrSongTime = findViewById(R.id.crrtime);


        if(ContextCompat.checkSelfPermission(this, strPermission[1]) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, strPermission, 1);
        }
        else {
            //Log.e("check", "before else" );
            int iErr = 0;
            try {
                LoadMp3Files();
                SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
                ViewPager viewPager = findViewById(R.id.view_pager);
                viewPager.setAdapter(sectionsPagerAdapter);
                TabLayout tabs = findViewById(R.id.tabs);
                tabs.setupWithViewPager(viewPager);

                Intent objIntent = new Intent(this, SongServiceClass.class);
                if (objServiceConnection == null)
                    objServiceConnection = new ServiceConnectionClass();
                bindService(objIntent, objServiceConnection, Context.BIND_AUTO_CREATE);

                // Log.e("check", "else" );
            } catch (Exception e) {
                iErr = 1;

            }

            if (iErr == 1){

                SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
                ViewPager viewPager = findViewById(R.id.view_pager);
                viewPager.setAdapter(sectionsPagerAdapter);
                TabLayout tabs = findViewById(R.id.tabs);
                tabs.setupWithViewPager(viewPager);
            }

        }

        ImageButton objPauseButton = findViewById(R.id.pbtn);
        objPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainTabActivity.objSongServiceClass.PauseSong();
            }

        });


    }
    ServiceConnectionClass objServiceConnection;
    public  static SongServiceClass objSongServiceClass;

    public class ServiceConnectionClass implements android.content.ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            objSongServiceClass = ((SongServiceClass.LocalBinder)service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }




    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onRequestPermissionsResult(int iRequestCode, String[] strPermission,  int[] intGrantResults) {
        if (iRequestCode == 1) {
            if(intGrantResults[0] == PackageManager.PERMISSION_GRANTED)
            {






                LoadMp3Files();
                SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
                ViewPager viewPager = findViewById(R.id.view_pager);
                viewPager.setAdapter(sectionsPagerAdapter);
                TabLayout tabs = findViewById(R.id.tabs);
                tabs.setupWithViewPager(viewPager);
                Log.e("check", "if");
            }
            else
                {
                    ActivityCompat.requestPermissions(this, strPermission, 1);
                }
        }
        else{
            Log.e("check", "error in requestcode");
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private  void  LoadMp3Files(){
        LoadMp3Resources objLoadMp3Resources = new LoadMp3Resources(this);
        objLoadMp3Resources.LoadAllSongs();
    }
}
