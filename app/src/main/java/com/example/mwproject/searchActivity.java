package com.example.mwproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mwproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class searchActivity extends AppCompatActivity {
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private search_resultActivity search_resultActivity2 = new search_resultActivity();

    ImageButton ibSearch;
    AutoCompleteTextView auto;
    View header;
    MainActivity ma;
    String myJSON;
    JSONArray userDB = null;
    ArrayList<HashMap<String, String>> videoList;
    TextView textView;
    ListView listView;
    String[] videoID;
    ListAdapter adapter;
    ArrayList<TabStorageVO> movieDataList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        ibSearch = findViewById(R.id.ibSearch);

        String items[] = {"연플리", "신예은", "에이틴"};

        auto = findViewById(R.id.auto);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, items);
        auto.setAdapter(adapter);

        listView = (ListView)findViewById(R.id.wrap);
        videoList = new ArrayList<HashMap<String, String>>();

        ibSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), search_resultActivity.class);
                intent.putExtra("videoName",auto.getText().toString());
                startActivity(intent);
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
