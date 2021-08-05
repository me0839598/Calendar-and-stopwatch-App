package com.example.appprojectdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;

import java.io.IOException;
import java.nio.CharBuffer;

public class MainActivity extends AppCompatActivity {
Chronometer chro;
ImageButton btstart,btstop;
boolean running;
long tmillisec,tstart,tbuff,tupdate = 0L;
int sec,mint,milisec;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chro=findViewById(R.id.chronometer);
        btstart=findViewById(R.id.bt_start);
        btstop=findViewById(R.id.bt_stop);



        handler = new Handler();


        btstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!running)
                {
                    tstart=SystemClock.uptimeMillis();
                    handler.postDelayed(runnable , 0 );
                    chro.start();
                    running = true;
                    btstop.setVisibility(View.GONE);

                    btstart.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause));

                }else {
                    tbuff +=tmillisec;
                    handler.removeCallbacks(runnable);
                    chro.stop();
                    running = false;
                    btstop.setVisibility(View.VISIBLE);
                    btstart.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_arrow));
                }

            }
        });


        btstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!running)
                {
                    btstart.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_arrow));
                    tmillisec=0l;
                    tstart=0l;
                    tbuff=0l;
                    tupdate=0l;
                    sec=0;
                    mint=0;
                    milisec=0;
                    chro.setText("00:00:00");
                }
            }
        });
    }


public  Runnable runnable=new Runnable() {
    @Override
    public void run() {
        tmillisec = SystemClock.uptimeMillis() - tstart;
        tupdate = tbuff + tmillisec;
        sec = (int) (tupdate/1000);
        mint=sec/60;
        sec = sec%60;

        milisec=(int) (tupdate%100);
        chro.setText(String.format("%02d", mint)+":"+String.format("%02d",sec)+":"+String.format("%02d",milisec) );
        handler.postDelayed(this ,60);
    }
};

    }


