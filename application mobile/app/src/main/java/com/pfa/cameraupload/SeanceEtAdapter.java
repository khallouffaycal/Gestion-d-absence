package com.pfa.cameraupload;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SeanceEtAdapter extends RecyclerView.Adapter<SeanceEtHolder> {

    Context ct;
    List<Seance> seances;

    public SeanceEtAdapter(Context ct, List<Seance> seances) {
        this.ct = ct;
        this.seances = seances;
    }

    @NonNull
    @Override
    public SeanceEtHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row,null);
        return new SeanceEtHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SeanceEtHolder holder, int position) {
        if(seances.size()==0)
            return;
        holder.Element.setText(seances.get(position).getElement());
        holder.Debut.setText(seances.get(position).getDebut());
        holder.Fin.setText(seances.get(position).getFin());
        holder.Type.setText(seances.get(position).getType());
        holder.Salle.setText(seances.get(position).getSalle());
    }

    @Override
    public int getItemCount() {
        if(seances==null) return 0;
        return seances.size();
    }
}
