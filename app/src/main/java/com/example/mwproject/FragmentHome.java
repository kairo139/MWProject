package com.example.mwproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import me.relex.circleindicator.CircleIndicator;

public class FragmentHome extends Fragment {
    FragmentPagerAdapter adapterViewPager;
    Button btnGoRd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View Current_v = inflater.inflate(R.layout.fragment_home, container, false); //Fragment View가 inflate하기전에 컴포넌트를 호출하기 때문에 NullPointerException 에러가 발생하므로

        //위 방식처럼 하지 않으면 findViewById에서 에러가 남
        btnGoRd = Current_v.findViewById(R.id.btnGoRd);
        btnGoRd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 몰라
            }
        });

        ViewPager vpPager = Current_v.findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter((getFragmentManager()));
        vpPager.setAdapter(adapterViewPager);

        CircleIndicator indicator = Current_v.findViewById(R.id.indicator);
        indicator.setViewPager(vpPager);

        return Current_v;
    }

    //뷰 페이저
    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:

                    return Vp_first.newInstance(0, "Page # 1");
                case 1:
                    return Vp_second.newInstance(1, "Page # 2");
                case 2:
                    return Vp_third.newInstance(2, "Page # 3");
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }


}
