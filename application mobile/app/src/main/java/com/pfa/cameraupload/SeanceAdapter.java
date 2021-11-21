package com.pfa.cameraupload;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SeanceAdapter extends RecyclerView.Adapter<SeanceHolder> {

    Context ct;
    List<Seance> seances;

    public SeanceAdapter(Context ct, List<Seance> seances) {
        this.ct = ct;
        this.seances = seances;
    }

    @NonNull
    @Override
    public SeanceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row,null);
        return new SeanceHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SeanceHolder holder, int position) {
        if(seances.size()==0)
            return;
        holder.Element.setText(seances.get(position).getElement());
        holder.Debut.setText(seances.get(position).getDebut());
        holder.Fin.setText(seances.get(position).getFin());
        holder.Type.setText(seances.get(position).getType());
        holder.Salle.setText(seances.get(position).getSalle());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                String Element = seances.get(position).getElement();
                String Debut = seances.get(position).getDebut();
                String Fin = seances.get(position).getFin();
                String Type = seances.get(position).getType();
                String Salle = seances.get(position).getSalle();
                long idSeance = seances.get(position).getIdSeance();
                Intent intent = new Intent(ct,Seance2Activity.class);
                intent.putExtra("Element",Element);
                intent.putExtra("Debut",Debut);
                intent.putExtra("Fin",Fin);
                intent.putExtra("Type",Type);
                intent.putExtra("Salle",Salle);
                intent.putExtra("idSeance",""+idSeance);
                Log.d("idSeance",""+idSeance);
                ct.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(seances==null) return 0;
        return seances.size();
    }
}
