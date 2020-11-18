package com.example.mwproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.io.BufferedReader;

public class dbtestt extends AppCompatActivity {

    View header;
    String myJSON;
    ImageView ivThumb;
    String imgUrl = "https://mw-zhdtw.run.goorm.io/image/";
    Bitmap bmImg;
    inputImage task;
    String dThumb;
    int uSeq;
    private static final String TAG_RESULTS = "result";
    private static final String TAG_DEPI = "Detail_Episode";
    private static final String TAG_DSUB = "Detail_subTitle";
    private static final String TAG_DTHUMB = "Detail_Thumb";
    private static final String TAG_Lookup = "WebDrama_content";
    private static final String TAG_TITLE = "Genere_SEQ";

    JSONArray video = null;

    ArrayList<HashMap<String, String>> videoList;

    ListView list;

    private getPreference getpreference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dbtest);
        header = getLayoutInflater().inflate(R.layout.dbt_listitem,null,false);
        ivThumb = (ImageView) header.findViewById(R.id.ivThumb);
        task = new inputImage();
        uSeq = ((MainActivity)MainActivity.mContext).uSEQ;

        list = (ListView) findViewById(R.id.listView);
        videoList = new ArrayList<HashMap<String, String>>();
        insertToDatabase(String.valueOf(uSeq));
        getData("https://mw-zhdtw.run.goorm.io/PHP_connection.php");

    }

    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            video = jsonObj.getJSONArray(TAG_RESULTS);

            //웹드라마 db받는곳
            for (int i = 0; i < video.length(); i++) {
                JSONObject c = video.getJSONObject(i);
                String dEpi = c.getString(TAG_DEPI);
                String dSub = c.getString(TAG_DSUB);
                dThumb = c.getString(TAG_DTHUMB);


                Log.d("task", String.valueOf(task));

                System.out.println(dSub + "\n");

                HashMap<String, String> videoInfo = new HashMap<String, String>();

                videoInfo.put(TAG_DEPI, dEpi);
                videoInfo.put(TAG_DSUB, dSub);
                //videoInfo.put(TAG_DTHUMB, dThumb);
                System.out.println(videoInfo);
                videoList.add(videoInfo);
            }
            //여까지


            //리스트에 띄워서 확인하려는거
            ListAdapter adapter = new SimpleAdapter(
                    dbtestt.this, videoList, R.layout.dbt_listitem,
                    new String[]{TAG_DEPI, TAG_DSUB},
                    new int[]{R.id.tv_epi, R.id.tv_subTitle}
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
                task.execute(imgUrl + dThumb);
            }
        }

        GetDataJSON g = new GetDataJSON();
        g.execute(url);

    }


    class inputImage extends AsyncTask<String, Integer, Bitmap> {
        URL myFileUrl = null;

        protected Bitmap doInBackground(String... urls) {
            try {
                URL myFileUrl = new URL(urls[0]);
                HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
                conn.setDoInput(true);
                conn.connect();

                InputStream is = conn.getInputStream();
                Log.d("is", String.valueOf(is));
                bmImg = BitmapFactory.decodeStream(is);
                Log.d("bmImg", String.valueOf(bmImg));


            } catch (IOException e) {
                e.printStackTrace();
            }
            return bmImg;
        }

        public InputStream bATIS(byte[] srcBytes){
            return new ByteArrayInputStream(srcBytes);
        }
        protected void onPostExecute(Bitmap img) {
            Log.d("bitmapImg", String.valueOf(img));
            ivThumb.setImageBitmap(img);
            ivThumb.invalidate();
        }
    }

    public void insertToDatabase(String uSeq) {
        class InsertData extends AsyncTask<String, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }

            @Override
            protected String doInBackground(String... params) {
                String uSeq =  params[0];
                try {
                    String link = "https://mw-zhdtw.run.goorm.io/PHP_pre.php";
                    String data = URLEncoder.encode("uSeq", "UTF-8") + "=" + URLEncoder.encode(uSeq, "UTF-8");

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

            }
        }
        InsertData task = new InsertData();
        task.execute(uSeq);
    }
}
