package com.jke.eventdrivensystem.interfaces;

import com.jke.eventdrivensystem.models.Event;

/**
 * Created by jke on 01/08/2017.
 */

public interface IEventHandler {
    /**
     * Event invoker.
     *
     * @param event
     */
    void invoke(Event event);

    /**
     * On event invoked.
     *
     * @param event event invoked.
     */
    void on(Event event);
}
