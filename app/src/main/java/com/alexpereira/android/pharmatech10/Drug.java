package com.alexpereira.android.pharmatech10;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alexpereira on 10/22/16.
 */

public class Drug implements Parcelable{
    private int mDrugID;
    private String mDrugName;
    private String mDrugBrand;
    private String mDrugPurpose;
    private String mDrugDEASchedule;
    private String mDrugSpecialConcern;
    private String mDrugCategory;
    private String mDrugStudyTopic;
    private String mDrugNotes;

    public Drug() {
    }

    public Drug(Drug drug){
        this.mDrugID = drug.getDrugID();
        this.mDrugBrand = drug.getDrugBrand();
        this.mDrugCategory = drug.getDrugCategory();
        this.mDrugDEASchedule = drug.getDrugDEASchedule();
        this.mDrugName = drug.getDrugName();
        this.mDrugPurpose = drug.getDrugPurpose();
        this.mDrugSpecialConcern = drug.getDrugSpecialConcern();
        this.mDrugStudyTopic = drug.getDrugStudyTopic();
        this.mDrugNotes = drug.getDrugNotes();
    }

    public void setDrugID(String val){ mDrugID = Integer.parseInt(val) ; }
    public void setDrugName(String val){ mDrugName = val; }
    public void setDrugBrand(String val){ mDrugBrand = val; }
    public void setDrugPurpose(String val){ mDrugPurpose = val; }
    public void setDrugDEASchedule(String val){ mDrugDEASchedule = val; }
    public void setDrugSpecialConcern(String val){ mDrugSpecialConcern = val; }
    public void setDrugCategory(String val){ mDrugCategory = val; }
    public void setDrugStudyTopic(String val){ mDrugStudyTopic = val; }
    public void setDrugNotes(String val){ mDrugNotes = val; }

    public int getDrugID() { return mDrugID; }
    public String getDrugName() { return mDrugName; }
    public String getDrugBrand() { return mDrugBrand; }
    public String getDrugPurpose() { return mDrugPurpose; }
    public String getDrugDEASchedule() { return mDrugDEASchedule; }
    public String getDrugSpecialConcern() { return mDrugSpecialConcern; }
    public String getDrugCategory() { return mDrugCategory; }
    public String getDrugStudyTopic() { return mDrugStudyTopic; }
    public String getDrugNotes() { return mDrugNotes; }




    // private int mData;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(mDrugName);
        out.writeString(mDrugBrand);
        out.writeString(mDrugCategory);
        out.writeString(mDrugDEASchedule);
        out.writeString(mDrugPurpose);
        out.writeString(mDrugSpecialConcern);
        out.writeString(mDrugStudyTopic);
        out.writeString(mDrugNotes);
        
    }

    public static final Parcelable.Creator<Drug> CREATOR
            = new Parcelable.Creator<Drug>() {
        public Drug createFromParcel(Parcel in) {
            return new Drug(in);
        }

        public Drug[] newArray(int size) {
            return new Drug[size];
        }
    };

    private Drug(Parcel in) {
        mDrugID = in.readInt();
        mDrugName = in.readString();
        mDrugBrand = in.readString();
        mDrugCategory = in.readString();
        mDrugDEASchedule = in.readString();
        mDrugPurpose = in.readString();
        mDrugSpecialConcern = in.readString();
        mDrugStudyTopic = in.readString();
        mDrugNotes = in.readString();
    }
}