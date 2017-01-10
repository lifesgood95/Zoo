package com.lng.zoo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lng.zoo.R;
import com.lng.zoo.model.GalleryImage;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by Laycene Gaspar on 01/15/2016.
 */
public class GalleryImageAdapter extends ArrayAdapter<GalleryImage> {
    public GalleryImageAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_gallery_thumbnail, parent, false);
            holder.image = (ImageView)convertView.findViewById(R.id.image);
            holder.progressBar = (ProgressBar)convertView.findViewById(R.id.progress);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.image.setVisibility(View.GONE);
        holder.progressBar.setVisibility(View.VISIBLE);

        final ViewHolder temp = holder;

        Picasso.with(getContext()).load(getItem(position).getThumbnail()).into(holder.image, new Callback() {
            @Override
            public void onSuccess() {
                temp.progressBar.setVisibility(View.GONE);
                temp.image.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError() {
                temp.progressBar.setVisibility(View.GONE);
            }
        });

        return convertView;

    }

    private class ViewHolder{
        ImageView image;
        ProgressBar progressBar;
    }
}
