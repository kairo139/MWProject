package com.example.mwproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class FragmentStorage extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View Current_v = inflater.inflate(R.layout.fragment_storage, container, false);

        ViewPager vp = Current_v.findViewById(R.id.viewpager);
        VPAdapter adapter = new VPAdapter(getFragmentManager());
        vp.setAdapter(adapter);

        //뷰 페이저와 탭 연동
        TabLayout tab = Current_v.findViewById(R.id.tabName);
        tab.setupWithViewPager(vp);

        return Current_v;
    }
}
