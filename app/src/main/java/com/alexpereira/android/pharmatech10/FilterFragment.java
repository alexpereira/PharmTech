package com.alexpereira.android.pharmatech10;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class FilterFragment extends DialogFragment {

    private String[] mPurposes = {"Purpose",
            "ACS",
            "AD(H)D",
            "Allergies",
            "Alzheimers",
            "Anti-Anxiety",
            "Anti-Depressant",
            "Anti-Depressant/sleep",
            "Anti-Dimentia",
            "Anti-inflammatory",
            "Anti-Psychotic",
            "Antibacterial",
            "Antibiotic",
            "Anticoagulant",
            "Anticonvulsant",
            "Antiemetic",
            "Antihistamine",
            "Antitussive",
            "Asthma",
            "Beta Blocker",
            "BPH",
            "CHF",
            "CHF/HBP",
            "Cholesterol",
            "COPD",
            "Coronary vasodilator",
            "Diabetes Type 2",
            "Diabetes Type I",
            "Diuretic",
            "Electrolyte",
            "Estrogen Replacement",
            "Expectorant",
            "Flu",
            "Frequent Urination",
            "Fungal infections",
            "GERD",
            "Glaucoma",
            "Gout",
            "HBP",
            "HBP/Angina",
            "Herpes Mgmt.",
            "I.B.S.",
            "Impotence",
            "Incontinence",
            "Low T",
            "Migraine",
            "Muscle Relaxer",
            "Opiate Addiction",
            "Osteoporosis",
            "Overactive Bladder",
            "Pain Relief",
            "Parkinson's",
            "Rheumatoid Arthritis",
            "Shingles Vaccine",
            "Sleep",
            "Sleep Aid",
            "Smoking addiction",
            "Thyroid replacement",
            "Ulcerative colitis",
            "Urinary tract analgesic",
            "Vitamin Supplement",
            "Weight Loss"};
    private String[] mCategory = {"Category",
            "ACE inhibitor",
            "Amphetamine",
            "Anti-Depressant/sleep",
            "Anti-fungal",
            "Anti-histamine",
            "Anti-infective",
            "Anti-platelet",
            "Anti-viral",
            "Antibacterial",
            "Anticholinergic",
            "Anticoagulent",
            "ARB",
            "Benzodiazepine",
            "Beta stimulant",
            "Beta-blocker",
            "Bisphosphonate",
            "Cardiovascular",
            "CCB",
            "Cephalosporin",
            "CNS",
            "Corticosteroid",
            "Diabetes",
            "Diuretic",
            "GI",
            "H-2 blocker",
            "Hormone",
            "Hypnotic",
            "K replacement",
            "Macrolide",
            "Marcrolide",
            "Musclo-skeletal",
            "Musculo-skeletal",
            "Musculoskeletal",
            "non-opiate",
            "NSAID",
            "Ophthalmic",
            "Opiate",
            "Penicillin",
            "PPI",
            "Quinolone",
            "Respiratory",
            "SNRI",
            "SSRI",
            "Statin",
            "Tetracycline",
            "Topical analgesic",
            "Tricyclic",
            "Urinary",
            "Vaccine",
            "Vitamin"};
    private String[] mStudyTopic = {"Study Topic",
            "Analgesics",
            "Anti-Infectives",
            "CNS",
            "CV",
            "Derm",
            "Dermatology",
            "DM/Endocrine",
            "Eyes",
            "GI System",
            "Hematology",
            "Musculo-Skeletal System",
            "Respiratory System",
            "Urinary System"};

    private Spinner purpose_spinner;
    private Spinner category_spinner;
    private Spinner study_topic_spinner;
    private Button button;


    onFilterSelectedListener mCallback;
    private String mPurposePicked;
    private String mCategoryPicked;
    private String mStudyTopicPicked;

    // Container Activity must implement this interface
    public interface onFilterSelectedListener {
        public abstract void onFilterSelected(String purpose, String category, String study_topic);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (onFilterSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter, container, false);


        purpose_spinner = (Spinner) view.findViewById(R.id.spinner1);
        category_spinner = (Spinner) view.findViewById(R.id.spinner2);
        study_topic_spinner = (Spinner) view.findViewById(R.id.spinner3);

        button = (Button) view.findViewById(R.id.button1);

        //Purpose spinner
        ArrayAdapter<String> adapter1 = new ArrayAdapter<> (getActivity().getBaseContext(),  android.R.layout.simple_spinner_item, mPurposes);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        purpose_spinner.setAdapter(adapter1);

        //Category spinner
        ArrayAdapter<String> adapter2 = new ArrayAdapter<> (getActivity().getBaseContext(),  android.R.layout.simple_spinner_item, mCategory);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category_spinner.setAdapter(adapter2);

        //Study topic spinner
        ArrayAdapter<String> adapter3 = new ArrayAdapter<> (getActivity().getBaseContext(),  android.R.layout.simple_spinner_item, mStudyTopic);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        study_topic_spinner.setAdapter(adapter3);


        purpose_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                mPurposePicked = mPurposes[position];
                dynamicButton();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // do nothing
            }

        });


        category_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                mCategoryPicked = mCategory[position];
                dynamicButton();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // do nothing
            }

        });

        study_topic_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                mStudyTopicPicked = mStudyTopic[position];
                dynamicButton();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // do nothing
            }

        });




        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mCallback.onFilterSelected(mPurposePicked, mCategoryPicked, mStudyTopicPicked);
                dismiss();
            }
        });

        return view;

    }

    public void dynamicButton(){
        if( purpose_spinner.getSelectedItemPosition() != 0 ||
            category_spinner.getSelectedItemPosition() != 0 ||
            study_topic_spinner.getSelectedItemPosition() != 0)
            button.setText("APPLY FILTERS");
        else
            button.setText("RESET");
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    // TODO: Figure out what the fuck is going on

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        return dialog;
    }

}
