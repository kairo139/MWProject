package com.example.mwproject;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class getPreference extends Application {
    public static Context getPreference_C;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        getPreference_C = this;
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
