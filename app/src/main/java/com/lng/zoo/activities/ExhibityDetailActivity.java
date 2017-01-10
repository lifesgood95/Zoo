package com.lng.zoo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.lng.zoo.R;
import com.lng.zoo.model.Animal;
import com.lng.zoo.utils.ConstantsUtils;
import com.squareup.picasso.Picasso;

/**
 * Created by Laycene Gaspar on 01/15/2016.
 */
public class ExhibityDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibit_detail);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Animal animal = getIntent().getExtras().getParcelable(ConstantsUtils.EXTRA_ANIMAL);

        TextView species = (TextView)findViewById(R.id.species);
        TextView description = (TextView)findViewById(R.id.description);
        ImageView image = (ImageView)findViewById(R.id.image);

        species.setText(animal.getSpecies());
        description.setText(animal.getDescription());
        Picasso.with(this).load(animal.getImage()).into(image);
    }
}
