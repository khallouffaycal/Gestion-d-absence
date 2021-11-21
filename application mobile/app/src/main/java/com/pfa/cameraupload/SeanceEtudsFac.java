package com.pfa.cameraupload;

import java.io.Serializable;

public class SeanceEtudsFac implements Serializable {
    private long idEtudiants;
    private String nomComplets;
    private int abs;
    public SeanceEtudsFac() {
    }
    public SeanceEtudsFac(long idEtudiants, String nomComplets, int abs) {
        this.idEtudiants = idEtudiants;
        this.nomComplets = nomComplets;
        this.abs = abs;
    }

    public long getIdEtudiants() {
        return idEtudiants;
    }

    public void setIdEtudiants(long idEtudiants) {
        this.idEtudiants = idEtudiants;
    }

    public String getNomComplets() {
        return nomComplets;
    }

    public void setNomComplets(String nomComplets) {
        this.nomComplets = nomComplets;
    }

    public int getAbs() {
        return abs;
    }

    public void setAbs(int abs) {
        this.abs = abs;
    }

    @Override
    public String toString() {
        return "SeanceEtudsFac{" +
                "idEtudiants=" + idEtudiants +
                ", nomComplets='" + nomComplets + '\'' +
                ", Abs=" + abs +
                '}';
    }
}
