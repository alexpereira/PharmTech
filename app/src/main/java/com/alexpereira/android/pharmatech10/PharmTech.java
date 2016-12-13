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

    private PharmatechdbHelper mPharmaTechDB;

    private int count =0;


    @Override
    public void onCreate() {
        super.onCreate();

        mPharmaTechDB = new PharmatechdbHelper(this);
        try {
            mPharmaTechDB.checkAndCopyDB();
            mPharmaTechDB.db_OPEN();
        } catch (SQLiteException e) {

        }
        try {
            // the table name is : Druglist
            // here is the part that needs to be modified
            // i think there is a problem with the buffer
            // the app couldnt load all the 400 drugs at the same time
            //
            Cursor mCursor = mPharmaTechDB.mQuery("SELECT * FROM Druglist");
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
                        drugs.add(mDrugs);
                        count++;

                    } while (mCursor.moveToNext());
                    Log.d(TAG, "DRUGS TOTAL: " + count);
                }
            }

        } catch (SQLiteException e) {
        }
    }
}
