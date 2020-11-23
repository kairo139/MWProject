package com.example.mwproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FragmentCategory extends Fragment {
    View Current_v;
    ImageButton ibGenre_school1;
    String myJSON;

    String wdSeq[] = new String[24];
    String seq = "";

    private static final String TAG_RESULTS = "result";
    private static final String TAG_WD_SEQ = "WebDrama_SEQ";
    private static final String TAG_WD_TITLE = "WebDrama_title";
    private static final String TAG_WD_CASE = "WebDrama_case"; //출연진
    private static final String TAG_WD_CONTENT = "WebDrama_content"; //소개
    private static final String TAG_WD_GENERE = "Genere_SEQ";
    private static final String TAG_WD_THUMB = "WebDrama_Thumb";

    JSONArray video = null;
    ArrayList<HashMap<String, String>> videoList;
    ListView list;
    ListViewAdapter3 adapter;
    String PhpUrl = "https://mw-zhdtw.run.goorm.io/PHP_category.php";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Current_v = inflater.inflate(R.layout.genre, container, false);
        list = (ListView) Current_v.findViewById(R.id.listView);
        videoList = new ArrayList<HashMap<String, String>>();
        adapter = new ListViewAdapter3();
        list.setAdapter(adapter);

        getData(PhpUrl, "1");
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

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), setEpisode.class);
                seq = wdSeq[position];
                intent.putExtra("wdSeq", seq);
                startActivity(intent);
            }
        });

        return Current_v;
    }

    public void changeView(int pos) {

        switch (pos) {
            case 0:
                adapter.clearList();
                getData(PhpUrl, "1");
                break;
            case 1:
                adapter.clearList();
                getData(PhpUrl, "2");
                break;
            case 2:
                adapter.clearList();
                getData(PhpUrl, "3");
                break;
            case 3:
                adapter.clearList();
                getData(PhpUrl, "4");
                break;
            case 4:
                adapter.clearList();
                getData(PhpUrl, "5");
                break;
            case 5:
                adapter.clearList();
                getData(PhpUrl, "6");
                break;
            case 6:
                adapter.clearList();
                getData(PhpUrl, "7");
                break;
            case 7:
                adapter.clearList();
                getData(PhpUrl, "8");
                break;
            case 8:
                adapter.clearList();
                getData(PhpUrl, "9");
                break;
            case 9:
                adapter.clearList();
                getData(PhpUrl, "10");
                break;
        }
    }

    protected void showList(String genreParam) {
        String genre = genreParam;
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            video = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < video.length(); i++) {
                JSONObject c = video.getJSONObject(i);
                wdSeq[i] = c.getString(TAG_WD_SEQ);
                String genreSeq = c.getString(TAG_WD_GENERE);
                String wdTitle = c.getString(TAG_WD_TITLE);
                String wdCase = c.getString(TAG_WD_CASE);
                String wdContent = c.getString(TAG_WD_CONTENT);
                String dThumb = c.getString(TAG_WD_THUMB);

                adapter.addItem3(wdTitle,wdCase,wdContent,dThumb);
                adapter.notifyDataSetChanged();

            }
            //여까지

            //리스트에 띄워서 확인하려는거
            /*adapter = new SimpleAdapter(
                    getActivity(), videoList, R.layout.genre_listitem,
                    new String[]{TAG_WD_TITLE, TAG_WD_CASE, TAG_WD_CONTENT},
                    new int[]{R.id.tvGenreTitle, R.id.tvGenreCase, R.id.tvGenreContent}
            );
            list.setAdapter(adapter);*/
            //여까지

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getData(String url, final String genreParam) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                String uri = params[0];
                BufferedReader bufferedReader = null;
                try {
                    String data = URLEncoder.encode("genreParam", "UTF-8") + "=" + URLEncoder.encode(genreParam, "UTF-8");
                    URL url = new URL(uri);
                    URLConnection conn = url.openConnection();
                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write(data);
                    wr.flush();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;


                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                        break;
                    }

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString();

                } catch (Exception e) {
                    return new String("Exception: " + e.getMessage());
                }
            }

            @Override
            protected void onPostExecute(String result) {
                myJSON = result;
                showList(genreParam);
            }
        }

        GetDataJSON g = new GetDataJSON();
        g.execute(url);

    }
}
