package com.alexpereira.android.pharmatech10;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class FilterFragment extends DialogFragment {

    private String[] mPurposes = {"Purpose","ACS","AD(H)D","ADHD / Narcolepsy","Allergies","Alzheimers","Antiaddictive","Antianxiety","Antiarthritis","Antibacterial","Antibiotic","Anticoagulant","Anticonvulsant","Antidepressant","Antidepressant/sleep","Antidimentia","Antiemetic","Antiepileptic","Antifungal","Antigout","Antihistamine","Antiinflammatory","Antiplatelet","Antipsychotic","Antitussive","Asthma","Asthma Inhaler","Beta Blocker","BPH","CHF","CHF/HBP","Cholesterol","Cholesterol Combo","COPD","Coronary vasodilator","Corticosteroid","Diabetes","Diabetes Type I","Diabetes Type II","Diuretic","DM type 2","Electrolyte","Estrogen","Expectorant","Flu","Freq. Urination","Fungal infections","GERD","Glaucoma","Gout","HBP","HBP/Angina","Herpes Mgmt.","Hypertension","I.B.S.","Impotence","Incontinence","Long-Acting Insulin","Low T","Migraine","Muscle Relaxer","Narcotic Analgesic","NSAID","NSAID Gel","Ocular Hypertension","Opiate Addiction","Osteoporosis","Overactive Bladder","Pain Relief","Pain, inflammation, antipyretic","Parkinson's","Rheum. arthritis","Shingles Vaccine","Sleep Aid","Smoking addiction","Supplement","Thyroid Hormone","Thyroid replacement","Ulcerative colitis","Urinary tract analgesic","Weight Loss"};
    private String[] mCategory = {"Category","ACE Inhibitor","Amphetamine","Analgesic","Anti-coagulent","Anti-Depressant/sleep","Anti-fungal","Anti-histamine","Anti-infective","Anti-platelet","Anti-viral","Antibacterial","Anticholinergic","ARB","Benzodiazepine","Beta stimulant","Beta-blocker","Bisphosphonate","Calc. Chan. Blocker","Cardiovascualr","Cardiovascular","Cardivascular","Cephalosporin","CNS","Corticosteroid","Diabetes","Diuretic","Endocrine","GI","H-2 blocker","Hormone","Hypnotic","K replacement","Long-Acting Insulin","Macrolide","Musculo-skeletal","non-opiate","NSAID","NSAID (Arthritis)","Ophthalmic","Opiate","Penicillin","PPI","Quinolone","Rapid-Acting Insulin","Respiratory","SNRI","SSRI","Statin","Tetracycline","Topical analgesic","Tricyclic","Urinary","Vaccine","Vitamin"};
    private String[] mStudyTopic = {"Study Topic","Analgesic","Anti-infective","CNS","CV","Derm","DM/Endocrine","Eye","GI","Heme","Musculo-skeletal","Respiratory","Skin","Urine"};

    private Spinner purpose_spinner;
    private Spinner category_spinner;
    private Spinner study_topic_spinner;
    private Button button;

//    private ArrayList<Drug> unfilteredDrugs;
//    private ArrayList<Drug> filteredDrugs;

    onPurposeSelectedListener mCallback;
    private String mPurposePicked;
    private String mCategoryPicked;
    private String mStudyTopicPicked;

    // Container Activity must implement this interface
    public interface onPurposeSelectedListener {
        public abstract void onPurposeSelected(String purpose, String category, String study_topic);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (onPurposeSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
//        unfilteredDrugs = new ArrayList<>();
//        filteredDrugs = new ArrayList<>();
//        unfilteredDrugs = cloneList(PharmTech.drugs);


        Context context = getActivity().getApplicationContext();
        CharSequence text = "FilterFragment onCreate";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();


    }

//    public ArrayList<Drug> filterByPurpose(int position) {
//        for (int i = 0; i < unfilteredDrugs.size(); i++) {
//            Log.d(TAG, "DRUG.GETPUR: " + unfilteredDrugs.get(i).getDrugPurpose().toLowerCase() + " ?? " + mPurposes[position].toLowerCase());
//            if (unfilteredDrugs.get(i).getDrugPurpose().toLowerCase().equals(mPurposes[position].toLowerCase())) {
//                filteredDrugs.add(unfilteredDrugs.get(i));
//                Log.d(TAG, "DRUG ADDED: " + unfilteredDrugs.get(i));
//            }
//        }
//
//        Log.d(TAG, "PURPOSE SELECTED: " + mPurposes[position]);
//        return filteredDrugs;
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter, container, false);


        // Initializing widgets

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
                // your code here
            }

        });


        category_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                mCategoryPicked = mCategory[position];
                dynamicButton();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
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
                // your code here
            }

        });




        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mCallback.onPurposeSelected(mPurposePicked, mCategoryPicked, mStudyTopicPicked);
                dismiss();
            }
        });



        Context context = getActivity().getApplicationContext();
        CharSequence text = "FilterFragment onCreateView";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

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
//        filteredDrugs.clear();
//        unfilteredDrugs.clear();
//        unfilteredDrugs.addAll(PharmTech.drugs);

        Context context = getActivity().getApplicationContext();
        CharSequence text = "FilterFragment onDismiss";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//
//        filteredDrugs.clear();
//        unfilteredDrugs.clear();
//        unfilteredDrugs.addAll(PharmTech.drugs);

        Context context = getActivity().getApplicationContext();
        CharSequence text = "FilterFragment onDismiss";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

    @Override
    public void onDetach() {
        super.onDetach();

//
//        filteredDrugs.clear();
//        unfilteredDrugs.clear();
//        unfilteredDrugs.addAll(PharmTech.drugs);

        Context context = getActivity().getApplicationContext();
        CharSequence text = "FilterFragment onDetach";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

    @Override
    public void onStop() {
        super.onStop();
//
//        filteredDrugs.clear();
//        unfilteredDrugs.clear();
//        unfilteredDrugs.addAll(PharmTech.drugs);

        Context context = getActivity().getApplicationContext();
        CharSequence text = "FilterFragment onStop";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

    // TODO: Figure out what the fuck is going on

    @Override
    public void onDestroy() {
        super.onDestroy();
//
//
//        filteredDrugs.clear();
//        unfilteredDrugs.clear();
//        unfilteredDrugs.addAll(PharmTech.drugs);

        Context context = getActivity().getApplicationContext();
        CharSequence text = "FilterFragment onDestroy";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        Context context = getActivity().getApplicationContext();
        CharSequence text = "FilterFragment onCreateDialog";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        return dialog;
    }

//    public static ArrayList<Drug> cloneList(ArrayList<Drug> drugs) {
//        ArrayList<Drug> clonedList = new ArrayList<Drug>(drugs.size());
//        for (Drug drug : drugs) {
//            clonedList.add(new Drug(drug));
//        }
//        return clonedList;
//    }

}
