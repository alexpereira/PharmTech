package com.alexpereira.android.pharmatech10;


/**
 * Created by alexpereira on 10/22/16.
 */

public class Drug{
    private int mDrugID;
    private String mDrugName;
    private String mDrugBrand;
    private String mDrugPurpose;
    private String mDrugDEASchedule;
    private String mDrugSpecialConcern;
    private String mDrugCategory;
    private String mDrugStudyTopic;

    public Drug() {
    }

    public Drug(Drug drug){

        this.mDrugBrand = drug.getDrugBrand();
        this.mDrugCategory = drug.getDrugCategory();
        this.mDrugDEASchedule = drug.getDrugDEASchedule();
        this.mDrugName = drug.getDrugName();
        this.mDrugPurpose = drug.getDrugPurpose();
        this.mDrugSpecialConcern = drug.getDrugSpecialConcern();
        this.mDrugStudyTopic = drug.getDrugStudyTopic();
    }


    public void setDrugName(String val){ mDrugName = val; }
    public void setDrugBrand(String val){ mDrugBrand = val; }
    public void setDrugPurpose(String val){ mDrugPurpose = val; }
    public void setDrugDEASchedule(String val){ mDrugDEASchedule = val; }
    public void setDrugSpecialConcern(String val){ mDrugSpecialConcern = val; }
    public void setDrugCategory(String val){ mDrugCategory = val; }
    public void setDrugStudyTopic(String val){ mDrugStudyTopic = val; }

    public String getDrugName() { return mDrugName; }
    public String getDrugBrand() { return mDrugBrand; }
    public String getDrugPurpose() { return mDrugPurpose; }
    public String getDrugDEASchedule() { return mDrugDEASchedule; }
    public String getDrugSpecialConcern() { return mDrugSpecialConcern; }
    public String getDrugCategory() { return mDrugCategory; }
    public String getDrugStudyTopic() { return mDrugStudyTopic; }
}