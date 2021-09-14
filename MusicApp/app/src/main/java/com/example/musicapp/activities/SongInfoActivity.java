package com.example.musicapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.musicapp.R;

public class SongInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.songinfo_main);

        Intent objIntent = getIntent();
        String strSongPath = objIntent.getStringExtra("songpath");
        String strSongName = objIntent.getStringExtra("songname");
        String strSongBitRate = objIntent.getStringExtra("songbitrate");

        TextView objSongName = findViewById(R.id.songname_info);
        TextView objSongPath = findViewById(R.id.songpath_info);
        TextView objSongBitarte = findViewById(R.id.songbitrate_info);

        objSongName.setText(strSongName);
        objSongPath.setText(strSongPath);
        objSongBitarte.setText(strSongBitRate);


    }
}
