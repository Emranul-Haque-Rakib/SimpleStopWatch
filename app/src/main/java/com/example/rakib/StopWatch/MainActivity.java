package com.example.rakib.StopWatch;

import android.os.*;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.rakib.myapplication.R;

public class MainActivity extends AppCompatActivity {

    private int seconds=0;
    private boolean running;
    private  boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null){
            seconds=savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("running");
            wasRunning=savedInstanceState.getBoolean("wasRunning");
        }
        Timer();




    }
    public void onclickstart(View view){

        running=true;
    }
    public  void onclickstop(View view)
    {
        running=false;
    }
    public void onclickreset(View view){

        running=false;
        seconds=0;
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasRunning=running;
        running=false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(wasRunning){
            running=true;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds",seconds);
        outState.putBoolean("runnng",running);
        outState.putBoolean("wasRunning",wasRunning);
    }

    private void Timer(){
        final TextView timeView = (TextView)findViewById(R.id.textView);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;

                String time = String.format("%d:%02d:%02d", hours, minutes, secs);

                timeView.setText(time);

                if(running){
                    seconds++;
                }

                handler.postDelayed(this, 100);
            }
        });


    }
}

