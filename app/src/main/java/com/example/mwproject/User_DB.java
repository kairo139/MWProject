package com.example.mwproject;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

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

public class User_DB extends AppCompatActivity {
    String myJSON;
    public static Context userdbcontext;
    public static String userid, password;
    private static final String TAG_UserResult = "result";
    private static final String TAG_UID = "User_ID";
    private static final String TAG_PW = "PW";
    private static final String TAG_NICKNAME = "NickName";
    private static final String TAG_BIRTH = "Birth";
    private static final String TAG_GENDER = "Gender";
    private static final String TAG_HINT = "Hint";

    JSONArray user = null;
    ArrayList<HashMap<String, String>> userInfoList;
    ListView list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = (ListView) findViewById(R.id.listView);
        userInfoList = new ArrayList<HashMap<String, String>>();
        getData("http://192.168.35.73/PHP_connection.php");
        userdbcontext = this;

    }

    protected void showList() {
        try {
            String inputID = ((MainActivity)MainActivity.mContext).inputUID;
            String inputPW = ((MainActivity)MainActivity.mContext).inputPW;


            JSONObject jsonObj = new JSONObject(myJSON);
            user = jsonObj.getJSONArray(TAG_UserResult);

            //유저정보 db받는곳
            for (int i = 0; i < user.length(); i++) {
                JSONObject c = user.getJSONObject(i);
                String uid = c.getString(TAG_UID);
                String pw = c.getString(TAG_PW);
                String nickname = c.getString(TAG_NICKNAME);
                String birth = c.getString(TAG_BIRTH);
                String gender = c.getString(TAG_GENDER);
                String hint = c.getString(TAG_HINT);

                HashMap<String, String> userInfo = new HashMap<String, String>();

                userInfo.put(TAG_UID, uid);
                userInfo.put(TAG_PW, pw);

                if(inputID.equals(uid) && inputPW.equals(pw)){
                    userid = uid; password = pw;
                }
                userInfoList.add(userInfo);
            }
            //여까지

            /*//리스트에 띄워서 확인하려는거
            ListAdapter adapter = new SimpleAdapter(
                    dbtestt.this, videoList, R.layout.dbt_listitem,
                    new String[]{TAG_VID, TAG_GENRE, TAG_TITLE},
                    new int[]{R.id.vid, R.id.genre, R.id.title}
            );
            list.setAdapter(adapter);
            //여까지*/

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
            }
        }

        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }

}
