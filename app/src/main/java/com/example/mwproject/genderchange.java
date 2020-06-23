package com.example.mwproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class genderchange extends AppCompatActivity {
    TextView p_gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genderchange);

        final RadioGroup rg = (RadioGroup)findViewById(R.id.g_radiogroup);
        Button g_btnsave = findViewById(R.id.g_btnsave);
        p_gender = findViewById(R.id.p_gender);

        g_btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton)findViewById(id);
                p_gender.setText(rb.getText().toString());
            }
        });
    }
}
