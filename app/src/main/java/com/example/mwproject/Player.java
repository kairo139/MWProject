package com.example.mwproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
        if(!wasRestored){
            player.cueVideo(vidieoID);
            /*
                1.이부분에다가 책갈피에서 들어온거면 sqlite 확인해서 시간 가져와서 재생
                player.seekToMillis(이부분에 가져온 시간 대입);
                player.play();
                2.그냥 다른 부분에서 들어온거면 처음부터 실행
                player.cueVideo(vidieoID);
                player.play();
             */
        }
    }

    // 해당하는 데이터 베이스는 SQLite Database 를 활용하여 저장할걸로 변경
    @Override
    public void onBackPressed(){    // 뒤로가기 버튼을 눌렀을 시
        int pauseTime = players.getCurrentTimeMillis();  // 영상을 실행 한 후 경과된 시간을 리턴
        /*
            vidieoID / pauseTime
            DB 설계 다시한후에 sqlite 설정 끝나면 이 부분에다가 가져가서  db에 저장 후 활용
         */
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

