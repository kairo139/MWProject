package com.example.mwproject;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mwproject.R;

public class searchActivity extends AppCompatActivity {
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private search_resultActivity search_resultActivity2 = new search_resultActivity();

    ImageButton ibSearch;
    AutoCompleteTextView auto;
    View header;
    MainActivity ma;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        ibSearch = findViewById(R.id.ibSearch);

        String items[] = {"연플리", "신예은", "에이틴"};

        auto = findViewById(R.id.auto);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, items);
        auto.setAdapter(adapter);


        ibSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            finish();
            //fragmentManager.beginTransaction().replace(R.id.frameLayout, search_resultActivity).commitAllowingStateLoss();
        }
    }
}
