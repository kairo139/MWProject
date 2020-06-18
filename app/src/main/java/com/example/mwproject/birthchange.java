package com.example.mwproject;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class birthchange extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.birthchange);

        final String[] month = {"1","2","3","4","5","6","7","8","9","10","11","12"};

        Spinner monthspinner = (Spinner)findViewById(R.id.birth_spinmonth);

        ArrayAdapter<String> monthadapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,month);
        monthspinner.setAdapter(monthadapter);
    }
}
