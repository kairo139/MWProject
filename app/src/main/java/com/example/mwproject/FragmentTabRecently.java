package com.example.mwproject;

import android.app.ActionBar;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

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

public class FragmentTabRecently extends Fragment{
    View Current_v;
    ImageButton recent_ep2;

    String myJSON;
    JSONArray userDB = null;

    TextView textView;

    ArrayList<TabStorageVO> movieDataList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Current_v = inflater.inflate(R.layout.tab_recently, container, false);
        InitializeMovieData();

        getData("1");

        ListView listView = (ListView)Current_v.findViewById(R.id.wrap);
        final TabStorageAdapter myAdapter = new TabStorageAdapter(Current_v.getContext(),movieDataList);

        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Toast.makeText(Current_v.getContext(),myAdapter.getItem(position).getDramaName(),Toast.LENGTH_LONG).show();
            }
        });
        return Current_v;
    }

    public void InitializeMovieData()
    {
        movieDataList = new ArrayList<TabStorageVO>();
        Log.d("test","dddddd");
        movieDataList.add(new TabStorageVO(R.drawable.ep1, "미션임파서블","15세 이상관람가"));
        movieDataList.add(new TabStorageVO(R.drawable.ep1, "아저씨","19세 이상관람가"));
        movieDataList.add(new TabStorageVO(R.drawable.ep1, "어벤져스","12세 이상관람가"));
        Log.d("test",movieDataList.get(0).getDramaName());
        Log.d("test",movieDataList.get(1).getDramaName());
        Log.d("test",movieDataList.get(2).getDramaName());
    }

    public void getData(final String detail_seq) {

        class GetDataJSON extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String uri = params[0];
                String link = "https:/https://mw-zhdtw.run.goorm.io/selectStorageViewRecord.php?User_SEQ="+ Integer.parseInt(uri);

                BufferedReader bufferedReader = null;

                try {
                    URL url = new URL(link);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return uri;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String result) {
                myJSON = result;
                showList();
            }
        }
        GetDataJSON getDataJSON = new GetDataJSON();
        getDataJSON.execute(detail_seq);
    }

    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            userDB = jsonObj.getJSONArray("User_result");


            //사용자db정보 받기
            for (int i = 0; i < userDB.length(); i++) {
                JSONObject c = userDB.getJSONObject(i);

                textView.setText(c.getString("Detail_subTitle"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
