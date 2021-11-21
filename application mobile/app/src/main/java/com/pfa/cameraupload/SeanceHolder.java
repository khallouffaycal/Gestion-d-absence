package com.pfa.cameraupload;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SeanceHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView Element,Debut,Fin,Type,Salle;
    ItemClickListener itemClickListener;
    SeanceHolder(@NonNull View itemView) {
        super(itemView);
        this.Element = itemView.findViewById(R.id.ElementId);
        this.Debut = itemView.findViewById(R.id.DebutSeance);
        this.Fin = itemView.findViewById(R.id.FinSeance);
        this.Type = itemView.findViewById(R.id.TypeSeance);
        this.Salle = itemView.findViewById(R.id.SalleSeance);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClickListener(v,getLayoutPosition());
    }
    public void setItemClickListener(ItemClickListener ic ){
        this.itemClickListener = ic;
    }
}
