package com.pfa.cameraupload;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudActivity extends AppCompatActivity {
    private Button SeaAll;
    private Button SeaBut;
    private Button Abs;
    private Button logOut;
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Gestion d'absence");
        setContentView(R.layout.activity_stud);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1f2649")));
        SeaAll = findViewById(R.id.btnSeancesAll);
        SeaBut = findViewById(R.id.btnSeances);
        Abs = findViewById(R.id.btnSeancesAbs);
        logOut = findViewById(R.id.btnLogOut);
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
        Abs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToAbsence();
            }
        });
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();
            }
        });
    }
    private void moveToSeance(){
        Intent intent = new Intent(StudActivity.this, SeanceEtActivity.class);
        intent.putExtra("Type","Normale");
        startActivity(intent);
    }
    private void moveToSeanceAll(){
        Intent intent = new Intent(StudActivity.this, SeanceEtActivity.class);
        intent.putExtra("Type","All");
        startActivity(intent);
    }
    private void moveToAbsence(){
        Intent intent = new Intent(StudActivity.this, SeanceEtActivity.class);
        intent.putExtra("Type","Absence");
        startActivity(intent);
    }
    private void Logout(){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
        Intent intent = new Intent(StudActivity.this, LoginActivity.class);
        startActivity(intent);
    }


}