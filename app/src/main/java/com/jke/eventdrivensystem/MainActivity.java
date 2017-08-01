package com.jke.eventdrivensystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jke.eventdrivensystem.abstracts.EventTarget;
import com.jke.eventdrivensystem.models.Event;
import com.jke.eventdrivensystem.views.SampleWidget;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private static int TIME_COUNT = 0;

    private EventTarget mEventTarget = new EventTarget() {
    };

    private Timer mTimer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SampleWidget widget1 = ((SampleWidget) findViewById(R.id.sw_1));
        mEventTarget.addEventListener("message", widget1);
        final SampleWidget widget2 = ((SampleWidget) findViewById(R.id.sw_2));
        mEventTarget.addEventListener("message", widget2);
        final Event event = new Event();
        event.type = "message"; // You can personalize this type string as you want.
        mTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (TIME_COUNT >= 15)
                    mEventTarget.removeEventListener();
                else if (TIME_COUNT >= 10)
                    mEventTarget.removeEventListener("message");
                else if (TIME_COUNT >= 5)
                    mEventTarget.removeEventListener("message", widget2);
                event.args = "Timer count = " + (++TIME_COUNT);
                mEventTarget.dispatch(event);
            }
        }, 0, 1000);
    }
}
