package com.jke.eventdrivensystem.abstracts;

import android.util.Log;

import com.jke.eventdrivensystem.BuildConfig;
import com.jke.eventdrivensystem.interfaces.IEventHandler;
import com.jke.eventdrivensystem.interfaces.IEventTarget;
import com.jke.eventdrivensystem.models.Event;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by jke on 01/08/2017.
 */

public abstract class EventTarget implements IEventTarget {
    private final String TAG = getClass().getName();
    private boolean DBG = BuildConfig.DEBUG;

    private HashMap<String, HashSet<IEventHandler>> mHandlerMap = new HashMap<>();

    @Override
    public final void addEventListener(String type, IEventHandler listener) {
        if (DBG)
            Log.d(TAG, "addEventListener: type = " + type + ", listener = " + listener);
        if (!mHandlerMap.containsKey(type))
            mHandlerMap.put(type, new HashSet<IEventHandler>());
        HashSet<IEventHandler> handlers = mHandlerMap.get(type);
        handlers.add(listener);
    }

    @Override
    public final void removeEventListener(String type, IEventHandler listener) {
        if (DBG)
            Log.d(TAG, "removeEventListener: type = " + type + ", listener = " + listener);
        if (!mHandlerMap.containsKey(type))
            return;
        HashSet<IEventHandler> handlers = mHandlerMap.get(type);
        handlers.remove(listener);
        if (handlers.isEmpty())
            mHandlerMap.remove(type);
    }

    @Override
    public final void removeEventListener(String type) {
        if (DBG)
            Log.d(TAG, "removeEventListener: type = " + type);
        if (!mHandlerMap.containsKey(type))
            return;
        mHandlerMap.remove(type);
    }

    @Override
    public final void removeEventListener() {
        mHandlerMap.clear();
    }

    @Override
    public void dispatch(Event event) {
        if (DBG)
            Log.d(TAG, "dispatch: event = " + event);
        if (!mHandlerMap.containsKey(event.type))
            return;
        HashSet<IEventHandler> handlers = mHandlerMap.get(event.type);
        if (event.target == null)
            event.attach(this);
        for (IEventHandler handler : handlers)
            handler.invoke(event);
    }
}
