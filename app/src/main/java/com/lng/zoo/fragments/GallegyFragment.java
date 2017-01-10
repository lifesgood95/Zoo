package com.lng.zoo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.lng.zoo.R;
import com.lng.zoo.activities.GalleryDetailActivity;
import com.lng.zoo.adapters.GalleryImageAdapter;
import com.lng.zoo.model.GalleryImage;
import com.lng.zoo.utils.ConstantsUtils;
import com.lng.zoo.utils.GalleryApiInterface;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Laycene Gaspar on 01/14/2016.
 */
public class GallegyFragment extends Fragment implements AdapterView.OnItemClickListener {
    private GridView mGridView;
    private GalleryImageAdapter mAdapter;

    public static GallegyFragment getInstance(){
        GallegyFragment frament = new GallegyFragment();
        return frament;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gallery_grid,container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mGridView = (GridView)view.findViewById(R.id.grid);
        mGridView.setOnItemClickListener(this);
        mGridView.setDrawSelectorOnTop(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdapter = new GalleryImageAdapter(getActivity(), 0);
        mGridView.setAdapter(mAdapter);

        RestAdapter restAdapter = new  RestAdapter.Builder().setEndpoint(getString(R.string.gallery_feed)).build();

        GalleryApiInterface galleryApiInterface = restAdapter.create(GalleryApiInterface.class);
        galleryApiInterface.getStreams(new Callback<List<GalleryImage>>() {
            @Override
            public void success(List<GalleryImage> galleryImages, Response response) {
                if (galleryImages == null || galleryImages.isEmpty() || !isAdded())
                    return;
                for(GalleryImage galleryImage : galleryImages){
                    Log.e("Zoo", galleryImage.getThumbnail());
                    mAdapter.add(galleryImage);
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Zoo", error.getMessage());
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GalleryImage galleryImage = (GalleryImage) parent.getItemAtPosition(position);

        Intent intent = new Intent(getActivity(), GalleryDetailActivity.class);
        intent.putExtra(ConstantsUtils.EXTRA_CAPTION, galleryImage.getCaption());
        intent.putExtra(ConstantsUtils.EXTRA_IMAGE, galleryImage.getImage());

        startActivity(intent);
    }
}
