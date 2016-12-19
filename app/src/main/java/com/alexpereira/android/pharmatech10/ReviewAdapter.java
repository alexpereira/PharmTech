package com.alexpereira.android.pharmatech10;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class ReviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final String TAG = "ReviewAdapter";

    private static ArrayList<Drug> mDataSet;

    private static TestAdapter mDbHelper;
    private static Context context;
    private static RecyclerViewClickListener itemListener;
    private static int positionToFlip = -1;



    public interface RecyclerViewClickListener
    {

        void recyclerViewListClicked(View v, int position, ViewGroup viewGroup);
    }


    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView mDrugName;
        private final TextView mBrandName;
        private final TextView mPurpose;
        private final TextView mDEASchedule;
        private final TextView mSpecialConcerns;
        private final TextView mCategory;
        private final TextView mStudyTopic;
        private final Button mNotesButton;

        public ViewHolder(final View v, final ViewGroup viewGroup) {
            super(v);
            mNotesButton = (Button) v.findViewById(R.id.notes_button);
            mDrugName = (TextView) v.findViewById(R.id.drug_name);
            mBrandName = (TextView) v.findViewById(R.id.brand_name);
            mPurpose = (TextView) v.findViewById(R.id.purpose);
            mDEASchedule = (TextView) v.findViewById(R.id.DEA_schedule);
            mSpecialConcerns = (TextView) v.findViewById(R.id.special_concerns);
            mCategory = (TextView) v.findViewById(R.id.drug_category);
            mStudyTopic = (TextView) v.findViewById(R.id.study_topic);

            mNotesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    positionToFlip = getAdapterPosition();

                    itemListener.recyclerViewListClicked(v, getLayoutPosition(), viewGroup);
                }
            });
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
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolderBack extends RecyclerView.ViewHolder{

        private final TextView mDrugNotes;
        private final Button mSaveNotes;
        private final Button mCancelNotes;

        public ViewHolderBack(final View v2, final ViewGroup viewGroup) {
            super(v2);

            mDrugNotes = (TextView) v2.findViewById(R.id.notes_field);
            mSaveNotes = (Button) v2.findViewById(R.id.save_notes_button);
            mCancelNotes = (Button) v2.findViewById(R.id.cancel_notes_button);

            mSaveNotes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    positionToFlip = -1;


                    InputMethodManager imm = (InputMethodManager) v.getContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);


                    try {
                        mDbHelper.open();
                    } catch (SQLiteException e) {
                        Log.d(TAG, "DATABASE DIDNT OPEN");
                    }
                    try {
                        Log.d(TAG, mDrugNotes.getText().toString());
                        int drugID = Integer.parseInt(mDrugNotes.getTag().toString());
                        String drugNotes = mDrugNotes.getText().toString();
                        Boolean updated = mDbHelper.updateTestData(drugNotes, drugID);

                        // update item in data
                        if(updated) {
                            mDataSet.get(getLayoutPosition()).setDrugNotes(drugNotes);
                        }

                        mDbHelper.close();

                    } catch (SQLiteException e) {
                        Log.d(TAG, "NOTES DIDNT SAVE");
                    }
                    itemListener.recyclerViewListClicked(v, getLayoutPosition(), viewGroup);
                }
            });
            mCancelNotes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    positionToFlip = -1;

                    InputMethodManager imm = (InputMethodManager) v.getContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                    itemListener.recyclerViewListClicked(v, getLayoutPosition(), viewGroup);
                }
            });
        }

        public TextView getCardDrugNotes() {
            return mDrugNotes;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(positionToFlip == position)
            return 2;
        else
            return 0;
    }

    /**
     * Initialize the dataset of the Adapter.
     */
    public ReviewAdapter(ArrayList<Drug> dataSet, Context context, RecyclerViewClickListener itemListener) {
        this.mDbHelper= new TestAdapter(context);

        mDataSet = dataSet;
        this.context = context;
        this.itemListener = itemListener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        // Create a new view.
        View card_front = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.drug_card, viewGroup, false);

        View card_back = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.drug_card_back, viewGroup, false);

        switch (viewType) {
            case 0: return new ViewHolder(card_front, viewGroup);
            case 2: return new ViewHolderBack( card_back, viewGroup);
        }
        return new ViewHolder(card_front, viewGroup);
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 0:
                ViewHolder viewHolder = (ViewHolder)holder;
                viewHolder.getCardDrugName().setText(mDataSet.get(position).getDrugName());
                viewHolder.getCardBrandName().setText(mDataSet.get(position).getDrugBrand().replace("&#174;","®"));
                viewHolder.getCardPurpose().setText(mDataSet.get(position).getDrugPurpose());
                viewHolder.getCardDEASchedule().setText(mDataSet.get(position).getDrugDEASchedule());
                viewHolder.getCardSpecialConcerns().setText(mDataSet.get(position).getDrugSpecialConcern());
                viewHolder.getCardCategory().setText(mDataSet.get(position).getDrugCategory());
                viewHolder.getCardStudyTopic().setText(mDataSet.get(position).getDrugStudyTopic());
                break;

            case 2:
                ViewHolderBack viewHolderBack = (ViewHolderBack) holder;
                viewHolderBack.getCardDrugNotes().setText(mDataSet.get(position).getDrugNotes());
                viewHolderBack.getCardDrugNotes().setTag(mDataSet.get(position).getDrugID());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}