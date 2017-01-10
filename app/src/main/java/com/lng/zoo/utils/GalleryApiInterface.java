package com.lng.zoo.utils;

import com.lng.zoo.model.GalleryImage;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Laycene Gaspar on 01/15/2016.
 */
public interface GalleryApiInterface {

    @GET("/gallery.json")
    public void getStreams(Callback<List<GalleryImage>> callback);
}
