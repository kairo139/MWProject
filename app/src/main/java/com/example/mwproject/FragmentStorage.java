package com.example.mwproject;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;


public class FragmentStorage extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_storage);

        ViewPager vp = findViewById(R.id.viewpager);
        VPAdapter adapter = new VPAdapter(getSupportFragmentManager());

        vp.setAdapter(adapter);

        //뷰 페이저와 탭 연동
        TabLayout tab = findViewById(R.id.tabName);
        tab.setupWithViewPager(vp);
    }
}
