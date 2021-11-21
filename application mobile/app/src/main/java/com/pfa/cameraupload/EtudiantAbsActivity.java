package com.pfa.cameraupload;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EtudiantAbsActivity extends AppCompatActivity {
    RecyclerView mRecycleView;
    AbsEtudAdapter myAdapter;
    Button remButton;
    SharedPreferences sharedpreferences;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Liste des etudiants absents");
        setContentView(R.layout.activity_etudiant_abs);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1f2649")));
        remButton = findViewById(R.id.btnAddRemAbs);
        mRecycleView = findViewById(R.id.RecycleSeance);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        sharedpreferences = getSharedPreferences("Keys", Context.MODE_PRIVATE);
        try {
            myAdapter = new AbsEtudAdapter(this, getAbsentEtud(sharedpreferences.getString("APIToken","")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        remButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                long idSeance = Long.parseLong(Objects.requireNonNull(intent.getStringExtra("idSeance")));
                long[] tmc = new long[myAdapter.idEtudiants.size()];
                SeanceAbsence tms = new SeanceAbsence();
                for(int i=0;i<myAdapter.idEtudiants.size();i++){
                    tmc[i]=myAdapter.idEtudiants.get(i);
                }
                tms.setIdSeance(idSeance);
                tms.setEtudiants(tmc);
                if(myAdapter.idEtudiants.size()>0){
                    try {
                        Toast.makeText(EtudiantAbsActivity.this,RemoveAbsence(sharedpreferences.getString("APIToken",""),tms),Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(EtudiantAbsActivity.this,"Une erreur est survenue",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(EtudiantAbsActivity.this,"Aucun etudiant selectionné",Toast.LENGTH_SHORT).show();
                }

            }
        });
        mRecycleView.setAdapter(myAdapter);
    }
    private ArrayList<SeanceEtuds> getAbsentEtud(String auth) throws IOException {
        Intent intent = getIntent();
        long idSeance = Long.parseLong(Objects.requireNonNull(intent.getStringExtra("idSeance")));
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        JsonPlaceHolderApi service =
                ServiceGenerator.createService(JsonPlaceHolderApi.class);
        Call<ArrayList<SeanceEtuds>> call = service.getEtudiantsAbs(auth,idSeance);
        ArrayList<SeanceEtuds> seances;
        Response<ArrayList<SeanceEtuds>> seancesr = call.execute();
        seances=seancesr.body();
        if (seances!=null) if(seances.size()!=0) {
            remButton.setVisibility(View.VISIBLE);
            remButton.setText("Supprimer");
        }
        if (seances==null) return new ArrayList<SeanceEtuds>();
        return seances;
    }
    private String RemoveAbsence(String auth,SeanceAbsence seanceAbsence) throws IOException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        JsonPlaceHolderApi service =
                ServiceGenerator.createService(JsonPlaceHolderApi.class);
        Call<SeanceAbsence> call = service.RemoveAbsence(auth,seanceAbsence);
        ArrayList<SeanceEtuds> seances;
        Response<SeanceAbsence> seancesr = call.execute();
        if(seancesr.isSuccessful()) return "Absence supprimé";
        return "Une erreur est survenue";
    }
}
