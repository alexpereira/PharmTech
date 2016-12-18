package com.alexpereira.android.pharmatech10;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final String TAG = "CustomAdapter";

    private static ArrayList<Drug> mDataSet;
    private static View view;

    private static TestAdapter mDbHelper;
    private static Context context;
    private static RecyclerViewClickListener itemListener;
    private static int positionToFlip = -1;
    private static int positionToFlipBack = -1;
    private int lastPosition = -1;

    public interface RecyclerViewClickListener
    {

        void recyclerViewListClicked(View v, int position, ViewGroup viewGroup);
    }


    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final CardView mCardView;
        private final TextView mDrugName;
        private final TextView mBrandName;
        private final TextView mPurpose;
        private final TextView mDEASchedule;
        private final TextView mSpecialConcerns;
        private final TextView mCategory;
        private final TextView mStudyTopic;
        private final Button mNotesButton;

        public ViewHolder(final View v, final View v2, final ViewGroup viewGroup) {
            super(v);
            // Define click listener for the ViewHolder's View.
//            v.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
//                    itemListener.recyclerViewListClicked(v, getLayoutPosition(), viewGroup);
//
//                    //viewGroup.addView(v2, getAdapterPosition());
//                    mDrugName.setText("YO");
//                    positionToFlip = getAdapterPosition();
//
//                    //viewGroup.removeView(v);
//                }
//            });
            mCardView = (CardView) v.findViewById(R.id.card_view);
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
                    Log.d(TAG, "BUTTON clicked.");
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

        public ViewHolderBack(final View v, final View v2, final ViewGroup viewGroup) {
            super(v2);
            // Define click listener for the ViewHolder's View.
//            v.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                }
//            });

            mDrugNotes = (TextView) v2.findViewById(R.id.notes_field);
            mSaveNotes = (Button) v2.findViewById(R.id.save_notes_button);
            mCancelNotes = (Button) v2.findViewById(R.id.cancel_notes_button);
            mSaveNotes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "SAVE BUTTON  clicked.");
                    positionToFlip = -1;


                    InputMethodManager imm = (InputMethodManager) v.getContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

//                    String UpdateRecordQuery = "UPDATE Druglist SET Notes='" + mDrugNotes.getText() + "' WHERE id=" + mDrugNotes.getTag() + ";";
//
//                    mDbHelper.execSQL(UpdateRecordQuery);

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
                            Log.d(TAG, "POSITION: SEE THIS " + getLayoutPosition());
                            Log.d(TAG, "NOTES: SEE THIS " + drugNotes);
                            mDataSet.get(getLayoutPosition()).setDrugNotes(drugNotes);
                        }

                        Log.d(TAG, "MAYBE SAVED...?");
                        mDbHelper.close();
                        Log.d(TAG, "MAYBE CLOSED...?");

                    } catch (SQLiteException e) {
                        Log.d(TAG, "NOTES DIDNT SAVE");
                    }
                    itemListener.recyclerViewListClicked(v, getLayoutPosition(), viewGroup);
                }
            });
            mCancelNotes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "CANCEL BUTTON  clicked.");
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
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        if(positionToFlip == position)
            return 2;
        else
            return 0;
    }

    /**
     * Initialize the dataset of the Adapter.
     */
    public CustomAdapter(ArrayList<Drug> dataSet,  Context context, RecyclerViewClickListener itemListener) {
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
            case 0: return new ViewHolder(card_front, card_back, viewGroup);
            case 2: return new ViewHolderBack(card_front, card_back, viewGroup);
        }
        return new ViewHolder(card_front, card_back, viewGroup);
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
                //setAnimation(viewHolder.mCardView, position);
                break;

            case 2:
                ViewHolderBack viewHolderBack = (ViewHolderBack) holder;
                viewHolderBack.getCardDrugNotes().setText(mDataSet.get(position).getDrugNotes());
                viewHolderBack.getCardDrugNotes().setTag(mDataSet.get(position).getDrugID());
                // Here you apply the animation when the view is bound

                break;
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return PharmTech.drugs.size();
    }
    /**
     * Here is the key method to apply the animation
     */
    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

}