package com.example.mwproject;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class search_resultActivity extends AppCompatActivity {

    TextView resu_tvResult, resu_tv1, resu_tv2, resu_tv3, resu_tv4;
    ImageButton resu_ib1, resu_ib2, resu_ib3, resu_ib4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result);

        resu_tvResult = findViewById(R.id.tvResult);
        resu_tv1 = findViewById(R.id.tv1);
        resu_tv2 = findViewById(R.id.tv2);
        resu_tv3 = findViewById(R.id.tv3);
        resu_tv4 = findViewById(R.id.tv4);
        resu_ib1 = findViewById(R.id.ib1);
        resu_ib2 = findViewById(R.id.ib2);
        resu_ib3 = findViewById(R.id.ib3);
        resu_ib4 = findViewById(R.id.ib4);

        resu_tvResult.setText("연플리");

        resu_ib1.setImageResource(R.drawable.ypl1);
        resu_tv1.setText("연애플레이리스트1\n\n플레이리스트\n\n새학기 시작을 풋풋한 대학 청춘 멜로와 함께");

        resu_ib2.setImageResource(R.drawable.ypl2);
        resu_tv2.setText("연애플레이리스트2\n\n플레이리스트\n\n풋풋한 대학 청춘 멜로 드라마");

        resu_ib3.setImageResource(R.drawable.ypl3);
        resu_tv3.setText("연애플레이리스트3\n\n플레이리스트\n\n캠퍼스 청춘 멜로 드라마");

        resu_ib4.setImageResource(R.drawable.ypl4);
        resu_tv4.setText("연애플레이리스트4\n\n플레이리스트\n\n대학생들의 청춘 공감 멜로 드라마");

    }
}
