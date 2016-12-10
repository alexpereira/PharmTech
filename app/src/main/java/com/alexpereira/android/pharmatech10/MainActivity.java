package com.alexpereira.android.pharmatech10;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private Menu menu;

    private static final String TAG = "MyActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        String[] mItemTitles;

        mItemTitles = getResources().getStringArray(R.array.drawer_items);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);


        mDrawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, mItemTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        mDrawerToggle = new ActionBarDrawerToggle(  this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {

                super.onDrawerClosed(drawerView);
                Log.i(TAG, "OOOOOOOPEEEEEN");
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                showMenu(false);

                super.onDrawerOpened(drawerView);
                Log.i(TAG, "CLOOOOOOOOOOOOOOSED");
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                FilterFragment f = (FilterFragment) fm.findFragmentByTag("filterTag");
                if (f != null)
                    ft.remove(f).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).commit();
            }
        };

        mDrawerLayout.addDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            Fragment fragment = new HomeFragment();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            Log.d(TAG, "onDrawerOpened: DRAWER OPENED");
            showMenu(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.close_flashcards)
            switch (item.getItemId()) {
                case R.id.close_flashcards:

                    Log.i(TAG, "TESTTTTTT");

                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    FilterFragment f = (FilterFragment) fm.findFragmentByTag("filterTag");

                    if (f == null) {  // not added
                        f = new FilterFragment();
                        ft.add(R.id.filter_frame, f, "filterTag");
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                    } else {  // already added

                        ft.remove(f);
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                    }

                    ft.commit();

                    return true;

                default:
                    return super.onOptionsItemSelected(item);

            }

        else {
            return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    private void selectItem(int position) {
        Fragment fragment;

        if (position == 0){
            showMenu(false);
            fragment = new HomeFragment();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        }
        if (position == 1){
            showMenu(true);
            fragment = new ReviewFragment();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            // TODO: CLEAR OFF FILTER OPTIONS
            // TODO: SHOW TOOLBAR FILTER MENU BUTTON
        }
        if (position == 2){
            showMenu(true);
            fragment = new FlashcardsFragment();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            // TODO: CLEAR OFF FILTER OPTIONS
            // TODO: SHOW TOOLBAR FILTER MENU BUTTON
        }
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }
    public void showMenu(boolean showMenu){
        if(menu == null)
            return;
        MenuItem item = menu.findItem(R.id.close_flashcards);
        item.setVisible(showMenu);
    }
}
