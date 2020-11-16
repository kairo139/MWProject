package com.example.mwproject;

import android.app.ActionBar;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

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

    ConstraintLayout constraintLayout;
    TextView textView;
    ImageButton imageButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Current_v = inflater.inflate(R.layout.tab_recently, container, false);

        constraintLayout = Current_v.findViewById(R.id.wrap);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        getData("1");

        imageButton = new ImageButton(this.getContext());
        imageButton.setBackgroundResource(R.drawable.ep1);
        imageButton.setId(View.generateViewId());
        // 리스트 뷰 사용으로 변경 후 하기
        imageButton.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT,ConstraintLayout.LayoutParams.WRAP_CONTENT));
        constraintSet.connect(imageButton.getId(),ConstraintSet.LEFT,ConstraintSet.PARENT_ID,ConstraintSet.LEFT,0);
        constraintSet.connect(imageButton.getId(),ConstraintSet.TOP,ConstraintSet.PARENT_ID,ConstraintSet.TOP,0);

        textView = new TextView(this.getContext());
        textView.setId(View.generateViewId());
        textView.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT,ConstraintLayout.LayoutParams.WRAP_CONTENT));
        constraintSet.connect(textView.getId(),ConstraintSet.LEFT,imageButton.getId(),ConstraintSet.RIGHT,0);
        constraintSet.connect(textView.getId(),ConstraintSet.TOP,ConstraintSet.PARENT_ID,ConstraintSet.TOP,0);
        constraintSet.applyTo(constraintLayout);

        /*recent_ep2 = Current_v.findViewById(R.id.recent_ep2);
        recent_ep2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Player.class);
                intent.putExtra("playTime",80000);
                intent.putExtra("videoID","ok9sgJtaIvY");
                startActivity(intent);
            }
        });*/
                return Current_v;
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
