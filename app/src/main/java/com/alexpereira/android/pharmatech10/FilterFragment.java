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

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexpereira on 12/10/16.
 */

public class FilterFragment extends DialogFragment {

    private String[] mPurposes = {"Purpose","ACS","AD(H)D","ADHD / Narcolepsy","Allergies","Allergies ","Alzheimers","Antiaddictive","Antianxiety","Antiarthritis","Antibacterial","Antibiotic","Anticoagulant","Anticonvulsant","Antidepressant","Antidepressant/sleep","Antidimentia","Antiemetic","Antiepileptic","Antifungal","Antigout","Antihistamine","Antiinflammatory","Antiplatelet","Antipsychotic","Antitussive","Asthma","Asthma Inhaler","Beta Blocker","BPH","CHF","CHF/HBP","Cholesterol","Cholesterol Combo","COPD","Coronary vasodilator","Corticosteroid","Diabetes","Diabetes Type I","Diabetes Type II","Diuretic","DM type 2","Electrolyte","Estrogen","Expectorant","Flu","Freq. Urination","Fungal infections","GERD","Glaucoma","Gout","HBP","HBP/Angina","Herpes Mgmt.","Hypertension","I.B.S.","Impotence","Incontinence","Long-Acting Insulin","Low T","Migraine","Muscle Relaxer","Narcotic Analgesic","NSAID","NSAID Gel","Ocular Hypertension","Opiate Addiction","Osteoporosis","Overactive Bladder","Pain Relief","Pain, inflammation, antipyretic","Parkinson's","Rheum. arthritis","Shingles Vaccine","Sleep Aid","Smoking addiction","Supplement","Thyroid Hormone","Thyroid replacement","Ulcerative colitis","Urinary tract analgesic","Weight Loss"};
    private String[] mCategory = {"Category","ACE Inhibitor","Amphetamine","Analgesic","Anti-coagulent","Anti-Depressant/sleep","Anti-fungal","Anti-histamine","Anti-infective","Anti-platelet","Anti-viral","Antibacterial","Anticholinergic","ARB","Benzodiazepine","Beta stimulant","Beta-blocker","Bisphosphonate","Calc. Chan. Blocker","Cardiovascualr","Cardiovascular","Cardivascular","Cephalosporin","CNS","Corticosteroid","Diabetes","Diuretic","Endocrine","GI","H-2 blocker","Hormone","Hypnotic","K replacement","Long-Acting Insulin","Macrolide","Musculo-skeletal","non-opiate","NSAID","NSAID (Arthritis)","Ophthalmic","Opiate","Penicillin","PPI","Quinolone","Rapid-Acting Insulin","Respiratory","SNRI","SSRI","Statin","Tetracycline","Topical analgesic","Tricyclic","Urinary","Vaccine","Vitamin"};
    private String[] mStudyTopic = {"Study Topic","Analgesic","Anti-infective","CNS","CV","Derm","DM/Endocrine","Eye","GI","Heme","Musculo-skeletal","Respiratory","Skin","Urine"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter, container, false);

        // Initializing widgets

        Spinner purpose_spinner = (Spinner) view.findViewById(R.id.spinner1);
        Spinner category_spinner = (Spinner) view.findViewById(R.id.spinner2);
        Spinner study_topic_spinner = (Spinner) view.findViewById(R.id.spinner3);

        final Button button = (Button) view.findViewById(R.id.button1);

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
