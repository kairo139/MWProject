package com.example.mwproject;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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
import java.util.HashMap;

public class FragmentTabRecently extends Fragment{
    View Current_v;

    String myJSON;
    JSONArray userDB = null;
    ArrayList<HashMap<String, String>> videoList;
    ListView listView;
    String[] videoID;
    int[] videoPlaytime;
    ListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Current_v = inflater.inflate(R.layout.tab_recently, container, false);

        Log.d("test",Integer.toString(((MainActivity)MainActivity.mContext).uSEQ));
        listView = Current_v.findViewById(R.id.wraps);
        videoList = new ArrayList<HashMap<String, String>>();
        getData(Integer.toString(((MainActivity)MainActivity.mContext).uSEQ));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), Player.class);
                intent.putExtra("playTime",videoPlaytime[i]);
                intent.putExtra("videoID",videoID[i]);
                startActivity(intent);
            }
        });


        return Current_v;
    }

    public void getData(String detail_seq) {

        class GetDataJSON extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String uri = params[0];
                Log.d("test",uri);
                String link = "https://mw-zhdtw.run.goorm.io/selectStorageViewRecord.php?User_SEQ="+ Integer.parseInt(uri);

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
        Log.d("test",detail_seq);
        getDataJSON.execute(detail_seq);
    }

    protected void showList() {
        videoList.clear();
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            userDB = jsonObj.getJSONArray("User_result");
            Log.d("test",Integer.toString(userDB.length()));
            videoID = new String[userDB.length()];
            videoPlaytime = new int[userDB.length()];
            //사용자db정보 받기
            for (int i = 0; i < userDB.length(); i++) {

                JSONObject c = userDB.getJSONObject(i);
                HashMap<String,String> videoInfo = new HashMap<>();
                String subTitle = " [" + c.getString("Detail_Episode")
                        + "] " + c.getString("Detail_subTitle");
                videoInfo.put("subTitle",subTitle);
                videoInfo.put("title",c.getString("WebDrama_title"));
                videoID[i] = c.getString("Detail_VideoID");
                videoPlaytime[i] = c.getInt("View_TIME");

                videoList.add(videoInfo);
            }
            adapter = new SimpleAdapter(
                    getActivity(), videoList, R.layout.tabstorage_custom,
                    new String[]{"title","subTitle"},
                    new int[]{R.id.TabStorageTitle,R.id.TabStorageSubTitle}
            );

            listView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
