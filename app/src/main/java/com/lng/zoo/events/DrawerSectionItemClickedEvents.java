package com.lng.zoo.events;

/**
 * Created by Laycene Gaspar on 01/14/2016.
 */
public class DrawerSectionItemClickedEvents {

    private String section;
    public DrawerSectionItemClickedEvents(String section){
        this.section = section;
    }

    public String getSection(){ return  section;}
}
