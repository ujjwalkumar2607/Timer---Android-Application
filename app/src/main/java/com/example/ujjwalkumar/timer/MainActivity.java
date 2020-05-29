package com.example.ujjwalkumar.timer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    SeekBar seekBar;
    Boolean counterIsActive = false;
    Button button;
    CountDownTimer countDownTimer;

    public void resetTimer()
    {
        textView.setText("0:30");
        seekBar.setProgress(30);
        seekBar.setEnabled(true);
        countDownTimer.cancel();
        button.setText("Go!");
        counterIsActive = false;
    }

    public void buttonClick(View view)
    {
        if(counterIsActive)
        {
            resetTimer();
        }
        else
        {
            counterIsActive = true;
            seekBar.setEnabled(false);
            button.setText("Stop!");

            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l)
                {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish()
                {
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.horn);
                    mplayer.start();
                    resetTimer();
                }
            }.start();
        }


    }


    public void updateTimer(int secondsLeft)
    {
        int minutes = secondsLeft/60;
        int seconds = secondsLeft-(minutes*60);

        String secondString = Integer.toString(seconds);
        if(seconds <= 9)
        {
            secondString = "0" + secondString;
        }

        textView.setText(Integer.toString(minutes) + ":" + secondString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);

        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {

                updateTimer(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
