package com.pfa.cameraupload;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private TextView logs;
    private Button loginbtn;
    private String tokens;
    private token tokenkey;
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Login");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1f2649")));
        setContentView(R.layout.activity_login);
        username= findViewById(R.id.username);
        password= findViewById(R.id.password);
        logs= findViewById(R.id.loglogin);
        loginbtn=findViewById(R.id.loginbutton);
        sharedpreferences = getSharedPreferences("Keys", Context.MODE_PRIVATE);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                try {
                    tokenkey = getToken(username.getText().toString(),password.getText().toString());
                    if(tokenkey==null) {
                        logs.setText("Login Failed");
                        logs.setVisibility(View.VISIBLE);
                    }
                    else{
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("APIToken", "Bearer <"+tokenkey.getToken()+">");
                        editor.putString("Type",tokenkey.getType());
                        editor.commit();
                        if(tokenkey.getType().equals("enseignant")){
                            Intent intent = new Intent(LoginActivity.this, ProfActivity.class);
                            startActivity(intent);
                        }
                        if(tokenkey.getType().equals("etudiant")){
                            Intent intent = new Intent(LoginActivity.this, StudActivity.class);
                            startActivity(intent);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
    }
    private token getToken(String username,String password) throws IOException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        JsonPlaceHolderApi service =
                ServiceGenerator.createService(JsonPlaceHolderApi.class);
        Call<token> call = service.getToken(username,password);
        Response<token> tokenResponse = call.execute();
        return tokenResponse.body();
    }
}
