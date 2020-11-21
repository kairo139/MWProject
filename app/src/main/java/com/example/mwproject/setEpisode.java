package com.example.mwproject;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

public class setEpisode extends AppCompatActivity {
    ArrayList<HashMap<String, String>> videoList;
    ListView list;
    ListAdapter adapter;
    View header;
    public static Context setEpisode_C;
    String myJSON;
    JSONArray video = null;
    TextView Ep_tvTitle, Ep_tvCharacter, Ep_tvContent;
    TextView Ep_tvep, Ep_tvSubTitle;
    //FragmentRanking fragmentRanking = new FragmentRanking();
    String videoID[] = new String[50];

    private static final String TAG_RESULTS = "result";
    private static final String TAG_WD_SEQ = "WebDrama_SEQ";
    private static final String TAG_WD_TITLE = "WebDrama_title";
    private static final String TAG_WD_CASE = "WebDrama_case"; //출연진
    private static final String TAG_WD_CONTENT = "WebDrama_content"; //소개
    private static final String TAG_WD_EPISODE = "Detail_Episode";
    private static final String TAG_WD_SUBTITLE = "Detail_subTitle";

    //setEpisode
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.episode);
        setEpisode_C = this;
        final Intent intentGet = getIntent();
        String seq = intentGet.getStringExtra("wdSeq");
        header = getLayoutInflater().inflate(R.layout.episode_list_item, null, false);

        Ep_tvep = header.findViewById(R.id.Ep_tvep);
        Ep_tvSubTitle = header.findViewById(R.id.Ep_tvSubTitle);

        list = (ListView) findViewById(R.id.listView2);
        videoList = new ArrayList<HashMap<String, String>>();

        Ep_tvTitle = findViewById(R.id.Ep_tvTitle);
        Ep_tvCharacter = findViewById(R.id.Ep_tvCharacter);
        Ep_tvContent = findViewById(R.id.Ep_tvContent);

        getData2("https://mw-zhdtw.run.goorm.io/PHP_episode.php", seq, "head");
        getData2("https://mw-zhdtw.run.goorm.io/PHP_epList.php", seq, "list");


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(adapterView.getContext(),Player.class);
                intent.putExtra("videoID",videoID[i]);
                startActivity(intent);
            }
        });
        /*list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), setEpisode.class);
                seq = wdSeq[position];
                intent.putExtra("wdSeq",seq);
                startActivity(intent);
            }
        });*/
    }

    protected void showList(String wd_Seq, String param) {
        String selected_WDseq = wd_Seq;
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            video = jsonObj.getJSONArray(TAG_RESULTS);

            if (param.equals("head")) {
                for (int i = 0; i < video.length(); i++) {
                    JSONObject c = video.getJSONObject(i);
                    String wdSeq = c.getString(TAG_WD_SEQ);
                    if (selected_WDseq.equals(wdSeq)) {
                        String wdTitle = c.getString(TAG_WD_TITLE);
                        String wdCase = c.getString(TAG_WD_CASE);
                        String wdContent = c.getString(TAG_WD_CONTENT);

                        Ep_tvTitle.setText(wdTitle);
                        Ep_tvCharacter.setText(wdCase);
                        Ep_tvContent.setText(wdContent);

                    }
                }
                list.setAdapter(adapter);
            } else if (param.equals("list")) {
                for (int i = 0, x = 0; i < video.length(); i++) {
                    JSONObject c = video.getJSONObject(i);
                    String wdSeq = c.getString(TAG_WD_SEQ);
                    if (selected_WDseq.equals(wdSeq)) {
                        String wdEp = c.getString(TAG_WD_EPISODE);
                        String wdSubTitle = c.getString(TAG_WD_SUBTITLE);
                        videoID[x++] = c.getString("Detail_VideoID");

                        HashMap<String, String> videoInfo = new HashMap<String, String>();

                        videoInfo.put(TAG_WD_EPISODE, wdEp);
                        videoInfo.put(TAG_WD_SUBTITLE, wdSubTitle);
                        videoList.add(videoInfo);
                    }
                }
                adapter = new SimpleAdapter(
                        this.getApplicationContext(), videoList, R.layout.episode_list_item,
                        new String[]{TAG_WD_EPISODE, TAG_WD_SUBTITLE},
                        new int[]{R.id.Ep_tvep, R.id.Ep_tvSubTitle}
                );
                list.setAdapter(adapter);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getData2(final String url, final String wdSeq, final String par) {
        class GetDataJSON extends AsyncTask<String, Void, String> {
            String wSeq = wdSeq;
            String param = par;

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
            protected void onPostExecute(String result) {
                myJSON = result;
                showList(wSeq, param);
            }
        }

        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }
}
