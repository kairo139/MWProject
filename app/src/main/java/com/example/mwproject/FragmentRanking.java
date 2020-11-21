package com.example.mwproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

public class FragmentRanking extends Fragment {
    //public static Object fragRanking_C;
    TextView tvRank_WDTitle, tvRank_WDCase, tvRank_WDContent;
    ImageView iv_Rank1;
    Spinner rank_spinner;
    View header;
    String myJSON;
    TextView tvNum1;
    String wdSeq[] = new String[24];
    String seq = "";
    public static Context fragRanking_C;
    static FragmentManager fragmentManager;

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

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View Current_v = inflater.inflate(R.layout.ranking, container, false);
        header = getLayoutInflater().inflate(R.layout.ranking_listitem, null, false);
        tvNum1 = (TextView) header.findViewById(R.id.tvNum1);
        iv_Rank1 = header.findViewById(R.id.ivRank);
        tvRank_WDTitle = header.findViewById(R.id.tvRank_WDTitle);
        tvRank_WDCase = header.findViewById(R.id.tvRank_WDCase);
        tvRank_WDContent = header.findViewById(R.id.tvRank_WDContent);

        list = (ListView) Current_v.findViewById(R.id.listView);
        videoList = new ArrayList<HashMap<String, String>>();

        rank_spinner = Current_v.findViewById(R.id.spinner);

        String rank[] = new String[2];
        rank[0] = "추천순";
        rank[1] = "조회수";

        ArrayAdapter spinnerAdpater = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, rank);
        rank_spinner.setAdapter(spinnerAdpater);
        rank_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    getData("https://mw-zhdtw.run.goorm.io/PHP_ranking.php");
                } else {
                    getData("https://mw-zhdtw.run.goorm.io/PHP_rankingLookup.php");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), setEpisode.class);
                seq = wdSeq[position];
                intent.putExtra("wdSeq",seq);
                startActivity(intent);
            }
        });

        return Current_v;
    }


    protected void showList() {
        videoList.clear();
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            video = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < video.length(); i++) {
                JSONObject c = video.getJSONObject(i);
                wdSeq[i] = c.getString(TAG_WD_SEQ);
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
                showList();
                //task.execute(imgUrl + dThumb);
            }
        }

        GetDataJSON g = new GetDataJSON();
        g.execute(url);

    }


    /*private void insertToDatabase(String idx) { ///idx를 넣는 이유는 php파일로 idx값을 보내서, MySQL DB에서 해당하는 idx값 가진 레코드를 가져올거라서

        class InsertData extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ColumnActivity.this, "Please Wait", null, true, true);
            }

            @Override
            protected String doInBackground(String... params) {

                try {
                    String idx = (String) params[0];


                    String link = "여기는 데이터를 받고 보내는 url부분-내가 만든 php파일주소";
                    String data = URLEncoder.encode("idx", "UTF-8") + "=" + URLEncoder.encode(idx, "UTF-8");


                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write(data);
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));


                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    // Read Server Response
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();

                } catch (Exception e) {
                    return new String("Exception: " + e.getMessage());
                }

            }*/
}
