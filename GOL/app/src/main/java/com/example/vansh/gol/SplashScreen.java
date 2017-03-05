package com.example.vansh.gol;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    MediaPlayer mpSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mpSplash = MediaPlayer.create(this, R.raw.logo_voice);
        mpSplash.start();
    Thread splashThread = new Thread() {
        @Override
        public void run(){
            try {
                sleep(3000);
                Intent intent = new Intent(getApplicationContext(),Home.class);
                startActivity(intent);
                finish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
        splashThread.start();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        mpSplash.release();
    }

//    @Override
//    protected void onPause(){
//        super.onPause();
//        mpSplash.pause();
//    }
//
//    @Override
//    protected void onResume(){
//        super.onResume();
//        mpSplash.start();
//    }
}
