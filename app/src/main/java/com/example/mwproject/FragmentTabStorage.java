package com.example.mwproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.squareup.picasso.Picasso;

public class FragmentTabStorage extends Fragment {

    ImageButton iBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View Current_v = inflater.inflate(R.layout.tab_storage, container, false);
        iBtn = Current_v.findViewById(R.id.ibtnYpl);
        String imageUrl = "https://mw-zhdtw.run.goorm.io/image/ep1.jpg";

        Picasso.get().load(imageUrl).resize(iBtn.getBackground().getIntrinsicHeight(),iBtn.getBackground().getIntrinsicWidth()).into(iBtn);
        return Current_v;
    }
}
