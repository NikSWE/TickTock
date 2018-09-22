package com.example.android.ticktock;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private long startTime = 0;
    private boolean running = false;
    private long currentTime = 0;
    private Button btnStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.start_btn);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnStart.setText("Stop");
                btnStart.setAllCaps(true);
                btnStart.setTextColor(Color.parseColor("#e62739"));
                btnStart.setBackgroundColor(Color.parseColor("#cc474b"));
            }
        });
    }


    private void updateMilliSeconds(long number) {
        TextView milliSecondsTextView = (TextView) findViewById(R.id.milliSeconds);
        milliSecondsTextView.setText("" + number);
    }


    public void stop() {
        this.running = false;
    }

    public void pause() {
        this.running = false;
        currentTime = System.currentTimeMillis() - startTime;
    }

    public void resume() {
        this.running = true;
        this.startTime = System.currentTimeMillis() - currentTime;
    }

    //elaspsed time in milliseconds
    public long getElapsedTimeMiliSecond() {
        long elapsed = 0;
        if (running) {
            elapsed = ((System.currentTimeMillis() - startTime) / 100) % 1000;
        }
        return elapsed;
    }

    //elaspsed time in seconds
    public long getElapsedTimeSeconds() {
        long elapsed = 0;
        if (running) {
            elapsed = ((System.currentTimeMillis() - startTime) / 1000) % 60;
        }
        return elapsed;
    }

    //elaspsed time in minutes
    public long getElapsedTimeMinuntes() {
        long elapsed = 0;
        if (running) {
            elapsed = (((System.currentTimeMillis() - startTime) / 1000) / 60) % 60;
        }
        return elapsed;
    }

    //elaspsed time in hours
    public long getElapsedTimeHour() {
        long elapsed = 0;
        if (running) {
            elapsed = ((((System.currentTimeMillis() - startTime) / 1000) / 60) / 60);
        }
        return elapsed;
    }
}
