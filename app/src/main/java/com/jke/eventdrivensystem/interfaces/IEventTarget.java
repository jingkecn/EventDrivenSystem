package com.jke.eventdrivensystem.interfaces;

import com.jke.eventdrivensystem.models.Event;

/**
 * Created by jke on 01/08/2017.
 */

public interface IEventTarget {
    /**
     * Add event listener.
     *
     * @param type     event type.
     * @param listener event handler.
     */
    void addEventListener(String type, IEventHandler listener);

    /**
     * Remove event listener.
     *
     * @param type     event type.
     * @param listener event handler.
     */
    void removeEventListener(String type, IEventHandler listener);

    /**
     * Remove event listener.
     *
     * @param type event type.
     */
    void removeEventListener(String type);

    /**
     * Remove event listener.
     */
    void removeEventListener();

    /**
     * Dispatch event.
     *
     * @param event event.
     */
    void dispatch(Event event);
}
