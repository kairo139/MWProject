package com.example.mwproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

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

public class MainActivity extends AppCompatActivity{
    Button btnPchange, btnLogin, btnLogOut;
    ImageButton btnSearch;
    View header;
    EditText edtID, edtPW;
    Button Login,btnSignUp, btnClose;
    String inputUID, inputPW;
    String uid, upw, uNickname;
    int uSEQ;

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentHome fragmentHome = new FragmentHome();
    private FragmentRanking fragmentRanking = new FragmentRanking();
    private FragmentCategory fragmentCategory = new FragmentCategory();
    //private FragmentEpisode episode = new FragmentEpisode();
    private search_resultActivity search_resultActivity = new search_resultActivity();

    DrawerLayout drawerLayout;

    NavigationView side_nav;

    public static final int sub = 1001;

    public static Context mContext;

    String myJSON;

    private static final String TAG_URESULTS = "User_result";
    private static final String TAG_USEQ = "User_SEQ";
    private static final String TAG_UID = "User_ID";
    private static final String TAG_UPW = "User_PW";
    private static final String TAG_NICKNAME = "User_NICKNAME";
    private static final String TAG_BIRTH = "User_BIRTH";
    private static final String TAG_GENDER = "User_GENDER";

    AlertDialog.Builder ad; DialogInterface ad2;

    JSONArray userDB = null;

    ArrayList<HashMap<String, String>> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        //툴바
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //사이드 바
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,0,0);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        side_nav = (NavigationView) findViewById(R.id.side_nav);

        notLogIn(); //default(비 로그인)

        btnSearch = findViewById(R.id.ibSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, searchActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        //바텀 네비게이션 (fragment)
        FragmentTransaction transaction = fragmentManager.beginTransaction(); //맨 처음에 나타날 frameLayout 설정
        transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());

        //사이드 바
        side_nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.recently:
                        intent = new Intent(getApplicationContext(),FragmentStorage.class);
                        startActivity(intent);
                        break;
                    case R.id.storageDrama:
                        break;
                    case R.id.btnLogin:
                        //Toast.makeText(getApplicationContext(),"d",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.advice:
                        intent = new Intent(getApplicationContext(),advice.class);
                        startActivity(intent);
                        break;
                    case R.id.dbtest:
                        intent = new Intent(getApplicationContext(),dbtestt.class);
                        startActivity(intent);
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){

        }
    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem){
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch(menuItem.getItemId())
            {
                case R.id.home:
                    transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();
                    break;
                case R.id.ranking:
                    transaction.replace(R.id.frameLayout, fragmentRanking).commitAllowingStateLoss();
                    break;
                case R.id.category:
                    transaction.replace(R.id.frameLayout, fragmentCategory).commitAllowingStateLoss();
                    break;
            }
            return true;
        }

    }

    private void showLoginDialog(){
        LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final ConstraintLayout loginLayout = (ConstraintLayout) li.inflate(R.layout.login_dialog,null);
        ad = new AlertDialog.Builder(this).setView(loginLayout);
        ad.create();
        ad2 = ad.show();

        edtID = loginLayout.findViewById(R.id.edtID);   edtPW = loginLayout.findViewById(R.id.edtPW); btnClose = loginLayout.findViewById(R.id.cancel);
        Login = loginLayout.findViewById(R.id.login);   btnSignUp = loginLayout.findViewById(R.id.btnSignUp);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputUID = edtID.getText().toString(); inputPW = edtPW.getText().toString();

                userList = new ArrayList<HashMap<String, String>>();

                getData(inputUID,inputPW);

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad2.dismiss();
                Intent intentd = new Intent(getApplicationContext(),membership.class);
                startActivity(intentd);
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

    //로그인 method
    public void logIn(final String unickname){
        TextView userNickname;
        header = side_nav.inflateHeaderView(R.layout.side_header_login);
        btnPchange = header.findViewById(R.id.btnPchange);
        btnLogOut = header.findViewById(R.id.btnLogOut);
        userNickname = header.findViewById(R.id.tvUserName);
        userNickname.setText(unickname);

        //정보수정 버튼
        btnPchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ProfileChange.class);
                startActivity(intent);
            }
        });

        //logout
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                header.setVisibility(View.GONE);
                uid = null; upw = null; uNickname = null;
                notLogIn();

            }
        });
    }

    private void notLogIn(){
        header = side_nav.inflateHeaderView(R.layout.side_header);
        btnLogin = header.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoginDialog();
            }
        });
    }



    public void SearchResultView(String str){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.addToBackStack(null); //이전 화면 기억
        System.out.println(str);
        transaction.replace(R.id.frameLayout, search_resultActivity).commitAllowingStateLoss();
    }

    public  void ToEpisodeActivity(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.addToBackStack(null); //이전 화면 기억
        //transaction.replace(R.id.frameLayout, episode).commitAllowingStateLoss();
    }

    protected void showList(String id, String pw) {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            userDB = jsonObj.getJSONArray(TAG_URESULTS);

            //사용자db정보 받기
            for (int i = 0; i < userDB.length(); i++) {
                JSONObject c = userDB.getJSONObject(i);
                uid = c.getString(TAG_UID);
                upw = c.getString(TAG_UPW);
                uNickname = c.getString(TAG_NICKNAME);

                if(uid.equals(id) && upw.equals(pw)){
                    header.setVisibility(View.GONE);
                    logIn(uNickname);
                    uSEQ = Integer.parseInt(c.getString(TAG_USEQ));
                    ad2.dismiss();
                }
                Log.d("U_SQE", String.valueOf(uSEQ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getData(final String uid, final String upw) {
        String url = "https://mw-zhdtw.run.goorm.io/PHP_login.php";
        class GetDataJSON extends AsyncTask<String, Void, String> {
            String id = uid; String pw = upw;

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
                showList(id,pw);
            }
        }

        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }
}
