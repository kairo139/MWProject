package com.example.mwproject;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class recommendationActivity extends AppCompatActivity {

    TextView reco_tvRecom1, reco_tvRecom1_1, reco_tvRecom1_2, reco_tvRecom2, reco_tvRecom2_1;
    ImageButton reco_ibRecom1_1, reco_ibRecom1_2, reco_ibRecom2_1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommendation);

        reco_tvRecom1 = findViewById(R.id.tvRecom1);
        reco_tvRecom1_1 = findViewById(R.id.tvRecom1_1);
        reco_tvRecom1_2 = findViewById(R.id.tvRecom1_2);
        reco_tvRecom2 = findViewById(R.id.tvRecom2);
        reco_tvRecom2_1 = findViewById(R.id.tvRecom2_1);
        reco_ibRecom1_1 = findViewById(R.id.ibRecom1_1);
        reco_ibRecom1_2 = findViewById(R.id.ibRecom1_2);
        reco_ibRecom2_1 = findViewById(R.id.ibRecom2_1);

        reco_tvRecom1.setText("로맨스 어떠신가요?");

        reco_ibRecom1_1.setImageResource(R.drawable.ypl2);
        reco_tvRecom1_1.setText("연애플레이리스트2\n\n플레이리스트\n\n풋풋한 대학 청춘 멜로 드라마");

        reco_ibRecom1_2.setImageResource(R.drawable.ypl1);
        reco_tvRecom1_2.setText("연애플레이리스트1\n\n플레이리스트\n\n새학기 시작을 풋풋한 대학 청춘 멜로와 함께");

        reco_tvRecom2.setText("학교 좋아하시나요?");

        reco_ibRecom2_1.setImageResource(R.drawable.ijj1);
        reco_tvRecom2_1.setText("일진에게 찍혔을 때\n\n콕tv\n\n한장의 사진, 한순간의 실수. 지긋지긋한 스토커를 떼어내려 프사로 설정한 남친짤이 우리 반 일진 사진이라고?");
    }
}
