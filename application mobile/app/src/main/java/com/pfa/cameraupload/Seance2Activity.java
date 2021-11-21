package com.pfa.cameraupload;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class Seance2Activity extends AppCompatActivity {
    TextView ElementT,DebutT,FinT,TypeT,SalleT;
    Button Abs,Etds,FacRec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seance2);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1f2649")));
        ElementT = findViewById(R.id.ElementId);
        DebutT = findViewById(R.id.DebutSeance);
        FinT = findViewById(R.id.FinSeance);
        TypeT = findViewById(R.id.TypeSeance);
        SalleT = findViewById(R.id.SalleSeance);
        Abs = findViewById(R.id.btnAbsence);
        Etds = findViewById(R.id.btnEtudiants);
        FacRec = findViewById(R.id.btnReconnaissanceFacial);

        Intent intent = getIntent();

        long idSeance = Long.parseLong(Objects.requireNonNull(intent.getStringExtra("idSeance")));
        String Element = intent.getStringExtra("Element");
        String Debut = intent.getStringExtra("Debut");
        String Fin = intent.getStringExtra("Fin");
        String Salle = intent.getStringExtra("Salle");
        String Type = intent.getStringExtra("Type");
        setTitle(Type+" "+Element);
        ElementT.setText(Element);
        DebutT.setText(Debut);
        FinT.setText(Fin);
        SalleT.setText(Salle);
        TypeT.setText(Type);
        Etds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Seance2Activity.this, EtudiantAllActivity.class);
                intent.putExtra("idSeance",""+idSeance);
                startActivity(intent);
            }
        });
        Abs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Seance2Activity.this, EtudiantAbsActivity.class);
                intent.putExtra("idSeance",""+idSeance);
                startActivity(intent);
            }
        });
        FacRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToFaceRec();
            }
        });
    }
    private void moveToFaceRec(){
        Intent in = getIntent();
        long idSeance = Long.parseLong(Objects.requireNonNull(in.getStringExtra("idSeance")));
        Intent intent = new Intent(Seance2Activity.this, FacRecActivity.class);
        intent.putExtra("idSeance",""+idSeance);
        startActivity(intent);
    }
}
