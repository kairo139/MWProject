package com.example.mwproject;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class birthchange extends AppCompatActivity {
    Button g_btnsave;
    EditText birth_edtyear;
    EditText birth_spinnerday;
    Spinner monthspinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.birthchange);

        final String[] month = {"1","2","3","4","5","6","7","8","9","10","11","12"};

        monthspinner = (Spinner)findViewById(R.id.birth_spinmonth);
        g_btnsave = findViewById(R.id.g_btnsave);
        birth_edtyear = findViewById(R.id.birth_edtyear);
        birth_spinnerday = findViewById(R.id.birth_spinday);

        ArrayAdapter<String> monthadapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,month);
        monthspinner.setAdapter(monthadapter);

        g_btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = birth_edtyear.getText().toString() + "." + monthspinner.getSelectedItem().toString() + "." + birth_spinnerday.getText().toString();
            }
        });
    }
}
