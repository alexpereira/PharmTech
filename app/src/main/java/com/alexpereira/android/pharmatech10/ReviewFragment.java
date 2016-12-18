package com.alexpereira.android.pharmatech10;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class ReviewFragment extends Fragment  implements Serializable, CustomAdapter.RecyclerViewClickListener {
    private static final String TAG = "RecyclerViewFragment";
    public static final String FILTERS = "filters";
    private ArrayList<Drug> filteredDrugs = new ArrayList();



    private ArrayList<Drug> data = PharmTech.drugs;

    protected RecyclerView mRecyclerView;
    protected CustomAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void recyclerViewListClicked(View v, int position, ViewGroup viewGroup){
        // THIS IS WHERE IMMA FLIP THAT MOTHERFUCKER
        Log.d(TAG, "Element " + position + " clicked. BITCHESSS");


        //int optionId = someExpression ? R.layout.option1 : R.layout.option2;


//        int optionId = someExpression ? R.layout.option1 : R.layout.option2;
//
//        View C = findViewById(R.id.C);
//        ViewGroup parent = (ViewGroup) C.getParent();
//        int index = parent.indexOfChild(C);
//        viewGroup.removeView(v);
//        v.setLayoutResource()
//        v = getLayoutInflater().inflate(R.layout.drug_card_back, parent, false);
//        parent.addView(C, index);

        //viewGroup.removeView(v);

        mAdapter.notifyItemChanged(position);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Review");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_review, container, false);
        rootView.setTag(TAG);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        mLayoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new CustomAdapter(data, this.getContext(), this);

        mRecyclerView.setAdapter(mAdapter);

        return rootView;
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
            filteredDrugs = args.getParcelableArrayList(FILTERS);
            data.clear();
            for (Drug drug : filteredDrugs) {
                data.add(drug);
            }

            // Notify adapter to filter to new data
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        super.onSaveInstanceState(savedInstanceState);
    }
}