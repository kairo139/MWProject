package com.example.mwproject;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.PrecomputedText;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.PrecomputedTextCompat;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Player extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    String vidieoID;// 데이터 베이스 완벽히 연결 후 실행 하려는 유튜브 영상 아이디 가져오기
    YouTubePlayer players;
    TextView player_liketxt,player_hits;
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player);
        Intent intent = getIntent();
        vidieoID = intent.getExtras().getString("videoID","");

        player_hits = findViewById(R.id.player_hits);
        player_liketxt = findViewById(R.id.player_liketxt);

        getYouTubePlayerProvider().initialize(DeveloperKey.DEVELOPER_KEY,this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored){
        players = player;

        if(getIntent().hasExtra("playTime")){
            player.cueVideo(vidieoID,getIntent().getIntExtra("playTime",0));
        }else {
            player.cueVideo(vidieoID);
        }
    }

    // 해당하는 데이터 베이스는 SQLite Database 를 활용하여 저장할걸로 변경
    @Override
    public void onBackPressed(){    // 뒤로가기 버튼을 눌렀을 시

        players.pause();

        int User_SEQ = 1;
        int Detail_SEQ = 1;
        int View_TIME = players.getCurrentTimeMillis();  // 영상을 실행 한 후 경과된 시간을 리턴

        insertToDatabase(Integer.toString(User_SEQ),Integer.toString(Detail_SEQ),Integer.toString(View_TIME));

        super.onBackPressed();
    }

    public void insertToDatabase(String User_SEQ, String Detail_SEQ, String View_TIME){
        class InsertData extends AsyncTask<String, Void, String>{
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
                try {
                    int User_SEQ = Integer.parseInt(params[0]);
                    int Detail_SEQ = Integer.parseInt(params[1]);
                    int View_TIME = Integer.parseInt(params[2]);

                    String link = "https://mw-zhdtw.run.goorm.io/insertViewRecord.php?User_SEQ=" + User_SEQ + "&Detail_SEQ=" + Detail_SEQ + "&View_TIME=" + View_TIME;

                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();
                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write(link);
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
        task.execute(User_SEQ,Detail_SEQ,View_TIME);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason){
        if(errorReason.isUserRecoverableError()){
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        }else{
            String errorMessage = String.format(getString(R.string.error_player), errorReason.toString());
            Toast.makeText(this,errorMessage, Toast.LENGTH_SHORT).show();
        }
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider(){
        return (YouTubePlayerView)findViewById(R.id.youtubeplayer);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == RECOVERY_DIALOG_REQUEST){
            getYouTubePlayerProvider().initialize(DeveloperKey.DEVELOPER_KEY,this);
        }
    }

}


