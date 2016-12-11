package com.alexpereira.android.pharmatech10;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";

    private ArrayList<Drug> mDataSet;

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mDrugName;
        private final TextView mBrandName;
        private final TextView mPurpose;
        private final TextView mDEASchedule;
        private final TextView mSpecialConcerns;
        private final TextView mCategory;
        private final TextView mStudyTopic;



        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            mDrugName = (TextView) v.findViewById(R.id.drug_name);
            mBrandName = (TextView) v.findViewById(R.id.brand_name);
            mPurpose = (TextView) v.findViewById(R.id.purpose);
            mDEASchedule = (TextView) v.findViewById(R.id.DEA_schedule);
            mSpecialConcerns = (TextView) v.findViewById(R.id.special_concerns);
            mCategory = (TextView) v.findViewById(R.id.drug_category);
            mStudyTopic = (TextView) v.findViewById(R.id.study_topic);
        }

        public TextView getCardDrugName() {
            return mDrugName;
        }
        public TextView getCardBrandName() {
            return mBrandName;
        }
        public TextView getCardPurpose() {
            return mPurpose;
        }
        public TextView getCardDEASchedule() {
            return mDEASchedule;
        }
        public TextView getCardSpecialConcerns() {
            return mSpecialConcerns;
        }
        public TextView getCardCategory() {
            return mCategory;
        }
        public TextView getCardStudyTopic() {
            return mStudyTopic;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     */
    public CustomAdapter(ArrayList<Drug> dataSet) {
        mDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.drug_card, viewGroup, false);

        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.getCardDrugName().setText(mDataSet.get(position).getDrugName());
        viewHolder.getCardBrandName().setText(mDataSet.get(position).getDrugBrand());
        viewHolder.getCardPurpose().setText(mDataSet.get(position).getDrugPurpose());
        viewHolder.getCardDEASchedule().setText(mDataSet.get(position).getDrugDEASchedule());
        viewHolder.getCardSpecialConcerns().setText(mDataSet.get(position).getDrugSpecialConcern());
        viewHolder.getCardCategory().setText(mDataSet.get(position).getDrugCategory());
        viewHolder.getCardStudyTopic().setText(mDataSet.get(position).getDrugStudyTopic());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return PharmTech.drugs.size();
    }
}