package com.alexpereira.android.pharmatech10;

import android.app.Application;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by alexpereira on 10/22/16.
 */

public class PharmTech extends Application {

    public static ArrayList<Drug> drugs = null;

    private TestAdapter mDbHelper;

    private int count =0;


    @Override
    public void onCreate() {
        super.onCreate();

        mDbHelper= new TestAdapter(this);
        try {
            mDbHelper.createDatabase();
            mDbHelper.open();
        } catch (SQLiteException e) {

        }
        try {
            Cursor mCursor = mDbHelper.getTestData();
            if (mCursor != null) {
                if (mCursor.moveToFirst()) {
                    drugs = new ArrayList();
                    do {
                        Drug mDrugs = new Drug();
                        mDrugs.setDrugID(mCursor.getString(0));
                        mDrugs.setDrugName(mCursor.getString(1));
                        mDrugs.setDrugBrand(mCursor.getString(2));
                        mDrugs.setDrugPurpose(mCursor.getString(3));
                        mDrugs.setDrugDEASchedule(mCursor.getString(4));
                        mDrugs.setDrugSpecialConcern(mCursor.getString(5));
                        mDrugs.setDrugCategory(mCursor.getString(6));
                        mDrugs.setDrugStudyTopic(mCursor.getString(7));
                        mDrugs.setDrugNotes(mCursor.getString(8));
                        Log.d(TAG, "DRUGS NOTES: " + mCursor.getString(8));
                        drugs.add(mDrugs);
                        count++;

                    } while (mCursor.moveToNext());
                    Log.d(TAG, "DRUGS TOTAL: " + count);
                }
                mCursor.close();
                mDbHelper.close();
            }

        } catch (SQLiteException e) {
        }
    }
}
