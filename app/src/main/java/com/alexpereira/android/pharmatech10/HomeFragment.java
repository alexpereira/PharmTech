package com.alexpereira.android.pharmatech10;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by alexpereira on 10/21/16.
 */

public class HomeFragment extends Fragment {
    public HomeFragment() {
    }

    Drug drug = PharmTech.drugs.get(new Random().nextInt(PharmTech.drugs.size()));

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        // -- inflate the layout for this fragment
        View inflatedView = inflater.inflate(R.layout.fragment_home, container, false);

        // Set the Text to try this out
        TextView t = (TextView) inflatedView.findViewById(R.id.drug_name);
        t.setText(drug.getDrugName());

        return inflatedView;
    }
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        // Hides menu from home fragment
        menu.findItem(R.id.close_flashcards).setVisible(false).setEnabled(false);
        return;
    }
}
