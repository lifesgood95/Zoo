package com.lng.zoo.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lng.zoo.R;
import com.lng.zoo.utils.ConstantsUtils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by Laycene Gaspar on 01/15/2016.
 */
public class GalleryDetailActivity extends AppCompatActivity {

    private TextView mCaptionTv;
    private ImageView mImageView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_detail);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mCaptionTv = (TextView)findViewById(R.id.caption);
        mImageView = (ImageView)findViewById(R.id.image);
        progressBar = (ProgressBar)findViewById(R.id.progress);

        mImageView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        if (getIntent() != null && getIntent().getExtras() != null){
            if (getIntent().getExtras().containsKey(ConstantsUtils.EXTRA_CAPTION)){
                mCaptionTv.setText(getIntent().getExtras().getString(ConstantsUtils.EXTRA_CAPTION));
            }
            if (getIntent().getExtras().containsKey(ConstantsUtils.EXTRA_IMAGE)){
                Picasso.with(this).load(getIntent().getExtras().getString(ConstantsUtils.EXTRA_IMAGE)).into(mImageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.GONE);
                        mImageView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError() {
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
        }
    }
}
