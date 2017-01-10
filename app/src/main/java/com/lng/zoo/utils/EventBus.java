package com.lng.zoo.utils;

import com.squareup.otto.Bus;

/**
 * Created by Laycene Gaspar on 01/14/2016.
 */

//Singleton Pattern
public class EventBus extends Bus {

    private static final EventBus bus = new EventBus();

    public static Bus getInstance(){
        return bus;
    }

    private EventBus(){

    }
}
