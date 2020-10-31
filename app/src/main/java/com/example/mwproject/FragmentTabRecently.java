package com.example.mwproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

public class FragmentTabRecently extends Fragment{
    View Current_v;
    ImageButton recent_ep2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Current_v = inflater.inflate(R.layout.tab_recently, container, false);

        recent_ep2 = Current_v.findViewById(R.id.recent_ep2);
        recent_ep2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Player.class);
                intent.putExtra("playTime",80000);
                startActivity(intent);
            }
        });
                return Current_v;
    }
}
