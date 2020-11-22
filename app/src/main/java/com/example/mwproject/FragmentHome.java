package com.example.mwproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import me.relex.circleindicator.CircleIndicator;

public class FragmentHome extends Fragment {
    private recommendationActivity recommendationActivity = new recommendationActivity();
    private FragmentManager fragmentManager;
    FragmentPagerAdapter adapterViewPager;
    Button btnGoRd;
    ImageButton ibSearch;

    View Current_v, header;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Current_v = inflater.inflate(R.layout.fragment_home, container, false); //Fragment View가 inflate하기전에 컴포넌트를 호출하기 때문에 NullPointerException 에러가 발생하므로
        fragmentManager = getActivity().getSupportFragmentManager();
        //위 방식처럼 하지 않으면 findViewById에서 에러가 남
        uSeq = ((MainActivity)MainActivity.mContext).uSEQ;

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        btnGoRd = Current_v.findViewById(R.id.btnGoRd);
        btnGoRd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction().replace(R.id.frameLayout,recommendationActivity).commitAllowingStateLoss();
            }
        });

        ViewPager vpPager = Current_v.findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter((getChildFragmentManager()));
        vpPager.setAdapter(adapterViewPager);

        CircleIndicator indicator = Current_v.findViewById(R.id.indicator);
        indicator.setViewPager(vpPager);

        ///////////////////////////////////
        header = getLayoutInflater().inflate(R.layout.recom_listitem,null,false);
        ivThumb = (ImageView) header.findViewById(R.id.ivThumb);
        task = new inputImage();

        list = (ListView) Current_v.findViewById(R.id.listView);
        list.setVerticalScrollBarEnabled(false);
        videoList = new ArrayList<HashMap<String, String>>();
        if(String.valueOf(uSeq).equals("0")){
            preDefault("https://mw-zhdtw.run.goorm.io/PHP_preDefault.php");
        }
        else
            getData("https://mw-zhdtw.run.goorm.io/PHP_pre.php",String.valueOf(uSeq));

        return Current_v;
    }

    //뷰 페이저
    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return Vp_first.newInstance(0, "Page # 1");
                case 1:
                    return Vp_second.newInstance(1, "Page # 2");
                case 2:
                    return Vp_third.newInstance(2, "Page # 3");
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }

    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            video = jsonObj.getJSONArray(TAG_RESULTS);

            //웹드라마 db받는곳
            for (int i = 0; i <3; i++) {
                JSONObject c = video.getJSONObject(i);
                String dEpi = c.getString(TAG_DEPI);
                String dSub = c.getString(TAG_DSUB);
                dThumb = c.getString(TAG_DTHUMB);

                Log.d("dTumb",dThumb);
                Log.d("task", String.valueOf(task));

                HashMap<String, String> videoInfo = new HashMap<String, String>();

                videoInfo.put(TAG_DEPI, dEpi);
                videoInfo.put(TAG_DSUB, dSub);
                videoInfo.put(TAG_DTHUMB, dThumb);
                videoList.add(videoInfo);

            }
            //여까지


            //리스트에 띄워서 확인하려는거
            ListAdapter adapter = new SimpleAdapter(
                    getActivity(), videoList, R.layout.recom_listitem,
                    new String[]{TAG_DEPI, TAG_DSUB},
                    new int[]{R.id.tv_epi, R.id.tv_subTitle}
            );
            list.setAdapter(adapter);
            //여까지

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getData(String url, final String seq) {
        class GetDataJSON extends AsyncTask<String, Void, String> {
            String uSeq = seq;
            @Override
            protected String doInBackground(String... params) {
                String uri = params[0];
                BufferedReader bufferedReader = null;
                try {
                    String data = URLEncoder.encode("uSeq", "UTF-8") + "=" + URLEncoder.encode(uSeq, "UTF-8");
                    URL url = new URL(uri);
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
                showList();
                task.execute(imgUrl + dThumb);
            }
        }

        GetDataJSON g = new GetDataJSON();
        g.execute(url);

    }

    public void preDefault(String url) {
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
            }
        }

        GetDataJSON g = new GetDataJSON();
        g.execute(url);

    }

    class inputImage extends AsyncTask<String, Integer, Bitmap> {

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

        protected void onPostExecute(Bitmap img) {
            Log.d("bitmapImg", String.valueOf(img));
            ivThumb.setImageBitmap(img);
            ivThumb.invalidate();
        }

    }
}
