package com.pfa.cameraupload;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SeanceEtHolder extends RecyclerView.ViewHolder  {
    TextView Element,Debut,Fin,Type,Salle;
    SeanceEtHolder(@NonNull View itemView) {
        super(itemView);
        this.Element = itemView.findViewById(R.id.ElementId);
        this.Debut = itemView.findViewById(R.id.DebutSeance);
        this.Fin = itemView.findViewById(R.id.FinSeance);
        this.Type = itemView.findViewById(R.id.TypeSeance);
        this.Salle = itemView.findViewById(R.id.SalleSeance);
    }
}
