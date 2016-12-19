package com.alexpereira.android.pharmatech10;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FilterFragment.onFilterSelectedListener {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private Menu menu;

    private ArrayList<Drug> unfilteredDrugs;
    private ArrayList<Drug> filteredDrugs;

    private static final String TAG = "MyActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Home");

        unfilteredDrugs = new ArrayList<>();
        filteredDrugs = new ArrayList<>();
        unfilteredDrugs = cloneList(PharmTech.drugs);

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
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                FilterFragment f = (FilterFragment) fm.findFragmentByTag("filterTag");

                if (f == null) {  // not added
                    f = new FilterFragment();
                    ft.add(R.id.filter_frame, f, "filterTag");
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                }
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                showMenu(false);
                super.onDrawerOpened(drawerView);

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

                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    FilterFragment f = (FilterFragment) fm.findFragmentByTag("filterTag");

                    if (f == null) {
                        f = new FilterFragment();
                        ft.add(R.id.filter_frame, f, "filterTag");
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                    } else {

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
            fragmentManager.beginTransaction().addToBackStack("Home").replace(R.id.content_frame, fragment).commit();
        }
        if (position == 1){
            showMenu(true);
            fragment = new ReviewFragment();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().addToBackStack("Review").replace(R.id.content_frame, fragment).commit();
        }
        if (position == 2){
            showMenu(true);
            fragment = new FlashcardsFragment();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().addToBackStack("Flashcards").replace(R.id.content_frame, fragment).commit();
        }
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void onFilterSelected(String drugPurposePicked, String drugCategoryPicked, String studyTopicPicked) {
        //If review fragment is open

        // Filters
        filterDrugs(drugPurposePicked, drugCategoryPicked, studyTopicPicked);



        int index = this.getFragmentManager().getBackStackEntryCount() - 1;
        FragmentManager.BackStackEntry backEntry = getFragmentManager().getBackStackEntryAt(index);
        String lastFragment = backEntry.getName();

        Fragment newFragment;

        if (lastFragment.equals("Flashcards"))
            newFragment = new FlashcardsFragment();
        else if (lastFragment.equals("Review"))
            newFragment = new ReviewFragment();
        else
            newFragment = new HomeFragment();
        //Fragment fragment = getFragmentManager().findFragmentByTag(tag);


        // Create fragment and give it an argument for the selected article
//        ReviewFragment newFragment = new ReviewFragment();
        Bundle args = new Bundle();

        if(filteredDrugs.isEmpty()) // If reset button is clicked
            args.putParcelableArrayList(ReviewFragment.FILTERS, unfilteredDrugs);
        else
            args.putParcelableArrayList(ReviewFragment.FILTERS, filteredDrugs);

        newFragment.setArguments(args);

        FragmentManager fm = getFragmentManager();

        FragmentTransaction transaction = fm.beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.content_frame, newFragment);

        // Commit the transaction
        transaction.commit();
    }


    public void filterDrugs(String drugPurposePicked, String drugCategoryPicked, String studyTopicPicked) {

        filteredDrugs.clear();
        for (int i = 0; i < unfilteredDrugs.size(); i++) {

            // FILTER PURPOSE
            if (unfilteredDrugs.get(i).getDrugPurpose().toLowerCase().equals(drugPurposePicked.toLowerCase())) {
                filteredDrugs.add(unfilteredDrugs.get(i));
            }

            // FILTER CATEGORY
            if (unfilteredDrugs.get(i).getDrugCategory().toLowerCase().equals(drugCategoryPicked.toLowerCase())) {
                filteredDrugs.add(unfilteredDrugs.get(i));
            }

            // FILTER STUDY TOPIC
            if (unfilteredDrugs.get(i).getDrugStudyTopic().toLowerCase().equals(studyTopicPicked.toLowerCase())) {
                filteredDrugs.add(unfilteredDrugs.get(i));
            }
        }
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

    public static ArrayList<Drug> cloneList(ArrayList<Drug> drugs) {
        ArrayList<Drug> clonedList = new ArrayList<>(drugs.size());
        for (Drug drug : drugs) {
            clonedList.add(new Drug(drug));
        }
        return clonedList;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }
}
