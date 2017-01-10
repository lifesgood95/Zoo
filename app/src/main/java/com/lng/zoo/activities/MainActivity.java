package com.lng.zoo.activities;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.lng.zoo.R;
import com.lng.zoo.events.DrawerSectionItemClickedEvents;
import com.lng.zoo.fragments.ExhibityListFrament;
import com.lng.zoo.fragments.GallegyFragment;
import com.lng.zoo.fragments.ZooMapFragment;
import com.lng.zoo.utils.EventBus;
import com.squareup.otto.Subscribe;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private String mCurrentFragmentTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        mdrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mdrawerLayout, R.string.drawer_opened, R.string.drawer_closed){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (getSupportActionBar()!= null)
                    getSupportActionBar().setTitle(R.string.drawer_opened);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (getSupportActionBar()!= null)
                    getSupportActionBar().setTitle(R.string.drawer_closed);
            }
        };

        mdrawerLayout.setDrawerListener(mActionBarDrawerToggle);

        displayInitialFragment();
    }

    private void displayInitialFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, ExhibityListFrament.getInstance()).commit();
        mCurrentFragmentTitle = getString(R.string.section_exhibits);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        mActionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mActionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getInstance().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getInstance().unregister(this);
    }

    @Subscribe
    public void onDrawerSectionItemClickEvent(DrawerSectionItemClickedEvents events){
        Toast.makeText(this, "MainActivity: Section Cliecked: " + events.getSection(), Toast.LENGTH_SHORT).show();
        mdrawerLayout.closeDrawers();
        if (events == null || TextUtils.isEmpty(events.getSection()) || events.getSection().equalsIgnoreCase(mCurrentFragmentTitle)){
            return;
        }

        if (events.getSection().equalsIgnoreCase(getString(R.string.section_map))){
            getSupportFragmentManager().beginTransaction().replace(R.id.container, ZooMapFragment.getInstance()).commit();
        }
        else if (events.getSection().equalsIgnoreCase(getString(R.string.section_gallery))){
            getSupportFragmentManager().beginTransaction().replace(R.id.container, GallegyFragment.getInstance()).commit();
        }
        else if (events.getSection().equalsIgnoreCase(getString(R.string.section_exhibits))){
            getSupportFragmentManager().beginTransaction().replace(R.id.container, ExhibityListFrament.getInstance()).commit();
        }
        else{
            return;
        }
        mCurrentFragmentTitle = events.getSection();
    }
}
