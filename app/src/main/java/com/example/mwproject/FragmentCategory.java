package com.example.mwproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

public class FragmentCategory extends Fragment {
    View Current_v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Current_v = inflater.inflate(R.layout.genre, container, false);

        TabLayout mTabLayout = Current_v.findViewById(R.id.layout_tab);

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
        TextView tv1 = Current_v.findViewById(R.id.tv1);
        TextView tv2 = Current_v.findViewById(R.id.tv2);
        TextView tv3 = Current_v.findViewById(R.id.tv3);

        switch(pos){
            case 0 :
                tv1.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.INVISIBLE);
                tv3.setVisibility(View.INVISIBLE);
                break;
            case 1 :
                tv1.setVisibility(View.INVISIBLE);
                tv2.setVisibility(View.VISIBLE);
                tv3.setVisibility(View.INVISIBLE);
                break;
            case 2 :
                tv1.setVisibility(View.INVISIBLE);
                tv2.setVisibility(View.INVISIBLE);
                tv3.setVisibility(View.VISIBLE);
                break;
        }
    }

}
