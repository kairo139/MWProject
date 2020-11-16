package com.example.mwproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    RadioGroup rg_gender;
    ImageButton[] imgBtn_Pre;
    Context C_memberShip;
    String[] isImgBtnChk = new String[10];

    private getUserInfo getuserinfo;

    public static membership mmembership;

    Spinner monthspinner, yearSpinner, daySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.membership);
        mmembership = this;
        getuserinfo = (getUserInfo) getApplicationContext();

        C_memberShip = this;

        edt_ID = findViewById(R.id.m_edtid);
        edt_NickName = findViewById(R.id.m_edtname);
        edt_PW = findViewById(R.id.m_edtpasswd);
        edt_PWCheck = findViewById(R.id.m_edtpasswdchk);

        btn_IDCheck = findViewById(R.id.m_btnid);
        btn_NickCheck = findViewById(R.id.m_btnname);
        rb_male = findViewById(R.id.g_radioMale);
        rb_female = findViewById(R.id.g_radioFemale);
        rb_no = findViewById(R.id.g_radioNo);
        rg_gender = findViewById(R.id.g_radiogroup);

        imgBtn_Pre = new ImageButton[10];
        Integer[] btnId = {R.id.imgBtn_school,R.id.imgBtn_company,R.id.imgBtn_action,
                            R.id.imgBtn_comedy,R.id.imgBtn_daily,R.id.imgBtn_romance,
                            R.id.imgBtn_fantasy,R.id.imgBtn_inference,R.id.imgBtn_reality,
                            R.id.imgBtn_webtoon};

        for(int i = 0; i<imgBtn_Pre.length; i++){
            imgBtn_Pre[i] = findViewById(btnId[i]);
        }

        //선호 버튼
        for(int i = 0; i<imgBtn_Pre.length; i++){
            final int INDEX;
            INDEX = i;
            imgBtn_Pre[INDEX].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!imgBtn_Pre[INDEX].isSelected()) {
                            imgBtn_Pre[INDEX].setBackgroundColor(Color.parseColor("#6c9bd4"));
                            imgBtn_Pre[INDEX].setSelected(true);
                        }
                    else{
                        imgBtn_Pre[INDEX].setBackgroundColor(Color.parseColor("#00ff0000"));
                        imgBtn_Pre[INDEX].setSelected(false);
                    }
                }
            });
        }

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
        yearSpinner = (Spinner) findViewById(R.id.membership_spinyear);
        ArrayAdapter<String> yearadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, year);
        yearSpinner.setAdapter(yearadapter);

        monthspinner = (Spinner) findViewById(R.id.membership_spinmonth);
        ArrayAdapter<String> monthadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, month);
        monthspinner.setAdapter(monthadapter);

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

        //성별 확인
        rg_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.g_radioFemale)
                    uGender = "F";
                else if(checkedId == R.id.g_radioMale)
                    uGender ="M";
                else
                    uGender = "";
            }
        });

        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                uYear = year[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                uYear = year[0];
            }
        });

        monthspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                uMonth = month[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                uMonth = month[0];
            }
        });

        daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                uDay = day[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                uDay = day[0];
            }
        });

        g_btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pw = edt_PW.getText().toString();
                chkPW = edt_PWCheck.getText().toString();

                for(int i =0; i<imgBtn_Pre.length; i++){
                    if(imgBtn_Pre[i].isSelected()){
                        isImgBtnChk[i]="1";
                    } else isImgBtnChk[i]="0";
                    Log.d("isselect", String.valueOf(isImgBtnChk[i]));
                }

                if (pw.equals(chkPW)&&!(getuserinfo.isNickOverlap)&&!(getuserinfo.isIdOverlap)) {
                    getuserinfo.insertToDatabase(id,pw,nickname,uYear,uMonth,uDay,uGender,isImgBtnChk);
                    finish();
                }
                else if(getuserinfo.isIdOverlap){Toast.makeText(getApplicationContext(), "아이디 중복확인을 완료해주세요.", Toast.LENGTH_SHORT).show();}
                else if(getuserinfo.isNickOverlap){Toast.makeText(getApplicationContext(), "닉네임 중복확인을 완료해주세요.", Toast.LENGTH_SHORT).show();}
                else Toast.makeText(getApplicationContext(), "비밀번호를 다시 확인해주세요.", Toast.LENGTH_SHORT).show();

            }
        });

        m_btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}