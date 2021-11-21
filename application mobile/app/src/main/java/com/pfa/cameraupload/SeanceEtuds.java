package com.pfa.cameraupload;


public class SeanceEtuds {
    private long idEtudiants;
    private String nomComplets;
    public SeanceEtuds() {
    }
    public SeanceEtuds(String nomComplets,long idEtudiants) {
        super();
        this.nomComplets = nomComplets;
        this.idEtudiants = idEtudiants;
    }
    public String getNomComplets() {
        return nomComplets;
    }
    public void setNomComplets(String nomComplets) {
        this.nomComplets = nomComplets;
    }
    public long getIdEtudiants(){return idEtudiants;}
    public void setIdEtudiants(long idEtudiants){this.idEtudiants = idEtudiants;}
    @Override
    public String toString() {
        return "SeanceEtuds{" +
                "idEtudiants=" + idEtudiants +
                ", nomComplets='" + nomComplets + '\'' +
                '}';
    }
}