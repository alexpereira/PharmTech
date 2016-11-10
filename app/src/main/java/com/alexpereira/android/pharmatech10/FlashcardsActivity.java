package com.alexpereira.android.pharmatech10;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by alexpereira on 10/21/16.
 */

public class FlashcardsActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcards);

        setTitle("Flashcards");
        //setTitle(PharmTech.drugs.get(10).getDrugName());

        //Purpose spinner
        Spinner purpose_spinner = (Spinner) findViewById(R.id.purpose_spinner);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,  android.R.layout.simple_spinner_item, PharmTech.drugPurposes);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        purpose_spinner.setAdapter(adapter1);

        //Category spinner
        Spinner category_spinner = (Spinner) findViewById(R.id.category_spinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.drawer_items, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category_spinner.setAdapter(adapter2);

        //Study topic spinner
        Spinner topic_spinner = (Spinner) findViewById(R.id.study_topic_spinner);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.drawer_items, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        topic_spinner.setAdapter(adapter3);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.close_flashcards:
                startActivity(new Intent(this, MainActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
