package com.example.mwproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.net.MalformedURLException;

public class membership extends AppCompatActivity {

    //Integer[] membershipimgID = {R.drawable.membership_school_icon,R.drawable.membership_company_img,R.drawable.membership_action_img,R.drawable.membership_comedy_img,R.drawable.membership_daily_img,R.drawable.membership_romance_img,R.drawable.membership_fantasy_img,R.drawable.membership_inference_img,R.drawable.membership_reality_img,R.drawable.membership_webtoon_img};
    Button btn_IDCheck, btn_NickCheck, g_btnsave, m_btnback;
    EditText edt_ID, edt_NickName, edt_PW, edt_PWCheck;
    String id, pw, nickname, chkPW;
    String uYear, uMonth, uDay, uBirth;
    String uGender = "";
    RadioButton rb_male, rb_female, rb_no;

    private getUserInfo getuserinfo;

    Spinner monthspinner, yearSpinner, daySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.membership);
        getuserinfo = (getUserInfo) getApplicationContext();

        edt_ID = findViewById(R.id.m_edtid);
        edt_NickName = findViewById(R.id.m_edtname);
        edt_PW = findViewById(R.id.m_edtpasswd);
        edt_PWCheck = findViewById(R.id.m_edtpasswdchk);

        btn_IDCheck = findViewById(R.id.m_btnid);
        btn_NickCheck = findViewById(R.id.m_btnname);
        rb_male = findViewById(R.id.g_radioMale);
        rb_female = findViewById(R.id.g_radioFemale);
        rb_no = findViewById(R.id.g_radioNo);


        final String[] month = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        final String[] year = new String[120];
        final String[] day = new String[31];

        for (int i = 0; i < 31; i++) {
            int j = i + 1;
            day[i] = String.valueOf(j);
        }

        for (int i = 0; i < 120; i++) {
            int j = i + 1900;
            year[i] = String.valueOf(j);
        }

        monthspinner = (Spinner) findViewById(R.id.membership_spinmonth);
        ArrayAdapter<String> monthadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, month);
        monthspinner.setAdapter(monthadapter);

        yearSpinner = (Spinner) findViewById(R.id.membership_spinyear);
        ArrayAdapter<String> yearadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, year);
        yearSpinner.setAdapter(yearadapter);

        daySpinner = (Spinner) findViewById(R.id.membership_spinday);
        ArrayAdapter<String> dayadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, day);
        daySpinner.setAdapter(dayadapter);


        g_btnsave = findViewById(R.id.m_btnok);
        m_btnback = findViewById(R.id.m_btnback);


        //ID중복확인
        btn_IDCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = edt_ID.getText().toString();
                if (id.equals("")) {
                    Toast.makeText(getApplicationContext(), "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else
                    getuserinfo.getData(id, "idCheck");
            }
        });

        //닉네임 중복확인
        btn_NickCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nickname = edt_NickName.getText().toString();
                if (nickname.equals("")) {
                    Toast.makeText(getApplicationContext(), "닉네임을 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else
                    getuserinfo.getData(nickname, "nickCheck");
            }
        });


        g_btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pw = edt_PW.getText().toString();
                chkPW = edt_PWCheck.getText().toString();
                if (pw.equals(chkPW)) {
                    uYear = yearSpinner.getSelectedItem().toString();
                    uMonth = monthspinner.getSelectedItem().toString();
                    uDay = monthspinner.getSelectedItem().toString();
                    uBirth = uYear + uMonth + uDay;

                    if (rb_male.isSelected())
                        uGender = "M";
                    else if (rb_female.isSelected())
                        uGender = "F";
                    else uGender = "";
                    getuserinfo.insertToDatabase(id,pw,nickname,uBirth,uGender);
                }
                else Toast.makeText(getApplicationContext(), "비밀번호를 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //startActivity(intent);
            }
        });

        m_btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
