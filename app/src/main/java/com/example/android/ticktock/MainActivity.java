package com.example.android.ticktock;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private boolean running = false;
    private TextView time_counter, mTextView;
    private Button btnStart, btnLap, btnStop, btnReset;
    private ScrollView mScrollView;
    private Chronometer mChronometer;
    private Context mContext;
    private Thread mThreadChrono;

    private int mLaps = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        time_counter = (TextView) findViewById(R.id.timer_display);

        btnStart = (Button) findViewById(R.id.start_btn);
        btnStop = (Button) findViewById(R.id.stop_btn);
        btnLap = (Button) findViewById(R.id.lap_btn);
        mTextView = (TextView) findViewById(R.id.lap_text_view);
        btnReset = (Button) findViewById(R.id.reset_btn);
        mScrollView = (ScrollView) findViewById(R.id.lap_scroll_view);

        btnPressState(true, false, false, false);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //button start has been pressed

                btnPressState(false, true, true, false);

                if (mChronometer == null) {
                    mChronometer = new Chronometer(mContext);
                    mThreadChrono = new Thread(mChronometer);
                    mThreadChrono.start();
                    mChronometer.start();

                } else {
                    mThreadChrono = new Thread(mChronometer);
                    mThreadChrono.start();
                    mChronometer.start();
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //button stop has been pressed

                btnPressState(true, false, false, true);

                mChronometer.pause();
                mThreadChrono.interrupt();
                mThreadChrono = null;
            }
        });

        btnLap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //button lap has been pressed

                if (mChronometer == null) {
                    return;
                }

                mTextView.append("Lap " + String.valueOf(mLaps) + "\t\t\t\t" + String.valueOf(time_counter.getText()) + "\n");

                mLaps++;

                mScrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        mScrollView.smoothScrollTo(0, mTextView.getBottom());
                    }
                });
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextView.setText("");
                time_counter.setText("00:00:00.000");

                btnPressState(true, false, false, false);

                mChronometer = null;

                mLaps = 1;
            }
        });

    }

    public void updateTimerText(final String time) {
        runOnUiThread(new Runnable() {
                          @Override
                          public void run() {
                              time_counter.setText(time);
                          }
                      }
        );
    }

    public void btnState() {
        if (!btnStart.isEnabled()) {
            btnStart.setTextColor(Color.parseColor("#A9A9A9"));
            btnStart.setBackgroundColor(Color.parseColor("#D3D3D3"));
        } else {
            btnStart.setTextColor(Color.parseColor("#39ff14"));
            btnStart.setBackgroundColor(Color.parseColor("#65a04e"));
        }
        if (!btnStop.isEnabled()) {
            btnStop.setTextColor(Color.parseColor("#A9A9A9"));
            btnStop.setBackgroundColor(Color.parseColor("#D3D3D3"));
        } else {
            btnStop.setTextColor(Color.parseColor("#ff0000"));
            btnStop.setBackgroundColor(Color.parseColor("#F08080"));
        }
        if (!btnLap.isEnabled()) {
            btnLap.setTextColor(Color.parseColor("#A9A9A9"));
            btnLap.setBackgroundColor(Color.parseColor("#D3D3D3"));
        } else {
            btnLap.setTextColor(Color.parseColor("#ffffff"));
            btnLap.setBackgroundColor(Color.parseColor("#696969"));
        }
        if (!btnReset.isEnabled()) {
            btnReset.setTextColor(Color.parseColor("#A9A9A9"));
            btnReset.setBackgroundColor(Color.parseColor("#D3D3D3"));
        } else {
            btnReset.setTextColor(Color.parseColor("#ffffff"));
            btnReset.setBackgroundColor(Color.parseColor("#696969"));
        }
    }

    public void btnPressState(boolean start, boolean stop, boolean lap, boolean reset) {
        btnStart.setEnabled(start);
        btnStop.setEnabled(stop);
        btnLap.setEnabled(lap);
        btnReset.setEnabled(reset);
        btnState();
    }
}
