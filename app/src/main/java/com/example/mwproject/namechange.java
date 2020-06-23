package com.example.mwproject;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class namechange extends AppCompatActivity {
    Button btn;
    EditText edt1;
    EditText edt2;
    TextView txt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.namechange);

        btn = findViewById(R.id.n_btnsave);
        edt1 = findViewById(R.id.n_edtlastname);
        edt2 = findViewById(R.id.n_edtfirstname);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = edt1.getText().toString() + edt2.getText().toString();
                txt1.setText(str);
            }
        });
    }
}
