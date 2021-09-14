package com.example.musicapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.musicapp.activities.MainTabActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class LoadMp3Resources {
    Context objContext;

    public  LoadMp3Resources(Context objContext){
        this.objContext = objContext;
        Log.e("check", "in constroctor" );

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void  LoadAllSongs(){
        Log.e("check", "in all songs" );
        Uri objUri;

        objUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Log.e("check","aa" + objUri);

        if (objUri != null) {
            //Uri objUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            String[] strProjection = {
                    //MediaStore.Audio.Media._ID,
                    MediaStore.Audio.Media.DATA,
                    MediaStore.Audio.Media.DISPLAY_NAME,
                    MediaStore.Audio.Media.DURATION,
                    //MediaStore.Audio.AudioColumns.BITRATE,
                    //MediaStore.Audio.AudioColumns.ARTIST,
                    //MediaStore.Audio.AudioColumns.ALBUM
            };
            Cursor objCursor = objContext.getContentResolver().query(objUri, strProjection, null, null, null);
            Log.e("check", "med all songs");
            if (objCursor != null){
                while (objCursor.moveToNext()) {
                    MainTabActivity.objSongPath.add(objCursor.getString(objCursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
                    MainTabActivity.objSongName.add(objCursor.getString(objCursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME)));
                    MainTabActivity.objSongBitrate.add("00");
                    Log.e("check", "med1 all songs");
                    String strDuration = objCursor.getString(objCursor.getColumnIndex(MediaStore.Audio.Media.DURATION));

                    int iMilliSeconds = Integer.parseInt(strDuration);
                    String audioTime;

                    //ong iHour = TimeUnit.MILLISECONDS.toHours(iMilliSeconds);
                    //long iMins = TimeUnit.MICROSECONDS.toMinutes(iMilliSeconds);
                    //long iSeconds = TimeUnit.MILLISECONDS.toSeconds(iMilliSeconds);
                    int mns = (iMilliSeconds / 60000) % 60000;
                    int scs = iMilliSeconds % 60000 / 1000;

                    //System.out.format( iMins + " : " + iSeconds);

                    //Date objDate = new Date();
                    //SimpleDateFormat objDateFormat = new SimpleDateFormat("mm:ss");
                    //objDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

                    //objDate.setMinutes(new Long(iMins).intValue());
                    //objDate.setSeconds(new Long(iSeconds).intValue());
                    audioTime = String.format("%02d:%02d", mns, scs);

                    String strData = audioTime;

                    MainTabActivity.objSongDuration.add(strData);
                    Log.e("check", "med2 all songs");
                    MediaMetadataRetriever objMediaDataRetriever = new MediaMetadataRetriever();
                    objMediaDataRetriever.setDataSource(objCursor.getString(objCursor.getColumnIndex(MediaStore.Audio.Media.DATA)));

                    byte[] byteData = objMediaDataRetriever.getEmbeddedPicture();
                    if (byteData != null) {
                        Bitmap objBitmap = BitmapFactory.decodeByteArray(byteData, 0, byteData.length);
                        MainTabActivity.objSongThumnail.add(objBitmap);

                    } else {
                       Bitmap objBitmap = BitmapFactory.decodeResource(objContext.getResources(), R.drawable.logo1);
                       MainTabActivity.objSongThumnail.add(objBitmap);

                    }

                    objMediaDataRetriever.release();
                    //objMediaDataRetriever = null;
                    //Log.e("Cursor" , "Song " +objCursor.getString(objCursor.getColumnIndex(MediaStore.Audio.AudioColumns.DURATION)));
                }
            Log.e("check", "end all songs");
            objCursor.close();

                Log.e("check", "end all songs");
        }
            else {
                Log.e("check","nouricursor");
            }

    }
        else {
            Log.e("check","nouri");
        }
}}
