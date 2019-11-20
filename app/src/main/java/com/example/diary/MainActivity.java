package com.example.diary;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.diary.fragments.CalendarFragment;
import com.roomorama.caldroid.CaldroidFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer = null;
    private Fragment mFragmentToSet = null;

    private void clearStack(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        int count = fragmentManager.getBackStackEntryCount();
        while(count > 0){
            fragmentManager.popBackStack();
            count--;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {}
            @Override public void onDrawerOpened(@NonNull View drawerView) {}
            @Override public void onDrawerStateChanged(int newState) {}

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                //Set your new fragment here
                if (mFragmentToSet != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .addToBackStack("AddRecipe")
                            .replace(R.id.fragment_content, mFragmentToSet)
                            .commit();
                    mFragmentToSet = null;
                }
            }
        });


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("AddRecipe")
                .replace(R.id.fragment_content, new CalendarFragment())
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        Class fragmentClass = null;

        if (id == R.id.nav_calendar){
            fragmentClass = CalendarFragment.class;
        } else if (id == R.id.nav_tracker) {

        } else if (id == R.id.nav_dream_book) {

        } else if (id == R.id.nav_results) {

        } else if (id == R.id.nav_statistics) {

        } else if (id == R.id.nav_settings){

        }
        try {
            mFragmentToSet = (Fragment) fragmentClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
