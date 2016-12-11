package com.alexpereira.android.pharmatech10;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.ArrayList;

public class ReviewFragment extends Fragment  implements Serializable{
    private static final String TAG = "RecyclerViewFragment";
    public static final String ARG_PURPOSE = "purpose";
    private ArrayList<Drug> filteredDrugs = new ArrayList();
    private String currentPurpose = "";

    private ArrayList<Drug> data = PharmTech.drugs;
    protected RecyclerView mRecyclerView;
    protected CustomAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Review");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_review, container, false);
        rootView.setTag(TAG);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        mLayoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new CustomAdapter(data);

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
            filteredDrugs = args.getParcelableArrayList(ARG_PURPOSE);
            data.clear();
            for (Drug drug: filteredDrugs) {
                data.add(drug);
            }

            Log.d(TAG, "PURPOSE ARRIVED: " + filteredDrugs);
            mAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        super.onSaveInstanceState(savedInstanceState);
    }
}



//
//
//package com.alexpereira.android.pharmatech10;
//
//        import android.app.Fragment;
//        import android.os.Bundle;
//        import android.support.v7.widget.LinearLayoutManager;
//        import android.support.v7.widget.RecyclerView;
//        import android.util.Log;
//        import android.view.LayoutInflater;
//        import android.view.View;
//        import android.view.ViewGroup;
//
//        import java.util.ArrayList;
//
//public class ReviewFragment extends Fragment {
//    private static final String TAG = "RecyclerViewFragment";
//    public static final String ARG_PURPOSE = "purpose";
//    private String currentPurpose = "";
//    private ArrayList<Drug> data = PharmTech.drugs;
//    protected RecyclerView mRecyclerView;
//    protected CustomAdapter mAdapter;
//    protected RecyclerView.LayoutManager mLayoutManager;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Review");
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_review, container, false);
//        rootView.setTag(TAG);
//
//        mLayoutManager = new LinearLayoutManager(getActivity());
//
//        mRecyclerView.setLayoutManager(mLayoutManager);
//
//        mAdapter = new CustomAdapter(data);
//
//        mRecyclerView.setAdapter(mAdapter);
//
//        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
//        return rootView;
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//
//        // During startup, check if there are arguments passed to the fragment.
//        // onStart is a good place to do this because the layout has already been
//        // applied to the fragment at this point so we can safely call the method
//        // below that sets the article text.
//        Bundle args = getArguments();
//        if (args != null) {
//            // Set article based on argument passed in
//            currentPurpose = args.getString(ARG_PURPOSE);
//            Log.d(TAG, "PURPOSE ARRIVED: " + currentPurpose);
//
//            if (!currentPurpose.isEmpty()) {
//                data = PharmTech.testDrugs;
//                mAdapter.notifyDataSetChanged();
//            }
//        }
//    }
//    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState) {
//        // Save currently selected layout manager.
//        super.onSaveInstanceState(savedInstanceState);
//    }
//}
