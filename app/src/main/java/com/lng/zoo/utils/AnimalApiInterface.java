package com.lng.zoo.utils;

import com.lng.zoo.model.Animal;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Laycene Gaspar on 01/14/2016.
 */

public interface AnimalApiInterface {
    @GET("/Exhibits.json")
    public void getStreams(Callback<List<Animal>> callback);
}
