package com.alexpereira.android.pharmatech10;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FlashcardsFragment extends Fragment{
    private static final String TAG = "FlashcardsFragment";
    public static final String FILTERS = "filters";
    private ArrayList<Drug> filteredDrugs = new ArrayList();


    FlashcardsPagerAdapter mFlashcardsPagerAdapter;

    private ArrayList<Drug> data = PharmTech.drugs;

    ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Flashcards");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_flashcards, container, false);
        rootView.setTag(TAG);

        mFlashcardsPagerAdapter = new FlashcardsPagerAdapter(data, getFragmentManager());

        mViewPager = (ViewPager) rootView.findViewById(R.id.pager);
        mViewPager.setAdapter(mFlashcardsPagerAdapter);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle args = getArguments();
        if (args != null) {

            filteredDrugs = args.getParcelableArrayList(FILTERS);
            data.clear();
            for (Drug drug : filteredDrugs) {
                data.add(drug);
            }

            mFlashcardsPagerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }
}