package com.example.test002;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    TestClass01 testClass01 = new TestClass01();
    TextView ytextView;
    int tonePeriod = 500;
    int toneType = ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD;
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            DoSignal();
        }
    };

    ToneGenerator toneGenerator = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
    private void DoSignal() {
        if (toneType == ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD)
        {
            toneType = ToneGenerator.TONE_CDMA_PIP;
        }
        else{
            toneType = ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD;
        }

        toneGenerator.startTone(toneType, 200);
    }

    Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ytextView = (TextView)findViewById(R.id.textView);
        ytextView.setText(String.valueOf(tonePeriod));
        timer.schedule(timerTask,1000, tonePeriod);
    }

    public void onButtonUp(View v)
    {
        tonePeriod += 10;
        RescheduleTimer();
    }

    public void onButtonDown(View v)
    {
        if (tonePeriod > 20) {
            tonePeriod -= 10;
        }
        RescheduleTimer();
    }

    private void RescheduleTimer() {
        ytextView.setText(String.valueOf(tonePeriod));
        timerTask.cancel();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                DoSignal();
            }
        };
        timer.schedule(timerTask, 1000, tonePeriod);
    }


}