package com.alexpereira.android.pharmatech10;

import android.app.Application;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by alexpereira on 10/22/16.
 */

public class PharmTech extends Application {

    public static ArrayList<Drug> drugs = null;
    public static ArrayList<String> drugPurposes = new ArrayList();


    @Override
    public void onCreate() {
        super.onCreate();
        parseDrugs();
        for (Drug drug : drugs) {
            drugPurposes.add(drug.getDrugPurpose());
        }




        List<String> al = drugPurposes;
        Set<String> hs = new LinkedHashSet<>(al);
        drugPurposes.clear();
        drugPurposes.addAll(hs);


    }
    public void parseDrugs() {
        XmlPullParserFactory pullParserFactory;
        try {
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();


            InputStream in_s = getApplicationContext().getAssets().open("drugs.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in_s, null);

            parseXML(parser);

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseXML(XmlPullParser parser) throws XmlPullParserException,IOException
    {

        int eventType = parser.getEventType();
        Drug currentDrug = null;

        while (eventType != XmlPullParser.END_DOCUMENT){
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                    drugs = new ArrayList();
                    break;
                case XmlPullParser.START_TAG:
                    if (parser.getName().equals("drug")){
                        currentDrug = new Drug();
                        Log.i("yoooo", parser.getAttributeValue(null,"Generic_Name"));
                        currentDrug.setDrugName(parser.getAttributeValue(null,"Generic_Name"));
                        currentDrug.setDrugBrand(parser.getAttributeValue(null,"Brand_Name"));
                        currentDrug.setDrugPurpose(parser.getAttributeValue(null,"Purpose"));
                        currentDrug.setDrugDEASchedule(parser.getAttributeValue(null,"DEA_Schedule"));
                        currentDrug.setDrugSpecialConcern(parser.getAttributeValue(null,"Special"));
                        currentDrug.setDrugCategory(parser.getAttributeValue(null,"Category"));
                        currentDrug.setDrugStudyTopic(parser.getAttributeValue(null,"Study_Topic"));
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if (parser.getName().equalsIgnoreCase("drug") && currentDrug != null)
                        drugs.add(currentDrug);
                    break;
            }
            eventType = parser.next();
        }
    }
}
