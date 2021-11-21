package com.pfa.cameraupload;

public class Seance {
    private long idSeance;
    private String debut;
    private String fin;
    private String element;
    private String type;
    private String salle;

    public long getIdSeance() {
        return idSeance;
    }

    public String getDebut() {
        return debut;
    }

    public String getFin() {
        return fin;
    }

    public String getElement() {
        return element;
    }

    public String getSalle() {
        return salle;
    }

    public String getType() {
        return type;
    }

    public void setIdSeance(long idSeance) {
        this.idSeance = idSeance;
    }

    public void setDebut(String debut) {
        this.debut = debut;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    @Override
    public String toString() {
        return "Seance{" +
                "idSeance=" + idSeance +
                ", Debut='" + debut + '\'' +
                ", Fin='" + fin + '\'' +
                ", Element='" + element + '\'' +
                ", Type='" + type + '\'' +
                ", Salle='" + salle + '\'' +
                '}';
    }
}
