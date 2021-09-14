package com.example.musicapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.musicapp.R;
import com.example.musicapp.activities.MainTabActivity;

import java.lang.reflect.Array;

public class CustomArrayAdapter extends ArrayAdapter<String> {
    String [] name;

    public CustomArrayAdapter (Context context, int resource, String [] name){
        super(context, resource, name);
        this.name = name;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View objView = LayoutInflater.from(getContext()).inflate(R.layout.song_row_view, null);
        ImageView objImageView = objView.findViewById(R.id.songimage);
        TextView objSongName = objView.findViewById(R.id.songname);
        TextView objSongDuration = objView.findViewById(R.id.songduration);

        objImageView.setImageBitmap(MainTabActivity.objSongThumnail.get(position));
        objSongName.setText(MainTabActivity.objSongName.get(position));
        objSongDuration.setText(MainTabActivity.objSongDuration.get(position));


        return objView;

    }
}
