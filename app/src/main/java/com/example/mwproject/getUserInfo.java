package com.example.mwproject;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

public class getUserInfo extends Application {
    //public getUserInfo(String url) throws MalformedURLException { this.url = new URL(url); }
    String myJSON;
    JSONArray userDB = null;
    String uid, upw, uNickname;
    String save_id="",save_pw="",save_nick="";

    boolean isIdOverlap = true; //아이디 중복이라는 뜻
    boolean isNickOverlap = true; //닉네임 중복이라는 뜻

    ArrayList<HashMap<String, String>> userList;

    public static Context UserInfoContext;

    private static final String TAG_URESULTS = "User_result";
    private static final String TAG_UID = "User_ID";
    private static final String TAG_UPW = "User_PW";
    private static final String TAG_NICKNAME = "User_NICKNAME";
    private static final String TAG_BIRTH = "User_BIRTH";
    private static final String TAG_GENDER = "User_GENDER";


    public void onCreate(@Nullable Bundle savedInstanceState) {
        UserInfoContext = this;

    }

    public void checkID(String getID, String check) {
        boolean btnCheck = true; //true가 id중복확인
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            userDB = jsonObj.getJSONArray(TAG_URESULTS);

            //사용자db정보 받기
            for (int i = 0; i < userDB.length(); i++) {
                JSONObject c = userDB.getJSONObject(i);
                uid = c.getString(TAG_UID);
                upw = c.getString(TAG_UPW);
                uNickname = c.getString(TAG_NICKNAME);


                Log.d("a:",uid);
                Log.d("a1:",uid);


                //아이디 중복확인
                if (check.equals("idCheck")) {
                    if (getID.equals(uid)) {
                        isIdOverlap = true; //중복
                        break;
                    } else {
                        isIdOverlap = false;
                        save_id = getID;
                    }
                }

                //닉네임 중복확인
                if (check.equals("nickCheck")) {
                    if (getID.equals(uNickname)) {
                        isNickOverlap = true;
                        break;
                    } else {
                        isNickOverlap = false;
                        save_nick = getID;
                    }
                }
            }

            if (check.equals("idCheck")) {
                if (isIdOverlap && btnCheck) {
                    Toast.makeText(getApplicationContext(), "중복된 아이디입니다.", Toast.LENGTH_SHORT).show();
                } else if (!isIdOverlap && btnCheck) {
                    Toast.makeText(getApplicationContext(), "사용할수 있는 아이디입니다.", Toast.LENGTH_SHORT).show();
                }
            } else if (check.equals("nickCheck")) {
                if (isNickOverlap) {
                    Toast.makeText(getApplicationContext(), "중복된 닉네임입니다.", Toast.LENGTH_SHORT).show();
                } else if (!isNickOverlap) {
                    Toast.makeText(getApplicationContext(), "사용할수 있는 닉네임입니다.", Toast.LENGTH_SHORT).show();
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getData(final String param1, final String param2) {
        final String getid = param1;
        final String check = param2;

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
                checkID(getid, check);

            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }

    /*public void insert(View view){
        insertToDatabase(name, address);
    }*/

    public void insertToDatabase(String id, String pw, String nickname, String birth, String gender) {
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
                String id = (String) params[0];
                String pw = (String) params[1];
                String nickname = (String) params[2];
                String birth = (String) params[3];
                String gender = (String) params[4];
                if (save_id.equals(id) && save_nick.equals(nickname)) {
                    try {
                        String link = "https://mw-zhdtw.run.goorm.io/PHP_signUp.php";
                        String data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");
                        data += "&" + URLEncoder.encode("pw", "UTF-8") + "=" + URLEncoder.encode(pw, "UTF-8")
                                + "&" + URLEncoder.encode("nickname", "UTF-8") + "=" + URLEncoder.encode(nickname, "UTF-8")
                                + "&" + URLEncoder.encode("birth", "UTF-8") + "=" + URLEncoder.encode(birth, "UTF-8")
                                + "&" + URLEncoder.encode("gender", "UTF-8") + "=" + URLEncoder.encode(gender, "UTF-8");

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
                return new String("NoException");
            }
        }
        InsertData task = new InsertData();
        task.execute(id, pw, nickname, birth, gender);

    }


}