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
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

public class FragmentCategory extends Fragment {
    View Current_v;
    ImageButton ibGenre_school1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Current_v = inflater.inflate(R.layout.genre, container, false);

        TabLayout mTabLayout = Current_v.findViewById(R.id.layout_tab);
        ibGenre_school1 = Current_v.findViewById(R.id.ibGenre_school1);

        ibGenre_school1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Player.class);
                startActivity(intent);
            }
        });
        mTabLayout.addTab(mTabLayout.newTab().setText("학교"));
        mTabLayout.addTab(mTabLayout.newTab().setText("일상"));
        mTabLayout.addTab(mTabLayout.newTab().setText("회사"));
        mTabLayout.addTab(mTabLayout.newTab().setText("액션"));
        mTabLayout.addTab(mTabLayout.newTab().setText("코미디"));
        mTabLayout.addTab(mTabLayout.newTab().setText("로맨스"));
        mTabLayout.addTab(mTabLayout.newTab().setText("판타지"));
        mTabLayout.addTab(mTabLayout.newTab().setText("추리"));
        mTabLayout.addTab(mTabLayout.newTab().setText("리얼리티"));
        mTabLayout.addTab(mTabLayout.newTab().setText("웹툰"));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                changeView(pos);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return Current_v;
    }

    public void changeView(int pos){
        ImageButton ibGenre_school1 = Current_v.findViewById(R.id.ibGenre_school1);
        TextView tvGenre_school1 = Current_v.findViewById(R.id.tvGenre_school1);

        ImageButton ibGenre_daily1 = Current_v.findViewById(R.id.ibGenre_daily1);
        TextView tvGenre_daily1 = Current_v.findViewById(R.id.tvGenre_daily1);

        ImageButton ibGenre_company1 = Current_v.findViewById(R.id.ibGenre_company1);
        TextView tvGebre_company1 = Current_v.findViewById(R.id.tvGenre_company1);

        ImageButton ibGenre_action1 = Current_v.findViewById(R.id.ibGenre_action1);
        TextView tvGenre_action1 = Current_v.findViewById(R.id.tvGenre_action1);

        ImageButton ibGenre_comedy1 = Current_v.findViewById(R.id.ibGenre_comedy1);
        TextView tvGenre_comedy1 = Current_v.findViewById(R.id.tvGenre_comedy1);

        ImageButton ibGenre_romance1 = Current_v.findViewById(R.id.ibGenre_romance1);
        TextView tvGenre_romance1 = Current_v.findViewById(R.id.tvGenre_romance1);

        ImageButton ibGenre_fantasy1 = Current_v.findViewById(R.id.ibGenre_fantasy1);
        TextView tvGenre_fantasy1 = Current_v.findViewById(R.id.tvGenre_fantasy1);

        ImageButton ibGenre_reasoning1 = Current_v.findViewById(R.id.ibGenre_reasoning1);
        TextView tvGenre_reasoning1 = Current_v.findViewById(R.id.tvGenre_reasoning1);

        ImageButton ibGenre_reality1 = Current_v.findViewById(R.id.ibGenre_reality1);
        TextView tvGenre_reality1 = Current_v.findViewById(R.id.tvGenre_reality1);

        ImageButton ibGenre_webtoon1 = Current_v.findViewById(R.id.ibGenre_webtoon1);
        TextView tvGenre_webtoon1 = Current_v.findViewById(R.id.tvGenre_webtoon1);

        switch(pos){
            case 0 :
                ibGenre_school1.setVisibility(View.VISIBLE);
                tvGenre_school1.setVisibility(View.VISIBLE);
                ibGenre_daily1.setVisibility(View.INVISIBLE);
                tvGenre_daily1.setVisibility(View.INVISIBLE);
                ibGenre_company1.setVisibility(View.INVISIBLE);
                tvGebre_company1.setVisibility(View.INVISIBLE);
                ibGenre_action1.setVisibility(View.INVISIBLE);
                tvGenre_action1.setVisibility(View.INVISIBLE);
                ibGenre_comedy1.setVisibility(View.INVISIBLE);
                tvGenre_comedy1.setVisibility(View.INVISIBLE);
                ibGenre_romance1.setVisibility(View.INVISIBLE);
                tvGenre_romance1.setVisibility(View.INVISIBLE);
                ibGenre_fantasy1.setVisibility(View.INVISIBLE);
                tvGenre_fantasy1.setVisibility(View.INVISIBLE);
                ibGenre_reasoning1.setVisibility(View.INVISIBLE);
                tvGenre_reasoning1.setVisibility(View.INVISIBLE);
                ibGenre_reality1.setVisibility(View.INVISIBLE);
                tvGenre_reality1.setVisibility(View.INVISIBLE);
                ibGenre_webtoon1.setVisibility(View.INVISIBLE);
                tvGenre_webtoon1.setVisibility(View.INVISIBLE);
                break;
            case 1 :
                ibGenre_school1.setVisibility(View.INVISIBLE);
                tvGenre_school1.setVisibility(View.INVISIBLE);
                ibGenre_daily1.setVisibility(View.VISIBLE);
                tvGenre_daily1.setVisibility(View.VISIBLE);
                ibGenre_company1.setVisibility(View.INVISIBLE);
                tvGebre_company1.setVisibility(View.INVISIBLE);
                ibGenre_action1.setVisibility(View.INVISIBLE);
                tvGenre_action1.setVisibility(View.INVISIBLE);
                ibGenre_comedy1.setVisibility(View.INVISIBLE);
                tvGenre_comedy1.setVisibility(View.INVISIBLE);
                ibGenre_romance1.setVisibility(View.INVISIBLE);
                tvGenre_romance1.setVisibility(View.INVISIBLE);
                ibGenre_fantasy1.setVisibility(View.INVISIBLE);
                tvGenre_fantasy1.setVisibility(View.INVISIBLE);
                ibGenre_reasoning1.setVisibility(View.INVISIBLE);
                tvGenre_reasoning1.setVisibility(View.INVISIBLE);
                ibGenre_reality1.setVisibility(View.INVISIBLE);
                tvGenre_reality1.setVisibility(View.INVISIBLE);
                ibGenre_webtoon1.setVisibility(View.INVISIBLE);
                tvGenre_webtoon1.setVisibility(View.INVISIBLE);
                break;
            case 2 :
                ibGenre_school1.setVisibility(View.INVISIBLE);
                tvGenre_school1.setVisibility(View.INVISIBLE);
                ibGenre_daily1.setVisibility(View.INVISIBLE);
                tvGenre_daily1.setVisibility(View.INVISIBLE);
                ibGenre_company1.setVisibility(View.VISIBLE);
                tvGebre_company1.setVisibility(View.VISIBLE);
                ibGenre_action1.setVisibility(View.INVISIBLE);
                tvGenre_action1.setVisibility(View.INVISIBLE);
                ibGenre_comedy1.setVisibility(View.INVISIBLE);
                tvGenre_comedy1.setVisibility(View.INVISIBLE);
                ibGenre_romance1.setVisibility(View.INVISIBLE);
                tvGenre_romance1.setVisibility(View.INVISIBLE);
                ibGenre_fantasy1.setVisibility(View.INVISIBLE);
                tvGenre_fantasy1.setVisibility(View.INVISIBLE);
                ibGenre_reasoning1.setVisibility(View.INVISIBLE);
                tvGenre_reasoning1.setVisibility(View.INVISIBLE);
                ibGenre_reality1.setVisibility(View.INVISIBLE);
                tvGenre_reality1.setVisibility(View.INVISIBLE);
                ibGenre_webtoon1.setVisibility(View.INVISIBLE);
                tvGenre_webtoon1.setVisibility(View.INVISIBLE);
                break;
            case 3 :
                ibGenre_school1.setVisibility(View.INVISIBLE);
                tvGenre_school1.setVisibility(View.INVISIBLE);
                ibGenre_daily1.setVisibility(View.INVISIBLE);
                tvGenre_daily1.setVisibility(View.INVISIBLE);
                ibGenre_company1.setVisibility(View.INVISIBLE);
                tvGebre_company1.setVisibility(View.INVISIBLE);
                ibGenre_action1.setVisibility(View.VISIBLE);
                tvGenre_action1.setVisibility(View.VISIBLE);
                ibGenre_comedy1.setVisibility(View.INVISIBLE);
                tvGenre_comedy1.setVisibility(View.INVISIBLE);
                ibGenre_romance1.setVisibility(View.INVISIBLE);
                tvGenre_romance1.setVisibility(View.INVISIBLE);
                ibGenre_fantasy1.setVisibility(View.INVISIBLE);
                tvGenre_fantasy1.setVisibility(View.INVISIBLE);
                ibGenre_reasoning1.setVisibility(View.INVISIBLE);
                tvGenre_reasoning1.setVisibility(View.INVISIBLE);
                ibGenre_reality1.setVisibility(View.INVISIBLE);
                tvGenre_reality1.setVisibility(View.INVISIBLE);
                ibGenre_webtoon1.setVisibility(View.INVISIBLE);
                tvGenre_webtoon1.setVisibility(View.INVISIBLE);
                break;
            case 4 :
                ibGenre_school1.setVisibility(View.INVISIBLE);
                tvGenre_school1.setVisibility(View.INVISIBLE);
                ibGenre_daily1.setVisibility(View.INVISIBLE);
                tvGenre_daily1.setVisibility(View.INVISIBLE);
                ibGenre_company1.setVisibility(View.INVISIBLE);
                tvGebre_company1.setVisibility(View.INVISIBLE);
                ibGenre_action1.setVisibility(View.INVISIBLE);
                tvGenre_action1.setVisibility(View.INVISIBLE);
                ibGenre_comedy1.setVisibility(View.VISIBLE);
                tvGenre_comedy1.setVisibility(View.VISIBLE);
                ibGenre_romance1.setVisibility(View.INVISIBLE);
                tvGenre_romance1.setVisibility(View.INVISIBLE);
                ibGenre_fantasy1.setVisibility(View.INVISIBLE);
                tvGenre_fantasy1.setVisibility(View.INVISIBLE);
                ibGenre_reasoning1.setVisibility(View.INVISIBLE);
                tvGenre_reasoning1.setVisibility(View.INVISIBLE);
                ibGenre_reality1.setVisibility(View.INVISIBLE);
                tvGenre_reality1.setVisibility(View.INVISIBLE);
                ibGenre_webtoon1.setVisibility(View.INVISIBLE);
                tvGenre_webtoon1.setVisibility(View.INVISIBLE);
                break;
            case 5 :
                ibGenre_school1.setVisibility(View.INVISIBLE);
                tvGenre_school1.setVisibility(View.INVISIBLE);
                ibGenre_daily1.setVisibility(View.INVISIBLE);
                tvGenre_daily1.setVisibility(View.INVISIBLE);
                ibGenre_company1.setVisibility(View.INVISIBLE);
                tvGebre_company1.setVisibility(View.INVISIBLE);
                ibGenre_action1.setVisibility(View.INVISIBLE);
                tvGenre_action1.setVisibility(View.INVISIBLE);
                ibGenre_comedy1.setVisibility(View.INVISIBLE);
                tvGenre_comedy1.setVisibility(View.INVISIBLE);
                ibGenre_romance1.setVisibility(View.VISIBLE);
                tvGenre_romance1.setVisibility(View.VISIBLE);
                ibGenre_fantasy1.setVisibility(View.INVISIBLE);
                tvGenre_fantasy1.setVisibility(View.INVISIBLE);
                ibGenre_reasoning1.setVisibility(View.INVISIBLE);
                tvGenre_reasoning1.setVisibility(View.INVISIBLE);
                ibGenre_reality1.setVisibility(View.INVISIBLE);
                tvGenre_reality1.setVisibility(View.INVISIBLE);
                ibGenre_webtoon1.setVisibility(View.INVISIBLE);
                tvGenre_webtoon1.setVisibility(View.INVISIBLE);
                break;
            case 6 :
                ibGenre_school1.setVisibility(View.INVISIBLE);
                tvGenre_school1.setVisibility(View.INVISIBLE);
                ibGenre_daily1.setVisibility(View.INVISIBLE);
                tvGenre_daily1.setVisibility(View.INVISIBLE);
                ibGenre_company1.setVisibility(View.INVISIBLE);
                tvGebre_company1.setVisibility(View.INVISIBLE);
                ibGenre_action1.setVisibility(View.INVISIBLE);
                tvGenre_action1.setVisibility(View.INVISIBLE);
                ibGenre_comedy1.setVisibility(View.INVISIBLE);
                tvGenre_comedy1.setVisibility(View.INVISIBLE);
                ibGenre_romance1.setVisibility(View.INVISIBLE);
                tvGenre_romance1.setVisibility(View.INVISIBLE);
                ibGenre_fantasy1.setVisibility(View.VISIBLE);
                tvGenre_fantasy1.setVisibility(View.VISIBLE);
                ibGenre_reasoning1.setVisibility(View.INVISIBLE);
                tvGenre_reasoning1.setVisibility(View.INVISIBLE);
                ibGenre_reality1.setVisibility(View.INVISIBLE);
                tvGenre_reality1.setVisibility(View.INVISIBLE);
                ibGenre_webtoon1.setVisibility(View.INVISIBLE);
                tvGenre_webtoon1.setVisibility(View.INVISIBLE);
                break;
            case 7 :
                ibGenre_school1.setVisibility(View.INVISIBLE);
                tvGenre_school1.setVisibility(View.INVISIBLE);
                ibGenre_daily1.setVisibility(View.INVISIBLE);
                tvGenre_daily1.setVisibility(View.INVISIBLE);
                ibGenre_company1.setVisibility(View.INVISIBLE);
                tvGebre_company1.setVisibility(View.INVISIBLE);
                ibGenre_action1.setVisibility(View.INVISIBLE);
                tvGenre_action1.setVisibility(View.INVISIBLE);
                ibGenre_comedy1.setVisibility(View.INVISIBLE);
                tvGenre_comedy1.setVisibility(View.INVISIBLE);
                ibGenre_romance1.setVisibility(View.INVISIBLE);
                tvGenre_romance1.setVisibility(View.INVISIBLE);
                ibGenre_fantasy1.setVisibility(View.INVISIBLE);
                tvGenre_fantasy1.setVisibility(View.INVISIBLE);
                ibGenre_reasoning1.setVisibility(View.VISIBLE);
                tvGenre_reasoning1.setVisibility(View.VISIBLE);
                ibGenre_reality1.setVisibility(View.INVISIBLE);
                tvGenre_reality1.setVisibility(View.INVISIBLE);
                ibGenre_webtoon1.setVisibility(View.INVISIBLE);
                tvGenre_webtoon1.setVisibility(View.INVISIBLE);
                break;
            case 8 :
                ibGenre_school1.setVisibility(View.INVISIBLE);
                tvGenre_school1.setVisibility(View.INVISIBLE);
                ibGenre_daily1.setVisibility(View.INVISIBLE);
                tvGenre_daily1.setVisibility(View.INVISIBLE);
                ibGenre_company1.setVisibility(View.INVISIBLE);
                tvGebre_company1.setVisibility(View.INVISIBLE);
                ibGenre_action1.setVisibility(View.INVISIBLE);
                tvGenre_action1.setVisibility(View.INVISIBLE);
                ibGenre_comedy1.setVisibility(View.INVISIBLE);
                tvGenre_comedy1.setVisibility(View.INVISIBLE);
                ibGenre_romance1.setVisibility(View.INVISIBLE);
                tvGenre_romance1.setVisibility(View.INVISIBLE);
                ibGenre_fantasy1.setVisibility(View.INVISIBLE);
                tvGenre_fantasy1.setVisibility(View.INVISIBLE);
                ibGenre_reasoning1.setVisibility(View.INVISIBLE);
                tvGenre_reasoning1.setVisibility(View.INVISIBLE);
                ibGenre_reality1.setVisibility(View.VISIBLE);
                tvGenre_reality1.setVisibility(View.VISIBLE);
                ibGenre_webtoon1.setVisibility(View.INVISIBLE);
                tvGenre_webtoon1.setVisibility(View.INVISIBLE);
                break;
            case 9 :
                ibGenre_school1.setVisibility(View.INVISIBLE);
                tvGenre_school1.setVisibility(View.INVISIBLE);
                ibGenre_daily1.setVisibility(View.INVISIBLE);
                tvGenre_daily1.setVisibility(View.INVISIBLE);
                ibGenre_company1.setVisibility(View.INVISIBLE);
                tvGebre_company1.setVisibility(View.INVISIBLE);
                ibGenre_action1.setVisibility(View.INVISIBLE);
                tvGenre_action1.setVisibility(View.INVISIBLE);
                ibGenre_comedy1.setVisibility(View.INVISIBLE);
                tvGenre_comedy1.setVisibility(View.INVISIBLE);
                ibGenre_romance1.setVisibility(View.INVISIBLE);
                tvGenre_romance1.setVisibility(View.INVISIBLE);
                ibGenre_fantasy1.setVisibility(View.INVISIBLE);
                tvGenre_fantasy1.setVisibility(View.INVISIBLE);
                ibGenre_reasoning1.setVisibility(View.INVISIBLE);
                tvGenre_reasoning1.setVisibility(View.INVISIBLE);
                ibGenre_reality1.setVisibility(View.INVISIBLE);
                tvGenre_reality1.setVisibility(View.INVISIBLE);
                ibGenre_webtoon1.setVisibility(View.VISIBLE);
                tvGenre_webtoon1.setVisibility(View.VISIBLE);
                break;
        }
    }

}
