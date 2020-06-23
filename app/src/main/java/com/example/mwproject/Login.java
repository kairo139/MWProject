package com.example.mwproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    EditText edtID, edtPW;
    Button btnLogin,btnSignUp, btnClose;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_dialog);
        btnLogin = findViewById(R.id.btnLogin); btnSignUp = findViewById(R.id.btnSignUp); btnClose = findViewById(R.id.cancel);
        edtID = findViewById(R.id.edtID); edtPW = findViewById(R.id.edtPW);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(edtID);
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        edtID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),membership.class);
                startActivity(intent);
            }
        });
    }
}
