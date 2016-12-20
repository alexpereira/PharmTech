package com.alexpereira.android.pharmatech10;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Jim on 12/17/2016.
 */

public class MixAndMatchFragment extends Fragment{
    private CheckBox cB1, cB2, cB3, cB4;
    private TextView answerChoice1, answerChoice2, answerChoice3, answerChoice4, quesionView;


    Drug drug = PharmTech.drugs.get(new Random().nextInt(PharmTech.drugs.size()));

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Mix and Match Quiz");
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        // -- inflate the layout for this fragment
        View inflatedView = inflater.inflate(R.layout.fragment_mixmatch, container, false);



        return inflatedView;
    }

}
