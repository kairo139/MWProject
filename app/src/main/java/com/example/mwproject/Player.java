package com.example.mwproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class Player extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    String vidieoID = "ok9sgJtaIvY";    // 데이터 베이스 완벽히 연결 후 실행 하려는 유튜브 영상 아이디 가져오기
    YouTubePlayer players;

    private static final int RECOVERY_DIALOG_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player);

        getYouTubePlayerProvider().initialize(DeveloperKey.DEVELOPER_KEY,this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored){
        players = player;

        if(getIntent().hasExtra("playTime")){
            player.cueVideo(vidieoID,getIntent().getIntExtra("playTime",0));
        }else {
            player.cueVideo(vidieoID);
        }/*
                1.이부분에다가 책갈피에서 들어온거면 sqlite 확인해서 시간 가져와서 재생
                player.seekToMillis(이부분에 가져온 시간 대입);
                player.play();
                2.그냥 다른 부분에서 들어온거면 처음부터 실행
                player.cueVideo(vidieoID);
                player.play();
             */

    }

    // 해당하는 데이터 베이스는 SQLite Database 를 활용하여 저장할걸로 변경
    @Override
    public void onBackPressed(){    // 뒤로가기 버튼을 눌렀을 시
        players.pause();
        int pauseTime = players.getCurrentTimeMillis();  // 영상을 실행 한 후 경과된 시간을 리턴

        /*
            insert into ViewRecord(User_id,Video_id,Date,Time) values(AI 사용해서 자동 올리기, User_ID(일단 test), Video_ID(vidieoID), sysdate() , pauseTime)
            이부분 php 들어가야 합니다.
         */

        super.onBackPressed();
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

