package com.pfa.cameraupload;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AbsEtudHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView Nom;
    CheckBox chk;

    ItemClickListener itemClickListener;
    AbsEtudHolder(@NonNull View itemView) {
        super(itemView);
        this.Nom = itemView.findViewById(R.id.Nom);
        this.chk = itemView.findViewById(R.id.checkboxetu);

        this.chk.setOnClickListener(this);


    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClickListener(v,getLayoutPosition());
    }
}

