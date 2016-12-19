package com.alexpereira.android.pharmatech10;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.ArrayList;

public class ReviewFragment extends Fragment  implements Serializable, ReviewAdapter.RecyclerViewClickListener {
    private static final String TAG = "RecyclerViewFragment";
    public static final String FILTERS = "filters";
    private ArrayList<Drug> filteredDrugs = new ArrayList();



    private ArrayList<Drug> data = PharmTech.drugs;

    protected RecyclerView mRecyclerView;
    protected ReviewAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void recyclerViewListClicked(View v, int position, ViewGroup viewGroup){
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

        mAdapter = new ReviewAdapter(data, getActivity(), this);

        mRecyclerView.setAdapter(mAdapter);


        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle args = getArguments();
        if (args != null) {
            filteredDrugs = args.getParcelableArrayList(FILTERS);
            data.clear();
            for (Drug drug : filteredDrugs) {
                data.add(drug);
            }
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

}