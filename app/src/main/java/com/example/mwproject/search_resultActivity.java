package com.example.mwproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class search_resultActivity extends Fragment {

    TextView resu_tvResult, resu_tv1, resu_tv2, resu_tv3, resu_tv4;
    ImageButton resu_ib1, resu_ib2, resu_ib3, resu_ib4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View Current_v = inflater.inflate(R.layout.search_result, container, false);

        resu_tvResult = Current_v.findViewById(R.id.tvResult);
        resu_tv1 = Current_v.findViewById(R.id.tv1);
        resu_tv2 = Current_v.findViewById(R.id.tv2);
        resu_tv3 = Current_v.findViewById(R.id.tv3);
        resu_tv4 = Current_v.findViewById(R.id.tv4);
        resu_ib1 = Current_v.findViewById(R.id.ib1);
        resu_ib2 = Current_v.findViewById(R.id.ib2);
        resu_ib3 = Current_v.findViewById(R.id.ib3);
        resu_ib4 = Current_v.findViewById(R.id.ib4);

        resu_tvResult.setText("연플리");

        resu_ib1.setBackgroundResource(R.drawable.ypl1);
        resu_tv1.setText("연애플레이리스트1\n\n플레이리스트\n\n새학기 시작을 풋풋한 대학 청춘 멜로와 함께");

        resu_ib2.setBackgroundResource(R.drawable.ypl2);
        resu_tv2.setText("연애플레이리스트2\n\n플레이리스트\n\n풋풋한 대학 청춘 멜로 드라마");

        resu_ib3.setBackgroundResource(R.drawable.ypl3);
        resu_tv3.setText("연애플레이리스트3\n\n플레이리스트\n\n캠퍼스 청춘 멜로 드라마");

        resu_ib4.setBackgroundResource(R.drawable.ypl4);
        resu_tv4.setText("연애플레이리스트4\n\n플레이리스트\n\n대학생들의 청춘 공감 멜로 드라마");

        return Current_v;
    }
}
