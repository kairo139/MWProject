package com.example.mwproject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.io.BufferedReader;

public class dbtestt extends AppCompatActivity {
    String myJSON;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_VID = "Video_ID";
    private static final String TAG_GENRE = "Genre";
    private static final String TAG_TITLE = "Title";

    JSONArray video = null;

    ArrayList<HashMap<String, String>> videoList;

    ListView list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dbtest);

        list = (ListView) findViewById(R.id.listView);
        videoList = new ArrayList<HashMap<String, String>>();
        getData("http://192.168.35.73/PHP_connection.php");
    }

    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            video = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < video.length(); i++) {
                JSONObject c = video.getJSONObject(i);
                String vid = c.getString(TAG_VID);
                String genre = c.getString(TAG_GENRE);
                String title = c.getString(TAG_TITLE);

                HashMap<String, String> videoInfo = new HashMap<String, String>();

                videoInfo.put(TAG_VID, vid);
                videoInfo.put(TAG_GENRE, genre);
                videoInfo.put(TAG_TITLE, title);

                videoList.add(videoInfo);
            }

            ListAdapter adapter = new SimpleAdapter(
                    dbtestt.this, videoList, R.layout.dbt_listitem,
                    new String[]{TAG_VID, TAG_GENRE, TAG_TITLE},
                    new int[]{R.id.vid, R.id.genre, R.id.title}
            );

            list.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getData(String url) {

        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine()) != null){
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
            protected void onPostExecute(String result){
                myJSON = result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }
}
