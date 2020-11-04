package com.example.mwproject;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

public class getUserInfo extends AppCompatActivity {
    String myJSON;
    JSONArray userDB = null;
    String uid, upw, uNickname;

    ArrayList<HashMap<String, String>> userList;

    public static Context UserInfoContext;

    private static final String TAG_URESULTS = "User_result";
    private static final String TAG_UID = "User_ID";
    private static final String TAG_UPW = "User_PW";
    private static final String TAG_NICKNAME = "User_NICKNAME";
    private static final String TAG_BIRTH = "User_BIRTH";
    private static final String TAG_GENDER = "User_GENDER";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserInfoContext = this;
        String url = "https://mw-zhdtw.run.goorm.io/PHP_login.php";
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
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }

    public void signUp(String getID, String getNickName, String check) {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            userDB = jsonObj.getJSONArray(TAG_URESULTS);

            //사용자db정보 받기
            for (int i = 0; i < userDB.length(); i++) {
                JSONObject c = userDB.getJSONObject(i);
                uid = c.getString(TAG_UID);
                upw = c.getString(TAG_UPW);
                uNickname = c.getString(TAG_NICKNAME);

                if (check.equals("idcheck")) {
                    if (uid.equals(getID)) {
                        System.out.println("아이디 중복이다");
                    }
                }
                else if(check.equals("nickcheck")){
                    if(uNickname.equals(getNickName)){
                        System.out.println("닉 중복.");
                    }
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

