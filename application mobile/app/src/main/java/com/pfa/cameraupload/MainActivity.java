package com.pfa.cameraupload;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    private static final int REQUEST_ID_CAMERA_RECORD_FILE_PERMISSION = 3 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Gestion d'absence");
        setContentView(R.layout.activity_main);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1f2649")));
        sharedpreferences = getSharedPreferences("Keys", Context.MODE_PRIVATE);
        if(sharedpreferences.getString("APIToken","").equals("")){
            moveToLogin();
        }
        if(sharedpreferences.getString("Type","").equals("etudiant")){
            moveToStud();
        }
        if(sharedpreferences.getString("Type","").equals("enseignant")){
            moveToProf();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        askPermission();

    }
    //ask for permission
    private void askPermission(){
        // With Android Level >= 23, you have to ask the user
        // for permission to read/write data on the device.
        if(Build.VERSION.SDK_INT>=23){
            int cameraPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
            int microPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
            int writePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int readPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            if(cameraPermission!= PackageManager.PERMISSION_GRANTED && microPermission!=PackageManager.PERMISSION_GRANTED &&
                    writePermission!=PackageManager.PERMISSION_GRANTED && readPermission!=PackageManager.PERMISSION_GRANTED){
                // If don't have permission so prompt the user.
                ActivityCompat.requestPermissions(this,
                        new String[]{ Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_ID_CAMERA_RECORD_FILE_PERMISSION);
                return;
            }

        }
    }
    private void moveToProf(){
        Intent intent = new Intent(MainActivity.this, ProfActivity.class);
        startActivity(intent);
    }private void moveToStud(){
        Intent intent = new Intent(MainActivity.this, StudActivity.class);
        startActivity(intent);
    }
    private void moveToLogin(){
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

}