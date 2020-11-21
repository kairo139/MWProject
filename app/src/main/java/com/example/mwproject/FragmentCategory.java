package com.example.mwproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class FragmentCategory extends Fragment {
    View Current_v;
    ImageButton ibGenre_school1;
    String myJSON;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_WD_SEQ = "WebDrama_SEQ";
    private static final String TAG_WD_TITLE = "WebDrama_title";
    private static final String TAG_WD_CASE = "WebDrama_case"; //출연진
    private static final String TAG_WD_CONTENT = "WebDrama_content"; //소개
    private static final String TAG_WD_RECOM = "WebDrama_Recom";
    private static final String TAG_WD_LOOKUP = "WebDrama_Lookup";

    JSONArray video = null;
    ArrayList<HashMap<String, String>> videoList;
    ListView list;
    ListAdapter adapter;
    String PhpUrl = "";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Current_v = inflater.inflate(R.layout.genre, container, false);

        TabLayout mTabLayout = Current_v.findViewById(R.id.layout_tab);

        mTabLayout.addTab(mTabLayout.newTab().setText("학교"));
        mTabLayout.addTab(mTabLayout.newTab().setText("일상"));
        mTabLayout.addTab(mTabLayout.newTab().setText("회사"));
        mTabLayout.addTab(mTabLayout.newTab().setText("액션"));
        mTabLayout.addTab(mTabLayout.newTab().setText("코미디"));
        mTabLayout.addTab(mTabLayout.newTab().setText("로맨스"));
        mTabLayout.addTab(mTabLayout.newTab().setText("판타지"));
        mTabLayout.addTab(mTabLayout.newTab().setText("추리"));
        mTabLayout.addTab(mTabLayout.newTab().setText("리얼리티"));
        mTabLayout.addTab(mTabLayout.newTab().setText("웹툰"));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                changeView(pos);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return Current_v;
    }

    public void changeView(int pos){

        switch(pos){
            case 0 :
                //getData(PhpUrl, 해당하는 장르인자);
                break;
            case 1 :
                break;
            case 2 :
                break;
            case 3 :
                break;
            case 4 :
                break;
            case 5 :
                break;
            case 6 :
                break;
            case 7 :
                break;
            case 8 :
                break;
            case 9 :
                break;
        }
    }

    protected void showList(/*,해당하는 장르 인자 받고*/) {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            video = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < video.length(); i++) {
                //장르 관련 데이터를 먼저 받고 비교문
                //if(받은 장르.equals.(해당하는 인자)
                //{나머지 쫘르륵}
                JSONObject c = video.getJSONObject(i);
                String wdTitle = c.getString(TAG_WD_TITLE);
                String wdCase = c.getString(TAG_WD_CASE);
                String wdContent = c.getString(TAG_WD_CONTENT);
                String wdRecom = c.getString(TAG_WD_RECOM);
                String wdLookup = c.getString(TAG_WD_LOOKUP);

                HashMap<String, String> videoInfo = new HashMap<String, String>();

                videoInfo.put(TAG_WD_TITLE, wdTitle);
                videoInfo.put(TAG_WD_CASE, wdCase);
                videoInfo.put(TAG_WD_CONTENT, wdContent);
                videoInfo.put("tvNum1", String.valueOf(i + 1));
                videoList.add(videoInfo);
            }
            //여까지

            //리스트에 띄워서 확인하려는거
            adapter = new SimpleAdapter(
                    getActivity(), videoList, R.layout.ranking_listitem,
                    new String[]{"tvNum1", TAG_WD_TITLE, TAG_WD_CASE, TAG_WD_CONTENT},
                    new int[]{R.id.tvNum1, R.id.tvRank_WDTitle, R.id.tvRank_WDCase, R.id.tvRank_WDContent}
            );
            list.setAdapter(adapter);
            //여까지

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getData(String url/*,해당하는 장르 인자 받고*/) {
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
                showList(/*,해당하는 장르 인자 보내고*/);
                //task.execute(imgUrl + dThumb);
            }
        }

        GetDataJSON g = new GetDataJSON();
        g.execute(url);

    }
}
