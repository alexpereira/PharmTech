package com.alexpereira.android.pharmatech10;

import android.app.Fragment;
import android.os.Bundle;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class FlashcardsPagerAdapter extends FragmentStatePagerAdapter {
    private static ArrayList<Drug> mDataSet;

    public FlashcardsPagerAdapter(ArrayList<Drug> dataSet, FragmentManager fm) {
        super(fm);
        this.mDataSet = dataSet;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new FlashcardFragment();
        Bundle args = new Bundle();
        args.putParcelable(FlashcardFragment.ARG_OBJECT, mDataSet.get(i));
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int getCount() {
        return mDataSet.size();
    }
}