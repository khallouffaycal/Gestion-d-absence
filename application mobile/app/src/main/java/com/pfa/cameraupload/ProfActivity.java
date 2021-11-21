package com.pfa.cameraupload;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ProfActivity extends AppCompatActivity {
    private Button SeaAll;
    private Button SeaBut;
    private Button LogOut;
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Gestion d'absence");
        setContentView(R.layout.activity_prof);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1f2649")));

        SeaAll = findViewById(R.id.btnSeancesAll);
        SeaBut = findViewById(R.id.btnSeances);
        LogOut = findViewById(R.id.btnLogOut);
        sharedpreferences = getSharedPreferences("Keys", Context.MODE_PRIVATE);
        SeaAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToSeanceAll();
            }
        });
        SeaBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToSeance();
            }
        });
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();
            }
        });
    }
    private void moveToSeance(){
        Intent intent = new Intent(ProfActivity.this, SeanceActivity.class);
        intent.putExtra("Type","Normale");
        startActivity(intent);
    }
    private void moveToSeanceAll(){
        Intent intent = new Intent(ProfActivity.this, SeanceActivity.class);
        intent.putExtra("Type","All");
        startActivity(intent);
    }
    private void Logout(){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
        Intent intent = new Intent(ProfActivity.this, LoginActivity.class);
        startActivity(intent);
    }

}