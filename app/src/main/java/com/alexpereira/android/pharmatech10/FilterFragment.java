package com.alexpereira.android.pharmatech10;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by alexpereira on 12/10/16.
 */

public class FilterFragment extends DialogFragment {

    private ArrayList<String> mPurposes= PharmTech.drugPurposes;

    // Study topic
    // DEA Schedule
    // Purpose
    // Category


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter, container, false);

        // Initializing widgets

        Spinner purpose_spinner = (Spinner) view.findViewById(R.id.spinner1);
        final Button button = (Button) view.findViewById(R.id.button1);

        //Purpose spinner
        ArrayAdapter<String> adapter1 = new ArrayAdapter<> (getActivity().getBaseContext(),  android.R.layout.simple_spinner_item, PharmTech.drugPurposes);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        purpose_spinner.setAdapter(adapter1);
//
//        //Category spinner
//        Spinner category_spinner = (Spinner) findViewById(R.id.spinner2);
//        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.drawer_items, android.R.layout.simple_spinner_item);
//        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        category_spinner.setAdapter(adapter2);
//
//        //Study topic spinner
//        Spinner topic_spinner = (Spinner) findViewById(R.id.spinner3);
//        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
//                R.array.drawer_items, android.R.layout.simple_spinner_item);
//        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        topic_spinner.setAdapter(adapter3);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dismiss();
            }
        });



        return view;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

}
