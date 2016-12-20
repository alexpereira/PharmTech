package com.alexpereira.android.pharmatech10;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jim Sacchetti on 12/17/2016.
 */

public class MultipleChoiceFragment extends Fragment {

    private Drug mDrugs = PharmTech.drugs.get(new Random().nextInt(PharmTech.drugs.size()));
    private static final String TAG = "MyActivity";

    public static final String ARG_PURPOSE = "purpose";

    private ArrayList<Drug> mFilteredDrugs = new ArrayList();
    private ArrayList<Drug> mData = PharmTech.drugs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Multiple Choice Quiz");
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // -- inflate the layout for this fragment
        //Log.i(TAG, mDrugs.getDrugName().toString() + " " + mDrugs.getDrugPurpose().toString() + " " + mDrugs.getDrugBrand().toString());
        View inflatedView = inflater.inflate(R.layout.fragment_multiplechoice, container, false);

        setVariables(inflatedView);

        return inflatedView;
    }

    @Override
    public void onStart() {
        super.onStart();

        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            mFilteredDrugs = args.getParcelableArrayList(ARG_PURPOSE);
            mData.clear();
            for (Drug drug : mFilteredDrugs) {
                mData.add(drug);
            }


        }
    }

    private void setVariables(View view) {
        CheckBox mChk1, mChk2, mChk3, mChk4;
        TextView mTv1, mTv2, mTv3, mTv4, mTvQuestion;
        //hook up checkboxes
        mChk1 = (CheckBox) view.findViewById(R.id.checkbox1);
        mChk2 = (CheckBox) view.findViewById(R.id.checkbox2);
        mChk3 = (CheckBox) view.findViewById(R.id.checkbox3);
        mChk4 = (CheckBox) view.findViewById(R.id.checkbox4);

        //Log.i(TAG, mDrugs.getDrugBrand().toString());
        //hook up text views
        mTv1 = (TextView) view.findViewById(R.id.textview1);
        mTv2 = (TextView) view.findViewById(R.id.textview2);
        mTv3 = (TextView) view.findViewById(R.id.textview3);
        mTv4 = (TextView) view.findViewById(R.id.textview4);
        mTvQuestion = (TextView) view.findViewById(R.id.question_text_view);

        mTv1.setText("Option 1");
        mTv2.setText("Option 2");
        mTv3.setText("Option 3");
        mTv4.setText("Option 4");


        setMainTextviewQuestion(mTvQuestion);
        for (int i = 0; i <=4; i++)
            setUpQuestionAnswers("mTv"+ i, "mChk" + i );

    }

    private void setMainTextviewQuestion(TextView mTvQuestion)
    {
        for(int i = 0; i < 100; i++)
            System.out.print(randomNumber(4, 1) + "\n");
        String mQuText = "";
        String mQuTextQuestion = "";
        String mFullQuest=" ";
        int mReturn = 0;
        switch(randomNumber(6,1)){
            case 1: mQuText = mDrugs.getDrugBrand().toString();
                mReturn = 1; break;
            case 2: mQuText = mDrugs.getDrugCategory().toString();
                mReturn = 2; break;
            case 3: mQuText = mDrugs.getDrugDEASchedule().toString();
                mReturn = 3; break;
            case 4: mQuText = mDrugs.getDrugName().toString();
                mReturn = 4; break;
            case 5: mQuText = mDrugs.getDrugSpecialConcern().toString();
                mReturn = 5; break;
            case 6: mQuText = mDrugs.getDrugPurpose().toString();
                mReturn = 6; break;
        }



        switch (mReturn){
            case 1: mQuTextQuestion = getResources().getString(R.string.naming_questions2); //naming
                mFullQuest = mQuTextQuestion + mQuText; break;

            case 2: mQuTextQuestion = getResources().getString(R.string.catagory_questions); //catagory
                mFullQuest = mQuText + mQuTextQuestion; break;

            case 3: if(randomNumber(2, 1)== 2){
                mQuText = mDrugs.getDrugBrand().toString(); //brand name purpose
            }
            else
                mQuText = mDrugs.getDrugName().toString(); // tradename

                mQuTextQuestion = getResources().getString(R.string.schedule_question);//schedule
                mFullQuest = mQuText + mQuTextQuestion; break;

            case 4: mQuTextQuestion =  getResources().getString(R.string.naming_questions1); //naming
                mFullQuest = mQuTextQuestion + " " + mQuText; break;

            case 5: mQuTextQuestion = getResources().getString(R.string.special_concerns_question);//special
                mFullQuest = mQuTextQuestion + mQuText; break;

            case 6: if(randomNumber(2, 1)== 2){
                mQuText = mDrugs.getDrugBrand().toString(); //brand name purpose
            }
            else
                mQuText = mDrugs.getDrugName().toString(); // tradename
                mQuTextQuestion = getResources().getString(R.string.purpose_question);

                mFullQuest = mQuTextQuestion + mQuText + ", is "; break;


        }


        mTvQuestion.setText(mFullQuest);

    }

    private int randomNumber(int max, int min) {
        int ranNum;
        Random rand = new Random();
        ranNum = rand.nextInt(((max+1) - (min+1)) + min);
        return ranNum;
    }

    private void setUpQuestionAnswers(String mTv, String mChk) {
        //Log.i(TAG, mTv + "\n");
    }

    private void compareAnswer() {


    }


}
