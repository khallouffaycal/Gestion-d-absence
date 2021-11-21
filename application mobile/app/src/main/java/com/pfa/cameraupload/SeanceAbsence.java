package com.pfa.cameraupload;

import java.util.Arrays;

public class SeanceAbsence {
    private long idSeance;
    private long[] etudiants;
    public SeanceAbsence() {
    }
    public SeanceAbsence(long idSeance, long[] etudiants) {
        super();
        this.idSeance = idSeance;
        this.etudiants = etudiants;
    }
    public long getIdSeance() {
        return idSeance;
    }
    public void setIdSeance(long idSeance) {
        this.idSeance = idSeance;
    }
    public long[] getEtudiants() {
        return etudiants;
    }
    public void setEtudiants(long[] etudiants) {
        this.etudiants = etudiants;
    }
    @Override
    public String toString() {
        return "SeanceAbsence [idSeance=" + idSeance + ", etudiants=" + Arrays.toString(etudiants) + "]";
    }
}
