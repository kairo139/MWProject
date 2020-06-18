package com.example.mwproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileChange extends AppCompatActivity {

    ImageButton prof_namebtn,prof_birthbtn,prof_genderbtn,prof_passwdbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilechange);

        prof_namebtn = findViewById(R.id.p_namebtn);
        prof_birthbtn = findViewById(R.id.p_birthbtn);
        prof_genderbtn = findViewById(R.id.p_genderbtn);
        prof_passwdbtn = findViewById(R.id.p_passwdbtn);

        prof_namebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),namechange.class);
                startActivity(intent);
                //이름수정
            }
        });

        prof_birthbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),birthchange.class);
                startActivity(intent);
                //생일 수정
            }
        });

        prof_genderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),genderchange.class);
                startActivity(intent);
                //성별 수정
            }
        });

        prof_passwdbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),passwdchange.class);
                startActivity(intent);
                //패스워드 수정
            }
        });
    }
}
