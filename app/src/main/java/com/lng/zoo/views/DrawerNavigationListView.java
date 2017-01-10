package com.lng.zoo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lng.zoo.R;
import com.lng.zoo.adapters.DrawerNavigationListAdapter;
import com.lng.zoo.events.DrawerSectionItemClickedEvents;
import com.lng.zoo.utils.EventBus;

/**
 * Created by Laycene Gaspar on 01/14/2016.
 */
public class DrawerNavigationListView extends ListView implements ListView.OnItemClickListener {

    public DrawerNavigationListView(Context context) {
        this(context, null);
    }

    public DrawerNavigationListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawerNavigationListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        DrawerNavigationListAdapter adapter = new DrawerNavigationListAdapter(context, 0);
        adapter.add(getContext().getString(R.string.section_exhibits));
        adapter.add(getContext().getString(R.string.section_gallery));
        adapter.add(getContext().getString(R.string.section_map));

        setAdapter(adapter);

        setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        EventBus.getInstance().post(new DrawerSectionItemClickedEvents((String) parent.getItemAtPosition(position)));
    }
}
