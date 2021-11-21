package com.pfa.cameraupload;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SeanceActivity extends AppCompatActivity {
    RecyclerView mRecycleView;
    SeanceAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Seances");
        setContentView(R.layout.activity_seance);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1f2649")));



        mRecycleView = findViewById(R.id.RecycleSeance);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        try {
            Intent intent = getIntent();
            String TypeSea=intent.getStringExtra("Type");
            if(TypeSea.equalsIgnoreCase("Normale"))
            myAdapter = new SeanceAdapter(this, getSeanceList());
            else {
                myAdapter = new SeanceAdapter(this,getSeanceListAll());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        mRecycleView.setAdapter(myAdapter);


    }


    private ArrayList<Seance> getSeanceList() throws IOException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        JsonPlaceHolderApi service =
                ServiceGenerator.createService(JsonPlaceHolderApi.class);
        Call<ArrayList<Seance>> call = service.getSeances("Bearer <TesT2>");
        ArrayList<Seance> seances;
        Response<ArrayList<Seance>> seancesr = call.execute();
        seances=seancesr.body();
        return seances;
    }
    private ArrayList<Seance> getSeanceListAll() throws IOException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        JsonPlaceHolderApi service =
                ServiceGenerator.createService(JsonPlaceHolderApi.class);
        Call<ArrayList<Seance>> call = service.getSeancesAll("Bearer <TesT2>");
        ArrayList<Seance> seances;
        Response<ArrayList<Seance>> seancesr = call.execute();
        seances=seancesr.body();
        return seances;
    }
}
