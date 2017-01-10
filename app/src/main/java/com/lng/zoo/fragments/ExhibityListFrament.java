package com.lng.zoo.fragments;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.lng.zoo.R;
import com.lng.zoo.activities.ExhibityDetailActivity;
import com.lng.zoo.adapters.ExhibitsAdapter;
import com.lng.zoo.model.Animal;
import com.lng.zoo.utils.AnimalApiInterface;
import com.lng.zoo.utils.ConstantsUtils;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Laycene Gaspar on 01/14/2016.
 */
public class ExhibityListFrament extends ListFragment {

    private ExhibitsAdapter mAdapter;

    public static ExhibityListFrament getInstance(){
        ExhibityListFrament frament = new ExhibityListFrament();
        return frament;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListShown(false);

        getListView().setPadding(16, 16, 16, 16);
        getListView().setDivider(new ColorDrawable(Color.TRANSPARENT));
        getListView().setDividerHeight(16);
        getListView().setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        getListView().setClipToPadding(true);

        mAdapter = new ExhibitsAdapter(getActivity(), 0);

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(getString(R.string.exhibits_feed)).build();

        AnimalApiInterface animalApiInterface = restAdapter.create(AnimalApiInterface.class);
        animalApiInterface.getStreams(new Callback<List<Animal>>() {
            @Override
            public void success(List<Animal> animals, Response response) {
                if (animals == null || animals.isEmpty() || !isAdded()) {
                    return;
                }

                for (Animal animal : animals) {
                    Log.e("Zoo", animal.getName());
                    mAdapter.add(animal);
                }
                mAdapter.notifyDataSetChanged();
                setListAdapter(mAdapter);
                setListShown(true);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Zoo", "Retrofit error: " + error.getMessage());
            }
        });
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent intent = new Intent(getActivity(), ExhibityDetailActivity.class);
        intent.putExtra(ConstantsUtils.EXTRA_ANIMAL, mAdapter.getItem(position));
        startActivity(intent);
    }
}
