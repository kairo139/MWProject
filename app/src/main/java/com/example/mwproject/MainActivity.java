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

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity{
    Button btnPchange, btnLogin, btnLogOut;
    ImageButton btnSearch;
    View header;
    EditText edtID, edtPW;
    Button Login,btnSignUp, btnClose;
    String id = "aaa"; String pw = "ab";

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentHome fragmentHome = new FragmentHome();
    private FragmentRanking fragmentRanking = new FragmentRanking();
    private FragmentCategory fragmentCategory = new FragmentCategory();
    private FragmentStorage fragmentStorage = new FragmentStorage();
    DrawerLayout drawerLayout;

    NavigationView side_nav;

    public static final int sub = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        //default(비 로그인)
        notLogIn();

        /*if(로그인 됐을때)
        header = side_nav.inflateHeaderView(R.layout.side_header_login);
        btnPchange = header.findViewById(R.id.btnPchange);
        //정보수정 버튼
        btnPchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ProfileChange.class);
                startActivity(intent);
            }
        });*/

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
                switch (item.getItemId()){
                    case R.id.recently:
                        Intent intent = new Intent(getApplicationContext(),FragmentStorage.class);
                        startActivity(intent);
                        break;
                    case R.id.storageDrama:
                        break;
                    case R.id.btnLogin:
                        System.out.println("sd");
                        Toast.makeText(getApplicationContext(),"d",Toast.LENGTH_SHORT).show();
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
        final AlertDialog.Builder ad = new AlertDialog.Builder(this).setView(loginLayout);
        ad.create();
        final DialogInterface ad2 = ad.show();

        edtID = loginLayout.findViewById(R.id.edtID);   edtPW = loginLayout.findViewById(R.id.edtPW); btnClose = loginLayout.findViewById(R.id.cancel);
        Login = loginLayout.findViewById(R.id.login);   btnSignUp = loginLayout.findViewById(R.id.btnSignUp);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = edtID.getText().toString(); String b = edtPW.getText().toString();
                if(a.equals(id) && b.equals(pw)){
                    header.setVisibility(View.GONE);
                    ad2.dismiss();
                    logIn();
                }
                else ;
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(edtPW.getText().toString());
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    //로그인 method
    private void logIn(){
        header = side_nav.inflateHeaderView(R.layout.side_header_login);
        btnPchange = header.findViewById(R.id.btnPchange);
        btnLogOut = header.findViewById(R.id.btnLogOut);

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


}
