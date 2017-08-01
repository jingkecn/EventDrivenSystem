package com.jke.eventdrivensystem.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

import com.jke.eventdrivensystem.BuildConfig;
import com.jke.eventdrivensystem.abstracts.EventListener;
import com.jke.eventdrivensystem.interfaces.IEventHandler;
import com.jke.eventdrivensystem.models.Event;

/**
 * Created by jke on 02/08/2017.
 */

public class SampleWidget extends AppCompatTextView implements IEventHandler {
    private final String TAG = getClass().getName();
    private boolean DBG = BuildConfig.DEBUG;

    private EventListener mEventListener = new EventListener() {
        @Override
        public void on(Event event) {
            if (DBG)
                Log.d(TAG, "on: event = " + event);
            switch (event.type) {
                case "message":
                    onMessage((String) event.args);
                default:
                    break;
            }
        }
    };

    public SampleWidget(Context context) {
        super(context);
    }

    public SampleWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SampleWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void invoke(Event event) {
        mEventListener.invoke(event);
    }

    @Override
    public void on(Event event) {
        mEventListener.on(event);
    }

    private void onMessage(String message) {
        setText(message);
    }
}
