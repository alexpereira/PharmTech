package com.alexpereira.android.pharmatech10;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Random;

/**
 * Created by alexpereira on 10/21/16.
 */

public class HomeFragment extends Fragment {
    public HomeFragment() {
    }

    Calendar calendar = Calendar.getInstance();
    int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
    Drug drug;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Home");
        setHasOptionsMenu(true);

        if (dayOfYear < 200)
            drug = PharmTech.drugs.get(dayOfYear);
        else
            drug = PharmTech.drugs.get(dayOfYear - 200);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        // -- inflate the layout for this fragment
        View inflatedView = inflater.inflate(R.layout.fragment_home, container, false);

        // Set the Text to try this out
        TextView drugName = (TextView) inflatedView.findViewById(R.id.drug_name);
        TextView drugBrandName = (TextView) inflatedView.findViewById(R.id.brand_name);
        TextView drugPurpose = (TextView) inflatedView.findViewById(R.id.purpose);
        TextView drugDEA = (TextView) inflatedView.findViewById(R.id.DEA_schedule);
        TextView drugSP = (TextView) inflatedView.findViewById(R.id.special_concerns);
        TextView drugCategory = (TextView) inflatedView.findViewById(R.id.drug_category);
        TextView drugST = (TextView) inflatedView.findViewById(R.id.study_topic);


        drugName.setText(drug.getDrugName());
        drugBrandName.setText(drug.getDrugBrand().replace("&#174;","®"));
        drugPurpose.setText(drug.getDrugPurpose());
        drugDEA.setText(drug.getDrugDEASchedule());
        drugSP.setText(drug.getDrugSpecialConcern());
        drugCategory.setText(drug.getDrugCategory());
        drugST.setText(drug.getDrugStudyTopic());

        return inflatedView;
    }
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        // Hides menu from home fragment
        menu.findItem(R.id.close_flashcards).setVisible(false).setEnabled(false);
        return;
    }
}
