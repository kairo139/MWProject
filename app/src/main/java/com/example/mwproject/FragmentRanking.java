package com.example.mwproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentRanking extends Fragment {
    TextView rank_tvRank1, rank_tvRank2, rank_tvRank3;
    ImageButton rank_ibRank1, rank_ibRank2, rank_ibRank3;
    Spinner rank_spinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View Current_v = inflater.inflate(R.layout.ranking, container, false);


        rank_tvRank1 = Current_v.findViewById(R.id.tvRank1);
        rank_tvRank2 = Current_v.findViewById(R.id.tvRank2);
        rank_tvRank3 = Current_v.findViewById(R.id.tvRank3);
        rank_ibRank1 = Current_v.findViewById(R.id.ibRank1);
        rank_ibRank2 = Current_v.findViewById(R.id.ibRank2);
        rank_ibRank3 = Current_v.findViewById(R.id.ibRank3);
        rank_spinner = Current_v.findViewById(R.id.spinner);

        String rank[] = new String[2];
        rank[0] = "추천순";
        rank[1] = "조회수";

        ArrayAdapter spinnerAdpater = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, rank);
        rank_spinner.setAdapter(spinnerAdpater);

        rank_ibRank1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) MainActivity.mContext).ToEpisodeActivity();
                //Intent intent = new Intent(getActivity(),Player.class);
                //intent.putExtra("videoID","ok9sgJtaIvY");
                //startActivity(intent);
                //영상 실행 화면
            }
        });

        rank_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    rank_ibRank1.setBackgroundResource(R.drawable.ijj1);
                    rank_tvRank1.setText("일진에게 찍혔을 때\n\n콕tv\n\n한장의 사진, 한순간의 실수. 지긋지긋한 스토커를 떼어내려 프사로 설정한 남친짤이 우리 반 일진 사진이라고?");

                    rank_ibRank2.setBackgroundResource(R.drawable.ypl1);
                    rank_tvRank2.setText("연애플레이리스트1\n\n플레이리스트\n\n새학기 시작을 풋풋한 대학 청춘 멜로와 함께");

                    rank_ibRank3.setBackgroundResource(R.drawable.ypl4);
                    rank_tvRank3.setText("연애플레이리스트4\n\n플레이리스트\n\n대학생들의 청춘 공감 멜로 드라마");
                }
                else{
                    rank_ibRank1.setBackgroundResource(R.drawable.ijj1);
                    rank_tvRank1.setText("일진에게 찍혔을 때\n\n콕tv\n\n한장의 사진, 한순간의 실수. 지긋지긋한 스토커를 떼어내려 프사로 설정한 남친짤이 우리 반 일진 사진이라고?");

                    rank_ibRank2.setBackgroundResource(R.drawable.ypl4);
                    rank_tvRank2.setText("연애플레이리스트4\n\n플레이리스트\n\n대학생들의 청춘 공감 멜로 드라마");

                    rank_ibRank3.setBackgroundResource(R.drawable.ypl1);
                    rank_tvRank3.setText("연애플레이리스트1\n\n플레이리스트\n\n새학기 시작을 풋풋한 대학 청춘 멜로와 함께");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return Current_v;
    }
}
