package com.jke.eventdrivensystem.models;

import android.util.Log;

import com.jke.eventdrivensystem.BuildConfig;
import com.jke.eventdrivensystem.interfaces.IEventTarget;

import java.lang.ref.WeakReference;

/**
 * Created by jke on 01/08/2017.
 */

public class Event {
    private final String TAG = getClass().getName();
    private boolean DBG = BuildConfig.DEBUG;

    public String type;
    public Object args;
    public WeakReference<IEventTarget> target;

    /**
     * Attach event target.
     *
     * @param target event target.
     */
    public void attach(IEventTarget target) {
        if (DBG)
            Log.d(TAG, "attach: target = " + target);
        if (this.target == null || target != this.target.get())
            this.target = new WeakReference<>(target);
    }

    @Override
    public String toString() {
        return super.toString() + "[" + type + "]: {args: " + args + ", target: " + target + "}";
    }
}
