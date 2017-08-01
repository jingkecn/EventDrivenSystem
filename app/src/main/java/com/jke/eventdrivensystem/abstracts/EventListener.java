package com.jke.eventdrivensystem.abstracts;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.jke.eventdrivensystem.BuildConfig;
import com.jke.eventdrivensystem.interfaces.IEventHandler;
import com.jke.eventdrivensystem.models.Event;

/**
 * Created by jke on 01/08/2017.
 */

public abstract class EventListener implements IEventHandler {
    private final String TAG = getClass().getName();
    private boolean DBG = BuildConfig.DEBUG;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (DBG)
                Log.d(TAG, "handleMessage: message = " + msg);
            Event event = (Event) msg.obj;
            if (event != null && msg.what == event.type.hashCode())
                on(event);
        }
    };

    @Override
    public final void invoke(Event event) {
        if (DBG)
            Log.d(TAG, "invoke: event = " + event);
        Message msg = new Message();
        msg.what = event.type.hashCode();
        msg.obj = event;
        mHandler.sendMessage(msg);
    }
}
