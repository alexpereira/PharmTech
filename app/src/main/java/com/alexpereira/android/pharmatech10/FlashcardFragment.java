package com.alexpereira.android.pharmatech10;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by alexpereira on 12/19/16.
 */

public class FlashcardFragment extends Fragment {

    public static final String ARG_OBJECT = "object";
    CardView card;
    LinearLayout card_cover;



    private Drug filteredDrugs = new Drug();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_flashcard, container, false);
        Bundle args = getArguments();

        filteredDrugs = args.getParcelable(ARG_OBJECT);

        ((TextView) rootView.findViewById(R.id.drug_name)).setText(filteredDrugs.getDrugName());
        ((TextView) rootView.findViewById(R.id.brand_name)).setText(filteredDrugs.getDrugBrand().replace("&#174;","®"));
        ((TextView) rootView.findViewById(R.id.purpose)).setText(filteredDrugs.getDrugPurpose());
        ((TextView) rootView.findViewById(R.id.DEA_schedule)).setText(filteredDrugs.getDrugDEASchedule());
        ((TextView) rootView.findViewById(R.id.special_concerns)).setText(filteredDrugs.getDrugSpecialConcern());
        ((TextView) rootView.findViewById(R.id.drug_category)).setText(filteredDrugs.getDrugCategory());
        ((TextView) rootView.findViewById(R.id.study_topic)).setText(filteredDrugs.getDrugStudyTopic());
        ((TextView) rootView.findViewById(R.id.cover_name)).setText(filteredDrugs.getDrugName());

        card = (CardView) rootView.findViewById(R.id.card_view);
        card_cover = (LinearLayout) rootView.findViewById(R.id.drug_cover);


        card.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {

                card_cover.setVisibility(View.INVISIBLE);

                return true;
            }
        });

        return rootView;
    }
}