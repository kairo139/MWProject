package com.example.mwproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class membership extends AppCompatActivity {

    //Integer[] membershipimgID = {R.drawable.membership_school_icon,R.drawable.membership_company_img,R.drawable.membership_action_img,R.drawable.membership_comedy_img,R.drawable.membership_daily_img,R.drawable.membership_romance_img,R.drawable.membership_fantasy_img,R.drawable.membership_inference_img,R.drawable.membership_reality_img,R.drawable.membership_webtoon_img};
    Button g_btnsave;

    Spinner monthspinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.membership);

        final String[] month = {"1","2","3","4","5","6","7","8","9","10","11","12"};

        monthspinner = (Spinner)findViewById(R.id.membership_spinmonth);
        g_btnsave = findViewById(R.id.m_btnok);


        ArrayAdapter<String> monthadapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,month);
        monthspinner.setAdapter(monthadapter);

        g_btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
