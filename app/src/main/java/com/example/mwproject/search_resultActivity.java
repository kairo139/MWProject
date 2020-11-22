package com.example.mwproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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

public class search_resultActivity extends AppCompatActivity {

    String myJSON;
    JSONArray userDB = null;
    ArrayList<HashMap<String, String>> videoList;
    TextView textView;
    ListView listView;
    String[] videoID;
    ListAdapter adapter;
    ArrayList<TabStorageVO> movieDataList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result);
        listView = (ListView)findViewById(R.id.wrap);
        videoList = new ArrayList<HashMap<String, String>>();
        Intent intent =getIntent();
        getData(intent.getExtras().getString("videoName"));

    }

    public void getData(String videoName) {

        class GetDataJSON extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String uri = params[0];
                Log.d("test",uri);
                String link = "https://mw-zhdtw.run.goorm.io/PHP_searchWebDrama.php?VideoName="+ uri;

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
        Log.d("test",videoName);
        getDataJSON.execute(videoName);
    }

    protected void showList() {
        videoList.clear();
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            userDB = jsonObj.getJSONArray("User_result");
            Log.d("test",Integer.toString(userDB.length()));

            //사용자db정보 받기
            for (int i = 0; i < userDB.length(); i++) {
                videoID = new String[userDB.length()];

                JSONObject c = userDB.getJSONObject(i);
                HashMap<String,String> videoInfo = new HashMap<>();
                videoInfo.put("title",c.getString("WebDrama_title"));
                videoInfo.put("case",c.getString("WebDrama_case"));
                videoInfo.put("content",c.getString("WebDrama_Content"));
                videoID[i] = c.getString("WebDrama_SEQ");
                videoList.add(videoInfo);
            }
            adapter = new SimpleAdapter(
                    getApplicationContext(), videoList, R.layout.tabstorage_custom,
                    new String[]{"title","case","content"},
                    new int[]{R.id.tvRank_WDTitle,R.id.tvRank_WDCase,R.id.tvRank_WDContent}
            );
            listView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
