package com.pfa.cameraupload;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class SeanceEtActivity extends AppCompatActivity {
    RecyclerView mRecycleView;
    SeanceEtAdapter myAdapter;
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Seances");
        setContentView(R.layout.activity_seance_et);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1f2649")));
        sharedpreferences = getSharedPreferences("Keys", Context.MODE_PRIVATE);



        mRecycleView = findViewById(R.id.RecycleSeance);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        try {
            Intent intent = getIntent();
            String TypeSea=intent.getStringExtra("Type");
            if(TypeSea.equalsIgnoreCase("Normale"))
                myAdapter = new SeanceEtAdapter(this, getSeanceList(sharedpreferences.getString("APIToken","")));
            if(TypeSea.equalsIgnoreCase("All")) {
                myAdapter = new SeanceEtAdapter(this,getSeanceListAll(sharedpreferences.getString("APIToken","")));
            }
            if(TypeSea.equalsIgnoreCase("Absence")) {
                myAdapter = new SeanceEtAdapter(this,getAbsence(sharedpreferences.getString("APIToken","")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        mRecycleView.setAdapter(myAdapter);


    }


    private ArrayList<Seance> getSeanceList(String auth) throws IOException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        JsonPlaceHolderApi service =
                ServiceGenerator.createService(JsonPlaceHolderApi.class);
        Call<ArrayList<Seance>> call = service.getSeancesEt(auth);
        ArrayList<Seance> seances;
        Response<ArrayList<Seance>> seancesr = call.execute();
        seances=seancesr.body();
        return seances;
    }
    private ArrayList<Seance> getSeanceListAll(String auth) throws IOException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        JsonPlaceHolderApi service =
                ServiceGenerator.createService(JsonPlaceHolderApi.class);
        Call<ArrayList<Seance>> call = service.getSeancesEtAll(auth);
        ArrayList<Seance> seances;
        Response<ArrayList<Seance>> seancesr = call.execute();
        seances=seancesr.body();
        return seances;
    }
    private ArrayList<Seance> getAbsence(String auth) throws IOException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        JsonPlaceHolderApi service =
                ServiceGenerator.createService(JsonPlaceHolderApi.class);
        Call<ArrayList<Seance>> call = service.getSeancesEtAbs(auth);
        ArrayList<Seance> seances;
        Response<ArrayList<Seance>> seancesr = call.execute();
        seances=seancesr.body();
        return seances;
    }
}
