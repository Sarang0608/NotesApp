package com.example.notes;

/****************************************************************************************************************************************************************************************************
                                                                    THIS ACTIVITY HANDLES ALL THE SETTINGS THAT ARE AVAILABLE IN THIS APP
 ***************************************************************************************************************************************************************************************************/

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        int whiteColorValue = Color.WHITE;

        toolbar = findViewById(R.id.settings_toolbar);
        toolbar.setTitleTextColor(whiteColorValue);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Settings");


        Spinner spinner = findViewById(R.id.theme_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Themes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

       switch(adapterView.getSelectedItem().toString()) {
           case "Dark" :
               getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
               break;
       }
       }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}