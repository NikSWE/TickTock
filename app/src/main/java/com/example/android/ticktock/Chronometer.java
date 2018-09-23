package com.example.android.ticktock;

import android.content.Context;

public class Chronometer implements Runnable {

    private static final long MILLIS_TO_SECONDS = 1_000;
    private static final long MILLIS_TO_MINUTES = 60_000;
    private static final long MILLIS_TO_HOURS = 3_600_000;

    private Context mContext;
    private long mStartTime;

    private boolean isRunning;

    public Chronometer(Context mContext) {
        this.mContext = mContext;
    }

    public void start() {
        isRunning = true;
        mStartTime = System.currentTimeMillis();
    }

    public void stop() {
        isRunning = false;
    }

    @Override
    public void run() {
        while(isRunning) {
            long since = System.currentTimeMillis() - mStartTime;

            int milliSeconds = (int) since % 1000;
            int seconds = (int) ((since / MILLIS_TO_SECONDS) % 60);
            int minutes = (int) ((since / MILLIS_TO_MINUTES) % 60);
            int hours = (int) ((since / MILLIS_TO_HOURS) % 24);

            ((MainActivity)mContext).updateTimerText(String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, milliSeconds));

        }
    }
}
