package com.example.musicapp.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.MediaStore;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.musicapp.activities.MainTabActivity;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import  static  com.example.musicapp.activities.MainTabActivity.objcrrSongSeekBar;

public class SongServiceClass extends Service {

    public  IBinder objBinder = new LocalBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return objBinder;
    }

    public  class LocalBinder extends Binder{

        public  SongServiceClass getService(){
            return SongServiceClass.this;
        }
    }

    CustomTimeHandler objCustomTimeHandler;
    MediaPlayer objMediaPlayer;
    int iScaleFactor = 0;

    //SimpleDateFormat objDateFormat = new SimpleDateFormat("HH:mm:ss");
    //Date objDate = new Date();

    public  void PlaySongs(int iPosition){

        try{
            if (objMediaPlayer != null){
                if (objMediaPlayer.isPlaying()){
                    objMediaPlayer.stop();
                    objMediaPlayer.release();
                    objMediaPlayer = null;
                }
            }

            objMediaPlayer = new MediaPlayer();
            objMediaPlayer.setDataSource(MainTabActivity.objSongPath.get(iPosition));
            objMediaPlayer.prepare();
            objMediaPlayer.start();

            iScaleFactor = objMediaPlayer.getDuration()/objcrrSongSeekBar.getMax();

            if (objCustomTimeHandler == null){
                objCustomTimeHandler = new CustomTimeHandler();
                objCustomTimeHandler.sendEmptyMessage(0);

                objcrrSongSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    boolean blSeekBarStartedTracking = false;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (blSeekBarStartedTracking){
                            objMediaPlayer.pause();
                            objMediaPlayer.seekTo(progress * iScaleFactor);
                            objMediaPlayer.start();

                        }
                        //CustomTimeHandler.postDelayed(this,1000);
                    }


                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        blSeekBarStartedTracking = true;

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void PauseSong(){
        if (objMediaPlayer.isPlaying()){
            objMediaPlayer.stop();
            objMediaPlayer.release();
            objMediaPlayer = null;
        }

    }

    public class CustomTimeHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {

            if (objMediaPlayer != null){
                if (objMediaPlayer.isPlaying()){
                    int iSongCureentTimeInMillis = objMediaPlayer.getCurrentPosition();
                    objcrrSongSeekBar.setProgress(iSongCureentTimeInMillis/iScaleFactor);

                    //
                    //String strDuration = objCursor.getString(objCursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                    int iMilliSeconds = iSongCureentTimeInMillis;
                    String audioTime;
                    int mns = (iMilliSeconds / 60000) % 60000;
                    int scs = iMilliSeconds % 60000 / 1000;
                    audioTime = String.format("%02d:%02d", mns, scs);
                    String strData = audioTime;
                    //MainTabActivity.objSongDuration.add(strData);

                    //objDate.setTime(objMediaPlayer.getCurrentPosition());
                    //objDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

                    //String strData = objDateFormat.format(objDate);

                    MainTabActivity.objcrrSongTime.setText(strData);
                }
            }
           // objCustomTimeHandler.postDelayed(thi);
            sendEmptyMessage(0);
        }
    }



}
