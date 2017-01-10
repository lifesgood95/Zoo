package com.lng.zoo.fragments;

import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by Laycene Gaspar on 01/14/2016.
 */
public class ZooMapFragment extends SupportMapFragment {
    public static ZooMapFragment getInstance(){
        ZooMapFragment frament = new ZooMapFragment();
        return frament;
    }
}
