package com.example.mwproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class ProfileChange extends AppCompatActivity {

    ImageButton prof_namebtn,prof_birthbtn,prof_genderbtn,prof_passwdbtn;
    PieChart pieChart;
    int[] colorArray = new int[]{Color.GRAY,Color.BLUE,Color.GREEN,Color.WHITE,Color.MAGENTA,Color.YELLOW,Color.RED,Color.CYAN,Color.LTGRAY};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilechange);

        prof_namebtn = findViewById(R.id.p_namebtn);
        prof_birthbtn = findViewById(R.id.p_birthbtn);
        prof_genderbtn = findViewById(R.id.p_genderbtn);
        prof_passwdbtn = findViewById(R.id.p_passwdbtn);
        pieChart = findViewById(R.id.p_piechart);

        PieDataSet pieDataSet = new PieDataSet(data1(),"");
        pieDataSet.setColors(colorArray);
        PieData pieData = new PieData(pieDataSet);
        pieChart.setDrawEntryLabels(false);
        pieChart.setUsePercentValues(true);
        pieData.setValueTextSize(20);
        pieChart.setHoleRadius(30);
        pieChart.setData(pieData);
        pieChart.invalidate();

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

    private ArrayList<PieEntry> data1() {
        ArrayList<PieEntry> datavalue = new ArrayList<>();

        datavalue.add(new PieEntry(30,"학교"));
        datavalue.add(new PieEntry(20,"회사"));
        datavalue.add(new PieEntry(10,"액션"));
        datavalue.add(new PieEntry(10,"코미디"));
        datavalue.add(new PieEntry(5,"일상"));
        datavalue.add(new PieEntry(5,"로맨스"));
        datavalue.add(new PieEntry(5,"판타지"));
        datavalue.add(new PieEntry(5,"추리"));
        datavalue.add(new PieEntry(5,"리얼리티"));
        datavalue.add(new PieEntry(5,"웹툰원작"));

        return datavalue;
    }
}
